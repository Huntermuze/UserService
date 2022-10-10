package com.huntermuze.userservice.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "User")
@Table(name = "User")
public class UserPOJO {
    @Id
    @GeneratedValue
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "address", nullable = false, unique = true)
    private String address;
    @Column(name = "age", nullable = false)
    private int age;
    @Column(name = "job", nullable = false)
    private String job;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "mobile_number", nullable = false, unique = true)
    private String mobileNumber;

    public UserPOJO(String id, String name, String address, int age, String job, String email, String mobileNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.job = job;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public UserPOJO() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPOJO userPOJO = (UserPOJO) o;
        return age == userPOJO.age && Objects.equals(id, userPOJO.id) && Objects.equals(name, userPOJO.name) &&
                Objects.equals(address, userPOJO.address) && Objects.equals(job, userPOJO.job) &&
                Objects.equals(email, userPOJO.email) && Objects.equals(mobileNumber, userPOJO.mobileNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, age, job, email, mobileNumber);
    }

    @Override
    public String toString() {
        return "UserPOJO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
