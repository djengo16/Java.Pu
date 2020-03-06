package fmi.informatics.functional;

import java.util.Arrays;
import java.util.List;

import fmi.informatics.extending.Student;

public class TestLambdas {

	public static void main(String[] args) {
		testMySimpleInterface();
		testMySimpleInterface2();
		sortingWithLambdas();
	}
	
	// Първи пример за ламбда
	public static void testMySimpleInterface() {
		// Присвояваме ламбда на представител от този интерфейс и изпълняваме метода helloWorld()
		MySimpleInterface ms = () -> System.out.println("Hello World!");
		ms.helloWorld();
		
		MySimpleInterface ms1 = new MySimpleInterface() {
			@Override
			public void helloWorld() {
				System.out.println("Hello World!");
			}
		};
		ms1.helloWorld();
		
		// Извикваме testFunctional с ламбда като параметър
		// Къдравите скоби са наложителни, за код който не може да се напише като един израз
		testFunctional(() -> {
			System.out.println("Testing 123");
			System.out.println("Testing 456");
		});
	}
	
	// Създаваме си метод, който приема като параметър функционалния интерфейс
	public static void testFunctional(MySimpleInterface msi) {
		msi.helloWorld();
	}
	
	// Втори пример за ламбда
	public static void testMySimpleInterface2() {
		// не е необходимо описване на типа на параметрите или върнатата стойност
		// тази информация се съдържа в дефиницията на метода в интерфейса
		testFunctional2((p1, p2) -> p1 + p2);
	}
	
	public static void testFunctional2(MySimpleInterface2 msi2) {
		String result = msi2.concatenate("Hello ", "world");
		System.out.println(result);
	}
	
	// Пример за сортиране на студенти по име с ламбда компаратор
	public static void sortingWithLambdas() {
		List<Student> randomStudents = 
			Arrays.asList(
				Student.StudentGenerator.make(),
				Student.StudentGenerator.make(),
				Student.StudentGenerator.make()
			);
		
		randomStudents.sort((a, b) -> {
			return a.getName().compareTo(b.getName());
		});
		
		System.out.println(randomStudents);
		randomStudents.forEach((s) -> System.out.println(s));
		randomStudents.forEach(System.out::println);
	}
}
