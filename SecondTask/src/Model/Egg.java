package Model;
import Interfaces.Boilable;
import Interfaces.Washable;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.ArrayList;
import java.util.List;

public class Egg implements Boilable, Washable {


    private int eggId;
    private int boilingTime;
    private String boilingPan;
    private boolean isWashedBeforeBoiling;
    private boolean isWashedAfterBoiling;
    private String eggCondition;


    public Egg(int eggId){
        this.eggId = eggId;
        this.isWashedAfterBoiling = false;
        this.isWashedBeforeBoiling = false;
    }

    private List<String> allowedPans = List.of("jazz","jug","pot");

    public void boil(int minutesOfBoiling,String boilingPan){

        this.boilingTime = minutesOfBoiling;
        System.out.println(String.format("Egg %d boiled for %d minutes!",getEggId(),minutesOfBoiling));

        setBoilingPan(boilingPan);

        if (this.boilingPan != null) {
            if (minutesOfBoiling > 0) {
                if ((minutesOfBoiling <= 3)) {
                    this.eggCondition = "soft-boiled";
                } else if (minutesOfBoiling <= 5) {
                    this.eggCondition = "normal-boiled";
                } else {
                    this.eggCondition = "hard-boiled";
                }
            }
            else {
                throw new InvalidDnDOperationException("Can't boil for 0 or negative minutes!");
            }
            }
        }


    public void washBeforeBoil(){
        this.isWashedBeforeBoiling = true;
        System.out.println(String.format("Egg %d is washed with cold water before the boiling!",getEggId()));
    }
    public void washAfterBoil(){
        if (eggCondition != null) {
            this.isWashedAfterBoiling = true;
            System.out.println(String.format("Egg %d is washed with cold water after the boiling!", getEggId()));
        }
    }

    protected void setBoilingPan(String boilingPan) {
        if (allowedPans.contains(boilingPan)) {
            this.boilingPan = boilingPan;
            System.out.println(String.format("Egg %d boiled in %s !",getEggId(),boilingPan));
        }
        else {
            System.out.println("Invalid pan type!");
        }
    }

    public void printEggInfo(){
        StringBuilder information = new StringBuilder();

        information.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        information.append("\n");
        if (this.eggCondition != null) {
            information.append(String.format("Egg with id %d boiled for %d minutes with %s"
                    , getEggId(), getBoilingTime(), getBoilingPan()));
        }
        else{
            information.append(String.format("Egg with id %d is not boiled",getEggId()));
        }
        information.append(System.getProperty("line.separator"));
        if(this.isWashedBeforeBoiling == true){
            information.append("It's washed before the boiling!");
            information.append(System.getProperty("line.separator"));
        }
        if (this.isWashedAfterBoiling){
            information.append("It's washed with cold water after the boiling!");
            information.append(System.getProperty("line.separator"));
        }
        if (this.eggCondition != null) {
            information.append(String.format("The egg is %s!", getEggCondition()));
        }
        information.append(System.getProperty("line.separator"));

        System.out.println(information);

    }

    public int getEggId() {
        return eggId;
    }

    private void setEggId(int eggId) {

        this.eggId = eggId;
    }

    public String getBoilingPan() {
        return boilingPan;
    }


    public boolean isWashedBeforeBoiling() {
        return isWashedBeforeBoiling;
    }

    public boolean isWashedAfterBoiling() {
        return isWashedAfterBoiling;
    }

    public int getBoilingTime() {
        return boilingTime;
    }

    public String getEggCondition() {
        return eggCondition;
    }
}
