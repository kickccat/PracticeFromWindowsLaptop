package de.is2.example.demo.students.dao;

import de.is2.example.demo.students.domain.Student;

import java.util.Collection;

public interface StudentDao {
    Collection<Student> getAllStudents();

    Student getStudentById(int id);

    void removeStudentById(int id);

    void updateStudent(Student student);

    void insertStudentToDB(Student student);
}
