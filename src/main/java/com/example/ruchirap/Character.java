package com.example.ruchirap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.animation.Animation;
import java.util.ArrayList;

public class Character {
    private static final ImageView stickHeroHolder = new ImageView();

    public static int getTheScore() {
        return currScore;
    }

    private static int currScore=0;
    private static final Line line = new Line();
    private static boolean flipped =false;
    private static boolean isMoving =false;
    static private final double imageHeight=35;

    public static void addStickHero(AnchorPane anchorPane){
        double imageWidth = 30;
        if(isMoving) isMoving =false;
        double imageLayX=Map.getFirstRect().getWidth()-imageWidth;
        double imageLayY=300-imageHeight;
        Image characterImage = new Image("C:\\Users\\Tanishq\\Desktop\\APPROJECT_2022419_2022533\\src\\main\\resources\\com\\example\\ruchirap\\char.png");
        stickHeroHolder.setLayoutX(imageLayX);
        stickHeroHolder.setLayoutY(imageLayY);
        stickHeroHolder.setFitHeight(imageHeight);
        stickHeroHolder.setFitWidth(imageWidth);
        stickHeroHolder.setImage(characterImage);
        anchorPane.getChildren().add(stickHeroHolder);
    }
    public static void addStick(AnchorPane anchorPane){
        double startX = Map.getFirstRect().getWidth();
        double startY = 300;
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(startX);
        line.setEndY(startY);
        line.setStroke(Color.RED);
        line.setStrokeWidth(5);
        anchorPane.getChildren().add(line);
    }
    public static void enlargeStick(){
        line.setEndY(line.getEndY()-5);
    }
    public static void rotateStick(){
        isMoving=true;
        Timeline timeline = new Timeline();
        double length = line.getStartY()- line.getEndY();
        KeyValue endXvalue = new KeyValue(line.endXProperty(), line.getStartX()+length);
        KeyValue endYvalue = new KeyValue(line.endYProperty(),300);
        KeyFrame keyframe = new KeyFrame(Duration.seconds(0.5),endXvalue,endYvalue);
        timeline.getKeyFrames().add(keyframe);
        timeline.setOnFinished(event -> {Character.moving(Main.playScreenPane());});
        timeline.play();
    }
    public static void fall(AnchorPane anchorPane){
        KeyValue fall = new KeyValue(stickHeroHolder.layoutYProperty(),500);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5),fall);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event -> {
            anchorPane.getChildren().remove(stickHeroHolder);
            anchorPane.getChildren().remove(line);
            Map.clearMap(anchorPane);
            reset(anchorPane);
            Map.clearMap(anchorPane);
        Map.firstRender(anchorPane);});
        timeline.play();
    }
    public static void changeImageX(boolean b, AnchorPane anchorPane){
        if(b){
            KeyValue walk = new KeyValue(stickHeroHolder.layoutXProperty(), line.getEndX());
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(2),walk);
            KeyFrame checkForCherry = new KeyFrame(Duration.millis(100));
            Timeline cherryChecker = new Timeline();
            cherryChecker.getKeyFrames().add(checkForCherry);
            cherryChecker.setCycleCount(Animation.INDEFINITE);
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(keyFrame);
            cherryChecker.play();
            timeline.setOnFinished(event -> {
                if(flipped){
                    anchorPane.getChildren().remove(stickHeroHolder);
                    anchorPane.getChildren().remove(line);
                    Map.clearMap(anchorPane);
                    Map.firstRender(anchorPane);
                }else {
                    Map.advanceMap(anchorPane);
                }
                cherryChecker.stop();
            });
            timeline.play();
        }
        else{
            anchorPane.getChildren().remove(line);
            KeyValue walk = new KeyValue(stickHeroHolder.layoutXProperty(),0);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(2),walk);
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(keyFrame);
            timeline.setOnFinished(event -> {
                Character.addStick(anchorPane);
                isMoving =false;});
            timeline.play();
        }
    }
    public static void moving(AnchorPane anchorPane){   //change
        ArrayList<Double> lengthBounds = new ArrayList<>();
        lengthBounds.add(Map.getSecondRec().getLayoutX()-Map.getFirstRect().getLayoutX()-Map.getFirstRect().getWidth());
        lengthBounds.add(Map.getSecondRec().getLayoutX()+Map.getSecondRec().getWidth()-Map.getFirstRect().getLayoutX()-Map.getFirstRect().getWidth());
        double stickLength=-line.getStartX()+ line.getEndX();
        if(lengthBounds.get(0)<=stickLength && lengthBounds.get(1)>= stickLength){
            Character.changeImageX(true,anchorPane);
            Character.currScore++;
        }
        else{
            isMoving =true;
            KeyValue walk = new KeyValue(stickHeroHolder.layoutXProperty(), line.getEndX());
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(2),walk);
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(keyFrame);
            timeline.setOnFinished(event -> {Character.fall(anchorPane);});
            timeline.play();
        }
    }
    public static void invertImage(){
        if(!(isMoving)){
            return;
        }
        flipped =true;
        double imageLayY=300;
        stickHeroHolder.setLayoutY(imageLayY);
        stickHeroHolder.setImage(new Image("C:\\Users\\Tanishq\\Desktop\\APPROJECT_2022419_2022533\\src\\main\\resources\\com\\example\\ruchirap\\Hcar.png"));
    }
    public static void revertImage(){
        if(!(isMoving)){
            return;
        }
        flipped =false;
        double new_ycord=300-imageHeight;
        stickHeroHolder.setLayoutY(new_ycord);
        stickHeroHolder.setImage(new Image("C:\\Users\\Tanishq\\Desktop\\APPROJECT_2022419_2022533\\src\\main\\resources\\com\\example\\ruchirap\\Char.png"));
    }

    public static ImageView getStickHeroHolder() {
        return stickHeroHolder;
    }
    public static void reset(AnchorPane anchorPane){
        try{
            anchorPane.getChildren().remove(line);
            anchorPane.getChildren().remove(stickHeroHolder);
            Map.clearMap(anchorPane);
        }
        catch (Exception ignored){

        }
    }
    public static boolean isDown() {
        return flipped;
    }
    public static boolean isMoving() {
        return isMoving;
    }
}

