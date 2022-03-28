package com.saya.coco.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RedXEventDao {
    @Query("SELECT * FROM Events WHERE club_name == 'RedCross' ")
    List<EventModel> getAllEvents();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEvent(EventModel event);


    @Update
    void updateEvent(EventModel event);

    @Delete
    void delete(EventModel event);
}
