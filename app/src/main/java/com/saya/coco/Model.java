package com.saya.coco;

public class Model {

    private int iv_bg, iv_logo;
    private String club_name, club_slogan, tv_info, tv_join;

    public Model(int iv_bg, int iv_logo, String club_name, String club_slogan, String tv_info, String tv_join) {
        this.iv_bg = iv_bg;
        this.iv_logo = iv_logo;
        this.club_name = club_name;
        this.club_slogan = club_slogan;
        this.tv_info = tv_info;
        this.tv_join = tv_join;
    }

    public int getIv_bg() {
        return iv_bg;
    }

    public void setIv_bg(int iv_bg) {
        this.iv_bg = iv_bg;
    }

    public int getIv_logo() {
        return iv_logo;
    }

    public void setIv_logo(int iv_logo) {
        this.iv_logo = iv_logo;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public String getClub_slogan() {
        return club_slogan;
    }

    public void setClub_slogan(String club_slogan) {
        this.club_slogan = club_slogan;
    }

    public String getTv_info() {
        return tv_info;
    }

    public void setTv_info(String tv_info) {
        this.tv_info = tv_info;
    }

    public String getTv_join() {
        return tv_join;
    }

    public void setTv_join(String tv_join) {
        this.tv_join = tv_join;
    }
}
