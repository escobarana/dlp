package ast.expressions;

import visitor.IVisitor;

public class Logic extends AbstractExpression {
    private String operator;
    private Expression e1;
    private Expression e2;


    public Logic(int line, int column, Expression e1, Expression e2, String operator) {
        super(line, column);
        this.e1 = e1;
        this.e2 = e2;
        this.operator = operator;
    }

    public Expression getE1() {

        return e1;
    }

    public Expression getE2() {

        return e2;
    }

    public String getOperator() {

        return operator;
    }

    @Override
    public String toString() {
        return "Logic{" + "operator='" + operator + '\'' + "; expr1= " + e1 + ", l-value= " + e1.getLValue() + "; expr2= " + e2 + ", l-value= " + e2.getLValue() + " }";
    }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
