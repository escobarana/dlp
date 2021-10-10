package ast.types;

import ast.expressions.Expression;
import ast.expressions.UnaryMinus;
import visitor.IVisitor;

public class DoubleType extends AbstractType {

    public DoubleType(int line, int column) {
        super(line, column);
    }


    @Override
    public String toString() {

        return "real" ;
    }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    // lab09
    @Override
    public Type uMinus(UnaryMinus uMinus) {
        return this;
    }

    @Override
    public Type cast(Type t) {
        if(t instanceof IntType || t instanceof DoubleType || t instanceof CharType) return t;
        if(t instanceof ErrorType) return t;
        return new ErrorType(this.getLine(), this.getColumn(), "Cannot perform a cast of double to " + t.toString());
    }

    @Override
    public Type arithmetic(Type type, Expression arith){
        if(type instanceof DoubleType) {
            return this;
        } else if(type instanceof ErrorType) {
            return type;
        } else{
            return new ErrorType(arith.getLine(), arith.getColumn(), "Cannot perform arithmetic operation with " + type.toString());
        }
    }

    @Override
    public Type comparison(Type type) {
        if(type instanceof DoubleType) {
            return new IntType(this.getLine(), this.getColumn());
        }else if(type instanceof ErrorType) {
            return type;
        }else{
            return new ErrorType(this.getLine(), this.getColumn(), "Cannot perform comparison with " + type.toString());}
    }

    @Override
    public int numberOfBytes() {
        return 4;
    }

    @Override
    public String suffix()
    {
        return "f";
    }

    @Override
    public boolean canBeWritable() {
        return true;
    }

    @Override
    public boolean canBeAssignable(Type t)
    {
        if(t instanceof DoubleType) return true;
        return false;
    }

    @Override
    public boolean isBuiltInType() { return true; }
}