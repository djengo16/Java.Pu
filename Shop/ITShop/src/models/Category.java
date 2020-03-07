package models;

import shop.Shop;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    public List<SubCategory> subCategories;

    public Category(String name) {
        this.name = name;
        subCategories = new ArrayList<>();
    }

    public String getName() { return name; }


}
