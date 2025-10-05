package com.krailo.school;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
import com.krailo.school.dao.LessonDao;
import com.krailo.school.dao.ScheduleDao;
import com.krailo.school.dao.StudentDao;
import com.krailo.school.dao.SubjectDao;
import com.krailo.school.dao.TeacherDao;
import com.krailo.school.entity.Audience;
import com.krailo.school.entity.Gang;
import com.krailo.school.entity.Gang.GangBuilder;
import com.krailo.school.entity.Gender;
import com.krailo.school.entity.Lesson;
import com.krailo.school.entity.Schedule;
import com.krailo.school.entity.Student;
import com.krailo.school.entity.StudentStatus;
import com.krailo.school.entity.Teacher;
import com.krailo.school.entity.WeekDay;
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
        StudentDao studentDao = (StudentDao) context.getBean(StudentDao.class);
        ScheduleDao scheduleDao = (ScheduleDao) context.getBean(ScheduleDao.class);
        LessonDao lessonDao = (LessonDao) context.getBean(LessonDao.class);

        // ------------------ЗНАЙТИ ВСІХ -------------------
        System.out.println("----FIND ALL---");
        // System.out.println(appView.viewGangs(gangDao.findAll()));

//         System.out.println(appView.viewStudents(studentDao.findAll()));

//        System.out.println(appView.viewStudents(studentDao.findByGangId(1)));
//        
//        System.out.println(appView.viewSchedules(scheduleDao.findAll()));
//        
//        System.out.println(appView.viewLessons(lessonDao.findAll()));

        // ------------------ЗНАЙТИ ПО ID ------------------------
        System.out.println("----FIND BY ID---");
        // System.out.println(appView.viewGang(gangDao.findById(1)));

        // System.out.println(appView.viewStudent(studentDao.findById(1)));

//        System.out.println(appView.viewSchedule(scheduleDao.findById(1)));
        
        
//        Lesson lesson = lessonDao.findById(1);
//        System.out.println(appView.viewLesson(lesson));
//        System.out.println(appView.viewStudents(lesson.getStudents()));
//        System.out.println(appView.viewStudents(studentDao.findByLessonId(1)));

        // ----------------------СТОРИТИ НОВОГО-------------------
        System.out.println("----CREATE NEW---");
//        Gang gang = Gang.builder().name("Читарика-ОШ-2").description("Олена Шашчак 2 група")
//                .subject(subjectDao.findById(1)).teacher(teacherDao.findById(1)).build();
//        int id = gangDao.create(gang);
//        System.out.println(appView.viewGang(gangDao.findById(id)));

//        Student student = Student.builder().gang(gangDao.findById(1)).firstName("firstname").lastName("lastname")
//                .contactName("contactname").phone("0672222222").gender(Gender.ХЛОПЕЦЬ).studentStatus(StudentStatus.УЧЕНЬ).build();
//        int id = studentDao.create(student);
//        System.out.println(appView.viewStudent(studentDao.findById(id)));

//        Schedule schedule = Schedule.builder().audience(audienceDao.findById(2)).gang(gangDao.findById(2)).
//                weekDay(WeekDay.ПОНЕДІЛОК).startTime(LocalTime.of(13, 00)).endTime(LocalTime.of(14, 00)).build();
//        int id = scheduleDao.create(schedule);
//        System.out.println(appView.viewSchedule(scheduleDao.findById(id)));

//        List<Student> stds = studentDao.findByGangId(2);
//        Lesson les = Lesson.builder().schedule(scheduleDao.findById(2)).lessonDate(LocalDate.of(2025, 9, 1))
//                .students(stds).build();
//        les.setStudents(stds);
//        System.out.println(appView.viewStudents(stds));
//        int id = lessonDao.create(les);
//        System.out.println(appView.viewLesson(lessonDao.findById(id)));

        // -------------------ОБНОВИТИ НОВОГО---------------
        System.out.println("----UPDATE---");
//        gang = gangDao.findById(id);
//        gang.setName("new name");
//        gangDao.update(gang);
//        System.out.println(appView.viewGang(gangDao.findById(id)));

//      student = studentDao.findById(id);
//      student.setFirstName("new first name");
//      student.setGender(Gender.ДІВЧИНА);
//      studentDao.update(student);
//      System.out.println(appView.viewStudent(studentDao.findById(id)));

//        schedule = scheduleDao.findById(id);
//        schedule.setWeekDay(WeekDay.ВІВТОРОК);
//        scheduleDao.update(schedule);
//        System.out.println(appView.viewSchedule(scheduleDao.findById(id)));

//        lesson = lessonDao.findById(id);
//        lesson.setLessonDate(LocalDate.of(2025, 9, 2));
//        lesson.getStudents().remove(2);
//        lesson.getStudents().add(studentDao.findById(12));
//        lessonDao.update(lesson);
//        System.out.println(appView.viewLesson(lessonDao.findById(id)));
//        System.out.println(appView.viewStudents(lessonDao.findById(id).getStudents()));

        // --------------------- ВИДАЛИТИ НОВОГО----------------
        System.out.println("----DELETE---");
//        gangDao.deleteById(id);
//        System.out.println(appView.viewGangs(gangDao.findAll()));

//    studentDao.deleteById(id);
//    System.out.println(appView.viewStudents(studentDao.findAll()));

//        scheduleDao.deleteById(id);
//        System.out.println(appView.viewSchedules(scheduleDao.findAll()));

//        System.out.println(appView.viewLessons(lessonDao.findAll()));
//        lessonDao.deleteById(id);
//        System.out.println(appView.viewLessons(lessonDao.findAll()));


        context.close();

    }

}
