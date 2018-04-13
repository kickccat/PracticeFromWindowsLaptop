package de.is2.example.demo.students.dao;

import de.is2.example.demo.students.domain.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository
@Qualifier (value = "mongoData")
public class MongoStudentDaoImpl implements StudentDao {

    @Override
    public Collection<Student> getAllStudents() {
        return new ArrayList<Student>() {
            {
                add(new Student(1, "Mario", "Physics"));
                add(new Student(2, "Sara", "CS"));
            }
        };
    }

    @Override
    public Student getStudentById(int id) {
        return null;
    }

    @Override
    public void removeStudentById(int id) {

    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void insertStudentToDB(Student student) {

    }
}
