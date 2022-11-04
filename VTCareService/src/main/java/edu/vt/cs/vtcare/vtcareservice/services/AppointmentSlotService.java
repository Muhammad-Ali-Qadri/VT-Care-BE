package edu.vt.cs.vtcare.vtcareservice.services;

import edu.vt.cs.vtcare.vtcareservice.dao.AppointmentSlotDao;
import edu.vt.cs.vtcare.vtcareservice.models.AppointmentSlot;

import java.util.List;

public class AppointmentSlotService {
    private AppointmentSlotDao appointmentSlotDao;

    public AppointmentSlotService() throws Exception{
        appointmentSlotDao = new AppointmentSlotDao();
    }

    public List<AppointmentSlot> getAppointmentList(long providerId) throws Exception {
        try {
            return appointmentSlotDao.getAppointmentSlotsById(providerId);
        } catch (Exception e) {
            System.out.println("Fetching providers list, had an issue.");
            throw e;
        }
    }

}
