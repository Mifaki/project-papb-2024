package com.mobile.petkuy;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Doctor.class}, version = 1)
public abstract class DoctorDatabase extends RoomDatabase {
    private static DoctorDatabase instance;
    public abstract DoctorDAO getDoctorDAO();

    public static synchronized DoctorDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                        DoctorDatabase.class, "doctor_database")
                        .fallbackToDestructiveMigration().
                        build();
        }
        return instance;
    }
}
