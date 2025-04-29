grammar ExpressionParser;

// Tokens
INT     : [0-9]+;
REAL    : INT '.' [0-9]+;
RATIONAL: INT '/' INT;

WS: [ \t\r\n]+ -> skip ;

// Operator
operator : '+' | '-' | '*' | '/';

// Numbers
number: RATIONAL | REAL | INT;
complex: '(' number ('+'|'-') number 'i' ')';

// Entry point
expr: op = operator '(' expr (',' expr)* ')'    # PrefixOperationExpr
    | '(' expr (',' expr)* ')' op = operator    # PostOperationExpr
    | expr op = operator expr                   # InfixOperationExpr
    | '(' expr ')'                              # ParensExpr
    | number                                    # NumberExpr
    | complex                                   # ComplexExpr
    ;