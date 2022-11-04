package edu.vt.cs.vtcare.vtcareservice.dao;

import edu.vt.cs.vtcare.vtcareservice.db.VTCareJDBC;
import edu.vt.cs.vtcare.vtcareservice.models.Appointment;

import java.sql.*;
import java.time.ZoneId;

public class AppointmentDao {
    private final Connection connection;

    public AppointmentDao() throws Exception {
        connection = VTCareJDBC.getInstance().getConnection();
    }

    private static final String CREATE_APPOINTMENT_SQL =
            "INSERT INTO appointments (provider_id, patient_id, date, " +
            "time, duration, is_video_appt, url, status)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String FIND_PROVIDER_BY_ID_SQL =
            "SELECT * FROM providers where id = ?";

    /**
     * Executes database query to persist the given appointment into the
     * database.
     * @param appointment appointment entity.
     * @return generated appointment Id.
     * @throws SQLException
     */
    public long persistAppointment(Appointment appointment)  {
        long appointmentId = -1;
        try (PreparedStatement statement = connection.prepareStatement(CREATE_APPOINTMENT_SQL,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, appointment.getProviderId());
            statement.setInt(2, appointment.getPatientId());
            statement.setDate(3,
                    java.sql.Date.valueOf(appointment.getDate().toInstant().
                            atZone(ZoneId.systemDefault()).toLocalDate()));
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
        } catch (SQLException e) {
            System.out.println("Encountered problem creating a new " +
                               "appointment " + e);
        }

        return appointmentId;
    }
}
