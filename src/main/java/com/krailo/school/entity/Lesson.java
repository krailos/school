package com.krailo.school.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Lesson {

    private int id;
    private Schedule schedule;
    private LocalDate lessonDate;
    private List<Student> students;

    public Lesson() {

    }

    public Lesson(int id, Schedule schedule, LocalDate lessonDate, List<Student> students) {
        this.id = id;
        this.schedule = schedule;
        this.lessonDate = lessonDate;
        this.students = students;
    }

    public Lesson(Schedule schedule, LocalDate lessonDate, List<Student> students) {
        this.schedule = schedule;
        this.lessonDate = lessonDate;
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public LocalDate getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(LocalDate lessonDate) {
        this.lessonDate = lessonDate;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Lesson [id=" + id + ", schedule=" + schedule + ", lessonDate=" + lessonDate + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lessonDate, schedule, students);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Lesson other = (Lesson) obj;
        return id == other.id && Objects.equals(lessonDate, other.lessonDate)
                && Objects.equals(schedule, other.schedule) && Objects.equals(students, other.students);
    }

    public static LessonBuilder builder() {
        return new LessonBuilder();
    }

    public static class LessonBuilder {

        private int id;
        private Schedule schedule;
        private LocalDate lessonDate;
        private List<Student> students;

        public LessonBuilder id(int id) {
            this.id = id;
            return this;
        }

        public LessonBuilder schedule(Schedule schedule) {
            this.schedule = schedule;
            return this;
        }

        public LessonBuilder lessonDate(LocalDate lessonDate) {
            this.lessonDate = lessonDate;
            return this;
        }

        public LessonBuilder students(List<Student> students) {
            this.students = students;
            return this;
        }

        public Lesson build() {
            return new Lesson(id, schedule, lessonDate, students);
        }

    }

}
