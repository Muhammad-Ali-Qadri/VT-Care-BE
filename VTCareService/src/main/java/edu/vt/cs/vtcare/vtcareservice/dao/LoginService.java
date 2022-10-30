package edu.vt.cs.vtcare.vtcareservice.dao;
import edu.vt.cs.vtcare.vtcareservice.db.VTCareJDBC;

import java.sql.*;

/***
 * TODO: Can be changed to respective entity logins in future releases. Can
 * work with current solution without worry.
 */

public class LoginService {
    private final Connection connection;

    private static final String LOGIN_SQL =
            "SELECT id FROM %s WHERE email = ? AND password = ?";

    public LoginService() throws Exception {
        connection = VTCareJDBC.getInstance().getConnection();
    }

    /***
     * Validates the input credentials.
     * @param email: The email address of the user to login
     * @param password: The password of the user to login
     * @param isProviderLogin: If this login was attempted by a provider or
     *                       a patient
     * @return The user id, or 0 in case of invalid credentials.
     * @throws SQLException
     */
    public int validateLogin(String email, String password,
                              boolean isProviderLogin) throws SQLException {

        String loginQuery = String.format(LOGIN_SQL, (isProviderLogin) ?
                "providers" : "patients");

        try (PreparedStatement statement = connection.prepareStatement(loginQuery)) {

            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet res = statement.executeQuery();

            if(res.next()){
                return res.getInt("id");
            }

            return 0;
        } catch (SQLException e) {
            throw new SQLException("Encountered problem creating a new provider ", e);
        }
    }

}
