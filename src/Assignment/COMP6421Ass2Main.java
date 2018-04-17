package Assignment;

import java.io.IOException;

import AST.Parser;
import LexicalAnalyzer.LexicalAnalyzer;


public class COMP6421Ass2Main {

	public static void main(String args[]) throws IOException {

		LexicalAnalyzer lex = new LexicalAnalyzer();
		lex.run("example-valid-program.txt");
//		lex.run("simple.txt");
		Parser parse= new Parser();
		parse.run("example-valid-program.txt");
//		parse.run("simple.txt");
		parse.parse();

	}
}

/*
 * COMP6421 (Compiler Design) Project
 *
 * This file is created by Sumit Sarkar (hitman3r44@gmail.com)
 *
 * $Author$ = Sumit Sarkar
 * $Email$	= hitman3r44@gmail.com
 * $ID$ = 27728573
 *
 */
//package Assignment;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//
//import LexicalAnalyzer.InputLoader;
//import LexicalAnalyzer.LexicalAnalyzer;
//import SemanticActions.SymbolTable;
//import SyntacticAnalyzer.SyntacticAnalyzer;
//import utils.SysLogger;
//
//
//public class COMP6421Ass2Main {
//
//	public static void main(String[] args) throws IOException{
//		// create and initialize the logger
//		SysLogger.init();
//
//		LexicalAnalyzer lex = new LexicalAnalyzer();
//		lex.run("simple.txt");
//
//		// show greetings
//		System.out.println("Wellcome to COMP 6421 Project.");
//		System.out.println("Developed by Sumit Sarkar.\n");
//		System.out.println("Please read $ThisProgram\\readme.pdf first.");
//		System.out.println(
//				"Please put all the test files under the root directoy of $ThisProgram\\input\\, which already has some sample files.");
//		System.out.println(
//				"The result of the progrom will be stored accordingly in the files under $ThisProgram\\output\\ \n");
//		System.out.println("Press any key to begin...\n");
//
//
//		try {
//			System.in.read();
//		} catch (IOException e) {
//
//		}
//
//		// load all test input files
//		InputLoader testFilesLoader = new InputLoader();
//		String path = System.getProperty("user.dir");
//
//		if (testFilesLoader.loadTextFiles(path) != 0) {
//			return;
//		}
//
//
//		// begin to lexical analyze for each file
//		for (int i = 0; i < testFilesLoader.lstFiles.size(); i++) {
//			SymbolTable st = null;
//			for (int j = 0; j < 2; j++) {
//				if (j == 0) {
//					SysLogger.enableLog(false);
//				} else {
//					SysLogger.enableLog(true);
//					//create output file first
//					SysLogger.setOutputFilenames(testFilesLoader.lstResultFiles.get(i),
//							testFilesLoader.lstErrFiles.get(i));
//					SysLogger.setASMFilenames(testFilesLoader.lstASMFiles.get(i));
//
//					// write time stamp to output files
//					SimpleDateFormat tmpDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					String datetimeNow = tmpDate.format(new java.util.Date());
//					//SysLogger.info(datetimeNow + "\nThe following is the result:");
//					String tabNote = "Note: If the width of TAB of your editor is not 4, please accordingly modify LexicalAnalyzer.java at line 26 and run again.\n";
//					//SysLogger.info(tabNote);
//					SysLogger.err(tabNote);
//					SysLogger.log("--------------------------------------------------");
//					SysLogger.log("Start to analyze: " + testFilesLoader.lstFiles.get(i));
//					SysLogger.log("--------------------------------------------------");
//				}
//
//
//				// create a syntax analyzer
//				SyntacticAnalyzer parser = new SyntacticAnalyzer();
//
//				if (parser.init(lex) != 0) {
//					continue;
//				}
//
//				if (parser.parseEx(st)) {
//					if(st != null){
//
//						st = (SymbolTable) parser.smActions.stHead.clone();
//
//						//scanner.getAllTokens();
//						parser.smActions.printAll();
//
//					}
//
//
//				} else {
//					continue;
//				}
//			}
//		}
//
//		InputLoader.deleteFilesWithExt(path);
//
//		System.out.println("\nThe program ends successfully!");
//		System.out.println("The result of the progrom has been stored in the files under $ThisProgram\\output\\ \n");
//
//	}
//}
