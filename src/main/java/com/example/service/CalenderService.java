package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Schedule;
import com.example.repository.ScheduleRepository;

@Service
@Transactional
public class CalenderService {
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	public List<Schedule> findByUserId(Integer userId){
		return scheduleRepository.findByUserId(userId);
	}

}
