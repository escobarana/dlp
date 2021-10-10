package ast.types;

import ast.expressions.Expression;
import visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class StructType extends AbstractType {

    List<StructField> parameters;

    public StructType(int line, int column, List<StructField> parameters) {
        super(line, column);
        this.parameters = new ArrayList<>(checkFields(parameters));
    }

    // Lab06
    private List<StructField> checkFields(List<StructField> fields){
        List<String> uniqueNames = new ArrayList<>();
        fields.forEach(sf -> {
                if(uniqueNames.contains(sf.getName())) {
                    new ErrorType(sf.getLine(), sf.getColumn(), "Duplicate StructField name " + sf.getName());
                }else {
                    uniqueNames.add(sf.getName());
                }
        });
        return fields;
    }

    public List<StructField> getFields(){
        return this.parameters;
    }

    public StructField getField(String member) {
        StructField returned = null;
        for (StructField rf: parameters) {
            if(rf.getName().equals(member))
                returned = rf;
        }
        return returned;
    }

    @Override // record((integer x int)x(character x char))
    public String toString() {
        return "record(" + this.parameters.toString() + ")";//"StructType{" + "parameters=" + parameters + '}';
    }

    @Override
    public <TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    // lab09
    @Override
    public Type dotOp(String str, Expression dotOp){
        for(StructField sf : getFields())
        {
            if(sf.getName().equals(str)) {
                return sf.getType();
            }
        }

        return new ErrorType(dotOp.getLine(), dotOp.getColumn(), "Cannot access this field: " + str);
    }

    // Lab10
    @Override
    public int numberOfBytes() {
        int sum = 0;

        for(StructField rf: this.parameters){
            sum += rf.getType().numberOfBytes();
        }

        return sum;
    }

}
