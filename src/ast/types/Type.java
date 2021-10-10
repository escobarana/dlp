package ast.types;

import ast.ASTNode;
import ast.expressions.Expression;
import ast.expressions.UnaryMinus;
import ast.expressions.UnaryNot;

import java.util.List;

public interface Type extends ASTNode {

    // LAB09
    Type arithmetic(Type type, Expression arith);
    Type squareBrackets(Type type, Expression a_index);
    Type comparison(Type type);
    Type dotOp(String str, Expression dotOp);
    Type logic(Type type, Expression logic);
    Type uMinus(UnaryMinus uMinus);
    Type uNot(UnaryNot uNot);
    Type parenthesis(List<Expression> args, Expression f_inv);
    Type cast(Type type);

    boolean isBoolean();
    boolean canBeWritable();  //write
    boolean canBeAssignable(Type type);
    boolean isBuiltInType();

    // Lab10 - for MAPL
    int numberOfBytes();

    // Lab 11
    String suffix();

}
