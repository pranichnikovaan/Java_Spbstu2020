import java.sql.*;
import java.util.Random;
import java.util.UUID;

public class Main {
    private static void createRecords(Connection connection, int n, int id) throws SQLException {
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < n; i++) {
            String prod = String.valueOf(UUID.randomUUID());
            String prod_name = "product" + (i + 1);
            double price = rand.nextDouble() * 1000;
            String stm = "INSERT INTO products (id, prodid, title, cost) " + "VALUES (?, ?, ?, ?)";
            PreparedStatement prepstm = connection.prepareStatement(stm);
            prepstm.setInt(1, id);
            prepstm.setString(2, prod);
            prepstm.setString(3, prod_name);
            prepstm.setDouble(4, price);
            prepstm.execute();
            prepstm.close();
        }
        System.out.println("Table is ready");
    }
    public static void main(String[] args) {
        Connection connection;
        Statement statement;
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
        statement.execute("CREATE TABLE products (id INT NOT NULL AUTO_INCREMENT, "+
                "prodid VARCHAR(36), title VARCHAR(50), cost DOUBLE NOT NULL, PRIMARY KEY(id), UNIQUE(prodid), UNIQUE(title));");
        System.out.println("Database is ready for work");

        int N = Integer.parseInt(args[0]);
        int id = 0;
        createRecords(connection, N, id);
        Parser parser = new Parser(connection, statement, id);
        try {
            parser.parse_commands();
        } catch (NumberFormatException ex) {
            System.out.println("Wrong parameter");
        }
        statement.close();
        connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
