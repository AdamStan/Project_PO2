package GUI; 
 import javafx.application.Application;
 import commands.*;
 import inout.IOclass;
 import java.util.Scanner;
 import javafx.event.ActionEvent;
 import javafx.event.EventHandler;
 import javafx.geometry.Insets;
 import javafx.scene.Scene;
 import javafx.scene.control.*;
 import javafx.scene.layout.*;
 import javafx.stage.Stage;
 import myownsql.Database;

public class MainWindow extends Application {
    Database data;
    Stage window;
    Scene scene;
    Button buttonLogin, buttonSkip, buttonExit;

    public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        //basic things:
        window = primaryStage;
        window.setTitle("BazaDanych");
        window.setOnCloseRequest(e -> {
            e.consume(); //without this window will close without asking
            closeProgram();
        });
        //Using GridPane:
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10)); //space from edges
        grid.setVgap(10); //space between rows
        grid.setHgap(10); //space between columns
        //Things which we want to add to our GridPane:
        Label nameLabel = new Label("Database: ");
        GridPane.setConstraints(nameLabel, 0, 0); //column, row
        TextField nameInput = new TextField();
        nameInput.setPromptText("Nazwa pliku bez .txt");
        GridPane.setConstraints(nameInput, 0, 1);
        //Buttons in one layout
        HBox bottomMenu = new HBox();
        buttonLogin = new Button("Log in");
        buttonLogin.setOnAction(e -> Logowanie(nameInput.getText()) );
        buttonSkip = new Button("Skip");
        buttonSkip.setOnAction(e -> {
            if(data == null) Logowanie("tymczasowa");
            else forData();
        });
        buttonExit = new Button("Exit");
        buttonExit.setOnAction(e -> closeProgram() );
        bottomMenu.getChildren().addAll(buttonLogin, 
                buttonSkip, buttonExit);
        GridPane.setConstraints(bottomMenu, 0, 2);
        //Adding all to grid:
        grid.getChildren().addAll(nameLabel, nameInput, bottomMenu);
        //Basic end:
        scene = new Scene(grid, 400, 400);
        window.setScene(scene);
        window.show();
    }
    private void closeProgram(){
        boolean result = ConfirmBox.display("Exit", "Do you want close program?");
        if(result){
            System.out.println("File is saved");
            window.close();
        }
    }
    private void Logowanie(String nameDatabase){
        try{
            data = IOclass.readObjects(nameDatabase);
            AlertBox.display("Warming", "Load database: " + nameDatabase);
        } catch(Exception e) {
            AlertBox.display("Error", "We don't have database: " + nameDatabase );
            data = new Database(nameDatabase);
            IOclass.writeObjects(data);
            AlertBox.display("Warming", "Database " + nameDatabase + " was created");
        } finally {
            forData();
        }
    }
    private void forData(){
        GridPane gridNew = new GridPane();
        gridNew.setPadding(new Insets(10,10,10,10)); //space from edges
        gridNew.setVgap(10); //space between rows
        gridNew.setHgap(10); //space between columns
        Button buttonOpis = new Button("Description Database");
        buttonOpis.setOnAction(e -> data.opis());
        Button buttonBack = new Button("Back");
        buttonBack.setOnAction(e -> this.start(window));
        GridPane.setConstraints(buttonOpis, 0, 0);
        GridPane.setConstraints(buttonBack, 0, 1);
        GridPane.setConstraints(buttonExit, 0, 2);
        gridNew.getChildren().addAll(buttonOpis, buttonExit, buttonBack);
        Scene scene = new Scene(gridNew, 400, 400);
        window.setScene(scene);
        window.show();
    }
}
