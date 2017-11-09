package dk.tg.renovation.models.entities;

public class ContactPerson {

    private String name;
    private int number;
    private int fkCVR;

    public ContactPerson() {
    }

    public ContactPerson(String name, int number, int fkCVR) {
        this.name = name;
        this.number = number;
        this.fkCVR = fkCVR;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getFkCVR() {
        return fkCVR;
    }

    public void setFkCVR(int fkCVR) {
        this.fkCVR = fkCVR;
    }
}
