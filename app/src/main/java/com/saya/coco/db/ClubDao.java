package com.saya.coco.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ClubDao {

    @Query("SELECT * FROM Clubs")
    List<Club> getAllClubs();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertClub(Club club);

    @Update
    void updateClub(Club event);
}
