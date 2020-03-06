package fmi.informatics.test;

import fmi.informatics.extending.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnitTest {
    @Test
    public void testFunctionalTask(){
        List<Student> studentList = Stream.of(Student.StudentGenerator.make(),
                Student.StudentGenerator.make(),
                Student.StudentGenerator.make())
                .filter(s -> s.getName().length() >= 5)
                .sorted((s1, s2) -> s1.getName().compareTo(s2.getName())).collect(Collectors.toList());

        boolean isLengthCorrect = true;
        for (Student student:studentList){
            if(student.getName().length() > 5){
                isLengthCorrect = false;
            }
        }

        Assert.assertTrue("The method testFunctionalTask works incorrect!",isLengthCorrect);
    }

    @Test
    public void testUpperCaseFunction(){
        List<String> elements = Stream.of("cat", "dog", "cow", "bird")
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        int lowerCaseLetters = 0;
        for (String element:elements){
            if(Character.isLowerCase(element.charAt(0))){
                lowerCaseLetters++;
            }
        }
        Assert.assertEquals("UpperCaseFunction  works incorrect!",0,lowerCaseLetters);
    }

    @Test
    public void testConcatenation(){
        List<Student> studs = new ArrayList<>();
        for (int i = 0; i < 5; ++i) studs.add(Student.StudentGenerator.make());
        String studNames = studs.stream()
                .sorted((s1, s2) -> s1.getName().compareTo(s2.getName()))
                .map(s -> s.getName())
                .reduce((s1, s2) -> {
                    if (s2 != null) return s1 + ", " + s2;
                    return s1;
                })
                .get();
        Assert.assertNotNull("Concatenation method works incorrect",studNames);
    }



}
