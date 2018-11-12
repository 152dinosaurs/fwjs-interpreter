# fwjs-interpreter

## Part 2 Prompt
At this point, you have written an interpreter for our Featherweight JavaScript language.
In this assignment, you will write the lexer and parser for this language using ANTLR.
(We won't connect the two just yet -- that will be the next assignment).

### PART A -- Write the lexer
-------------------------------
Before you can write the parsing rules, you will need to specify the lexing rules.  In this step, you specify the valid words of the language.

In ANTLR, lexing rules start with a capital letter.

The reserved words in FWJS are:

* `if`
* `else`
* `while`
* `function`
* `var`
* `print`

Additionally, you will need rules for literals:

* BOOLs will be either `true` or `false`.
* INTs are integers of any length.  There should be no unnecessary leading zeroes.
* NULL is represented by the word `null`.

You will also have various symbols in the language:
* `*`, `/`, `+`, `-`, `%` are the usual mathematical operations.
* `>`, `<`, `>=`, `<=`, `==` are the usual equality comparisons.
* `;` separates statements.

You will need identifiers.  The rules for variable names in FWJS:

* The first character must be an alphabetic character or an underscore.
* The remaining characters must be alphabetic characters, numeric characters, or underscores.

Finally, you will need to consider whitespace and comments.
Neither should be part of the resulting AST, so be sure to use 'skip'.

* NEWLINEs are either `\r\n` or `\n`
* BLOCK_COMMENTs are the usual C-style `/* comments */`.  They do not support nesting.
* LINE_COMMENTs comment out everything until the end of the line.  They use the usual C++ style // where everything after a slash-slash is a comment.
* Spaces and tabs are whitespace characters, and can be ignored.


### PART B -- Write the parser
-------------------------------
Once you have your lexing rules complete, you may add your parsing rules.
In ANTLR, parsing rules start with a lower case letter.

The rules are as follows:

A program is one or more statements.

A statement may be:

* An expression followed by a semicolon.
* An if statement, with the usual JavaScript syntax.
* A while statement.
* A print statement.
* An empty statement (a semicolon by itself).

An expression can be:

* Binary operations, where
  * `*`, `/`, `'%'` are the highest precedence.
  * `+` and `-` middle precedence.
  * `<`, `<=`, `>`, `>=`, and `==` are the lowest precedence.
* A function declaration (e.g. `function(x){ x+1 }`)
* A function application (e.g. `f(1,2)`)
* A variable declaration (`var x=3`)
* a variable reference (`x`)
* An assignment statement (`x=4`)
* A constant (`int`, `bool`, or `null`)
* An expression in parenthesis

The sampleParseTrees/ directory contains some parse trees for the programs found in fwjsScripts/.  All .tree files were generated with grun's -tree option.  Your parse trees should be similar, but might not be exactly identical.  (Note that grun supports a -gui option that might be helpful to examine).


### PART C -- Submit your code
----------------------------
When you have completed this assignment, submit your grammar file.  No other code is needed.

When you submit, list the names of all your team members within Canvas.



## Part 1 Prompt

In this assignment, you will write a parser and an interpreter for Featherweight JavaScript (FWJS), a minimal version of JavaScript (or something similar to it).  You may work in teams of 1-4 people.

Starter code is available on the course website.

Expressions in this language include:

* If statements.  Unlike in JavaScript, evaluating if statements returns a value.
* While expressions.  Unlike in JavaScript, evaluating while statements returns a value (specifically null).
* Sequence expressions.  (In other words, you may have several statements in a row.)
* Binary operation expressions, such as addition, subtraction, etc.
* Variable declarations.
* Assignment statements.
* Functions.  Evaluating a function will return a closure.
* Function application.  Unlike in JavaScript, FWJS will include implicit returns (similar to Scheme and Ruby).
* Print statements.  Similar to console.log in JavaScript, these statements evaluate an expression and print the result.

Values in this language include booleans, ints, a special null values, and closures.  A closure is a combination of a function definition and its scope environment tracking any free variables in the function body.


Expression.java contains the representation for all expressions in FWJS.  Each class implements the evaluate method, which takes in an Environment (i.e. the mapping of variables to values) and returns a value.  The types of Values are stored in Value.java.

Most of the interpreter is similar to homework 2.  The main new challenge will be handling functions, which are closures in this language.

FunctionDeclExpr takes a list of parameter names and a body.  When evaluated, it returns a closure included the environment used when it was defined.

FunctionAppExpr takes an expression (which should evaluate to a closure) and a list of arguments.  When evaluated, the environment is ignored.  Instead a new environment is created including all of the parameters with the arguments bound to the parameter names.  Note that you will need to evaluate each argument to a value before the closure.apply method can be used.

Environment.java handles the resolution of JavaScript variables.  Update the resolveVar and updateVar methods to handle variable lookup correctly.

* If a variable is undefined in the current environment, look for it in the outer scope.
  (This holds for both updateVar and resolveVar).
* When resolving a variable that is undefined in the global scope, return null.  (In JavaScript, we would
  return undefined, but we will simplify the behavior).
* When updating a variable that is undefined in the global scope, create a new variable.
  (JavaScript follows this behavior; hence, if you forget to type 'var' when creating a new local
   variable, you might create a global variable instead).

* ExpressionTest.java has some JUnit test cases that will be useful to consider.  You can get the JAR files for JUnit from http://junit.org.


A Makefile (http://www.gnu.org/software/make/manual/make.html) is provided for this project.  Update the two lines at the top of the file to specify where you have your JUnit libraries.

`make` will compile all code to a build/ directory.

`make test` runs the unit tests for this assignment.  All tests should pass once you have completed the assignment.

`make solution.zip` will produce the zip file that you must submit as your solution for this assignment.
