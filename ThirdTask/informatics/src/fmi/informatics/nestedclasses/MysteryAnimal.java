package fmi.informatics.nestedclasses;

public class MysteryAnimal {

    private AnimalName name;
    private String sound = "defaul sound";

    private class Cow{
        public Cow(){
            sound = "moo";

        }
    }

    private class Cat{
        public Cat(){
            sound = "meow";
        }
    }

    private class Dog {
        public Dog(){
            sound = "baw baw";
        }
    }

    public MysteryAnimal(AnimalName name){
        this.name = name;

        switch(name){
            case Bella:
                new Cow();
                break;
            case Chloe:
                new Cat();
                break;
            case Molly:
                new Dog();
                break;
                default:
                    System.out.println("Sorry, we haven't that kind of animal");
                    break;

        }
    }
    public void showSound(){
        System.out.println(this.name.name() + " makes " + this.sound);
    }
}
