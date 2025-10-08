package com.krailo.school.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.krailo.school.dao.mapper.DiscountRowMapper;
import com.krailo.school.dao.mapper.PriceRowMapper;
import com.krailo.school.entity.Discount;
import com.krailo.school.entity.Price;

@Repository
public class DiscountDao {

    private static final String SQL_SELECT_ALl = "SELECT * FROM discount";
    private static final String SQL_SELECT_BY_DISCOUNT_ID = "SELECT * FROM discount  WHERE id = ?";
    private static final String SQL_SELECT_BY_STUDENT_ID = "SELECT * FROM discount  WHERE student_id = ?";
    private static final String SQL_SELECT_BY_STUDENT_ID_AND_SUBJECT_ID = "SELECT * FROM discount  WHERE student_id = ? AND subject_id = ?";
    private static final String SQL_INSERT_DISCOUNT = """
            INSERT INTO discount (subject_id, student_id, discount, name, discount_date)
            VALUES (?, ?, ?, ?, ?)
            """;
    private static final String SQL_UPDATE_BY_ID = """
            UPDATE discount
            SET subject_id = ?, student_id = ?, discount = ?, name = ?, discount_date = ?
            WHERE id = ?""";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM discount WHERE id = ?";

    private JdbcTemplate jdbcTemplate;
    private DiscountRowMapper discountRowMapper;

    public DiscountDao(JdbcTemplate jdbcTemplate, DiscountRowMapper discountRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.discountRowMapper = discountRowMapper;
    }

    public List<Discount> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALl, discountRowMapper);
    }
    
    public Discount findById(int id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_DISCOUNT_ID, discountRowMapper, id);
    }
    
    public int create(Discount discount) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_DISCOUNT, new String[] { "id" });
            ps.setInt(1, discount.getSubject().getId());
            ps.setInt(2, discount.getStudent().getId());
            ps.setInt(3, discount.getDiscount());
            ps.setString(4, discount.getName());
            ps.setDate(5, Date.valueOf(discount.getDate()));
            return ps;
        }, keyHolder);
        discount.setId(keyHolder.getKey().intValue());
        return discount.getId();
    }
    
    public void update(Discount discount) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, discount.getSubject().getId(), discount.getStudent().getId(),
                discount.getDiscount(), discount.getName(), discount.getDate(), discount.getId());
    }

    public void deleteById(int id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }
    
    
    public List<Discount> findByStudentId (int studentId) {
        return jdbcTemplate.query(SQL_SELECT_BY_STUDENT_ID, discountRowMapper, studentId);
    }

    public List<Discount> findByStudentIdAndSubjectId (int studentId, int subject_id) {
        return jdbcTemplate.query(SQL_SELECT_BY_STUDENT_ID_AND_SUBJECT_ID, discountRowMapper, studentId, subject_id);
    }

}
