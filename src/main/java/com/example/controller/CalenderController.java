package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Schedule;
import com.example.domain.User;
import com.example.service.CalenderService;

@Controller
@RequestMapping("/calender")
public class CalenderController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private CalenderService calenderService;
	
	
	@GetMapping("/showCalender")
	public String  showCalender(Model model) {
		if(session.getAttribute("user") != null) {
			List<Schedule> scheduleList = calenderService.findByUserId(((User)session.getAttribute("user")).getId());
			model.addAttribute("scheduleList",scheduleList);
			
		}
		//仮でUserId=2のユーザーのスケジュールを取得
		List<Schedule> scheduleList = calenderService.findByUserId(2);
		model.addAttribute("scheduleList",scheduleList);
	
		
		return "calender.html";
	}
	

}
