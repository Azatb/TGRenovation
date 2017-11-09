package dk.tg.renovation.models.entities;

public class Company {
    private String companyName;
    private int cvr;
    private int pNumber;
    private String pickupAdress;

    public Company() {
    }

    public Company(String companyName, int cvr, int pNumber, String pickupAdress) {
        this.companyName = companyName;
        this.cvr = cvr;
        this.pNumber = pNumber;
        this.pickupAdress = pickupAdress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCvr() {
        return cvr;
    }

    public void setCvr(int cvr) {
        this.cvr = cvr;
    }

    public int getpNumber() {
        return pNumber;
    }

    public void setpNumber(int pNumber) {
        this.pNumber = pNumber;
    }

    public String getPickupAdress() {
        return pickupAdress;
    }

    public void setPickupAdress(String pickupAdress) {
        this.pickupAdress = pickupAdress;
    }
}
