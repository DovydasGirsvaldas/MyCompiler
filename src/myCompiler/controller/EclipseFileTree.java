package myCompiler.controller;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import myCompiler.model.FilePathTreeItem;

public class EclipseFileTree extends AnchorPane implements Initializable{

    public static final String WORKSPACE_URI = "C:\\Users\\Dovydas\\workspace";
    File currentDir;
    OpenFileInterface parent;

    @FXML
    private TreeView<String> filesTree;

    public EclipseFileTree(OpenFileInterface parent){
        this.parent= parent;
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("../layout/EclipseFileTree.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void findFiles(File dir, TreeItem<String> parent) {
        TreeItem<String> root = new FilePathTreeItem(dir.toPath());
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                findFiles(file, root);
            } else {
                root.getChildren().add(new FilePathTreeItem(file.toPath()));
            }
        }
        if (parent == null) {
            filesTree.setRoot(root);
        } else {
            parent.getChildren().add(root);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentDir = new File(WORKSPACE_URI);
        findFiles(currentDir, null);
        filesTree.setShowRoot(false);
        System.out.println("parent"+parent);
        filesTree.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                FilePathTreeItem item = (FilePathTreeItem) filesTree.getSelectionModel().getSelectedItem();
                if(mouseEvent.getClickCount() == 2 && !item.isDirectory()){
                    System.out.println(item.getFullPath());
                    parent.openFile(item.getFullPath());
                    // find parent, cast to openFile interface, let it handle it
                    //openTab(item.getFullPath());
                }
            }
        });
    }

    public interface OpenFileInterface{
        public void openFile(String path);
    }
}
