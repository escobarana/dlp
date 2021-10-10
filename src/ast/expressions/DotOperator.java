package ast.expressions;

import visitor.IVisitor;

public class DotOperator extends AbstractExpression{
    private String field;
    private Expression expression;

    public DotOperator(int line, int column, String field, Expression expression) {
        super(line, column);
        this.field = field;
        this.expression=expression;
    }

    public String getField() {
        return field;
    }

    public Expression getExpression() {
        return this.expression;
    }

    @Override
    public String toString() {
        return "DotOperator{" + "field='" + field + '\'' + "; expression= " + expression + ", l-value= " + expression.getLValue() + " }";

    }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
