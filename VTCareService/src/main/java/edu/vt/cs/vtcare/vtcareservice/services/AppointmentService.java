package edu.vt.cs.vtcare.vtcareservice.services;

import edu.vt.cs.vtcare.meetingservices.MeetingService;
import edu.vt.cs.vtcare.meetingservices.models.MeetingDetails;
import edu.vt.cs.vtcare.meetingservices.models.MeetingResponse;
import edu.vt.cs.vtcare.meetingservices.services.ZoomMeetingService;
import edu.vt.cs.vtcare.vtcareservice.dao.AppointmentDao;
import edu.vt.cs.vtcare.vtcareservice.models.Appointment;
import edu.vt.cs.vtcare.vtcareservice.models.AppointmentStatus;
import edu.vt.cs.vtcare.vtcareservice.models.Patient;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AppointmentService {
    private final AppointmentDao appointmentDao;
    private static final String PASSWORD = "123456";

    public AppointmentService() throws Exception {
        appointmentDao = new AppointmentDao();
    }

    /**
     *
     */
    public Appointment scheduleAppointment(Appointment appointment) throws IOException {
        MeetingResponse res = getMeetingResponse(appointment);
        appointment.setUrl(res.getUrl());
        appointment.setExternalId(res.getId());
        appointment.setId(appointmentDao.persistAppointment(appointment));
        return appointment;
    }

    private MeetingResponse getMeetingResponse(Appointment appointment) throws IOException {
        String agenda = String.format("Meeting for %s, with Dr. %s",
                appointment.getPatientName(), appointment.getProviderName());

        MeetingDetails meetingDetails = new MeetingDetails(agenda,
                appointment.getDuration(), PASSWORD,
                appointment.getProviderName(), appointment.getProviderEmail()
                , appointment.getPatientName(), appointment.getPatientEmail()
                , appointment.getDateTimeString(), appointment.getTime());

        MeetingService meetingService = new ZoomMeetingService();

        return meetingService.createMeeting(meetingDetails);
    }

    public List<Appointment> getAppointmentList(int providerId) throws Exception {
        try {
            return appointmentDao.getAppointmentsById(providerId);
        } catch (Exception e) {
            System.out.println("Fetching providers list, had an issue.");
            throw e;
        }
    }

    /**
     * fetches the list of appointments for given patient id
     * @param patientId
     * @return
     * @throws Exception
     */
    public List<Appointment> getAppointmentListByPatientId(long patientId) throws Exception {
        try {
            return appointmentDao.getAppointmentsByPatientId(patientId);
        } catch (Exception e) {
            System.out.println("Encountered an issue while fetching upcoming appointments for patient.");
            throw e;
        }
    }

    /**
     * Update appointment status.
     * @param id id of appointment to change the status
     * @param status updated status
     * @return
     * @throws SQLException
     */
    public void updateAppointmentStatus(long id, AppointmentStatus status) throws Exception {
        try {
            appointmentDao.updateAppointmentStatus(id, status);
        } catch (Exception e) {
            System.out.println("Encountered an issue while updating " +
                               "appointment status");
            throw e;
        }
    }
}