package ast.definitions;

import ast.AbstractASTNode;
import ast.types.Type;

public abstract class AbstractDefinition extends AbstractASTNode implements Definition {
    private String name;
    private Type type;

    public AbstractDefinition(int line, int column, Type type, String name) {
        super(line, column);
        this.type = type;
        this.name = name;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type t) {
        this.type = t;
    }

    @Override
    public String getName() {
        return name;
    }


}
