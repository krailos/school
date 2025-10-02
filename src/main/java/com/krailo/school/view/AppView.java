package com.krailo.school.view;

import java.util.List;

import org.springframework.stereotype.Component;

import com.krailo.school.entity.Gang;

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
        String groupViewFormat = "id = %-2d | name = %-20s | description = %-25s | subject = %-10S | teacher = %-10s %-10s ";
        sb.append(String.format(groupViewFormat, g.getId(), g.getName(), g.getDescription(), g.getSubject().getName(),
                g.getTeacher().getFirstName(), g.getTeacher().getLastName()) + System.lineSeparator());

        return sb.toString();
    }
}
