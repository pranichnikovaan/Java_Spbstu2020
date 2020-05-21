import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainWindow extends Application {
    public static void main(String[] args) {
        MainWindowController.n = Integer.parseInt(args[0]);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("main_window.fxml"));
            primaryStage.setTitle("Database");
            primaryStage.setScene(new Scene(root, 762, 434));
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
