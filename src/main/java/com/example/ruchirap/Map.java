package com.example.ruchirap;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;


public class Map {
    private static final Label ScoreLabel=new Label();
    private static final Label ScoreValue=new Label();
    private static final double rectangleHeight = 200;
    static int returnCount = 0;
    static private final ImageView cherryImageContainer = new ImageView();
    static private final Random random = new Random();
    static private final Rectangle pivotPlatform = new Rectangle();
    static private final Rectangle targetPLatform = new Rectangle();
    static private double distance;

    static private void pivotPlatform() {
        pivotPlatform.setHeight(rectangleHeight);
        pivotPlatform.setWidth(50);
        pivotPlatform.setLayoutX(0);
        pivotPlatform.setLayoutY(300);
    }

    static private void targetPLatform(Rectangle rectangle1, Rectangle rectangle2) {
        rectangle2.setHeight(rectangleHeight);
        double width = random.nextDouble(50, 100);
        double layX = random.nextDouble(rectangle1.getWidth() + 30, rectangle1.getWidth() + 120);
        rectangle2.setWidth(width);
        rectangle2.setLayoutX(layX);
        rectangle2.setLayoutY(300);
        distance = rectangle2.getLayoutX() - rectangle1.getWidth();
    }

    static public void firstRender(AnchorPane anchorPane) {
        if (returnCount != 0) {
            returnCount = 0;
        }
        ScoreLabel.setTextFill(Color.WHITE);
        ScoreLabel.setText("Score");
        ScoreLabel.setLayoutX(183);
        ScoreLabel.setLayoutY(5);
        ScoreLabel.setPrefHeight(21);
        ScoreLabel.setPrefWidth(40);
        anchorPane.getChildren().add(ScoreLabel);
        ScoreValue.setTextFill(Color.WHITE);
        ScoreValue.setText("0");
        ScoreValue.setLayoutX(239);
        ScoreValue.setLayoutY(5);
        ScoreValue.setPrefHeight(10);
        ScoreValue.setPrefWidth(5);
        anchorPane.getChildren().add(ScoreValue);
        pivotPlatform();
        targetPLatform(pivotPlatform, targetPLatform);
        anchorPane.getChildren().add(pivotPlatform);
        anchorPane.getChildren().add(targetPLatform);
        Character.addStickHero(anchorPane);
        Character.addStick(anchorPane);
    }

    static private void removeStage(AnchorPane anchorPane) {
        if (returnCount % 2 == 0) {
            anchorPane.getChildren().remove(pivotPlatform);
        } else {
            anchorPane.getChildren().remove(targetPLatform);
        }
    }

    static private void addStage(AnchorPane anchorPane) {
        if (returnCount % 2 == 0) {
            targetPLatform(targetPLatform, pivotPlatform);
            anchorPane.getChildren().add(pivotPlatform);
        } else {
            targetPLatform(pivotPlatform, targetPLatform);
            anchorPane.getChildren().add(targetPLatform);
        }
    }

    static public void advanceMap(AnchorPane anchorPane) {
        removeStage(anchorPane);
        Rectangle animatedPlatform;
        if (returnCount % 2 == 0) {
            animatedPlatform = targetPLatform;
        } else {
            animatedPlatform = pivotPlatform;
        }
        double distance = 0 - animatedPlatform.getLayoutX();
        KeyValue keyValue = new KeyValue(animatedPlatform.layoutXProperty(), animatedPlatform.getLayoutX() + distance);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), keyValue);
        Timeline timeline = new Timeline(keyFrame);
        timeline.setOnFinished(event -> {
            addStage(anchorPane);
            returnCount++;
        });
        timeline.play();
        Character.changeImageX(false,anchorPane);
        if(Character.getTheScore() != Integer.parseInt(ScoreValue.getText())){
            ScoreValue.setText(String.valueOf(Character.getTheScore()));
        }
    }

    public static Rectangle getFirstRect() {
        if (returnCount % 2 == 0) {
            return pivotPlatform;
        }
        return targetPLatform;
    }

    public static Rectangle getSecondRec() {
        if (returnCount % 2 == 0) {
            return targetPLatform;
        }
        return pivotPlatform;
    }


    static public void clearMap(AnchorPane anchorPane) {
        try {
            anchorPane.getChildren().remove(pivotPlatform);
            anchorPane.getChildren().remove(targetPLatform);
            anchorPane.getChildren().remove(ScoreLabel);
            anchorPane.getChildren().remove(ScoreValue);
        } catch (Exception ignored) {

        }
    }

}
