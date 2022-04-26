package com.example.controller;

import java.sql.Date;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Schedule;
import com.example.form.UpdateScheduleForm;
import com.example.service.DeleteScheduleService;
import com.example.service.UpdateScheduleService;

@Controller
@RequestMapping("/update")
public class UpdateController {
	
	@Autowired
	private UpdateScheduleService updateScheduleService;
	
	@Autowired
	private DeleteScheduleService deleteScheduleService;
	
	@PostMapping("")
	public String update(UpdateScheduleForm form,Integer id) {
		Date startDate = Date.valueOf(form.getStartDate());
		System.out.println(form.getStartTime());
		Time startTime = Time.valueOf(form.getStartTime());
		Date endDate = Date.valueOf(form.getEndDate());
		Time endTime = Time.valueOf(form.getEndTime());
		Schedule schedule = new Schedule();
		schedule.setId(id);
		schedule.setTitle(form.getTitle());
		schedule.setDescription(form.getDescription());
		schedule.setStartDate(startDate);
		schedule.setEndDate(endDate);
		schedule.setStartTime(startTime);
		schedule.setEndTime(endTime);
		updateScheduleService.update(schedule);
		

		
		return "redirect:/scheduleDetail?id="+id;
	}
	
	

	@PostMapping("/deletedUpdate")
	public String delete(Integer id) {
		deleteScheduleService.deletedUpdate(id);
		return "redirect:/calender";
	}

}
