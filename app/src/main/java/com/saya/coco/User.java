package com.saya.coco;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.saya.coco.db.EventModel;

@Entity(tableName = "Users", foreignKeys = @ForeignKey(
        entity = Class.class,
        parentColumns = {"event_id"},
        childColumns = {"eventId"},
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE))

public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    public int id;


    @ColumnInfo(name = "eventId", index = true)
    public int eventId;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "email_address")
    public String emailAddress;

    @ColumnInfo(name = "student_id")
    public String studentID;

    @ColumnInfo(name = "phone_number")
    public String phoneNumber;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "faculty")
    public String schoolFromDB;

    @ColumnInfo(name = "year")
    public String yrFromDB;

    @ColumnInfo(name = "gender")
    public String sexFromDB;

    @ColumnInfo(name = "age")
    public int ageFromDB;

    @Embedded
    public EventModel eventModel;

    //    String passFromDB;
//    String cpassFromDB;

   public User() {}

    public User(String firstFromDB, String lastFromDB, String emailFromDB
                    ,  String numFromDB, String studentIDFromDB, String passwordFromDB,
                String schoolFromDB,String yrFromDB,String sexFromDB,int ageFromDB) {
        this.firstName = firstFromDB;
        this.lastName = lastFromDB;
        this.emailAddress = emailFromDB;
        this.phoneNumber = numFromDB;
        this.studentID = studentIDFromDB;
        this.password = passwordFromDB;
        this.schoolFromDB = schoolFromDB;
        this.yrFromDB = yrFromDB;
        this.sexFromDB = sexFromDB;
        this.ageFromDB = ageFromDB;
//        this.passFromDB = passFromDB;
//        this.cpassFromDB = cpassFromDB;
    }

//    public String getFirstFromDB() {
//        return firstName;
//    }
//
//    public String getLastFromDB() {
//        return lastName;
//    }
//
//    public String getEmailFromDB() {
//        return emailAddress;
//    }
//
//    public String getNumFromDB() {
//        return phoneNumber;
//    }
//
//    public String getStudentIDFromDB() {
//        return studentID;
//    }
//
//    public String getSchoolFromDB() {
//        return schoolFromDB;
//    }
//
//    public String getYrFromDB() {
//        return yrFromDB;
//    }
//
//    public String getSexFromDB() {
//        return sexFromDB;
//    }
//
//    public int getAgeFromDB() {
//        return ageFromDB;
//    }

//    public String getPassword() {
//        return password;
//    }




//    public String getSchoolFromDB() {
//        return schoolFromDB;
//    }
//
//    public String getYrFromDB() {
//        return yrFromDB;
//    }
//
//    public String getSexFromDB() {
//        return sexFromDB;
//    }
//
//    public int getAgeFromDB() {
//        return ageFromDB;
//    }
//
//    public String getPassFromDB() {
//        return passFromDB;
//    }

//    public String getCpassFromDB() {
//        return cpassFromDB;
//    }

//    public void setFirstFromDB(String firstFromDB) {
//        this.firstName = firstFromDB;
//    }
//
//    public void setLastFromDB(String lastFromDB) {
//        this.lastName = lastFromDB;
//    }
//
//    public void setEmailFromDB(String emailFromDB) {
//        this.emailAddress = emailFromDB;
//    }
//
//    public void setStudentIDFromDB(String studentIDFromDB) {
//        this.studentID = studentIDFromDB;
//    }
//
//    public void setNumFromDB(String numFromDB) {
//        this.phoneNumber = numFromDB;
//    }

//    public void setSchoolFromDB(String schoolFromDB) {
//        this.schoolFromDB = schoolFromDB;
//    }
//
//    public void setYrFromDB(String yrFromDB) {
//        this.yrFromDB = yrFromDB;
//    }
//
//    public void setSexFromDB(String sexFromDB) {
//        this.sexFromDB = sexFromDB;
//    }
//
//    public void setAgeFromDB(int ageFromDB) {
//        this.ageFromDB = ageFromDB;
//    }
//
//    public void setPassFromDB(String passFromDB) {
//        this.passFromDB = passFromDB;
//    }
//
//    public void setCpassFromDB(String cpassFromDB) {
//        this.cpassFromDB = cpassFromDB;
//    }
}
