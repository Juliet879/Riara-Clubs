package com.saya.coco;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "EventsCounter")

public class Counter {
    int eventsNumber;

    public Counter() {}


    public Counter(int eventsNum) {
        this.eventsNumber = eventsNum;

    }

    public int getEventsNumber() {
        return eventsNumber;
    }


}
