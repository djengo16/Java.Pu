package fmi.informatics.events.events.hw;

import fmi.informatics.events.Observable;
import fmi.informatics.events.Observer;

public class Teenager implements Celebrator {

    private String name;


    public Teenager(String name){
        this.name = name;
    }



    public void enterParty(){
        System.out.println(String.format("%s entered the party",this.name));
    }

    public void greet(){
        System.out.println(String.format("%s: Hi everybody!",this.name));

    }

    public String getName() {
        return name;
    }
}
