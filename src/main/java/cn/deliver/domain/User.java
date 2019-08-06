package cn.deliver.domain;

import java.sql.Timestamp;

public class User {
    private Integer id;

    private String authId;

    private String phone;

    private String password;

    private String status;

    private String role;

    private Timestamp registerTime;

    private String registerArea;

    private String registerRole;

    private String email;

    public User() {
    }

    public User(Integer id, String authId, String phone, String password, String status, String role, Timestamp registerTime, String registerArea, String registerRole, String email) {
        this.id = id;
        this.authId = authId;
        this.phone = phone;
        this.password = password;
        this.status = status;
        this.role = role;
        this.registerTime = registerTime;
        this.registerArea = registerArea;
        this.registerRole = registerRole;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", authId='" + authId + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", role='" + role + '\'' +
                ", registerTime=" + registerTime +
                ", registerArea='" + registerArea + '\'' +
                ", registerRole='" + registerRole + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public String getRegisterArea() {
        return registerArea;
    }

    public void setRegisterArea(String registerArea) {
        this.registerArea = registerArea;
    }

    public String getRegisterRole() {
        return registerRole;
    }

    public void setRegisterRole(String registerRole) {
        this.registerRole = registerRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}