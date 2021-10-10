package ast;

import ast.definitions.Definition;
import ast.definitions.FunctionDefinition;
import visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class Program implements ASTNode {
    private List<Definition> definitions;
    private int line;
    private int column;

    public Program(int line, int column, List<Definition> definitions, FunctionDefinition main){
        this.line=line;
        this.column=column;
        this.definitions = new ArrayList<>(definitions);
        this.definitions.add(main);
    }

    public List<Definition> getDefinitions(){
        return this.definitions;
    }

    @Override
    public int getLine() {

        return line;
    }

    @Override
    public int getColumn() {

        return column;
    }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    @Override
    public String toString() {
        return "Program{" + "definitions=" + definitions + ", line=" + line + ", column=" + column + '}';
    }
}
