package com.krailo.school.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.krailo.school.dao.SubjectDao;
import com.krailo.school.entity.PriceSubject;

@Component
public class PriceSubjectRowMapper implements RowMapper<PriceSubject> {

    SubjectDao subjectDao;

    public PriceSubjectRowMapper(SubjectDao subjectDao) {
        super();
        this.subjectDao = subjectDao;
    }

    @Override
    public PriceSubject mapRow(ResultSet rs, int rowNum) throws SQLException {
        PriceSubject priceSubject = new PriceSubject();
        priceSubject.setId(rs.getInt("id"));
        priceSubject.setName(rs.getString("name"));
        priceSubject.setPrice(rs.getInt("price"));
        priceSubject.setSubject(subjectDao.findById(rs.getInt("subject_id")));
        priceSubject.setDate(rs.getObject("price_date", LocalDate.class));
        return priceSubject;
    }

}
