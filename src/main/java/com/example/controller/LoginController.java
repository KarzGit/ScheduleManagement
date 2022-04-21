package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {

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

}
