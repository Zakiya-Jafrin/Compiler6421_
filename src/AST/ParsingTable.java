package AST;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class ParsingTable {
    static String predictSet[][] = {
            {"1", "prog", "class int float id program", "; funcBody program funcDefList classDeclList"},


            {"2","classDeclList","class", "classDeclList classDecl"},
            {"3","classDeclList","program int float id", "EPSILON"},

            {"4","funcDefList","int float id", "funcDefList funcDef"},
            {"5","funcDefList","program", "EPSILON"},

            {"6","funcBody","{","} optVarStat {"},

            {"7","optVarStat","int float","optVarStat optVar"},
            {"8","optVarStat","if for get put return","statementList optStat"},
            {"9","optVarStat","id","varStatLf id"},
            {"10","optVarStat","}","EPSILON"},

            {"11","optVar","int float","; arraySizeList id variableList"},
            {"12","variableList","int","int"},
            {"13","variableList","float","float"},

            {"14","optStat","if","; statBlock else statBlock then ) expr ( if"},
            {"15","optStat","for","; statBlock ) assignStat ; relExpr ; expr assignOp id type ( for"},
            {"16","optStat","get", "; ) variable ( get"},
            {"17","optStat","put", "; ) expr ( put"},
            {"18","optStat","return", "; ) expr ( return"},

            {"19","varStatLf","id","optVarStat ; arraySizeList id"},
            {"20","varStatLf","[ ( = .","statementList ; expr assignOp variableLf"},


            {"21","classDecl","class", "; } optVarFuncDecl { optclassDecl id class"},
            {"22","funcDef","int float id", "; funcBody funcHead"},

            {"23","statementList","if for get put return id","statementList statement"},
            {"24","statementList","}", "EPSILON"},

            {"25","optclassDecl",":","optclassDeclList id :"},
            {"26","optclassDecl","{", "EPSILON"},

            {"27","optVarFuncDecl","int float id", "optVarFuncDeclList id type"},
            {"28","optVarFuncDecl","}", "EPSILON"},
            {"29","optVarFuncDeclList","[ ;", "optVarFuncDecl ; arraySizeList"},
            {"30","optVarFuncDeclList","(", "funcDeclList ; ) fParams ("},


            {"31","funcDeclList","int float id", "funcDeclList funcDecl"},
            {"32","funcDeclList","}", "EPSILON"},

            {"33","funcHead","int float id", ") fParams ( funcHeadLf id type"},

            {"34","statement","id", "; assignStat"},
            {"35","statement","if", "; statBlock else statBlock then ) expr ( if"},
            {"36","statement","for", "; statBlock ) assignStat ; relExpr ; expr assignOp id type ( for"},
            {"37","statement","get", "; ) variable ( get"},
            {"38","statement","put", "; ) expr ( put"},
            {"39","statement","return", "; ) expr ( return"},

            {"40","optclassDeclList",",", "optclassDeclList id ,"},
            {"41","optclassDeclList","{", "EPSILON"},

            {"42","funcDecl","int float id", "; ) fParams ( id type"},
            {"43","type","int","int"},
            {"44","type","float", "float"},
            {"45","type","id","id"},

            {"46","funcHeadLf","sr","id sr"},
            {"47","funcHeadLf","(", "EPSILON"},

            {"48","fParams","int float id","fParamsTailList arraySizeList id type"},
            {"49","fParams",")","EPSILON"},

            {"50","arraySizeList","[","arraySizeList arraySize"},
            {"51","arraySizeList",", ; )", "EPSILON"},

            {"52","assignStat","id", "expr assignOp variable"},

            {"53","expr","id intNum floatNum ( not + -", "exprLf arithExpr"},
            {"54","exprLf",", ) ;","EPSILON"},
            {"55","exprLf","eq neq lt gt leq geq", "arithExpr relOp"},

            {"56","statBlock","{","} statementList {"},
            {"57","statBlock","if for get put return id","statement"},
            {"58","statBlock","else ;","EPSILON"},

            {"59","assignOp","=","="},
            {"60","relExpr","intNum floatNum ( not id + -","arithExpr relOp arithExpr"},

            {"61","variable","id","variableLf id"},
            {"62","variableLf","[ .", "optVariable indiceList"},
            {"63","variableLf","(","variable . ) aParams ("},


            {"64","optVariable",".","variable ."},
            {"65","optVariable","= )","EPSILON"},

            {"66","fParamsTailList",",","fParamsTailList fParamsTail"},
            {"67","fParamsTailList",")", "EPSILON"},

            {"68","arraySize ","[","] intNum ["},

            {"69","arithExpr","id intNum floatNum ( not + -","arithExprLf term"},
            {"70","arithExprLf","+ - or", "arithExprLf term addOp"},
            {"71","arithExprLf","] ) eq neq lt gt leq geq ; ,","EPSILON"},

            {"72","aParams","id intNum floatNum ( + - not", "aParamsTailList expr"},
            {"73","aParams",")","EPSILON"},
            {"74","aParamsTailList",",","aParamsTailList aParamsTail"},
            {"75","aParamsTailList",")","EPSILON"},
            {"76","fParamsTail",",","arraySizeList id type ,"},
            {"77","aParamsTail",",","expr ,"},

            {"78","relOp","eq","eq"},
            {"79","relOp","neq","neq"},
            {"80","relOp","lt","lt"},
            {"81","relOp","gt","gt"},
            {"82","relOp","leq","leq"},
            {"83","relOp","geq","geq"},

            {"84","addOp","+","+"},
            {"85","addOp","-","-"},
            {"86","addOp","or","or"},
            {"87","multOp","*","*"},
            {"88","multOp","/","/"},
            {"89","multOp","and","and"},

            {"90","indiceList","[","indiceList indice"},
            {"91","indiceList",". = ) * / and + - or ] eq neq lt gt leq geq , ;", "EPSILON"},

            {"92","term","id intNum floatNum ( not + -","termLf factor"},
            {"93","termLf","* / and","termLf factor multOp"},
            {"94","termLf","+ - or ] ) eq neq lt gt leq geq , ;", "EPSILON"},

            {"95","indice","[", "] arithExpr ["},

            {"96","sign","+", "+"},
            {"97","sign","-","-"},

            {"98","factor","id","factorLf"},
            {"99","factor","intNum", "intNum"},
            {"100","factorList",".", "factorLf ."},
            {"101","factor","floatNum","floatNum"},
            {"102","factor","(",") arithExpr ("},
            {"103","factor","not", "factor not"},
            {"104","factor","+ -", "factor sign"},
            {"105","factorLf","id","factorList factorLff id"},
            {"105","factorLf","id","factorList factorLff id"},
            {"106","factorLff","[","indiceList"},
            {"107","factorLff","(",") aParams ("},
            {"108","factorList","* / and + - or ] ) eq neq lt gt leq geq , ;", "EPSILON"},

//          for Skip Errors
            {"109","varStatLf","}","EPSILON"},
            {"110","optVarFuncDeclList","}", "EPSILON"},
            {"111","variableLf","= )","EPSILON"},
            {"112", "prog", "$", "EPSILON"},
            {"113","factorLff",". * / and + - or ] ) eq neq lt gt leq geq , ;", "EPSILON"},

    };

    static String nonterminalSymbols [] = {"0","prog",
            "classDeclList", "classDeclList",
            "funcDefList","funcDefList",
            "funcBody",
            "optVarStat", "optVarStat", "optVarStat", "optVarStat", "optVar","variableList","variableList","optStat","optStat",
            "optStat","optStat","optStat","varStatLf","varStatLf","classDecl", "funcDef","statementList","statementList",
            "optclassDecl", "optclassDecl","optVarFuncDecl","optVarFuncDecl","optVarFuncDeclList","optVarFuncDeclList",
            "funcDeclList", "funcDeclList","funcHead","statement","statement","statement","statement","statement","statement",
            "optclassDeclList", "optclassDeclList","funcDecl","type","type","type",
            "funcHeadLf","funcHeadLf","fParams","fParams","arraySizeList",
            "arraySizeList", "assignStat",
            "expr","exprLf","exprLf", "statBlock","statBlock","statBlock","assignOp","relExpr","variable","variableLf","variableLf",
            "optVariable","optVariable","fParamsTailList","fParamsTailList","arraySize", "arithExpr","arithExprLf","arithExprLf",
            "aParams","aParams",
            "aParamsTailList","aParamsTailList","fParamsTail","aParamsTail",
            "relOp","relOp","relOp","relOp","relOp","relOp","addOp","addOp","addOp","multOp","multOp","multOp",
            "indiceList","indiceList","term","termLf","termLf",
            "indice","sign","sign", "factor","factor","factorList","factor","factor","factor","factor","factorLf","factorLff",
            "factorLff","factorList","varStatLf", "optVarFuncDeclList","variableLf","prog", "factorLff"};


    static Hashtable<String, String> getnonTerminal = new Hashtable<String, String>(120);
    static Hashtable<String, String> getpredict = new Hashtable<String, String>(120);
    static Hashtable<String, String> getReverseExpression = new Hashtable<String, String>(120);

    public static int init() {
        for (int i = 0; i < predictSet.length; i++) {
            for (int j = 0; j < 4; j++) {
                getnonTerminal.put(predictSet[i][0], predictSet[i][1]);
                getpredict.put(predictSet[i][0], predictSet[i][2]);
                getReverseExpression.put(predictSet[i][0], predictSet[i][3]);
            }
        }
        return 0;
    }

    static ArrayList<String> indexes  = new ArrayList<String>();
    public static String[] getNonterminalSymbols(String symbol){
        indexes =new ArrayList<String>();
        for(int i=0; i<nonterminalSymbols.length; i++){
            if(nonterminalSymbols[i].equals(symbol))
            {
                String id = Integer.toString(i);
                indexes.add(id);
            }
        }
        String [] arr = indexes.toArray(new String[indexes.size()]);
        return arr;
    }



    public static String getPredictSet(String symbol, String tk){
//    public static String getPredictSet(String symbol, TokenType tk){
        String matchType = null;
        String ruletoApply = null;
        String rule = null;
        String [] ruleNum = getNonterminalSymbols(symbol);
        for(int i=0 ; i<= ruleNum.length-1; i++){
            rule = ruleNum[i];
            Set<String> allkey = getpredict.keySet();
            String [] setToarray = allkey.toArray(new String[allkey.size()]);
            for (int j=0 ; j<= allkey.size()-1; j++){
                String nonterm = setToarray[j];
                if(rule.equals(nonterm)){
                    String rulesMatch = getpredict.get(rule);
                    String[] tokecnMatch = rulesMatch.split(" ");
                    for(int k =0; k<= tokecnMatch.length-1; k++){
                        String tokecnMatched = tokecnMatch[k];
                        if(tokecnMatched.equals(tk)|| tokecnMatched.equals(matchType)){
                            tokecnMatched= rule;
                            ruletoApply = tokecnMatched;
//                            System.out.println(ruletoApply);
//                            grammerToApply(ruletoApply);
                        }
                    }
                }
            }
        }
        return ruletoApply;
    }

    public static String[] grammerToApply(String rule){
        String grammer = getReverseExpression.get(rule);
        String[] splitGrammmer = grammer.split(" ");
        return splitGrammmer;
    }
}
