package edu.sjsu.fwjs;

import java.util.ArrayList;
import java.util.List;

/**
 * FWJS expressions.
 */
public interface Expression {
    int value = 0;

	/**
     * Evaluate the expression in the context of the specified environment.
     */
    public Value evaluate(Environment env);
}

// NOTE: Using package access so that all implementations of Expression
// can be included in the same file.

/**
 * FWJS constants.
 */
class ValueExpr implements Expression {
    private Value val;
    public ValueExpr(Value v) {
        this.val = v;
    }
    public Value evaluate(Environment env) {
        return this.val;
    }
}

/**
 * Expressions that are a FWJS variable.
 */
class VarExpr implements Expression {
    private String varName;
    public VarExpr(String varName) {
        this.varName = varName;
    }
    public Value evaluate(Environment env) {
        return env.resolveVar(varName);
    }
}

/**
 * A print expression.
 */
class PrintExpr implements Expression {
    private Expression exp;
    public PrintExpr(Expression exp) {
        this.exp = exp;
    }
    public Value evaluate(Environment env) {
        Value v = exp.evaluate(env);
        System.out.println(v.toString());
        return v;
    }
}
/**
 * Binary operators (+, -, *, etc).
 * Currently only numbers are supported.
 */
class BinOpExpr implements Expression {
    private Op op;
    private Expression e1;
    private Expression e2;
    public BinOpExpr(Op op, Expression e1, Expression e2) {
        this.op = op;
        this.e1 = e1;
        this.e2 = e2;
    }

    @SuppressWarnings("incomplete-switch")
    public Value evaluate(Environment env) {
    	
    	IntVal num1 = (IntVal)e1.evaluate(env);
    	IntVal num2 = (IntVal)e2.evaluate(env);
    	
        if(op == op.ADD){
        	return new IntVal (num1.toInt() + num2.toInt());     	
		}else if (op == op.SUBTRACT){
        	return new IntVal (num1.toInt() - num2.toInt());
		}else if (op == op.MULTIPLY){
			return new IntVal (num1.toInt() * num2.toInt());
		}else if (op == op.DIVIDE){
			return new IntVal (num1.toInt() / num2.toInt());
		}else if (op == op.MOD){
			return new IntVal (num1.toInt() % num2.toInt());
		}else if (op == op.GT){
			return new BoolVal (num1.toInt() > num2.toInt());
		}else if (op == op.GE){
			return new BoolVal (num1.toInt() >= num2.toInt());
		}else if (op == op.LT){
			return new BoolVal (num1.toInt() < num2.toInt());
		}else if (op == op.LE){
			return new BoolVal (num1.toInt() <= num2.toInt());
		}else if (op == op.EQ){
			return new BoolVal (num1.equals(num2));
		}else{
			return null;
		}

    }
}

/**
 * If-then-else expressions.
 * Unlike JS, if expressions return a value.
 */
class IfExpr implements Expression {
    private Expression cond;
    private Expression thn;
    private Expression els;
    public IfExpr(Expression cond, Expression thn, Expression els) {
        this.cond = cond;
        this.thn = thn;
        this.els = els;
    }
    public Value evaluate(Environment env) {
        
    	BoolVal check = (BoolVal) cond.evaluate(env); 
    	if (check.toBoolean())
    	{
    		return thn.evaluate(env);
    	}else 
    	{
    		return els.evaluate(env);
    	}
    }
}

/**
 * While statements (treated as expressions in FWJS, unlike JS).
 */
class WhileExpr implements Expression {
    private Expression cond;
    private Expression body;
    public WhileExpr(Expression cond, Expression body) {
        this.cond = cond;
        this.body = body;
    }
    public Value evaluate(Environment env) {
    	Value result = new NullVal();
    	
    	while (((BoolVal)cond.evaluate(env)).toBoolean())
    	{
    		result = body.evaluate(env);
 
    	}
    	
    	return result;
    }
}

/**
 * Sequence expressions (i.e. 2 back-to-back expressions).
 */
class SeqExpr implements Expression {
    private Expression e1;
    private Expression e2;
    public SeqExpr(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
    public Value evaluate(Environment env) {
        e1.evaluate(env);
        Value v2 = e2.evaluate(env); 
        return v2; //because of implicit return
        /*
         * TODO: Verify that this actually works.
         * The relevant JUnit test depends on AssignExpr and BinOpExpr, 
         * so I can't verify if this does anything meaningful yet.
         */
    }
}

/**
 * Declaring a variable in the local scope.
 */
class VarDeclExpr implements Expression {
    private String varName;
    private Expression exp;
    public VarDeclExpr(String varName, Expression exp) {
        this.varName = varName;
        this.exp = exp;
    }
    public Value evaluate(Environment env) {
        Value v = exp.evaluate(env);
        env.createVar(varName, v);
        return v;
    }
}

/**
 * Updating an existing variable.
 * If the variable is not set already, it is added
 * to the global scope.
 */
class AssignExpr implements Expression {
    private String varName;
    private Expression e;
    public AssignExpr(String varName, Expression e) {
        this.varName = varName;
        this.e = e;
    }
    public Value evaluate(Environment env) {
    	Value v = e.evaluate(env);
    	
    	/*
    	 * due to the implementation of updateVar, this will do the
    	 * right thing regardless if the variable has been set already.
    	 * See comment on Environment.updateVar() for more info.
    	 */
        env.updateVar(varName, v);
        return v;
    }
}

/**
 * A function declaration, which evaluates to a closure.
 */
class FunctionDeclExpr implements Expression {
    private List<String> params;
    private Expression body;
    public FunctionDeclExpr(List<String> params, Expression body) {
        this.params = params;
        this.body = body;
    }
    public Value evaluate(Environment env) {
    	body.evaluate(env);
        ClosureVal closure = new ClosureVal(params, body, env);
        return closure;
    }
}

/**
 * Function application.
 * FunctionAppExpr takes an expression 
 * (which should evaluate to a closure) 
 * and a list of arguments.  When evaluated, 
 * the environment is ignored.  Instead a new environment 
 * is created including all of the parameters with the 
 * arguments bound to the parameter names.  Note that you 
 * will need to evaluate each argument to a value before 
 * the closure.apply method can be used.
 */
class FunctionAppExpr implements Expression {
    private Expression f;
    private List<Expression> args;
    public FunctionAppExpr(Expression f, List<Expression> args) {
        this.f = f;
        this.args = args;
    }
    public Value evaluate(Environment env) 
    {
        Environment newEnv = new Environment();
        
        List <Value> evalTo = new ArrayList <Value>();
        List <String> evalToString = new ArrayList <String>();
        for (int i = 0; i < args.size(); i++)
        {
        	evalTo.add(args.get(i).evaluate(env));
        	evalToString.add(evalTo.get(i).toString());
        	newEnv.updateVar(evalToString.get(i), evalTo.get(i));
        }
        ClosureVal want = new ClosureVal(evalToString, f, newEnv);
        
        return want.apply(evalTo);
    }
}