package fmi.informatics.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import fmi.informatics.comparators.AgeComparator;
import fmi.informatics.comparators.EgnComparator;
import fmi.informatics.comparators.HeightComparator;
import fmi.informatics.comparators.NameComparator;
import fmi.informatics.comparators.PersonComparator;
import fmi.informatics.comparators.WeightComparator;
import fmi.informatics.enums.EType;
import fmi.informatics.extending.Person;
import fmi.informatics.extending.Professor;
import fmi.informatics.extending.Student;
import fmi.informatics.util.FileReader;

// създаваме клас PersonDataGUI

public class PersonDataGUI extends JFrame {

	public static Person[] defaultPeople;

	public static Person[] people;

	JTable table;
	PersonDataModel personDataModel;

	public static void main(String[] args) {
		// Ако извикваме четенето от файл, трябва да закоментираме метода getPeople()
		getPeople();
		
		// TODO Извикваме четенето от файл
		// people = FileReader.readPeople();
		
		// TODO Извикваме писането във файл
		initializeData();
		
		PersonDataGUI gui = new PersonDataGUI();
		gui.createAndShowGUI();
	}
	
	// TODO Добавяме писането/четенето във файл
	private static void initializeData() {
		if (!FileReader.isFileExists()) {
			FileReader.createPersonFile();
		}
		
		FileReader.writePeople(people);
	}
	
	public static void getPeople() {
		people = new Person[8];

		for (int i = 0; i < 4; i++) {
			Person student = Student.StudentGenerator.make();
			people[i] = student;
			Person.setGender(people[i]);
		}

		for (int i = 4; i < 8; i++) {
			Person professor = Professor.ProfessorGenerator.make();
			people[i] = professor;
			Person.setGender(people[i]);
		}
	}
	public static void getDefaultPeople(){
		defaultPeople = new Person[8];
		defaultPeople = FileReader.readPeople();
	}
	
	public void createAndShowGUI() {
		JFrame frame = new JFrame("Таблица с данни за хора");
		frame.setSize(500, 500);
		
		JLabel label = new JLabel("Списък с потребители", JLabel.CENTER);
		
		personDataModel = new PersonDataModel(people);
		table = new JTable(personDataModel);
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		// Добавяме бутон за сортиране по години с Comparable interface
		JButton buttonSortAge = new JButton("Сортирай по години");

		// Добавяме бутон за сортиране
		JButton buttonSort = new JButton("Сортирай");
		
		// TODO Добавяме бутон за филтриране
		JButton buttonFilter = new JButton("Филтрирай");

		// reset button
		JButton buttonReset = new JButton("Възстанови");
		
		// TODO Добавяме панел, където ще поставим бутоните
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(buttonSortAge);
		buttonsPanel.add(buttonSort);
		buttonsPanel.add(buttonFilter);
		buttonsPanel.add(buttonReset);

		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(label, BorderLayout.NORTH);
		container.add(scrollPane, BorderLayout.CENTER);
		// TODO Добавяме панелът с бутоните в контейнера
		container.add(buttonsPanel, BorderLayout.SOUTH);
		
		// Добавяме listener към бутона за сортиране по години
		buttonSortAge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Arrays.sort(people);
				table.repaint();
			}
		});
		getDefaultPeople();
		buttonReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				personDataModel = new PersonDataModel(defaultPeople);

				table.setModel(personDataModel);
				table.repaint();
			}

		});
		
		// TODO Променяме диалога за сортиране
		final JDialog sortDialog = new CustomDialog(getSortText(), this, EType.SORT);
		
		// TODO Добавяме диалог за филтрацията
		final JDialog filterDialog = new CustomDialog(getFilterText(), this, EType.FILTER);
		
		// Добавяме listener към бутона за сортиране
		buttonSort.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sortDialog.pack();
				sortDialog.setVisible(true);
			}
		});
		
		// TODO Добавяме listener за филтрация
		buttonFilter.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				filterDialog.pack();
				filterDialog.setVisible(true);
			}
		});


		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {

				if (me.getClickCount() == 2) {     //Проверка за двойния клик

					int row = table.getSelectedRow();
					StringBuilder information = new StringBuilder();
					int searchedEgn = (int) (table.getValueAt(row,1)); // Взимаме стойността на егн-то от избрания ред

					for (Person person:
						 people) {

						if(person.getEgn() == searchedEgn){ // Проверка дали егн-то съвпада с търсеното

							information.append("Name: " + person.getName() + "\n");
							if(person.getGender() == 'm') {
								information.append("Gender: Male.\n");
							}
							else {
								information.append("Gender: Female.\n");
							}
							information.append("EGN: " + person.getEgn() + "\n");
							information.append("Age: " + person.getAge() + "\n");
							information.append("Height: " + person.getHeight() + "\n");
							information.append("Weight: " + person.getWeight() + "\n");


							if(person instanceof Student){
//
								information.append(String.format("%s is student at %s" +
												" university,\n with faculty Number: %s."
										,person.getName()
										,((Student) person).getUniversity()
										,((Student) person).getFacNumber()));
							}
							else {
								information.append(String.format("%s is %s."
										,person.getName()
										,((Professor)person ).getTitle()));
							}
						}
					}

					JOptionPane.showMessageDialog(null
							, information);
				}
			}
		});
		
		frame.setVisible(true);
	}
	
	// TODO Добавяме метод за филтриране
	public void filterTable(int intValue, JTable table, Person[] people) {

		switch (intValue) {
			case 1: 
				this.personDataModel = new PersonDataModel(filterData(people, Student.class));
				break;
			case 2: 
				this.personDataModel = new PersonDataModel(filterData(people, Professor.class));
				break;
			case 3: 
				this.personDataModel = new PersonDataModel(filterData(people, Person.class));
				break;
		}
		
		table.setModel(this.personDataModel);
		table.repaint();	
	}
	
	// TODO Добавяме помощен метод за филтриране
	public static Person[] filterData(Person[] persons, Class<?> clazz) {
		ArrayList<Person> filteredData = new ArrayList<>();
		
		for (int i = 0; i < persons.length; i++) {
			
			if (clazz == Person.class) {
				// Тук обхващаме филтрирането на "Други"
				if (!(persons[i] instanceof Student) && !(persons[i] instanceof Professor)) {
					filteredData.add(persons[i]);

				}
			} else if (clazz.isInstance(persons[i])) { // Филтриране по студент или професор
				filteredData.add(persons[i]);
			}
		}
		
		// Преобразуваме списъка в масив
		Person[] filteredDataArray = new Person[filteredData.size()];
		filteredDataArray = filteredData.toArray(filteredDataArray);
		return filteredDataArray;
	}

	public void sortTable(int intValue, JTable table, Person[] people) {
		PersonComparator comparator = null;
		
		switch (intValue) {
			case 1: 
				comparator = new NameComparator(); 
				break;
			case 2: 
				comparator = new EgnComparator();
				break;
			case 3:
				comparator = new HeightComparator();
				break;
			case 4: 
				comparator = new WeightComparator();
				break;
			case 5:
				comparator = new AgeComparator();
				break;
		}

		if (comparator == null) { // Ако стойността е null - сортирай по подразбиране
			Arrays.sort(people); // Сортировка по подразбиране по години
		} else {
			Arrays.sort(people, comparator);
		}
		personDataModel = new PersonDataModel(people);
		table.setModel(personDataModel);
		table.repaint();	
	}
	
	private static String getSortText() {
		return "Моля, въведете цифрата на колоната, по която да се сортират данните: \n" +
				" 1 - Име \n" +
				" 2 - ЕГН \n" +
				" 3 - Височина \n" +
				" 4 - Тегло \n" +
				" 5 - Години \n"; 
	}
	
	// TODO Добавяме текст, който да се визуализира в диалога за филтриране
	private static String getFilterText(){
		return "Моля, въведете цифрата на филтъра, който искате да използвате: \n" +
			   " 1 - Студенти \n" +
			   " 2 - Преподаватели \n" + 
			   " 3 - Други \n";
	}
}
