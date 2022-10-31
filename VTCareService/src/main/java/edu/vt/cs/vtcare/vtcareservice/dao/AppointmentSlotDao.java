package edu.vt.cs.vtcare.vtcareservice.dao;

import edu.vt.cs.vtcare.vtcareservice.db.VTCareJDBC;
import edu.vt.cs.vtcare.vtcareservice.models.AppointmentSlot;
import edu.vt.cs.vtcare.vtcareservice.models.Provider;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class AppointmentSlotDao {
    private Connection connection;

    public AppointmentSlotDao() throws Exception {
        connection = VTCareJDBC.getInstance().getConnection();
    }

    private static final String FIND_SCHEDULES_BY_PROVIDER =
            "SELECT * " +
                    "FROM provider_schedule where provider_id = ? order by day, provider_id, start_time;";

    public List<AppointmentSlot> getAppointmentSlotsById(long providerId) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(FIND_SCHEDULES_BY_PROVIDER)) {
            statement.setLong(1, providerId);
            ResultSet res = statement.executeQuery();
            List<AppointmentSlot> appointmentSlotList = new ArrayList<>();

            while(res.next() ){
                appointmentSlotList.add( parseAppointmentSlots(res) );
            }
            return appointmentSlotList;
        } catch (SQLException e) {
            System.out.println(e.getStackTrace() );
            throw e;
        }

    }

    public AppointmentSlot parseAppointmentSlots(ResultSet resultSet) throws SQLException{
        long id = resultSet.getInt("id");
        long providerId = resultSet.getInt("provider_id");
        int day = resultSet.getInt("day");
        String startTime = resultSet.getTime("start_time").toString();
        String endTime = resultSet.getTime("end_time").toString();
        int slotDuration = resultSet.getInt("slot_duration");

        return new AppointmentSlot(id, providerId, day, startTime, endTime, slotDuration);
    }
}
