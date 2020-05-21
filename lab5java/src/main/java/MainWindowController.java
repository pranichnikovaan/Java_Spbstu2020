import Database.TableHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Database.CRUDExecutor;

public class MainWindowController {
    private  CRUDExecutor executor;
    static int n;

    public void handleExitButton() {
        Stage stage = (Stage) Exit_button.getScene().getWindow();
        stage.close();
    }
    private void parseCommands() {
        try {
            String command = input_field.getText();
            input_field.setText("");
            String[] parameters = command.split(" ");
            switch (parameters[0]) {
                case "/add":
                    if (parameters.length >= 3) {
                        double price = Double.parseDouble(parameters[2]);
                        executor.addProduct(parameters[1], price);
                        output_field.setText(output_field.getText() + "Info has been added" + "\n");
                    } else {
                        output_field.setText(output_field.getText() + "Not enough parameters\n");
                    }
                    break;
                case "/delete":
                    if (parameters.length >= 2) {
                        executor.deleteProduct(parameters[1]);
                        output_field.setText(output_field.getText() + "Info has been deleted" + "\n");
                    } else {
                        output_field.setText(output_field.getText() + "Not enough parameters\n");
                    }
                    break;
                case "/show_all":
                    executor.showAll();
                    break;
                case "/price":
                    if (parameters.length >= 2) {
                        executor.showPrice(parameters[1]);
                    } else {
                        output_field.setText(output_field.getText() + "Not enough parameters\n");
                    }
                    break;
                case "/change_price":
                    if (parameters.length >= 3) {
                        executor.changePrice(parameters[1], parameters[2]);
                    }  else {
                        output_field.setText(output_field.getText() + "Not enough parameters\n");
                    }
                    break;
                case "/filter_by_price":
                    if (parameters.length >= 3) {
                        if (Double.parseDouble(parameters[1]) >= Double.parseDouble(parameters[2])) {
                            output_field.setText(output_field.getText() + "Wrong interval\n");
                            break;
                        }
                        executor.filter(Double.parseDouble(parameters[1]), Double.parseDouble(parameters[2]));
                    } else {
                        output_field.setText(output_field.getText() + "Not enough parameters\n");
                    }
                    break;
                default:
                    output_field.setText(output_field.getText() + "Nu such command\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void handleExecuteButton() {
        parseCommands();
    }
    public void handleClearButton() {
        output_field.setText("");
    }
    public void handleShowAllButton() throws SQLException {
        executor.showAll();
    }
    public void handleDoneAddButton() {
        try {
            if (name_field.getText().equals("") || price_field.getText().equals("")) {
                output_field.setText(output_field.getText() + "Empty parameters\n");
                add_window.setVisible(false);
                price_field.setText("");
                name_field.setText("");
                return;
            }
            String prod_name = name_field.getText();
            double price = Double.parseDouble(price_field.getText());
            executor.addProduct(prod_name, price);
        } catch (Exception ex) {
            output_field.setText(output_field.getText() + "Wrong parameters\n");
            ex.printStackTrace();
        }
        add_window.setVisible(false);
        price_field.setText("");
        name_field.setText("");
    }
    public void handleCancelAddButton() {
        add_window.setVisible(false);
        price_field.setText("");
        name_field.setText("");
    }
    public void handleDoneFilterButton() {
        try {
            if (price1_field.getText().equals("") || price2_field.getText().equals("")) {
                output_field.setText(output_field.getText() + "Empty parameters\n");
                filter_window.setVisible(false);
                price1_field.setText("");
                price2_field.setText("");
                return;
            }
            double low = Double.parseDouble(price1_field.getText());
            double high = Double.parseDouble(price2_field.getText());
            executor.filter(low, high);
        } catch (Exception ex) {
            output_field.setText(output_field.getText() + "Wrong parameters\n");
            ex.printStackTrace();
        }
        filter_window.setVisible(false);
        price1_field.setText("");
        price2_field.setText("");
    }
    public void handleCancelDeleteButton() {
        delete_window.setVisible(false);
        delete_field.setText("");
    }
    public void handleDoneDeleteButton() {
        try {
            if (delete_field.getText().equals("")) {
                output_field.setText(output_field.getText() + "Empty parameters\n");
                delete_window.setVisible(false);
                return;
            }
            String name = delete_field.getText();
            executor.deleteProduct(name);
        } catch (Exception ex) {
            output_field.setText(output_field.getText() + "Wrong parameters\n");
            ex.printStackTrace();
        }
        delete_window.setVisible(false);
        delete_field.setText("");
    }
    public void handleCancelFilterButton() {
        filter_window.setVisible(false);
        price1_field.setText("");
        price2_field.setText("");
    }
    public void handleFilterButton() {
        filter_window.setVisible(true);
    }
    public void handleDeleteButton() {
        delete_window.setVisible(true);
    }
    public void showAddWindow() {
        add_window.setVisible(true);
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane add_window;

    @FXML
    private TextField price_field;

    @FXML
    private TextField name_field;

    @FXML
    public Button done1_button;

    @FXML
    public Button cancel1_button;

    @FXML
    public Pane delete_window;

    @FXML
    public TextField delete_field;

    @FXML
    private Button done3_button;

    @FXML
    private Button cancel3_button;

    @FXML
    public Pane filter_window;

    @FXML
    public TextField price1_field;

    @FXML
    public TextField price2_field;

    @FXML
    private Button done2_button;

    @FXML
    private Button cancel2_button;

    @FXML
    private Button showAll_button;

    @FXML
    private TextArea output_field;

    @FXML
    private Button clear_button;

    @FXML
    private TextField input_field;

    @FXML
    private Button add_button;

    @FXML
    private Button delete_button;

    @FXML
    private Button filter_button;

    @FXML
    private Button Exit_button;

    @FXML
    private Button execute_button;

    @FXML
    void initialize() {
        TableHandler.createTable();
        try {
             executor = new CRUDExecutor(TableHandler.connection, TableHandler.statement, output_field);
            executor.createRecords(n);
             } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}