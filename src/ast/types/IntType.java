package ast.types;

import ast.expressions.Expression;
import ast.expressions.UnaryMinus;
import ast.expressions.UnaryNot;
import visitor.IVisitor;

public class IntType  extends AbstractType {

    public IntType(int line, int column) {

        super(line, column);
    }

    @Override
    public String toString() { return "int"; }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    // lab09
    @Override
    public Type comparison(Type t)
    {
        if(t instanceof IntType) return this;
        if(t instanceof ErrorType) return t;
        return new ErrorType(this.getLine(), this.getColumn(), "Cannot perform a comparison operation with int and "+t.toString());
    }

    @Override
    public Type uMinus(UnaryMinus uMinus) {
        return this;
    }

    @Override
    public Type uNot(UnaryNot uNot)
    {
        return this;
    }

    @Override
    public Type arithmetic(Type t, Expression arithmetic)
    {
        if(t instanceof IntType) { return this; }
        if(t instanceof ErrorType) { return t; }
        return new ErrorType(arithmetic.getLine(), arithmetic.getColumn(), "Cannot perform an arithmetic operation for integer type and " + t.toString());
    }

    @Override
    public Type cast(Type t) {
        if(t instanceof IntType || t instanceof DoubleType || t instanceof CharType) { return t; }
        if(t instanceof ErrorType) { return t; }
        return new ErrorType(this.getLine(), this.getColumn(), "Cannot perform a cast of integer to " + t.toString());
    }

    @Override
    public Type logic(Type t, Expression logical)
    {
        if(t instanceof IntType) { return this; }
        if(t instanceof ErrorType) { return t; }
        return new ErrorType(logical.getLine(), logical.getColumn(), "Cannot perform a logical operation with int and " + t.toString());
    }

    @Override
    public int numberOfBytes() { return 2; }

    @Override
    public String suffix() { return "i"; }

    @Override
    public boolean isBoolean() { return true; }

    @Override
    public boolean canBeWritable() { return true; }

    @Override
    public boolean canBeAssignable(Type t)
    {
        if(t instanceof IntType) { return true; }
        return false;
    }

    @Override
    public boolean isBuiltInType() { return true; }
}
