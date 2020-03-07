package models;

import shop.Shop;

import java.util.ArrayList;
import java.util.List;

public class SubCategory {
    private String name;
    public List<Product> products;

    public SubCategory(String name) {
        this.name = name;
        products = new ArrayList<>();
    }

    public String getName() { return name; }


}
