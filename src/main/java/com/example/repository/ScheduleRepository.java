package com.example.repository;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		schedule.setEndDate(rs.getDate("end_date"));
		schedule.setEndTime(rs.getTime("end_time"));
		return schedule;

	};

	public List<Schedule> findByUserId(Integer userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("id,user_id,title,description,kinds,start_date,start_time,end_date,end_time ");
		sql.append("FROM ");
		sql.append("schedules ");
		sql.append("WHERE ");
		sql.append("(user_id=:userId ");
		sql.append("OR ");
		sql.append("id IN");
		sql.append("(SELECT schedule_id FROM share WHERE shared_user_id = :userId)) ");
		sql.append("AND ");
		sql.append("deleted is null ");
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

	public Schedule load(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("id, user_id, title, description, kinds, start_date, start_time, end_date, end_time ");
		sql.append("FROM ");
		sql.append("schedules ");
		sql.append("WHERE ");
		sql.append("id=:id ");

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		Schedule schedule = template.queryForObject(sql.toString(), param, SCHEDULE_ROW_MAPPER);
		
		return schedule;
	}
	
	public void update(Schedule schedule) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE " );
		sql.append("Schedules ");
		sql.append("SET ");
		sql.append("title=:title, description=:description,kinds=:kinds,start_date=:startDate,start_time=:startTime, ");
		sql.append("end_date=:endDate, end_time=:endTime ");
		sql.append("WHERE ");
		sql.append("id=:id;");
		
		SqlParameterSource param= new MapSqlParameterSource().addValue("title", schedule.getTitle()).addValue("description", schedule.getDescription())
				.addValue("kinds", schedule.getKinds()).addValue("startDate",schedule.getStartDate()).addValue("startTime", schedule.getStartTime())
				.addValue("endDate", schedule.getEndDate()).addValue("endTime", schedule.getEndTime()).addValue("id",schedule.getId());
		
		template.update(sql.toString(),param);
		
		
	}
	
	public void deletedUpdate(Integer id) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println(timestamp);
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ");
		sql.append("schedules ");
		sql.append("SET ");
		sql.append("deleted=:deleted ");
		sql.append("WHERE ");
		sql.append("id=:id");
		SqlParameterSource param = new MapSqlParameterSource().addValue("deleted", timestamp).addValue("id", id);
		template.update(sql.toString(),param);
	}
	
	public List<Schedule> getTodaySchedule(Integer userId) {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formatDate = sdf.format(today);
		Date formatDate2=null;
		try {
			 formatDate2 = sdf.parse(formatDate);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("id,user_id,title,description,kinds,start_date,start_time,end_date,end_time ");
		sql.append("FROM ");
		sql.append("schedules ");
		sql.append("WHERE ");
		sql.append("(user_id=:userId ");
		sql.append("OR ");
		sql.append("id IN");
		sql.append("(SELECT schedule_id FROM share WHERE shared_user_id = :userId)) ");
		sql.append("AND ");
		sql.append("start_date = :startDate ");
		sql.append("AND ");
		sql.append("deleted is null ");
		String Sql = sql.toString();
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("startDate", formatDate2);
		return template.query(Sql, param, SCHEDULE_ROW_MAPPER);

	}
	

}
