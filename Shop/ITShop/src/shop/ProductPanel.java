package shop;

import models.Client;
import models.Product;
import models.SubCategory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProductPanel {


    public static void drawPnael(JPanel panelContent, SubCategory sc, Client client) {
        panelContent.removeAll();

        JPanel productsPanel = new JPanel();
        productsPanel.setLayout(new WrapLayout());
        productsPanel.setBackground(Color.decode("#669999"));

        for (Product product : sc.products) {

            BufferedImage tmp = null;
            try {
                tmp = ImageIO.read(new File("D:/Java/Java.Projects.Homeworks/Java.Pu/Shop/ITShop"
                        + product.getImg_path()));

            } catch (IOException e1) {
                e1.printStackTrace();
            }
            final Image image = tmp.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            JPanel productPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image, 0, 0, null);
                }
            };
            productPanel.setLayout(new BorderLayout());
            productPanel.setPreferredSize(new Dimension(200, 200));


            JLabel name = new JLabel(product.getName(), SwingConstants.CENTER);
            name.setOpaque(true);
            name.setBackground(new Color(142, 206, 249, 127));
            name.setForeground(Color.BLACK);

            productPanel.add(BorderLayout.PAGE_START, name);

            JPanel downPanel = new JPanel();
            downPanel.setLayout(new GridLayout(1, 2));

            JLabel price = new JLabel(String.valueOf(product.getPrice()) + " bgn");
            price.setOpaque(true);
            price.setBackground(new Color(255, 255, 255, 0));
            price.setForeground(Color.WHITE);

            JLabel buy = new JLabel("Add to basket", SwingConstants.CENTER);
            buy.setOpaque(true);
            buy.setBackground(new Color(29, 135, 242, 0));
            buy.setForeground(Color.BLACK);
            buy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(255, 255, 255, 0)));

            buy.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    client.addProduct(product);

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    buy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    buy.setForeground(Color.WHITE);
                    buy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
                    buy.setBackground(new Color(29, 135, 242, 127));
                    productPanel.repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    buy.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    buy.setForeground(Color.black);
                    buy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
                            new Color(255, 255, 255, 0)));
                    buy.setBackground(new Color(29, 135, 242, 0));
                    productPanel.repaint();
                }
            });

            downPanel.setOpaque(true);
            downPanel.add(price);
            downPanel.add(buy);
            downPanel.setBackground(new Color(74, 160, 247, 127));
            productPanel.add(BorderLayout.PAGE_END, downPanel);

            productsPanel.add(productPanel);

            productPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    name.setBackground(new Color(142, 206, 249, 191));
                    downPanel.setBackground(new Color(74, 160, 247, 191));
                    productPanel.repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    name.setBackground(new Color(142, 206, 249, 127));
                    downPanel.setBackground(new Color(74, 160, 247, 127));
                    productPanel.repaint();
                }
            });

        }

        JScrollPane scroll = new JScrollPane(productsPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        panelContent.add(BorderLayout.CENTER, scroll);
        panelContent.revalidate();
        panelContent.repaint();
    }
}
