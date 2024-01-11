package com.example.ruchirap;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class playOperator {
    public void backTosquare1(ActionEvent event){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene menuScene = ((Node)event.getSource()).getScene();
        menuScene.setRoot(Main.getMenuFxmlLoad());
        stage.setScene(menuScene);
        stage.show();
    }
}
