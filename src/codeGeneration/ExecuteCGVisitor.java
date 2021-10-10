package codeGeneration;

import ast.Program;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.statements.*;
import ast.types.*;

import java.util.List;

// Code to execute one statement, definition or program
public class ExecuteCGVisitor  extends AbstractCGVisitor<int[],Void> {
    // Statements and Definitions

    private CodeGeneration cg;
    private AddressCGVisitor av;
    private ValueCGVisitor vv;

    public ExecuteCGVisitor(CodeGeneration cg, AddressCGVisitor av, ValueCGVisitor vv)
    {
        this.cg = cg;
        this.av = av;
        this.vv = vv;
    }

    private int getBytesToReturn(FunctionDefinition funcDef) {
        if(funcDef.getType().getReturnType() instanceof VoidType) {
            return 0;
        } else {
            return funcDef.getType().getReturnType().numberOfBytes();
        }
    }

    private int getLocalBytes(List<Statement> body) {
        int bytes = 0;
        for(Statement statement: body)
        {
            if(statement instanceof VarDefinition)
                bytes += ((VarDefinition) statement).getType().numberOfBytes();
        }
        return bytes;
    }

    private int getParamBytes(FunctionDefinition funcDef) {
        int bytes = 0;

        for (VarDefinition param: funcDef.getType().getParameters()){
            bytes += param.getType().numberOfBytes();
        }

        return bytes;
    }

    public Void visit(FunctionType funcType, int[] params) {
        cg.write("\t' * Parameters");
        funcType.getParameters().forEach(vd -> vd.accept(this, params));

        return null;
    }

    // Lab 11
    /*
    execute[[FunctionDefinition funcDefinition -> statement*]]()=
        int bytesParams = 0;
        int bytesLocals = 0;
        int bytesLocals = ?;
        statement*.foreach(statement-> execute[[statement]](bytesLocals, bytesParams, bytesReturn))
        if(bytesReturn==0) //no return
            <ret > bytesReturn, bytesLocals, bytesParams
     */

    public Void visit(FunctionDefinition functionDefinition, int[] params) {
        cg.write(" ");
        cg.line(functionDefinition.getLine());
        cg.write(" ");
        cg.write(" "+functionDefinition.getName()+":");
        // cg.printComment("\t' * Parameters");

        functionDefinition.getType().accept(this, params); //parameters

        cg.write("\t' * Local variables");

        functionDefinition.getBody().forEach(statement-> {
            if(statement instanceof VarDefinition)
                statement.accept(this, params);
        });

        int byteParams = getParamBytes(functionDefinition);
        int byteLocals = getLocalBytes(functionDefinition.getBody());

        cg.enter(byteLocals);

        int bytesReturn = getBytesToReturn(functionDefinition);

        functionDefinition.getBody().forEach(statement-> {
            if(!(statement instanceof VarDefinition))
            {
                cg.write(" ");
                cg.line(statement.getLine());
                statement.accept(this, new int[]{byteLocals,byteParams,bytesReturn});
                cg.write("");
            }
        });

        if(bytesReturn==0)
            cg.ret(bytesReturn,byteLocals,byteParams);

        return null;
    }



    // Lab 11
    /*
    execute[[Program: program -> definition* ]]()=
        for (Definition definition: definition*)
            if (definition instanceof VarDefinition)
                execute[[definition]]()
        for (Definition definition: definition*)
            if (definition instanceof FunctionDefinition)
                execute[[definition]]()
     */
    public Void visit(Program program, int[] params) {
        cg.write(" ");
        cg.source();

        cg.write(" ");
        cg.write("\t' * Global variables");
        program.getDefinitions().forEach(def -> {
            if(def instanceof VarDefinition) // Global variables
                def.accept(this, params);
        });

        cg.write("");
        cg.write("' Invocation to the main function");
        cg.call("main");
        cg.halt();
        cg.write("");

        program.getDefinitions().forEach(def -> { // function definitions
            if(def instanceof FunctionDefinition)
               def.accept(this, params);
        });

        cg.close(); // close PrintStream

        return null;
    }

    // Lab 11
    /*
    execute[[VarDefinition: varDefinition -> type ID]] =
	        <' * > type.toString() ID.text <( offset > varDefinition.offset <)>

     */
    public Void visit(VarDefinition vDef, int[] params)
    {
        cg.write("\t' * " + vDef.getType().toString() + " " + vDef.getName() + " (offset "+vDef.getOffset() + ")");
        return null;
    }

    // Lab 11
    /*
    execute[[Assignment: statement -> expression1 expression2]]() =
        address[[expression1]]
        value[[expression2]]
        <store> expression1.type.suffix()
     */
    public Void visit(Assignment assignment, int[] params) {
        cg.write("\t ' * Assignment");
        assignment.getVariable().accept(this.av, null);
        assignment.getValue().accept(this.vv, null);
        cg.store(assignment.getVariable().getType()); // store prints the suffix
        return null;
    }

    /*
    // Lab 11
    execute[[Read: statement -> expression]] =
        address[[expression]]
        <in> expression.type.suffix()
        <store> expression.type.suffix()
    */
    public Void visit(Read read, int[] params) {
        cg.write("\t' * Read");
        read.getExpression().accept(this.av, null);
        cg.in(read.getExpression().getType());
        cg.store(read.getExpression().getType());
        return null;
    }

    /*
    // Lab 11
    execute[[Write: statement-> expression]] =
        value[[expression]]
        <out> expression.type.suffix()
    */
    public Void visit(Write write, int[] params) {
        cg.write("\t' * Write");
        write.getExpression().accept(this.vv, null);
        cg.out(write.getExpression().getType());
        return null;
    }

    // Lab 12
    /*
    execute[[While: statement1 -> expression statement2*]] =
        int labelNumber = cg.getLabels(2)
        <label> labelNumber <:>
        value[[expression]]
        <jz label> labelNumber+1
        statement2*.forEach(statement-> execute[[statement]]
        <jmp label> labelNumber
        <label> labelNumber+1 <:>
     */
    public Void visit(While whileStatement, int[] params) {
        cg.write("\t ' * While statement");
        cg.write(" ");
        cg.line(whileStatement.getLine());
        cg.write(" ");

        int labelNumber = cg.getLabels(2);
        cg.label(labelNumber);
        whileStatement.getCondition().accept(this.vv, null);
        cg.jz(labelNumber+1);
        cg.write("\t' * Body of the while statement");
        whileStatement.getBody().forEach(statement-> {
            cg.line(statement.getLine());
            statement.accept(this, params);
        });
        cg.jmp(labelNumber);
        cg.label(labelNumber+1);
        return null;
    }

    // Lab 12
    /*
    execute[[IfElse: statement1 -> expression statement2+ statement3*]] =
        int labelNumber = cg.getLabels(2);
        value[[expression]]
        <jz label> labelNumber
        statement2+.forEach(statement -> execute[[statement]])
        <jmp label> labelNumber+1
        <label>labelNumber<:>
        statement3*.forEach(statement -> execute[[statement]])
        <label>labelNumber+1<:>
     */
    public Void visit(IfElse ifelse, int[] params) {
        cg.write("\t' * If statement");
        cg.write(" ");
        cg.line(ifelse.getLine());
        cg.write(" ");

        int labelNumber = cg.getLabels(2);
        ifelse.getCondition().accept(this.vv, null);
        cg.jz(labelNumber);
        cg.write("\t' * Body of the If statement");
        ifelse.getIfBody().forEach(statement -> {
            cg.line(statement.getLine());
            statement.accept(this, params);
        });
        cg.jmp(labelNumber+1);
        cg.label(labelNumber);
        cg.write("\t' * Body of the Else statement");
        ifelse.getElseBody().forEach(statement-> {
            cg.line(statement.getLine());
            statement.accept(this, params);
        });
        cg.label(labelNumber+1);
        return null;
    }


    // Lab 13
    /*
        execute[[Return: statement -> expression1: expression2 ]](funcDefinition)=
            value[[expression1]]
            <ret>expression2.type.numberOfBytes() <,>
            funcDefinition.bytesLocalsSum<,>
            funcDefinition.type.bytesParamsSum
     */
    public Void visit(Return returnStatement, int[] params) {
        cg.write("\t' * Return");
        int[] toReturn = params;
        returnStatement.getExpression().accept(this.vv, null);
        cg.ret(toReturn[2], toReturn[0], toReturn[1]);

        return null;
    }

    // Lab 13
    /*
    execute[[FunctionInvocation: statement -> expression1 expression2*]] =
        expression2*.forEach(exp -> value[[expression2]])
        <call > expression1.name
        if(!(expression1.type.returnType instanceOf VoidType))
            <pop> expression1.type.returnType.suffix()

     */
    public Void visit(FunctionInvocation invocation, int[] params) {
        invocation.getExpressions().forEach(param -> param.accept(this.vv, null));
        cg.call(invocation.getName().getName());
        if(!(((FunctionType)invocation.getName().getType()).getReturnType() instanceof VoidType))
            cg.pop(((FunctionType) invocation.getName().getType()).getReturnType());
        return null;
    }

}
