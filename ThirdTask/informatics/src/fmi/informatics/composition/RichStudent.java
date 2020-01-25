package fmi.informatics.composition;

import fmi.informatics.extending.Student;

public class RichStudent {

    private Student student;

    public RichStudent(String name,int egn,String university,String speciality,int facNumber){
        student = new Student(name,egn,university,speciality,facNumber);
    }

    public void run(){
        student.run();
    }

    public void study(){
        student.study();
    }

    public void takeExam(){
        student.takeExam();
    }

    @Override
    public String toString(){
    return student.toString();
    }

    public void goBar(){
        System.out.println(String.format("Student %s %s drinking GreyGoose"
                ,this.getClass().getSimpleName()
                ,student.getName()));
    }

    public String getUniversity(){
        return student.getUniversity();
    }

    public void setUniversity(String university){
        student.setUniversity(university);
    }

}
