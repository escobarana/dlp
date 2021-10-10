package ast.expressions;

import ast.AbstractASTNode;
import ast.types.Type;

public abstract class AbstractExpression extends AbstractASTNode implements Expression {

    private boolean lValue; // lab07
    private Type type; // lab09

    public AbstractExpression(int line, int column) {
        super(line, column);
    }

    @Override
    public boolean isLValue() {
        return lValue;
    }

    @Override
    public void setLValue(boolean lValue) {
        this.lValue = lValue;
    }

    @Override
    public boolean getLValue() {
        return this.lValue;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public void setType(Type type){
        this.type = type;
    }
}
