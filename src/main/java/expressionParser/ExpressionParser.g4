grammar ExpressionParser;

// Entry point
expr: expr op=('*'|'/') expr   # MulDivExpr
    | expr op=('+'|'-') expr   # AddSubExpr
    | '(' expr ')'             # ParensExpr
    | complex                  # ComplexExpr
    | number                   # NumberExpr
    ;

// Numbers
number: RATIONAL | REAL | INT;
complex : '(' number ('+'|'-') number 'i' ')' ;

// Tokens
RATIONAL: INT '/' INT ;
REAL    : INT '.' [0-9]+ ;
INT     : [0-9]+ ;

WS : [ \t\r\n]+ -> skip ;
