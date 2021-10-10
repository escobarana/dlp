package ast.types;

import ast.ASTNode;
import visitor.IVisitor;

public class StructField implements ASTNode {
    private int line;
    private int column;
    private String name;
    private Type type;
    private int offset;

    public StructField(int line, int column, Type type, String name) {
        this.line=line;
        this.column=column;
        this.type=type;
        this.name=name;
    }

    public Type getType() { return type; }


    public String getName() { return name; }

    @Override
    public int getLine() { return line; }

    @Override
    public int getColumn() { return column; }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    @Override
    public String toString() { //(integer x int)x(character x char)
        return "(" + this.name + " x " + this.type + ")";//"StructField{" + "line=" + line + ", column=" + column + ", name='" + name + '\'' + ", type=" + type + '}';
    }

    // Lab10
    public int getOffset() { return offset; }

    // Lab10
    public void setOffset(int offset) { this.offset=offset; }
}
