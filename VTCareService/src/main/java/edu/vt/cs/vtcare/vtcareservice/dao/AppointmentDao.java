package edu.vt.cs.vtcare.vtcareservice.dao;

import edu.vt.cs.vtcare.vtcareservice.db.VTCareJDBC;
import edu.vt.cs.vtcare.vtcareservice.models.Appointment;
import edu.vt.cs.vtcare.vtcareservice.models.AppointmentStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class AppointmentDao {
    private final Connection connection;

    public AppointmentDao() throws Exception {
        connection = VTCareJDBC.getInstance().getConnection();
    }

    private static final String CREATE_APPOINTMENT_SQL =
            "INSERT INTO appointments (provider_id, patient_id, date, " +
            "time, duration, is_video_appt, url, status)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String FIND_APPOINTMENTS_BY_PROVIDER =
            "SELECT * " +
            "FROM appointments where provider_id = ? order by date, time;";

    /**
     * Executes database query to persist the given appointment into the
     * database.
     * @param appointment appointment entity.
     * @return generated appointment Id.
     * @throws SQLException
     */
    public long persistAppointment(Appointment appointment) {
        long appointmentId = -1;
        try (PreparedStatement statement = connection.prepareStatement(CREATE_APPOINTMENT_SQL,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, appointment.getProviderId());
            statement.setInt(2, appointment.getPatientId());
            statement.setDate(3,
                    java.sql.Date.valueOf(appointment.getDate()));
            statement.setString(4, appointment.getTime());
            statement.setInt(5, appointment.getDuration());
            statement.setBoolean(6, appointment.isVideoAppointment());
            statement.setString(7, appointment.getUrl());
            statement.setString(8, appointment.getStatus().toString());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                appointmentId = rs.getLong(1);
            }
        }
        catch (SQLException e) {
            System.out.println("Encountered problem creating a new " +
                               "appointment " + e);
        }

        return appointmentId;
    }

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
        LocalDate date = resultSet.getDate("date").toLocalDate();
        String time = resultSet.getTime("time").toString();
        int duration = resultSet.getInt("duration");
        boolean isVideo = resultSet.getBoolean("is_video_appt");
        String url = resultSet.getString("url");
        String status = resultSet.getString("status");

        return new Appointment(id, providerId, patientId, duration, isVideo,
                "", "", "", "",
                date, time, url, AppointmentStatus.valueOf(status));
    }
}
