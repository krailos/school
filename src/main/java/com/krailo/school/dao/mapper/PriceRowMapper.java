package com.krailo.school.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.krailo.school.dao.SubjectDao;
import com.krailo.school.entity.Price;

@Component
public class PriceRowMapper implements RowMapper<Price> {

    SubjectDao subjectDao;

    public PriceRowMapper(SubjectDao subjectDao) {
        super();
        this.subjectDao = subjectDao;
    }

    @Override
    public Price mapRow(ResultSet rs, int rowNum) throws SQLException {
        Price priceSubject = new Price();
        priceSubject.setId(rs.getInt("id"));
        priceSubject.setName(rs.getString("name"));
        priceSubject.setPrice(rs.getInt("price"));
        priceSubject.setSubject(subjectDao.findById(rs.getInt("subject_id")));
        priceSubject.setDate(rs.getObject("price_date", LocalDate.class));
        return priceSubject;
    }

}
