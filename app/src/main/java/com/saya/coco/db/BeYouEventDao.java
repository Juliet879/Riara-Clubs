package com.saya.coco.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.saya.coco.User;

import java.util.List;

@Dao
public interface BeYouEventDao {
    @Query("SELECT * FROM Events WHERE club_name == 'BeYou'")
    List<EventModel> getAllEvents();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEvent(EventModel event);

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Update
    void updateEvent(EventModel event);

    @Delete
    void delete(EventModel event);
}
