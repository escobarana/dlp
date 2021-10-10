package ast.definitions;

import ast.ASTNode;
import ast.types.Type;
import visitor.AbstractVisitor;

public interface Definition extends ASTNode {

    Type getType();
    void setType(Type t); // Exam
    String getName();
    void setScope(int scope);
    int getScope();
}
