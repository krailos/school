package com.krailo.school.entity;

import java.util.Objects;

public class Gang {

    private int id;
    private String name;
    private String description;
    private Subject subject;
    private Teacher teacher;

    public Gang() {

    }

    public Gang(int id, String name, String description, Subject subject, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.subject = subject;
        this.teacher = teacher;
    }

    public Gang(String name, String description, Subject subject, Teacher teacher) {
        this.name = name;
        this.description = description;
        this.subject = subject;
        this.teacher = teacher;
    }

    public Gang(String name, Subject subject, Teacher teacher) {
        this.name = name;
        this.subject = subject;
        this.teacher = teacher;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Gang [id=" + id + ", name=" + name + ", description=" + description + ", subject=" + subject
                + ", teacher=" + teacher + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, id, name, subject, teacher);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Gang other = (Gang) obj;
        return Objects.equals(description, other.description) && id == other.id && Objects.equals(name, other.name)
                && Objects.equals(subject, other.subject) && Objects.equals(teacher, other.teacher);
    }
    
    public static GangBuilder builder () {
        return new GangBuilder();
    }

    public static class GangBuilder {
        private int id;
        private String name;
        private String description;
        private Subject subject;
        private Teacher teacher;

        public GangBuilder id(int id) {
            this.id = id;
            return this;
        }

        public GangBuilder name(String name) {
            this.name = name;
            return this;
        }

        public GangBuilder description(String description) {
            this.description = description;
            return this;
        }

        public GangBuilder subject(Subject subject) {
            this.subject = subject;
            return this;
        }

        public GangBuilder teacher(Teacher teacher) {
            this.teacher = teacher;
            return this;
        }

        public Gang build() {
            return new Gang(id, name, description, subject, teacher);
        }

    }

}
