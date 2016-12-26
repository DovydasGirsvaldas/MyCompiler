package myCompiler.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import jdk.internal.util.xml.impl.Pair;
import myCompiler.customControls.TreeContexMenu;

import java.io.IOException;

/**
 * Created by Dovydas on 12/25/2016.
 */
public class CreationDialog {

    String name="";
    boolean isCreating;

    public CreationDialog(){
    }

    public DialogResult display(String message, Window window){
        try {
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(window);

            Parent root = FXMLLoader.load(getClass().getResource("../layout/DialogEnterName.fxml"));
            Scene dialogScene = new Scene(root);

            Label labelMsg = (Label) dialogScene.lookup("#labelMsg");
            TextField nameEntered= (TextField) dialogScene.lookup("#nameEntered");
            Button btnOK = (Button) dialogScene.lookup("#btnOk");
            Button btnCancel = (Button) dialogScene.lookup("#btnCancel");
            labelMsg.setText(message);
            btnOK.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    isCreating = true;
                    name = nameEntered.getText();
                    dialog.close();
                }
            });
            btnCancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    isCreating = false;
                    dialog.close();
                }
            });

            dialog.setScene(dialogScene);
            dialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new DialogResult(isCreating, name);
    }

    public final class DialogResult {
        public boolean isCreating;
        public String fileName;

        public DialogResult(boolean isCreating, String fileName) {
            this.isCreating = isCreating;
            this.fileName = fileName;
        }

    }
}
