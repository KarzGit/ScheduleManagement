package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Schedule;
import com.example.service.ScheduleDetailService;

@Controller
@RequestMapping("/scheduleDetail")
public class ScheduleDetailController {

	@Autowired
	private ScheduleDetailService service;

	@RequestMapping("")
	public String showScheduleDetailPage(Integer id, Model model) {

		Schedule schedule = service.load(id);

		model.addAttribute(schedule);

		return "schedule_detail.html";
	}

}
