package fmi.informatics.tests;

import fmi.informatics.events.events.hw.Party;
import fmi.informatics.events.events.hw.Teenager;
import fmi.informatics.extending.Person;
import fmi.informatics.extending.Professor;
import fmi.informatics.extending.Student;



import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UnitTests  {
    public void testGenderChecker(){

        Person student = new Student("Ivan",123,
                "SU","KS",004);

        if(student.getGender() == 'm'){
            System.out.println("Method genderCheck works correctly!");
        }
        else {
            System.out.println("Method genderCheck works incorrectly!");
        }

    }

    public static void testProfessorGenerator(){
        Person person =  Professor.ProfessorGenerator.make();
        if(person != null){
            System.out.println("Professor generator method works fine!");
        }
        else {
            System.out.println("Professor generator method doesn't work!");
        }
    }

    public static void testStudentGenerator(){
        Person student = Student.StudentGenerator.make();
        if(student != null){
            System.out.println("Student generator method works fine!");
        }
        else {
            System.out.println("Student generator method doesn't work!");
        }
    }

    public static void testAddMethodInPartyClass(){



        Teenager teen1 = new Teenager("Ivan");
        Teenager teen2 = new Teenager("Stefan");

        Party party = new Party();

        party.addPerson(teen1);
        party.addPerson(teen2);

        if (assertInt(2,party.getSIze())){
            System.out.println("addPerson method works correctly");
        }
        else{
            System.out.println("addPerson method workds incorrectly");
        }


    }

    public static boolean assertChar(char expected,char result){
        return expected == result;
    }
    public static boolean assertInt(int expected,int result){
        return expected == result;
    }

}
