package dk.tg.renovation.models.entities;

public class AdditionalInfo {
    private String settlement;
    private String comments;
    private String weekDay;
    private String region;
    private int fkCVR;
    private int id;

    public AdditionalInfo() {
    }

    public AdditionalInfo(String settlement, String comments, String weekDay, String region, int fkCVR, int id) {
        this.settlement = settlement;
        this.comments = comments;
        this.weekDay = weekDay;
        this.region = region;
        this.fkCVR = fkCVR;
        this.id = id;
    }

    public AdditionalInfo(String settlement, String comments, String weekDay, String region, int fkCVR) {
        this.settlement = settlement;
        this.comments = comments;
        this.weekDay = weekDay;
        this.region = region;
        this.fkCVR = fkCVR;
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

    public int getFkCVR() {
        return fkCVR;
    }

    public void setFkCVR(int fkCVR) {
        this.fkCVR = fkCVR;
    }
}