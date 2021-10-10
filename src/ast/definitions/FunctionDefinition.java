package ast.definitions;

import ast.statements.Statement;
import ast.types.FunctionType;
import ast.types.Type;
import visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class FunctionDefinition extends AbstractDefinition {

    private List<Statement> body;
    private int scope;
    private FunctionType type;

    public FunctionDefinition(int line, int column, String name, FunctionType type, List<Statement> body) {
        super(line, column, type, name);
        this.body = new ArrayList<>(body);
        this.type = type;
    }

    public List<Statement> getBody() {
        return body;
    }

    @Override
    public FunctionType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        String toReturn = "FunctionDefinition{ " + "variables: ";
        toReturn += ", body=";
        for(Statement s: body){ toReturn += s.toString()+ '\n'; };
        return toReturn;
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
