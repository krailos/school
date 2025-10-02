package com.krailo.school.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.krailo.school.entity.Audience;

@Component
public class AudienceRowMapper implements RowMapper<Audience> {

    @Override
    public Audience mapRow(ResultSet rs, int rowNum) throws SQLException {
        Audience audience = new Audience();
        audience.setId(rs.getInt("id"));
        audience.setName(rs.getString("name"));
        audience.setDescription(rs.getString("description"));
        return audience;
    }

}
