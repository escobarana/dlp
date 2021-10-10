package ast.expressions;

import ast.types.Type;
import visitor.IVisitor;

public class Cast extends AbstractExpression{
    private Expression expression;
    private Type type;

    public Cast(int line, int column, Expression expression, Type type) {
        super(line, column);
        this.expression = expression;
        this.type = type;
    }

    public Expression getExpression() {

        return expression;
    }

    public Type getType() {

        return type;
    }

    @Override
    public String toString() {
        return "Cast{" + "expr=" + expression + ", l-value= " + expression.getLValue() + ", type=" + type + '}';
    }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}