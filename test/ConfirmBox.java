package test;
 import javafx.stage.*;
 import javafx.scene.*;
 import javafx.scene.layout.*;
 import javafx.scene.control.*;
 import javafx.geometry.*;

public class ConfirmBox {
    static boolean answer;
    
    public static boolean display(String title, String message){
        Stage window = new Stage();
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        
        Label label = new Label();
        label.setText(message);
        
        Button yesButton = new Button("YES");
        Button noButton = new Button("NO");
        
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });
        
        VBox layout = new VBox(10); //Why ten?
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.BASELINE_CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
        return answer;
    }
}
