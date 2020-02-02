package fmi.informatics.events;

import java.util.ArrayList;
import java.util.Iterator;

public class TestEvents {
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		TV tv = new TV();
		User user = new User("Pesho", tv);
		
		tv.addObserver(user);
		// Не е позволено да премахваме елементи от списък, докато ги обхождаме
		// tv.setInStock(true); // not allowed
		
		// Показваме защо не е позволено да премахваме елементи от списък, докато ги обхождаме
		// Ако искаме да обхождаме колекция и да премахваме елементи имаме 2 варианта:
		// 1. Да използваме java.util.Iterator
		// 2. Да започнем обхождането на списъка отзад напред
		ArrayList<String> all = new ArrayList<>();
		all.add("aaa");
		all.add("bbb");
		all.add("ccc");
		
		for (String s : all) {
			System.out.println(s);

			if (s.equals("aaa")) {
				// all.remove(s); // not allowed
            } 
		}
		
		// Използване на java.util.Iterator
        Iterator iterator = all.iterator(); 
        while (iterator.hasNext()) {
            String currentValue = (String) iterator.next();
            
            if (currentValue.equals("aaa")) {
            	iterator.remove();
            } 
        } 
  
        System.out.println("Modified ArrayList: " + all);
	}
}