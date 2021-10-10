package ast.expressions;

import visitor.IVisitor;

public class IntLiteral extends AbstractExpression {
    private int value;

    public IntLiteral(int line, int column, int value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public String toString() {
        return "IntLiteral{" + "value=" + value + '}';
    }

    public int getValue() {

        return this.value;
    }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
