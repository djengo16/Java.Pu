package comparators;

import extending.Student;

import javax.swing.*;
import java.util.Comparator;

public abstract class StudentComparator implements Comparator<Student> {
    protected int sortOrder = 1; // Стойност по подразбиране, сортиране по възходящ ред

    public void setSortOrder(SortOrder order) {
        if (order.equals(SortOrder.DESCENDING)) {
            this.sortOrder = -1;
        } else {
            this.sortOrder = 1;
        }
    }

}
