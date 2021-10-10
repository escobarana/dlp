package ast.types;

import ast.definitions.VarDefinition;
import ast.expressions.Expression;
import visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class FunctionType extends AbstractType {
    List<VarDefinition> parameters;
    Type returnType;

    public FunctionType(int line, int column, List<VarDefinition> parameters, Type returnType) {
        super(line, column);
        this.parameters = new ArrayList<>(parameters);
        this.returnType = returnType;
    }

    public List<VarDefinition> getParameters() {

        return this.parameters;
    }

    public Type getReturnType() {

        return this.returnType;
    }

    @Override
    public String toString() {
        return "FunctionType{" + "parameters=" + parameters + ", returnType=" + returnType + '}';
    }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    // Lab09
    @Override
    public Type parenthesis(List<Expression> args, Expression f_inv) {
        if(getParameters().size()!=args.size())
            return new ErrorType(f_inv.getLine(),f_inv.getColumn(), "Number of arguments doesn´t match, expected: "+ getParameters().size() +" and were given: "+args.size());
        for(int i = 0; i < args.size(); i++)
        {
            if(!getParameters().get(i).getType().canBeAssignable(args.get(i).getType()))
                return new ErrorType(f_inv.getLine(), f_inv.getColumn(), "Type of parameter doesn´t match, expected: "+getParameters().get(i).getType()+" and was given: "+args.get(i).getType());
        }
        return this.getReturnType();
    }
}
