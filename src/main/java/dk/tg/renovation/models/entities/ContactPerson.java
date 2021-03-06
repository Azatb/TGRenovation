package dk.tg.renovation.models.entities;

public class ContactPerson {

    private String name;
    private int number;
    private String pickupAdress;
    private int fkCVR;
    private int id;

    public ContactPerson() {
    }

    public ContactPerson(String name, int number, String pickupAdress) {
        this.name = name;
        this.number = number;
        this.pickupAdress = pickupAdress;
    }

    public ContactPerson(String name, int number, String pickupAdress, int fkCVR) {
        this.name = name;
        this.number = number;
        this.pickupAdress = pickupAdress;
        this.fkCVR = fkCVR;
    }

    public int getId() {
        return id;
    }

    public String getPickupAdress() {
        return pickupAdress;
    }

    public void setPickupAdress(String pickupAdress) {
        this.pickupAdress = pickupAdress;
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
