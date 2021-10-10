package ast.types;

import ast.expressions.Expression;
import visitor.IVisitor;

public class ArrayType extends AbstractType {

    private int size;
    private Type returnType;

    public ArrayType(int line, int column, int size, Type retType) {
        super(line, column);
        this.size = size;
        this.returnType = retType;
        recursion();
    }

    private void recursion(){
        if(returnType.getClass().equals(ArrayType.class)){

            int auxSize = this.size;
            this.size = ((ArrayType)returnType).getSize();
            ((ArrayType)returnType).setSize(auxSize);
            ((ArrayType)returnType).recursion();

        }
    }

    public Type getReturnType() {

        return returnType;
    }

    public int getSize() {

        return size;
    }

    public void setSize(int size) {

        this.size = size;
    }

    @Override
    public String toString() {
        return "[" + this.size +  "," + this.returnType + "]";//"ArrayType{ " + "size= " + size + ", returnType= " + returnType + '}';
    }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    // lab09
    @Override
    public Type squareBrackets(Type type, Expression array_index) {
        if(type instanceof IntType) {
            return this.getReturnType();
        }else if(type instanceof ErrorType) {
            return type;
        }else {
            return new ErrorType(array_index.getLine(), array_index.getColumn(), "Cannot index array with " + type.toString());
        }
    }

    @Override
    public int numberOfBytes()
    {
        return this.size*this.returnType.numberOfBytes();
    }
}
