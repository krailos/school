package com.krailo.school.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.krailo.school.dao.mapper.PriceRowMapper;
import com.krailo.school.entity.Price;
import com.krailo.school.entity.Schedule;

@Repository
public class PriceDao {

    private static final String SQL_SELECT_ALL_PRICE = "SELECT * FROM price";
    private static final String SQL_SELECT_PRICE_BY_ID = "SELECT * FROM price  WHERE id = ?";
    private static final String SQL_INSERT_PRICE = """
            INSERT INTO price (subject_id, price, name, price_date)
            VALUES (?, ?, ?, ?)
            """;
    private static final String SQL_UPDATE_BY_ID = """
            UPDATE price 
            SET subject_id = ?, price = ?, name = ?, price_date = ?
            WHERE id = ?""";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM price WHERE id = ?";

    private JdbcTemplate jdbcTemplate;
    private PriceRowMapper priceSubjectRowMapper;

    public PriceDao (JdbcTemplate jdbcTemplate,PriceRowMapper priceSubjectRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.priceSubjectRowMapper = priceSubjectRowMapper;
    }

    public List<Price> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_PRICE, priceSubjectRowMapper);
    }

    public Price findById(int id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_PRICE_BY_ID, priceSubjectRowMapper, id);
    }

    public int create(Price priceSubject) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_PRICE, new String[] { "id" });
            ps.setInt(1, priceSubject.getSubject().getId());
            ps.setInt(2, priceSubject.getPrice());
            ps.setString(3, priceSubject.getName());
            ps.setDate(4, Date.valueOf(priceSubject.getDate()));
            return ps;
        }, keyHolder);
        priceSubject.setId(keyHolder.getKey().intValue());
        return priceSubject.getId();
    }

    public void update(Price priceSubject) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, priceSubject.getSubject().getId(), priceSubject.getPrice(),
                priceSubject.getName(), priceSubject.getDate(), priceSubject.getId());
    }

    public void deleteById(int id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }
    
}
