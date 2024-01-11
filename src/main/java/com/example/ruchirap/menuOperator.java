package com.example.ruchirap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class menuOperator {
    public void loadGameScreen(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene playscene = ((Node) event.getSource()).getScene();
        playscene.setRoot(Main.getPlayFxmlload());
        Main.getPlayFxmlload().requestFocus();
        playscene.setOnKeyPressed(event1 -> {
            if(event1.getCode()==KeyCode.SPACE){
                if(!Character.isMoving()){
                    Character.enlargeStick();
                }
                else if(!Character.isDown()){
                    Character.invertImage();
                }
                else{
                    Character.revertImage();
                }
            }
        });
        playscene.setOnKeyReleased(e -> {
            if(e.getCode()==KeyCode.SPACE && (!Character.isMoving()) ){
                Character.rotateStick();
            }
        });
        Map.firstRender(Main.playScreenPane());
        stage.show();
    }
}