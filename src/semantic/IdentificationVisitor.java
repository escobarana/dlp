package semantic;

import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.Variable;
import ast.types.ErrorType;
import symboltable.SymbolTable;
import visitor.AbstractVisitor;

// Link all Variable nodes to their Definitions
public class IdentificationVisitor extends AbstractVisitor<Void, Void> {

    // Use SymbolTable class as a private field
    private SymbolTable st = new SymbolTable();

    @Override
    public Void visit(FunctionDefinition f_def, Void params){

        if(!st.insert(f_def)) {
            new ErrorType(f_def.getLine(), f_def.getColumn(), f_def.getName() + " already defined in this scope");
        }
        st.set(); // set a new scope
        f_def.getType().accept(this, params);
        f_def.getBody().forEach(statement -> statement.accept(this, params)); // Traverse the childs
        st.reset(); // Reset
        return null;
    }

    @Override
    public Void visit(Variable variable, Void params) {
        variable.setDefinition(st.find(variable.getName()));
        if(variable.getDefinition() == null) {
            new ErrorType(variable.getLine(), variable.getColumn(), variable.getName() + " has not been defined yet.");
        }
        return null;
    }

    @Override
    public Void visit(VarDefinition v_def, Void params) {
        v_def.getType().accept(this, params);

        if(!st.insert(v_def)) {
            new ErrorType(v_def.getLine(), v_def.getColumn(), v_def.getName() + " already defined in this scope.");
        }

        return null;
    }
}
