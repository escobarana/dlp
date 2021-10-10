package ast.statements;

import ast.expressions.Expression;
import visitor.IVisitor;

public class Read extends AbstractStatement  {
    private Expression expression;

    public Read(int line, int column, Expression expression) {
        super(line, column);
        this.expression = expression;
    }

    public Expression getExpression() {

        return expression;
    }

    @Override
    public String toString() {
        return "Read{" + "; expression= " + expression + ", l-value= " + expression.getLValue() + " }";
    }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
