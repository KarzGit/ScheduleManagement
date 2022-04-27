package com.example.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

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
		System.out.println(form);
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
		String originalIconImagePath = form.getIconImageFile().getOriginalFilename();
		int lastDotIndex = originalIconImagePath.lastIndexOf(".");
		if (lastDotIndex != -1) {

			String extension = originalIconImagePath.substring(lastDotIndex);
			String imageName = UUID.randomUUID().toString();
			String newIconImagePath = imageName + extension;

			Path imagePath = Paths.get("src/main/resources/static/img/" + newIconImagePath);
			System.out.println(newIconImagePath);
			OutputStream os;
			try {
				os = Files.newOutputStream(imagePath, StandardOpenOption.CREATE);
				byte[] bytes = form.getIconImageFile().getBytes();
				os.write(bytes);
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

			user.setIconImagePath(newIconImagePath);
		} else if (lastDotIndex == -1) {
			user.setIconImagePath("default.png");
		}
		user.setMailNotification(Integer.parseInt(form.getMailNotification()));

		service.insert(user);

		return "redirect:/login";
	}

}
