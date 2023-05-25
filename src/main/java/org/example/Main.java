package org.example;

import org.example.dao.CourseDAO;
import org.example.dao.StudentDAO;
import org.example.dao.TeacherDAO;
import org.example.model.Course;
import org.example.model.Student;
import org.example.model.Teacher;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        char again = 'y';

        System.out.println("\nWelcome to Driving School Management System!\n");

        while (again == 'y') {
            System.out.println("Choose one option (s,a,d)");
            System.out.println("s - show all students");
            System.out.println("a - add a new student");
            System.out.println("c - add a new course");
            System.out.println("d - delete course");
            System.out.println("n - exit");


            char action = scanner.nextLine().charAt(0);

            if (action == 'a') {
                addNewStudent();
            } else if (action == 's') {
                showAllStudents();
            } else if (action == 'd') {
                deleteCourse();
            } else if (action == 'c') {
                addNewCourse();
            } else if (action == 'e') {
                again = 'n';
            }
        }
    }

    private static void addNewCourse() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter course name");
        String newCourse = scanner.nextLine();
        System.out.println("Enter category");
        String newCategory = scanner.nextLine();
        System.out.println("Enter start date of the course (dd.mm.yyyy)");
        LocalDate startDate = parseDateFromScanner(formatter, scanner);
        System.out.println("Enter end date of the course (dd.mm.yyyy)");
        LocalDate endDate = parseDateFromScanner(formatter, scanner);

        CourseDAO courseDAO = new CourseDAO();
        TeacherDAO teacherDAO = new TeacherDAO();
        List<Teacher> teachers = teacherDAO.getTeachers();
        System.out.println("\nChoose a teacher for this course\n");
        for (Teacher teacher : teachers) {
            System.out.printf("#%d: %s%n", teacher.getId(), teacher.getFullName());
        }
        System.out.print("Teacher Id: # ");
        int teacherId = scanner.nextInt();
        Teacher teacher = teacherDAO.getTeacherById(teacherId);

        try {
            courseDAO.createNewCourse(teacher, newCourse, newCategory, startDate, endDate);
            System.out.println("\nNew course added successfully!\n");
        } catch (Exception e) {
            System.out.println("\nSomething went wrong\n" + e);
        }
    }

    private static LocalDate parseDateFromScanner(DateTimeFormatter formatter, Scanner scanner) {
        LocalDate startDate;
        while (true) {
            try {
                startDate = LocalDate.parse(scanner.next(), formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("\nInvalid date format! (dd.mm.yyyy)");
                System.out.println("\nPlease try again:");
            }
        }
        return startDate;
    }

    private static void deleteCourse() {

        Scanner scanner = new Scanner(System.in);

        CourseDAO courseDAO = new CourseDAO();
        List<Course> courses = courseDAO.getCourses();
        System.out.println("\nSelect course to delete");
        for (Course course : courses) {
            System.out.printf("#%d: %s%n", course.getId(), course.getCourseName());
        }
        System.out.print("Insert Id: # ");
        int courseId = scanner.nextInt();
        boolean deleted = courseDAO.deleteCourseById(courseId);
        if (deleted) {
            System.out.println("Course deleted successfully");
        } else {
            System.out.println("Course is not deleted");
        }
    }

    private static void addNewStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name and lastname");
        String newFullName = scanner.nextLine();
        System.out.println("Enter id code");
        String newIdCode = scanner.nextLine();
        System.out.println("Enter telephone");
        String newTelephone = scanner.nextLine();
        System.out.println("Enter address");
        String newAddress = scanner.nextLine();
        System.out.println("Enter email");
        String newEmail = scanner.nextLine();

        CourseDAO courseDAO = new CourseDAO();
        List<Course> courses = courseDAO.getCourses();
        System.out.println("\nChoose a course for the student: ");
        for (Course course : courses) {
            System.out.printf("#%d: %s%n", course.getId(), course.getCourseName());
        }
        System.out.print("Course Id: #");
        int courseId = scanner.nextInt();
        Course course = courseDAO.getCourseById(courseId);

        StudentDAO studentDAO = new StudentDAO();
        try {
            studentDAO.addNewStudent(course, newFullName, newIdCode, newTelephone, newAddress, newEmail);
            System.out.println("\nNew student added successfully!\n");
        } catch (Exception e) {
            System.out.println("\nSomething went wrong\n" + e);
        }
    }

    private static void showAllStudents() {
        StudentDAO studentDAO = new StudentDAO();
        List<Student> students = studentDAO.getStudents();
        System.out.println(String.format("%-2s %-20s %-20s %-20s %-20s %-20s %-20s",
                "Id",
                "Name Lastname",
                "Id code",
                "Phone",
                "Address",
                "Email",
                "Course Name"));
        for (Student student : students) {
            System.out.println(String.format("%-2d %-20s %-20s %-20s %-20s %-20s %-20s",
                    student.getId(),
                    student.getFullName(),
                    student.getIdCode(),
                    student.getPhone(),
                    student.getAddress(),
                    student.getEmail(),
                    student.getCourse().getCourseName()));
        }
    }


}
