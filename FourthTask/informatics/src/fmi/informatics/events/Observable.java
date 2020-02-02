package fmi.informatics.events;

// създаваме интерфейс Observable (наблюдаван)
public interface Observable {
	
	public void addObserver(Observer o);

	public void removeObserver(Observer o);

	public void notifyObserver();
}
