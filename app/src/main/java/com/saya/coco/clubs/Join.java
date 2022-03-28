package com.saya.coco.clubs;

public class Join {

    private String klabTitle, tcTitle, tcSubTitle, clubTC;


    public Join(String klabTitle, String tcTitle, String tcSubTitle, String clubTC) {
        this.klabTitle = klabTitle;
        this.tcTitle = tcTitle;
        this.tcSubTitle = tcSubTitle;
        this.clubTC = clubTC;

    }

    public String getKlabTitle() {
        return klabTitle;
    }

    public void setKlabTitle(String klabTitle) {
        this.klabTitle= klabTitle;
    }

    public String getTcTitle() {
        return tcTitle;
    }

    public void setTcTitle(String tcTitle) {
        this.tcTitle = tcTitle;
    }

    public String getTcSubTitle() {
        return tcSubTitle;
    }

    public void setTcSubTitle(String tcSubTitle) {
        this.tcSubTitle = tcSubTitle;
    }

    public String getClubTC() {
        return clubTC;
    }

    public void setClubTC(String clubTC) {
        this.clubTC = clubTC;
    }


}
