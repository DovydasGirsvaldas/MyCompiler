package myCompiler.controller;

import java.net.URL;
import java.util.ResourceBundle;

import myCompiler.controller.EclipseFileTree.OpenFileInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable, OpenFileInterface{

    private EclipseCodingPane eclipseCodingPane;

    @FXML
    private BorderPane mainContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EclipseMenuBar eclipseMenuBar= new EclipseMenuBar();
        EclipseFileTree eclipseFileTree= new EclipseFileTree(this);
        eclipseCodingPane= new EclipseCodingPane();
        mainContainer.setTop(eclipseMenuBar);
        mainContainer.setLeft(eclipseFileTree);
        mainContainer.setCenter(eclipseCodingPane);

    }

    @Override
    public void openFile(String path) {
        eclipseCodingPane.openTab(path);
    }


    //			image1.setImage(new javafx.scene.image.Image(getClass().getResource("/resources/drawable/viva1.jpg").toExternalForm()));



}
