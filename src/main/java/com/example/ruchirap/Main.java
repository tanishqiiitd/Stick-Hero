package com.example.ruchirap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    static Parent menuFxmlLoad;
    static {
        try {
            menuFxmlLoad = new FXMLLoader(Main.class.getResource("menu.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static Parent playFxmlload;

    static {
        try {
            playFxmlload = new FXMLLoader(Main.class.getResource("playWindow.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Parent getMenuFxmlLoad() {
        return menuFxmlLoad;
    }

    public static Parent getPlayFxmlload() {
        return playFxmlload;
    }
    public static AnchorPane playScreenPane(){
        Parent root = getPlayFxmlload();
        return (AnchorPane) root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }
}