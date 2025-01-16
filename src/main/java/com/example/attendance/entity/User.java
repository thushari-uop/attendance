package com.example.attendance.entity;

import jakarta.persistence.*;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String status;
    private String name;
    private String company;
    private String department;
    private int tableNo = 0;
    private boolean attend = Boolean.FALSE;

    public User() {}

    public User(int id, String name, String company, String department, int tableNo, boolean attend, String status) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.department = department;
        this.tableNo = tableNo;
        this.attend = attend;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getDepartment() {
        return department;
    }

    public int getTableNo() {
        return tableNo;
    }

    public boolean isAttend() {
        return attend;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public void setAttend(boolean attend) {
        this.attend = attend;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
