grammar FeatherweightJavaScript;

@header { package edu.sjsu.fwjs.parser; }

// ***Lexing rules***

// Reserved words
IF        : 'if' ;
ELSE      : 'else' ;
WHILE	  : 'while' ;
FUNCTION  : 'function' ;
VAR		  : 'var' ;
PRINT	  : 'print' ;

// Literals
INT       : [1-9][0-9]* | '0' ;
BOOL	  : 'true' | 'false' ;
NULL	  : 'null' ;

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
ID	      : [a-zA-Z_][a-zA-Z0-9_]*;

// Whitespace and comments
NEWLINE   : '\r'? '\n' -> skip ;
BLOCK_COMMENT :'/*' .*? '*/' -> skip ;
LINE_COMMENT  : '//' ~[\n\r]* -> skip ;
WS            : [ \t]+ -> skip ; 


// ***Parsing rules ***

/** The start rule */
prog: stat+ ;

<<<<<<< HEAD
stat: expr SEPARATOR                                    # bareExpr
    | IF '(' expr ')' block ELSE block                  # ifThenElse
    | IF '(' expr ')' block                             # ifThen
    | WHILE '(' expr ')' block                          # while
    | PRINT '(' expr ')' SEPARATOR?                     # print
    | SEPARATOR                                         # empty
    ;

expr: expr op=( MUL | DIV | MOD ) expr                  				# Mul Div Mod
    | expr op=( ADD | SUB ) expr                         				# Add Sub
    | expr op=( LESS | LESSEQ | GREATER | GREATEREQ | EQUAL ) expr      # Compare
    | FUNCTION params block                             				# Function declaration
    | ID args                                         					# Function application f(1,2)
    | VAR ID '=' expr                                 					# variable declaration var x=3
    | ID                                                 				# variable reference (x)
    | ID '=' expr                                     					# assignment statement (x=4)
    | INT                                               				# int
    | BOOL                                                 				# bool
    | NULL                                                 				# null
    | '(' expr ')'                                      				# An expression in parenthesis

=======
stat: expr SEPARATOR                                       # bareExpr
    | IF '(' expr ')' block ELSE block                     # ifThenElse
    | IF '(' expr ')' block                                # ifThen
    | WHILE '(' expr ')' block                             # while
    | PRINT '(' expr ')' SEPARATOR?                        # print
    | SEPARATOR                                            # empty
    ;

expr: expr op=( MUL | DIV | MOD ) expr                     # MulDivMod
    | expr op=( ADD | SUB ) expr                           # AddSub
    | expr op=( LESS | LESSEQ | GREATER | GREATEREQ | EQUAL ) expr                                                   # Compare
    | FUNCTION params block                                # FuncDec
    | ID args                                              # FuncApp
    | VAR ID '=' expr                                      # VarDec
    | ID                                                   # VarRef
    | ID '=' expr                                          # Assign
    | INT                                                  # int
    | BOOL                                                 # bool
    | NULL                                                 # null
    | '(' expr ')'                                         # parens
>>>>>>> 46c1569a1d9017cd90cd14e1011a3997038fd804
    ;

block: '{' stat* '}'                                       # fullBlock
     | stat                                                # simpBlock
     ;

params: '(' (ID (',' ID)* )? ')' ; //parameters

args: '(' (expr (',' expr)* )? ')' ; //arguments