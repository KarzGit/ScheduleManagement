package com.example.service;

import java.util.List;
import java.util.Map;

import java.nio.charset.StandardCharsets;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.example.domain.Schedule;
import com.example.repository.ScheduleRepository;

@Service
public class MailService {
	
	@Autowired
	private  JavaMailSender javaMailSender;
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	

	public List<Schedule> getTodaySchedule(Integer userId){
		return scheduleRepository.getTodaySchedule(userId);
	}
	
	
	public void sendMail(Context context) {
		
		javaMailSender.send(new MimeMessagePreparator() {

	        @Override
	        public void prepare(MimeMessage mimeMessage) throws Exception {
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,StandardCharsets.UTF_8.name());
	          
	            helper.setTo("gettodayschedule@gmail.com"); //送り先
	            helper.setSubject("本日の予定");
	            helper.setText(getMailBody("mail", context), true);
	        }
	    });

	}
	
	private String getMailBody(String templateName, Context context) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(mailTemplateResolver());
		return templateEngine.process(templateName, context);
		
	}
	private ClassLoaderTemplateResolver mailTemplateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setPrefix("templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setCacheable(true);
		return templateResolver;
	}
	
	

}
