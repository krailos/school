package com.krailo.school.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.krailo.school.dao.mapper.ScheduleRowMapper;
import com.krailo.school.dao.mapper.StudentRowMapper;
import com.krailo.school.entity.Schedule;
import com.krailo.school.entity.Student;

@Repository
public class ScheduleDao {

    private static final String SQL_SELECT_ALL_SCHEDULES = "SELECT * FROM schedule";
    private static final String SQL_SELECT_SCHEDULE_BY_ID = "SELECT * FROM schedule  WHERE id = ?";
    private static final String SQL_INSERT_SCHEDULE = """
            INSERT INTO schedule (audience_id, gang_id, week_day, start_time, end_time)
            VALUES (?, ?, CAST (? AS week_day), ?, ?)
            """;
    private static final String SQL_DELETE_BY_ID = "DELETE FROM schedule WHERE id = ?";
    private static final String SQL_UPDATE_BY_ID = """
            UPDATE schedule SET audience_id = ?, gang_id = ?,
            week_day = CAST (? as week_day),  start_time = ?, end_time = ? where id = ?""";

    private JdbcTemplate jdbcTemplate;
    private ScheduleRowMapper scheduleRowMapper;

    public ScheduleDao(JdbcTemplate jdbcTemplate, ScheduleRowMapper scheduleRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.scheduleRowMapper = scheduleRowMapper;
    }

    public List<Schedule> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SCHEDULES, scheduleRowMapper);
    }

    public Schedule findById(int id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_SCHEDULE_BY_ID, scheduleRowMapper, id);
    }

    public int create(Schedule schedule) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_SCHEDULE, new String[] { "id" });
            ps.setInt(1, schedule.getAudience().getId());
            ps.setInt(2, schedule.getGang().getId());
            ps.setString(3, Optional.ofNullable(schedule.getWeekDay()).map(g -> g.name()).orElse(null));
            ps.setTime(4, Time.valueOf(schedule.getStartTime()));
            ps.setTime(5, Time.valueOf(schedule.getEndTime()));
            return ps;
        }, keyHolder);
        schedule.setId(keyHolder.getKey().intValue());
        return schedule.getId();
    }

    public void update(Schedule schedule) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, schedule.getAudience().getId(), schedule.getGang().getId(),
                schedule.getWeekDay().name(), schedule.getStartTime(), schedule.getEndTime(), schedule.getId());
    }

    public void deleteById(int id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

}
