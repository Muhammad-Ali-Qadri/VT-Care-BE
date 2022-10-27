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

    private static final String FIND_ALL_PROVIDERS =
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

    public List<Provider> getProvider(long id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_PROVIDER_ID_SQL)) {
            statement.setLong(1, id);

            ResultSet res = statement.executeQuery();
            List<Provider> items = new ArrayList<>();

            if(res.next() ){
                Provider prov = new Provider(res.getObject("name", String.class) ,
                        res.getObject("email", String.class) ,
                        res.getObject("password", String.class),
                        res.getObject("gender", String.class) ,
                        res.getObject("date_of_birth", Date.class).toString(),
                        res.getObject("address", String.class),
                        res.getObject("contact", String.class),
                        res.getObject("experience", Integer.class),
                        res.getObject("specialization", String.class)
                        );
                items.add(prov);
            }
            return items;
        } catch (SQLException e) {
            throw new SQLException("Encountered problem fetching a provider ", e);
        }
    }

    public List<Provider> getProviders() throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_PROVIDERS)) {

            ResultSet res = statement.executeQuery();
            List<Provider> items = new ArrayList<>();

            while(res.next() ){
                Provider prov = new Provider(res.getObject("name", String.class) ,
                        res.getObject("email", String.class) ,
                        res.getObject("password", String.class),
                        res.getObject("gender", String.class) ,
                        res.getObject("date_of_birth", Date.class).toString(),
                        res.getObject("address", String.class),
                        res.getObject("contact", String.class),
                        res.getObject("experience", Integer.class),
                        res.getObject("specialization", String.class)
                );
                items.add(prov);
            }
            return items;
        } catch (SQLException e) {
            throw new SQLException("Encountered problem fetching providers ", e);
        }
    }


}
