package myCompiler.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import myCompiler.controller.EclipseFileTree.OpenFileInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable, OpenFileInterface{

    private EclipseCodingPane eclipseCodingPane;
    private EclipseFileTree eclipseFileTree;

    @FXML
    private BorderPane mainContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EclipseMenuBar eclipseMenuBar= new EclipseMenuBar(this);
        eclipseFileTree= new EclipseFileTree(this);
        eclipseCodingPane= new EclipseCodingPane();
        mainContainer.setTop(eclipseMenuBar);
        mainContainer.setLeft(eclipseFileTree);
        mainContainer.setCenter(eclipseCodingPane);

    }

    @Override
    public void openFile(String path) {
        eclipseCodingPane.openTab(path);
    }

    public void addTreeItem(File file){
        eclipseFileTree.addTreeItem(file);
    }

}
