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

    private static final String FIND_BY_PROVIDER_ID_SQL =
            "SELECT * " +
                    "FROM providers " +
                    "WHERE id = ?";

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
     * @param id of provider we're looking for
     * @return list of providers with only 1 entry, when providerID is known
     * @throws SQLException on SQL errors like table not existing
     */
    public List<Provider> getProvider(long id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_PROVIDER_ID_SQL)) {
            statement.setLong(1, id);

            ResultSet res = statement.executeQuery();
            List<Provider> items = new ArrayList<>();

            if(res.next() ){
                extractProvider(res, items);
            }
            return items;
        } catch (SQLException e) {
            throw new SQLException("Encountered problem fetching a provider ", e);
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
            List<Provider> items = new ArrayList<>();

            while(res.next() ){
                extractProvider(res, items);
            }
            return items;
        } catch (SQLException e) {
            throw new SQLException("Encountered problem fetching providers ", e);
        }
    }

    /**
     *
     * @param res resultset to iterate through, used to construct provider
     * @param items data structure used to contain providers
     * @throws SQLException
     */
    private void extractProvider(ResultSet res, List<Provider> items) throws SQLException {
        Provider prov = new Provider(res.getObject("name", String.class) ,
                res.getObject("email", String.class) ,
                "",// probably don't want to leak a provider's password
                res.getObject("gender", String.class) ,
                res.getObject("date_of_birth", Date.class).toString(),
                res.getObject("address", String.class),
                res.getObject("contact", String.class),
                res.getObject("experience", Integer.class),
                res.getObject("specialization", String.class)
        );
        prov.setProviderId(res.getObject("id", Integer.class) );
        items.add(prov);
    }


}
