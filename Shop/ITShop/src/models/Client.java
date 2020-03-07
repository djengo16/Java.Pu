package models;

import java.util.*;

public class Client  {


private ArrayList<Product> basket;

public Client(){
    this.basket = new ArrayList<>();
}

public void addProduct(Product product){
    if (!this.basket.contains(product)){
       this.basket.add(product);
    }
    for (Product p:basket){
        if (p.getName().equals(product.getName())){
            p.setQantity(p.getQantity()+1);
        }
    }
}

public ArrayList<Product> getProducts(){
    return this.basket;
}

}
