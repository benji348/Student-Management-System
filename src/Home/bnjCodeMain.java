package Home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class bnjCodeMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = (Parent)FXMLLoader.load(getClass().getResource("bnjCode.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Student Management System");
        stage.getIcons().add(new Image("file:src/Images/schoolManagement2.png"));
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
