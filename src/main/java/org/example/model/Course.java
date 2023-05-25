package org.example.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "`courses`", schema = "`drivingschool`")
public class Course {

    @Id
    private int id;
    @Column(name = "`course_name`")
    private String courseName;
    @Column(name = "`category`")
    private String category;
    @Column(name = "`start_date`")
    private LocalDate startDate;
    @Column(name = "`end_date`")
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn (name = "teacher_id")
    private Teacher teacher;
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Student> students;

    Course(){
    }

    public Course(String courseName, String category, LocalDate startDate, LocalDate endDate) {
        this.courseName = courseName;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", category='" + category + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", teacher=" + teacher +
                ", students=" + students.size() +
                '}';
    }
}
