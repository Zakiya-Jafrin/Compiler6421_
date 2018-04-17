//package Assignment;
//
//public class in {
//
//    class InheritedUtility {
//        int member1 ;
//    } ;
//    class Utility : InheritedUtility {
//        int var1 [ 4 ] [ 5 ] [ 7 ] [ 8 ] [ 9 ] [ 1 ] [ 0 ] ;
//        float var2 ;
//        int findMax ( int array [ 100 ] ) ;
//        int findMin ( int array [ 100 ] ) ;
//    } ;
//    int Utility :: findMax ( int array [ 100 ] ) {
//        int maxValue 3;
//        int idx;
//        maxValue = array [ 100 ] ;
//        for ( int idx = 99 ; idx > 0 ; idx = idx - 1 ) {
//            if ( array [ idx ] > maxValue ) then {
//                maxValue = array [ idx ] ;
//            } else { } ;
//        } ;
//        return (maxValue);
//    };
//    int Utility :: findMin ( int array [ 100 ] ) {
//        int minValue ;
//        int idx ;
//        minValue = array [ 100 ] ;
//        for ( int idx = 1 ; idx <= 99 ; idx = ( idx ) + 1 ) {
//            if ( array [ idx ] < maxValue + 1 / 8 or idx ) then {
//                maxValue = array [ idx ] ;
//            } else { } ;
//        } ;
//        return ( minValue ) ;
//    };
//
//    float randomize ( ) {
//        float value ;
//        value = 100 * ( 2 + 3.0 / 7.0006 ) ;
//        value = 1.05 + ( ( 2.04 * 2.47 ) - 3.0 ) + 7.0006 > 1 and not - 1 ;
//        return ( value ) ;
//    } ;
//    program {
//        int sample [ 100 ] ;
//        int idx ;
//        int maxValue ;
//        int minValue ;
//        Utility utility ;
//        Utility arrayUtility [ 2 ] [ 3 ] [ 6 ] [ 7 ] ;
//        for ( int t = 0 ; t <= 100 ; t = t + 1) {
//            get ( sample [ t ] ) ;
//            sample [ t ] = ( sample [ t ] * randomize ( ) ) ;
//        } ;
//        maxValue = utility . findMax ( sample ) ;
//        minValue = utility . findMin ( sample ) ;
//        utility . var1 [ 4 ] [ 1 ] [ 0 ] [ 0 ] [ 0 ] [ 0 ] [ 0 ] = 10 ;
//        arrayUtility [ utility . var1 [ 1 ] [ 2 ] [ 3 ] [ 4 ] [ 5 ] [ 6 ] [ idx + maxValue ] ] [ 1 ] [ 1 ] [ 1 ] . var2 = 2.5 ;
//        put ( maxValue ) ;
//        put ( minValue ) ;
//    };
//}

//prog	'program', ε, 'class', 'int', 'float', 'id'
//        classDeclRep	ε, 'class'
//        funcDefRep	ε, 'int', 'float', 'id'
//        funcBody	'{'
//        varStatOpt	'id', ε, 'int', 'float', 'if', 'for', 'get', 'put', 'return'
//        varBuiltInType	'int', 'float'
//        statOpt	'if', 'for', 'get', 'put', 'return'
//        varStatChoice	'id', '(', ε, 'lsq', '.', '='
//        classDecl	'class'
//        statRep	ε, 'if', 'for', 'get', 'put', 'return', 'id'
//        baseClassOpt	':', ε
//
//
//        varFuncDeclOpt	ε, 'int', 'float', 'id'
//        varFuncDeclOptTail	';', '(', ε, 'lsq'
//        funcDeclRep	ε, 'int', 'float', 'id'
//        statement	'if', 'for', 'get', 'put', 'return', 'id'
//        baseClassRep	',', ε
//        type	'int', 'float', 'id'
//        ownerClassOpt	'sr', ε
//        fParams	ε, 'int', 'float', 'id'
//        arrSizeRep	ε, 'lsq'
//        exprTail	ε, 'eq', 'neq', 'lt', 'gt', 'leq', 'geq'
//        statBlock	'{', ε, 'if', 'for', 'get', 'put', 'return', 'id'
//        assignOp	'='
//        variable	'id'
//        variableTail	'(', ε, 'lsq', '.'
//        variableOpt	'.', ε
//        fParamsTailRep	ε, ','
//        arraySize	'lsq'
//        arithExprTail	ε, '+', '-', 'or'
//        relOp	'eq', 'neq', 'lt', 'gt', 'leq', 'geq'
//        indiceRep	ε, 'lsq'
//        fParamsTail	','
//        addOp	'+', '-', 'or'
//        termTail	ε, '*', '/', 'and'
//        indice	'lsq'
//        multOp	'*', '/', 'and'
//        factor	'intNum', 'floatNum', '(', 'not', 'id', '+', '-'
//        varFuncCallOpt	'id'
//        varFuncCallChoice	'(', ε, 'lsq'
//        factorTail	'.', ε
//        aParams	ε, 'intNum', 'floatNum', '(', 'not', 'id', '+', '-'
//        sign	'+', '-'
//        aParamsTailRep	ε, ','
//        aParamsTail	','
//        varOpt	'int', 'float'
//        funcHead	'int', 'float', 'id'
//        funcDecl	'int', 'float', 'id'
//        assignStat	'id'
//        funcDef	'int', 'float', 'id'
//        term	'intNum', 'floatNum', '(', 'not', 'id', '+', '-'
//        arithExpr	'intNum', 'floatNum', '(', 'not', 'id', '+', '-'
//        expr	'intNum', 'floatNum', '(', 'not', 'id', '+', '-'
//        relExpr	'intNum', 'floatNum', '(', 'not', 'id', '+', '-'
//
