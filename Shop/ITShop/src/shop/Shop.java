package shop;

import models.Category;
import models.Client;
import models.Product;
import models.SubCategory;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;

public class Shop {


	public static Client client = new Client();
	private static List<Category> categories = new ArrayList<>();
	 public static SubCategory lastClickedSubCategory = null;

	public static void main(String[] args) { // izpulnenieto na programata zapochva ot tuk
		createAndShowGui();
	}

	public static void createAndShowGui() {

		JFrame shopFrame = new JFrame();

		shopFrame.setTitle("Shoes Shop");
		shopFrame.setLayout(new BorderLayout());
		shopFrame.setMinimumSize(new Dimension(600, 600));
		shopFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelMenu = new JPanel();
		shopFrame.add(BorderLayout.LINE_START, panelMenu);
		panelMenu.setLayout(new BorderLayout());
		panelMenu.setBackground(Color.decode("#385454"));
		panelMenu.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		JPanel panelContent = new JPanel();
		shopFrame.add(BorderLayout.CENTER, panelContent);
		panelContent.setLayout(new BorderLayout());
		panelContent.setBackground(Color.decode("#669999"));



		JLabel welcomeLabel = new JLabel("<html><center><h1>Welcome!</h1><br>Please," +
				" select a category.</center></html>", SwingConstants.CENTER);
		panelContent.add(BorderLayout.PAGE_START, welcomeLabel); /* gore */

		//---------------------------------------
		final String PERSON_FILE_EXTENSION = ".file";
		final String PERSON_FILE_NAME = "shopdata";
		final String FILE_PATH = "D:/Java/Нова папка/ITShop/ITshop/src/files/";
		final String FULL_PATH = FILE_PATH + PERSON_FILE_NAME + PERSON_FILE_EXTENSION;


		try {

			FileInputStream fileStream = new FileInputStream(FULL_PATH);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileStream));
			for (String line; (line = bufferedReader.readLine()) != null; ) {

				switch (line.charAt(0)) {
					case '>':
						Category category = new Category(line.substring(1));
						categories.add(category);
						break;
					case '_':
						SubCategory subCategory = new SubCategory(line.substring(1));
						categories.get(categories.size() - 1).subCategories.add(subCategory);
						break;
					case '*':
						String[] productAsString = line.substring(1).split(",");
						String[] priceAsString = productAsString[1].split(" ");
						double price = Double.parseDouble(priceAsString[0]);
						Product product = new Product(productAsString[0]
								, price
								,productAsString[2]);
						int categoryIndex = categories.size() - 1;
						int subCategoryIndex = categories.get(categoryIndex).subCategories.size() - 1;
						categories.get(categoryIndex).subCategories.get(subCategoryIndex).products.add(product);
						break;
				}

			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}


		int count = 0;
		for (Category category : categories) {
			count++;
			for (SubCategory subCategory : category.subCategories) {
				count++;
			}
		}

		JPanel menu = new JPanel();
		panelMenu.add(BorderLayout.PAGE_START, menu);
		menu.setLayout(new GridLayout(count+2, 1));
		menu.setBackground(Color.decode("#c9e9ff"));
		for (Category c : categories) {
			JLabel categoryLabel = new JLabel(c.getName(), SwingConstants.LEFT);

			menu.add(categoryLabel);

			for (SubCategory sc : c.subCategories) {
				JLabel subCategoryLabel = new JLabel(sc.getName(), SwingConstants.RIGHT);
				menu.add(subCategoryLabel);
				subCategoryLabel.setOpaque(true);
				subCategoryLabel.setForeground(Color.WHITE);
				subCategoryLabel.setBackground(Color.decode("#385454"));
				subCategoryLabel.setBorder(BorderFactory
						.createMatteBorder(1, 0, 1, 1,
								(new Color(255, 255, 255, 0))));
				subCategoryLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						//Default sorting products by price !!
						for (Category category : categories){
							for (SubCategory subCategory : category.subCategories){
								subCategory.products.sort((p1,p2)-> Double.compare(p1.getPrice(),p2.getPrice()));
							}
						}
						lastClickedSubCategory = sc; // this is if we want to use products sorting
						ProductPanel.drawPnael(panelContent,sc,client);

					}

					@Override
					public void mouseEntered(MouseEvent e) {
						super.mouseEntered(e);
						subCategoryLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						subCategoryLabel.setForeground(Color.decode("#389eff"));
						subCategoryLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1,
								(Color.BLACK)));
						subCategoryLabel.setBackground(new Color(194, 255, 211, 127));
						menu.repaint();
					}

					@Override
					public void mouseExited(MouseEvent e) {
						super.mouseExited(e);
						subCategoryLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						subCategoryLabel.setForeground(Color.white);
						subCategoryLabel.setBorder(BorderFactory.createMatteBorder
								(1, 0, 1, 1, (new Color(255, 255, 255, 0))));
						subCategoryLabel.setBackground(Color.decode("#385454")); // 0% alpha
						menu.repaint();
					}
				});
			}
		}
		JLabel basketLabel = new JLabel("Your Products", SwingConstants.LEFT);
		basketLabel.setOpaque(true);
		basketLabel.setBackground(new Color(29, 135, 242, 127));
		basketLabel.setForeground(Color.black);
        basketLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				BasketDataGui.createAndShowGUI(client.getProducts());

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				basketLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				basketLabel.setForeground(Color.WHITE);
				basketLabel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
				basketLabel.setBackground(new Color(29, 135, 242, 127));
				menu.repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				basketLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				basketLabel.setForeground(Color.black);
				basketLabel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
						new Color(255, 255, 255, 0)));
				basketLabel.setBackground(new Color(29, 135, 242, 0));
				menu.repaint();
			}

		});

		menu.add(basketLabel);
		JButton sortAlphabeticaly = new JButton("Sort products");
		menu.add(sortAlphabeticaly);
		sortAlphabeticaly.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				super.mouseClicked(e);
				for (Category category : categories){
					for (SubCategory subCategory : category.subCategories){
						subCategory.products.sort((p1,p2)->p1.getName().compareTo(p2.getName()));
					}
				}
				ProductPanel.drawPnael(panelContent,lastClickedSubCategory,client);
				panelContent.revalidate();
				panelContent.repaint();

			}
		});

		shopFrame.pack();
		shopFrame.setLocationRelativeTo(null);
		shopFrame.setVisible(true);
	}
}
