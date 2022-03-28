package com.saya.coco.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.saya.coco.Counter;
import com.saya.coco.User;

@Database(entities = {EventModel.class, Club.class, NotificationModel.class, User.class}, version  = 24, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MusicEventDao musicEventDao();

    public abstract BeYouEventDao beYouEventDao();

    public abstract RedXEventDao redXEventDao();

    public abstract NotificationsDao notificationsDao();

    public abstract ClubDao clubDao();

    public abstract UserDao userDao();


    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context) {

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "EventsDb")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return INSTANCE;
    }
}
