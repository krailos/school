package com.krailo.school;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.krailo.school.config.SchoolConfig;
import com.krailo.school.dao.AudienceDao;
import com.krailo.school.dao.GangDao;
import com.krailo.school.dao.SubjectDao;
import com.krailo.school.dao.TeacherDao;
import com.krailo.school.entity.Audience;
import com.krailo.school.entity.Gang;
import com.krailo.school.entity.Gang.GangBuilder;
import com.krailo.school.entity.Teacher;
import com.krailo.school.view.AppView;

@SpringBootApplication
@PropertySource("classpath:application.properties")

public class SchoolSpringJdbcApplication {

    public static void main(String[] args) throws DataAccessException {
        SpringApplication.run(SchoolSpringJdbcApplication.class, args);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SchoolConfig.class);

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("school-schema.sql"));
        populator.addScript(new ClassPathResource("school-data.sql"));
        DatabasePopulatorUtils.execute(populator, (DataSource) context.getBean("dataSource"));
        System.out.println("data populated");

        AppView appView = (AppView) context.getBean(AppView.class);
        AudienceDao audienceDao = (AudienceDao) context.getBean(AudienceDao.class);
        SubjectDao subjectDao = (SubjectDao) context.getBean(SubjectDao.class);
        TeacherDao teacherDao = (TeacherDao) context.getBean(TeacherDao.class);
        GangDao gangDao = (GangDao) context.getBean(GangDao.class);

        // ------------------ЗНАЙТИ ВСІХ -------------------
        System.out.println(appView.viewGangs(gangDao.findAll()));

        // ------------------ЗНАЙТИ ПО ID ------------------------
        System.out.println(appView.viewGang(gangDao.findById(1)));

        // ----------------------СТОРИТИ НОВОГО-------------------
        Gang gang = Gang.builder().name("Читарика-ОШ-2").description("Олена Шашчак 2 група")
                .subject(subjectDao.findById(1)).teacher(teacherDao.findById(1)).build();
        int id = gangDao.create(gang);
        System.out.println(appView.viewGang(gangDao.findById(id)));
        
        

        // -------------------ОБНОВИТИ НОВОГО---------------
        gang = gangDao.findById(id);
        gang.setName("new name");
        gangDao.update(gang);
        System.out.println(appView.viewGang(gangDao.findById(id)));

        // --------------------- ВИДАЛИТИ НОВОГО----------------
        gangDao.deleteById(id);
        System.out.println(appView.viewGangs(gangDao.findAll()));

        context.close();

    }

}
