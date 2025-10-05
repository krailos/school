package com.krailo.school.entity;

import java.time.LocalDate;
import java.util.Objects;

import com.krailo.school.entity.Price.PriceSubjectBuilder;

public class Discount {

    private int id;
    private String name;
    private int discount;
    private Student student;
    private Subject subject;
    private LocalDate date;

    public Discount() {

    }

    public Discount(int id, String name, int discount, Student student, Subject subject, LocalDate date) {
        super();
        this.id = id;
        this.name = name;
        this.discount = discount;
        this.student = student;
        this.subject = subject;
        this.date = date;
    }

    public Discount(String name, int discount, Student student, Subject subject, LocalDate date) {
        this.name = name;
        this.discount = discount;
        this.student = student;
        this.subject = subject;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Discount [id=" + id + ", name=" + name + ", discount=" + discount + ", student=" + student
                + ", subject=" + subject + ", date=" + date + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, discount, id, name, student, subject);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Discount other = (Discount) obj;
        return Objects.equals(date, other.date) && discount == other.discount && id == other.id
                && Objects.equals(name, other.name) && Objects.equals(student, other.student)
                && Objects.equals(subject, other.subject);
    }

    public static DiscountBuilder builder () {
        return new DiscountBuilder() ;
    }
    
    public static class DiscountBuilder {

        private int id;
        private String name;
        private int discount;
        private Student student;
        private Subject subject;
        private LocalDate date;

        public DiscountBuilder id(int id) {
            this.id = id;
            return this;
        }

        public DiscountBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DiscountBuilder discount(int discount) {
            this.discount = discount;
            return this;
        }

        public DiscountBuilder student (Student student) {
            this.student = student;
            return this;
        }

        public DiscountBuilder subject(Subject subject) {
            this.subject = subject;
            return this;
        }

        public DiscountBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Discount build() {
            return new Discount(id, name, discount, student, subject, date);
        }

    }

}
