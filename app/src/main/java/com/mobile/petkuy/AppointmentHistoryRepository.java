package com.mobile.petkuy;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AppointmentHistoryRepository {
    private AppointmentHistoryDAO appointmentHistoryDAO;
    private LiveData<List<AppointmentHistory>> allAppointmentHistory;

    public AppointmentHistoryRepository(Application application) {
        AppointmentHistoryDatabase database = AppointmentHistoryDatabase.getInstance(application);
        appointmentHistoryDAO = database.getAppointmentHistoryDAO();
        allAppointmentHistory = appointmentHistoryDAO.getAllAppointmentHistory();
    }

    public void insert(AppointmentHistory appointmentHistory) {
        new InsertAppointmentHistoryAsyncTask(appointmentHistoryDAO).execute(appointmentHistory);
    }

    public LiveData<List<AppointmentHistory>>getAllAppointmentHistory() {
        return allAppointmentHistory;
    }

    private static class InsertAppointmentHistoryAsyncTask extends AsyncTask<AppointmentHistory, Void, Void> {
        private AppointmentHistoryDAO appointmentHistoryDAO;
        private InsertAppointmentHistoryAsyncTask(AppointmentHistoryDAO appointmentHistoryDAO) {
            this.appointmentHistoryDAO = appointmentHistoryDAO;
        }

        @Override
        protected Void doInBackground(AppointmentHistory... appointmentHistories) {
            appointmentHistoryDAO.addAppointmentHistory(appointmentHistories[0]);
            return null;
        }
    }

    public void delete(AppointmentHistory appointmentHistory) {
        new DeleteAppointmentHistoryAsyncTask(appointmentHistoryDAO).execute(appointmentHistory);
    }

    private static class DeleteAppointmentHistoryAsyncTask extends AsyncTask<AppointmentHistory, Void, Void> {
        private AppointmentHistoryDAO appointmentHistoryDAO;
        private DeleteAppointmentHistoryAsyncTask(AppointmentHistoryDAO appointmentHistoryDAO) {
            this.appointmentHistoryDAO = appointmentHistoryDAO;
        }

        @Override
        protected Void doInBackground(AppointmentHistory... appointmentHistories) {
            appointmentHistoryDAO.deleteAppointmentHistory(appointmentHistories[0]);
            return null;
        }
    }
}
