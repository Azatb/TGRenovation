package dk.tg.renovation.models.entities;

import dk.tg.renovation.models.services.Ilogin;

public class Chauffør{

    private int chaufførId;
    private String chaufførUserName;
    private String chaufførPassword;
    private String chaufførRegion;


    public Chauffør() {
    }

    public Chauffør(String chaufførUserName, String chaufførPassword, String chaufførRegion) {
        this.chaufførUserName = chaufførUserName;
        this.chaufførPassword = chaufførPassword;
        this.chaufførRegion = chaufførRegion;
    }

    public int getChaufførId() {
        return chaufførId;
    }

    public void setChaufførId(int chaufførId) {
        this.chaufførId = chaufførId;
    }

    public String getChaufførUserName() {
        return chaufførUserName;
    }

    public void setChaufførUserName(String chaufførUserName) {
        this.chaufførUserName = chaufførUserName;
    }

    public String getChaufførPassword() {
        return chaufførPassword;
    }

    public void setChaufførPassword(String chaufførPassword) {
        this.chaufførPassword = chaufførPassword;
    }

    public String getChaufførRegion() {
        return chaufførRegion;
    }

    public void setChaufførRegion(String chaufførRegion) {
        this.chaufførRegion = chaufførRegion;
    }
}
