package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Schedule;
import com.example.repository.ScheduleRepository;

@Service
@Transactional
public class UpdateScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;
	
	
	public void update(Schedule schedule) {
		scheduleRepository.update(schedule);
	}
}
