package myCompiler.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class EclipseMenuBar extends AnchorPane implements Initializable {

    private MainController mainController;

    @FXML
    private HBox containerHbox;
    @FXML
    private MenuItem newPackage;

    public EclipseMenuBar(MainController mainController) {
        this.mainController = mainController;
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("../layout/EclipseMenuBar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
            }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newPackage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String message = "Enter a name for new package";
                CreationDialog dialog = new CreationDialog();
                CreationDialog.DialogResult result = dialog.display(message, null );
                if (result.isCreating){
                    File file = new File(EclipseFileTree.WORKSPACE_URI+"\\"+result.fileName);
                    file.mkdirs();
                    mainController.addTreeItem(file);
                }
            }
        });
    }
}
