package dk.tg.renovation.models.entities;


public class Company {
    private String companyName;
    private String password;
    private int cvr;
    private int pNumber;

    public Company() {
    }

    public Company(String companyName, String password, int cvr, int pNumber) {
        this.companyName = companyName;
        this.password = password;
        this.cvr = cvr;
        this.pNumber = pNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
