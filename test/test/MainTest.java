package test; 
 import javafx.application.Application;
 import java.util.Scanner;
 import javafx.event.ActionEvent;
 import javafx.event.EventHandler;
 import javafx.geometry.Insets;
 import javafx.scene.Scene;
 import javafx.scene.control.Button;
 import javafx.scene.layout.*;
 import javafx.scene.control.Label;
 import javafx.scene.control.CheckBox;
 import javafx.scene.control.ComboBox;
 import javafx.scene.control.TextField;
 import javafx.stage.Stage;

public class MainTest extends Application {
    Stage window;
    Scene scene;
    Button button;
    ComboBox<String> combobox;
    
    public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("BazaDanych");
        
        combobox = new ComboBox<>();
        combobox.getItems().addAll(
                "Good Will Hunting",
                "It",
                "Something new"
        );
        combobox.setPromptText("What is your favorite movie?");
        combobox.setEditable(true);
        combobox.setOnAction(e -> System.out.println("User selected: " + combobox.getValue()) );
        button = new Button("Clik me");
        button.setOnAction( e -> printMovie() );
        
        
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10,10,10,10));
        layout.getChildren().addAll(combobox, button);
        
        scene = new Scene(layout, 500, 400);
        window.setScene(scene);
        window.show();
    }
    private void printMovie(){
        System.out.println(combobox.getValue());
    }
    
    private void handleOptions(CheckBox box1, CheckBox box2){
         String message = "";
         if(box1.isSelected()){
             message += box1.getText() + "\n";
         }
         if(box2.isSelected()){
             message += box2.getText() + "\n";
         }
         System.out.print(message);
    }
    private boolean isInt(TextField txt, String message){
        try{
            int value = Integer.parseInt(txt.getText());
            System.out.println("Liczba");
            return true;
        } catch(NumberFormatException e) {
            System.out.println("Jakies znaczki");
            return false;
        }
    }
    private void closeProgram(){
        boolean result = ConfirmBox.display("Exit", "Do you want close program?");
        if(result){
            System.out.println("File is saved");
            window.close();
        }
    }
    
    /*
    ChoiceBox <String> choiceBox = new ChoiceBox<>();
    choiceBox.getItems().add("Apple");
    choiceBox.getItems().add("Banana");
    choiceBox.getItems().addAll("Cucumber", "Raw");

    choiceBox.setValue("Banana");

    button = new Button("Clik me");

    choiceBox.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> 
        System.out.println(newValue) );

    button.setOnAction(e -> {
        System.out.println(choiceBox.getValue());
    });

    VBox layout = new VBox(10);
    layout.setPadding(new Insets(10,10,10,10));
    layout.getChildren().addAll(choiceBox, button);
    */
    
    /*A, TAKIE TAM:
    TextField nameInput = new TextField();
    button = new Button("Clik me");
    button.setOnAction(e -> isInt(nameInput, nameInput.getText()) );

    VBox layout = new VBox(10);
    layout.setPadding(new Insets(10,10,10,10));
    layout.getChildren().addAll(nameInput, button);
    scene = new Scene(layout, 500, 400);
    */
    
    /*BARDZO FAJNE LOGOWANIE
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10,10,10,10));
    grid.setVgap(8);
    grid.setHgap(10);
    //Name fields
    Label nameLabel = new Label("Username: ");
    GridPane.setConstraints(nameLabel, 0, 0); //column, row
    TextField nameInput = new TextField("Jan");
    GridPane.setConstraints(nameInput, 1, 0);
    //Password fields
    Label passwordLabel = new Label("Password: ");
    GridPane.setConstraints(passwordLabel, 0, 1);
    TextField passwordInput = new TextField();
    passwordInput.setPromptText("password");
    GridPane.setConstraints(passwordInput, 1, 1);
    //Button
    Button loginButton = new Button("Log in");
    GridPane.setConstraints(loginButton, 2, 2);

    grid.getChildren().addAll(nameLabel, nameInput, passwordLabel,
            passwordInput, loginButton);
    */
    
    /*
    fajne eventy + może się to przydać
    
    window.setOnCloseRequest(e -> {
        e.consume();
        closeProgram();
    });

    HBox topMenu = new HBox();
    Button buttonA = new Button("File");
    Button buttonB = new Button("Edit");
    Button buttonC = new Button("View");
    topMenu.getChildren().addAll(buttonA, buttonB, buttonC);

    VBox leftMenu = new VBox();
    Button buttonD = new Button("D");
    Button buttonE = new Button("E");
    Button buttonF = new Button("F");
    leftMenu.getChildren().addAll(buttonD, buttonE, buttonF);

    BorderPane border = new BorderPane();
    border.setTop(topMenu);
    border.setLeft(leftMenu);

    Label label_1 = new Label("Welcome");
    button1 = new Button("Close Program");
    button1.setOnAction(e -> closeProgram());

    StackPane layout = new StackPane();
    layout.getChildren().addAll(label_1, button1);
    scene1 = new Scene(border, 500, 400);
    window.setScene(scene1);
    */

}
