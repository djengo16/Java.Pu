package fmi.informatics.repositories;

import fmi.informatics.composition.RichStudent2;
import fmi.informatics.extending.Person;
import fmi.informatics.extending.Professor;
import fmi.informatics.extending.Student;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class PersonRepo {
    
    private ArrayList<Person> people;
    
    public PersonRepo(){
        this.people = new ArrayList<>();
    }

    public int getPeopleCount(){
        return this.people.size();
    }

    public int getStudentCount(){
        return people.stream()
                .filter(x->x.getClass().getSimpleName().equals("Student"))
                .map(x->(Student) x)
                .collect(Collectors.toList())
                .size();
    }

    public int getRichStudentCount(){
        return people.stream()
                .filter(x->x instanceof RichStudent2)
                .map(x->(RichStudent2) x)
                .collect(Collectors.toList())
                .size();
    }

    public int getProfessorCount(){
        return people.stream()
                .filter(x->x instanceof Professor)
                .map(x->(Professor) x)
                .collect(Collectors.toList())
                .size();
    }

    public void addPerson(Person person){
        this.people.add(person);
    }

    public void removePerson(Person personToRemove){
        this.people.remove(personToRemove);
    }

    public void callStudyToAllInstances(){
        for (Person person:
             this.people) {
            if (person instanceof Student || person instanceof RichStudent2){
                person.study();
            }
        }
    }

}
