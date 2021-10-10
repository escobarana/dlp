package ast.types;

import ast.expressions.Expression;
import ast.expressions.UnaryMinus;
import ast.expressions.UnaryNot;
import errorhandler.ErrorHandler;
import visitor.IVisitor;

import java.util.List;

public class ErrorType extends AbstractType {

    private String message;
    private int line;
    private int column;

    public ErrorType(int line, int column, String message) {
        super(line, column);
        this.line = line;
        this.column = column;
        this.message = message;
        ErrorHandler.getInstance().addError(this); // register the error
    }

    public int getLine(){
        return this.line;
    }

    public int getColumn(){
        return this.column;
    }

    @Override
    public String toString(){
        return "{Error type: " + "[ line: " + getLine() + " column: " + this.getColumn() + "; "  + this.message + " ]}";
    }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    @Override
    public Type logic(Type t, Expression logical)
    {
        return this;
    }

    @Override
    public Type arithmetic(Type t, Expression arithmetic)
    {
        return this;
    }

    @Override
    public Type squareBrackets(Type t, Expression indexing)
    {
        return this;
    }

    @Override
    public Type uNot(UnaryNot unaryNot)
    {
        return this;
    }

    @Override
    public Type uMinus(UnaryMinus unaryMinus)
    {
        return this;
    }

    @Override
    public Type comparison(Type type)
    {
        return this;
    }

    @Override
    public Type cast(Type castType)
    {
        return this;
    }

    @Override
    public Type dotOp(String member, Expression access)
    {
        return this;
    }

    @Override
    public Type parenthesis(List<Expression> arguments, Expression invocation)
    {
        return this;
    }


}
