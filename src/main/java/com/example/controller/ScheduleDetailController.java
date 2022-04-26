package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Schedule;
import com.example.form.UpdateScheduleForm;
import com.example.service.DeleteScheduleService;
import com.example.service.ScheduleDetailService;

@Controller
@RequestMapping("/scheduleDetail")
public class ScheduleDetailController {

	@Autowired
	private ScheduleDetailService service;
	
	@Autowired
	private DeleteScheduleService deleteScheduleService;
	
	@ModelAttribute
	public UpdateScheduleForm setUpForm() {
		return new UpdateScheduleForm();
	}

	@RequestMapping("")
	public String showScheduleDetailPage(Integer id, Model model) {

		Schedule schedule = service.load(id);

		model.addAttribute(schedule);

		return "schedule_detail.html";
	}
	
	
	
	@PostMapping("/deletedUpdate")
	public String delete(Integer id) {
		deleteScheduleService.deletedUpdate(id);
		return "redirect:/calender";
	}

}
