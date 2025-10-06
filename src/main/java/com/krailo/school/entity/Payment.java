package com.krailo.school.entity;

import java.time.LocalDate;
import java.util.Objects;

import com.krailo.school.entity.Price.PriceBuilder;

public class Payment {

    private int id;
    private Student student;
    private int sum;
    private LocalDate date;
    private String description;

    public Payment() {

    }

    public Payment(int id, Student student, int sum, LocalDate date, String description) {
        super();
        this.id = id;
        this.student = student;
        this.sum = sum;
        this.date = date;
        this.description = description;
    }

    public Payment(Student student, int sum, LocalDate date, String description) {
        this.student = student;
        this.sum = sum;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Payment [id=" + id + ", student=" + student + ", sum=" + sum + ", date=" + date + ", discription="
                + description + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, description, id, student, sum);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Payment other = (Payment) obj;
        return Objects.equals(date, other.date) && Objects.equals(description, other.description) && id == other.id
                && Objects.equals(student, other.student) && sum == other.sum;
    }
    
    
    public static PaymentBuilder builder () {
        return new PaymentBuilder();
    }
    public static class PaymentBuilder {
        
        private int id;
        private Student student;
        private int sum;
        private LocalDate date;
        private String description;
        
        public PaymentBuilder student (Student student) {
            this.student = student;
            return this;
        }
        
        public PaymentBuilder sum (int sum) {
            this.sum = sum;
            return this;
        }
        
        public PaymentBuilder date (LocalDate date) {
            this.date =  date;
            return this;
        }
        
        public PaymentBuilder discription (String description) {
            this.description = description;
            return this;
        }
        
        public Payment build() {
            return new Payment (id, student, sum, date, description);
        }
        
     
    }

}
