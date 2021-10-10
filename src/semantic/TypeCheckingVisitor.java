package semantic;

import ast.definitions.FunctionDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import visitor.AbstractVisitor;

import java.util.List;

/*
    EXAM: TypeCheckingVisitor. New semantic rules added:

*/

// Check types compatibility, enforces the type rules os the language.
// If a type rule is not enforced, a type error is produced.
public class TypeCheckingVisitor extends AbstractVisitor<Void, Void> {

    @Override
    public Void visit(FunctionDefinition f_def, Void params) {

        super.visit(f_def, params);

        return null;
    }

    @Override
    public Void visit(Arithmetic arith, Void params) {

        super.visit(arith, params);

        arith.setLValue(false);

        arith.setType(arith.getE1().getType().arithmetic(arith.getE2().getType(), arith));
        if (arith.getType()==null){
            new ErrorType(arith.getLine(), arith.getColumn(), "No compatible types");
        }

        return null;
    }

    /*
        ArrayIndexing: expression1 -> expression2 expression3
            expression1.type = expression2.type.squareBrackets(expression3.type)
     */
    @Override
    public Void visit(ArrayIndexing a_index, Void params) {

        super.visit(a_index, params);

        a_index.setLValue(true);

        a_index.setType(a_index.getE1().getType().squareBrackets(a_index.getE2().getType(), a_index));

        if (a_index.getType()==null){
            a_index.setType(new ErrorType(a_index.getE1().getLine(),
                    a_index.getE1().getColumn(),"Not possible to access the array"));
        }

        return null;
    }

    @Override
    public Void visit(UnaryMinus u_minus, Void params) {

        super.visit(u_minus, params);

        u_minus.setLValue(false);

        u_minus.setType(u_minus.getExpression().getType().uMinus(u_minus));

        return null;
    }

    @Override
    public Void visit(UnaryNot u_not, Void params) {

        super.visit(u_not, params);

        u_not.setLValue(false);

        u_not.setType(u_not.getExpression().getType().uNot(u_not));

        return null;
    }

    @Override
    public Void visit(Variable var, Void params) {

        super.visit(var, params);

        var.setLValue(true);

        if (var.getDefinition() == (null)) { // .Equals does not work here
            var.setType(new ErrorType(var.getLine(), var.getColumn(), "Variable " + var.getName() + " has no definition."));
        } else {
            var.setType(var.getDefinition().getType());
        }

        return null;
    }

    @Override
    public Void visit(Assignment ass, Void params){

        super.visit(ass, params);

        if(!ass.getVariable().getLValue())
            new ErrorType(ass.getLine(), ass.getColumn(), "LValue required.");

        if (!(ass.getVariable().getType() instanceof ErrorType) && !(ass.getValue().getType() instanceof ErrorType)) {
            if (!ass.getVariable().getType().canBeAssignable(ass.getValue().getType())) {
                new ErrorType(ass.getLine(), ass.getColumn(), "Both parts of the assignment must be equal, trying to assign a "+ass.getValue().getType()+" to a "+ass.getVariable().getType());
            }
        }

        return null;
    }
    /*
         FuncInvocation: expression1 -> expression2 expression3 *
             Type[] argTypes = expression3*.stream().map(exp -> exp.type).toArray(Type[]::new);
             expression1.type = expression2.type.invocation(argTypes)
     */

    @Override
    public Void visit(FunctionInvocation f_inv, Void params) {

        super.visit(f_inv, params);

        f_inv.setLValue(false);

        f_inv.setType(f_inv.getName().getType().parenthesis(f_inv.getExpressions(), f_inv));

        if (f_inv.getType() == null){ // .Equals does not work here
            f_inv.setType(new ErrorType(f_inv.getName().getLine(),
                    f_inv.getName().getColumn(), "Invalid Parameters"));
        }

        return null;
    }

    @Override
    public Void visit(IfElse if_else, Void params) {

        super.visit(if_else, null);
        if(!if_else.getCondition().getType().isBoolean())
            new ErrorType(if_else.getLine(), if_else.getColumn(), "Condition of an 'if' must be boolean");

        return null;
    }

    @Override
    public Void visit(Read read, Void params){

        super.visit(read, params);
        if(!read.getExpression().isLValue())
            new ErrorType(read.getLine(), read.getColumn(), "LValue required.");

        return null;
    }

    @Override
    public Void visit(Cast cast, Void params) {

        super.visit(cast, params);

        cast.setLValue(false);

        cast.setType(cast.getExpression().getType().cast(cast.getType()));

        if (cast.getType()==null){
            cast.setType(new ErrorType(cast.getExpression().getLine(), cast.getExpression().getColumn(),
                    "No compatible types to be casted"));
        }

        return null;
    }

    @Override
    public Void visit(DotOperator dot_op, Void params) {

        super.visit(dot_op, params);

        dot_op.setLValue(true);

        dot_op.setType(dot_op.getExpression().getType().dotOp(dot_op.getField(), dot_op));

        if (dot_op.getType() == null){ // .Equals does not work here
            dot_op.setType(new ErrorType(dot_op.getExpression().getLine(), dot_op.getExpression().getColumn(),
                    "Field does not exist '" + dot_op.getField() + "' in the struct"));
        }

        return null;
    }

    /*
         WhileStmt: statement1 -> expression statement2*
            expression.type.isBoolean();
     */
    @Override
    public Void visit(While wh, Void params) {

        super.visit(wh, params);

        if(!wh.getCondition().getType().isBoolean())
            new ErrorType(wh.getLine(), wh.getColumn(), "Condition of a 'while' must be boolean");


        return null;
    }

    @Override
    public Void visit(Write write, Void params){

        super.visit(write, params);

        if (!(write.getExpression().getType() instanceof ErrorType))
            if(!write.getExpression().getType().canBeWritable())
                new ErrorType(write.getLine(), write.getColumn(), "Expression of a 'write' must be writable");

        return null;
    }

    @Override
    public Void visit(CharLiteral c_lit, Void params) {

        c_lit.setLValue(false);

        c_lit.setType(new CharType(c_lit.getLine(), c_lit.getColumn()));

        return null;
    }

    /*
         Comparison: expression1 -> expression2 (>|<|==|>=|<=) expression3)
            expression1.type = expression2.type.comparison(expression3.type)
     */

    @Override
    public Void visit(Comparison comp, Void params) {

        super.visit(comp, params);

        comp.setLValue(false);

        comp.setType(comp.getE1().getType().comparison(comp.getE2().getType()));

        return null;
    }

    @Override
    public Void visit(IntLiteral i_lit, Void params) {

        i_lit.setLValue(false);

        i_lit.setType(new IntType(i_lit.getLine(),i_lit.getColumn()));

        return null;
    }

    @Override
    public Void visit(Logic logic, Void params) {

        super.visit(logic, params);

        logic.setLValue(false);

        logic.setType(logic.getE1().getType().logic(logic.getE2().getType(), logic));

        return null;
    }

    @Override
    public Void visit(RealLiteral r_lit, Void params) {

        r_lit.setLValue(false);

        r_lit.setType(new DoubleType(r_lit.getLine(),r_lit.getColumn()));

        return null;
    }
}
