package com.krailo.school.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krailo.school.dao.DiscountDao;
import com.krailo.school.dao.LessonDao;
import com.krailo.school.dao.PaymentDao;
import com.krailo.school.dao.PriceDao;
import com.krailo.school.entity.Discount;
import com.krailo.school.entity.Lesson;
import com.krailo.school.entity.Price;
import com.krailo.school.entity.Student;
import com.krailo.school.entity.Subject;


@Service
public class AccountService {

    private LessonDao lessonDao;
    private DiscountDao discountDao;
    private PriceDao priceDao;
    private PaymentDao paymentDao;

    public AccountService(LessonDao lessonDao, DiscountDao discountDao, PriceDao priceDao, PaymentDao paymentDao) {
        this.lessonDao = lessonDao;
        this.discountDao = discountDao;
        this.priceDao = priceDao;
        this.paymentDao = paymentDao;
    }
    
    
    
    public Bill createBill (Student student, LocalDate startDate, LocalDate endDate) {
        Bill bill = new Bill();
        bill.setStudent(student);
        bill.setStartDate(startDate);
        bill.setEndDate(endDate);
        bill.setItems(createItems(student, startDate, endDate));
        bill.setPayments(paymentDao.findByStudentId(student.getId()));
        bill.calculateTotalItemSum();
        bill.calculateTotalPaymentSum();
        bill.calculeateBalance();
        return bill;
        
       
        
    }

    public List<ItemBill> createItems(Student student, LocalDate startDate, LocalDate endDate) {
        student.setDiscounts(discountDao.findByStudentId(student.getId()));
        List<Lesson> lessons = lessonDao.findByStudentIdAndBetweenDates(student.getId(), startDate, endDate);
        List<ItemBill> items = new ArrayList<>();
        for (Lesson lesson : lessons) {
            Subject subject = lesson.getSchedule().getGang().getSubject();
            subject.setPrices(priceDao.findBySubjectId(subject.getId()));
            ItemBill item = createItem(lesson, student);
            item.calculateItemSum();
            items.add(item);
        }
        return items;
    }

    public ItemBill createItem(Lesson lesson, Student student) {
        ItemBill item = new ItemBill();
        item.setStudent(student);
        item.setLesson(lesson);
        item.setGang(lesson.getSchedule().getGang());
        item.setSubject(lesson.getSchedule().getGang().getSubject());
        item.setPrice(lesson.getSchedule().getGang().getSubject().getPrices().stream()
                .sorted(Comparator.comparing(Price::getDate).reversed())
                .filter(p -> p.getDate().isBefore(lesson.getLessonDate())
                        || p.getDate().isEqual(lesson.getLessonDate()))
                .findFirst().orElse(null));
        item.setDiscount( student.getDiscounts().stream()
                .filter(d -> d.getSubject().equals(lesson.getSchedule().getGang().getSubject()))
                .sorted(Comparator.comparing(Discount::getDate).reversed())
                .filter(d -> d.getDate().isBefore(lesson.getLessonDate()) || d.getDate().equals(lesson.getLessonDate()))
                .findFirst().orElse(null));
        item.setDate(lesson.getLessonDate());
        return item;
    }
    
    
    

}
