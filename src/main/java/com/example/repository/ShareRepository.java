package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Share;

@Repository
public class ShareRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	public void insert(Share share) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ");
		sql.append("share ");
		sql.append("VALUES(:parentUserId, :sharedUserId, :scheduleId)");

		SqlParameterSource param = new BeanPropertySqlParameterSource(share);

		template.update(sql.toString(), param);
	}

}
