package com.krailo.school.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.krailo.school.dao.SubjectDao;
import com.krailo.school.dao.TeacherDao;
import com.krailo.school.entity.Gang;

@Component
public class GangRowMapper implements RowMapper<Gang> {

    SubjectDao subjectDao;
    TeacherDao teacherDao;

    public GangRowMapper(SubjectDao subjectDao, TeacherDao teacherDao) {
        this.subjectDao = subjectDao;
        this.teacherDao = teacherDao;
    }

    @Override
    public Gang mapRow(ResultSet rs, int rowNum) throws SQLException {
        Gang gang = new Gang();
        gang.setId(rs.getInt("id"));
        gang.setName(rs.getString("name"));
        gang.setDescription(rs.getString("description"));
        gang.setSubject(subjectDao.findById(rs.getInt("subject_id")));
        gang.setTeacher(teacherDao.findById(rs.getInt("teacher_id")));
        return gang;
    }

}
