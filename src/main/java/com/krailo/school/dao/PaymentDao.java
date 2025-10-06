package com.krailo.school.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.krailo.school.dao.mapper.PaymentRowMapper;
import com.krailo.school.entity.Payment;

@Repository
public class PaymentDao {

    private static final String SQL_SELECT_ALL_PRICE = "SELECT * FROM payment";
    private static final String SQL_SELECT_PRICE_BY_ID = "SELECT * FROM payment  WHERE id = ?";
    private static final String SQL_INSERT_PRICE = """
            INSERT INTO payment (student_id, payment_sum, description, payment_date)
            VALUES (?, ?, ?, ?)
            """;
    private static final String SQL_UPDATE_BY_ID = """
            UPDATE payment
            SET student_id = ?, payment_sum = ?, description = ?, payment_date = ?
            WHERE id = ?""";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM payment WHERE id = ?";

    private JdbcTemplate jdbcTemplate;
    private PaymentRowMapper paymentRowMapper;

    public PaymentDao(JdbcTemplate jdbcTemplate, PaymentRowMapper paymentRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.paymentRowMapper = paymentRowMapper;
    }

    public List<Payment> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_PRICE, paymentRowMapper);
    }

    public Payment findById(int id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_PRICE_BY_ID, paymentRowMapper, id);
    }

    public int create(Payment payment) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_PRICE, new String[] { "id" });
            ps.setInt(1, payment.getStudent().getId());
            ps.setInt(2, payment.getSum());
            ps.setString(3, payment.getDescription());
            ps.setDate(4, Date.valueOf(payment.getDate()));
            return ps;
        }, keyHolder);
        payment.setId(keyHolder.getKey().intValue());
        return payment.getId();
    }

    public void update(Payment payment) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, payment.getStudent().getId(), payment.getSum(), payment.getDescription(),
                payment.getDate(), payment.getId());
    }

    public void deleteById(int id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

}
