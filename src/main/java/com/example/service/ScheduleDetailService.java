package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Schedule;
import com.example.repository.ScheduleRepository;

@Service
@Transactional
public class ScheduleDetailService {

	@Autowired
	private ScheduleRepository repository;

	public Schedule load(Integer id) {
		return repository.load(id);
	}

}
