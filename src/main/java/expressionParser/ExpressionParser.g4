grammar ExpressionParser;

// Tokens
INT     : [0-9]+;
REAL    : INT '.' [0-9]+;
RATIONAL: INT '/' INT;
VARIABLE: [a-hj-zA-HJ-Z]; // All letters except i and I are for imaginary number

WS: [ \t\r\n]+ -> skip ;

// Operator
operator : '+' | '-' | '*' | '/';

// Numbers
number: RATIONAL | REAL | INT;
imaginary: number 'i';
complex: '('? number op = ('+'|'-') imaginary ')'?;

// Matrix
matrix: '[' row (',' row)* ']';
row: '[' number (',' number)* ']';

matrixOperator: ('^T' | 'T^')   # Transpose
              | ('^I' | 'I^')   # Identity
              | ('^-1' | '-1^') # Inverted;

matrixFunction: '('? matrix ')'? matrixOperator # MatrixPostfix
              | matrixOperator'('? matrix ')'?  # MatrixPrefix;

// Linear Equation
variableNumber: number? VARIABLE ;
equation: '('? expr op = '=' expr ')'? ;
linearEquation: 'solve' '(' equation (',' equation)* ')';

// Entry point
expr: op = operator '(' expr (',' expr)* ')'    # PrefixOperationExpr
    | '(' expr (',' expr)* ')' op = operator    # PostOperationExpr
    | expr op = operator expr                   # InfixOperationExpr
    | '(' expr ')'                              # ParensExpr
    | number                                    # NumberExpr
    | complex                                   # ComplexExpr
    | matrix                                    # MatrixExpr
    | variableNumber                            # VarExpr
    | linearEquation                            # LinearExpr
    | matrixFunction                            # MatrixFunctionExpr
    ;
