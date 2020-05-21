import java.sql.*;
import java.util.Scanner;
import java.util.UUID;

class Parser {
    private Connection connection;
    private Statement statement;
    private int id;
    Parser(Connection con, Statement stat, int prod_id) {
        connection = con;
        statement = stat;
        id = prod_id;
    }

    void parse_commands() throws SQLException {
        String str;
        Scanner scan = new Scanner(System.in);
        str = scan.nextLine();
        String command = str.trim();
        while (!(command.equals("/end"))) {
            String[] parameters = command.split(" ");
            switch (parameters[0]) {
                case "/add":
                    if (parameters.length >= 3) {
                        String prod = String.valueOf(UUID.randomUUID());
                        String prod_name = parameters[1];
                        double price = Double.parseDouble(parameters[2]);
                        if (price <= 0) {
                            System.out.println("Negative price");
                            break;
                        }
                        String stm = "INSERT INTO products (id, prodid, title, cost) " + "VALUES (?, ?, ?, ?)";
                        PreparedStatement prepstm = connection.prepareStatement(stm);
                        prepstm.setInt(1, id);
                        prepstm.setString(2, prod);
                        prepstm.setString(3, prod_name);
                        prepstm.setDouble(4, price);
                        try {
                            prepstm.execute();
                        } catch (SQLIntegrityConstraintViolationException ex) {
                            System.out.println("This product is already in a table");
                            prepstm.close();
                            break;
                        }
                        prepstm.close();
                        System.out.println("Info has been added");
                    } else {
                        System.out.println("Not enough arguments");
                    }
                    break;
                case "/delete":
                    if (parameters.length >= 2) {
                        String stm = "DELETE FROM products WHERE title=?";
                        PreparedStatement prepstm = connection.prepareStatement(stm);
                        prepstm.setString(1, parameters[1]);
                        int flag = prepstm.executeUpdate();
                        if (flag == 0) {
                            System.out.println("No such product");
                            break;
                        }
                        prepstm.close();
                        System.out.println("Info has been deleted");
                    } else {
                        System.out.println("Not enough arguments");
                    }
                    break;
                case "/show_all":
                    ResultSet rs = statement.executeQuery("SELECT * FROM products");
                    if (!rs.isBeforeFirst()) {
                        System.out.println("Table is empty");
                        break;
                    }
                    while (rs.next()) {
                        System.out.println(rs.getInt(1));
                        System.out.println(rs.getString(2));
                        System.out.println(rs.getString(3));
                        System.out.println(rs.getDouble(4));
                    }
                    break;
                case "/price":
                    if (parameters.length >= 2) {
                        String sttm = "SELECT * FROM products WHERE title=?";
                        PreparedStatement prp = connection.prepareStatement(sttm);
                        prp.setString(1, parameters[1]);
                        ResultSet rs1 = prp.executeQuery();
                        if (!rs1.isBeforeFirst()) {
                            System.out.println("No such product");
                            break;
                        }
                        while (rs1.next()) {
                            double price = rs1.getDouble("cost");
                            System.out.println(price);
                        }
                        rs1.close();
                    } else {
                        System.out.println("Not enough arguments");
                    }
                    break;
                case "/change_price":
                    if (parameters.length >= 3) {
                        String sttm = "UPDATE products SET cost=? WHERE title=?";
                        PreparedStatement prp = connection.prepareStatement(sttm);
                        prp.setString(1, parameters[2]);
                        prp.setString(2, parameters[1]);
                        int rs2 = prp.executeUpdate();
                        if (rs2 == 0) {
                            System.out.println("No such product");
                            break;
                        }
                        System.out.println("Price was updated");
                    } else {
                        System.out.println("Not enough arguments");
                    }
                    break;
                case "/filter_by_price":
                    if (parameters.length >= 3) {
                        String sttm = "SELECT * FROM products WHERE cost>=? AND cost<=?";
                        PreparedStatement prp = connection.prepareStatement(sttm);
                        if (Double.parseDouble(parameters[1]) >= Double.parseDouble(parameters[2])) {
                            System.out.println("Invalid price range");
                            break;
                        }
                        prp.setString(1, parameters[1]);
                        prp.setString(2, parameters[2]);
                        ResultSet rs1 = prp.executeQuery();
                        if (!rs1.isBeforeFirst()) {
                            System.out.println("No such products");
                            break;
                        }
                        while (rs1.next()) {
                            System.out.println(rs1.getInt("id"));
                            System.out.println(rs1.getString("prodid"));
                            System.out.println(rs1.getString("title"));
                            System.out.println(rs1.getDouble("cost"));
                        }
                        rs1.close();
                    } else {
                        System.out.println("Not enough arguments");
                    }
                    break;
                default:
                    System.out.println("No such command");
                    break;
            }

            str = scan.nextLine();
            command = str.trim();
        }
    }
}
