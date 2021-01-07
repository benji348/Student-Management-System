package loginapp;

import Admin.AdminController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import students.StudentsFXMLController;

public class LoginMainController
  implements Initializable
{
  LoginModel loginModel = new LoginModel();
  @FXML
  private Label dbstatus;
  @FXML
  private Button loginButton;
  @FXML
  private TextField username;
  @FXML
  private PasswordField password;
  @FXML
  private ComboBox<option> combobox;

  public void initialize(URL url, ResourceBundle rb)
  {
    if (this.loginModel.isDatabaseConnected()) {
      this.dbstatus.setText("Connexion to the Database Succeed");
    } else {
      this.dbstatus.setText("Not Connected");
    }
    this.combobox.setItems(FXCollections.observableArrayList(option.values()));
  }

  @FXML
  public void Login(ActionEvent event)
  {
    try
    {
      if (this.loginModel.isLogin(this.username.getText(), this.password.getText(), ((option)this.combobox.getValue()).toString()))
      {
        Stage stage = (Stage)this.loginButton.getScene().getWindow();
        stage.close();
        switch (((option)this.combobox.getValue()).toString())
        {
        case "Admin":
          adminLogin();
          break;
        case "Student":
          studentLogin();
        }
      }
      else
      {
        this.dbstatus.setText("Wrong Creditials");
      }
    }
    catch (Exception localException) {}
  }

  public void studentLogin() {
    try
    {
      Stage userStage = new Stage();
      FXMLLoader loader = new FXMLLoader();
      Pane root = (Pane)loader.load(getClass().getResource("/students/students.fxml").openStream());
      StudentsFXMLController studentController = (StudentsFXMLController)loader.getController();

      Scene scene = new Scene(root);
      userStage.setScene(scene);
      userStage.getIcons().add(new Image("file:src/Images/schoolManagement2.png"));
      userStage.show();
      userStage.centerOnScreen();
    }
    catch (IOException e)
    {
      System.out.println(e.getMessage());
    }
  }

  public void adminLogin() {
    try
    {
      Stage adminStage = new Stage();
      FXMLLoader adminLoader = new FXMLLoader();
      Pane adminroot = (Pane)adminLoader.load(getClass().getResource("/Admin/adminDashboard.fxml").openStream());
      AdminController admintController = (AdminController)adminLoader.getController();

      Scene adminscene = new Scene(adminroot);

      adminStage.setScene(adminscene);
      adminStage.setTitle("Student Management System");
      adminStage.setResizable(true);
      adminStage.getIcons().add(new Image("file:src/Images/schoolManagement.png"));
      adminscene.getStylesheets().add(getClass().getResource("/Admin/style.css").toString());
      adminStage.show();
      adminStage.centerOnScreen();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
