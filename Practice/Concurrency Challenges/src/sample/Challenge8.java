package sample;

public class Challenge8 {
    
    public static void main(String[] args) {
        final Tutor tutor = new Tutor();
        final Student student = new Student(tutor);
    
        tutor.setStudent(student);
        
        Thread tutorThread = new Thread(tutor::studyTime);
        
        Thread studentThread = new Thread(student::handInAssignment);
        
        tutorThread.start();
        studentThread.start();
    }
}

class Tutor {
    
    private Student student;
    
    void setStudent(Student student) {
        this.student = student;
    }
    
    void studyTime() {
        synchronized (this) {
            System.out.println("Tutor has arrived");
        }
        synchronized (student) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            student.startStudy();
            System.out.println("Tutor is studying with student");
        }
    }
    
    void getProgressReport() {
        System.out.println("Tutor gave progress report");
    }
}

class Student {
    
    private Tutor tutor;
    
    Student(Tutor tutor) {
        this.tutor = tutor;
    }
    
    void startStudy() {
        System.out.println("Student is studying");
    }
    
    void handInAssignment() {
        synchronized (tutor) {
            tutor.getProgressReport();
            synchronized (this) {
                System.out.println("Student handed in assignment");
                tutor.notifyAll();
            }
        }
    }
}
