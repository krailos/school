package com.krailo.school.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.krailo.school.entity.Teacher;

@Component
public class TeacherRowMapper implements RowMapper <Teacher> {

    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(rs.getInt("id"));
        teacher.setFirstName(rs.getString("first_name"));
        teacher.setSecondName(rs.getString("second_name"));
        teacher.setLastName(rs.getString("last_name"));
        teacher.setPhone(rs.getString("phone"));
        teacher.setEmail(rs.getString("email"));
        teacher.setAddress(rs.getString("address"));
        teacher.setBirthDate(rs.getObject("birth_date", LocalDate.class));
        teacher.setDescription(rs.getString("description"));
        return teacher;
    }

}
