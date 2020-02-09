package fmi.informatics.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;

import fmi.informatics.comparators.AgeComparator;
import fmi.informatics.comparators.EgnComparator;
import fmi.informatics.comparators.HeightComparator;
import fmi.informatics.comparators.NameComparator;
import fmi.informatics.comparators.PersonComparator;
import fmi.informatics.comparators.WeightComparator;
import fmi.informatics.extending.Person;
import fmi.informatics.extending.Professor;
import fmi.informatics.extending.Student;
import fmi.informatics.tests.UnitTest;

// създаваме клас PersonDataGUI
public class PersonDataGUI {
	
	public static Person[] people;
	JTable table;
	PersonDataModel personDataModel;

	public static void main(String[] args) {
		getPeople();
		PersonDataGUI gui = new PersonDataGUI();
		gui.createAndShowGUI();
	}
	
	public static void getPeople() {
		people = new Person[8];
		
		for (int i = 0; i < 4; i++) {
			Person student = Student.StudentGenerator.make();
			people[i] = student;
		}
		
		for (int i = 4; i < 8; i++) {
			Person professor = Professor.ProfessorGenerator.make();
			people[i] = professor;
		}
	}
	
	public void createAndShowGUI() {
		JFrame frame = new JFrame("Таблица с данни за хора");
		frame.setSize(500, 555);
		
		JLabel label = new JLabel("Списък с потребители", JLabel.CENTER);
		
		personDataModel = new PersonDataModel(people);
		table = new JTable(personDataModel);
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		// Добавяме бутон за сортиране по години с Comparable interface
		JButton buttonSortAge = new JButton("Сортирай по години");

		// Добавяме бутони за сортиране
		JButton buttonSortAscending = new JButton("Сортирай по възходящ ред");
		JButton buttonSortDescending = new JButton("Сортирай по низходящ ред");



		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(label, BorderLayout.NORTH);
		container.add(scrollPane, BorderLayout.CENTER);



		container.setLayout(new FlowLayout(FlowLayout.CENTER));
		container.add(buttonSortDescending);
		container.add(buttonSortAscending);
		container.add(buttonSortAge);


		
		// Добавяме listener към бутона за сортиране по години
		buttonSortAge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Arrays.sort(people);
				UnitTest.TestAscendingSortPeopleByAge(Arrays.asList(people));

				table.repaint();

			}
		});
		
		// Добавяме диалог
		final JDialog sortDialogAscending = new CustomDialog(getSortText(), this,SortOrder.ASCENDING);

		buttonSortAscending.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sortDialogAscending.pack();

				sortDialogAscending.setVisible(true);
			}
		});
		final JDialog sortDialogDescending = new CustomDialog(getSortText(), this,SortOrder.DESCENDING);
		// Добавяме listener към бутона за сортиране
		buttonSortDescending.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sortDialogDescending.pack();
				sortDialogDescending.setVisible(true);

			}
		}



		);
		
		frame.setVisible(true);
	}
	
	public void sortTable(int intValue, JTable table, Person[] people,SortOrder order) {
		PersonComparator comparator = null;


			switch (intValue) {
				case 1:
					comparator = new NameComparator();
					checkIfDescending( order, comparator);
					break;
				case 2:
					comparator = new EgnComparator();
					checkIfDescending( order, comparator);
					break;
				case 3:
					comparator = new HeightComparator();
					checkIfDescending( order, comparator);
					break;
				case 4:
					comparator = new WeightComparator();
					checkIfDescending( order, comparator);
					break;
				case 5:
					comparator = new AgeComparator();
					checkIfDescending( order, comparator);
					break;

			}

		if (comparator == null) { // Ако стойността е null - сортирай по подразбиране
			Arrays.sort(people); // Сортировка по подразбиране по години
		} else {
			Arrays.sort(people, comparator);

		}
		
		table.repaint();	
	}

	private static void checkIfDescending(SortOrder order,PersonComparator comparator){
		if (order.equals(SortOrder.DESCENDING)){
			comparator.setSortOrder(SortOrder.DESCENDING);

		}
	}

	private static String getSortText() {
		return "Моля, въведете цифрата на колоната, по която да се сортират данните: \n" +
				" 1 - Име \n" +
				" 2 - ЕГН \n" +
				" 3 - Височина \n" +
				" 4 - Тегло \n" +
				" 5 - Години \n"; 
	}
}
