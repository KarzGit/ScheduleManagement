package com.example.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Schedule;
import com.example.form.InsertScheduleForm;
import com.example.service.InsertScheduleService;

@Controller
@RequestMapping("/insertSchedule")
public class InsertScheduleController {

	@RequestMapping("")
	public String showInsertSchedulePage() {
		return "insert_schedule.html";
	}

	@Autowired
	private InsertScheduleService service;

	@ModelAttribute
	private InsertScheduleForm setUpForm() {
		return new InsertScheduleForm();
	}

	@RequestMapping("/insert")
	public String insert(InsertScheduleForm form, Model model) {
		Date startDate = Date.valueOf(form.getStartDate());
		Time startTime = Time.valueOf(form.getStartTime());
		Date endDate = Date.valueOf(form.getEndDate());
		Time endTime = Time.valueOf(form.getEndTime());

		Duration dateDuration = Duration.between(startDate.toLocalDate(), endDate.toLocalDate());

		if (dateDuration.toDays() < 0) {
			model.addAttribute("dateErrorMessage", "終了日時は開始日時以降の日時を入力してください");
			return showInsertSchedulePage();
		} else if (dateDuration.toDays() == 0) {
			Duration timeDuration = Duration.between(startTime.toLocalTime(), endTime.toLocalTime());
			if (timeDuration.toHours() <= 0) {
				model.addAttribute("dateErrorMessage", "終了日時は開始日時以降の日時を入力してください");
				return showInsertSchedulePage();
			}
		}

		Schedule schedule = new Schedule();
		schedule.setUserId(Integer.parseInt(form.getUserId()));
		schedule.setTitle(form.getTitle());
		schedule.setDescription(form.getDescription());
		schedule.setKinds(form.getKinds());
		schedule.setStartDate(Date.valueOf(form.getStartDate()));
		schedule.setStartTime(Time.valueOf(form.getStartTime()));
		schedule.setEndDate(Date.valueOf(form.getEndDate()));
		schedule.setEndTime(Time.valueOf(form.getEndTime()));

		return "redirect:/calender";
	}

}
