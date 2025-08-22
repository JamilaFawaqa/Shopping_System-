package com.example.jlmart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class ProductController {
    @FXML
    private ImageView img;

    @FXML
    private Label name;

    @FXML
    private Label price;
    public CustomerController cc;
    private Image imge;
    private Product pro;

    public CustomerController getCc() {
        return cc;
    }

    public void setCc(CustomerController cc) {
        this.cc = cc;
    }

    public void setData(Product p){
        pro=p;
        try {
            Image i = new Image(getClass().getResourceAsStream(p.getImg()));
            imge=i;

            img.setImage(i);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        name.setText(p.getName());
        price.setText(String.valueOf(p.getPrice()));
    }
    public void openProduct(ActionEvent event) throws IOException {
        cc.productTB.setImage(imge);
        cc.proName.setText( pro.getName());
        cc.price.setText(String.valueOf(pro.getPrice()));
        cc.BPorder.toFront();
    }
}
