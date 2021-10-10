package ast.statements;

import ast.expressions.Expression;
import ast.expressions.Variable;
import ast.types.Type;
import visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class FunctionInvocation extends AbstractStatement implements Expression {
    private List<Expression> expressions;
    private Variable name;
    private Type type;
    private boolean lValue;

    public FunctionInvocation(int line, int column, Variable name, List<Expression> expressions) {
        super(line, column);
        this.name = name;
        this.expressions = new ArrayList<>(expressions);
    }

    public FunctionInvocation(int line, int column, Variable name) {
        super(line, column);
        this.name = name;
        this.expressions = new ArrayList<>();
    }

    public Variable getName(){
        return this.name;
    }

    public List<Expression> getExpressions(){
        return this.expressions;
    }

    @Override
    public String toString() {
        return "FunctionInvocation{" + "expressions=" + expressions + ", name='" + name.getName() + '\'' + '}';
    }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    @Override
    public boolean isLValue() {
        return lValue;
    }

    @Override
    public void setLValue(boolean lValue) {
        this.lValue = lValue;
    }

    @Override
    public boolean getLValue() {
        return this.lValue;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }
}
