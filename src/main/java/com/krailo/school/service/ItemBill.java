package com.krailo.school.service;

import java.time.LocalDate;
import java.util.Optional;

import com.krailo.school.entity.Discount;
import com.krailo.school.entity.Gang;
import com.krailo.school.entity.Lesson;
import com.krailo.school.entity.Price;
import com.krailo.school.entity.Student;
import com.krailo.school.entity.Subject;

public class ItemBill {

    private Student student;
    private Lesson lesson;
    private Price price;
    private Discount discount;
    private LocalDate date;
    private Gang gang;
    private Subject subject;
    private int itemSum;

    public ItemBill() {

    }

    public void calculateItemSum() {
        int discountSum = 0;
        if (discount != null) {
            discountSum =  Optional.ofNullable(discount.getValue()).orElse(0);
        }
        int priceSum = price.getPrice();
        itemSum = priceSum - priceSum * discountSum / 100;

    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Discount getDiscount() {
        if (discount == null) {
            return new Discount();
        }
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Gang getGang() {
        return gang;
    }

    public void setGang(Gang gang) {
        this.gang = gang;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getItemSum() {
        return itemSum;
    }

    public void setItemSum(int itemSum) {
        itemSum = itemSum;
    }

}
