package edu.vt.cs.vtcare.vtcareservice.dao;

import edu.vt.cs.vtcare.vtcareservice.db.VTCareJDBC;
import edu.vt.cs.vtcare.vtcareservice.models.Provider;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ProviderDao {
    private Connection connection;

    public ProviderDao() throws Exception {
        connection = VTCareJDBC.getInstance().getConnection();
    }

    private static final String CREATE_PROVIDER_SQL =
            "INSERT INTO providers (name, email, password, gender, date_of_birth, address, contact, specialization, experience)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String FIND_ALL_PROVIDERS_SQL =
            "SELECT * " +
                    "FROM providers";

    public long persistProvider(Provider provider) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_PROVIDER_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, provider.getName());
            statement.setString(2, provider.getEmail());
            statement.setString(3, provider.getPassword());
            statement.setString(4, provider.getGender());
            statement.setDate(5, java.sql.Date.valueOf(provider.getDate()));
            statement.setString(6, provider.getAddress());
            statement.setString(7, provider.getContact());
            statement.setString(8, provider.getSpecialization());
            statement.setInt(9, provider.getYearsOfExperience());
            statement.executeUpdate();
            long providerId = -1;
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                providerId = rs.getLong(1);
            }
            return providerId;
        } catch (SQLException e) {
            throw new SQLException("Encountered problem creating a new provider ", e);
        }
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
                providers.add( extractProvider(res) );
            }
            return providers;
        } catch (SQLException e) {
            System.out.println(e.getStackTrace() );
            throw e;
        }
    }

    /**
     *
     * @param res resultset to iterate through, used to construct provider
     * @throws SQLException
     */
    private Provider extractProvider(ResultSet res) throws SQLException {
        Provider prov = new Provider(res.getString("name") ,
                res.getString("email") ,
                "",// probably don't want to leak a provider's password
                res.getString("gender") ,
                res.getDate("date_of_birth").toString(), //initial model has this as a string
                res.getString("address"),
                res.getString("contact"),
                res.getInt("experience"),
                res.getString("specialization")
        );
        prov.setProviderId(res.getInt("id") );
        return prov;
    }


}
