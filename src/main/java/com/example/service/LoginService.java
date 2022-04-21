package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

@Service
@Transactional
public class LoginService {

	@Autowired
	private UserRepository repository;

	public User findByMailAndPassword(String mail, String password) {
		return repository.findByMailAndPassword(mail, password);
	}

}
