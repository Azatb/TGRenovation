package dk.tg.renovation.models.entities;

public class AdditionalInfo {
    private String settlement;
    private String comments;
    private int fkCVR;
    private int id;

    public AdditionalInfo() {
    }

    public AdditionalInfo(String settlement, String comments, int fkCVR, int id) {
        this.settlement = settlement;
        this.comments = comments;
        this.fkCVR = fkCVR;
        this.id = id;
    }

    public AdditionalInfo(String settlement, String comments, int fkCVR) {
        this.settlement = settlement;
        this.comments = comments;
        this.fkCVR = fkCVR;
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
