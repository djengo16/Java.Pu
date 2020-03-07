package shop;

import models.Product;

import javax.swing.table.AbstractTableModel;



public class ProductDataModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private Product[] products;

    // constructor
    public ProductDataModel(Product[] products) {
        this.products = products;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return products.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return products[rowIndex].getName();
            case 1:
                return products[rowIndex].getPrice();
            case 2:
                return products[rowIndex].getQantity();

        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Product name";
            case 1:
                return "Price";
            case 2:
                return "Quantity";


            default:
                return super.getColumnName(column);
        }
    }
}
