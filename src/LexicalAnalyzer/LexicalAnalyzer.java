package LexicalAnalyzer;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.InputMismatchException;

public class LexicalAnalyzer {

	public enum TokenType {
		ID, INT_NUM, FLOAT_NUM,EQUAL, NOT_EQUAL,LESS_THAN, GREATER_THAN, LESS_THAN_OR_EQUAL, GREATER_THAN_OR_EQUAL,
		SEMICOLON, COMMA, DOT, COLON, COLON_COLON,
		PLUS, MINUS, TIMES, DIVIDE, ASSIGN, AND, NOT, OR,
		LPAREN, RPAREN, LBRACE, RBRACE, LSQBRACKET, RSQBRACKET,
		IF, THEN, ELSE, FOR, CLASS, INT,FLOAT, GET, PUT, PROGRAM, RETURN,
		FILE_END, UNKNOWN, COMMENT, COMMENT_NOT_END,
		KEYWORD, PUNCTUATION, OPAREATORS;

		public String lexeme;
		public int lineNumber;

		public String getLexeme() {
			return this.lexeme;
		}

		public int getLineNumber() {
			return lineNumber;
		}
	}

	public static LineNumberReader fileReader;

	private String tokenStream = "";
	private String errorStream = "";
	private int numOfTokens = 0;
	private int numOfErrors = 0;
	public TokenType tokens = null;



	/**
	 * running the le
	 * ]
	 * \xical analyzer
	 * @param fileName name of the source code file
	 * @throws IOException
	 */
	public void run(String fileName) throws IOException {
		this.tokenStream = "";
		this.errorStream = "";
		this.numOfErrors = 0;
		this.numOfTokens = 0;

		TokenType token ;

		String inputFileName = "Input/"+ fileName;
		String ouputFileName = "Output/"
				+ fileName.substring(0, fileName.lastIndexOf('.'))
				+ "_Lexical_Output.txt";

		String errorFileName = "Output/"
				+ fileName.substring(0, fileName.lastIndexOf('.'))
				+ "_Lexical_Error.txt";


		fileReader = new LineNumberReader(new FileReader(
				inputFileName));
//		token = tokenScan(fileReader);


		// create output files
		FileWriter errorFile = new FileWriter(errorFileName);
		BufferedWriter errorOutput = new BufferedWriter(errorFile);
		errorOutput.write("");
		errorOutput.close();


		FileWriter resultFile = new FileWriter(ouputFileName);
		BufferedWriter resultOutput = new BufferedWriter(resultFile);
		resultOutput.write("");
		resultOutput.close();

		System.out.println("Lexical analysis of file " + fileName + " is started!");


		while ((token = tokenScan(fileReader)) != TokenType.FILE_END) {

			if (token != TokenType.COMMENT) // skip the comment
				if (token == TokenType.COMMENT_NOT_END)// unclosed comment error
				{
					writetoFile(errorFileName,
							"Lexical Error: Unclosed Comment at Line "
									+ token.lineNumber + "\r\n");
					System.out
							.println("Lexical Error: Unclosed Comment at Line "
									+ token.lineNumber + "");
					this.errorStream += ("[COMMENT_UNCLOSED at Line "
							+ token.lineNumber + "]");
					this.numOfErrors++;

					System.out.println("Lexical Error: Unclosed Comment at Line "+ token.lineNumber + "\r\n");
				} else if (token == TokenType.UNKNOWN) // unknown token error
				{
					writetoFile(errorFileName, "Lexical Error: Unknown token "
							+ token.lexeme + " at Line " + token.lineNumber
							+ "\r\n");
					System.out.println("Lexical Error: Unrecognized Token "
							+ token.lexeme + " at Line " + token.lineNumber
							+ "");
					this.errorStream += ("[UNKNOWN_TOKEN " + token.lexeme
							+ " at Line " + token.lineNumber + "]");
					this.numOfErrors++;
				}
				else{
					writetoFile(ouputFileName, "Token recognized as '" + token.name() + "' : '" + token.lexeme
							+ "' at Line number :" + token.lineNumber+"\r\n");
					tokenStream += ("["+token.name() + "]");
					tokens = token;
					this.numOfTokens++;
				}

			fileReader.reset(); // reset the reading position to the marked position for backtracking
		}
		fileReader.close();


		fileReader = new LineNumberReader(new FileReader(
				inputFileName));
//
//		nextToken();


//		while (tk != TokenType.FILE_END) // if the parsing has completed
//		// and still some content other
//		// than comment is left
//		{
//			writetoFile(errorFileName, "Error : " + tk.lineNumber);
//			nextToken();
//		}



		System.out
				.println("Lexical analysis is done! Please check the output file!");
		System.out.println(this.numOfTokens + " tokens recognized, "
				+ this.numOfErrors + " errors encoutered.\n");

	}

	private static void writetoFile(String fileName, String str) throws IOException {
		FileWriter fstream = new FileWriter(fileName, true);
		BufferedWriter out = new BufferedWriter(fstream);

		out.write(str);
		out.close();
	}

	//Extract the next token in the program (called by syntactic analyzer)
//	public static TokenType nextToken(){ return tk
	public static String nextToken(){
		TokenType tk;
		String lexeme = "$";
		while ((tk = tokenScan(fileReader)) != TokenType.FILE_END) {
			if(tk == TokenType.INT_NUM){
				lexeme="intNum";
				return lexeme;
			}else if(tk == TokenType.FLOAT_NUM){
				lexeme = "floatNum";
				return lexeme;
			}else if(tk == TokenType.ID){
				lexeme = "id";
				return lexeme;
			}else if(tk == TokenType.CLASS){
				lexeme ="class";
				return lexeme;
			}else if(tk == TokenType.PROGRAM){
				lexeme ="program";
				return lexeme;
			}else if(tk == TokenType.GET){
				lexeme ="get";
				return lexeme;
			}else if(tk == TokenType.PUT){
				lexeme ="put";
				return lexeme;
			}else if(tk == TokenType.INT){
				lexeme ="int";
				return lexeme;
			}else if(tk == TokenType.FLOAT){
				lexeme ="float";
				return lexeme;
			}else if(tk == TokenType.FOR){
				lexeme ="for";
				return lexeme;
			}else if(tk == TokenType.IF){
				lexeme ="if";
				return lexeme;
			}else if(tk == TokenType.ELSE){
				lexeme ="else";
				return lexeme;
			}else if(tk == TokenType.THEN){
				lexeme ="then";
				return lexeme;
			}else if(tk == TokenType.RETURN){
				lexeme ="return";
				return lexeme;
			}else if(tk == TokenType.EQUAL){
				lexeme ="eq";
				return lexeme;
			}else if(tk == TokenType.NOT_EQUAL){
				lexeme ="neq";
				return lexeme;
			}else if(tk == TokenType.GREATER_THAN_OR_EQUAL){
				lexeme ="geq";
				return lexeme;
			}else if(tk == TokenType.GREATER_THAN){
				lexeme ="gt";
				return lexeme;
			}else if(tk == TokenType.LESS_THAN_OR_EQUAL){
				lexeme ="leq";
				return lexeme;
			}else if(tk == TokenType.PLUS){
				lexeme ="+";
				return lexeme;
			}else if(tk == TokenType.MINUS){
				lexeme ="-";
				return lexeme;
			}else if(tk == TokenType.TIMES){
				lexeme ="*";
				return lexeme;
			}else if(tk == TokenType.DIVIDE){
				lexeme ="/";
				return lexeme;
			}else if(tk == TokenType.NOT){
				lexeme ="not";
				return lexeme;
			}else if(tk == TokenType.AND){
				lexeme ="and";
				return lexeme;
			}else if(tk == TokenType.OR){
				lexeme ="or";
				return lexeme;
			}else if(tk == TokenType.ASSIGN){
				lexeme ="=";
				return lexeme;
			}else if(tk == TokenType.COLON_COLON){
				lexeme ="sr";
				return lexeme;
			}else if(tk == TokenType.COLON){
				lexeme =":";
				return lexeme;
			}else if(tk == TokenType.COMMA){
				lexeme =",";
				return lexeme;
			}else if(tk == TokenType.DOT){
				lexeme =".";
				return lexeme;
			}else if(tk == TokenType.SEMICOLON){
				lexeme =";";
				return lexeme;
			}else if(tk == TokenType.LSQBRACKET){
				lexeme ="[";
				return lexeme;
			}else if(tk == TokenType.RSQBRACKET){
				lexeme ="]";
				return lexeme;
			}else if(tk == TokenType.LBRACE){
				lexeme ="{";
				return lexeme;
			}else if(tk == TokenType.RBRACE){
				lexeme ="}";
				return lexeme;
			}else if(tk == TokenType.LPAREN){
				lexeme ="(";
				return lexeme;
			}else if(tk == TokenType.RPAREN){
				lexeme =")";
				return lexeme;
			}
			if (tk != TokenType.COMMENT) // skip the comment
				if (tk == TokenType.COMMENT_NOT_END){// unclosed comment error
					System.out.println("Token Error: unclosed comment");
				} else if (tk == TokenType.UNKNOWN){ // unknown token error
					System.out.println("Token Unknown");
				} else{
					System.out.println("Error");
				}

			try {
				fileReader.reset();
			} catch (IOException e) {
				e.printStackTrace();
			}// reset the reading position to the marked position for backtracking
		}
		return lexeme;
	}



	public static TokenType tokenScan(LineNumberReader fileReader) {

		TokenType tokenType = TokenType.COMMENT;

		String tokenVal = "";
		int lineNumber = 0;

		char curChar, nextChar;

		try {
			curChar = (char) fileReader.read();
			lineNumber = fileReader.getLineNumber();

			// skip whitespace
			while (Character.isWhitespace(curChar)) {
				curChar = (char) fileReader.read();
				lineNumber = fileReader.getLineNumber();
			}

			// mark the position for backtracking
			fileReader.mark(1024);

			switch (curChar) {
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':

					tokenVal = tokenVal + curChar;
					nextChar = (char) fileReader.read();

					while (nextChar == '.' || Character.isDigit(nextChar)) {
						if (nextChar == '.') {
							nextChar = (char) fileReader.read();
							if (nextChar == '0') {
								tokenVal = tokenVal +'.' + nextChar;
								fileReader.mark(1024);
								nextChar = (char) fileReader.read();
								if (!Character.isDigit(nextChar)) {
									fileReader.reset();
									break;
								}
							} else if (Character.isDigit(nextChar)) {
								tokenVal = tokenVal + '.' + nextChar;
								fileReader.mark(1024);
								nextChar = (char) fileReader.read();

								while (Character.isDigit(nextChar)) {
									if (nextChar != '0') {
										tokenVal = tokenVal + nextChar;
										fileReader.mark(1024);
										nextChar = (char) fileReader.read();
										continue;
									} else {
										String temp = tokenVal;
										while (nextChar == '0') {
											temp = temp + nextChar;
											nextChar = (char) fileReader.read();
										}
										if (!Character.isDigit(nextChar)) {
											fileReader.reset();
											break;
										} else {
											tokenVal = temp+nextChar;
											fileReader.mark(1024);
											nextChar = (char) fileReader.read();
											continue;
										}
									}
								}
							}
							break;
						}
						tokenVal = tokenVal + nextChar;
						fileReader.mark(1024);
						nextChar = (char) fileReader.read();
					}

					if (tokenVal.matches("[0-9]+")) {
						tokenType = TokenType.INT_NUM;
					} else {
						tokenType = TokenType.FLOAT_NUM;
						if(tokenType == TokenType.FLOAT_NUM && (nextChar =='e')){
							tokenType = TokenType.FLOAT_NUM;
							tokenVal = tokenVal + nextChar;
							fileReader.mark(1024);
							nextChar = (char) fileReader.read();
							if(nextChar == '0' || nextChar == '-'){
								tokenType = TokenType.FLOAT_NUM;
								tokenVal = tokenVal + nextChar;
								fileReader.mark(1024);
								if(nextChar == '-') {
									nextChar = (char) fileReader.read();
									while(Character.isDigit(nextChar)){
										tokenType = TokenType.FLOAT_NUM;
										tokenVal = tokenVal + nextChar;
										fileReader.mark(1024);
										nextChar = (char) fileReader.read();
									}
								}
								nextChar = (char) fileReader.read();
							}
//                        if(tokenVal.matches("[1-9][0-9]*\\.?[1-9]*(exp(\\+|-)?([1-9]([0-9])*|0))?")){
						}
						tokenType = TokenType.FLOAT_NUM;
					}
					break;

				case '/':
					nextChar = (char) fileReader.read();
					if (nextChar == '/') {
						fileReader.readLine(); // end-of-line comment, skip the rest of current line
						fileReader.mark(1024);
					} else if (nextChar == '*') {
						do {
							curChar = (char) fileReader.read();
							if (curChar == '*') {
								do {
									nextChar = (char) fileReader.read();
								} while (nextChar == '*');
							}
							if ((int) curChar == 65535) // comment not closed even the file ends
							{
								tokenType = TokenType.COMMENT_NOT_END;
								break;
							}
						} while (curChar != '*' || nextChar != '/'); // multi-line comment
						fileReader.mark(1024);
					} else {
						tokenVal += curChar;
						tokenType = TokenType.DIVIDE; // divide operator
					}
					break;
				case ';':
					tokenType = TokenType.SEMICOLON;
					tokenVal += curChar;
					break;
				case ',':
					tokenType = TokenType.COMMA;
					tokenVal += curChar;
					break;
				case '.':
					tokenType = TokenType.DOT;
					tokenVal += curChar;
					break;
				case ':':
					tokenVal += curChar;
					nextChar = (char) fileReader.read();
					if (nextChar == ':') {
						tokenVal += nextChar;
						tokenType = TokenType.COLON_COLON;
						fileReader.mark(1024);
					} else {
						tokenType = TokenType.COLON;
					}
					break;
				case '(':
					tokenType = TokenType.LPAREN;
					tokenVal += curChar;
					break;
				case ')':
					tokenType = TokenType.RPAREN;
					tokenVal += curChar;
					break;
				case '{':
					tokenType = TokenType.LBRACE;
					tokenVal += curChar;
					break;
				case '}':
					tokenType = TokenType.RBRACE;
					tokenVal += curChar;
					break;
				case '[':
					tokenType = TokenType.LSQBRACKET;
					tokenVal += curChar;
					break;
				case ']':
					tokenType = TokenType.RSQBRACKET;
					tokenVal += curChar;
					break;
				case '+':
					tokenType = TokenType.PLUS;
					tokenVal += curChar;
					break;
				case '-':
					tokenType = TokenType.MINUS;
					tokenVal += curChar;
					break;
				case '*':
					tokenType = TokenType.TIMES;
					tokenVal += curChar;
					break;
				case '=':
					tokenVal += curChar;
					nextChar = (char) fileReader.read();
					if (nextChar == '=') {
						tokenVal += nextChar;
						tokenType = TokenType.EQUAL;
						fileReader.mark(1024);
					} else {
						tokenType = TokenType.ASSIGN;
					}
					break;
				case '<':
					tokenVal += curChar;
					nextChar = (char) fileReader.read();
					if (nextChar == '=') {
						tokenVal += nextChar;
						tokenType = TokenType.LESS_THAN_OR_EQUAL;
						fileReader.mark(1024);
					} else if (nextChar == '>') {
						tokenVal += nextChar;
						tokenType = TokenType.NOT_EQUAL;
						fileReader.mark(1024);
					} else {
						tokenType = TokenType.LESS_THAN;
					}
					break;
				case '>':
					tokenVal += curChar;
					nextChar = (char) fileReader.read();
					if (nextChar == '=') {
						tokenVal += nextChar;
						tokenType = TokenType.GREATER_THAN_OR_EQUAL;
						fileReader.mark(1024);
					} else {
						tokenType = TokenType.GREATER_THAN;
					}
					break;

				case 'A':
				case 'B':
				case 'C':
				case 'D':
				case 'E':
				case 'F':
				case 'G':
				case 'H':
				case 'I':
				case 'J':
				case 'K':
				case 'L':
				case 'M':
				case 'N':
				case 'O':
				case 'P':
				case 'Q':
				case 'R':
				case 'S':
				case 'T':
				case 'U':
				case 'V':
				case 'W':
				case 'X':
				case 'Y':
				case 'Z':
				case 'a':
				case 'b':
				case 'c':
				case 'd':
				case 'e':
				case 'f':
				case 'g':
				case 'h':
				case 'i':
				case 'j':
				case 'k':
				case 'l':
				case 'm':
				case 'n':
				case 'o':
				case 'p':
				case 'q':
				case 'r':
				case 's':
				case 't':
				case 'u':
				case 'v':
				case 'w':
				case 'x':
				case 'y':
				case 'z':
					tokenVal = tokenVal + curChar;
					nextChar = (char) fileReader.read();
					while (nextChar == '_' || Character.isLetterOrDigit(nextChar)) {
						tokenVal = tokenVal + nextChar;
						fileReader.mark(1024);
						nextChar = (char) fileReader.read();
					}
					TokenType reservedWord = checkReservedWords(tokenVal);
					if (reservedWord != null) {
						tokenType = reservedWord;
						break;
					} else
						tokenType = TokenType.ID;
					break;


				case '0':
					tokenVal = tokenVal + curChar;
					fileReader.mark(1024);
					nextChar = (char) fileReader.read();
					if (nextChar == '.') {
						nextChar = (char) fileReader.read();
						if(nextChar =='0'){
							fileReader.reset();
						}else if (Character.isDigit(nextChar)) {
							tokenVal = tokenVal + '.' + nextChar;
							fileReader.mark(1024);
							nextChar = (char) fileReader.read();

							while (Character.isDigit(nextChar)) {
								if (nextChar != '0') {
									tokenVal = tokenVal + nextChar;
									fileReader.mark(1024);
									nextChar = (char) fileReader.read();
									continue;
								} else {
									String temp = tokenVal;
									while (nextChar == '0') {
										temp = temp + nextChar;
										nextChar = (char) fileReader.read();
									}
									if (!Character.isDigit(nextChar)) {
										fileReader.reset();
										break;
									} else {
										tokenVal = temp+nextChar;
										fileReader.mark(1024);
										nextChar = (char) fileReader.read();
										continue;
									}
								}
							}
						}
					}
					tokenType = TokenType.INT_NUM;
					break;

				default:
					if ((int) curChar == 65535)
						tokenType = TokenType.FILE_END;
					else {
						tokenType = TokenType.UNKNOWN;
						tokenVal = tokenVal + curChar;
						nextChar = (char) fileReader.read();
						while (Character.isLetter(nextChar)) {
							tokenVal = tokenVal + nextChar;
							fileReader.mark(1024);
							nextChar = (char) fileReader.read();
							while(Character.isDigit(nextChar)){
								tokenVal = tokenVal + nextChar;
								fileReader.mark(1024);
								nextChar = (char) fileReader.read();
							}
						}
					}
			}

		}

		catch (StringIndexOutOfBoundsException e) {
			System.out.println("Error: " + e.getMessage());

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());

		} catch (InputMismatchException e) {
			System.out.println("Error: " + e.getMessage());

		} finally {

		}
		tokenType.lexeme = tokenVal;
		tokenType.lineNumber = lineNumber + 1;
		return tokenType;

	}

	public static TokenType checkReservedWords(String tokenValue) {
		if (tokenValue.equals("if")) {
			return TokenType.IF;
		} else if (tokenValue.equals("then")) {
			return TokenType.THEN;
		} else if (tokenValue.equals("else")) {
			return TokenType.ELSE;
		} else if (tokenValue.equals("for")) {
			return TokenType.FOR;
		} else if (tokenValue.equals("class")) {
			return TokenType.CLASS;
		} else if (tokenValue.equals("int")) {
			return TokenType.INT;
		} else if (tokenValue.equals("float")) {
			return TokenType.FLOAT;
		} else if (tokenValue.equals("get")) {
			return TokenType.GET;
		} else if (tokenValue.equals("put")) {
			return TokenType.PUT;
		}else if (tokenValue.equals("return")) {
			return TokenType.RETURN;
		}else if (tokenValue.equals("program")) {
			return TokenType.PROGRAM;
		} else if (tokenValue.equals("and")) {
			return TokenType.AND;
		} else if (tokenValue.equals("or")) {
			return TokenType.OR;
		} else if (tokenValue.equals("not")) {
			return TokenType.NOT;
		} else
			return null;
	}

}
