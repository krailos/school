package com.krailo.school.dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.krailo.school.dao.mapper.GangRowMapper;
import com.krailo.school.entity.Gang;
import com.krailo.school.entity.Subject;

@Repository
public class GangDao {

    private static final String SQL_SELECT_ALL = "SELECT * FROM gang ";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM gang WHERE id = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM gang WHERE id = ?";
    private static final String SQL_INSERT_GANG = "INSERT INTO gang (subject_id, teacher_id, name, description ) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_BY_ID = "UPDATE gang SET subject_id = ?, teacher_id = ?, name = ?, description = ? where id = ?";

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Gang> gangRowMapper;

    public GangDao(JdbcTemplate jdbcTemplate, RowMapper<Gang> gangRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.gangRowMapper = gangRowMapper;
    }

    public List<Gang> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, gangRowMapper);
    }

    public Gang findById(int id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, gangRowMapper, id);
    }

    public void deleteById(int id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    public void update(Gang gang) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, gang.getSubject().getId(), gang.getTeacher().getId(), gang.getName(),
                gang.getDescription(), gang.getId());
    }

    public int create(Gang gang) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_GANG, new String[] { "id" });
            ps.setInt(1, gang.getSubject().getId());
            ps.setInt(2, gang.getTeacher().getId());
            ps.setString(3, gang.getName());
            ps.setString(4, gang.getDescription());
            return ps;
        }, keyHolder);
        gang.setId(keyHolder.getKey().intValue());
        return gang.getId();
    }

}
