package com.krailo.school.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLType;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.krailo.school.dao.mapper.StudentRowMapper;
import com.krailo.school.entity.Gender;
import com.krailo.school.entity.Student;
import com.krailo.school.entity.Teacher;

@Repository
public class StudentDao {

    private static final String SQL_SELECT_ALL_STUDENTS = "SELECT * FROM student";
    private static final String SQL_SELECT_STUDENT_BY_ID = "SELECT * FROM student  WHERE id = ?";
    private static final String SQL_INSERT_STUDENT = """
            INSERT INTO student (gang_id, first_name, second_name, last_name, contact_name, phone, email, address,
            gender, student_status,  birth_date, description)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, CAST (? AS gender), CAST (? AS student_status), ?, ?)
            """;
    private static final String SQL_DELETE_BY_ID = "DELETE FROM student WHERE id = ?";
    private static final String SQL_UPDATE_BY_ID = """
            UPDATE student SET gang_id = ?, first_name = ?, second_name = ?, last_name = ?,
            contact_name = ?, phone = ?, email = ?, address = ?,
            gender = CAST (? as gender), student_status = CAST (? as student_status),  birth_date = ?, description = ? where id = ?""";

    private JdbcTemplate jdbcTemplate;
    private StudentRowMapper studentRowMapper;

    public StudentDao(JdbcTemplate jdbcTemplate, StudentRowMapper studentRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.studentRowMapper = studentRowMapper;
    }

    public List<Student> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_STUDENTS, studentRowMapper);
    }

    public Student findById(int id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_STUDENT_BY_ID, studentRowMapper, id);
    }

    public int create(Student student) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_STUDENT, new String[] { "id" });
            ps.setInt(1, student.getGang().getId());
            ps.setString(2, student.getFirstName());
            ps.setString(3, student.getSecondName());
            ps.setString(4, student.getLastName());
            ps.setString(5, student.getContactName());
            ps.setString(6, student.getPhone());
            ps.setString(7, student.getEmail());
            ps.setString(8, student.getAddress());
            ps.setString(9, Optional.ofNullable(student.getGender()).map(g -> g.name()).orElse(null));
            ps.setString(10, Optional.ofNullable(student.getStudentStatus()).map(ss -> ss.name()).orElse(null));
            ps.setDate(11, Optional.ofNullable(student.getBirthDate()).map(d -> Date.valueOf(d)).orElse(null));
            ps.setString(12, student.getDescription());
            return ps;
        }, keyHolder);
        student.setId(keyHolder.getKey().intValue());
        return student.getId();
    }

    public void update(Student student) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, student.getGang().getId(), student.getFirstName(),
                student.getSecondName(), student.getLastName(), student.getContactName(), student.getPhone(),
                student.getEmail(), student.getAddress(), student.getGender().name(), student.getStudentStatus().name(),
                Optional.ofNullable(student.getBirthDate()).map(d -> Date.valueOf(d)).orElse(null),
                student.getDescription(), student.getId());
    }

}
