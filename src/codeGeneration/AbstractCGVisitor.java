package codeGeneration;

import ast.Program;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import visitor.IVisitor;

public class AbstractCGVisitor<TP, TR> implements IVisitor<TP, TR> {
    // *** all classes in codeGeneration package must extend this class

    @Override
    public TR visit(FunctionDefinition f_def, TP params) {

        throw new IllegalStateException("Function definition is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(VarDefinition v_def, TP params) {

        throw new IllegalStateException("Variable definition is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(Arithmetic arith, TP params) {

        throw new IllegalStateException("Arithmetic is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(ArrayIndexing a_index, TP params) {

        throw new IllegalStateException("Indexing is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(Cast cast, TP params) {

        throw new IllegalStateException("Cast is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(CharLiteral c_lit, TP params) {

        throw new IllegalStateException("Character literal is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(Comparison comp, TP params) {

        throw new IllegalStateException("Comparison is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(DotOperator dot_op, TP params) {

        throw new IllegalStateException("Dot operator (Access) is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(IntLiteral i_lit, TP params) {

        throw new IllegalStateException("Integer literal is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(Logic logic, TP params) {

        throw new IllegalStateException("Logical is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(RealLiteral r_lit, TP params) {

        throw new IllegalStateException("Real literal (Double) is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(UnaryMinus u_minus, TP params) {

        throw new IllegalStateException("Unary minus is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(UnaryNot u_not, TP params) {

        throw new IllegalStateException("Unary not is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(Variable var, TP params) {

        throw new IllegalStateException("Variable is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(Assignment ass, TP params) {

        throw new IllegalStateException("Assignment is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(FunctionInvocation f_inv, TP params) {

        throw new IllegalStateException("Function invocation is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(IfElse if_else, TP params) {

        throw new IllegalStateException("If-else is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(Read read, TP params) {

        throw new IllegalStateException("Read is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(Return ret, TP params) {

        throw new IllegalStateException("Return is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(While wh, TP params) {

        throw new IllegalStateException("While is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(Write write, TP params) {

        throw new IllegalStateException("Write is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(ArrayType a_type, TP params) {

        throw new IllegalStateException("Array type is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(CharType c_type, TP params) {

        throw new IllegalStateException("Character type is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(DoubleType d_type, TP params) {

        throw new IllegalStateException("Double type (real type) is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(ErrorType e_type, TP params) {

        throw new IllegalStateException("Error type is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(FunctionType f_type, TP params) {

        throw new IllegalStateException("Function type is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(IntType i_type, TP params) {

        throw new IllegalStateException("Integer type is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(StructType s_type, TP params) {

        throw new IllegalStateException("Struct type (record type) is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(StructField s_field, TP params) {

        throw new IllegalStateException("Struct field (record field) is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(VoidType v_type, TP params) {

        throw new IllegalStateException("Void type is not supported by " + this.getClass().getName());
    }

    @Override
    public TR visit(Program prog, TP params) {

        throw new IllegalStateException("Program is not supported by " + this.getClass().getName());
    }
}
