package fmi.informatics.tests;

import fmi.informatics.extending.Student;

public class StudentTest {

    public static void getTypicalDrinkTest(Student student){
        String typicalDrinkExpected = "Whiskey";
        String typicalDrinkResult = student.getTypicalDrink();

        if (assertString(typicalDrinkResult,typicalDrinkExpected)){
            System.out.println("Method getTypicalDrink() from class Student works correctly");
        }
        else {
            System.err.println("Method getTypicalDrink() from class Student workds incorectly");
        }
    }

    public static boolean assertString(String result,String expected){
        return result == expected;
    }
}
