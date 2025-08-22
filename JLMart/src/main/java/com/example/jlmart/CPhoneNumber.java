package com.example.jlmart;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CPhoneNumber {
    private String phoneNum;
    private static int count=0;
    private static int counter;
    private Button remove;
    public static TableView<CPhoneNumber> phoneNumbers;

    public CPhoneNumber(String phoneNum) {
        this.phoneNum = phoneNum;
        setCount(count+1);
        counter=count;
        remove=new Button();
        remove.setPrefSize(20,20);
        Image img=new Image(getClass().getResourceAsStream("remove.png"));
        ImageView i=new ImageView(img);
        i.setFitHeight(20);
        i.setFitWidth(20);
        remove.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        remove.setGraphic(i);
        remove.setOnAction(event -> {
            ObservableList<CPhoneNumber> list=phoneNumbers.getItems();
            list.remove(this);
            phoneNumbers.setItems(list);

        });

    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCount(int count) {
        CPhoneNumber.count = count;
    }

    public Button getRemove() {
        return remove;
    }

    public void setRemove(Button remove) {
        this.remove = remove;
    }

    public static int getCount() {
        return count;
    }
}
