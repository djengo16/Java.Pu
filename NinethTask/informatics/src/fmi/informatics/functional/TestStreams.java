package fmi.informatics.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import fmi.informatics.extending.Student;

public class TestStreams {

	public static void main(String[] args) {
		testDefaultInterface();
		testStreams();
		functionalTask();
		task();
		moreStreams();
	}
	
	public static void testDefaultInterface() {
		Rock onEarth = new Rock();
		onEarth.drop(1000);
	}
	
	public static void testStreams() {
		// Създаваме прост стрийм от букви, който итерираме чрез метода forEach()
		// един от начините за създаване на стрийм - от масив
		List<Character> letters = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h');
		Stream<Character> letterStream = letters.stream();
		letterStream.forEach(l -> System.out.println(l));
		
		// Създаване на стриймове
		Stream<Character> charStream = Stream.of('a', 'b', 'c');
		Stream<String> stringStream = Stream.of("hello", "my", "friend");
		Stream<Integer> intStream = Stream.of(1, 2, 3);
		
		IntStream intStream2 = IntStream.range(0, 4); // специален примитивен stream
		DoubleStream doubleStream = DoubleStream.of(1.1, 2.2, 3.3); // специален примитивен stream
		LongStream longStream = LongStream.range(0, 4); // специален примитивен stream
		
		String sentence = "Hello World";
		IntStream charStream2 = sentence.chars(); // оптимизиран чрез примитивен stream
		
		System.out.println("=================================================");
		
		// връща елементите, които в себе си имат буквата "е"
		stringStream.filter(s -> s.contains("e")).forEach(System.out::println);
		
		// умножава всеки елемент по себе си
		intStream.map(num -> num * num).forEach(System.out::println);
		
		// преобразува отделните символи до стрингове като например /a/ /b/ /c/
		charStream.map(l -> "/" + l + "/").forEach(System.out::println);
		
		 // преобразува int stream към Integer stream
		intStream2.mapToObj(Integer::new).forEach(System.out::println);
		
		// forEach се ползва като dummy терминираща операция
		Stream.of("one", "two", "three").peek(System.out::println).forEach(c -> {}); 
		
		Stream.of(Student.StudentGenerator.make(), Student.StudentGenerator.make())
			.flatMap(s -> Stream.of(s.getName(), s.getSpeciality(), s.getUniversity()))
			.forEach(System.out::println);
		
		// сортира в натурален ред
		Stream.of("B", "A", "C").sorted().forEach(System.out::println);
		
		// обърнато сортиране в натурален ред
		Stream.of("B", "A", "C").sorted(Comparator.reverseOrder()).forEach(System.out::println);
		
		// обърнато сортиране в натурален ред със собствен компаратор
		Stream.of("B", "A", "C").sorted((a, b) -> -a.compareTo(b)).forEach(System.out::println);
		
		// използваме готови колектори
		List<String> collected = Stream.of("B", "A", "C").collect(Collectors.toList());
		System.out.println(collected);
		
		// Връща String от елементите в Stream
		String merged = Stream.of("B", "A", "C").collect(Collectors.joining());
		System.out.println(merged);
		
		boolean match = Stream.of("or", "not", "to").anyMatch(s -> s.equals("or"));
		System.out.println(match);
		
		long wordsCount = Stream.of("or", "not", "to").count();
		System.out.println(wordsCount);
		
		String found = Stream.of("or", "not", "to").findFirst().orElse("nothing found");
		System.out.println(found);
		
		String concat = Stream.of("be", "or", "not").reduce((a, b) -> a + " " + b).get();
		System.out.println(concat);
		
		Stream.of("cat", "dog", "cow", "bird")
	      .map(String::toUpperCase)
	      .collect(Collectors.toList())
	      .forEach(System.out::println);
	}
	
	// Решете задачата чрез императивен подход
	public static void functionalTask() {
		// функционален подход
		Stream.of(Student.StudentGenerator.make(), 
				Student.StudentGenerator.make(),
				Student.StudentGenerator.make())
			.filter(s -> s.getName().length() >= 5)
			.sorted((s1, s2) -> s1.getName().compareTo(s2.getName()))
			.forEach(System.out::println);
	}
	
	public static void task() {
		List<Student> students = new ArrayList<>();
		
		for (int i = 0; i < 5; ++i) {
			students.add(Student.StudentGenerator.make());
		}
		
		String studentsNames = students.stream()
			.sorted((s1, s2) -> s1.getName().compareTo(s2.getName()))
			.map(s -> s.getName())
			.reduce((s1, s2) -> { 
				if (s2 != null) return s1 + ", " + s2;
				return s1;
			})
			.get();
		System.out.println(studentsNames);
	}
	
	public static void moreStreams() {
		IntStream.iterate(0, i -> i + 1)
		.skip(5)
		.limit(10)
		.forEach(i -> {
			if (i%2 == 0) System.out.println(i); else System.err.println(i);
		});	
	}
}
