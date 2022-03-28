package com.saya.coco.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notifications")

public class NotificationModel {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "notification")
    public String notification;
}
