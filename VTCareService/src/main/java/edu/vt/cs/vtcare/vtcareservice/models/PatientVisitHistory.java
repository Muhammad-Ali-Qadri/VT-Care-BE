package edu.vt.cs.vtcare.vtcareservice.models;

/**
 * Encapsulates a single patient visit history.
 *
 * Multiple instances constitutes the combined history of the patient.
 */
public class PatientVisitHistory {
    private long id;
    private long patientId;
    private String apptDate;
    private String providerName;
    private String diagnosis;
    private String prescription;
    private String notes;

    public PatientVisitHistory(long id, long patientId, String apptDate, String providerName, String diagnosis, String prescription, String notes) {
        this.id = id;
        this.patientId = patientId;
        this.apptDate = apptDate;
        this.providerName = providerName;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public String getApptDate() {
        return apptDate;
    }

    public void setApptDate(String apptDate) {
        this.apptDate = apptDate;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
