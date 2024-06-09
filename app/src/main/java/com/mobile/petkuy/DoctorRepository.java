package com.mobile.petkuy;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DoctorRepository {
    private DoctorDAO doctorDAO;
    private LiveData<List<Doctor>> allDoctor;

    public DoctorRepository(Application application) {
        DoctorDatabase database = DoctorDatabase.getInstance(application);
        doctorDAO = database.getDoctorDAO();
        allDoctor = doctorDAO.getAllDoctor();
    }

    public void insert(Doctor doctor) {
        new InsertDoctorAsyncTask(doctorDAO).execute(doctor);
    }

    public LiveData<List<Doctor>> getAllDoctors() {
        return allDoctor;
    }

    private static class InsertDoctorAsyncTask extends AsyncTask<Doctor, Void, Void>{
        private DoctorDAO doctorDAO;
        private InsertDoctorAsyncTask(DoctorDAO doctorDAO) {
            this.doctorDAO = doctorDAO;
        }

        @Override
        protected Void doInBackground(Doctor... doctors) {
            doctorDAO.addDoctor(doctors[0]);
            return null;
        }
    }

    public void delete(Doctor doctor) {
        new DeleteDoctorAsyncTask(doctorDAO).execute(doctor);
    }

    private static class DeleteDoctorAsyncTask extends AsyncTask<Doctor, Void, Void> {
        private DoctorDAO doctorDAO;
        private DeleteDoctorAsyncTask(DoctorDAO doctorDAO) {
            this.doctorDAO = doctorDAO;
        }

        @Override
        protected Void doInBackground(Doctor... doctors) {
            doctorDAO.deleteDoctor(doctors[0]);
            return null;
        }
    }

    public void deleteAllDoctors() {
        new DeleteAllDoctorsAsyncTask(doctorDAO).execute();
    }

    private static class DeleteAllDoctorsAsyncTask extends AsyncTask<Void, Void, Void> {
        private DoctorDAO doctorDAO;

        private DeleteAllDoctorsAsyncTask(DoctorDAO doctorDAO) {
            this.doctorDAO = doctorDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            doctorDAO.deleteAllDoctors();
            return null;
        }
    }
}
