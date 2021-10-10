package ast.statements;

import ast.expressions.Expression;
import visitor.IVisitor;

public class Write extends AbstractStatement  {
    private Expression expression;

    public Write(int line, int column, Expression expression) {
        super(line, column);
        this.expression = expression;
    }

    public Expression getExpression() {

        return expression;
    }

    @Override
    public String toString() {
        return "Write{" + "; expression= " + expression + ", l-value= " + expression.getLValue() + " }";
    }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
