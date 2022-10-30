package edu.vt.cs.vtcare.vtcareservice.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class VTCareJDBC {
    private static VTCareJDBC vtCareJDBC;
    private Connection con = null;

    private VTCareJDBC() {

    }

    public static VTCareJDBC getInstance() {
        if (vtCareJDBC == null) {
            vtCareJDBC = new VTCareJDBC();
        }
        return vtCareJDBC;
    }

    public Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/vtcare";
        String user = "root";
        String password = "Rootpw4123!";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Successfully secured connection to VTCareDB.");
            return con;
        } catch (SQLException e) {
            System.out.println("An exception occurred while creating DB Connection." + e);
            throw new Exception("An exception occurred while creating DB Connection.");
        }
    }

}
