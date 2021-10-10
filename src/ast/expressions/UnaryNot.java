package ast.expressions;

import visitor.IVisitor;

public class UnaryNot extends AbstractExpression{
    private Expression expression;

    public UnaryNot(int line, int column, Expression expression) {
        super(line, column);
        this.expression = expression;
    }

    public Expression getExpression() {

        return expression;
    }


    @Override
    public String toString() {
        return "UnaryNot{" + "; expression= " + expression + ", l-value= " + expression.getLValue() + " }";
    }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
