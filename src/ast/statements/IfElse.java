package ast.statements;

import ast.expressions.Expression;
import visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class IfElse extends AbstractStatement  {
    private Expression condition;
    private List<Statement> ifBody;
    private List<Statement> elseBody;


    public IfElse(int line, int column, Expression condition, List<Statement> ifBody) {
        super(line, column);
        this.condition = condition;
        this.ifBody = new ArrayList<>(ifBody);
        this.elseBody = new ArrayList<>();
    }

    public IfElse(int line, int column, Expression condition, List<Statement> ifBody, List<Statement> elseBody) {
        super(line, column);
        this.condition = condition;
        this.ifBody=new ArrayList<>(ifBody);
        this.elseBody=new ArrayList<>(elseBody);
    }

    public Expression getCondition() {

        return condition;
    }

    public List<Statement> getIfBody() {

        return ifBody;
    }

    public List<Statement> getElseBody() {

        return elseBody;
    }

    @Override
    public String toString() {
        String toReturn = "If Else{" + "condition=" + condition + '\n';
        toReturn +=", ifBody=";
        for (Statement c : ifBody) {
            toReturn +=  c.toString();
        }
        toReturn +=", elseBody=";
        for (Statement c : elseBody) {
            toReturn +=  c.toString();
        }
        toReturn +='}';
        return toReturn;
    }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR>  visitor, TP param) {
        return visitor.visit(this, param);
    }
}
