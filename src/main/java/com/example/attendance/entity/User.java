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
    private int epfNo;
    private int qrCode;
    private int tableNo = 0;
    private boolean attend = Boolean.FALSE;

    public User() {}

    public User(int id, String status, String name, String company, String department, int epfNo, int qrCode, int tableNo, boolean attend) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.company = company;
        this.department = department;
        this.epfNo = epfNo;
        this.qrCode = qrCode;
        this.tableNo = tableNo;
        this.attend = attend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getEpfNo() {
        return epfNo;
    }

    public void setEpfNo(int epfNo) {
        this.epfNo = epfNo;
    }

    public int getQrCode() {
        return qrCode;
    }

    public void setQrCode(int qrCode) {
        this.qrCode = qrCode;
    }

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public boolean isAttend() {
        return attend;
    }

    public void setAttend(boolean attend) {
        this.attend = attend;
    }
}
