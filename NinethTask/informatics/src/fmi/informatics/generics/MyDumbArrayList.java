package fmi.informatics.generics;

/* 
 * Нека си представим, че класа ArrayList не съществува и ние искаме да създадем свой собствен. 
 * Използвайки текущите си познания за това, че всички обекти в Java наследяват класа Object
 * е логично да обмислим използването на такъв подход, но дали е достатъчно удобен?
*/

// Пример за реализация на собствен ArrayList по негенеричен начин
public class MyDumbArrayList {
	
	Object[] data = new Object[16];
	int size = 0;
	
	public MyDumbArrayList() {
		
	}
	
	private void changeCapacity(int toCapacity, int skipCellNumber) {
		Object[] resizedData = data;
		
		if (toCapacity > 1) {
			resizedData = new Object[toCapacity];
		}
	
		int newSize = 0;
		
		for (int i = 0; i < data.length && newSize < toCapacity; ++i) {
			if (i != skipCellNumber) {
				resizedData[newSize++] = data[i];
			}
		}
		
		this.data = resizedData;
	}
	
	public void add(Object o) {
		if (size == data.length) {
			changeCapacity(data.length * 2, -1); 
		}
		
		this.data[size++] = o;
	}

	public Object get(int position) {
		if (position > size - 1 || position < 0) {
			throw new IndexOutOfBoundsException("Няма такъв елемент");
		}
		
		return this.data[position];
	}

	public Object remove(int position) {
		
		if (size == 0) {
			throw new IndexOutOfBoundsException("Няма такъв елемент");
		}
		
		Object o = get(position);
		this.data[position] = null;
		changeCapacity(--size, position);
		return o;
	}

	public int size() {
		return this.size;
	}
}
