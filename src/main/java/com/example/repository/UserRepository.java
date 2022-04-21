package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

@Repository
public class UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	private PasswordEncoder encoder;

	/**
	 * ユーザ登録処理
	 * 
	 * @param user
	 */
	public void insert(User user) {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ");
		sql.append("users (name, mail, password, zipcode, icon_image_path, mail_notification) ");
		sql.append("VALUES ");
		sql.append("(:name, :mail, :password, :zipcode, :iconImagePath, :mailNotification) ");
		
		user.setPassword(encoder.encode(user.getPassword()));
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);

		template.update(sql.toString(), param);
	}

	/**
	 * メール重複チェック
	 * 
	 * @param mail
	 * @return id or null
	 */
	public Integer findByMail(String mail) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("id ");
		sql.append("FROM ");
		sql.append("users ");
		sql.append("WHERE ");
		sql.append("mail=:mail");

		SqlParameterSource param = new MapSqlParameterSource().addValue("mail", mail);

		Integer id;
		try {
			id = template.queryForObject(sql.toString(), param, Integer.class);
			return id;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
