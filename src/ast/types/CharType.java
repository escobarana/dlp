package ast.types;

import ast.expressions.Expression;
import visitor.IVisitor;

public class CharType extends AbstractType {

    public CharType(int line, int column) {
        super(line, column);
    }

    @Override
    public String toString() {

        return "char";
    }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    // lab09
    @Override
    public Type arithmetic(Type type, Expression arith){
        if(type instanceof CharType) {
            return new IntType(arith.getLine(), arith.getColumn());
        } else if(type instanceof ErrorType) {
            return type;
        } else{
        return new ErrorType(arith.getLine(), arith.getColumn(), "Cannot perform arithmetic operation with " + type.toString());
        }
    }

    @Override
    public Type comparison(Type type) {
        if(type instanceof CharType) {
            return new IntType(this.getLine(), this.getColumn());
        }else if(type instanceof ErrorType) {
            return type;
        }else{
            return new ErrorType(this.getLine(), this.getColumn(), "Cannot perform comparison with " + type.toString());}
    }

    @Override
    public int numberOfBytes()
    {
        return 1;
    }

    @Override
    public Type cast(Type t)
    {
        if(t instanceof IntType || t instanceof DoubleType || t instanceof CharType) return t;
        if(t instanceof ErrorType) return t;
        return new ErrorType(this.getLine(), this.getColumn(), "Cannot perform a cast of char to " + t.toString());
    }

    @Override
    public String suffix()
    {
        return "b";
    }

    @Override
    public boolean canBeWritable()
    {
        return true;
    }

    @Override
    public boolean isBuiltInType() { return true; }

    @Override
    public boolean canBeAssignable(Type t)
    {
        if(t instanceof CharType) return true;
        return false;
    }
}
