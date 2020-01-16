package Repositories;

import Model.Egg;

import java.util.ArrayList;
import java.util.List;

public class Eggs {

    private ArrayList<Egg> eggs;

    public Eggs(){
        this.eggs = new ArrayList<>();
    }

    public void addEgg(Egg egg){
        this.eggs.add(egg);
    }

    public void removeEgg(int id) {
        Egg toRemove = eggs.stream()
                .filter(x -> id == x.getEggId())
                .findAny()
                .orElse(null);

        if (toRemove != null) {
            eggs.remove(toRemove);
        } else {
            System.out.println("Invalid egg id!");
        }
    }
        public void printEggsInfo(){
            for (Egg egg:
                 eggs) {
                egg.printEggInfo();
            }

        }
    }



