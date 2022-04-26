package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repository.ScheduleRepository;

@Service
@Transactional
public class DeleteScheduleService {
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	
	public void deletedUpdate(Integer id) {
		scheduleRepository.deletedUpdate(id);
	}

}
