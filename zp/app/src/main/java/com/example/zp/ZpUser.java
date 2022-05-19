package com.example.zp;

public class ZpUser {
    private String id;
    private String username;
    private String password;
    private String onespeak;
    private String skill;

    private String byyx;
    private String xl;
    private String age;

    public String getByyx() {
        return byyx;
    }

    public void setByyx(String byyx) {
        this.byyx = byyx;
    }

    public String getXl() {
        return xl;
    }

    public void setXl(String xl) {
        this.xl = xl;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public ZpUser() {
    }

    public ZpUser(String username, String password, String onespeak, String skill) {
        this.username = username;
        this.password = password;
        this.onespeak = onespeak;
        this.skill = skill;
    }

    public ZpUser(String username, String password, String onespeak, String skill, String byyx, String xl,String workstu, String age) {
        this.username = username;
        this.password = password;
        this.onespeak = onespeak;
        this.skill = skill;
        this.byyx = byyx;
        this.xl = xl;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOnespeak() {
        return onespeak;
    }

    public void setOnespeak(String onespeak) {
        this.onespeak = onespeak;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
