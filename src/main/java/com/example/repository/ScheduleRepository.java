package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Schedule;

@Repository
public class ScheduleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Autowired
	private static final RowMapper<Schedule> SCHEDULE_ROW_MAPPER = (rs, i) -> {
		Schedule schedule = new Schedule();
		schedule.setId(rs.getInt("id"));
		schedule.setUserId(rs.getInt("user_id"));
		schedule.setTitle(rs.getString("title"));
		schedule.setDescription(rs.getString("description"));
		schedule.setKinds(rs.getString("kinds"));
		schedule.setStartDate(rs.getDate("start_date"));
		schedule.setStartTime(rs.getTime("start_time"));
		schedule.setStartDate(rs.getDate("end_date"));
		schedule.setStartTime(rs.getTime("end_time"));
		return schedule;

	};

	public List<Schedule> findByUserId(Integer userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("id,user_id,title,description,kinds,start_date,start_time,end_date,end_time ");
		sql.append("FROM ");
		sql.append("schedules ");
		sql.append("WHERE ");
		sql.append("user_id=:userId ");
		sql.append("OR ");
		sql.append("id= ");
		sql.append("(SELECT schedule_id FROM share WHERE shared_user_id = :userId);");
		String Sql = sql.toString();
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		return template.query(Sql, param, SCHEDULE_ROW_MAPPER);

	}

	public Integer insert(Schedule schedule) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ");
		sql.append("schedules(user_id, title, description, kinds, start_date, start_time, end_date, end_time) ");
		sql.append("VALUES(:userId, :title, :description, :kinds, :startDate, :startTime, :endDate, :endTime) ");
		sql.append("RETURNING id");

		SqlParameterSource param = new BeanPropertySqlParameterSource(schedule);

		Integer scheduleId = template.queryForObject(sql.toString(), param, Integer.class);

		return scheduleId;
	}

}
