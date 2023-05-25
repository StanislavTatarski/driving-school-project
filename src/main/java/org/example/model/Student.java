package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "`students`", schema = "`drivingschool`")
public class Student {

    @Id
    private int id;
    @Column(name = "`fullname`")
    private String fullName;
    @Column(name = "`id_code`")
    private String idCode;
    @Column(name = "`phone`")
    private String phone;
    @Column(name = "`address`")
    private String address;
    @Column(name = "`email`")
    private String email;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Student() {
    }

    public Student(String fullName, String idCode, String telephone, String address, String email) {
        this.fullName = fullName;
        this.idCode = idCode;
        this.phone = telephone;
        this.address = address;
        this.email = email;
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName.strip();
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", idCode='" + idCode + '\'' +
                ", telephone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", course=" + course +
                '}';
    }
}
