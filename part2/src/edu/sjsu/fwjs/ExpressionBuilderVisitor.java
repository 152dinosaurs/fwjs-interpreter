package edu.sjsu.fwjs;

import java.util.ArrayList;
import java.util.List;

import edu.sjsu.fwjs.parser.FeatherweightJavaScriptBaseVisitor;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.AddSubContext;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.ArgsContext;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.AssignContext;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.BoolContext;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.ExprContext;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.FuncAppContext;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.FuncDecContext;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.MulDivModContext;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.NullContext;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser.PrintContext;

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
    	// TODO Auto-generated method stub
    	return super.visitFuncDec(ctx);
    }
    
    @Override
    public Expression visitAddSub(AddSubContext ctx) {
        Op oper; 
    	Expression num1 = visit(ctx.expr(0));
        Expression num2 = visit(ctx.expr(1));
        
        if (ctx.op == ctx.ADD())
        {
        	oper = Op.ADD;
        }
        else
        {
        	oper = Op.SUBTRACT;
        }
        return new BinOpExpr(oper, num1, num2);
    }
    
    @Override
    public Expression visitMulDivMod(MulDivModContext ctx) {
    	// TODO Auto-generated method stub
    	return super.visitMulDivMod(ctx);
    }
    
    @Override
    public Expression visitNull(NullContext ctx) {
       	return new ValueExpr(null);
    }
    
    // NOT SURE if we have to do this one or not??? -Micah
    @Override
    public Expression visitArgs(ArgsContext ctx) {
    	// TODO Auto-generated method stub
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
    public Expression visitAssign(AssignContext ctx) {
    	// TODO Auto-generated method stub
    	return super.visitAssign(ctx);
    }
    
    // TODO: there MIGHT be more visits we have to do. -Micah

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

    @Override
    public Expression visitSimpBlock(FeatherweightJavaScriptParser.SimpBlockContext ctx) {
        return visit(ctx.stat());
    }
}
