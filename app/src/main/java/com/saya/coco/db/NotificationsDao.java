package com.saya.coco.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotificationsDao {
    @Query("SELECT * FROM Notifications")
    List<NotificationModel> getAllNotifications();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEvent(NotificationModel notificationModel);

}
