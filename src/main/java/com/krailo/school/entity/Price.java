package com.krailo.school.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Price {

    private int id;
    private String name;
    private int price;
    private Subject subject;
    private LocalDate date;

    public Price() {

    }

    public Price(int id, String name, int price, Subject subject, LocalDate date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.subject = subject;
        this.date = date;
    }

    public Price(String name, int price, Subject subject, LocalDate date) {
        this.name = name;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
        return "PriceSubject [id=" + id + ", name=" + name + ", price=" + price + ", subjec=" + subject + ", date="
                + date + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, id, name, price, subject);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Price other = (Price) obj;
        return Objects.equals(date, other.date) && id == other.id && Objects.equals(name, other.name)
                && price == other.price && Objects.equals(subject, other.subject);
    }
    
    public static PriceBuilder builder () {
        return new PriceBuilder();
    }

    public static class PriceBuilder {
        private int id;
        private String name;
        private int price;
        private Subject subject;
        private LocalDate date;

        public PriceBuilder id(int id) {
            this.id = id;
            return this;
        }

        public PriceBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PriceBuilder price(int price) {
            this.price = price;
            return this;
        }

        public PriceBuilder subject(Subject subject) {
            this.subject = subject;
            return this;
        }

        public PriceBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Price build() {
            return new Price(id, name, price, subject, date);
        }

    }

}
