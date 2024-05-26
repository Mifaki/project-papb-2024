package com.mobile.petkuy;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DoctorDAO {

    @Insert
    public void addDoctor(Doctor doctor);

    @Update
    public void updateDoctor(Doctor doctor);

    @Delete
    public void deleteDoctor(Doctor doctor);

    @Query("select * from doctor")
    public LiveData<List<Doctor>> getAllDoctor();

    @Query("DELETE FROM doctor")
    public void deleteAllDoctors();
}
