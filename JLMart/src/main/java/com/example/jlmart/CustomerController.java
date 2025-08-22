package com.example.jlmart;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;

public class CustomerController implements Initializable {
    private final CustomerController instance= this;
    @FXML
    private GridPane productGrid;
    @FXML
    public BorderPane BPexplore;
    @FXML
    public ImageView productTB;
    @FXML
    public Spinner<Integer> checkAmount;
    @FXML
    public BorderPane BPorder;
    @FXML
    public Label proName;
    @FXML
    public Label errorLabel;
    @FXML
    public Label price;
    LogInSession id=LogInSession.getInstance();
    ArrayList<Product> products;
    @FXML
    private TableView<Product> table2;

    @FXML
    private TableColumn<Product, String> table2C1;

    @FXML
    private TableColumn<Product, String> table2C2;

    @FXML
    private TableColumn<Product, Spinner> table2C3;

    @FXML
    private TableColumn<Product, Button> table2C4;
    @FXML
    private TableColumn<Product, Integer> table2C6;
    @FXML
    BorderPane B3;

    @FXML
    private TableView<Mail> table3;

    @FXML
    private TableColumn<Mail, String> table3C1;

    @FXML
    private TableColumn<Mail, String> table3C2;

    @FXML
    private TableColumn<Mail, Date> table3C3;

    @FXML
    private TableView<Mail> table4;

    @FXML
    private TableColumn<Mail, String> table4C1;

    @FXML
    private TableColumn<Mail, String> table4C2;

    @FXML
    private TableColumn<Mail, Date> table4C3;
    @FXML
    private ChoiceBox<String> chooseE;
    @FXML
    private TextArea messageTS;

    @FXML
    private Button send;
    @FXML
    private TableColumn<Product, Integer> tableC5;
    @FXML
    private BorderPane MailB;
    @FXML
    private BorderPane newMessageB;
    @FXML
            BorderPane myAccountBorder;
    @FXML
    TextField firstName;
    @FXML
    TextField lastName;
    @FXML
    TextField address;
    @FXML
    DatePicker dateOfBirth;
    @FXML
    TextField phoneNumber;
    @FXML
    TextField password;
    @FXML
    TextField rewritePassword;
    @FXML
    CheckBox male;
    @FXML
    CheckBox female;
    @FXML
            DatePicker FromDateDP;
    @FXML
            DatePicker ToDateDP;
    @FXML
    private TableColumn<Purchase, Integer> purchaseNumCol;
    @FXML
    private TableColumn<Purchase, Integer> purchaseAmountCol;
    @FXML
    private TableColumn<Purchase, String> productsCol;
    @FXML
    private TableColumn<Purchase, Date> purchaseDateCol;

    @FXML
    private TableView<Purchase> purchaseInfoTable;
    @FXML
    BorderPane purInfoBorder;
    @FXML
    private BorderPane paymentInfoBorder;
    @FXML
    private TableView<Payment> paymentInfoTable;
    @FXML
    private TableColumn<Payment, Integer> paymentNumCol;
    @FXML
    private TableColumn<Payment, Integer> paymentAmountCol;
    @FXML
    private TableColumn<Payment, String> currencyCol;
    @FXML
    private TableColumn<Payment, Date> paymentDateCol;
    @FXML
            DatePicker FromDateDP1;
    @FXML
            DatePicker ToDateDP1;
    @FXML
    private TableView<CPhoneNumber> phoneNumbers;


    @FXML
    private TableColumn<CPhoneNumber, Integer> tableC1;

    @FXML
    private TableColumn<CPhoneNumber, String> tableC2;

    @FXML
    private TableColumn<CPhoneNumber, Button> tableC3;


    Connection con;
    Statement stmt ;
    @Override

    public void initialize (URL url, ResourceBundle resourceBundle){
        ImageView i=new ImageView(new Image(getClass().getResourceAsStream("send.png")));
        i.setFitHeight(30);
        i.setFitWidth(30);
        send.setGraphic(i);
        Product.cart=table2;
        table2C1.setCellValueFactory(new PropertyValueFactory<Product, String>("code"));
        table2C2.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        table2C3.setCellValueFactory(new PropertyValueFactory<Product, Spinner>("spin"));
        table2C4.setCellValueFactory(new PropertyValueFactory<Product, Button>("remove"));
        tableC5.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
        table2C6.setCellValueFactory(new PropertyValueFactory<Product, Integer>("prices"));
        purchaseNumCol.setCellValueFactory(new PropertyValueFactory<Purchase, Integer>("pNum"));
        purchaseAmountCol.setCellValueFactory(new PropertyValueFactory<Purchase, Integer>("pAmount"));
        productsCol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("productName"));
        purchaseDateCol.setCellValueFactory(new PropertyValueFactory<Purchase, Date>("pDate"));
        paymentNumCol.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("payId"));
        paymentAmountCol.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("payAmount"));
        currencyCol.setCellValueFactory(new PropertyValueFactory<Payment, String>("payCurrency"));
        paymentDateCol.setCellValueFactory(new PropertyValueFactory<Payment, Date>("payDate"));
        CPhoneNumber.phoneNumbers=phoneNumbers;
        tableC1.setCellValueFactory(new PropertyValueFactory<CPhoneNumber, Integer>("counter"));
        tableC2.setCellValueFactory(new PropertyValueFactory<CPhoneNumber, String>("phoneNum"));
        tableC3.setCellValueFactory(new PropertyValueFactory<CPhoneNumber, Button>("remove"));


        try {
            con = Connect.con;
            stmt = Connect.stmt;

            try {
                ResultSet rss= stmt.executeQuery("select message,username,sendDate from mailbox where sender='"+LogInSession.getInstance().getUsername()+"'");
                ArrayList<Mail> mailSent=new ArrayList<>();
                while (rss.next()){
                    mailSent.add(new Mail(rss.getString(1),rss.getString(2),rss.getDate(3).toLocalDate()));
                }
                table3C1.setCellValueFactory(new PropertyValueFactory<Mail, String>("username"));
                table3C2.setCellValueFactory(new PropertyValueFactory<Mail, String>("message"));
                table3C3.setCellValueFactory(new PropertyValueFactory<Mail, Date>("sendDate"));
                table3.getItems().addAll(mailSent);

                ResultSet rsss= stmt.executeQuery("select message,username,sendDate from mailbox where username='"+LogInSession.getInstance().getUsername()+"'");
                ArrayList<Mail> mailR=new ArrayList<>();
                while (rsss.next()){
                    mailR.add(new Mail(rsss.getString(1),rsss.getString(2),rsss.getDate(3).toLocalDate()));
                }
                table4C1.setCellValueFactory(new PropertyValueFactory<Mail, String>("username"));
                table4C2.setCellValueFactory(new PropertyValueFactory<Mail, String>("message"));
                table4C3.setCellValueFactory(new PropertyValueFactory<Mail, Date>("sendDate"));
                table4.getItems().addAll(mailR);



                ResultSet rs = stmt.executeQuery("select empName from employee");
                ArrayList<String> str = new ArrayList<>();
                while (rs.next()) {
                    str.add(rs.getString(1));
                }
                chooseE.getItems().addAll(str);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            BPexplore.toFront();
            SpinnerValueFactory<Integer> spf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
            spf.setValue(1);
            checkAmount.setValueFactory(spf);
            products = new ArrayList<>(getProducts());
            int row = 0;
            int col = 3;
            for (Product product : products) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ProductCard.fxml"));

                try {
                    Pane pane = fxmlLoader.load();

                    ProductController productController = fxmlLoader.getController();
                    productController.setCc(instance);
                    productController.setData(product);

                    if (col == 3) {
                        col = 0;
                        ++row;
                    }
                    productGrid.add(pane, col++, row);
                    GridPane.setMargin(pane, new Insets(20));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }catch (Exception e){
            System.out.println("connection with database is lost.");
        }
    }
    private ArrayList<Product> getProducts () {
        ArrayList<Product> ls = new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery("SELECT proName, proPrice, imgsrc, productCode FROM product;");

            while (resultSet.next()) {
                Product product = new Product();
                product.setName(resultSet.getString(1));
                product.setPrice(resultSet.getInt(2));
                product.setImg(resultSet.getString(3));
                product.setCode(resultSet.getString(4));
                ls.add(product);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ls;
    }
    public void backTPL(ActionEvent event) throws IOException {
        BPexplore.toFront();
    }
    public void order(ActionEvent event) throws IOException {
        try{

            ResultSet rs=stmt.executeQuery("select * from account_product where username='"+LogInSession.getInstance().getUsername()+"' and productCode in(select productCode from product where proName='"+proName.getText()+"');");

            if(!rs.next()){
                Product pro=new Product();
                for(Product p:products){
                    if(proName.getText().equals(p.getName())){
                        pro=p;
                        break;
                    }
                }
                try {

                    stmt.execute("Insert into account_product values ('"+LogInSession.getInstance().getUsername()+"', '"+pro.getCode()+"', "+checkAmount.getValue()+");");
                    errorLabel.setText("product is ordered successfully check your cart to confirm what you have ordered");
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            else{
                errorLabel.setText("product already in cart");
            }


        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    public void cart(ActionEvent event) throws IOException {

        try {
            ObservableList<Product> list = table2.getItems();
            list.clear();
            Statement stmt2=con.createStatement();
            ResultSet rs = stmt2.executeQuery("select * from account_product where username='" + LogInSession.getInstance().getUsername() + "'");

            while (rs.next()) {
                list.add(new Product(rs.getString(2), rs.getString(1), rs.getInt(3)));
                System.out.println(list.get(list.size()-1).code);
            }
            table2.setItems(list);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        B3.toFront();
    }
    public void clearAll(ActionEvent event) throws IOException {
        try{
            stmt.execute("delete from account_product where username='"+LogInSession.getInstance().getUsername()+"'");
            ObservableList<Product> list=table2.getItems();
            list.clear();
            table2.setItems(list);
            errorLabel.setText("cleared...");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void confirmCart(ActionEvent event) throws IOException {
        try{
            Statement stmt2= con.createStatement();
            ResultSet rs=stmt2.executeQuery("select * from account_product where username='"+LogInSession.getInstance().getUsername()+"'");
            while(rs.next()){
                ResultSet rs2=stmt.executeQuery("select count(purchaseNum) from purchase");
                rs2.next();
                int purNum=rs2.getInt(1)+1;
                stmt.execute("insert into purchase values ("+purNum+", '"+rs.getString(2)+"', '"+LocalDate.now()+"', "+LogInSession.getInstance().getTheUserID()+", "+rs.getInt(3)+");");

            }
            stmt.execute("delete from account_product where username='"+LogInSession.getInstance().getUsername()+"'");
            ObservableList<Product> l=table2.getItems();
            l.clear();
            table2.setItems(l);
            errorLabel.setText("products will get to your address soon");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void productsView(ActionEvent event) throws IOException {
        BPexplore.toFront();
    }
    public void newMessage(ActionEvent event) throws IOException {
        newMessageB.toFront();
    }
    public void mailBox(ActionEvent event) throws IOException {
        MailB.toFront();
    }
    public void backMail(ActionEvent event) throws IOException {
        MailB.toFront();
    }
    public void myAccountBtn (ActionEvent event){
        myAccountBorder.toFront();
        try {
            ResultSet rs = stmt.executeQuery("SELECT c.customerName, c.customerAddress, c.customerGender, c.customerDateOfBirth, a.password FROM customer AS c JOIN account AS a ON c.customerId = a.customerId WHERE c.customerId = "+LogInSession.getInstance().getTheUserID()+";");
            rs.next();
            String name = rs.getString("customerName");
            String addresss = rs.getString("customerAddress");
            String genderr = rs.getString("customerGender");
            String date = rs.getString("customerDateOfBirth");
            String pass = rs.getString("password");
            String Name[] = name.split(" ");
            firstName.setText(Name[0]);
            lastName.setText(Name[1]);
            address.setText(addresss);
            LocalDate datee = LocalDate.parse(date);
            dateOfBirth.setValue(datee);
            password.setText(pass);
            rewritePassword.setText(pass);
            if(genderr.equals("male")){
                male.setSelected(true);
            }else{
                female.setSelected(true);
            }
            ResultSet rs2 = stmt.executeQuery("SELECT cPhoneNum FROM customerphonenum WHERE customerId = "+LogInSession.getInstance().getTheUserID()+"; ");
            ObservableList<CPhoneNumber> cphonelist = FXCollections.observableArrayList();
            int counter = 1;
            System.out.println(counter);
            while (rs2.next()){
                String p = rs2.getString("cPhoneNum");
                CPhoneNumber phone  = new CPhoneNumber(p);
                cphonelist.add(phone);
            }
            phoneNumbers.getItems().addAll(cphonelist);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void updateCustomerInfo(ActionEvent event) throws IOException {
        try {
            if(firstName.getText().equals("") || lastName.getText().equals("") || address.getText().equals("") || dateOfBirth.getValue().toString().equals("") ) {
                errorLabel.setText("fill all text fields first");
            }
            else{
                if(password.getText().equals(rewritePassword.getText())) {
                    ResultSet rs = stmt.executeQuery("select * from customer where(customerId=" + LogInSession.getInstance().getTheUserID() + ");");
                    rs.next();
                    String[] name = rs.getString(2).split(" ");
                    name[0] = firstName.getText();
                    name[1] = lastName.getText();
                    String gender = (female.isSelected()) ? "female" : "male";
                    stmt.execute("update customer set customerName='" + name[0] + " " + name[1] + "', customerAddress = '" + address.getText() + "', customerDateOfBirth = '" + dateOfBirth.getValue().toString() + "', customerGender = '" + gender + "' where (customerId=" + LogInSession.getInstance().getTheUserID() + ");");

                    stmt.execute("UPDATE account set username = '" + name[0] + " " + name[1] + "', password = '" + password.getText() + "'where (customerId=" + LogInSession.getInstance().getTheUserID() + ");");

                }else{
                    errorLabel.setText("mismatch password!");
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    public void addNumber(ActionEvent event) throws IOException, SQLException {
        ObservableList<CPhoneNumber> list= phoneNumbers.getItems();
        if (phoneNumber.getText() == null) {
            errorLabel.setText("wrong number format");
        }
        else
            try {
                int d = Integer.parseInt(phoneNumber.getText());
                list.add(new CPhoneNumber(phoneNumber.getText()));
                phoneNumbers.setItems(list);
                stmt.execute("INSERT INTO customerphonenum VALUES ("+LogInSession.getInstance().getTheUserID()+", '"+phoneNumber.getText()+"');");
            } catch (NumberFormatException nfe) {
                errorLabel.setText("wrong number format");
            }

    }

    public void sendAction(ActionEvent event) throws IOException {
        try {
            ResultSet rs = stmt.executeQuery("select count(*) from mailbox");
            rs.next();
            int num = rs.getInt(1) + 1;
            stmt.execute("insert into mailbox (mailNum, message, username ,sendDate,sender) values(" + num + ", '" + messageTS.getText() + "', '" + chooseE.getValue() + "', '" + LocalDate.now() + "', '" + LogInSession.getInstance().getUsername() + "')");
            errorLabel.setText("message was sent successfully");
            table3.getItems().add(new Mail(messageTS.getText(), chooseE.getValue(), LocalDate.now()));
            table3.refresh();
            table4.refresh();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void showResultBtn (ActionEvent event){
        purchaseInfoTable.getItems().clear(); //dalete
        if (FromDateDP.getValue().equals("") || ToDateDP.getValue().equals("")){
            errorLabel.setText("make sure to fill all fields");
        }
        else{
            try {
                ResultSet rs = stmt.executeQuery("SELECT purchase.purchaseNum, purchase.purcahseAmount, product.proName, purchase.dateOfPurchase FROM purchase JOIN product ON purchase.productCode = product.productCode WHERE (customerId = "+LogInSession.getInstance().getTheUserID()+" and purchase.dateOfPurchase between '"+FromDateDP.getValue().toString()+"' and '"+ToDateDP.getValue().toString()+"');");
                ObservableList<Purchase> purchaseList = FXCollections.observableArrayList();
                while (rs.next()){
                    int pNumber = rs.getInt("purchaseNum");
                    int pAmount = rs.getInt("purcahseAmount");
                    String pName = rs.getString("proName");
                    java.sql.Date pDate = rs.getDate("dateOfPurchase");
                    Purchase purchase = new Purchase(pNumber,pAmount, pName, pDate);
                    purchaseList.add(purchase);
                }
                purchaseInfoTable.getItems().addAll(purchaseList);
                purchaseInfoTable.refresh();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void showPaymentResultBtn (ActionEvent event){
        paymentInfoTable.getItems().clear(); //dalete
        if (FromDateDP1.getValue().equals("") || ToDateDP1.getValue().equals("")){
            errorLabel.setText("make sure to fill all fields");
        }
        else{
            try {
                ResultSet rs = stmt.executeQuery("SELECT payId, amount, currency, payDate FROM payment WHERE (customerId = "+LogInSession.getInstance().getTheUserID()+" and payDate between '"+FromDateDP1.getValue().toString()+"' and '"+ToDateDP1.getValue().toString()+"');");
                ObservableList<Payment> paymentList = FXCollections.observableArrayList();
                while (rs.next()){
                    int pNumber = rs.getInt("payId");
                    int pAmount = rs.getInt("amount");
                    String pCurrency = rs.getString("currency");
                    java.sql.Date pDate = rs.getDate("payDate");
                    Payment payment = new Payment(pNumber,pAmount, pCurrency, pDate);
                    paymentList.add(payment);
                }
                paymentInfoTable.getItems().addAll(paymentList);
                paymentInfoTable.refresh();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void purchaseInfoBtn (ActionEvent event){
        purInfoBorder.toFront();
    }
    public void paymentInfoBtn (ActionEvent event){
        paymentInfoBorder.toFront();
    }
    public void backBtnInCustomer (ActionEvent event) throws IOException {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene=new Scene(FXMLLoader.load(getClass().getResource("hello-view.fxml")));
        stage.setScene(scene);
    }

}
