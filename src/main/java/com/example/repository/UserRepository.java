package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
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

	private static final ResultSetExtractor<List<User>> USER_EXTRACTOR = (rs) -> {
		List<User> userList = new ArrayList<>();
		List<Integer> followedList = null;
		int nowId = 0;
		int beforeId = 0;

		while (rs.next()) {
			nowId = rs.getInt("id");
			if (nowId != beforeId) {
				User user = new User();
				userList.add(user);
				user.setId(nowId);
				user.setName(rs.getString("name"));
				user.setMail(rs.getString("mail"));
				user.setPassword(rs.getString("password"));
				user.setZipcode(rs.getInt("zipcode"));
				user.setIconImagePath(rs.getString("icon_image_path"));
				user.setMailNotification(rs.getInt("mail_notification"));

				followedList = new ArrayList<>();
				user.setFollowedList(followedList);
				beforeId = nowId;
			}

			followedList.add(rs.getInt("shared_user_id"));
		}

		return userList;
	};

	public User findByMailAndPassword(String mail, String password) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("id, name, mail, password, zipcode, icon_image_path, mail_notification, shared_user_id ");
		sql.append("FROM ");
		sql.append("users ");
		sql.append("LEFT JOIN ");
		sql.append("share ");
		sql.append("ON ");
		sql.append("id=parent_user_id ");
		sql.append("WHERE mail=:mail");
		SqlParameterSource param = new MapSqlParameterSource().addValue("mail", mail);
		List<User> userList = template.query(sql.toString(), param, USER_EXTRACTOR);

		if (userList.size() == 0) {
			return null;
		}
		if (!encoder.matches(password, userList.get(0).getPassword())) {
			return null;
		}

		return userList.get(0);
	}

}
