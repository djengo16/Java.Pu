package gui

import extending.Student

class StudentDataGuiTest extends GroovyTestCase {
    void testFilterData() {
        Student student = new Student("Ivan","Ivanov","Georgiev");
        Student student1 = new Student("Stefan","Petrov","Petrov");
        Student[] students = new Student[2];
        students[0] = student;
        student[1] = student1;

        StudentDataGui.filterData(students,"Petrov");
        ArrayList<Student> studentsAsList = new ArrayList<>(Student);

        studentsAsList = Arrays.asList(students);

        assertArrayEquals(1,studentsAsList.size());
        assertArrayEquals("Petrov",studentsAsList[0].lastName);
    }
}
