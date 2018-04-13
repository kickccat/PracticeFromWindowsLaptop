package de.is2.example.demo.students.dao;

import de.is2.example.demo.students.domain.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier(value = "fakeData")
public class FakeStudentDaoImpl implements StudentDao {

    private static Map<Integer, Student> students;

    static {

        students = new HashMap<>() {
            {
                put(1, new Student(1, "Joey", "CS"));
                put(2, new Student(2, "Lily", "Math"));
                put(3, new Student(3, "Anna", "Chemie"));
            }
        };
    }

    @Override
    public Collection<Student> getAllStudents(){
        return students.values();
    }

    @Override
    public Student getStudentById(int id){
        return students.get(id);
    }

    @Override
    public void removeStudentById(int id) {
        students.remove(id);
    }

    @Override
    public void updateStudent(Student student){
        Student student1 = students.get(student.getId());
        student1.setCourse(student.getCourse());
        student1.setName(student.getName());
        students.put(student.getId(), student);
    }

    @Override
    public void insertStudentToDB(Student student) {
        students.put(student.getId(), student);
    }
}
