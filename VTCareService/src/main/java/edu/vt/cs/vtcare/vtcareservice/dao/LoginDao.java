package edu.vt.cs.vtcare.vtcareservice.dao;
import edu.vt.cs.vtcare.vtcareservice.db.VTCareJDBC;

import java.sql.*;

public class LoginDao {
    private final Connection connection;

    private static final String LOGIN =
            "SELECT id FROM %s WHERE email = ? AND password = ?";

    public LoginDao() throws Exception {
        connection = VTCareJDBC.getInstance().getConnection();
    }

    public int validateLogin(String email, String password,
                              boolean isProviderLogin) throws SQLException {

        String finalString = String.format(LOGIN, (isProviderLogin) ?
                "providers" : "patients");

        try (PreparedStatement statement = connection.prepareStatement(finalString)) {

            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet res = statement.executeQuery();

            if(res.next()){
                return res.getInt(1);
            }

            return 0;
        } catch (SQLException e) {
            throw new SQLException("Encountered problem creating a new provider ", e);
        }
    }

}
