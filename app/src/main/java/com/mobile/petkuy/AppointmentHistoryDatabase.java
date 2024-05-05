package com.mobile.petkuy;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {AppointmentHistory.class}, version = 1)
public abstract class AppointmentHistoryDatabase extends RoomDatabase {
    private static AppointmentHistoryDatabase instance;
    public abstract AppointmentHistoryDAO getAppointmentHistoryDAO();

    public static synchronized AppointmentHistoryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppointmentHistoryDatabase.class, "appointment_history_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
