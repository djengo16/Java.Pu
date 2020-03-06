package fmi.informatics.functional;

// Създаваме собствен функционален интерфейс
// Анотацията може да се пропусне, 
// защото компилаторът зачита всеки интерфейс с 1 абстрактен метод за функционален интерфейс
@FunctionalInterface
public interface MySimpleInterface {
	void helloWorld();
}
