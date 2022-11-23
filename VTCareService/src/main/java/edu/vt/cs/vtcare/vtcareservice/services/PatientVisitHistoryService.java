package edu.vt.cs.vtcare.vtcareservice.services;

import edu.vt.cs.vtcare.vtcareservice.dao.PatientVisitHistoryDao;
import edu.vt.cs.vtcare.vtcareservice.models.PatientVisitHistory;

import java.sql.SQLException;
import java.util.List;

/**
 * Contains the business logic for Patient Visit History
 */
public class PatientVisitHistoryService {
    private PatientVisitHistoryDao patientVisitHistoryDao;

    public PatientVisitHistoryService() throws Exception {
        patientVisitHistoryDao = new PatientVisitHistoryDao();
    }

    /**
     * fetches the patient history for a given patient ID
     *
     * @param patientId the provider to be persisted.
     * @return List of patient history objects
     */
    public List<PatientVisitHistory> findHistoryByPatientId(long patientId) throws SQLException {
        return patientVisitHistoryDao.findHistoryByPatientId(patientId);
    }

    /**
     * Persists patient's history into the database.
     * @param patientVisitHistory
     * @return
     */
    public PatientVisitHistory persistPatientHistory(PatientVisitHistory patientVisitHistory) {
        long historyId =  patientVisitHistoryDao.persistPatientHistory(patientVisitHistory);
        patientVisitHistory.setId(historyId);
        return patientVisitHistory;
    }
}
