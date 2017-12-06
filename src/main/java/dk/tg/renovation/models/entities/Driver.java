package dk.tg.renovation.models.entities;

public class Driver {

    private String driverUserName;
    private String driverPassword;
    private String driverRegion;
    private int driverId;


    public Driver() {
    }

    public Driver(String driverUserName, String driverPassword, String driverRegion) {
        this.driverUserName = driverUserName;
        this.driverPassword = driverPassword;
        this.driverRegion = driverRegion;
    }

    public Driver(String driverUserName, String driverPassword, String driverRegion, int driverId) {
        this.driverUserName = driverUserName;
        this.driverPassword = driverPassword;
        this.driverRegion = driverRegion;
        this.driverId = driverId;
    }

    public String getDriverUserName() {
        return driverUserName;
    }

    public void setDriverUserName(String driverUserName) {
        this.driverUserName = driverUserName;
    }

    public String getDriverPassword() {
        return driverPassword;
    }

    public void setDriverPassword(String driverPassword) {
        this.driverPassword = driverPassword;
    }

    public String getDriverRegion() {
        return driverRegion;
    }

    public void setDriverRegion(String driverRegion) {
        this.driverRegion = driverRegion;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }
}
