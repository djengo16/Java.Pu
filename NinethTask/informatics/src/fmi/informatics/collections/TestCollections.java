package fmi.informatics.collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TestCollections {

	public static void main(String[] args) {
		testListInterface();
		testSetInterface();
		testArraySort();
	}
	
	// Пример за ArrayList
	public static void testListInterface() {
		List<String> foodList = new ArrayList<>();
		
		if (foodList.isEmpty()) {
			System.out.println("Списъкът е празен!");
		}
		
		foodList.add("Pizza");
		
		// Възможно е добавянето на дублирани записи
		String pizza = new String("Pizza");
		foodList.add(pizza);
		foodList.add(pizza);
		
		System.out.println("--- След добавянето на всички пици ---");
		System.out.println(foodList);
		
		// Възможно е добавянето на елемент на определена позиция
		foodList.add(2, "Salad");
		foodList.add("Spagetti");
		
		System.out.println("--- След добавянето на елемент на конкретна позиция ---");
		System.out.println(foodList);
		
		System.out.println("--- След премахването на първия елемент ---");
		foodList.remove(0);
		System.out.println(foodList);
		
		if (!foodList.contains(pizza)) {
			System.out.println("Няма пици в списъка с храна");
		}
		
		ArrayList<String> newList = new ArrayList<>();
		newList.add(new String("Apple"));
		newList.add(new String("Pear"));
		
		System.out.println("--- След добавяне на цяла колекция ---");
		foodList.addAll(newList);
		System.out.println(foodList);
	}
	
	// Пример за HashSet
	public static void testSetInterface() {
		Set<String> foodSet = new HashSet<>();
		foodSet.add("Pizza");
		foodSet.add("Spagetti");
		foodSet.add("Soup");
		foodSet.add("Brocolli");
		foodSet.add("Apple");
		System.out.println("Set: " + foodSet);
		
		Set<String> treeSet = new TreeSet<>();
		treeSet.addAll(foodSet);
		System.out.println("TreeSet: " + treeSet);
	}
	
	// Пример за сортиране на колекция
	public static void testArraySort() {
		Integer i = 5;
		int j = i;
		i = j + 1;
		j = i.intValue(); // ръчно unbox-ване: от Integer -> int
		i = new Integer(7); // ръчно box-ване: от int -> Integer
		
		List<Integer> integers = new ArrayList<>();
		integers.add(1);
		integers.add(500);
		integers.add(70);
		
		integers.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				if (a < b) {
					return -1;
				}
		
				return (a > b ? 1 : 0);
			}
		});
		
		System.out.println("Сортиран списък: " + integers);
	}
}
