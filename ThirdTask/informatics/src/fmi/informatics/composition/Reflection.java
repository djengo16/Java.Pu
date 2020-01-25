package fmi.informatics.composition;

import fmi.informatics.extending.Person;
import fmi.informatics.extending.Student;

import java.lang.reflect.Constructor;

public class Reflection {
    public static void main(String[] args) {

        try {
            Class cls = Class.forName("fmi.informatics.extending.Student");

            System.out.println("Class found: " + cls.getName());
            System.out.println("Package = " + cls.getPackage());

        } catch(ClassNotFoundException ex) {

            System.out.println(ex.toString());
        }

            Class student = new Student().getClass();

        System.out.println(student.getSuperclass().getSimpleName());


            try {
                Student s = new Student();
                System.out.println(s);
                Student b = s.getClass().newInstance();
                System.out.println(b);
            }
            catch(InstantiationException e) {
                System.out.println(e.toString());
            } catch(IllegalAccessException e) {
                System.out.println(e.toString());
            }

    }

}
