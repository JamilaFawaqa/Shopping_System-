package com.example.jlmart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.io.IOException;
import java.util.ResourceBundle;

public class SignController implements Initializable {
    @FXML
    TextField usernameBox;
    Connection con;
    Statement stmt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        con=Connect.con;
        stmt=Connect.stmt;
    }

    @FXML
    TextField passwordBox;
    @FXML
    Label errorLabel;


    public void signInAction(ActionEvent event) throws IOException {
        String username=usernameBox.getText();
        String password=passwordBox.getText();

                try{


                    ResultSet rs=stmt.executeQuery("select * from account where username='"+username+"' and password='"+password+"'");
                    if(rs.next()){
                        if(rs.getInt(4)!=0) {
                            LogInSession.getInstance().setTheUserID(rs.getInt(4));
                            LogInSession.getInstance().setUsername(rs.getString(1));
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Employee.fxml")));
                            stage.setX(150);
                            stage.setY(50);
                            stage.setScene(scene);
                        }
                        else{
                            LogInSession.getInstance().setTheUserID(rs.getInt(3));
                            LogInSession.getInstance().setUsername(rs.getString(1));
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Customer.fxml")));
                            stage.setX(150);
                            stage.setY(50);
                            stage.setScene(scene);
                        }
                    }
                    else
                        errorLabel.setText("username or password is wrong");

                }catch(Exception e){ System.out.println(e.getMessage());}
            }

    public void signUpAction(ActionEvent event) throws IOException {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene=new Scene(FXMLLoader.load(getClass().getResource("SignUp.fxml")));
        stage.setScene(scene);
    }
}
