package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String db_driver = "com.mysql.cj.jdbc.Driver";
    private static final String db_url = "jdbc:mysql://localhost:3306/db_kata_study";
    private static final String db_username = "bestuser";
    private static final String db_password = "bestuser";

    public static Connection getConnection() {

        Connection connection = null;
        try {
            Class.forName(db_driver);
            connection = DriverManager.getConnection(db_url, db_username, db_password);
            System.out.println("Connection OK");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }
    // реализуйте настройку соеденения с БД
}
