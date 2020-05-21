package Database;

import javafx.scene.control.TextArea;
import java.sql.*;
import java.util.Random;
import java.util.UUID;

public class CRUDExecutor {
    static private Connection connection;
    static private Statement statement;
    private int id;
    private TextArea field;

    public CRUDExecutor(Connection con, Statement st, TextArea area) {
        connection = con;
        statement = st;
        field = area;
    }

    public void createRecords(int n) throws SQLException {
        id = 0;
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
        field.setText(field.getText() + "Records are created" + "\n");
    }

    public void addProduct(String prod_name, double price) throws SQLException {
        String prod = String.valueOf(UUID.randomUUID());
        String stm = "INSERT INTO products (id, prodid, title, cost) " + "VALUES (?, ?, ?, ?)";
        PreparedStatement prepstm = connection.prepareStatement(stm);
        prepstm.setInt(1, id);
        prepstm.setString(2, prod);
        prepstm.setString(3, prod_name);
        prepstm.setDouble(4, price);
        prepstm.execute();
        prepstm.close();
        field.setText(field.getText() + "Product was added" + "\n");
    }

    public void deleteProduct(String prod_name) throws SQLException {
        String stm = "DELETE FROM products WHERE title=?";
        PreparedStatement prepstm = connection.prepareStatement(stm);
        prepstm.setString(1, prod_name);
        int flag = prepstm.executeUpdate();
        if (flag == 0) {
            field.setText(field.getText() + "No such product" + "\n");
            return;
        }
        prepstm.close();
        field.setText(field.getText() + "Product was deleted" + "\n");
    }

    public void showPrice(String prod_name) throws SQLException {
        String sttm = "SELECT * FROM products WHERE title=?";
        PreparedStatement prp = connection.prepareStatement(sttm);
        prp.setString(1, prod_name);
        ResultSet rs1 = prp.executeQuery();
        if (!rs1.isBeforeFirst()) {
            field.setText(field.getText() + "No such product" + "\n");
            return;
        }
        while (rs1.next()) {
            double price = rs1.getDouble("cost");
            field.setText(field.getText() + price + "\n");
        }
        rs1.close();
    }

    public void showAll() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT * FROM products");
        if (!rs.isBeforeFirst()) {
            field.setText(field.getText() + "Table is empty" + "\n");
            return;
        }
        while (rs.next()) {
            field.setText(field.getText() + rs.getInt(1) + "\n" + rs.getString(2) + "\n" + rs.getString(3)
                    + "\n" + rs.getDouble(4) + "\n");
        }
    }

    public void filter(double left, double right) throws SQLException {
        String sttm = "SELECT * FROM products WHERE cost>=? AND cost<=?";
        PreparedStatement prp = connection.prepareStatement(sttm);
        prp.setString(1, String.valueOf(left));
        prp.setString(2, String.valueOf(right));
        ResultSet rs1 = prp.executeQuery();
        if (!rs1.isBeforeFirst()) {
            field.setText(field.getText() + "No such products" + "\n");
            return;
        }
        while (rs1.next()) {
            field.setText(field.getText() + "\n" + rs1.getInt("id") + "\n" + rs1.getString("prodid") + "\n" + rs1.getString("title")
                    + "\n" + rs1.getDouble("cost"));
        }
        rs1.close();
    }

    public void changePrice(String prod_name, String new_price) throws SQLException {
        String sttm = "UPDATE products SET cost=? WHERE title=?";
        PreparedStatement prp = connection.prepareStatement(sttm);
        try {
            double price = Double.parseDouble(new_price);
            prp.setString(1, String.valueOf(price));
            prp.setString(2, prod_name);
            int rs2 = prp.executeUpdate();
            if (rs2 == 0) {
                field.setText(field.getText() + "No such product" + "\n");
                return;
            }
            field.setText(field.getText() + "Price was updated" + "\n");
        } catch (Exception ex) {
            field.setText(field.getText() + "Wrong parameters" + "\n");
            ex.printStackTrace();
        }
    }
}
