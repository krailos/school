package com.krailo.school.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.krailo.school.dao.StudentDao;
import com.krailo.school.entity.Payment;

@Component
public class PaymentRowMapper implements RowMapper<Payment> {

    StudentDao studentDao;

    public PaymentRowMapper(StudentDao studentDao) {
        super();
        this.studentDao = studentDao;
    }

    @Override
    public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Payment payment = new Payment();
        payment.setId(rs.getInt("id"));
        payment.setSum(rs.getInt("payment_sum"));
        payment.setStudent(studentDao.findById(rs.getInt("student_id")));
        payment.setDate(rs.getObject("payment_date", LocalDate.class));
        payment.setDescription(rs.getString("description"));
        return payment;
    }

}
