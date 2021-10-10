package codeGeneration;

import ast.definitions.VarDefinition;
import ast.expressions.ArrayIndexing;
import ast.expressions.DotOperator;
import ast.expressions.Variable;
import ast.types.StructField;
import ast.types.StructType;

// Code to push the address of an expression
public class AddressCGVisitor extends AbstractCGVisitor<Void, Void>{
    // Variable, ArrayIndexing, DotOperator

    private CodeGeneration cg;
    private ValueCGVisitor vv;

    public AddressCGVisitor(CodeGeneration cg)
    {
        this.cg = cg;
    }

    public void setValueVisitor(ValueCGVisitor vv) {
        this.vv = vv;
    }

    /*
    // Lab 11
    address[[Variable: exp -> ID]] =
        if(exp.definition.scope==0)
            <pusha > exp.definition.offset
        else {
            <push bp>
            <pusha > exp.definition.offset
            <addi>
        }
    */
    public Void visit(Variable variable, Void params) {
        if(variable.getDefinition().getScope()==0) // global variable
            cg.pushA(((VarDefinition)variable.getDefinition()).getOffset());
        else
        {
            cg.pushBP();
            cg.pushI(((VarDefinition)variable.getDefinition()).getOffset());
            cg.addi();
        }
        return null;
    }

    /*
    // Lab 12
    address[[ArrayIndexing: expression -> expression1 expression2]] =
        address[[expression1]]
        value[[expression2]]
                <pushi > expression1.type.numberOfBytes()
                <muli>
                <addi>
    */
    public Void visit(ArrayIndexing indexing, Void params) {
        indexing.getE1().accept(this, params);
        indexing.getE2().accept(this.vv, params);
        cg.pushI((indexing.getE1().getType()).numberOfBytes());
        cg.muli();
        cg.addi();
        return null;
    }

    /*
    // Lab 12
    address[[DotOperator: expression1 -> expression2 ID]] =
        address[[expression2]]
        <pushi > expression2.getType().getField(expression1.member).offset
        <addi>
    */
    public Void visit(DotOperator dot, Void params)
    {
        dot.getExpression().accept(this, params);
        cg.pushI(((StructType)dot.getExpression().getType()).getField(dot.getField()).getOffset());
        cg.addi();
        return null;
    }
}
