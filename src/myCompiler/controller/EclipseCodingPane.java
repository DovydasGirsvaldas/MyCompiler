package myCompiler.controller;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class EclipseCodingPane extends AnchorPane  implements Initializable{

    @FXML
    private TabPane codingPane;

    public EclipseCodingPane(){
        FXMLLoader fxmlLoader=new FXMLLoader(
                getClass().getResource("../layout/EclipseCodingPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Tab openTab(String path){
        Tab tab = new Tab();
        CodeTab pane = new CodeTab(path);
        tab.setContent(pane);
        tab.setClosable(true);
        Label tabALabel = new Label(Paths.get(path).getFileName().toString());
        tab.setGraphic(tabALabel);
        tab.setOnCloseRequest(new EventHandler<Event>()
        {
            @Override
            public void handle(Event arg0)
            {
                pane.saveFileChanges();
            }
        });
        codingPane.getTabs().add(tab);
        codingPane.getSelectionModel().select(tab);
        return tab;
    }



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
       // openTab("C:\\Users\\Dovydas\\workspace\\TestingFX\\src\\application\\Main.java");
    }

}
