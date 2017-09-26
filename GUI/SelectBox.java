package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SelectBox {
    public static void display(String message){
        Stage window = new Stage();
        window.setTitle("Select");
        
        Label label_description = new Label();
        label_description.setText("Result of Selection: ");
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e-> window.close());
        Label label_message = new Label();
        label_message.setText(message);
        
        VBox layout = new VBox(10); //Why ten?
        layout.setPadding(new Insets(10,10,10,10));
        layout.getChildren().addAll(label_description,label_message, closeButton);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
