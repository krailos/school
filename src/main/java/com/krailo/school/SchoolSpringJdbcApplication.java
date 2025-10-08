package com.krailo.school;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
import com.krailo.school.dao.DiscountDao;
import com.krailo.school.dao.GangDao;
import com.krailo.school.dao.LessonDao;
import com.krailo.school.dao.PaymentDao;
import com.krailo.school.dao.PriceDao;
import com.krailo.school.dao.ScheduleDao;
import com.krailo.school.dao.StudentDao;
import com.krailo.school.dao.SubjectDao;
import com.krailo.school.dao.TeacherDao;
import com.krailo.school.entity.Audience;
import com.krailo.school.entity.Discount;
import com.krailo.school.entity.Gang;
import com.krailo.school.entity.Gang.GangBuilder;
import com.krailo.school.entity.Gender;
import com.krailo.school.entity.Lesson;
import com.krailo.school.entity.Payment;
import com.krailo.school.entity.Price;
import com.krailo.school.entity.Schedule;
import com.krailo.school.entity.Student;
import com.krailo.school.entity.StudentStatus;
import com.krailo.school.entity.Teacher;
import com.krailo.school.entity.WeekDay;
import com.krailo.school.service.AccountService;
import com.krailo.school.service.Bill;
import com.krailo.school.view.AppView;

@SpringBootApplication
@PropertySource("classpath:application.properties")

public class SchoolSpringJdbcApplication {

    /**
     * @param args
     * @throws DataAccessException
     */
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
        PriceDao priceDao = (PriceDao) context.getBean(PriceDao.class);
        DiscountDao discountDao = (DiscountDao) context.getBean(DiscountDao.class);
        PaymentDao paymentDao = (PaymentDao) context.getBean(PaymentDao.class);
        AccountService accountServie = (AccountService) context.getBean(AccountService.class);

        // ------------------ЗНАЙТИ ВСІХ -------------------
        System.out.println("----FIND ALL---");
        // System.out.println(appView.viewGangs(gangDao.findAll()));

//         System.out.println(appView.viewStudents(studentDao.findAll()));

//        System.out.println(appView.viewStudents(studentDao.findByGangId(1)));
//        
//        System.out.println(appView.viewSchedules(scheduleDao.findAll()));
//        
//        System.out.println(appView.viewLessons(lessonDao.findAll()));

//       System.out.println(appView.viewLessons(lessonDao.findByStudentId(1)));
//       System.out.println(appView.viewLessons(lessonDao.findByStudentIdAndBetweenDates(1, LocalDate.of(2025, 9, 1), LocalDate.of(2025, 9,14))));

        // System.out.println(appView.viewpPriceSubjects(priceSubjectDao.findAll()));

        // System.out.println(appView.viewpDiscounts(discountDao.findAll()));
//        System.out
//                .println(appView.viewpDiscounts(discountDao.findByStudentIdAndSubject_id(studentDao.findById(1).getId(),
//                        lessonDao.findById(1).getSchedule().getGang().getSubject().getId())));

//        System.out.println(appView.viewpPayments(paymentDao.findAll()));

//        System.out.println(appView.viewItems( accountServie.createItems(studentDao.findById(1),
//                LocalDate.of(2025, 9, 1), LocalDate.of(2025, 9, 15))));
        
        System.out.println();
        System.out.println();
        System.out.println(appView.viewBill( accountServie.createBill(studentDao.findById(1),
        LocalDate.of(2025, 9, 1), LocalDate.of(2025, 9, 15))));
        System.out.println();
        System.out.println();
        

        // ------------------ЗНАЙТИ ПО ID ------------------------
        System.out.println("----FIND BY ID---");
        // System.out.println(appView.viewGang(gangDao.findById(1)));

        // System.out.println(appView.viewStudent(studentDao.findById(1)));

//        System.out.println(appView.viewSchedule(scheduleDao.findById(1)));

//        Lesson lesson = lessonDao.findById(1);
//        System.out.println(appView.viewLesson(lesson));
//        System.out.println(appView.viewStudents(lesson.getStudents()));
//        System.out.println(appView.viewStudents(studentDao.findByLessonId(1)));

        // System.out.println(appView.viewpPriceSubject(priceSubjectDao.findById(1)));

        // System.out.println(appView.viewpDiscount(discountDao.findById(1)));

        // System.out.println(appView.viewpPayment(paymentDao.findById(1)));

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

//        Price priceSubject = Price.builder().subject(subjectDao.findById(1)).price(150).name("new price")
//                .date(LocalDate.of(2025, 9, 2)).build();
//        int id = priceDao.create(priceSubject);
//        System.out.println(appView.viewpPriceSubject(priceDao.findById(id)));

//        Discount discount  = Discount.builder().subject(subjectDao.findById(1)).student(studentDao.findById(3)).
//                name("second cours").date(LocalDate.of(2025, 9, 1)).discount(50).build();
//        int id = discountDao.create(discount);
//        System.out.println(appView.viewpDiscount(discountDao.findById(id)));

//        Payment payment = Payment.builder().student(studentDao.findById(3)).sum(1000).discription("pay for course").date(LocalDate.of(2025, 9, 1)).build();
//        int id = paymentDao.create(payment);
//        System.out.println(appView.viewpPayment(paymentDao.findById(id)));

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

//        priceSubject = priceDao.findById(id);
//        priceSubject.setPrice(130);
//        priceDao.update(priceSubject);
//        System.out.println(appView.viewpPriceSubject(priceDao.findById(id)));

//      discount = discountDao.findById(id);
//      discount.setDiscount(30);
//      discountDao.update(discount);
//      System.out.println(appView.viewpDiscount(discountDao.findById(id)));

//      payment = paymentDao.findById(id);
//      payment.setSum(500);
//      paymentDao.update(payment);
//      System.out.println(appView.viewpPayment(paymentDao.findById(id)));

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

//        System.out.println(appView.viewpPriceSubjects(priceDao.findAll()));
//        priceDao.deleteById(id);
//        System.out.println(appView.viewpPriceSubjects(priceDao.findAll()));

//        System.out.println(appView.viewpDiscounts(discountDao.findAll()));
//        discountDao.deleteById(id);
//        System.out.println(appView.viewpDiscounts(discountDao.findAll()));

//        System.out.println(appView.viewpPayments(paymentDao.findAll()));
//        paymentDao.deleteById(id);
//        System.out.println(appView.viewpPayments(paymentDao.findAll()));

        context.close();

    }

}
