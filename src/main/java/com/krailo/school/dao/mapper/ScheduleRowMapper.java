package com.krailo.school.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.krailo.school.dao.AudienceDao;
import com.krailo.school.dao.GangDao;
import com.krailo.school.entity.Gender;
import com.krailo.school.entity.Schedule;
import com.krailo.school.entity.WeekDay;

@Component
public class ScheduleRowMapper implements RowMapper<Schedule> {

    AudienceDao audienceDao;
    GangDao gangDao;

    public ScheduleRowMapper(AudienceDao audienceDao, GangDao gangDao) {
        this.audienceDao = audienceDao;
        this.gangDao = gangDao;
    }

    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Schedule schedule = new Schedule();
        schedule.setId(rs.getInt("id"));
        schedule.setAudience(audienceDao.findById(rs.getInt("audience_id")));
        schedule.setGang(gangDao.findById(rs.getInt("gang_id")));
        schedule.setWeekDay(WeekDay.valueOf(rs.getString("week_day")));
        schedule.setStartTime(rs.getObject("start_time", LocalTime.class));
        schedule.setEndTime(rs.getObject("end_time", LocalTime.class));
        return schedule;
    }

}
