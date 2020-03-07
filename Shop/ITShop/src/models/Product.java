package models;

public class Product {
    private String name, img_path;
    private double price;
    private int qantity;

    public int getQantity() {
        return qantity;
    }

    public void setQantity(int qantity) {
        this.qantity = qantity;
    }

    public Product(String name, double price, String img_path) {
        this.name = name;
        this.img_path = img_path;
        this.price = price;
        this.qantity = 0;
    }

    public String getName() {
        return name;
    }

    public String getImg_path() {
        return img_path;
    }

    public double getPrice() {
        return price;
    }
}
