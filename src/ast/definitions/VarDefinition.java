package ast.definitions;

import ast.expressions.Expression;
import ast.statements.Statement;
import ast.types.Type;
import visitor.IVisitor;

public class VarDefinition extends AbstractDefinition implements Statement {

    private int offset;
    private int scope;
    private Type type;
    // private Expression expression; // Exam


    public VarDefinition(int line, int column, Type type, String name) {

        super(line, column, type, name);
        this.type = type;
    }

    // Exam - Allow initialization of local variables upon definition (only possible for built-in types)
/*    public VarDefinition(int line, int column, Type t, String name, Expression exp) {

        super(line, column, t, name);
        this.type = t;
        this.expression = exp;
    }*/

    /*public Expression getExpression() {
        return this.expression;
    }*/

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public void setType(Type t) {
        this.type = t;
    }

    // Lab10
    public int getOffset() {
        return offset;
    }

    // Lab10
    public void setOffset(int offset) { this.offset=offset; }


    @Override
    public String toString() {
        return " " + this.type.toString();//"VarDefinition{" + "offset=" + offset + '}';
    }

    // Lab07
    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    // Lab08
    @Override
    public void setScope(int scope) {
        this.scope = scope;
    }

    // Lab08
    @Override
    public int getScope() {
        return this.scope;
    }
}
