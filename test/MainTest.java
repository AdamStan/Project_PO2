package test; 
 import javafx.application.Application;
 import commands.*;
 import java.util.Scanner;
 import javafx.event.ActionEvent;
 import javafx.event.EventHandler;
 import javafx.scene.Scene;
 import javafx.scene.control.Button;
 import javafx.scene.layout.VBox;
 import javafx.scene.layout.StackPane;
 import javafx.scene.control.Label;
 import javafx.stage.Stage;
 import myownsql.Database;

public class MainTest extends Application {
    Stage window;
    Scene scene1;
    Button button1;
    
    public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("BazaDanych");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        Label label_1 = new Label("Welcome");
        button1 = new Button("Close Program");
        button1.setOnAction(e -> closeProgram());
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label_1, button1);
        scene1 = new Scene(layout1, 200, 200);
        
        window.setScene(scene1);
        window.show();
    }
    private void closeProgram(){
        boolean result = ConfirmBox.display("Exit", "Do you want close program?");
        if(result){
            System.out.println("File is saved");
            window.close();
        }
    }

}
