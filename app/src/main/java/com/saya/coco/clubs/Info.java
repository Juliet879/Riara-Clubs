package com.saya.coco.clubs;


public class Info {


    private String club_title, about_us, club_desc;


    public Info(String club_title, String about_us, String club_desc) {
        this.club_title = club_title;
        this.about_us = about_us;
        this.club_desc = club_desc;

    }

    public String getClub_title() {
        return club_title;
    }

    public void setClub_title(String club_title) {
        this.club_title = club_title;
    }

    public String getAbout_us() {
        return about_us;
    }

    public void setAbout_us(String about_us) {
        this.about_us = about_us;
    }

    public String getClub_desc() {
        return club_desc;
    }

    public void setClub_desc(String club_desc) {
        this.club_desc = club_desc;
    }

}
