package myCompiler.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Dovydas on 12/25/2016.
 */
public class ActionDialog{

        boolean choice;

    public ActionDialog(){

    }

    public boolean display(String message, Window window ){
        try {
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(window);

            Parent root = FXMLLoader.load(getClass().getResource("../layout/ActionDialog.fxml"));
            Scene dialogScene = new Scene(root);

            Label labelMsg = (Label) dialogScene.lookup("#labelMsg");
            Button btnOK = (Button) dialogScene.lookup("#btnOK");
            Button btnCancel = (Button) dialogScene.lookup("#btnCancel");
            labelMsg.setText(message);
            btnOK.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    choice = true;
                    dialog.close();
                }
            });
            btnCancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    choice = false;
                    dialog.close();
                }
            });

            dialog.setScene(dialogScene);
            dialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return choice;
    }

}
