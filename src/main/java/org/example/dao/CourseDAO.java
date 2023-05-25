package org.example.dao;

import org.example.model.Course;
import org.example.model.Teacher;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.time.LocalDate;
import java.util.List;

public class CourseDAO {


    public Course createNewCourse(Teacher teacher, String newCourse, String newCategory, LocalDate startDate,
                                  LocalDate endDate) {

        Session session = HibernateUtils.getCurrentSessionFromConfig();
        Transaction trn = session.beginTransaction();

        Course course = new Course(newCourse, newCategory, startDate, endDate);
        course.setCourseName(newCourse);
        course.setCategory(newCategory);
        course.setStartDate(startDate);
        course.setEndDate(endDate);

        course.setTeacher(teacher);
        session.save(course);
        trn.commit();
        return course;
    }

    public Course getCourseById(int courseId) {
        Session session = HibernateUtils.getCurrentSessionFromConfig();
        Transaction trn = session.beginTransaction();
        Course course = session.load(Course.class, courseId);
        trn.commit();
        return course;
    }

    public boolean deleteCourseById(int courseId) {
        Session session = HibernateUtils.getCurrentSessionFromConfig();
        Transaction trn = session.beginTransaction();
        try {
            Course course = session.load(Course.class, courseId);
            session.delete(course);
            trn.commit();
        } catch (Exception e) {
            trn.rollback();
            return false;
        }
        return true;
    }

    public List<Course> getCourses() {
        Session session = HibernateUtils.getCurrentSessionFromConfig();
        Transaction trn = session.beginTransaction();
        List<Course> courses = session.createCriteria(Course.class).list();
        trn.commit();
        return courses;
    }

}
