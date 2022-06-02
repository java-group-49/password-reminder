package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbManagement {
    public static Connection getConnection() throws SQLException {
        String className = "org.postgresql.Driver";
        String url = "jdbc:postgresql://database-2022.ct3islcjkcto.eu-central-1.rds.amazonaws.com:5432/testdb";
        String userName = "postgres";
        String password = "postgres";

        try {
            Class.forName(className);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return DriverManager.getConnection(url, userName, password);
    }
}
