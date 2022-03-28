package com.saya.coco.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Clubs")
public class Club {

    @PrimaryKey(autoGenerate = true)
    public int uid;


    @ColumnInfo(name = "club_name")
    public String clubName;
    }


//    public String getClubName() {
//        return clubName;
//    }
//
//    public void setClubName(String clubName) {
//        this.clubName = clubName;
//    }
//
//    public int getNoOfStudents() {
//        return noOfStudents;
//    }
//
//    public void setNoOfStudents(int noOfStudents) {
//        this.noOfStudents = noOfStudents;
//    }
//
//    public String getClubID() {
//        return ClubID;
//    }
//
//    public void setClubID(String clubID) {
//        ClubID = clubID;
//    }

