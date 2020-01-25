package fmi.informatics.composition;

import fmi.informatics.extending.Anonymous;
import fmi.informatics.extending.Person;
import fmi.informatics.extending.Professor;
import fmi.informatics.extending.Student;
import fmi.informatics.interfaces.Adaptable;
import fmi.informatics.repositories.PersonRepo;
import fmi.informatics.tests.UnitTests;

public class TestClass {
    public static void main(String[] args) {


        Student student = new Student("Ivan", 123, "testUni", "Informatics", 234);
        Student student1 = new Student();
        Professor professor = new Professor();
        RichStudent2 richStudent2 = new RichStudent2("Stefan", 23, "test", "std", 214);

        PersonRepo people = new PersonRepo();
        people.addPerson(student);
        people.addPerson(student1);
        people.addPerson(professor);
        people.addPerson(richStudent2);

        System.out.println(people.getStudentCount());
        System.out.println(people.getProfessorCount());
        Statistics.showStatistics(people);

        UnitTests.testGetPeopleCount(new PersonRepo());

        Anonymous an = new Anonymous();
        an.study();


    }


}
