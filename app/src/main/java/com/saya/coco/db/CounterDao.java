package com.saya.coco.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.saya.coco.Counter;

import java.util.List;

@Dao
public interface CounterDao {
    @Query("SELECT * FROM EventsCounter")
    List<Counter> getEventsNumber();

}
