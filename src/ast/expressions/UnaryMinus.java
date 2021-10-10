package ast.expressions;

import visitor.IVisitor;

public class UnaryMinus extends AbstractExpression{
    private Expression expression;

    public UnaryMinus(int line, int column, Expression expression) {
        super(line, column);
        this.expression = expression;
    }

    public Expression getExpression() {

        return expression;
    }


    @Override
    public String toString() {
        return "UnaryMinus{" + "; expression= " + expression + ", l-value= " + expression.getLValue() + " }";
    }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}