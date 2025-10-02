package com.krailo.school.dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.krailo.school.entity.Subject;

@Repository
public class SubjectDao {
    private static final String SQL_SELECT_ALL_SUBJECTS = "SELECT * FROM subject";
    private static final String SQL_SELECT_SUBJECT_BY_ID = "SELECT * FROM subject  WHERE id = ?";
    private static final String SQL_INSERT_SUBJECT = "INSERT INTO subject (name, description) VALUES (?, ?)";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM subject WHERE id = ?";
    private static final String SQL_UPDATE_BY_ID = "UPDATE subject SET name = ?, description = ? where id = ?";

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Subject> subjectRowMapper;

    public SubjectDao(JdbcTemplate jdbcTemplate, RowMapper<Subject> subjectRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.subjectRowMapper = subjectRowMapper;
    }

    public List<Subject> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SUBJECTS, subjectRowMapper);
    }

    public Subject findById(int id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_SUBJECT_BY_ID, subjectRowMapper, id);
    }

    public int create(Subject subject) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_SUBJECT, new String[] { "id" });
            ps.setString(1, subject.getName());
            ps.setString(2, subject.getDescription());
            return ps;
        }, keyHolder);
        subject.setId(keyHolder.getKey().intValue());
        return subject.getId();
    }

    public void update(Subject subject) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, subject.getName(), subject.getDescription(), subject.getId());
    }

    public void deleteById(int id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

}
