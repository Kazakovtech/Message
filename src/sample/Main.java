package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application {
public static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        stage = primaryStage;
        primaryStage.toFront();
        primaryStage.requestFocus();
        //primaryStage.setMaximized(true);
        primaryStage.setHeight(550);
        primaryStage.setWidth(500);
        primaryStage.setResizable(false);
//stage.getIcons().add(new Image("smile.jpg"));


        System.setOut(new java.io.PrintStream(System.out,true,"Cp866"));

    }
    public static Stage getStage(){
        return stage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
