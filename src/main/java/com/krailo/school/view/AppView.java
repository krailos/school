package com.krailo.school.view;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.krailo.school.entity.Discount;
import com.krailo.school.entity.Gang;
import com.krailo.school.entity.Lesson;
import com.krailo.school.entity.Payment;
import com.krailo.school.entity.Price;
import com.krailo.school.entity.Schedule;
import com.krailo.school.entity.Student;
import com.krailo.school.service.Bill;
import com.krailo.school.service.ItemBill;

@Component
public class AppView {

    public String viewItems(List<ItemBill> biilItems) {
        StringBuilder sb = new StringBuilder();
        String viewFormat = "lessonId = %-3d | subject = %-10s | price = %-5d | student name = %-10s | discount = %-5d | lessonDate = %-10s ";
        for (ItemBill b : biilItems) {
            sb.append(String.format(viewFormat,b.getLesson().getId(), b.getSubject().getName(), b.getPrice().getPrice(),
                    b.getStudent().getFirstName(), b.getDiscount().getDiscount(), b.getDate()) + System.lineSeparator());
        }
        return sb.toString();
    }

    public String viewItem(ItemBill b) {
        StringBuilder sb = new StringBuilder();
        String viewFormat = "lessonId = %-3d | subject = %-10s | price = %-5d | student name = %-10s | discount = %-5d | lessonDate = %-10s ";
        sb.append(String.format(viewFormat,b.getLesson().getId(), b.getSubject().getName(), b.getPrice().getPrice(),
                b.getStudent().getFirstName(), b.getDiscount().getDiscount(), b.getDate()) + System.lineSeparator());
        return sb.toString();
    }

    public String viewpPayments(List<Payment> payment) {
        StringBuilder sb = new StringBuilder();
        String viewFormat = "id = %-3d | studentId = %-3d | sum = %-7s | description = %-10s | date = %-10s  ";
        for (Payment p : payment) {
            sb.append(String.format(viewFormat, p.getId(), p.getStudent().getId(), p.getSum(), p.getDescription(),
                    p.getDate()) + System.lineSeparator());
        }
        return sb.toString();
    }

    public String viewpPayment(Payment p) {
        StringBuilder sb = new StringBuilder();
        String viewFormat = "id = %-3d | studentId = %-3d | sum = %-7s | description = %-10s | date = %-10s  ";
        sb.append(String.format(viewFormat, p.getId(), p.getStudent().getId(), p.getSum(), p.getDescription(),
                p.getDate()) + System.lineSeparator());
        return sb.toString();
    }

    public String viewpDiscounts(List<Discount> discount) {
        StringBuilder sb = new StringBuilder();
        String viewFormat = "id = %-3d | subjectId = %-3d | studentId = %-3d | discount = %-7s | name = %-10s | date = %-10s  ";
        for (Discount d : discount) {
            sb.append(String.format(viewFormat, d.getId(), d.getSubject().getId(), d.getStudent().getId(),
                    d.getDiscount(), d.getName(), d.getDate()) + System.lineSeparator());
        }
        return sb.toString();
    }

    public String viewpDiscount(Discount d) {
        StringBuilder sb = new StringBuilder();
        String viewFormat = "id = %-3d | subjectId = %-3d | studentId = %-3d | discount = %-7s | name = %-10s | date = %-10s  ";
        sb.append(String.format(viewFormat, d.getId(), d.getSubject().getId(), d.getStudent().getId(), d.getDiscount(),
                d.getName(), d.getDate()) + System.lineSeparator());
        return sb.toString();
    }

    public String viewpPriceSubjects(List<Price> priceSubject) {
        StringBuilder sb = new StringBuilder();
        String viewFormat = "id = %-3d | subject = %-15s | price = %-7s | name = %-10s | date = %-10s  ";
        for (Price p : priceSubject) {
            sb.append(String.format(viewFormat, p.getId(), p.getSubject().getName(), p.getPrice(), p.getName(),
                    p.getDate()) + System.lineSeparator());
        }
        return sb.toString();
    }

    public String viewpPriceSubject(Price p) {
        StringBuilder sb = new StringBuilder();
        String viewFormat = "id = %-3d | subject = %-15s | price = %-7s | name = %-10s | date = %-10s  ";
        sb.append(String.format(viewFormat, p.getId(), p.getSubject().getName(), p.getPrice(), p.getName(), p.getDate())
                + System.lineSeparator());
        return sb.toString();
    }

    public String viewLessons(List<Lesson> lesson) {
        StringBuilder sb = new StringBuilder();
        String viewFormat = "lesson_id = %-3d | scheduleId = %-3d | lessonDate = %-10s | studentCount = %-3d  ";
        for (Lesson l : lesson) {
            sb.append(String.format(viewFormat, l.getId(), l.getSchedule().getId(), l.getLessonDate(),
                    l.getStudents().size()) + System.lineSeparator());
        }
        return sb.toString();
    }

    public String viewLesson(Lesson l) {
        StringBuilder sb = new StringBuilder();
        String viewFormat = "id = %-3d | scheduleId = %-3d | lessonDate = %-10s | studentCount = %-3d  ";
        sb.append(
                String.format(viewFormat, l.getId(), l.getSchedule().getId(), l.getLessonDate(), l.getStudents().size())
                        + System.lineSeparator());
        return sb.toString();
    }

    public String viewSchedules(List<Schedule> schedule) {
        StringBuilder sb = new StringBuilder();
        String viewFormat = "id = %-3d | audId = %-3d audName = %-10S  | gangId = %-3d gangName = %-10S | "
                + "weekDay = %-10S | startTime = %-10s | endTime = %-10s ";
        for (Schedule sc : schedule) {
            sb.append(String.format(viewFormat, sc.getId(), sc.getAudience().getId(), sc.getAudience().getName(),
                    sc.getGang().getId(), sc.getGang().getName(), sc.getWeekDay().name(), sc.getStartTime(),
                    sc.getEndTime()) + System.lineSeparator());
        }
        return sb.toString();
    }

    public String viewSchedule(Schedule sc) {
        StringBuilder sb = new StringBuilder();
        String viewFormat = "id = %-3d | audId = %-3d audName = %-10S  | gangId = %-3d gangName = %-10S | "
                + "weekDay = %-10S | startTime = %-10s | endTime = %-10s ";
        sb.append(String.format(viewFormat, sc.getId(), sc.getAudience().getId(), sc.getAudience().getName(),
                sc.getGang().getId(), sc.getGang().getName(), sc.getWeekDay().name(), sc.getStartTime(),
                sc.getEndTime()) + System.lineSeparator());
        return sb.toString();
    }

    public String viewGangs(List<Gang> gang) {
        StringBuilder sb = new StringBuilder();
        String groupViewFormat = "id = %-2d | name = %-20s | description = %-25s | subject = %-10S | teacher = %-10s %-10s ";
        for (Gang g : gang) {
            sb.append(
                    String.format(groupViewFormat, g.getId(), g.getName(), g.getDescription(), g.getSubject().getName(),
                            g.getTeacher().getFirstName(), g.getTeacher().getLastName()) + System.lineSeparator());
        }
        return sb.toString();
    }

    public String viewGang(Gang g) {
        StringBuilder sb = new StringBuilder();
        String groupViewFormat = "id = %-2d | name = %-20s | description = %-25s | subject = %-10S | teacher_id = %-4d";
        sb.append(String.format(groupViewFormat, g.getId(), g.getName(), g.getDescription(), g.getSubject().getName(),
                g.getTeacher().getId()) + System.lineSeparator());

        return sb.toString();
    }

    public String viewStudents(List<Student> student) {
        StringBuilder sb = new StringBuilder();
        String studentViewFormat = "id = %-2d | gang_id = %-4d | f_name = %-10s | s_name = %-10S | l-_name = %-15s | contact = %-15s |  "
                + "phone = %-10s |  email = %-10s |  address = %-10s | gender = %-10s |  status = %-10s |  "
                + "birtDate = %-10s |  description = %-10s";
        for (Student s : student) {
            sb.append(String.format(studentViewFormat, s.getId(), s.getGang().getId(), s.getFirstName(),
                    s.getSecondName(), s.getLastName(), s.getContactName(), s.getPhone(), s.getEmail(), s.getAddress(),
                    s.getGender().toString(), s.getStudentStatus().toString(), s.getBirthDate().toString(),
                    s.getDescription()) + System.lineSeparator());
        }
        return sb.toString();
    }

    public String viewStudent(Student s) {
        StringBuilder sb = new StringBuilder();
        String studentViewFormat = "id = %-2d | gang_id = %-4d | f_name = %-10s | s_name = %-10S | l-_name = %-15s | contact = %-15s |  "
                + "phone = %-10s |  email = %-10s |  address = %-10s | gender = %-10s |  status = %-10s |  "
                + "birthDate = %-10s |  description = %-10s";
        sb.append(String.format(studentViewFormat, s.getId(), s.getGang().getId(), s.getFirstName(), s.getSecondName(),
                s.getLastName(), s.getContactName(), s.getPhone(), s.getEmail(), s.getAddress(),
                s.getGender().toString(), s.getStudentStatus().toString(),
                Optional.ofNullable(s.getBirthDate()).map(d -> d.toString()).orElse("null"), s.getDescription())
                + System.lineSeparator());
        return sb.toString();
    }
}
