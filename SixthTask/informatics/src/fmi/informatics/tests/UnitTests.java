package fmi.informatics.tests;

import fmi.informatics.extending.Person;
import fmi.informatics.extending.Professor;
import fmi.informatics.extending.Student;
import fmi.informatics.gui.PersonDataGUI;

public class UnitTests {


    public  static  void TestFilterDataMethod(){
        Person[] people = new Person[3];
        Professor professor1 = new Professor();
        Professor professor2 = new Professor();
        Student student = new Student();

        Person[] filteredPeople = new Person[1];

        try{
            filteredPeople = PersonDataGUI.filterData(people,Student.class);
            System.out.println("Filter Method works correctly!");
        }
        catch(Exception e) {
            System.out.println("Filter Method works incorrectly!");
        }
    }

    public static void TestMethodSetGender(){

      Person person = new Student();
      person.setName("Ivan");
      Person.setGender(person);
      if (person.getGender() == 'm'){
          System.out.println("Method setGender works correctly!");
      }
  }
}
