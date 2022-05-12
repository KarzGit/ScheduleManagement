package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.Context;

import com.example.domain.Schedule;
import com.example.domain.User;
import com.example.service.LoginService;
import com.example.service.MailService;

@Controller
@RequestMapping("/login")
@EnableScheduling

public class LoginController {
	

	@Autowired
	private MailService mailService;

	@Autowired
	private LoginService service;

	@RequestMapping("")
	public String showLoginPage() {
		return "login.html";
	}

	@Autowired
	private HttpSession session;

	@RequestMapping("/complete")
	
	public String login(String mail, String password, Model model) {
		User user = service.findByMailAndPassword(mail, password);

		if (user == null) {
			model.addAttribute("errorLogin", "メールまたはパスワードが異なります");
			return showLoginPage();
		}

		session.setAttribute("user", user);

		return "redirect:/calender";
	}
	
	
	
	
	@Scheduled(cron="0 0 7 * * *")
	public void sendScheduleMail() {
		
		
		Date today = new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
	
		
		List<Schedule> todaySchedule=null;
		todaySchedule = mailService.getTodaySchedule(1,sdf.format(today)).stream()
				.sorted(Comparator.comparing(Schedule::getStartTime))
				.collect(Collectors.toList());
	
		  Map<String, Object> sendMailInfo = new HashMap<String, Object>();
		  sendMailInfo.put("toName", "山田太郎");
		  sendMailInfo.put("today",sdf.format(today) );
		  sendMailInfo.put("subject", "今日の予定");
		  sendMailInfo.put("link", "メール本文のリンク先アドレス");
		  sendMailInfo.put("todaySchedule",todaySchedule);
		  
		  Context context = new Context();
		  context.setVariables(sendMailInfo);

		 mailService.sendMail(context);
		
		
	}
	
	

}
