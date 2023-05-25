package org.example.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "`teachers`", schema = "`drivingschool`")
public class Teacher {

    @Id
    private int id;
    @Column(name = "`fullname`")
    private String fullName;
    @Column(name = "`phone`")
    private String phone;
    @Column(name = "`address`")
    private String address;
    @Column(name = "`email`")
    private String email;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Course> courses;

    public Teacher () {
    }

    public Teacher(String fullName, String phone, String address, String email) {
        this.fullName = fullName;
        this.phone = phone;
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
        this.fullName = fullName;
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

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", courses=" + courses.size() +
                '}';
    }
}
