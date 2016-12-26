package myCompiler.customControls;

import com.sun.jnlp.ApiDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import myCompiler.controller.ActionDialog;
import myCompiler.controller.CreationDialog;
import myCompiler.controller.EclipseFileTree;
import myCompiler.model.FilePathTreeItem;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by Dovydas on 12/25/2016.
 */
public class TreeContexMenu extends ContextMenu {

    public enum NewFile{ JAVA, XML, FILE, FOLDER};
    TreeView filesTree;

    public TreeContexMenu(TreeView filesTree, EclipseFileTree.contextElementType type) {
        this.filesTree = filesTree;
        createMenuItems(type);
    }


    private void createMenuItems(EclipseFileTree.contextElementType type) {
    if (type == EclipseFileTree.contextElementType.DIRECTORY) {
        Menu newSubMenu = new Menu("New");
        MenuItem javaMenuItem = new MenuItem("Java class");
        javaMenuItem.setOnAction(event -> {
            newFileDialog(NewFile.JAVA);
        });
        MenuItem xmlMenuItem = new MenuItem("XML file");
        xmlMenuItem.setOnAction(event -> {
            newFileDialog(NewFile.XML);
        });
        MenuItem fileMenuItem = new MenuItem("File");
        fileMenuItem.setOnAction(event -> {
            newFileDialog(NewFile.FILE);
        });
        MenuItem folderMenuItem = new MenuItem("Folder");
        folderMenuItem.setOnAction(event -> {
            newFileDialog(NewFile.FOLDER);
        });
        newSubMenu.getItems().addAll(javaMenuItem, xmlMenuItem, fileMenuItem, folderMenuItem);
        getItems().add(newSubMenu);
    }
    MenuItem deleteMenuItem = new MenuItem("Delete...");
    deleteMenuItem.setOnAction(event -> {
        menuPickedDelete();
    });
    getItems().add(deleteMenuItem);
    }

    private void newFileDialog(NewFile type){
        String extention= "";
        String message= "";
        switch (type){
            case JAVA:
                extention = ".java";
                message = "Enter the name of new Java class";
                break;
            case FILE:
                extention = "";
                message = "Enter the name of new file";
                break;
            case XML:
                extention = ".xml";
                message = "Enter the name of new XML file";
                break;
            case FOLDER:
                extention = "";
                message = "Enter the name of new package";
                break;
        }
        CreationDialog dialog = new CreationDialog();
        CreationDialog.DialogResult result = dialog.display(message, getOwnerWindow());
        if (result.isCreating){
            result.fileName = result.fileName + extention;
            createNewFile(result.fileName, type);
        }
    }

    private void createNewFile(String fileName, NewFile type) {
        TreeItem c = (TreeItem) filesTree.getSelectionModel().getSelectedItem();
        FilePathTreeItem item = (FilePathTreeItem) c;
        String path = item.getFullPath().toString()+"\\"+fileName;
        System.out.println(""+path);
        File file = new File(path);
        if (item.isDirectory()){
            if (type.equals(NewFile.FOLDER)){
                file.mkdirs();
            }else{
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    return;
                }
            }
            c.getChildren().add(new FilePathTreeItem(file.toPath()));
        }
    }

    private void menuPickedDelete(){
        TreeItem c = (TreeItem) filesTree.getSelectionModel().getSelectedItem();
        FilePathTreeItem item = (FilePathTreeItem) c;
        ActionDialog actionDialog = new ActionDialog();
        boolean answer = actionDialog.display("Delete "+item.getFullPath().getFileName()+ " ?", getOwnerWindow());
        if (answer){
            File file = new File(item.getFullPath().toString());
            deleteDirectory(file);
            c.getParent().getChildren().remove(c);
        }
    }

    private void deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            File[] children = dir.listFiles();
            for (int i = 0; i < children.length; i++) {
                deleteDirectory(children[i]);
            }
        }
            dir.delete();
    }

}
