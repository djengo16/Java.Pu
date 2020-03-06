package fmi.informatics.generics;

import java.util.ArrayList;

import fmi.informatics.composition.RichStudent;
import fmi.informatics.extending.Person;
import fmi.informatics.extending.Professor;
import fmi.informatics.extending.Student;

public class TestMyArray {

	public static void main(String[] args) {
		testMyDumbArray();
		testGenericArray();
		testGenerics();
	}
	
	public static void testMyDumbArray() {
		MyDumbArrayList list = new MyDumbArrayList();
		
		list.add(new Student());
		list.add(new Professor());
		list.add(new RichStudent("name", 1234, "university", "speciality", 111111));
		
		Professor p = (Professor) list.remove(1);
		p.goBar();
		
		Student s = (Student) list.get(0);
		s.goBar();
	}
	
	public static void testGenericArray() {
		MyGenericDumbArrayList<?> list = new MyGenericDumbArrayList<>();
		
		list.add(new Student());
		list.add(new Professor());
		
		// Грешка: Не можем да добавим обект, който не наследява Person класа
		// list.add(new RichStudent("name", 1234, "university", "speciality", 111111));
	}
	
	public static void testGenerics() {
		MyGenericDumbArrayList<Student> list = new MyGenericDumbArrayList<>();
		
		// RichStudent не е наследник на Person
		// MyGenericDumbArrayList<RichStudent> listP = new MyGenericDumbArrayList<>();
		
		MyGenericDumbArrayList.fromList(new ArrayList<Person>());
		MyGenericDumbArrayList.fromList(new ArrayList<Object>());
		
		// Student не е супер клас на Person
		// MyGenericDumbArrayList.fromList(new ArrayList<Student>());
	}
}
