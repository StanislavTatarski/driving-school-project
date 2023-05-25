package org.example.dao;

import org.example.model.Teacher;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TeacherDAO {

    public Teacher createTeacher(String fullName, String phone, String address, String email) {

        Session session = HibernateUtils.getCurrentSessionFromConfig();
        Transaction trn = session.beginTransaction();

        Teacher teacher = new Teacher(fullName, phone, address, email);
        teacher.setFullName(fullName);
        teacher.setPhone(phone);
        teacher.setAddress(address);
        teacher.setEmail(email);

        session.save(teacher);
        trn.commit();

        return teacher;
    }
    public Teacher getTeacherById(int teacherId) {
        Session session = HibernateUtils.getCurrentSessionFromConfig();
        Transaction trn = session.beginTransaction();
        Teacher teacher = session.load(Teacher.class, teacherId);
        trn.commit();
        return teacher;
    }
    public List<Teacher> getTeachers() {
        Session session = HibernateUtils.getCurrentSessionFromConfig();
        Transaction trn = session.beginTransaction();
        List<Teacher> teachers = session.createCriteria(Teacher.class).list();
        trn.commit();
        return teachers;
    }
}
