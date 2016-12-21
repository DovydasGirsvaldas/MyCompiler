package myCompiler.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class CodeTab extends AnchorPane implements Initializable{

    @FXML
    private TextArea lineNumberArea;

    @FXML
    private TextArea contentArea;

    @FXML
    private Tab tab;

    public CodeTab(String filePath) {
        super();
        FXMLLoader fxmlLoader=new FXMLLoader(
                getClass().getResource("../layout/CodeTab.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        File file= new File(filePath);
        String newLine = System.getProperty("line.separator");
        int lineCounter=0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineCounter++;
                contentArea.appendText(line+newLine);
                lineNumberArea.appendText(lineCounter+newLine);
            }
        } catch (FileNotFoundException e) {
            contentArea.appendText("Error while opening: file not found");
        } catch (IOException e) {
            contentArea.appendText("Error while opening: IOException");
        }
    }

    public Tab getTab(){
        return tab;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}