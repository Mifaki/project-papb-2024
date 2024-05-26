package com.mobile.petkuy;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AppointmentHistoryDAO {
    @Insert
    public void addAppointmentHistory(AppointmentHistory appointmentHistory);

    @Update
    public void updateAppointmentHistory(AppointmentHistory appointmentHistory);

    @Delete
    public void deleteAppointmentHistory(AppointmentHistory appointmentHistory);

    @Query("select * from appoinment_history")
    public LiveData<List<AppointmentHistory>> getAllAppointmentHistory();
}
