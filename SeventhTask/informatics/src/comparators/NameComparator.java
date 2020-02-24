package comparators;

import extending.Student;

public class NameComparator extends StudentComparator {
    @Override
    public int compare(Student student, Student student1) {
        if (student.getName().equals(student1.getName())) {
            return 0;
        } else {
            return (student.getName().compareTo(student1.getName()) * sortOrder);
        }
    }
}
