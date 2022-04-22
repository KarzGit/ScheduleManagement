package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Share;
import com.example.repository.ShareRepository;
import com.example.repository.UserRepository;

@Service
@Transactional
public class InsertScheduleService {

	@Autowired
	private UserRepository userRepository;

	public Integer findByMail(String mail) {
		return userRepository.findByMail(mail);
	}

	@Autowired
	private ShareRepository shareRepository;

	public void insertShare(Share share) {
		shareRepository.insert(share);
	}

}
