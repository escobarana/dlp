package ast.expressions;

import visitor.IVisitor;

public class ArrayIndexing extends AbstractExpression{
    private Expression e1;
    private Expression e2;

    public ArrayIndexing(int line, int column, Expression e1, Expression exp2) {
        super(line, column);
        this.e1 = e1;
        this.e2 = exp2;
    }

    public Expression getE1() {

        return e1;
    }
    public Expression getE2() {

        return e2;
    }

    @Override
    public String toString() {
        return "ArrayIndexing{" + "expr1=" + e1 + ", l-value=" + e1.getLValue() + "; expr2=" + e2 + ", l-value=" + e2.getLValue() + '}';
    }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}