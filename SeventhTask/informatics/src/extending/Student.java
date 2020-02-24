package extending;

public class Student {

    private String name;
    private String surName;
    private String lastName;

    public Student(String name, String surName, String lastName) {
        this.name = name;
        this.surName = surName;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
