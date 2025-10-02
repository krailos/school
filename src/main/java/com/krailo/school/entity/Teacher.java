package com.krailo.school.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Teacher {

    private int id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private LocalDate birthDate;
    private String description;

    public Teacher() {

    }

    public Teacher(int id, String firstName, String secondName, String lastName, String phone, String email,
            String address, LocalDate birthDate, String description) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
        this.description = description;
    }

    public Teacher(String firstName, String secondName, String lastName, String phone, String email, String address,
            LocalDate birthDate, String description) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
        this.description = description;
    }

    public Teacher(String firstName, String lastName, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
    }

    public Teacher(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Teacher [id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", lastName="
                + lastName + ", phone=" + phone + ", email=" + email + ", address=" + address + ", birthDate="
                + birthDate + ", description=" + description + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, birthDate, description, email, firstName, id, lastName, phone, secondName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Teacher other = (Teacher) obj;
        return Objects.equals(address, other.address) && Objects.equals(birthDate, other.birthDate)
                && Objects.equals(description, other.description) && Objects.equals(email, other.email)
                && Objects.equals(firstName, other.firstName) && id == other.id
                && Objects.equals(lastName, other.lastName) && Objects.equals(phone, other.phone)
                && Objects.equals(secondName, other.secondName);
    }

}
