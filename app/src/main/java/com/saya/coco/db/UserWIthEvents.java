package com.saya.coco.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.saya.coco.User;

import java.util.List;

public class UserWIthEvents {
    @Embedded private EventModel eventModel;

    @Relation(
            parentColumn = "event_id",
            entityColumn = "user_id"
    )
    public User user;

    }

