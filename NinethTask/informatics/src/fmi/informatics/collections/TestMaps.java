package fmi.informatics.collections;

import java.util.Map;
import java.util.HashMap;

public class TestMaps {

	public static void main(String[] args) {
		testMaps();
	}
	
	public static void testMaps() {
		// Създаваме HashMap, добавяме и премахваме елементи
		Map<String, Integer> personYearBorn = new HashMap<>();
		personYearBorn.put("Ivan", 1888);
		personYearBorn.put("Petkan", 3002);
		personYearBorn.remove("Petkan");
		
		System.out.println("Ivan is born in " + personYearBorn.get("Ivan"));
		
		personYearBorn.put("Ivan", 1889);
		System.out.println("Ivan is born in " + personYearBorn.get("Ivan"));
		
		System.out.println("Available people inside: " + personYearBorn.size());
		
		personYearBorn.put("Asparuh", 640);
		
		System.out.println("Is Petkan here: " + personYearBorn.containsKey("Petkan"));
		System.out.println("Is 1889 year here: " + personYearBorn.containsValue(1889));
		
		System.out.println("All keys: " + personYearBorn.keySet());
		System.out.println("All values: " + personYearBorn.values() + "\n");
		
		System.out.println("Iterating keys:");
		for (String key : personYearBorn.keySet()) {
			System.out.println(key);
		}
				
		System.out.println("Iterating entries:");
		for (Map.Entry<String, Integer> entry : personYearBorn.entrySet()) {
			System.out.println("Person " + entry.getKey() + " is born in " + entry.getValue());
		}
	}
}
