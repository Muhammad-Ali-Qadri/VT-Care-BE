package edu.vt.cs.vtcare.vtcareservice.dao;

import edu.vt.cs.vtcare.vtcareservice.db.VTCareJDBC;
import edu.vt.cs.vtcare.vtcareservice.models.Insurance;

import java.sql.*;

/**
 * Interacts with the DB to perform CRUD on Insurance Entities
 */
public class InsuranceDao {

    private Connection connection;

    public InsuranceDao() throws Exception {
        connection = VTCareJDBC.getInstance().getConnection();
    }

    private static final String CREATE_INSURANCE_SQL =
            "INSERT INTO insurances (policy_no, network_id)" +
                    "VALUES (?, ?)";

    private static final String FIND_INSURANCE_BY_ID_SQL =
            "SELECT * FROM insurances where id = ?";

    /**
     * Executes database query to persist the given insurance into the database.
     * @param insurance patient entity
     * @return generated insurance Id.
     * @throws SQLException
     */
    public long persistInsurance(Insurance insurance)  {
        long insuranceId = -1;
        try (PreparedStatement statement = connection.prepareStatement(CREATE_INSURANCE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, insurance.getPolicyNo());
            statement.setLong(2, insurance.getNetworkId());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                insuranceId = rs.getLong(1);
            }
        } catch (SQLException e) {
            System.out.println("Encountered problem creating a new insurance " + e);
        }
        return insuranceId;
    }

    /**
     * Finds a insurance by Id
     * @param insuranceId
     * @return the found entity from the database.
     */
    public Insurance findInsuranceById(long insuranceId) {
        Insurance insurance = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_INSURANCE_BY_ID_SQL)) {
            statement.setLong(1, insuranceId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    insurance = parseInsurance(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Encountered problem while fetching insurance. Id = " + insuranceId + "\n" + e);
        }
        return insurance;
    }

    /**
     * Parses the ResultSet object to build a insurance entity.
     * @param resultSet
     * @return Insurance entity
     */
    private Insurance parseInsurance(ResultSet resultSet) throws SQLException {

        long id = resultSet.getLong("id");
        long policyNo = resultSet.getLong("policy_no");
        long networkId = resultSet.getLong("network_id");

        return new Insurance(id, policyNo, networkId);
    }

}
