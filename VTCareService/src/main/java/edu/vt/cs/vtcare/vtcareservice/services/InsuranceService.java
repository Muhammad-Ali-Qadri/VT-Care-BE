package edu.vt.cs.vtcare.vtcareservice.services;

import edu.vt.cs.vtcare.vtcareservice.dao.InsuranceDao;
import edu.vt.cs.vtcare.vtcareservice.models.Insurance;

import java.sql.SQLException;

public class InsuranceService {
    private InsuranceDao insuranceDao;

    public InsuranceService() throws Exception {
        insuranceDao = new InsuranceDao();
    }

    /**
     * Persists the given insurance entity to database.
     *
     * @param insurance the insurance to be persisted.
     * @return Insurance with the generated ID.
     */
    public Insurance persistInsurance(Insurance insurance) throws SQLException {
        long insuranceId = insuranceDao.persistInsurance(insurance);
        insurance.setId(insuranceId);
        return insurance;
    }

    /**
     * Fetches insurance with given id
     *
     * @param insuranceId
     * @return Insurance
     */
    public Insurance findInsuranceById(long insuranceId) throws SQLException {
       return insuranceDao.findInsuranceById(insuranceId);
    }
}
