package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.RegisterForm;
import com.example.service.RegisterService;


@Controller
@RequestMapping("/register")
public class RegisterController {

	@RequestMapping("")
	public String showRegisterPage() {
		return "register.html";
	}

	@Autowired
	private RegisterService service;
	
	

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
	
	

}
