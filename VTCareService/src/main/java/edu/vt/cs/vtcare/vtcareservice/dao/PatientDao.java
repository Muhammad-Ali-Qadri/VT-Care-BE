package edu.vt.cs.vtcare.vtcareservice.dao;

import edu.vt.cs.vtcare.vtcareservice.db.VTCareJDBC;
import edu.vt.cs.vtcare.vtcareservice.models.Insurance;
import edu.vt.cs.vtcare.vtcareservice.models.Patient;

import java.sql.*;

/**
 * Carries out the CRUD operations for Patient entity.
 */
public class PatientDao {

    private Connection connection;

    public PatientDao() throws Exception {
        connection = VTCareJDBC.getInstance().getConnection();
    }

    private static final String CREATE_PATIENT_SQL =
            "INSERT INTO patients (name, email, password, gender, date_of_birth, address, contact, employer, designation, insurance_id)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String FIND_PATIENT_BY_ID_SQL =
            "SELECT * FROM patients where id = ?";

    /**
     * Executes database query to persist the given patient into the database.
     * @param patient patient entity
     * @return generated patient Id.
     * @throws SQLException
     */
    public long persistPatient(Patient patient)  {
        long patientId = -1;
        try (PreparedStatement statement = connection.prepareStatement(CREATE_PATIENT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, patient.getName());
            statement.setString(2, patient.getEmail());
            statement.setString(3, patient.getPassword());
            statement.setString(4, patient.getGender());
            statement.setDate(5, java.sql.Date.valueOf(patient.getDateOfBirth()));
            statement.setString(6, patient.getAddress());
            statement.setString(7, patient.getContact());
            statement.setString(8, patient.getEmployer());
            statement.setString(9, patient.getDesignation());
            statement.setLong(10, patient.getInsurance().getId());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                patientId = rs.getLong(1);
            }
        } catch (SQLException e) {
            System.out.println("Encountered problem creating a new patient " + e);
        }
        return patientId;
    }

    /**
     * Finds a patient by Id/
     * @param patientId
     * @return the found entity from the database.
     */
    public Patient findPatientById(long patientId) {
        Patient patient = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_PATIENT_BY_ID_SQL)) {
            statement.setLong(1, patientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    patient = parsePatient(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Encountered problem while fetching patient. Id = " + patientId + "\n" + e);
        }
        return patient;
    }

    /**
     * Parses the ResultSet object to build a patient entity.
     * @param resultSet
     * @return Patient entity
     */
    private Patient parsePatient(ResultSet resultSet) throws SQLException {

        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String gender = resultSet.getString("gender");
        String dob = resultSet.getDate("date_of_birth").toString();
        String address = resultSet.getString("address");
        String contact = resultSet.getString("contact");
        String employer = resultSet.getString("employer");
        String designation = resultSet.getString("designation");
        long insuranceId = resultSet.getInt("insurance_id");
        Insurance insurance = new Insurance();
        insurance.setId(insuranceId);

        return new Patient(id, name, email, password, gender, dob, address, contact, employer, designation, insurance);
    }
}
