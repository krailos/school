package com.krailo.school.view;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.krailo.school.entity.Gang;
import com.krailo.school.entity.Student;

@Component
public class AppView {

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
                + "birtDate = %-10s |  description = %-10s";
        sb.append(String.format(studentViewFormat, s.getId(), s.getGang().getId(), s.getFirstName(), s.getSecondName(),
                s.getLastName(), s.getContactName(), s.getPhone(), s.getEmail(), s.getAddress(),
                s.getGender().toString(), s.getStudentStatus().toString(),  Optional.ofNullable(s.getBirthDate()).map(d -> d.toString()).orElse("null"),
                s.getDescription()) + System.lineSeparator());
        return sb.toString();
    }
}
