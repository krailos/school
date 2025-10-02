package com.krailo.school.entity;

import java.util.Objects;

public class Audience {

    private int id;
    private String name;
    private String description;

    public Audience() {
    }

    public Audience(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Audience(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Audience(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Audience [id=" + id + ", name=" + name + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Audience other = (Audience) obj;
        return Objects.equals(description, other.description) && id == other.id && Objects.equals(name, other.name);
    }

}
