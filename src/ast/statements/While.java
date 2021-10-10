package ast.statements;

import ast.expressions.Expression;
import visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class While extends AbstractStatement  {
    private Expression condition;
    private List<Statement> body;

    public While(int line, int column, Expression condition, List<Statement> body) {
        super(line, column);

        this.condition = condition;
        this.body = new ArrayList<>(body);
    }

    public Expression getCondition() {

        return condition;
    }

    public List<Statement> getBody() {

        return body;
    }

    @Override
    public String toString() {
        String toReturn = "\"While{\"" + "condition=" + condition + '\n';
        toReturn +=", body=";
        for (Statement c : body) {
            toReturn +=  c.toString();
        }

        toReturn +='}';
        return toReturn;
    }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
