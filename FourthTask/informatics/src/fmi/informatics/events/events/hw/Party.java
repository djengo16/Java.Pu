package fmi.informatics.events.events.hw;

import java.util.ArrayList;
import java.util.List;

public class Party implements Celebratable {

    private List<Teenager> people;

    public Party(){
        this.people = new ArrayList<>();
    }

    public void addPerson(Teenager teenager){
        teenager.enterParty();
        this.people.add(teenager);


        teenager.greet();
        greetBack(teenager);
    }

    public int getSIze(){
        return this.people.size();
    }

    public void greetBack(Celebrator celebrator){
        for (Teenager c:
             people) {
            if(!c.equals(celebrator)){
                System.out.println(String.format("Hi!(%s)",c.getName()));
            }
        }
    }

}
