package myCompiler.controller;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import myCompiler.customControls.TreeContexMenu;
import myCompiler.model.FilePathTreeItem;

public class EclipseFileTree extends AnchorPane implements Initializable{

    public  enum contextElementType{DIRECTORY, FILE}
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
                FilePathTreeItem item = new FilePathTreeItem(file.toPath());
                root.getChildren().add(item);
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
        filesTree.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                FilePathTreeItem item = (FilePathTreeItem) filesTree.getSelectionModel().getSelectedItem();
                if (mouseEvent.getButton() == MouseButton.SECONDARY){
                    ContextMenu contextMenu = null;
                    if (item!=null) {
                        if (item.isDirectory()) {
                            contextMenu = new TreeContexMenu(filesTree, contextElementType.DIRECTORY);
                        } else {
                            contextMenu = new TreeContexMenu(filesTree, contextElementType.FILE);
                        }
                        filesTree.setContextMenu(contextMenu);
                        contextMenu.show(filesTree, mouseEvent.getScreenX(), mouseEvent.getScreenY());
                    }
                    filesTree.setContextMenu(null);
                }else {
                    if (mouseEvent.getClickCount() == 2 && !item.isDirectory()) {
                        System.out.println(item.getFullPath());
                        parent.openFile(item.getFullPath().toString());
                    }
                }
                }
        });
    }

    public void addTreeItem(File file) {
        filesTree.getRoot().getChildren().add(new FilePathTreeItem(file.toPath()));
    }

    public interface OpenFileInterface{
        void openFile(String path);
    }
}
