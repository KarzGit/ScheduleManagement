package com.example.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.time.Period;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Schedule;
import com.example.domain.Share;
import com.example.domain.User;
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

	@Autowired
	private HttpSession session;

	@RequestMapping("/insert")
	public String insert(@Validated InsertScheduleForm form, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return showInsertSchedulePage();
		}
		
		Date startDate = Date.valueOf(form.getStartDate());
		Time startTime = Time.valueOf(form.getStartTime() + ":00");
		Date endDate = Date.valueOf(form.getEndDate());
		Time endTime = Time.valueOf(form.getEndTime() + ":00");

		Period period = Period.between(startDate.toLocalDate(), endDate.toLocalDate());

		if (period.isNegative()) {
			model.addAttribute("dateErrorMessage", "終了日時は開始日時以降の日時を入力してください");
			return showInsertSchedulePage();
		} else if (period.isZero()) {
			Duration timeDuration = Duration.between(startTime.toLocalTime(), endTime.toLocalTime());
			if (timeDuration.isZero() || timeDuration.isNegative()) {
				model.addAttribute("dateErrorMessage", "終了日時は開始日時以降の日時を入力してください");
				return showInsertSchedulePage();
			}
		}

		Schedule schedule = new Schedule();
		User user = (User) session.getAttribute("user");
		schedule.setUserId(user.getId());
		schedule.setTitle(form.getTitle());
		schedule.setDescription(form.getDescription());
		schedule.setKinds(form.getKinds());
		schedule.setStartDate(startDate);
		schedule.setStartTime(startTime);
		schedule.setEndDate(endDate);
		schedule.setEndTime(endTime);

		Integer scheduleId = service.insertSchedule(schedule);

		if (!form.getMemberMail().equals("")) {
			Integer memberId = service.findByMail(form.getMemberMail());

			Share share = new Share();
			share.setParentUserId(user.getId());
			share.setSharedUserId(memberId);
			share.setScheduleId(scheduleId);

			service.insertShare(share);
		}

		return "redirect:/calender";
	}

}
