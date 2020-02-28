package fmi.informatics.generics;

import java.util.Iterator;
import java.util.List;

import fmi.informatics.extending.Person;
import fmi.informatics.interfaces.Active;

// Пример за реализация на собствен генеричен ArrayList
public class MyGenericDumbArrayList<T extends Person & Active> {
	
	private Object[] data = new Object[10];
	private int size = 0;
	
	public MyGenericDumbArrayList() {
		
	}
	
	private void changeCapacity(int toCapacity, int skipCellNumber) {
		Object[] resizedData = data;
		
		if (toCapacity > 1) {
			resizedData = new Object[toCapacity];
		}
		
		int newSize = 0;
		
		for (int i = 0; i < data.length && newSize < toCapacity; i++) {
			if (i != skipCellNumber) {
				resizedData[newSize++] = data[i];
			}
		}
		
		this.data = resizedData;
	}
	
	public void add(Person person) {
		if (size == data.length) {
			changeCapacity(data.length * 2, -1);
		}
		
		this.data[size++] = person;
	}
	
	@SuppressWarnings("unchecked")
	public T get(int position) {
		if (position > size-1 || position < 0) {
			throw new IndexOutOfBoundsException("Няма такъв елемент");
		}
		
		return ((T) this.data[position]);
	}
	
	public T remove(int position) {
		if (size == 0) {
			throw new IndexOutOfBoundsException();
		}
		
		T o = get(position);
		this.data[position] = null;
		changeCapacity(--size, position);
		
		return o;
	}
	
	// преобразува List в MyGenericDumbArrayList
	public static MyGenericDumbArrayList<?> fromList(List<? super Person> list) {
		MyGenericDumbArrayList<?> result = new MyGenericDumbArrayList<>();
		
		if (list != null) {
			for (Iterator<? super Person> it = list.iterator(); it.hasNext();) {
				// кастваме заради ограничението T extends Person & Active
				result.add((Person) it.next());
			}
		}
		
		return result;
	}
}
