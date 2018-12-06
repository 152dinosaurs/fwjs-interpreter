package edu.sjsu.fwjs;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import edu.sjsu.fwjs.parser.FeatherweightJavaScriptBaseVisitor;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.AddSubContext;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.ArgsContext;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.AssignContext;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.BoolContext;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.CompareContext;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.ExprContext;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.FuncAppContext;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.FuncDecContext;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.MulDivModContext;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.NullContext;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.PrintContext;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.VarDecContext;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.VarRefContext;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.WhileContext;

public class ExpressionBuilderVisitor extends FeatherweightJavaScriptBaseVisitor<Expression>{
    @Override
    public Expression visitProg(FeatherweightJavaScriptParser.ProgContext ctx) {
        List<Expression> stmts = new ArrayList<Expression>();
        for (int i=0; i<ctx.stat().size(); i++) {
            Expression exp = visit(ctx.stat(i));
            if (exp != null) stmts.add(exp);
        }
        return listToSeqExp(stmts);
    }

    @Override
    public Expression visitBareExpr(FeatherweightJavaScriptParser.BareExprContext ctx) {
        return visit(ctx.expr());
    }
    
    @Override
    public Expression visitPrint(PrintContext ctx) {
    	Expression exp = visit(ctx.expr());
    	return new PrintExpr(exp);
    }

    @Override
    public Expression visitIfThenElse(FeatherweightJavaScriptParser.IfThenElseContext ctx) {
        Expression cond = visit(ctx.expr());
        Expression thn = visit(ctx.block(0));
        Expression els = visit(ctx.block(1));
        return new IfExpr(cond, thn, els);
    }
    
    @Override
    public Expression visitIfThen(FeatherweightJavaScriptParser.IfThenContext ctx) {
        Expression cond = visit(ctx.expr());
        Expression thn = visit(ctx.block());
        return new IfExpr(cond, thn, null);
    }

    @Override
    public Expression visitFuncApp(FuncAppContext ctx) {
    	Expression func = null;
    	// TODO: fix this
    	return new FunctionAppExpr(func, getArgList(ctx.args()));
    }
    
    @Override
    public Expression visitFuncDec(FuncDecContext ctx) {
    	List<String> params = new ArrayList<String>();
    	params.add(ctx.FUNCTION().toString());
        Expression body = visit(ctx.block());
    	return new FunctionDeclExpr(params, body);
    }
    
    @Override
    public Expression visitAddSub(AddSubContext ctx) {
    	Op operator = getOp(ctx.op.getText());
    	Expression e1 = visit(ctx.expr(0));
    	Expression e2 = visit(ctx.expr(1));
    	return new BinOpExpr(operator, e1, e2);
    }
    
    @Override
    public Expression visitVarRef(VarRefContext ctx) {
    	return new VarExpr(ctx.ID().getText());
    }
    
    @Override
    public Expression visitVarDec(VarDecContext ctx) {
    	String varName = ctx.ID().getText();
    	Expression exp = visit(ctx.expr());
    	return new VarDeclExpr(varName, exp);
    }
    
    @Override
    public Expression visitMulDivMod(MulDivModContext ctx) {
    	Op operator = getOp(ctx.op.getText());
    	Expression e1 = visit(ctx.expr(0));
    	Expression e2 = visit(ctx.expr(1));
    	return new BinOpExpr(operator, e1, e2);
    }
    
    @Override
    public Expression visitWhile(WhileContext ctx) {
        Expression cond = visit(ctx.expr());
        Expression body = visit(ctx.block());
    	return new WhileExpr(cond, body);
    }
    
    @Override
    public Expression visitNull(NullContext ctx) {
       	return new ValueExpr(new NullVal());
    }
    
    // NOT SURE if we have to do this one or not??? -Micah
    @Override
    public Expression visitArgs(ArgsContext ctx) {
    	
    	return super.visitArgs(ctx);
    }
    
    /**
     * Helper function to get a List of expressions from an ArgsContext.
     * @param ctx the args context
     * @return a List of expressions
     */
    public List<Expression> getArgList(ArgsContext ctx){
    	List<Expression> result = new ArrayList<>();
    	for(ExprContext thisExpr : ctx.expr()) {
    		result.add(visit(thisExpr));
    	}
    	return result;
    }
    
    @Override
    public Expression visitCompare(CompareContext ctx) {
    	Op operator = getOp(ctx.op.getText());
    	Expression e1 = visit(ctx.expr(0));
    	Expression e2 = visit(ctx.expr(1));
    	return new BinOpExpr(operator, e1, e2);
    }
    
    @Override
    public Expression visitAssign(AssignContext ctx) {
    	String name = ctx.ID().toString();
    	Expression val = visit(ctx.expr());
    	return new AssignExpr(name, val);
    }
    
    @Override
    public Expression visitInt(FeatherweightJavaScriptParser.IntContext ctx) {
        int val = Integer.valueOf(ctx.INT().getText());
        return new ValueExpr(new IntVal(val));
    }
    
    @Override
    public Expression visitBool(BoolContext ctx) {
    	boolean val = Boolean.valueOf(ctx.BOOL().getText());
    	return new ValueExpr(new BoolVal(val));
    }

    @Override
    public Expression visitParens(FeatherweightJavaScriptParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Expression visitFullBlock(FeatherweightJavaScriptParser.FullBlockContext ctx) {
        List<Expression> stmts = new ArrayList<Expression>();
        for (int i=1; i<ctx.getChildCount()-1; i++) {
            Expression exp = visit(ctx.getChild(i));
            stmts.add(exp);
        }
        return listToSeqExp(stmts);
    }

    /**
     * Converts a list of expressions to one sequence expression,
     * if the list contained more than one expression.
     */
    private Expression listToSeqExp(List<Expression> stmts) {
        if (stmts.isEmpty()) return null;
        Expression exp = stmts.get(0);
        for (int i=1; i<stmts.size(); i++) {
            exp = new SeqExpr(exp, stmts.get(i));
        }
        return exp;
    }
    
    private Op getOp(String opString) {
    	Op op = null;
    	switch (opString) {
		case "==":
			op = Op.EQ;
			break;
    	case "+":
    		op = Op.ADD;
    		break;
    	case "-":
    		op = Op.SUBTRACT;
    		break;
    	case "/":
    		op = Op.DIVIDE;
    		break;
    	case "*":
    		op = Op.MULTIPLY;
    		break;
    	case "%":
    		op = Op.MOD;
    		break;
    	case ">=":
    		op = Op.GE;
    		break;
    	case ">":
    		op = Op.GT;
    		break;
    	case "<=":
    		op = Op.LE;
    		break;
    	case "<":
    		op = Op.LT;
    		break;
    		
    }
    	return op;
    }

    @Override
    public Expression visitSimpBlock(FeatherweightJavaScriptParser.SimpBlockContext ctx) {
        return visit(ctx.stat());
    }
}
