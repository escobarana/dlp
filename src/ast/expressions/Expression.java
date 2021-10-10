package ast.expressions;

import ast.ASTNode;
import ast.types.Type;

public interface Expression extends ASTNode {

    // Lab 07
    boolean isLValue();
    void setLValue(boolean lValue);
    boolean getLValue();

    // lab09
    Type getType();
    void setType(Type type);
}
