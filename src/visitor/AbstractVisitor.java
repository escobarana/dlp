package visitor;

import ast.Program;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;

// Traverse trees
public class AbstractVisitor<TP, TR> implements IVisitor<TP, TR>{

    @Override
    public TR visit(FunctionDefinition f_def, TP params) {

        f_def.getType().accept(this, params);
        f_def.getBody().forEach(statement -> statement.accept(this, params));

        return null;
    }

    @Override
    public TR visit(VarDefinition v_def, TP params) {

        v_def.getType().accept(this, params);

        return null;
    }

    @Override
    public TR visit(Arithmetic arith, TP params) {

        arith.getE1().accept(this, params);
        arith.getE2().accept(this, params);


        return null;
    }

    @Override
    public TR visit(ArrayIndexing a_index, TP params) {

        a_index.getE1().accept(this, params);
        a_index.getE2().accept(this, params);

        return null;
    }

    @Override
    public TR visit(Cast cast, TP params) {

        cast.getType().accept(this, params);
        cast.getExpression().accept(this, params);

        return null;
    }

    @Override
    public TR visit(CharLiteral c_lit, TP params) { return null; }

    @Override
    public TR visit(Comparison comp, TP params) {

        comp.getE1().accept(this, params);
        comp.getE2().accept(this, params);

        return null;
    }

    @Override
    public TR visit(DotOperator dot_op, TP params) {

        dot_op.getExpression().accept(this, params);


        return null;
    }

    @Override
    public TR visit(IntLiteral i_lit, TP params) { return null; }

    @Override
    public TR visit(Logic logic, TP params) {

        logic.getE1().accept(this, params);
        logic.getE2().accept(this, params);

        return null;
    }

    @Override
    public TR visit(RealLiteral r_lit, TP params) { return null; }

    @Override
    public TR visit(UnaryMinus u_minus, TP params) {

        u_minus.getExpression().accept(this, params);

        return null;
    }

    @Override
    public TR visit(UnaryNot u_not, TP params) {

        u_not.getExpression().accept(this, params);

        return null;
    }

    @Override
    public TR visit(Variable var, TP params) { return null; }

    @Override
    public TR visit(Assignment ass, TP params) {

        ass.getVariable().accept(this, params);
        ass.getValue().accept(this, params);

        return null;
    }

    @Override
    public TR visit(FunctionInvocation f_inv, TP params) {

        f_inv.getName().accept(this, params);
        f_inv.getExpressions().forEach(exp -> exp.accept(this, params));

        return null;
    }

    @Override
    public TR visit(IfElse if_else, TP params) {

        if_else.getCondition().accept(this, params);
        if_else.getIfBody().forEach(st -> st.accept(this, params));
        if_else.getElseBody().forEach(st -> st.accept(this, params));

        return null;
    }

    @Override
    public TR visit(Read read, TP params) {

        read.getExpression().accept(this, params);

        return null;
    }

    @Override
    public TR visit(Return ret, TP params) {

        ret.getExpression().accept(this, params);

        return null;
    }

    @Override
    public TR visit(While wh, TP params) {

        wh.getCondition().accept(this, params);
        wh.getBody().forEach(st -> st.accept(this, params));

        return null;
    }

    @Override
    public TR visit(Write write, TP params) {

        write.getExpression().accept(this, params);

        return null;
    }

    @Override
    public TR visit(ArrayType a_type, TP params) {

        a_type.getReturnType().accept(this, params);
        return null;
    }

    @Override
    public TR visit(CharType c_type, TP params) {
        return null;
    }

    @Override
    public TR visit(DoubleType d_type, TP params) {
        return null;
    }

    @Override
    public TR visit(ErrorType e_type, TP params) {
        return null;
    }

    @Override
    public TR visit(FunctionType f_type, TP params) {

        f_type.getReturnType().accept(this, params);
        f_type.getParameters().forEach(p -> p.accept(this, params));
        return null;
    }

    @Override
    public TR visit(IntType i_type, TP params) {
        return null;
    }

    @Override
    public TR visit(StructType s_type, TP params) {

        s_type.getFields().forEach(f -> f.accept(this, params));
        return null;
    }

    @Override
    public TR visit(StructField s_field, TP params) {

        s_field.getType().accept(this, params);
        return null;
    }

    @Override
    public TR visit(VoidType v_type, TP params) {
        return null;
    }

    @Override
    public TR visit(Program prog, TP params) {

        // prog.accept(this, null); -> INFINITE LOOP, CALLS ITSELF
        /*for(Definition d : prog.getDefinitions())
        {
            d.accept(this, null);
        } Is the same as: */
        prog.getDefinitions().forEach(definition -> definition.accept(this, params));

        return null;
    }
}
