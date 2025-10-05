package com.krailo.school.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.krailo.school.dao.LessonDao;
import com.krailo.school.dao.ScheduleDao;
import com.krailo.school.dao.StudentDao;
import com.krailo.school.entity.Lesson;
import com.krailo.school.entity.Schedule;

@Component
public class LessonRowMapper implements RowMapper<Lesson> {

    ScheduleDao scheduleDao;
    StudentDao studentDao;


    public LessonRowMapper(ScheduleDao scheduleDao, StudentDao studentDao) {
        super();
        this.scheduleDao = scheduleDao;
        this.studentDao = studentDao;
    }


    @Override
    public Lesson mapRow(ResultSet rs, int rowNum) throws SQLException {
        Lesson lesson = new Lesson();
        lesson.setId(rs.getInt("id"));
        lesson.setSchedule(scheduleDao.findById(rs.getInt("schedule_id")));
        lesson.setLessonDate( (rs.getObject("lesson_date", LocalDate.class)));
        lesson.setStudents(studentDao.findByLessonId(rs.getInt("id")));
        return lesson;
    }

}
