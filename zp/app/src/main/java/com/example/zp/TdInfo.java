package com.example.zp;

public class TdInfo {
    private String id;
    private String job;
    private String username;
    private String place;
    private String ltdname;
    private String salary;
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TdInfo() {
    }

    public TdInfo(String job, String username, String place, String ltdname, String salary, String date) {
        this.job = job;
        this.username = username;
        this.place = place;
        this.ltdname = ltdname;
        this.salary = salary;
        this.date = date;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getLtdname() {
        return ltdname;
    }

    public void setLtdname(String ltdname) {
        this.ltdname = ltdname;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
