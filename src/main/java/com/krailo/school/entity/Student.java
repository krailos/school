package com.krailo.school.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Student {

    private int id;
    private Gang gang;
    private String firstName;
    private String secondName;
    private String lastName;
    private String contactName;
    private String phone;
    private String email;
    private String address;
    private Gender gender;
    private StudentStatus studentStatus;
    private LocalDate birthDate;
    private String description;
    private List<Discount> discounts;

    public Student() {

    }

    public Student(int id, Gang gang, String firstName, String secondName, String lastName, String contactName,
            String phone, String email, String address, Gender gender, StudentStatus studentStatus, LocalDate birthDate,
            String description) {
        this.id = id;
        this.gang = gang;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.contactName = contactName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.studentStatus = studentStatus;
        this.birthDate = birthDate;
        this.description = description;
    }

    public Student(int id, Gang gang, String firstName, String lastName, String contactName, String phone) {
        this.id = id;
        this.gang = gang;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactName = contactName;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Gang getGang() {
        return gang;
    }

    public void setGang(Gang gang) {
        this.gang = gang;
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

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public StudentStatus getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
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

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", gang=" + gang + ", firstName=" + firstName + ", secondName=" + secondName
                + ", lastName=" + lastName + ", contactName=" + contactName + ", phone=" + phone + ", email=" + email
                + ", address=" + address + ", gender=" + gender + ", studentStatus=" + studentStatus + ", birthDate="
                + birthDate + ", description=" + description + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, birthDate, contactName, description, email, firstName, gang, gender, id, lastName,
                phone, secondName, studentStatus);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        return Objects.equals(address, other.address) && Objects.equals(birthDate, other.birthDate)
                && Objects.equals(contactName, other.contactName) && Objects.equals(description, other.description)
                && Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
                && Objects.equals(gang, other.gang) && gender == other.gender && id == other.id
                && Objects.equals(phone, other.phone) && Objects.equals(secondName, other.secondName)
                && studentStatus == other.studentStatus;
    }

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public static class StudentBuilder {

        private int id;
        private Gang gang;
        private String firstName;
        private String secondName;
        private String lastName;
        private String contactName;
        private String phone;
        private String email;
        private String address;
        private Gender gender;
        private StudentStatus studentStatus;
        private LocalDate birthDate;
        private String description;

        public StudentBuilder id(int id) {
            this.id = id;
            return this;
        }

        public StudentBuilder gang(Gang gang) {
            this.gang = gang;
            return this;
        }

        public StudentBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public StudentBuilder secondName(String secondName) {
            this.secondName = secondName;
            return this;
        }

        public StudentBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public StudentBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public StudentBuilder email(String email) {
            this.email = email;
            return this;
        }

        public StudentBuilder address(String address) {
            this.address = address;
            return this;
        }

        public StudentBuilder contactName(String contactName) {
            this.contactName = contactName;
            return this;
        }

        public StudentBuilder studentStatus(StudentStatus studentStatus) {
            this.studentStatus = studentStatus;
            return this;
        }

        public StudentBuilder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public StudentBuilder description(String description) {
            this.description = description;
            return this;
        }

        public StudentBuilder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Student build() {
            return new Student(id, gang, firstName, secondName, lastName, contactName, phone, email, address, gender,
                    studentStatus, birthDate, description);
        }

    }

}
