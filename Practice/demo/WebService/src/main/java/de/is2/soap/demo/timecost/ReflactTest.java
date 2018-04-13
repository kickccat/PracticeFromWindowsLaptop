package de.is2.webservice.demo.timecost;

class Person{
    @Override
    public String toString() {
        return "It's a person.";
    }
}

public class ReflactTest {
    public static void main(String[] args) {
        Class<?> clazz;
        try {
            clazz = Class.forName("de.is2.webservice.demo.timecost.Person");
            Person person = (Person) clazz.newInstance();
            System.out.println(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
