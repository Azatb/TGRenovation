package dk.tg.renovation.models.entities;

public class Oil {

    private String size;
    private int amount;
    private int fkCVR;

    public Oil() {
    }

    public Oil(String size, int amount) {
        this.size = size;
        this.amount = amount;
    }

    public Oil(String size, int amount, int fkCVR) {
        this.size = size;
        this.amount = amount;
        this.fkCVR = fkCVR;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getFkCVR() {
        return fkCVR;
    }

    public void setFkCVR(int fkCVR) {
        this.fkCVR = fkCVR;
    }
}
