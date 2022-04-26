package com.example.controller;

import java.sql.Date;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Schedule;
import com.example.domain.User;
import com.example.form.RegisterForm;
import com.example.form.UpdateScheduleForm;
import com.example.service.RegisterService;
import com.example.service.UpdateScheduleService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@RequestMapping("")
	public String showRegisterPage() {
		return "register.html";
	}

	@Autowired
	private RegisterService service;
	
	@Autowired
	private UpdateScheduleService updateScheduleService;

	@ModelAttribute
	private RegisterForm setUpRegisterForm() {
		return new RegisterForm();
	}

	@RequestMapping("/insert")
	public String insert(@Validated RegisterForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return showRegisterPage();
		}

		Integer userId = service.findByMail(form.getMail());

		if (userId != null) {
			model.addAttribute("duplicateMailMessage", "そのメールアドレスはすでに使われています");
			return showRegisterPage();
		}

		User user = new User();
		user.setName(form.getName());
		user.setMail(form.getMail());
		user.setPassword(form.getPassword());
		user.setZipcode(Integer.parseInt(form.getZipcode()));
		if(!form.getIconImagePath().equals("")) {
			user.setIconImagePath(form.getIconImagePath());
		}else if(form.getIconImagePath().equals("")){
			user.setIconImagePath("default.png");
		}
		
		user.setMailNotification(Integer.parseInt(form.getMailNotification()));

		service.insert(user);

		return "redirect:/login";
	}
	
	@PostMapping("/update")
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

}
