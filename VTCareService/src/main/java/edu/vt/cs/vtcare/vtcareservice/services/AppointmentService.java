package edu.vt.cs.vtcare.vtcareservice.services;

import edu.vt.cs.vtcare.vtcareservice.dao.AppointmentDao;
import edu.vt.cs.vtcare.vtcareservice.models.Appointment;

import java.util.List;

public class AppointmentService {

    private AppointmentDao appointmentDao;

    public AppointmentService() throws Exception{
        appointmentDao = new AppointmentDao();
    }

    public List<Appointment> getAppointmentList(int providerId) throws Exception {
        try {
            return appointmentDao.getAppointmentsById(providerId);
        } catch (Exception e) {
            System.out.println("Fetching providers list, had an issue.");
            throw e;
        }
    }

}
