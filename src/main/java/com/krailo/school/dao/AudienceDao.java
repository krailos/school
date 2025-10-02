package com.krailo.school.dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.krailo.school.entity.Audience;

@Repository
public class AudienceDao {

    private static final String SQL_SELECT_ALL_AUDIENCES = "SELECT * FROM audience";
    private static final String SQL_SELECT_AUDIENCE_BY_ID = "SELECT * FROM audience  WHERE id = ?";
    private static final String SQL_INSERT_AUDIENCE = "INSERT INTO audience (name, description) VALUES (?, ?)";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM audience WHERE id = ?";
    private static final String SQL_UPDATE_BY_ID = "UPDATE audience SET name = ?, description = ? where id = ?";

    // @Autowired
    private JdbcTemplate jdbcTemplate;
    // @Autowired
    private RowMapper<Audience> audienceRowMapper;

    public AudienceDao(JdbcTemplate jdbcTemplate, RowMapper<Audience> audienceRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.audienceRowMapper = audienceRowMapper;
    }

    public List<Audience> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_AUDIENCES, audienceRowMapper);
    }

    public Audience findById(int id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_AUDIENCE_BY_ID, audienceRowMapper, id);
    }

    public int create(Audience audience) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_AUDIENCE, new String[] { "id" });
            ps.setString(1, audience.getName());
            ps.setString(2, audience.getDescription());
            return ps;
        }, keyHolder);
        audience.setId(keyHolder.getKey().intValue());
        return audience.getId();
    }

    public void update(Audience audience) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, audience.getName(), audience.getDescription(), audience.getId());       
    }
    
    public void deleteById(int id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
        }

}
