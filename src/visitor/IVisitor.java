package visitor;

import ast.Program;
import ast.definitions.*;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;

public interface IVisitor<TP, TR> {

    // definitions
    TR visit(FunctionDefinition f_def, TP params);
    TR visit(VarDefinition v_def, TP params);

    // expressions
    TR visit(Arithmetic arith, TP params);
    TR visit(ArrayIndexing a_index, TP params);
    TR visit(Cast cast, TP params);
    TR visit(CharLiteral c_lit, TP params);
    TR visit(Comparison comp, TP params);
    TR visit(DotOperator dot_op, TP params);
    TR visit(IntLiteral i_lit, TP params);
    TR visit(Logic logic, TP params);
    TR visit(RealLiteral r_lit, TP params);
    TR visit(UnaryMinus u_minus, TP params);
    TR visit(UnaryNot u_not, TP params);
    TR visit(Variable var, TP params);

    // statements
    TR visit(Assignment ass, TP params);
    TR visit(FunctionInvocation f_inv, TP params);
    TR visit(IfElse if_else, TP params);
    TR visit(Read read, TP params);
    TR visit(Return ret, TP params);
    TR visit(While wh, TP params);
    TR visit(Write write, TP params);

    // types
    TR visit(ArrayType a_type, TP params);
    TR visit(CharType c_type, TP params);
    TR visit(DoubleType d_type, TP params);
    TR visit(ErrorType e_type, TP params);
    TR visit(FunctionType f_type, TP params);
    TR visit(IntType i_type, TP params);
    TR visit(StructType s_type, TP params);
    TR visit(StructField s_field, TP params);
    TR visit(VoidType v_type, TP params);

    // program
    TR visit(Program prog, TP params);
}
