package myCompiler.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Stack;

/**
 * Created by Dovydas on 12/22/2016.
 */
public class SyntaxAnalyzer {
    BufferedReader code;
    private Stack stack;
    private  StringBuilder wholeText;
    private  StringBuilder lineNumbers;
    private final String[] javaReservedWords= { "abstract",	"continue",	"for","new", "switch",
            "assert","default",	"goto",	"package","synchronized",
    "boolean","do","if","private","this", "break","double","implements","protected","throw",
    "byte",	"else","import","public","throws", "case","enum","instanceof","return",	"transient", "catch",
            "extends","int","short","try", "char","final","interface","static",	"void",
    "class","finally","long","volatile", "const","float","native","super","while"};

    public enum Extention {
        JAVA, XML
    }

    public SyntaxAnalyzer(Extention extention, BufferedReader br){
        this.code = br;
        this.stack = new Stack();
    }

  public Stack analyzeBrackets(){
      String newLine = System.getProperty("line.separator");
      wholeText = new StringBuilder();
      lineNumbers = new StringBuilder();
      int lineCounter=0;
      String line;
      try {
          while ((line = code.readLine()) != null) {
              lineCounter++;
              wholeText.append(line + newLine);
              lineNumbers.append(lineCounter + newLine);
              int position = 0;
              for (char a : line.toCharArray()) {
                  position++;
                  switch (a) {
                      case '{':
                          stack.add(new SymbolOccurence(lineCounter, position));
                          break;
                      case '}':
                          stack.pop();
                          break;
                  }
              }
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
      for (Object s: stack){
          System.out.println("Bracket open at line: "+((SymbolOccurence)s).lineNumber+" position: "+((SymbolOccurence)s).position);
      }

      return stack;
  }


  // todo refactor
  public String getWholeTextDocument(){
      return wholeText.toString();
  }

  public String getLineNumbers(){
      return lineNumbers.toString();
  }


    private class SymbolOccurence{
        public int lineNumber;
        public int position;

        public SymbolOccurence(int lineNumber, int position){
            this.lineNumber = lineNumber;
            this.position = position;
        }

    }
}
