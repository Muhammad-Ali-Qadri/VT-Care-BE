package edu.vt.cs.vtcare.vtcareservice.dao;

import edu.vt.cs.vtcare.vtcareservice.db.VTCareJDBC;
import edu.vt.cs.vtcare.vtcareservice.models.PatientVisitHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Carries out the CRUD operations for PatientVisitHistory entity.
 */
public class PatientVisitHistoryDao {

    private Connection connection;

    public PatientVisitHistoryDao() throws Exception {
        connection = VTCareJDBC.getInstance().getConnection();
    }

    private static final String FIND_HISTORY_BY_PATIENT_ID_SQL =
            "SELECT * FROM patient_visit_histories where patient_id = ?";

    /**
     * Finds a visit history by patientId
     * @param patientId
     * @return the found entity from the database.
     */
    public List<PatientVisitHistory> findHistoryByPatientId(long patientId) {
        List<PatientVisitHistory> patientVisitHistory = new ArrayList<PatientVisitHistory>();
        try (PreparedStatement statement = connection.prepareStatement(FIND_HISTORY_BY_PATIENT_ID_SQL)) {
            statement.setLong(1, patientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    patientVisitHistory.add(parseHistory(resultSet));
                }
            }
        } catch (SQLException e) {
            System.out.println("Encountered problem while fetching patient history. Id = " + patientId + "\n" + e);
        }
        return patientVisitHistory;
    }

    /**
     * Parses the ResultSet object to build a PatientVisitHistory entity.
     * @param resultSet
     * @return PatientVisitHistory entity
     */
    private PatientVisitHistory parseHistory(ResultSet resultSet) throws SQLException {

        long id = resultSet.getLong("id");
        long patientId = resultSet.getLong("patient_id");
        String apptDate = resultSet.getDate("appointment_date").toString();
        String providerName = resultSet.getString("provider_name");
        String diagnosis = resultSet.getString("diagnosis");
        String prescription = resultSet.getString("prescription");
        String notes = resultSet.getString("notes");

        return new PatientVisitHistory(id, patientId, apptDate, providerName, diagnosis, prescription, notes);
    }
}
