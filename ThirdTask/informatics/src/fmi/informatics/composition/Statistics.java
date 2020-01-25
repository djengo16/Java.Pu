package fmi.informatics.composition;

import fmi.informatics.repositories.PersonRepo;

public class Statistics {

    public static void showStatistics(PersonRepo people) {

        int studentsCount = people.getStudentCount();
        int richStudentsCount = people.getRichStudentCount();
        int professorsCount = people.getProfessorCount();
        int totalCount = people.getPeopleCount();

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Total people count is %d.", totalCount));
        sb.append(System.getProperty("line.separator"));
        sb.append(String.format("Total students count is: %d.", studentsCount));
        sb.append(System.getProperty("line.separator"));
        sb.append(String.format("Total rich students count is %d.", richStudentsCount));
        sb.append(System.getProperty("line.separator"));
        sb.append(String.format("Total professors count is %d.", professorsCount));
        sb.append(System.getProperty("line.separator"));


        System.out.println(sb);

        people.callStudyToAllInstances();

        }


    }

