package fmi.informatics.gui;

import javax.swing.table.AbstractTableModel;

import fmi.informatics.extending.Student;

// Създаваме клас PersonDataModel
public class PersonDataModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private Student[] students;

	// constructor
	public PersonDataModel(Student[] students) {
		this.students = students;
	}

	@Override
	public int getColumnCount() {
		return 6; // брой колони в таблицата
	}

	@Override
	public int getRowCount() {
		return students.length; // брой редове в таблицата
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case 0:
				return students[rowIndex].getName();
			case 1:
				return students[rowIndex].getEgn();
			case 2:
				return students[rowIndex].getAge();
			case 3:
				return students[rowIndex].getHeight();
			case 4:
				return students[rowIndex].getWeight();
				case 5:
					return students[rowIndex].getSpeciality();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
			case 0:
				return "Име";
			case 1:
				return "ЕГН";
			case 2:
				return "Години";
			case 3:
				return "Височина";
			case 4:
				return "Тегло";
			case 5:
				return"Специалност:";
	
			default:
				return super.getColumnName(column);
		}
	}
}
