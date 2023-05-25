package org.example.dao;

import org.example.model.Course;
import org.example.model.Student;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDAO {

    public Student addNewStudent(Course course, String fullName, String idCode, String phone,
                                 String address, String email) {

        Session session = HibernateUtils.getCurrentSessionFromConfig();
        Transaction trn = session.beginTransaction();

        Student student = new Student();
        student.setFullName(fullName);
        student.setIdCode(idCode);
        student.setPhone(phone);
        student.setAddress(address);
        student.setEmail(email);

        student.setCourse(course);
        session.save(student);
        trn.commit();

        return student;
    }

    public List<Student> getStudents() {
        Session session = HibernateUtils.getCurrentSessionFromConfig();
        Transaction trn = session.beginTransaction();
        List<Student> students = session.createCriteria(Student.class).list();
        trn.commit();
        return students;
    }
}
