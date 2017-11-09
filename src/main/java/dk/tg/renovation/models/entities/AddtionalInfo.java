package dk.tg.renovation.models.entities;

public class AddtionalInfo {
    private int settlement;
    private String comments;
    private int fkCVR;

    public AddtionalInfo() {
    }

    public AddtionalInfo(int settlement, String comments, int fkCVR) {
        this.settlement = settlement;
        this.comments = comments;
        this.fkCVR = fkCVR;
    }

    public int getSettlement() {
        return settlement;
    }

    public void setSettlement(int settlement) {
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
