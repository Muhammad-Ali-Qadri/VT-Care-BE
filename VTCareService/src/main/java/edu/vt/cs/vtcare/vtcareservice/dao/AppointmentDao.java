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
            "time, duration, is_video_appt, url, external_id, status)" +
            "SELECT ?, ?, ?, ?, ?, ?, ?, ?, ? " +
            "WHERE NOT EXISTS (SELECT 1 FROM appointments WHERE provider_id =" +
            " " +
            "? AND date = ? AND time = ?) AND NOT EXISTS (SELECT 1 " +
            "FROM appointments WHERE patient_id = ? AND date = ? AND " +
            "time = ?)";

    private static final String FIND_APPOINTMENTS_BY_PROVIDER =
            "SELECT appt.*, pt.name 'patient_name', pt.email 'patient_email'," +
            " pr.name 'provider_name', pr.email 'provider_email'" +
            " FROM (appointments appt INNER JOIN patients pt ON pt.id = appt" +
            ".patient_id)" +
            " INNER JOIN providers pr ON pr.id = appt.provider_id" +
            " WHERE appt.provider_id = ? ORDER BY date desc, time desc;";

    private static final String FIND_APPOINTMENTS_BY_PATIENT =
            "SELECT appt.*, pt.name 'patient_name', pt.email 'patient_email'," +
            " pr.name 'provider_name', pr.email 'provider_email'" +
            " FROM (appointments appt INNER JOIN patients pt ON pt.id = appt" +
            ".patient_id)" +
            " INNER JOIN providers pr ON pr.id = appt.provider_id" +
            " WHERE appt.patient_id = ? ORDER BY date desc, time desc;";

    private static final String FIND_APPOINTMENT =
            "SELECT appt.*, pt.name 'patient_name', pt.email 'patient_email'," +
            " pr.name 'provider_name', pr.email 'provider_email'" +
            " FROM (appointments appt INNER JOIN patients pt ON pt.id = appt" +
            ".patient_id)" +
            " INNER JOIN providers pr ON pr.id = appt.provider_id" +
            " WHERE appt.id = ? ORDER BY date desc, time desc;";

    private static final String UPDATE_APPOINTMENT_STATUS =
            "UPDATE appointments " +
            "SET status = ? " +
            "WHERE id = ?;";

    private static final String UPDATE_APPOINTMENT_SCHEDULE =
            "UPDATE appointments " +
            "SET date = ? " +
            ", time = ? " +
            "WHERE id = ?;";

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
            statement.setString(8, appointment.getExternalId());
            statement.setString(9, appointment.getStatus().toString());
            statement.setInt(10, appointment.getProviderId());
            statement.setDate(11,
                    java.sql.Date.valueOf(appointment.getDate()));
            statement.setString(12, appointment.getTime());
            statement.setInt(13, appointment.getPatientId());
            statement.setDate(14,
                    java.sql.Date.valueOf(appointment.getDate()));
            statement.setString(15, appointment.getTime());

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

    /**
     * Fetches appointments for a given patient Id.
     * @param patientId
     * @return
     * @throws SQLException
     */
    public List<Appointment> getAppointmentsByPatientId(long patientId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(FIND_APPOINTMENTS_BY_PATIENT)) {
            statement.setLong(1, patientId);
            ResultSet res = statement.executeQuery();
            List<Appointment> appointmentList = new ArrayList<>();

            while (res.next()) {
                appointmentList.add(parseAppointments(res));
            }
            return appointmentList;
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
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
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_APPOINTMENT_STATUS)) {
            statement.setString(1, status.toString());
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
            throw e;
        }
    }

    /**
     * Update appointment time.
     * @param appointment object with new schedule
     * @return
     * @throws SQLException
     */
    public void updateAppointmentSchedule(Appointment appointment) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_APPOINTMENT_SCHEDULE)) {
            statement.setDate(1, java.sql.Date.valueOf(appointment.getDate()));
            statement.setString(2, appointment.getTime());
            statement.setLong(3, appointment.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
            throw e;
        }
    }

    /**
     * Get appointment by  id
     * @param id id of appointment to get its object
     * @return String external id
     * @throws SQLException
     */
    public Appointment getAppointmentById(long id) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement(FIND_APPOINTMENT)) {
            statement.setLong(1, id);
            ResultSet res = statement.executeQuery();
            List<Appointment> appointmentList = new ArrayList<>();

            while (res.next()) {
                appointmentList.add(parseAppointments(res));
            }

            return appointmentList.get(0);
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
        String externalId = resultSet.getString("external_id");
        String status = resultSet.getString("status");
        String patientEmail = resultSet.getString("patient_email");
        String patientName = resultSet.getString("patient_name");
        String providerEmail = resultSet.getString("provider_email");
        String providerName = resultSet.getString("provider_name");

        return new Appointment(id, providerId, patientId, duration, isVideo,
                providerName, providerEmail, patientName, patientEmail,
                date, time, url, externalId, AppointmentStatus.valueOf(status));
    }
}
