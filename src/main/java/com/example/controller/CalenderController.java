package com.example.controller;

import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.OpenWeatherMap;
import com.example.domain.Schedule;
import com.example.domain.User;
import com.example.service.CalenderService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/calender")
public class CalenderController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private CalenderService calenderService;


	@GetMapping("")
	public String showCalender(Model model) throws Exception {
		if (session.getAttribute("user") != null) {
			session.setAttribute("existUser", true);
			List<Schedule> scheduleList = calenderService.findByUserId(((User) session.getAttribute("user")).getId());
			model.addAttribute("scheduleList", scheduleList);

			// 緯度経度取得
			User user = (User) session.getAttribute("user");
			ObjectMapper latlonMapper = new ObjectMapper();

			StringBuilder latlonUrl = new StringBuilder();
			latlonUrl.append("http://api.openweathermap.org/geo/1.0/zip?zip=");
			latlonUrl.append(user.getZipcode());
			latlonUrl.append(",jp&appid=");
			latlonUrl.append(""); // OpenWeatherMapのAPIキー

			URL preUrl = new URL(latlonUrl.toString());
			JsonNode latlonDocument = latlonMapper.readTree(preUrl);

			OpenWeatherMap weatherMap = new OpenWeatherMap();
			weatherMap.setLat(latlonDocument.get("lat").doubleValue());
			weatherMap.setLon(latlonDocument.get("lon").doubleValue());

			// 天気取得
			ObjectMapper weatherMapper = new ObjectMapper();
			StringBuilder weatherUrl = new StringBuilder();
			weatherUrl.append("https://api.openweathermap.org/data/2.5/weather?lat=");
			weatherUrl.append(weatherMap.getLat());
			weatherUrl.append("&lon=");
			weatherUrl.append(weatherMap.getLon());
			weatherUrl.append("&appid=");
			weatherUrl.append(""); // OpenWeatherMapのAPIキー
			weatherUrl.append("&lang=ja&units=metric");

			URL url = new URL(weatherUrl.toString());
			JsonNode weatherDocument = weatherMapper.readTree(url);
			weatherMap.setTemp((int) weatherDocument.get("main").get("temp").doubleValue());

			for (JsonNode weather : weatherDocument.get("weather")) {
				weatherMap.setWeather(weather.get("description").textValue());
				weatherMap.setWeatherIconPath(weather.get("icon").textValue() + ".png");
			}

			model.addAttribute("weatherMap", weatherMap);
			model.addAttribute("weather", true);

		} else {
			session.setAttribute("existUser", false);
			model.addAttribute("weather", false);
		}

//		// 仮でUserId=2のユーザーのスケジュールを取得
//		List<Schedule> scheduleList = calenderService.findByUserId(2);
//		model.addAttribute("scheduleList", scheduleList);

		return "calender.html";
	}

}
