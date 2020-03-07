
package util;

import models.Product;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;



// Четене и писане във файл
public class FileReader {

    private static final String PERSON_FILE_EXTENSION = ".file";
    private static final String PERSON_FILE_NAME = "products";
    private static final String FILE_PATH = "D:/Java/Java.Projects.Homeworks/Java.Pu/Shop/ITShop/src/files/";
    private static final String FULL_PATH = FILE_PATH + PERSON_FILE_NAME + PERSON_FILE_EXTENSION;

    private static ArrayList<Product> products = new ArrayList<>();

    public static void createPersonFile() {
        File file = new File(FULL_PATH);
        file.getParentFile().mkdirs();

        try {
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("Файлът не може да бъде създаден!");
            e.printStackTrace();
        }
    }

    public static boolean isFileExists() {
        File file = new File(FULL_PATH);
        return file.exists();
    }

    @SuppressWarnings("resource")




    public static void writePeople(Product[] products,double totalPrice) {
        Writer writer = null;
        if(products.length == 0){
            JOptionPane.showMessageDialog(null,"The basket is empty!");
            return;
        }

        try {
            writer = new BufferedWriter(new FileWriter(FULL_PATH, true)); // enable append
            writer.append("-------------Invoice---------------\n");
            for (Product product : products) {
                writer.append("Product name: ");
                writer.append(product.getName() + "\t\n");
                writer.append("| Unit price: ");
                writer.append(product.getPrice() + "\t\n");
                writer.append("| Quantity: ");
                writer.append(product.getQantity() + "\t\n");
            }
            writer.append("Total price: " +totalPrice + "\t");
            writer.append("\n------------------------------------------------");
            JOptionPane.showMessageDialog(null,"\n" +
                    "You have successfully purchased the products.");
        } catch (IOException e) {
            System.err.println("Записът не може да бъде добавен във файла!");
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Записвачът не може да бъде затворен правилно!");
                    e.printStackTrace();
                }
            }
        }
    }
}