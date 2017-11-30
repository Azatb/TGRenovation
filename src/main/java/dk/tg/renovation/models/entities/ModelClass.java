package dk.tg.renovation.models.entities;

public class ModelClass {

    private String name;
    private int number;
    private String pickupAdress;
    private String size;
    private int amount;
    private String settlement;
    private String comments;
    private String weekDay;
    private String region;
    private int id;

    public ModelClass() {
    }

    public ModelClass(String name, int number, String pickupAdress, String size, int amount, String settlement, String comments, String weekDay, String region) {
        this.name = name;
        this.number = number;
        this.pickupAdress = pickupAdress;
        this.size = size;
        this.amount = amount;
        this.settlement = settlement;
        this.comments = comments;
        this.weekDay = weekDay;
        this.region = region;
    }

    public ModelClass(String name, int number, String pickupAdress, String size, int amount, String settlement, String comments, String weekDay, String region, int id) {
        this.name = name;
        this.number = number;
        this.pickupAdress = pickupAdress;
        this.size = size;
        this.amount = amount;
        this.settlement = settlement;
        this.comments = comments;
        this.weekDay = weekDay;
        this.region = region;
        this.id = id;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPickupAdress() {
        return pickupAdress;
    }

    public void setPickupAdress(String pickupAdress) {
        this.pickupAdress = pickupAdress;
    }

   

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
}
