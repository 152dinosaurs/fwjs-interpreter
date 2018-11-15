grammar FeatherweightJavaScript;

@header { package edu.sjsu.fwjs.parser; }

// ***Lexing rules***

// Reserved words
IF        : 'if' ;
ELSE      : 'else' ;
WHILE	  : 'while' ; //added
FUNCTION  : 'function' ; //added
VAR		  : 'var' ; //added
PRINT	  : 'print' ; //added

// Literals
INT       : [1-9][0-9]* | '0' ;
BOOL	  : 'true' | 'false' ; //added
NULL	  : 'null' ; //added

// Symbols
MUL       : '*' ;
DIV       : '/' ;
ADD 	  : '+' ;
SUB		  : '-' ;
MOD 	  : '%' ;
GREATER   : '>' ;
LESS 	  : '<' ;
GREATEREQ : '>=';
LESSEQ	  : '<=';
EQUAL	  : '==';
SEPARATOR : ';' ;

// Identifiers
ID	      : [a-z|A-Z|'_'][a-z|A-Z|'_'| 0-9]*; //added as identifier, not sure

// Whitespace and comments
NEWLINE   : '\r'? '\n' -> skip ;
BLOCK_COMMENT :'/*' .*? '*/' -> skip ; //added, not sure
LINE_COMMENT  : '//' ~[\n\r]* -> skip ;
WS            : [ \t]+ -> skip ; // ignore whitespace


// ***Parsing rules ***

/** The start rule */
prog: stat+ ;

stat: expr SEPARATOR                                    # bareExpr
    | IF '(' expr ')' block ELSE block                  # ifThenElse
    | IF '(' expr ')' block                             # ifThen
    | WHILE '(' expr ')' block                             # while
    | PRINT '(' expr ')' SEPARATOR?                     # print
    | SEPARATOR                                            # empty
    ;

expr: expr op=( MUL | DIV | MOD ) expr                  # MulDivMod
    | expr op=( ADD | SUB ) expr                         # AddSub
    | expr op=( LESS | LESSEQ | GREATER | GREATEREQ | EQUAL ) expr             # Compare
    | FUNCTION params block                             # FuncDec
    | ID args                                         # FuncApp
    | VAR ID '=' expr                                 # VarDec
    | ID                                                 # VarRef
    | ID '=' expr                                     # Assign
    | INT                                               # int
    | BOOL                                                 # bool
    | NULL                                                 # null
    | '(' expr ')'                                      # parens
    ;

block: '{' stat* '}'                                    # fullBlock
     | stat                                             # simpBlock
     ;

params: '(' (ID (',' ID)* )? ')' ; // ??

args: '(' (expr (',' expr)* )? ')' ; // ??