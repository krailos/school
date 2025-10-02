package com.krailo.school.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.krailo.school.entity.Audience;
import com.krailo.school.entity.Teacher;

@Repository
public class TeacherDao {

    private static final String SQL_SELECT_ALL_TEACHERS = "SELECT * FROM teacher";
    private static final String SQL_SELECT_TEACHER_BY_ID = "SELECT * FROM teacher  WHERE id = ?";
    private static final String SQL_INSERT_TEACHER = """
            INSERT INTO teacher (first_name, second_name, last_name, phone, email, address, birth_date, description)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;
    private static final String SQL_DELETE_BY_ID = "DELETE FROM teacher WHERE id = ?";
    private static final String SQL_UPDATE_BY_ID = "UPDATE teacher SET name = ?, description = ? where id = ?";

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Teacher> teacherRowMapper;

    public TeacherDao(JdbcTemplate jdbcTemplate, RowMapper<Teacher> teacherRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.teacherRowMapper = teacherRowMapper;
    }

    public List<Teacher> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_TEACHERS, teacherRowMapper);
    }

    public Teacher findById(int id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_TEACHER_BY_ID, teacherRowMapper, id);
    }

    public int create(Teacher teacher) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_TEACHER, new String[] { "id" });
            ps.setString(1, teacher.getFirstName());
            ps.setString(2, teacher.getSecondName());
            ps.setString(3, teacher.getLastName());
            ps.setString(4, teacher.getPhone());
            ps.setString(5, teacher.getEmail());
            ps.setString(6, teacher.getAddress());
            ps.setDate(7, Optional.ofNullable(teacher.getBirthDate()).map(d -> Date.valueOf(d)).orElse(null));
            ps.setString(8, teacher.getDescription());
            return ps;
        }, keyHolder);
        teacher.setId(keyHolder.getKey().intValue());
        return teacher.getId();
    }

    public void update(Teacher teacher) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, teacher.getFirstName(), teacher.getSecondName(), teacher.getLastName(),
                teacher.getPhone(), teacher.getEmail(), teacher.getAddress(), Date.valueOf(teacher.getBirthDate()),
                teacher.getDescription());
    }

    public void deleteById(int id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

}
