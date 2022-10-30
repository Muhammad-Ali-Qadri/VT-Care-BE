package edu.vt.cs.vtcare.vtcareservice.services;

import java.sql.SQLException;

public class LoginService {
    private final edu.vt.cs.vtcare.vtcareservice.dao.LoginService loginService;

    public LoginService() throws Exception {
        loginService = new edu.vt.cs.vtcare.vtcareservice.dao.LoginService();
    }

    /**
     * Verify if an authorized user is trying to log in to the application.
     * @param email: Email to be verified
     * @param password: The password to be verified
     * @param isProviderLogin: Make sure that we verify from either providers
     *                      table or the patients table
     * @return if email and password is valid or not.
     */
    public int login(String email, String password,
                                    boolean isProviderLogin) throws SQLException {
        return loginService.validateLogin(email, password,
                isProviderLogin);
    }
}
