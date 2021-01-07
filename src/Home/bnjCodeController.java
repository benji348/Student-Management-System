package Home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class bnjCodeController implements Initializable {
    Stage stage = new Stage();
    @FXML
    private Button homePageIDButton, AdminIDButton, timeTableIdButton, classesIdButton, updateIdButton,stingsIdButton, okButton;
    @FXML
    private void HandleButton(ActionEvent event) throws IOException {
        if(event.getSource() == AdminIDButton){
            try {
                adminPart();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(event.getSource() == homePageIDButton){
            homePage();
        }else if(event.getSource() == okButton){

        }
    }


    public void adminPart() throws IOException {
        FXMLLoader adminOpen = new FXMLLoader();
        try {
            Pane pane= adminOpen.load(getClass().getResource("/loginapp/LoginMain.fxml").openStream());
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            stage.setTitle("Student Management System");
            stage.getIcons().add(new Image("file:src/Images/schoolManagement2.png"));
            scene.getStylesheets().add(getClass().getResource("/Home/style.css").toString());
            stage.setResizable(false);
            stage.show();
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void homePage(){
        FXMLLoader homePage = new FXMLLoader();
        try {
            stage.initModality(Modality.APPLICATION_MODAL);
            Pane pane= homePage.load(getClass().getResource("/home/homePageMessage.fxml").openStream());
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            stage.getIcons().add(new Image("file:src/Images/schoolManagement2.png"));
            scene.getStylesheets().add(getClass().getResource("/Home/style.css").toString());
            stage.setResizable(false);
            stage.showAndWait();
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
