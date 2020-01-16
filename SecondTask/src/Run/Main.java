package Run;

import Repositories.Eggs;
import Model.Egg;
public class Main {
    public static void main(String[] args) {
        Eggs eggs = new Eggs();

        Egg eggOne = new Egg(123);
        eggOne.boil(4,"jazz");
        eggOne.washAfterBoil();
        System.out.println("---------------------------");
        Egg eggTwo = new Egg(321);
        eggTwo.washBeforeBoil();
        eggTwo.boil(6,"tray");
        System.out.println("---------------------------");
        Egg eggThree = new Egg(432);
        eggThree.boil(3,"pot");

        eggs.addEgg(eggOne);
        eggs.addEgg(eggTwo);
        eggs.addEgg(eggThree);

        eggs.printEggsInfo();

        eggs.removeEgg(321);
        System.out.println("=============================");

        eggs.printEggsInfo();

    }
}
