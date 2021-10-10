package codeGeneration;

import ast.expressions.*;
import ast.statements.FunctionInvocation;
import ast.types.StructField;
import ast.types.StructType;
import visitor.AbstractVisitor;

// Code to push the value of an expression
public class ValueCGVisitor extends AbstractVisitor<Void,Void> {
    //Expressions

    private CodeGeneration cg;
    private AddressCGVisitor av;

    public ValueCGVisitor(CodeGeneration cg)
    {
        this.cg = cg;
    }

    public void setAddressVisitor(AddressCGVisitor vv)
    {
        this.av=vv;
    }

    // Lab 11
    /*
    value[[CharLiteral: expression -> CHAR_CONSTANT]] =
        <push>exp.type.suffix()< >(int)exp.value
    */
    public Void visit(CharLiteral charLiteral, Void params) {
        cg.push(charLiteral.getType(), charLiteral.getValue());
        return null;
    }

    // Lab 11
    /*
    value[[IntLiteral: expression -> INT_CONSTANT]] =
        <push>exp.type.suffix()< >exp.value
    */
    public Void visit(IntLiteral intLiteral, Void params) {
        cg.push(intLiteral.getType(), intLiteral.getValue());
        return null;
    }

    // Lab 11
    /*
    value[[DoubleLiteral: expression -> DOUBLE_CONSTANT]] =
        <push>exp.type.suffix()< >exp.value
    */
    public Void visit(RealLiteral realLiteral, Void params) {
        cg.push(realLiteral.getType(), realLiteral.getValue());
        return null;
    }

    // Lab 11
    /*
    value[[Variable: expression -> ID]]()=
        address[[expression]]
        <load>expression.definition.type.suffix()
     */
    public Void visit(Variable variable, Void params) {
        variable.accept(this.av, params);
        if(variable.getType().getClass() == StructType.class) { // EXAM
            for (StructField sf : ((StructType) variable.getType()).getFields()) {
                cg.load(sf.getType());
            }
        }else{
            cg.load(variable.getDefinition().getType());
        }

        return null;
    }

    // Lab 11
    /*
    value[[Cast: expression1 -> type expression2]] () =
        value[[expression2]]
        <cast> expression2.type.suffix() type.suffix()
     */
    public Void visit(Cast cast, Void params) {
        cast.getExpression().accept(this, params);
        cg.conversion(cast.getExpression().getType(), cast.getType());
        return null;
    }

    // Lab 11
    /*
    value[[Arithmetic: expression -> expression1 (+|-|*|/) expression2]] =
        value[[expression1]]
        expression1.type.conversion(expression.type)
        value[[expression2]]
        expression2.type.conversion(expression.type)
        switch(expression.operator) {
            cg.arithmetic(expression.operator, expression.type)
            case "+": <add> expression1.type.suffix() break;
            case "-": <sub> expression1.type.suffix() break;
            case "*": <mul> expression1.type.suffix() break;
            case "/": <div> expression1.type.suffix() break;
    */
    public Void visit(Arithmetic arithmetic, Void params) {
        arithmetic.getE1().accept(this, params);
        cg.conversion(arithmetic.getE1().getType(), arithmetic.getType());
        arithmetic.getE2().accept(this, params);
        cg.conversion(arithmetic.getE2().getType(), arithmetic.getType());
        cg.arithmetic(arithmetic.getOperator(), arithmetic.getType());
        return null;
    }

    // Lab 11
    /*
    value[[Comparison: expression -> expression1 (>|<|>=|<=|!=|==) expression2]] =
        value[[expression1]]
        expression1.type.convertTo(expression.type)
        value[[expression2]]
        expression2.type.convertTo(expression.type)
        switch(expression.operator) {
            cg.comparison(expression.operator)
            case ">": <gt> expression.type.suffix() break;
            case "<": <lt> expression.type.suffix() break;
            case ">=": <ge> expression.type.suffix() break;
            case "<=": <le> expression.type.suffix() break;
            case "==": <eq> expression.type.suffix() break;
            case "!=": <ne> expression.type.suffix() break;
         }
     */
    public Void visit(Comparison comparison, Void params) {
        comparison.getE1().accept(this, params);
        cg.conversion(comparison.getE1().getType(), comparison.getType());
        comparison.getE2().accept(this, params);
        cg.conversion(comparison.getE2().getType(), comparison.getType());
        cg.comparison(comparison.getOperator(), comparison.getType());
        return null;
    }

    // Lab 11
    /*
    value[[Logic: expression-> expression1 operator expression2]] =
        value[[expression1]]
        value[[expression2]]
        switch(expression.operator){
            case "&&": <AND> expression.type.suffix() break;
            case "||": <OR> expression.type.suffix() break;
        }
     */
    public Void visit(Logic logical, Void params) {
        logical.getE1().accept(this, params);
        logical.getE2().accept(this, params);
        cg.logic(logical.getOperator());
        return null;
    }

    @Override
    public Void visit(UnaryNot unaryNot, Void param) {
        unaryNot.getExpression().accept(this, param);
        cg.not();
        return null;
    }

    @Override
    public Void visit(UnaryMinus um, Void param) {
        cg.push(um.getType(), 0);
        um.getExpression().accept(this, param);
        cg.sub(um.getType());
        return null;
    }

    // Lab 12
    /*
    value[[ArrayIndexing: expression1 -> expression2 expression3]] =
        address[[expression1]]
        <load> expression1.type.suffix()
     */
    public Void visit(ArrayIndexing indexing, Void params)
    {
        indexing.accept(this.av, params);
        cg.load(indexing.getType());
        return null;
    }

    // Lab 12
    /*
    value[[DotOp: expression1 -> expression2 ID]]=
        address[[expression1]]
        <load> expression1.type.suffix()
     */
    public Void visit(DotOperator dot, Void params) {
        dot.accept(this.av, params);
        cg.load(dot.getType());
        return null;
    }

    // Lab 13
    /*
    value[[FunctionInvocation: expression1 -> expression2 expression3*]] =
        expression3*.forEach(expression -> value[expression])
        <call > expression2.name
     */
    public Void visit(FunctionInvocation invocation, Void params)
    {
        invocation.getExpressions().forEach(argument -> argument.accept(this, params));
        cg.call(invocation.getName().getName());
        return null;
    }
}
