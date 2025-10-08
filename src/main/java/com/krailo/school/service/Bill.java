package com.krailo.school.service;

import java.time.LocalDate;
import java.util.List;

import com.krailo.school.entity.Payment;
import com.krailo.school.entity.Student;

public class Bill {
    
    private Student student;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<ItemBill> items;
    private List<Payment> payments;
    int totalItemSum;
    int totalPaymentSum;
    int balance;

    public Bill () {
        
    }
    
    public void calculateTotalItemSum() {
        for (ItemBill i : items) {
            totalItemSum = i.getItemSum() + totalItemSum;
        }
    }

    public void calculateTotalPaymentSum() {
        for (Payment p : payments) {
            totalPaymentSum = p.getSum() + totalPaymentSum;
        }
    }
    
    public void calculeateBalance () {
        balance = totalPaymentSum -  totalItemSum;
    }

    public List<ItemBill> getItems() {
        return items;
    }

    public void setItems(List<ItemBill> items) {
        this.items = items;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public int getTotalItemSum() {
        return totalItemSum;
    }

    public void setTotalItemSum(int totalItemSum) {
        this.totalItemSum = totalItemSum;
    }

    public int getTotalPaymentSum() {
        return totalPaymentSum;
    }

    public void setTotalPaymentSum(int totalPaymentSum) {
        this.totalPaymentSum = totalPaymentSum;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    

    
    
}
