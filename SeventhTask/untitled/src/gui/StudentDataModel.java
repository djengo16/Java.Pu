package gui;

import extending.Student;

import javax.swing.table.AbstractTableModel;

public class StudentDataModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    private Student[] people;

    // constructor
    public StudentDataModel(Student[] people) {
        this.people = people;
    }

    @Override
    public int getColumnCount() {
        return 3; // брой колони в таблицата
    }

    @Override
    public int getRowCount() {
        return people.length; // брой редове в таблицата
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return people[rowIndex].getName();
            case 1:
                return people[rowIndex].getSurName();
            case 2:
                return people[rowIndex].getLastName();

        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Име";
            case 1:
                return "Презиме";
            case 2:
                return "Фамилия";

            default:
                return super.getColumnName(column);
        }
    }
}
