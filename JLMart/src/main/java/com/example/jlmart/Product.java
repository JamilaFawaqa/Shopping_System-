package com.example.jlmart;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.ResultSet;
import java.sql.Statement;

public class Product {
    public String name;
    public String img;
    public String code;
    public int price;
    private String id;
    private Button remove;
    public static TableView<Product> cart;

    public Product(String code, int amount, String name ) {
        this.name = name;
        this.code = code;
        this.amount = amount;
    }

    public Button getRemove() {
        return remove;
    }

    public void setRemove(Button remove) {
        this.remove = remove;
    }

    public Spinner<Integer> getSpin() {
        return spin;
    }

    public void setSpin(Spinner<Integer> spin) {
        this.spin = spin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }
    private int prices;

    public int getPrices() {
        return prices;
    }

    public void setPrices(int prices) {
        this.prices = prices;
    }

    public Product(String code, String id, int amount) {
        remove=new Button();
        spin=new Spinner<>();
        this.code = code;
        this.id = id;
        this.amount = amount;
        SpinnerValueFactory<Integer> spf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        spf.setValue(amount);
        spin.setValueFactory(spf);
        Image img=new Image(getClass().getResourceAsStream("remove.png"));
        ImageView i=new ImageView(img);
        i.setFitHeight(20);
        i.setFitWidth(20);
        remove.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        remove.setGraphic(i);



        Statement stmt = Connect.stmt;
        try {
            ResultSet s = stmt.executeQuery("select proName from product where productCode='" + code + "'");
            s.next();
            name = s.getString(1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        remove.setOnAction(event -> {
            try {
                ObservableList<Product> list = cart.getItems();
                stmt.execute("delete from account_product where (username='" + id + "' and productCode='" + code + "');");
                list.remove(this);

                cart.setItems(list);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        });
        try{
            ResultSet rs=stmt.executeQuery("select proPrice from product where productCode='"+code+"'");
            rs.next();
            price=rs.getInt(1);
            prices=price*amount;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        spin.valueProperty().addListener((observable, oldValue, newValue) -> {
            try {
                stmt.execute("update account_product set amountInCart = " + newValue + " where (username='" + id + "' and productCode='" + code + "');");
                ObservableList<Product> l = cart.getItems();
                l.get(l.indexOf(this)).setPrices(price * spin.getValue());
                cart.refresh();
                cart.setItems(l);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        });


    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    private Spinner<Integer> spin;
    private int amount;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Product() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
