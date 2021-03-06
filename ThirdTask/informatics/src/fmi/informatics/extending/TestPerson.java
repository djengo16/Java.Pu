package fmi.informatics.extending;

import java.util.ArrayList;
import java.util.Calendar;

public class TestPerson {
	
	  public static void main(String[] args) {
		 // Person p = new Person();
		 // p.walk();
		 // System.out.println("Person name: " + p.getName() + "\n");

		  Student s = new Student("Pesho", 838383, "PU", "Informatics", 12365);
		  Calendar calendar = Calendar.getInstance();
		  calendar.set(2020,1,1,8,0);
		  s.getUpEarly(calendar);
		  
		  s.run();
		  s.goBar("Rakia");
		  System.out.println("Student name: " + s.getName());
		  System.out.println("Student EGN: " + s.getEgn());
		  System.out.println("Overrided toString method: " + s.toString());
		  System.out.println();
		  
		  Professor prof = new Professor();
		  prof.setTitle("Assistant");
		  prof.walk();
		  System.out.println(prof.toString());
		  System.out.println();
		  
		  printPerson();
	  }

	public static void printPerson() {	
		ArrayList<Person> persons = new ArrayList<>();
		
		for (int i = 0; i <= 10; i++) {
			if (i % 2 == 0) {
				persons.add(new Student());
			} else {
				persons.add(new Professor());
			}
		}

		// При отпечатването на конзолата, въпреки че работим с обекти от тип
		// Person, ще се извика методът toString на съответния клас наследник
		// Student или Professor (това е т.нар. полиморфизъм - различно поведение на обект)
		System.out.println("Show all elements from the list:");
		for (Person p : persons) {
			System.out.println(p.toString());
		}
	}
}