package com.example.jlmart;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.xml.transform.Result;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SignUpController implements  Initializable{
    @FXML
    TextField firstName;
    @FXML
    TextField lastName;
    @FXML
    TextField address;
    @FXML
    TextField phoneNumber;
    @FXML
    DatePicker dateOfBirth;
    @FXML
    TextField password;
    @FXML
    TextField rewritePassword;
    @FXML
    Label errorLabel;
    @FXML
    CheckBox female;
    @FXML
    CheckBox male;
    @FXML
    private TableView<CPhoneNumber> phoneNumbers;


    @FXML
    private TableColumn<CPhoneNumber, Integer> tableC1;

    @FXML
    private TableColumn<CPhoneNumber, String> tableC2;

    @FXML
    private TableColumn<CPhoneNumber, Button> tableC3;

    Connection con;
    Statement stmt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        con=Connect.con;
        stmt = Connect.stmt;
        CPhoneNumber.phoneNumbers=phoneNumbers;
        tableC1.setCellValueFactory(new PropertyValueFactory<CPhoneNumber, Integer>("counter"));
        tableC2.setCellValueFactory(new PropertyValueFactory<CPhoneNumber, String>("phoneNum"));
        tableC3.setCellValueFactory(new PropertyValueFactory<CPhoneNumber, Button>("remove"));
    }

    public void signUpConfirm(ActionEvent event) throws IOException {
        if(firstName.getText().equals("")||lastName.getText().equals("")||address.getText().equals("")||phoneNumbers.getItems().isEmpty()||password.getText().equals("")||rewritePassword.getText().equals("")||(!female.isSelected()&& !male.isSelected())){
            errorLabel.setText("make shure to fill all fields");
        } else if (!password.getText().equals(rewritePassword.getText())) {
            errorLabel.setText("passwords mismatch");
        } else{

            String gender=(female.isSelected()) ? "female":"male";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                ResultSet rs=stmt.executeQuery("select count(customerId) from customer");
                rs.next();
                int id=rs.getInt(1)+1;
                ResultSet rss=stmt.executeQuery("select * from employee where (empName='"+firstName.getText() + " " + lastName.getText() +"');");
                if(!rss.next()) {
                    stmt.execute("insert into customer (customerId, customerName, customerAddress, customerGender, customerDateOfBirth) values (" + id + ", '" + firstName.getText() + " " + lastName.getText() + "', '" + address.getText() + "', '" + gender + "','" + dateOfBirth.getValue() + "');");
                    stmt.execute("insert into account (username, password, customerId, dateOfCreation) values ('" + firstName.getText() + " " + lastName.getText() + "', '" + password.getText() + "', " + id + ", '" + LocalDate.now() + "');");

                    for (CPhoneNumber c:phoneNumbers.getItems()) {
                        stmt.execute("insert into customerphonenum values(" + id + ", " + c.getPhoneNum() + ");");
                    }
                    errorLabel.setText("success...");
                }
                else{
                    errorLabel.setText("this name is used");

                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void backSignIn(ActionEvent event) throws IOException {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene=new Scene(FXMLLoader.load(getClass().getResource("hello-view.fxml")));
        stage.setScene(scene);
    }
    public void addNumber(ActionEvent event) throws IOException {
        ObservableList<CPhoneNumber> list= phoneNumbers.getItems();
        if (phoneNumber.getText() == null) {
            errorLabel.setText("wrong number format");
        }
        else
            try {
                int d = Integer.parseInt(phoneNumber.getText());
                list.add(new CPhoneNumber(phoneNumber.getText()));
                phoneNumbers.setItems(list);
            } catch (NumberFormatException nfe) {
                errorLabel.setText("wrong number format");
            }

    }

}
