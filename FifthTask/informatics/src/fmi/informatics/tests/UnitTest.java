package fmi.informatics.tests;

import com.sun.jdi.Value;
import fmi.informatics.extending.Person;

import java.util.ArrayList;
import java.util.List;

public class UnitTest {

        public static void TestAscendingSortPeopleByHeight(List<Person> people){

            for (int i = 0;i < people.size() - 1;i++) {
                    if (people.get(i).getHeight() > people.get(i+1).getHeight()) {
                        System.out.println("Ascending sort method works incorrectly!");
                        return;
                    }
            }
            System.out.println("Ascending sort method works correctly");

        }


        public static void TestDescendingSortPeopleByHeight(List<Person> people){

        for (int i = 0;i < people.size() - 1;i++) {
            if (people.get(i).getHeight() < people.get(i+1).getHeight()) {
                System.out.println("Descending sort method works incorrectly!");
                return;
            }
            System.out.println("Descending sort method works correctly");
            }
       }

    public static void TestAscendingSortPeopleByAge(List<Person> people){

        for (int i = 0;i < people.size() - 1;i++) {
            if (people.get(i).getAge() > people.get(i+1).getAge()) {
                System.out.println("Ascending sort method works incorrectly!");
                return;
            }
        }
        System.out.println("Ascending sort method works correctly!");

    }


    public static void TestDescendingSortPeopleByAge(List<Person> people){

        for (int i = 0;i < people.size() - 1;i++) {
            if (people.get(i).getAge() < people.get(i+1).getAge()) {
                System.out.println("Descending sort method works incorrectly!");
                return;
            }
            System.out.println("Descending sort method works correctly");
        }
    }






}



