package ast.expressions;

import ast.definitions.Definition;
import visitor.IVisitor;

public class Variable extends AbstractExpression {

    private String name;
    private Definition definition;

    public Variable(int line, int column, String name) {
        super(line, column);
        this.name = name;
    }

    public String getName() {

        return this.name;
    }

    public Definition getDefinition()
    {
        return this.definition;
    }

    public void setDefinition(Definition definition)
    {
        this.definition = definition;
    }

    @Override
    public String toString() {
        return "Variable{" + "name='" + name + '\'' + '}';
    }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}