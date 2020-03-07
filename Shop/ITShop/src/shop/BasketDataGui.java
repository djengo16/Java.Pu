package shop;



import models.Product;
import util.FileReader;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.util.*;

import javax.swing.*;



// създаваме клас PersonDataGUI
public class BasketDataGui {




    public static void createAndShowGUI(ArrayList<Product> products) {
        JTable table;
        ProductDataModel productDataModel;
        JFrame frame = new JFrame("Your products");
        frame.setSize(600, 500);

        JLabel label = new JLabel("List of products", JLabel.CENTER);
        Product[] productsArray = products.toArray(new Product[products.size()]);

         double totalPrice = 0;
        for (Product p :products){
            totalPrice+=p.getQantity() * p.getPrice();
        }
        JButton buy = new JButton("Buy " + totalPrice + "bgn total.");
        double finalTotalPrice = totalPrice;
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                FileReader.writePeople(productsArray, finalTotalPrice);
            }
        });
        productDataModel = new ProductDataModel(productsArray);
        table = new JTable(productDataModel);

        JScrollPane scrollPane = new JScrollPane(table);


        // Добавяме панел, където ще поставим бутоните
        JPanel buttonsPanel = new JPanel();

        buttonsPanel.add(buy);


        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(label, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);
        // Добавяме панелът с бутоните в контейнера
        container.add(buttonsPanel, BorderLayout.SOUTH);


        // Добавяме listener за филтрация

        frame.setVisible(true);
    }



}
