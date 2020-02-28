package gui

import extending.Student
import org.testng.annotations.Test

class StudentDataGuiTest extends GroovyTestCase {
    @Test
    void testFilterData() {
        Student student = new Student("Ivan","Georgiev","Ivanov");
        Student[] students = new Student[1];
        students[0] = student;
        StudentDataGui.filterData(students,"Stefanov");
        assertEquals(null,students[0]);
    }
}
