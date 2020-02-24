package comparators;

import extending.Student;

import java.util.Comparator;

public class SurnameComparator extends StudentComparator {
    @Override
    public int compare(Student student, Student student1) {
        if (student.getSurName().equals(student1.getSurName())) {
            return 0;
        } else {
            return (student.getSurName().compareTo(student1.getSurName()) * sortOrder);
        }
    }
}
