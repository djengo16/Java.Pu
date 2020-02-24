package gui;

import comparators.LastnameComparator;
import comparators.NameComparator;
import comparators.StudentComparator;
import comparators.SurnameComparator;
import enums.EType;
import extending.Student;
import util.FileReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;


public class StudentDataGui extends JFrame {
    public static Student[] students;

    JTable table;
    StudentDataModel studentDataModel;

    public static void main(String[] args) {
        // Ако извикваме четенето от файл, трябва да закоментираме метода getPeople()
        // getPeople();

        // TODO Извикваме четенето от файл
        students = FileReader.readPeople();

        // TODO Извикваме писането във файл
        initializeData();

        StudentDataGui gui = new StudentDataGui();
        gui.createAndShowGUI();
    }

    // TODO Добавяме писането/четенето във файл
    public static void initializeData() {
        if (!FileReader.isFileExists()) {
            FileReader.createPersonFile();
        }


    }



    public void createAndShowGUI() {
        JFrame frame = new JFrame("Таблица с данни за студенти");
        frame.setSize(600, 350);

        JLabel label = new JLabel("Списък с потребители", JLabel.CENTER);

        frame.getContentPane().setBackground(Color.lightGray);

        studentDataModel = new StudentDataModel(students);
        table = new JTable(studentDataModel);
        table.setBackground(Color.orange);
        JScrollPane scrollPane = new JScrollPane(table);

        // Добавяме бутон за сортиране по години с Comparable interface


        // Добавяме бутон за сортиране

        JButton buttonSortAscending = new JButton("Сортирай по възходящ ред");
        JButton buttonSortDescending = new JButton("Сортирай по низходящ ред");

        // TODO Добавяме бутон за филтриране
        JButton buttonFilter = new JButton("Филтрирай");


        // TODO Добавяме панел, където ще поставим бутоните
        JPanel buttonsPanel = new JPanel();

        buttonsPanel.add(buttonSortAscending);
        buttonsPanel.add(buttonFilter);
        buttonsPanel.add(buttonSortDescending);



        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(label, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);
        // TODO Добавяме панелът с бутоните в контейнера
        container.add(buttonsPanel, BorderLayout.SOUTH);




        // TODO Променяме диалога за сортиране
      //  final JDialog sortDialog = new CustomDialog(getSort(), this, EType.SORT);

        // TODO Добавяме диалог за филтрацията
        final JDialog filterDialog = new CustomDialog(getFilterText(), this, EType.FILTER,SortOrder.ASCENDING);

        // Добавяме listener към бутона за сортиране
// Добавяме диалог
        final JDialog sortDialogAscending = new CustomDialog(getSortText(), this,EType.SORT,SortOrder.ASCENDING);

        buttonSortAscending.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortDialogAscending.pack();

                sortDialogAscending.setVisible(true);
            }
        });

        final JDialog sortDialogDescending = new CustomDialog(getSortText(), this,EType.SORT,SortOrder.DESCENDING);
        // Добавяме listener към бутона за сортиране
        buttonSortDescending.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                sortDialogDescending.pack()
                ;sortDialogDescending.setVisible(true);

            }
        }



        );
        // TODO Добавяме listener за филтрация
        buttonFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterDialog.pack();
                filterDialog.setVisible(true);
            }
        });
    frame.setVisible(true);
    }

        // TODO Добавяме метод за филтриране
        public void filterTable ( String lastName , JTable table, Student[] students){


                    this.studentDataModel = new StudentDataModel(filterData(students,lastName));

            table.setModel(this.studentDataModel);
            table.repaint();
        }

        // TODO Добавяме помощен метод за филтриране
        public static Student[] filterData (Student[]students,String lastName){
            ArrayList<Student> filteredData = new ArrayList<>();

            for (int i = 0; i < students.length; i++) {

                if (lastName.equals(students[i].getLastName())) {


                        filteredData.add(students[i]);
                    }
                }

            // Преобразуваме списъка в масив
            Student[] filteredDataArray = new Student[filteredData.size()];
            filteredDataArray = filteredData.toArray(filteredDataArray);
            return filteredDataArray;
        }

        public void sortTable ( int intValue, JTable table, Student[]students,SortOrder order){
            StudentComparator comparator = null;

            switch (intValue) {
                case 1:

                    comparator = new NameComparator();
                    checkIfDescending( order, comparator);
                    break;
                case 2:

                    comparator = new SurnameComparator();
                    checkIfDescending( order, comparator);
                    break;
                case 3:

                    comparator = new LastnameComparator();
                    checkIfDescending( order, comparator);
                    break;

            }

            if (comparator == null) { // Ако стойността е null - сортирай по подразбиране
                Arrays.sort(students); // Сортировка по подразбиране по години
            } else {
                Arrays.sort(students, comparator);
            }
            studentDataModel = new StudentDataModel(students);
            table.setModel(studentDataModel);
            table.repaint();
        }
    private static void checkIfDescending(SortOrder order,StudentComparator comparator){
        if (order.equals(SortOrder.DESCENDING)){
            comparator.setSortOrder(SortOrder.DESCENDING);

        }
    }

        private static String getSortText () {
            return "Моля, въведете цифрата на колоната, по която да се сортират данните: \n" +
                    " 1 - Име \n" +
                    " 2 - Презиме \n" +
                    " 3 - Фамилия \n";

        }

        // TODO Добавяме текст, който да се визуализира в диалога за филтриране
        private static String getFilterText () {
            return "Моля, въведете фамилията на търсения студент\n" ;
        }
    }
