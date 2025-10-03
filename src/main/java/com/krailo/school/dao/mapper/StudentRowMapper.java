package com.krailo.school.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.krailo.school.dao.GangDao;
import com.krailo.school.entity.Gender;
import com.krailo.school.entity.Student;
import com.krailo.school.entity.StudentStatus;

@Component
public class StudentRowMapper implements RowMapper<Student> {

    GangDao gangDao;

    public StudentRowMapper(GangDao gangDao) {
        this.gangDao = gangDao;
    }

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setGang(gangDao.findById(rs.getInt("gang_id")));
        student.setFirstName(rs.getString("first_name"));
        student.setSecondName(rs.getString("second_name"));
        student.setLastName(rs.getString("last_name"));
        student.setContactName(rs.getString("contact_name"));
        student.setPhone(rs.getString("phone"));
        student.setEmail(rs.getString("email"));
        student.setAddress(rs.getString("address"));
        student.setGender(Gender.valueOf(rs.getString("gender")));
        student.setStudentStatus(StudentStatus.valueOf(rs.getString("student_status")));
        student.setBirthDate(rs.getObject("birth_date", LocalDate.class));
        student.setDescription(rs.getString("description"));
        return student;
    }

}
