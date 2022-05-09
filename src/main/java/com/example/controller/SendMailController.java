package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;

import com.example.domain.Schedule;
import com.example.domain.User;
import com.example.service.MailService;

@Controller
@RequestMapping("/sendMail")
public class SendMailController {
	
	@Autowired
	private MailService mailService;

	@Autowired
	private HttpSession session;
	
	@RequestMapping("/sendScheduleMail")
	@ResponseBody
	public void sendScheduleMail(Model model) {
		User user = (User)session.getAttribute("user");
		List<Schedule> todaySchedule=null;
		todaySchedule = mailService.getTodaySchedule(user.getId()).stream()
				.sorted(Comparator.comparing(Schedule::getStartTime))
				.collect(Collectors.toList());
		
		
	
		Date today = new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd");
		
	
		  Map<String, Object> sendMailInfo = new HashMap<String, Object>();
		  sendMailInfo.put("toName", user.getName());
		  sendMailInfo.put("today",sdf.format(today) );
		  sendMailInfo.put("subject", "今日の予定");
		  sendMailInfo.put("link", "メール本文のリンク先アドレス");
		  sendMailInfo.put("todaySchedule",todaySchedule);
		  
		  Context context = new Context();
		  context.setVariables(sendMailInfo);

		 mailService.sendMail(context);
		
	}

}
