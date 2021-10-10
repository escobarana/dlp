package ast.statements;

import ast.expressions.Expression;
import ast.types.StructField;
import ast.types.StructType;
import visitor.IVisitor;

import java.util.List;

public class Assignment extends AbstractStatement {

    private Expression variable;
    private Expression value;

    public Assignment(int line, int column, Expression variable, Expression value) {
        super(line, column);
        this.variable = variable;
        this.value = value;
    }

    public Expression getValue(){
        return this.value;
    }

    public Expression getVariable(){
        return this.variable;
    }

    @Override
    public String toString() {
        return "Assignment{" + "variable=" + this.variable + ", value=" + value + '}';
    }


    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

}
