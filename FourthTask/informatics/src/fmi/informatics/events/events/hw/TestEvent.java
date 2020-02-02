package fmi.informatics.events.events.hw;

public class TestEvent  {
    public static void main(String[] args) {
        Teenager teen1 = new Teenager("Ivan");
        Teenager teen2 = new Teenager("Stefan");
        Teenager teen3 = new Teenager("Svetla");

        Party party = new Party();

        party.addPerson(teen1);
        party.addPerson(teen2);
        party.addPerson(teen3);

    }
}
