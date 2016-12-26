package myCompiler.controller;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import myCompiler.model.SyntaxAnalyzer;

public class CodeTab extends AnchorPane implements Initializable{
    private String filePath;

    @FXML
    private TextArea lineNumberArea;

    @FXML
    private TextArea contentArea;

    public CodeTab(String filePath) {
        super();
        this.filePath = filePath;
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

            // todo replac ewith hit library
            SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer(SyntaxAnalyzer.Extention.JAVA, br);
            Stack bracketErrors = syntaxAnalyzer.analyzeBrackets();

            lineNumberArea.appendText(syntaxAnalyzer.getLineNumbers());
            contentArea.appendText(syntaxAnalyzer.getWholeTextDocument());

        } catch (FileNotFoundException e) {
            contentArea.appendText("Error while opening: file not found");
        } catch (IOException e) {
            contentArea.appendText("Error while opening: IOException");
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void saveFileChanges(){
        String text = contentArea.getText();
        PrintWriter out = null;
        try {
            out = new PrintWriter(filePath);
            out.println(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (out!=null)
                    out.close();
        }

    }

}