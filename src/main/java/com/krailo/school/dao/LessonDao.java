package com.krailo.school.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import com.krailo.school.dao.mapper.LessonRowMapper;
import com.krailo.school.entity.Lesson;
import com.krailo.school.entity.Student;

@Repository
public class LessonDao {

    private static final String SQL_SELECT_ALL_LESSONS = "SELECT * FROM lesson";
    private static final String SQL_SELECT_LESSON_BY_ID = "SELECT * FROM lesson  WHERE id = ?";
    private static final String SQL_INSERT_LESSON = """
            INSERT INTO lesson (schedule_id, lesson_date)
            VALUES (?, ?)
            """;
    private static final String SQL_DELETE_BY_ID = "DELETE FROM lesson WHERE id = ?";
    private static final String SQL_UPDATE_BY_ID = """
            UPDATE lesson
            SET schedule_id = ?, lesson_date = ?
            WHERE id = ?""";
    private static final String SQL_INSERT_LESSONS_STUDENTS = "INSERT INTO lessons_students (lesson_id, student_id) VALUES(?, ?)";
    private static final String SQL_DELETE_LESSONS_STUDENTS = "DELETE FROM lessons_students WHERE lesson_id = ? AND student_id = ?";

    private JdbcTemplate jdbcTemplate;
    private LessonRowMapper lessonRowMapper;

    public LessonDao(JdbcTemplate jdbcTemplate, LessonRowMapper lessonRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.lessonRowMapper = lessonRowMapper;
    }

    public List<Lesson> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LESSONS, lessonRowMapper);
    }

    public Lesson findById(int id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_LESSON_BY_ID, lessonRowMapper, id);
    }

    public int create(Lesson lesson) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_LESSON, new String[] { "id" });
            ps.setInt(1, lesson.getSchedule().getId());
            ps.setDate(2, Optional.ofNullable(lesson.getLessonDate()).map(d -> Date.valueOf(d)).orElse(null));
            return ps;
        }, keyHolder);
        lesson.setId(keyHolder.getKey().intValue());
        for (Student student : lesson.getStudents()) {
            jdbcTemplate.update(SQL_INSERT_LESSONS_STUDENTS, lesson.getId(), student.getId());
        }
        return lesson.getId();
    }

    public void update(Lesson lesson) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, lesson.getSchedule().getId(),
                Optional.ofNullable(lesson.getLessonDate()).map(d -> Date.valueOf(d)).orElse(null), lesson.getId());
        List<Student> oldStudents = findById(lesson.getId()).getStudents();
        oldStudents.stream().filter(s -> !lesson.getStudents().contains(s)).
        forEach(s -> jdbcTemplate.update(SQL_DELETE_LESSONS_STUDENTS, lesson.getId(), s.getId()));
        lesson.getStudents().stream().filter(s -> !oldStudents.contains(s)).
        forEach(s -> jdbcTemplate.update(SQL_INSERT_LESSONS_STUDENTS, lesson.getId(), s.getId()));        
    }

    public void deleteById(int id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

}
