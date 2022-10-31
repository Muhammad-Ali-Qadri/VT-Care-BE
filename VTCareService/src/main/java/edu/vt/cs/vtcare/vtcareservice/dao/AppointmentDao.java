package edu.vt.cs.vtcare.vtcareservice.dao;

import edu.vt.cs.vtcare.vtcareservice.db.VTCareJDBC;
import edu.vt.cs.vtcare.vtcareservice.models.Appointment;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class AppointmentDao {
    private Connection connection;

    public AppointmentDao() throws Exception {
        connection = VTCareJDBC.getInstance().getConnection();
    }

    private static final String FIND_APPOINTMENTS_BY_PROVIDER =
            "SELECT * " +
                    "FROM appointments where provider_id = ? order by date, time;";

    public List<Appointment> getAppointmentsById(int providerId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(FIND_APPOINTMENTS_BY_PROVIDER)) {
            statement.setInt(1, providerId);
            ResultSet res = statement.executeQuery();
            List<Appointment> appointmentSlotList = new ArrayList<>();

            while (res.next()) {
                appointmentSlotList.add(parseAppointments(res));
            }
            return appointmentSlotList;
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
            throw e;
        }
    }

    public Appointment parseAppointments(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("id");
        int providerId = resultSet.getInt("provider_id");
        int patientId = resultSet.getInt("patient_id");
        String date = resultSet.getDate("date").toString();
        String time = resultSet.getTime("time").toString();
        int duration = resultSet.getInt("duration");
        boolean is_video = resultSet.getBoolean("is_video_appt");
        String url = resultSet.getString("url");
        String status = resultSet.getString("status");

        return new Appointment(id, providerId, patientId, date, time, duration, is_video, url, status);
    }
}
