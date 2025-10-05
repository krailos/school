package com.krailo.school.entity;

import java.time.LocalTime;
import java.util.Objects;

public class Schedule {

    private int id;
    private Audience audience;
    private Gang gang;
    private WeekDay weekDay;
    private LocalTime startTime;
    private LocalTime endTime;

    public Schedule() {

    }

    public Schedule(int id, Audience audience, Gang gang, WeekDay weekDay, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.audience = audience;
        this.gang = gang;
        this.weekDay = weekDay;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Schedule(Audience audience, Gang gang, WeekDay weekDay, LocalTime startTime, LocalTime endTime) {
        this.audience = audience;
        this.gang = gang;
        this.weekDay = weekDay;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Audience getAudience() {
        return audience;
    }

    public void setAudience(Audience audience) {
        this.audience = audience;
    }

    public Gang getGang() {
        return gang;
    }

    public void setGang(Gang gang) {
        this.gang = gang;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Schedule [id=" + id + ", audience=" + audience + ", gang=" + gang + ", weekDay=" + weekDay
                + ", startTime=" + startTime + ", endTime=" + endTime + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(audience, endTime, gang, id, startTime, weekDay);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Schedule other = (Schedule) obj;
        return Objects.equals(audience, other.audience) && Objects.equals(endTime, other.endTime)
                && Objects.equals(gang, other.gang) && id == other.id && Objects.equals(startTime, other.startTime)
                && weekDay == other.weekDay;
    }

    public static ScheduleBuilder builder() {
        return new ScheduleBuilder();
    }

    public static class ScheduleBuilder {
        private int id;
        private Audience audience;
        private Gang gang;
        private WeekDay weekDay;
        private LocalTime startTime;
        private LocalTime endTime;

        public ScheduleBuilder id(int id) {
            this.id = id;
            return this;
        }

        public ScheduleBuilder audience (Audience audience) {
            this.audience = audience;
            return this;
        }

        public ScheduleBuilder gang (Gang gang) {
            this.gang = gang;
            return this;
        }

        public ScheduleBuilder weekDay(WeekDay weekDay) {
            this.weekDay = weekDay;
            return this;
        }

        public ScheduleBuilder startTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public ScheduleBuilder endTime(LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Schedule build() {
            return new Schedule(id, audience, gang, weekDay, startTime, endTime);
        }

    }

}
