package com.example.jlmart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {


    @FXML
    TextField firstName;
    @FXML
    TextField lastName;
    @FXML
    TextField salary;
    @FXML
    TextField hours;
    @FXML
    TextField phoneNumber;
    @FXML
    TextArea phoneNumbers;
    @FXML
    Label errorLabel;
    @FXML
    PasswordField password;
    @FXML
    PasswordField rewritePassword;
    @FXML
    TextField firstName1;
    @FXML
    TextField lastName1;
    @FXML
    TextField salary1;
    @FXML
    TextField hours1;
    @FXML
    TextField phoneNumber1;
    @FXML
    TextArea phoneNumbers1;
    @FXML
    PasswordField password1;
    @FXML
    TextField id;
    @FXML
    PasswordField rewritePassword1;
    @FXML
    BorderPane borderPane;
    @FXML
    TextField productCodetxt;
    @FXML
    TextField productNametxt;
    @FXML
    TextField productAmounttxt;
    @FXML
    TextField productPricetxt;
    @FXML
    DatePicker expiaryDatetxt;
    @FXML
    TextField productDeptxt;
    @FXML
    TextField productCodeToRemovetxt;
    @FXML
    ComboBox<String> comboBox;
    @FXML
    TextField customerIdForRatingtxt;
    @FXML
    BorderPane customerRatingBorder;
    @FXML
    BorderPane removeProductBorder;
    @FXML
    BorderPane addEmployeeBorder;
    @FXML
    BorderPane updateEmployeeBorder;
    @FXML
    BorderPane addProductBorder;
    @FXML
    BorderPane paymentBorder;
    @FXML
    TextField customerIdForPaymenttxt;
    @FXML
    TextField amountOfPaytxt;
    @FXML
    ComboBox<String> comboBoxCurrency;
    @FXML
    private TableColumn<Product, Integer> proAmountCol;
    @FXML
    private TableColumn<Product, String> proCodeCol;
    @FXML
    private TableColumn<Product, String> proNameCol;
    @FXML
    private TableView<Product> expDateTable;
    @FXML
    DatePicker expDateDP;
    @FXML
    BorderPane expDateBorder;
    @FXML
    BorderPane purchaseInfoBorder;
    @FXML
    BorderPane purInfoAllBorder;
    @FXML
    DatePicker allFromDateDP;
    @FXML
    DatePicker allToDateDP;
    @FXML
    private TableColumn<Purchase, Integer> purchaseNumCol;
    @FXML
    private TableColumn<Purchase, Integer> purchaseAmountCol;
    @FXML
    private TableColumn<Purchase, Integer> customerIdCol;
    @FXML
    private TableColumn<Purchase, String> customerNameCol;
    @FXML
    private TableView<Purchase> purchaseInfoAllTable;
    @FXML
    TextField customerIdForSpecific;
    @FXML
    DatePicker fromDateForSpecific;
    @FXML
    DatePicker toDateForSpecific;
    @FXML
    private TableColumn<Purchase, Integer> purchaseNumCol1;
    @FXML
    private TableColumn<Purchase, Integer> purchaseAmountCol1;
    @FXML
    private TableColumn<Purchase, Integer> customerIdCol1;
    @FXML
    private TableColumn<Purchase, String> customerNameCol1;
    @FXML
    private TableView<Purchase> purchaseInfoSpecificTable;
    @FXML
    BorderPane specificCustomerBorder;
    @FXML
    Button adddEmp;
    @FXML
    Button updEmp;
    @FXML
            TextField imageName;
    @FXML
            Button chooseButton;

    Connection con;
    Statement stmt;
    LogInSession idd = LogInSession.getInstance();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        con=Connect.con;
        stmt=Connect.stmt;

        if (!LogInSession.getInstance().getUsername().equals("admin")){
            adddEmp.setDisable(true);
            updEmp.setDisable(true);
        }

        comboBox.setItems(FXCollections.observableArrayList("*","**","***","****","*****"));
        comboBoxCurrency.setItems(FXCollections.observableArrayList("$ USD","₪ NIS","JOD","€ EUR","£ GBP", "Other"));
        proCodeCol.setCellValueFactory(new PropertyValueFactory<Product, String>("code"));
        proAmountCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("amount"));
        proNameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        purchaseNumCol.setCellValueFactory(new PropertyValueFactory<Purchase, Integer>("pNum"));
        purchaseAmountCol.setCellValueFactory(new PropertyValueFactory<Purchase, Integer>("pAmount"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<Purchase, Integer>("cID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("cName"));
        purchaseNumCol1.setCellValueFactory(new PropertyValueFactory<Purchase, Integer>("pNum"));
        purchaseAmountCol1.setCellValueFactory(new PropertyValueFactory<Purchase, Integer>("pAmount"));
        customerIdCol1.setCellValueFactory(new PropertyValueFactory<Purchase, Integer>("cID"));
        customerNameCol1.setCellValueFactory(new PropertyValueFactory<Purchase, String>("cName"));
    }
    public void addNumber(ActionEvent event) throws IOException {
        if (phoneNumber.getText() == null) {
            errorLabel.setText("wrong number format");
        }
        else
            try {
                int d = Integer.parseInt(phoneNumber.getText());
                phoneNumbers.setText(phoneNumbers.getText()+","+phoneNumber.getText());
            } catch (NumberFormatException nfe) {
                errorLabel.setText("wrong number format");
            }
    }
    public void removeNumber(ActionEvent event) throws IOException {
        if(phoneNumbers.getText().equals(""))
            errorLabel.setText("no numbers existing");
        else {
            int index = phoneNumbers.getText().lastIndexOf(",");
            phoneNumbers.setText(phoneNumbers.getText().substring(0, index));
        }
    }
    public void addNumber1(ActionEvent event) throws IOException {
        if (phoneNumber1.getText() == null) {
            errorLabel.setText("wrong number format");
        }
        else
            try {
                int d = Integer.parseInt(phoneNumber1.getText());
                phoneNumbers1.setText(phoneNumbers1.getText()+","+phoneNumber1.getText());
            } catch (NumberFormatException nfe) {
                errorLabel.setText("wrong number format");
            }

    }
    public void removeNumber1(ActionEvent event) throws IOException {
        if(phoneNumbers1.getText().equals(""))
            errorLabel.setText("no numbers existing");
        else {
            int index = phoneNumbers1.getText().lastIndexOf(",");
            phoneNumbers1.setText(phoneNumbers1.getText().substring(0, index));
        }
    }

    public void signUpConfirm(ActionEvent event) throws IOException {

        if(firstName.getText().equals("")||lastName.getText().equals("")||salary.getText().equals("")||hours.getText().equals("")||phoneNumbers.getText().equals("")||password.getText().equals("")||rewritePassword.getText().equals("")){
            errorLabel.setText("make sure to fill all fields");
        } else if (!password.getText().equals(rewritePassword.getText())) {
            errorLabel.setText("passwords mismatch");

        }

        else{
            try {
                int sal = Integer.parseInt(salary.getText());
                try {
                    int hou = Integer.parseInt(hours.getText());
                    try {
                        ResultSet rs = stmt.executeQuery("SELECT COUNT(employeeId) FROM employee;");
                        rs.next();
                        int id = rs.getInt(1) + 1;
                        ResultSet rss = stmt.executeQuery("select * from customer where (customerName='"+firstName.getText() + " " + lastName.getText() +"');");
                        if(!rss.next()) {
                            stmt.execute("insert into employee values (" + id + ", '" + firstName.getText() + " " + lastName.getText() + "', " + salary.getText() + ", " + hours.getText() + ");");
                            stmt.execute("insert into account (username, password, employeeId, dateOfCreation) values ('" + firstName.getText() + " " + lastName.getText() + "', '" + password.getText() + "', " + id + ", '" + LocalDate.now() + "');");
                            String[] numbers = phoneNumbers.getText().split(",");
                            for (int counter = 1; counter < numbers.length; counter++) {
                                stmt.execute("insert into empphonenum values(" + id + ", " + numbers[counter] + ");");
                            }
                            errorLabel.setText("success...");
                        }
                        else{
                            errorLabel.setText("this name is used");
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } catch (NumberFormatException e) {
                    errorLabel.setText("hours must be numeric");
                }
                } catch (NumberFormatException e) {
                    errorLabel.setText("salary must be numeric");

            }
        }
    }
    public void updateEmployee(ActionEvent event) throws IOException {
        updateFirstName();
        updateLastName();
        updateSalary();
        updateWorkHours();
        updateNumbers();
        updatePassword();
    }
    public void updateFirstName() throws IOException {
        try {
            if(!firstName1.getText().equals("")) {
                ResultSet rs=stmt.executeQuery("select * from employee where(employeeId="+id.getText()+");");
                rs.next();
                String[] name=rs.getString(2).split(" ");
                name[0]=firstName1.getText();
                stmt.execute("update employee set empName='"+name[0]+" "+name[1]+"' where (employeeId="+id.getText()+");");
                stmt.execute("update account set username='"+name[0]+" "+name[1]+"' where (employeeId="+id.getText()+");");
            }
            else{
                errorLabel.setText("fill text field first");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void updateLastName() throws IOException {
        try {
            if(!lastName1.getText().equals("")) {
                ResultSet rs=stmt.executeQuery("select * from employee where(employeeId="+id.getText()+");");
                rs.next();
                String[] name=rs.getString(2).split(" ");
                name[1]=lastName1.getText();
                stmt.execute("update employee set empName='"+name[0]+" "+name[1]+"' where (employeeId="+id.getText()+");");
                stmt.execute("update account set username='"+name[0]+" "+name[1]+"' where (employeeId="+id.getText()+");");
            }
            else{
                errorLabel.setText("fill text field first");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void updateSalary() throws IOException {
        try {
            if(!salary1.getText().equals("")) {
                Integer.parseInt(salary1.getText());
                try {

                    stmt.execute("update employee set empSalary="+salary1.getText()+" where (employeeId="+id.getText()+");");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            else{
                errorLabel.setText("fill text field first");
            }
        }catch (NumberFormatException e){
            errorLabel.setText("salary must be numeric");
        }
    }
    public void updateWorkHours() throws IOException {
        try {
            Integer.parseInt(hours1.getText());
            try {
                if(!hours1.getText().equals("")) {

                    stmt.execute("update employee set activehours="+hours1.getText()+" where (employeeId="+id.getText()+");");
                }
                else{
                    errorLabel.setText("fill text field first");
                }
                } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }catch (NumberFormatException e){
            errorLabel.setText("work hours must be numeric");
        }

    }
    public void updateNumbers() throws IOException {
        try {
            if(!phoneNumbers1.getText().equals("")) {

                stmt.execute("delete from empphonenum where(employeeId="+id.getText()+");");
                String[] numbers = phoneNumbers1.getText().split(",");
                for (int counter = 1; counter < numbers.length; counter++) {
                    stmt.execute("insert into empphonenum values("+id.getText()+", "+numbers[counter]+");");
                }
            }
            else {
                errorLabel.setText("fill text field first");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
    public void updatePassword() throws IOException {
        try {
            if(password1.getText().equals(rewritePassword1.getText())) {
                if (!password1.getText().equals("")) {

                    stmt.execute("update account set password='" + password1.getText() + "' where (employeeId="+id.getText()+");");
                } else {
                    errorLabel.setText("fill text field first");
                }
            }
            else{
                errorLabel.setText("password mismatch");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public void searchById() throws IOException {
        try {
            Integer.parseInt(id.getText());
        try {
                ResultSet rs = stmt.executeQuery("select * from employee where (employeeId = "+id.getText()+");");
                if (rs.next()) {
                    String name=rs.getString(2);
                    String[] sName=name.split(" ");
                    firstName1.setText(sName[0]);
                    if(sName.length>1)
                        lastName1.setText(sName[1]);
                    salary1.setText(Integer.toString(rs.getInt(3)));
                    hours1.setText(Integer.toString(rs.getInt(4)));
                    rs=stmt.executeQuery("select * from empphonenum where(employeeId="+id.getText()+");");
                    phoneNumbers1.setText("");
                    while (rs.next()){
                        phoneNumbers1.setText(phoneNumbers1.getText()+","+Integer.toString(rs.getInt(2)));
                    }
                    rs=stmt.executeQuery("select * from account where(employeeId="+id.getText()+");");
                    rs.next();
                    password1.setText(rs.getString(2));

                } else {
                    errorLabel.setText("employee does not exist");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }catch (NumberFormatException e){
            errorLabel.setText("id must be numeric");
        }

    }
    public void addProductBtn(ActionEvent event) throws IOException {
        if(productCodetxt.getText().equals("")||productNametxt.getText().equals("")||productAmounttxt.getText().equals("")||productPricetxt.getText().equals("")||productDeptxt.getText().equals("")||expiaryDatetxt.getValue().equals("")){
            errorLabel.setText("make sure to fill all fields");
        }

        else{
            try {
                Integer.parseInt(productPricetxt.getText());
                try {
                    Integer.parseInt(productAmounttxt.getText());
                    try {
                        ResultSet rss = stmt.executeQuery("select * from product where (productCode='"+productCodetxt.getText()+"');");
                        if(!rss.next()) {
                            ResultSet rs = stmt.executeQuery("SELECT depNum FROM department WHERE (depName='"+productDeptxt.getText()+"');");
                            LocalDate myexpDate = expiaryDatetxt.getValue();
                            rs.next();
                            stmt.execute("INSERT INTO product (productCode, expDate, proAmount, proSection, proPrice, proName, depNum, employeeId, imgsrc) VALUES ('" + productCodetxt.getText() + "', '" + myexpDate.toString() + "', " + productAmounttxt.getText() + ", '" + productDeptxt.getText() + "', " + productPricetxt.getText() + ", '" + productNametxt.getText() + "', " + rs.getInt("depNum")  + ", "+LogInSession.getInstance().getTheUserID()+", '"+imageName.getText()+"');");
                        }
                        else{
                            errorLabel.setText("this product is already exists!...");
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } catch (NumberFormatException e) {
                    errorLabel.setText("hours must be numeric");
                }
            } catch (NumberFormatException e) {
                errorLabel.setText("salary must be numeric");

            }
        }

    }
    public void chooseImage (ActionEvent event){
        FileChooser fileChooser = new FileChooser();

        // Set the extension filter (optional)
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);

        // Show the file chooser dialog
        Stage stage = (Stage) chooseButton.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        // Process the selected file (e.g., load the image)
        if (selectedFile != null) {
            String fileName = selectedFile.getName();
            // Process the selected file here, such as loading the image
            System.out.println("Selected file: " + fileName);
            imageName.setText(fileName);
        }
    }
    public void removeProductBtn (ActionEvent event) throws IOException{
        if(productCodeToRemovetxt.getText().equals("")){
            errorLabel.setText("make sure to fill all fields");
        }
        else{
            try {
                stmt.execute("DELETE FROM product WHERE (productCode = '"+productCodeToRemovetxt.getText()+"');");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void submit (ActionEvent event) {
        if(customerIdForRatingtxt.getText().equals("") || comboBox.getSelectionModel().getSelectedItem().toString().equals("")){
            errorLabel.setText("make sure to fill all fields");
        }
        else{
            try {
                stmt.execute("UPDATE customer SET cusromerRating = '" + comboBox.getSelectionModel().getSelectedItem().toString() + "' WHERE customerId = " + customerIdForRatingtxt.getText() + "");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void submitPayment (ActionEvent event){
        if (customerIdForPaymenttxt.getText().equals("") || amountOfPaytxt.getText().equals("") || comboBoxCurrency.getSelectionModel().getSelectedItem().toString().equals("")){
            errorLabel.setText("make sure to fill all fields");
        }
        else {
            try {
                ResultSet rs = stmt.executeQuery("SELECT COUNT(payId) FROM payment;");
                rs.next();
                int id = rs.getInt(1) + 1;
                stmt.execute("INSERT INTO payment VALUES ("+ id +",'"+ comboBoxCurrency.getSelectionModel().getSelectedItem().toString()+"',"+amountOfPaytxt.getText()+","+customerIdForPaymenttxt.getText()+","+ LogInSession.getInstance().getTheUserID()+",'"+LocalDate.now()+"');");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public void showResultExpDateBtn(ActionEvent event){
        expDateTable.getItems().clear(); //dalete
        if (expDateDP.getValue().equals("")){
            errorLabel.setText("make sure to fill all fields");
        }
        else{
            try {
                ResultSet rs = stmt.executeQuery("SELECT productCode, proName, proAmount FROM product WHERE (expDate = '"+expDateDP.getValue().toString()+"');");
                ObservableList<Product> proList = FXCollections.observableArrayList();
                while (rs.next()){
                    String productCode = rs.getString("productCode");
                    String productName = rs.getString("proName");
                    int productAmount = rs.getInt("proAmount");
                    Product product = new Product(productCode,productAmount, productName);
                    proList.add(product);
                }
                expDateTable.getItems().addAll(proList);
                expDateTable.refresh();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void showResultPurchaseInfoAllBtn (ActionEvent event){
        purchaseInfoAllTable.getItems().clear(); //dalete
        if (allFromDateDP.getValue().equals("") || allToDateDP.getValue().equals("")){
            errorLabel.setText("make sure to fill all fields");
        }
        else{
            try {
                ResultSet rs = stmt.executeQuery("SELECT purchase.purchaseNum, purchase.purcahseAmount, purchase.customerId, customer.customerName FROM purchase JOIN customer ON purchase.customerId = customer.customerId WHERE (purchase.dateOfPurchase between '"+allFromDateDP.getValue().toString()+"' and '"+allToDateDP.getValue().toString()+"');");
                ObservableList<Purchase> purchaseAllList = FXCollections.observableArrayList();
                while (rs.next()){
                    int pNumber = rs.getInt("purchaseNum");
                    int pAmount = rs.getInt("purcahseAmount");
                    int cId = rs.getInt("customerId");
                    String cName = rs.getString("customerName");
                    Purchase purchase = new Purchase(pNumber,pAmount, cId, cName);
                    purchaseAllList.add(purchase);
                }
                purchaseInfoAllTable.getItems().addAll(purchaseAllList);
                purchaseInfoAllTable.refresh();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void showResultPurchaseInfoSpecificBtn (ActionEvent event){
        purchaseInfoSpecificTable.getItems().clear(); //dalete
        LocalDate fromDate = fromDateForSpecific.getValue();
        LocalDate toDate = toDateForSpecific.getValue();
        if(customerIdForSpecific.getText().equals("")){
            errorLabel.setText("make sure to fill all fields");
        }
        else if (fromDate == null || toDate == null){
            try {
                ResultSet rs = stmt.executeQuery("SELECT purchase.purchaseNum, purchase.purcahseAmount, purchase.customerId, customer.customerName FROM purchase JOIN customer ON purchase.customerId = customer.customerId WHERE (purchase.customerId = '"+customerIdForSpecific.getText()+"');");
                ObservableList<Purchase> purchaseCustomerList = FXCollections.observableArrayList();
                while (rs.next()){
                    int pNumber = rs.getInt("purchaseNum");
                    int pAmount = rs.getInt("purcahseAmount");
                    int cId = rs.getInt("customerId");
                    String cName = rs.getString("customerName");
                    Purchase purchase = new Purchase(pNumber,pAmount, cId, cName);
                    purchaseCustomerList.add(purchase);
                }
                purchaseInfoSpecificTable.getItems().addAll(purchaseCustomerList);
                purchaseInfoSpecificTable.refresh();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            try {
                ResultSet rs = stmt.executeQuery("SELECT purchase.purchaseNum, purchase.purcahseAmount, purchase.customerId, customer.customerName FROM purchase JOIN customer ON purchase.customerId = customer.customerId WHERE (purchase.dateOfPurchase between '"+fromDateForSpecific.getValue().toString()+"' and '"+toDateForSpecific.getValue().toString()+"' AND purchase.customerId = '"+customerIdForSpecific.getText()+"');");
                ObservableList<Purchase> purchaseCustomerList = FXCollections.observableArrayList();
                while (rs.next()){
                    int pNumber = rs.getInt("purchaseNum");
                    int pAmount = rs.getInt("purcahseAmount");
                    int cId = rs.getInt("customerId");
                    String cName = rs.getString("customerName");
                    Purchase purchase = new Purchase(pNumber,pAmount, cId, cName);
                    purchaseCustomerList.add(purchase);
                }
                purchaseInfoSpecificTable.getItems().addAll(purchaseCustomerList);
                purchaseInfoSpecificTable.refresh();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void purchaseInfoCustomerBtn (ActionEvent event) {
        specificCustomerBorder.toFront();
    }
    public void backToPurchaseInfo (ActionEvent event) {
        purchaseInfoBorder.toFront();
    }
    public void backToPurchaseInfo1 (ActionEvent event) {
        purchaseInfoBorder.toFront();
    }
    public void purchaseInfoAllBtn (ActionEvent event) {
        purInfoAllBorder.toFront();
    }
    public void addEmp (ActionEvent event) {
        addEmployeeBorder.toFront();
    }
    public void rateCustomer (ActionEvent event) {
        customerRatingBorder.toFront();
    }
    public void removeProduct (ActionEvent event) {
        removeProductBorder.toFront();
    }
    public void updateEmp (ActionEvent event) {
        updateEmployeeBorder.toFront();
    }
    public void addProduct (ActionEvent event) {
        addProductBorder.toFront();
    }
    public void payment (ActionEvent event) {
        paymentBorder.toFront();
    }
    public void expDateShowBtn (ActionEvent event){
        expDateBorder.toFront();
    }
    public void purchaseInfoBtn (ActionEvent event) {
        purchaseInfoBorder.toFront();
    }
    public void backBtnInEmp (ActionEvent event) throws IOException {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene=new Scene(FXMLLoader.load(getClass().getResource("hello-view.fxml")));
        stage.setScene(scene);
    }


}
