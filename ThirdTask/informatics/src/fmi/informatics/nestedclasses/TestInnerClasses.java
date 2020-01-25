package fmi.informatics.nestedclasses;

public class TestInnerClasses {
    public static void main(String[] args) {
        MysteryAnimal mysteryAnimal =
                new MysteryAnimal(AnimalName.Bella);
        mysteryAnimal.showSound();

        MysteryAnimal mysteryAnimal1 =
                new MysteryAnimal(AnimalName.Chloe);
        mysteryAnimal1.showSound();

        MysteryAnimal mysteryAnimal2 =
                new MysteryAnimal(AnimalName.Betty);
        mysteryAnimal2.showSound();

    }
}
