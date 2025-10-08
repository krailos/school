package com.krailo.school.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.krailo.school.dao.StudentDao;
import com.krailo.school.dao.SubjectDao;
import com.krailo.school.entity.Discount;

@Component
public class DiscountRowMapper implements RowMapper<Discount> {

    SubjectDao subjectDao;
    StudentDao studentDao;

    public DiscountRowMapper(SubjectDao subjectDao, StudentDao studentDao) {
        super();
        this.subjectDao = subjectDao;
        this.studentDao = studentDao;
    }

    @Override
    public Discount mapRow(ResultSet rs, int rowNum) throws SQLException {
        Discount discount = new Discount();
        discount.setId(rs.getInt("id"));
        discount.setName(rs.getString("name"));
        discount.setValue(rs.getInt("discount"));
        discount.setSubject(subjectDao.findById(rs.getInt("subject_id")));
        discount.setStudent(studentDao.findById(rs.getInt("student_id")));
        discount.setDate(rs.getObject("discount_date", LocalDate.class));
        return discount;
    }
}
