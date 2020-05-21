package Database;

import java.sql.*;

public class TableHandler {
    static public Connection connection;
    static public Statement statement;
    public static void createTable() {
        final String DATABASE_URL = "jdbc:mysql://localhost:3306?serverTimezone=Europe/Moscow";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(DATABASE_URL, "ann", "Ann490");
            statement = connection.createStatement();
            statement.execute("DROP DATABASE IF EXISTS products");
            statement.execute("CREATE DATABASE products");
            statement.execute("USE products");
            statement.execute("CREATE TABLE products (id INT NOT NULL AUTO_INCREMENT, " +
                    "prodid VARCHAR(36), title VARCHAR(50), cost DOUBLE NOT NULL, PRIMARY KEY(id), UNIQUE(prodid), UNIQUE(title));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
