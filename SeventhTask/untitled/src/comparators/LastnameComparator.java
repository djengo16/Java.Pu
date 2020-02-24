package comparators;

import extending.Student;

public class LastnameComparator extends StudentComparator {
    @Override
    public int compare(Student student, Student student1) {
        if (student.getLastName().equals(student1.getLastName())) {
            return 0;
        } else {
            return (student.getLastName().compareTo(student1.getLastName()) * sortOrder);
        }
    }
}
