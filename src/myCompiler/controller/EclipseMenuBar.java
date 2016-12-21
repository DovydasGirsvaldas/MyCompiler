package myCompiler.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class EclipseMenuBar extends AnchorPane {

    @FXML
    private HBox containerHbox;

    public EclipseMenuBar() {
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
}
