package ast.types;

import ast.AbstractASTNode;
import ast.expressions.Cast;
import ast.expressions.Expression;
import ast.expressions.UnaryMinus;
import ast.expressions.UnaryNot;

import java.util.List;

public abstract class AbstractType extends AbstractASTNode implements Type {

    public AbstractType(int line, int column) {
        super(line, column);
    }

    // Lab09
    @Override
    public Type arithmetic(Type type, Expression arith){
        return new ErrorType(arith.getLine(), arith.getColumn(), "Cannot perform an arithmetic operation here.");
    }

    @Override
    public Type squareBrackets(Type type, Expression a_index){
        return new ErrorType(type.getLine(), type.getColumn(), "Cannot perform an array indexing here.");
    }

    @Override
    public Type comparison(Type type){
        return new ErrorType(type.getLine(), type.getColumn(), "Cannot perform a comparison between these types here.");
    }

    @Override
    public Type dotOp(String str, Expression dotOp){
        return new ErrorType(dotOp.getLine(),dotOp.getColumn(), "Cannot access a non struct type here.");
    }

    @Override
    public Type logic(Type type, Expression logic){
        return new ErrorType(logic.getLine(), logic.getColumn(), "Cannot perform a logic operation here.");
    }

    @Override
    public Type uMinus(UnaryMinus uMinus){
        return new ErrorType(uMinus.getLine(), uMinus.getColumn(), "Cannot perform a unary minus operation here.");
    }

    @Override
    public Type uNot(UnaryNot uNot){
        return new ErrorType(uNot.getLine(), uNot.getColumn(), "Cannot perform a unary not operation of " + uNot.getExpression().getType());
    }

    @Override
    public Type cast(Type cast){
        return new ErrorType(cast.getLine(), cast.getColumn(), "Cannot perform a cast operation at column: " + cast.getColumn() + " line: " + cast.getLine() );
    }

    @Override
    public Type parenthesis(List<Expression> args, Expression f_inv){
        return new ErrorType(f_inv.getLine(), f_inv.getColumn(), "Cannot invoke function");
    }

    @Override
    public boolean isBoolean() {
        return false;
    }

    @Override
    public boolean canBeWritable() {
        return false;
    }

    @Override
    public boolean canBeAssignable(Type type) { return false; }

    @Override // new functionality
    public boolean isBuiltInType() { return false; }

    @Override
    public int numberOfBytes(){
        throw new RuntimeException("Cannot compute the number of bytes of " + this + " at Line: " + this.getLine() + ", Column: " + this.getColumn());
    }

    @Override
    public String suffix()
    {
        throw new RuntimeException("CG: Can´t compute the suffix of "+this);
    }
}
