package ast.types;

import visitor.IVisitor;

public class VoidType extends AbstractType {

    public VoidType(int line, int column) {
        super(line, column);
    }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
