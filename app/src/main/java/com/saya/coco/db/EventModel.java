package com.saya.coco.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.saya.coco.User;

@Entity(tableName = "Events")
public class EventModel {
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "event_id")
    public int event_id;

    @ColumnInfo(name = "club_name")
    public String clubName;

    @ColumnInfo(name = "event_title")
    public String eventTitle;

    @ColumnInfo(name = "event_location")
    public String eventLocation;

    @ColumnInfo(name = "event_day")
    public String eventDay;

    @ColumnInfo(name = "start_time")
    public String startTime;

    @ColumnInfo(name = "end_time")
    public String endTime;

    @ColumnInfo(name = "frequency")
    public String frequency;

    @ColumnInfo(name = "emailAcc")
    public String emailAcc;

    public EventModel(){}

    public EventModel(String club, String title, String location, String date, String eventStartTime, String eventEndTime, String frequency, String calendar) {
        this.eventTitle = title;
        this.clubName = club;
        this.eventLocation = location;
        this.eventDay = date;
        this.startTime = eventStartTime;
        this.endTime = eventEndTime;
        this.frequency = frequency;
        this.emailAcc = calendar;
    }
}


//    }
//    public String getEvent_name() {
//        return title;
//    }
//    public void setEvent_name(String club_name) {
//        this.title = title;
//    }
//    public String getEvent_club() {
//        return club;
//    }
//    public void setEvent_club(String club) {
//        this.club = club;
//    }
//    public String getEvent_location() {
//        return location;
//    }
//    public void setEvent_location(String location) {
//        this.location = location;
//    }
//    public String getEvent_date() {
//        return date;
//    }
//    public void setEvent_date(String date) {
//        this.date = date;
//    }
//    public String getEvent_startTime() {
//        return startTime;
//    }
//    public void setEvent_startTime(String startTime) {
//        this.startTime = startTime;
//    }
//    public String getEvent_endTime() {
//        return endTime;
//    }
//    public void setEvent_endTime(String endTime) {
//        this.endTime = endTime;
//    }
//    public String getEvent_frequency() {
//        return frequency;
//    }
//    public void setEvent_frequency(String frequency) {
//        this.frequency = frequency;
//    }
//    public String getEvent_calendar() {
//        return calendar;
//    }
//    public void setEvent_calendar(String calendar) {
//        this.frequency = calendar;
//    }
//

//
//
//    public EventModel(String club, String title, String location, String date, String eventStartTime, String eventEndTime, String frequency, String calendar) {
//        this.title = title;
//        this.club = club;
//        this.location = location;
//        this.date = date;
//        this.startTime = eventStartTime;
//        this.endTime = eventEndTime;
//        this.frequency = frequency;
//        this.calendar = calendar;
//    }

