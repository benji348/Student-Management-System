package Admin;

import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminController
  implements Initializable
{
  Stage window = new Stage();
  @FXML
  private TextField id;
  @FXML
  private TextField firstname;
  @FXML
  private TextField lastname;
  @FXML
  private TextField email;
  @FXML
  private DatePicker dob;
  @FXML
  private TableView<StudentData> studenttable;
  @FXML
  private TableColumn<StudentData, String> idcolumn;
  @FXML
  private TableColumn<StudentData, String> firstnamecolumn;
  @FXML
  private TableColumn<StudentData, String> lastnamecolumn;
  @FXML
  private TableColumn<StudentData, String> emailcolumn;
  @FXML
  private TableColumn<StudentData, String> dobcolumn;
  @FXML
  private Button updatePromtButton;

  @FXML
  private Button addStudentButton;
  @FXML
  private Button updateButton;
  @FXML
  private Button deleteStudentButton;

  private ObservableList<StudentData> data;
  private dbConnection dc;

  public void handlebuttonAction(ActionEvent event){
    if(!this.id.getText().isEmpty() && event.getSource() == addStudentButton){
      addStudent();

    }else if(event.getSource()==updateButton){
      updateStudents();
    }else if(event.getSource() == deleteStudentButton){
      deleteStudent();
    }
  }


  
  public void initialize(URL url, ResourceBundle rb)
  {
    this.dc = new dbConnection();
    showStudentData();
  }

  private void addStudent()
  {
    String sql = "INSERT INTO `students`(`id`, `fname`, `lname`, `email`, `DOB`) VALUES (? , ?, ?, ?, ?)";
    try
    {
      Connection conn = dbConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, this.id.getText());
      stmt.setString(2, this.firstname.getText());
      stmt.setString(3, this.lastname.getText());
      stmt.setString(4, this.email.getText());
      stmt.setString(5, this.dob.getEditor().getText());

      stmt.execute();
      clearData();
      showStudentData();
      conn.close();
    }
    catch (SQLException e) {
      System.err.println(e.getMessage());
    }

  }

  private void updateStudents() {
    String sql = "UPDATE students set fname =?,lname=?,email=?, DOB = ?"+" where id = " +this.id.getText();
    try {
      Connection conn = dbConnection.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, this.firstname.getText());
      stmt.setString(2, this.lastname.getText());
      stmt.setString(3, this.email.getText());
      stmt.setString(4, this.dob.getEditor().getText());


      stmt.execute();
      clearData();
      showStudentData();
      conn.close();
    } catch (SQLException exception) {
      System.out.println(exception.getMessage());
    }
  }

  public ObservableList<StudentData> getStudentInformation(){
    ObservableList<StudentData> studentInformation = FXCollections.observableArrayList();
    try {
      Connection conn = dbConnection.getConnection();
      Statement statement = conn.createStatement();
      ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students");
      while (rs.next()) {
        studentInformation.add(new StudentData(rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5)));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  return  studentInformation;

  }
  public void showStudentData(){
    ObservableList<StudentData> list = getStudentInformation();

    this.idcolumn.setCellValueFactory(new PropertyValueFactory("ID"));
    this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory("firstName"));
    this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory("lastName"));
    this.emailcolumn.setCellValueFactory(new PropertyValueFactory("email"));
    this.dobcolumn.setCellValueFactory(new PropertyValueFactory("DOB"));

    this.studenttable.setItems(list);
  }
  public void clearData(){
    //Clear after loading
    firstname.clear();
    id.clear();
    lastname.clear();
    email.clear();
    dob.setValue(null);
  }

  @FXML
  private void deleteStudent(){
    String sql = "DELETE FROM students where id = ?";
    try {
      Connection conn = dbConnection.getConnection();
      PreparedStatement preparedStatement = conn.prepareStatement(sql);
      preparedStatement.setString(1,this.id.getText());
      preparedStatement.executeUpdate();
      showStudentData();
      clearData();
      conn.close();
    } catch (SQLException exception) {
      exception.getMessage();
    }


  }

  public void handleMouseAction(javafx.scene.input.MouseEvent mouseEvent) {
    StudentData studentData = studenttable.getSelectionModel().getSelectedItem();
    id.setText(studentData.getID());
    firstname.setText(studentData.getFirstName());
    lastname.setText(studentData.getLastName());
    email.setText(studentData.getEmail());

  }


}
