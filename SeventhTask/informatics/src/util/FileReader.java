package util;

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

import extending.Student;


// Четене и писане във файл
public class FileReader {

    private static final String PERSON_FILE_EXTENSION = ".file";
    private static final String PERSON_FILE_NAME = "students";
    private static final String FILE_PATH = "src/util/files/";
    private static final String FULL_PATH = FILE_PATH + PERSON_FILE_NAME + PERSON_FILE_EXTENSION;

    private static ArrayList<Student> studentsList = new ArrayList<>();

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
    public static Student[] readPeople() {
        try {
            FileInputStream fileStream = new FileInputStream(FULL_PATH);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileStream));
            String stringLine;

            while((stringLine = bufferedReader.readLine()) != null) {
                String[] data = new String[3];
                 data = stringLine.split(" ");
                addPerson(data);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Student[] people = new Student[5];
        return studentsList.toArray(people);
    }

    private static void addPerson(String[] data) {

            Student student = new Student(data[0],data[1],data[2]);
            studentsList.add(student);

    }

    public static void writePeople(Student[] students) {
        Writer writer = null;

        try {
            // Искаме append функция, затова стойността в конструктора е true
            writer = new BufferedWriter(new FileWriter(FULL_PATH, true));

            for (Student student : students) {
                writer.append(student.getName() + "\t");
                writer.append(student.getSurName() + "\t");
                writer.append(student.getLastName() + "\t");

                writer.append("\n");

                System.out.printf("%s е успешно добавен във файла! \n", student.getName());
            }
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
