package fmi.informatics.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

import fmi.informatics.comparators.AgeComparator;
import fmi.informatics.comparators.EgnComparator;
import fmi.informatics.comparators.HeightComparator;
import fmi.informatics.comparators.NameComparator;
import fmi.informatics.comparators.PersonComparator;
import fmi.informatics.comparators.WeightComparator;
import fmi.informatics.enums.EType;
import fmi.informatics.extending.Person;
import fmi.informatics.extending.Student;
import fmi.informatics.util.FileReader;

// създаваме клас PersonDataGUI
public class PersonDataGUI {
	
	public static Student[] students;
	JTable table;
	PersonDataModel personDataModel;

	public static void main(String[] args) {
		getPeople();
		
		// Извикваме писането/четенето във файл
		initializeData();
		
		PersonDataGUI gui = new PersonDataGUI();
		gui.createAndShowGUI();
	}
	
	// Добавяме писането/четенето във файл
	private static void initializeData() {
		if (!FileReader.isFileExists()) {
			FileReader.createPersonFile();
		}
		
		FileReader.writePeople(students);
	}
	
	public static void getPeople() {
		students = new Student[8];
		
		for (int i = 0; i < 8; i++) {
			Student student = Student.StudentGenerator.make();
			students[i] = student;
		}
		
		//for (int i = 4; i < 8; i++) {
		//	Person professor = Professor.ProfessorGenerator.make();
		//	people[i] = professor;
		//}

	}
	
	public void createAndShowGUI() {
		JFrame frame = new JFrame("Таблица с данни за хора");
		frame.setSize(600, 500);
		
		JLabel label = new JLabel("Списък с потребители", JLabel.CENTER);
		
		personDataModel = new PersonDataModel(students);
		table = new JTable(personDataModel);
		
		JScrollPane scrollPane = new JScrollPane(table);

		JButton buttonShowInformation = new JButton("Покажи информация!");
		JButton buttonSortAscending = new JButton("Сортирай възходящо!");
		JButton buttonSortDescending = new JButton("Сортирай низходящо!");


		// Добавяме бутон за сортиране по години с Comparable interface
		JButton buttonSortAge = new JButton("Сортирай по години");

		// Добавяме бутон за сортиране
		JButton buttonSort = new JButton("Сортирай");
		
		// Добавяме бутон за филтриране
		JButton buttonFilter = new JButton("Филтрирай");
		
		// Добавяме панел, където ще поставим бутоните
		JPanel buttonsPanel = new JPanel();
		//buttonsPanel.add(buttonSortAge);
		buttonsPanel.add(buttonSortDescending);
		buttonsPanel.add(buttonSortAscending);
		buttonsPanel.add(buttonShowInformation);

		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(label, BorderLayout.NORTH);
		container.add(scrollPane, BorderLayout.CENTER);
		// Добавяме панелът с бутоните в контейнера
		container.add(buttonsPanel, BorderLayout.SOUTH);
		
		// Добавяме listener към бутона за сортиране по години
		buttonSortAge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Arrays.sort(students);
				table.repaint();
			}
		});
		
		// Добавяме диалог
		final JDialog sortDialog = new CustomDialog(getSortText(), this, EType.SORT);
		
		// Добавяме диалог за филтрацията
		final JDialog filterDialog = new CustomDialog(getFilterText(), this, EType.FILTER);

		buttonSortAscending.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {


				List<Student> sortedStudents = Arrays.asList(students);
				sortedStudents.sort((s1,s2)->s1.getSpeciality().compareTo(s2.getSpeciality()));

				Student[] sorted = sortedStudents.toArray(new Student[8]);
				personDataModel  = new PersonDataModel(sorted);
				table.setModel(personDataModel);
				table.repaint();
			}
		});
		buttonSortDescending.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {


				List<Student> sortedStudents = Arrays.asList(students);
				sortedStudents.sort((s1,s2)->s2.getSpeciality().compareTo(s1.getSpeciality()));

				Student[] sorted = sortedStudents.toArray(new Student[8]);
				personDataModel  = new PersonDataModel(sorted);
				table.setModel(personDataModel);
				table.repaint();
			}
		});

		// Добавяме listener към бутона за сортиране
		buttonSort.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sortDialog.pack();
				sortDialog.setVisible(true);
			}
		});

		buttonShowInformation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb = new StringBuilder();

				Arrays.stream(students)
						.filter(x-> x instanceof Student)
						.forEach(student -> sb.append(String
								.format("Name:%s \nAge:%d \nUniversity: %s \nFaculty Num: %s \n--------- \n"
										,student.getName(),student.getAge(),((Student) student).getUniversity()
								,((Student) student).getFacNumber())));
				JOptionPane.showMessageDialog(null,sb);
			}
		});


		
		// Добавяме listener за филтрация
		buttonFilter.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				filterDialog.pack();
				filterDialog.setVisible(true);
			}
		});
		
		frame.setVisible(true);
	}
	
	// Добавяме метод за филтриране
//	public void filterTable(int intValue, JTable table, Person[] people) {
//
//		switch (intValue) {
//			case 1:
//				this.personDataModel = new PersonDataModel(filterData(students));
//				break;
//			case 2:
//				this.personDataModel = new PersonDataModel(filterData(students, Professor.class));
//				break;
//			case 3:
//				this.personDataModel = new PersonDataModel(filterData(students, Person.class));
//				break;
//		}
		
//		table.setModel(this.personDataModel);
//		table.repaint();
//	}
	
	private Person[] filterData(Student[] persons, Class<?> clazz) {
		ArrayList<Person> filteredData = new ArrayList<>();
		
		for (int i = 0; i < persons.length; i++) {
			
			if (clazz == Person.class) {
				// Тук обхващаме филтрирането на "Други"
//				if (!(persons[i] instanceof Student) && !(persons[i] instanceof Professor)) {
//					filteredData.add(persons[i]);
//				}
//			}  if (clazz.isInstance(persons[i])) { // Филтриране по студент или професор
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
		
		table.repaint();	
	}
	
	private static String getSortText() {
		return "Моля, въведете цифрата на колоната, по която да се сортират данните: \n" +
				" 1 - Име \n" +
				" 2 - ЕГН \n" +
				" 3 - Височина \n" +
				" 4 - Тегло \n" +
				" 5 - Години \n"+
				" 6 - Специалност \n";
	}
	
	// Добавяме текст, който да се визуализира в диалога за филтриране
	private static String getFilterText(){
		return "Моля, въведете цифрата на филтъра, който искате да използвате: \n" +
			   " 1 - Студенти \n" +
			   " 2 - Преподаватели \n" + 
			   " 3 - Други \n";
	}
}
