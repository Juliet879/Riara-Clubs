package com.saya.coco.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.saya.coco.User;

import java.util.List;

@Dao
public interface UserDao {
//    @Transaction
//    @Query("SELECT * FROM Users")
//    List<UserWIthEvents> getUserWithEvents();

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);


//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertEvent(EventModel event);

}
