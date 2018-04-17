package AST;

import LexicalAnalyzer.LexicalAnalyzer;
import LexicalAnalyzer.LexicalAnalyzer.TokenType;
import utils.SysLogger;

import java.io.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;

public class Parser {
    private LexicalAnalyzer.TokenType tokenType;

    Stack stack = new Stack();
    private boolean error;
    private String curToken= null;

    String ouputFileName;
    String errorFileName;

    public static LineNumberReader fileReader;

    private String tokenStream = "";
    private String errorStream = "";
    private int numOfTokens = 0;
    private int numOfErrors = 0;


    private static String terminalSymbols [] ={"program",";","class","id","{","}",":","(",")","sr"
            ,"if","then","else","for","=","get","put","return","+","-",
            "intNum","floatNum","not",".","[","]","int","float",",","eq","neq","lt","gt","leq",
            "geq","or","*","/","and","$"};



    private List belongsto = Arrays.asList(terminalSymbols);
    private String grammer = "check";
    String lastnontermPop= null;



    public boolean parse(){
        ParsingTable.init();
        stack.push("$");
        stack.push("prog");
        curToken = LexicalAnalyzer.nextToken();
        String x ;
        while(!(stack.peek().toString().equals("$"))) {
            x = stack.peek().toString();
            if (belongsto.contains(x)) {
                if (x.equals(curToken)) {
                    stack.pop();
                    try {
                        System.out.println("Successfully parsed the the Token :'" + curToken+" '");
                        writetoFile(ouputFileName, "Successfully parsed the the Token : '" + curToken + "' "+ "\r\n" );
                    }catch (Exception e){
                        System.out.println(e);
                    }
                    curToken = LexicalAnalyzer.nextToken();
                } else {
                    skipError();
                    error = true;
                }
            } else {
                if (!grammer.equals("EPSILON")) {
                    String theRule = ParsingTable.getPredictSet(x, curToken);
//                    if (theRule != null && !theRule.equals("109")&& !theRule.equals("110")&&
//                            !theRule.equals("111")&& !theRule.equals("112")&& !theRule.equals("113")) {
                    if (theRule != null){
                        lastnontermPop = stack.pop().toString();
//                        System.out.println("Successfully popped :'" + lastnontermPop);
                        String[] pushReverse = ParsingTable.grammerToApply(theRule);
                        for (int i = 0; i <= pushReverse.length - 1; i++) {
                            if (pushReverse[i].equals("EPSILON")) {
                                grammer = "EPSILON";
//                                System.out.println("EPSILON");
                            } else {
                                stack.push(pushReverse[i]);
//                                System.out.println("Successfully pushed :'" + pushReverse[i]);
                            }
                        }
                    } else {
                        skipError();
                        error = true;
                    }
                } else {
                    skip();
                    error = true;
                }
            }
        }
        if (error == true || curToken.equals("$")) {
            return false;
        } else {
            return true;
        }

    }

    public String skipError(){
        if(curToken.equals("$")){
            stack.pop();
        }else{
            System.out.println("Syntax error : " + curToken);
            try {
                writetoFile(ouputFileName, "Syntax Error : '" + curToken + "' "+ "\r\n" );
            }catch (Exception e){
                System.out.println(e);
            }

            curToken = LexicalAnalyzer.nextToken();
        }
        return curToken;
    }

    public String skip(){
        grammer = "check";
        return grammer;
    }

    /**
     * running the Parser
     */
    public void run(String fileName) throws IOException {
        this.tokenStream = "";
        this.errorStream = "";
        this.numOfErrors = 0;
        this.numOfTokens = 0;

        LexicalAnalyzer.TokenType token ;

        String inputFileName = "Input/"+ fileName;
        ouputFileName = "Output/"
                + fileName.substring(0, fileName.lastIndexOf('.'))
                + "_Parsing_Output.txt";

        errorFileName = "Output/"
                + fileName.substring(0, fileName.lastIndexOf('.'))
                + "_Parsing_Error.txt";


        fileReader = new LineNumberReader(new FileReader(
                inputFileName));

        // create output files
        FileWriter errorFile = new FileWriter(errorFileName);
        BufferedWriter errorOutput = new BufferedWriter(errorFile);
        errorOutput.write("");
        errorOutput.close();


        FileWriter resultFile = new FileWriter(ouputFileName);
        BufferedWriter resultOutput = new BufferedWriter(resultFile);
        resultOutput.write("");
        resultOutput.close();

    }

    private static void writetoFile(String fileName, String str) throws IOException {
        FileWriter fstream = new FileWriter(fileName, true);
        BufferedWriter out = new BufferedWriter(fstream);

        out.write(str);
        out.close();
    }
}