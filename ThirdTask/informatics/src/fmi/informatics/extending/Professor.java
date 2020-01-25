package fmi.informatics.extending;

import java.util.Calendar;

public  class Professor extends Person {

	private String title;
	
	public Professor() {
		
	}
	
	public Professor(String title, String name, boolean isMale, 
					 int age, int height, int weight) {
		super(name, isMale, age, height, weight);
		this.title = title;
	}

	public void run(int minutes){

	}
	public void getUpEarly(Calendar hour){

	}
	public void study(){

	}
	public void think(){

	}
	public void act(){

	}

	public void getShowr(){

	}
	
	@Override
	public String toString() {
		return String.format("The professor %s is with title %s", 
							 this.getName(), this.getTitle());
	}

	// Getters and setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}