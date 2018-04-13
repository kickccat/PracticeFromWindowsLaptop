package de.is2.example.demo.students.service;

import de.is2.example.demo.students.dao.StudentDao;
import de.is2.example.demo.students.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentService {

    @Autowired
    @Qualifier(value = "mongoData")
    private StudentDao studentDao;

    public Collection<Student> getAllStudents(){
        return studentDao.getAllStudents();
    }

    public Student getStudentById(int id){
        return studentDao.getStudentById(id);
    }

    public void removeStudentById(int id) {
        studentDao.removeStudentById(id);
    }

    public void updateStudent(Student student){
        studentDao.updateStudent(student);
    }

    public void insertStudent(Student student) {
        studentDao.insertStudentToDB(student);
    }
}
