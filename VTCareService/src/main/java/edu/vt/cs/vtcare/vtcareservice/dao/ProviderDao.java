package edu.vt.cs.vtcare.vtcareservice.dao;

import edu.vt.cs.vtcare.vtcareservice.db.VTCareJDBC;
import edu.vt.cs.vtcare.vtcareservice.models.Provider;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Holds the code that interacts with the database and performs CRUD operations on
 * the Provider entity.
 */
public class ProviderDao {
    private Connection connection;

    public ProviderDao() throws Exception {
        connection = VTCareJDBC.getInstance().getConnection();
    }

    private static final String CREATE_PROVIDER_SQL =
            "INSERT INTO providers (name, email, password, gender, date_of_birth, address, contact, specialization, experience)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String FIND_PROVIDER_BY_ID_SQL =
            "SELECT * FROM providers where id = ?";

    private static final String FIND_ALL_PROVIDERS_SQL =
            "SELECT * FROM providers";

    /**
     * Executes database query to persist the given provider into the database.
     * @param provider provider entity
     * @return generated provider Id.
     * @throws SQLException
     */
    public long persistProvider(Provider provider)  {
        long providerId = -1;
        try (PreparedStatement statement = connection.prepareStatement(CREATE_PROVIDER_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, provider.getName());
            statement.setString(2, provider.getEmail());
            statement.setString(3, provider.getPassword());
            statement.setString(4, provider.getGender());
            statement.setDate(5, java.sql.Date.valueOf(provider.getDateOfBirth()));
            statement.setString(6, provider.getAddress());
            statement.setString(7, provider.getContact());
            statement.setString(8, provider.getSpecialization());
            statement.setInt(9, provider.getYearsOfExperience());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                providerId = rs.getLong(1);
            }
        } catch (SQLException e) {
            System.out.println("Encountered problem creating a new provider " + e);
        }
        return providerId;
    }

    /**
     * Finds a provider by Id/
     * @param providerId
     * @return the found entity from the database.
     */
    public Provider findProviderById(long providerId) {
        Provider provider = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_PROVIDER_BY_ID_SQL)) {
            statement.setLong(1, providerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    provider = parseProvider(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Encountered problem while fetching provider. Id = " + providerId + "\n" + e);
        }
        return provider;
    }

    /**
     * Parses the ResultSet object to build a provider entity.
     * @param resultSet
     * @return Provider entity
     */
    private Provider parseProvider(ResultSet resultSet) throws SQLException {

        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String gender = resultSet.getString("gender");
        String dob = resultSet.getDate("date_of_birth").toString();
        String address = resultSet.getString("address");
        String contact = resultSet.getString("contact");
        String specialization = resultSet.getString("specialization");
        int experience = resultSet.getInt("experience");

        return new Provider(id, name, email, password, gender, dob, address, contact, specialization, experience);
    }

    /**
     *
     * @return ListOfAllProviders
     * @throws SQLException When query is malformed, or other SQL related issues.
     */
    public List<Provider> getProviders() throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_PROVIDERS_SQL)) {

            ResultSet res = statement.executeQuery();
            List<Provider> providers = new ArrayList<>();

            while(res.next() ){
                providers.add( parseProvider(res) );
            }
            return providers;
        } catch (SQLException e) {
            System.out.println(e.getStackTrace() );
            throw e;
        }
    }
}
