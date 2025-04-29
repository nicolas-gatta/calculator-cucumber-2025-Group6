grammar Expression;

expr
    : infixExpr
    | prefixExpr
    | postfixExpr
    ;

infixExpr
    : '(' expr op=operator expr ')' #InfixOperation
    | INT                             #IntLiteral
    ;

prefixExpr
    : '(' operator (expr (separator expr)*)? ')' #PrefixOperation
    ;

postfixExpr
    : '(' (expr (separator expr)*)? operator ')' #PostfixOperation
    ;

operator
    : '+'
    | '-'
    | '*'
    | '/'
    ;

separator
    : ','
    | WS+ //white space separator
    ;

INT : [0-9]+;

WS : [ \t\r\n]+ -> skip;