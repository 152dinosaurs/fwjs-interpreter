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
INT       : [1-9][0-9]* | '0' ; //any length + no leading 0
BOOL	  : 'true' | 'false' ; //added, not sure
NULL	  : 'null' //not sure on this

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
VARNAME	  : ['a'..'z' | 'A'..'Z' | '_'] ['a'..'z' | 'A'..'Z' | '_' | 0-9]* ; //added as identifier, not sure

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
    ;

expr: expr op=( '*' | '/' | '%' ) expr                  # MulDivMod
    | INT                                               # int
    | '(' expr ')'                                      # parens
    ;

block: '{' stat* '}'                                    # fullBlock
     | stat                                             # simpBlock
     ;

