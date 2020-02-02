package fmi.informatics.tests;

import fmi.informatics.extending.Person;
import fmi.informatics.extending.Professor;
import fmi.informatics.extending.Student;

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

    public void testProfessorGenerator(){
        Person person =  Professor.ProfessorGenerator.make();
        if(person != null){
            System.out.println("Professor generator method works fine!");
        }
        else {
            System.out.println("Professor generator method doesn't work!");
        }
    }

    public void testStudentGenerator(){
        Person student = Student.StudentGenerator.make();
        if(student != null){
            System.out.println("Student generator method works fine!");
        }
        else {
            System.out.println("Student generator method doesn't work!");
        }
    }

    public static boolean assertChar(char expected,char result){
        return expected == result;
    }

}
