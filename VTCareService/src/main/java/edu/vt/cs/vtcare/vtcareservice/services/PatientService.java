package edu.vt.cs.vtcare.vtcareservice.services;


import edu.vt.cs.vtcare.vtcareservice.dao.PatientDao;
import edu.vt.cs.vtcare.vtcareservice.models.Insurance;
import edu.vt.cs.vtcare.vtcareservice.models.Patient;

/**
 * Contains the business logic for patients
 */
public class PatientService {

    private PatientDao patientDao;

    public PatientService() throws Exception {
        patientDao = new PatientDao();

    }

    /**
     * Persists the given patient entity to database.
     *
     * @param patient the patient to be persisted.
     * @return patient with the generated ID.
     */
    public Patient persistPatient(Patient patient) throws Exception {
        //persist the insurance first, get insurance with insurance Id and
        //then persist the patient with the insurance id foreign key.
        InsuranceService insuranceService = new InsuranceService();
        Insurance insurance = insuranceService.persistInsurance(patient.getInsurance());
        patient.setInsurance(insurance);
        long patientId = patientDao.persistPatient(patient);
        patient.setId(patientId);
        return patient;
    }

    /**
     * Finds a patient in the database against the given Id
     * @param patientId
     * @return
     */
    public Patient findPatientById(long patientId) throws Exception {
        InsuranceService insuranceService = new InsuranceService();
        PatientVisitHistoryService patientVisitHistoryService = new PatientVisitHistoryService();
        AppointmentService appointmentService = new AppointmentService();

        Patient patient = patientDao.findPatientById(patientId);
        patient.setUpcomingAppointments(appointmentService.getAppointmentListByPatientId(patientId));
        patient.setInsurance(insuranceService.findInsuranceById(patient.getInsurance().getId()));
        patient.setHistory(patientVisitHistoryService.findHistoryByPatientId(patientId));

        return patient;
    }
}
