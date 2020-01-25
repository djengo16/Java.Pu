package fmi.informatics.tests;

import fmi.informatics.extending.Professor;
import fmi.informatics.extending.Student;
import fmi.informatics.repositories.PersonRepo;

public class UnitTests {

    public static void testStudentCounter(PersonRepo people){
        people.addPerson(new Student());
        people.addPerson(new Student());


        if (assertInt(2,people.getStudentCount())){
            System.out.println("Method getStudentCount works correctly");
        }
        else {
            System.out.println("Method getStudentCount works incorrectly");
        }
    }

    public static void testProffesorCounter(PersonRepo people){
        people.addPerson(new Professor());
        people.addPerson(new Professor());


        if (assertInt(2,people.getProfessorCount())){
            System.out.println("Method getProffesorCount works correctly");
        }
        else {
            System.out.println("Method getProffesorCount works incorrectly");
        }
    }

    public static void testGetPeopleCount(PersonRepo people){
        people.addPerson(new Student());
        people.addPerson(new Student());
        people.addPerson(new Professor());


        if (assertInt(3,people.getPeopleCount())){
            System.out.println("Method getPeopleCount works correctly");
        }
        else {
            System.out.println("Method getPeopleCount works incorrectly");
        }
    }

    public static void testAddMethod(PersonRepo people){
        Student student = new Student("ivan",2,"pu","inf",3);
        people.addPerson(student);


        if (assertInt(1,people.getPeopleCount())){
            System.out.println("Method addPerson works correctly");
        }
        else {
            System.out.println("Method addPerson works incorrectly");
        }
    }


    public static void testRemoveMehod(PersonRepo people){
        Student student = new Student("ivan",2,"pu","inf",3);
        people.addPerson(student);
        people.removePerson(student);

        if (assertInt(0,people.getPeopleCount())){
            System.out.println("Method removePerson works correctly");
        }
        else {
            System.out.println("Method removePerson works incorrectly");
        }
    }

    private static boolean assertInt(int expected,int result){
        return expected == result;
    }
}
