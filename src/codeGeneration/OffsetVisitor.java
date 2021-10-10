package codeGeneration;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;;
import ast.types.FunctionType;
import ast.types.StructField;
import ast.types.StructType;
import visitor.AbstractVisitor;

// Lab10
public class OffsetVisitor extends AbstractVisitor<Void, Void>{
/*
    chars - 1 byte
    ints - 2 bytes (boolean type included)
    reals (floats) - 4 bytes
    mem. addresses - 2 bytes
    record - addition of the sizes of the field types
 */
    int bytes_global = 0; // offset global variables
    int bytes_local = 0; // offset local variables

    /**
     * FunctionDefinition: func_def -> statement*
     * for(func_def v:func_def*){localBytesSum+=v.type.numberOfBytes(); v.offset=-localBytesSum}
     *
     * @param func_def
     * @param param
     * @return
     */
    @Override
    public Void visit(FunctionDefinition func_def, Void param){

        func_def.getType().accept(this, param);
        func_def.getBody().forEach(st -> { st.accept(this, param); });

        // reset local offset
        this.bytes_local = 0;

        return null;
    }

    /** ## check
     * VarDefinition: var_def -> type ID
     *  if(var_def.scope==0){var_def.setOffset(globalOffset); globalOffset+=var_def.getType().numberOfBytes()}
     *  else{localOffset+=var_def.getType().numberBytes(); var_def.setOffset(-localOffset);}
     *
     * @param var_def
     * @param param
     * @return
     */
    @Override
    public Void visit(VarDefinition var_def, Void param){

        var_def.getType().accept(this, param);

        if(var_def.getScope() == 0){ // global variable
            var_def.setOffset(this.bytes_global); // positive
            this.bytes_global += var_def.getType().numberOfBytes();
        }else{ //Local variable (BP - sum(previous variables size (including itself))
            this.bytes_local -= var_def.getType().numberOfBytes();
            var_def.setOffset(this.bytes_local); // negative
        }

        return null;
    }

    /**
     * FunctionType: func_type -> type var_def*
     * for(int i =var_def*.size()-1;i>=0;i--){VarDefinition v=var_def*.get(i); v.offset=4+paramBytesSum; paramBytesSum+=v.type.numberOfBytes();}
     *
     * @param func_type
     * @param param
     * @return
     */
    @Override
    public Void visit(FunctionType func_type, Void param){

        int bytesParams = 4; // params
        // BP + 4 + sum(size of the right hand side)
        func_type.getReturnType().accept(this, param);

        for(int i = func_type.getParameters().size()-1; i >= 0; i--){ // from right to left
            func_type.getParameters().get(i).setOffset(bytesParams);
            bytesParams += func_type.getParameters().get(i).getType().numberOfBytes();
        }
        func_type.getReturnType().accept(this, param);

        return null;
    }

    /**
     * StructType -> struct_type -> struct_field*
     * foreach(st -> st.setOffset(paramsByteSum); paramsByteSum+=st.getType().numberOfBytes();)
     *
     * @param struct_type
     * @param param
     * @return
     */
    @Override
    public Void visit(StructType struct_type, Void param){

        int fieldsByteSum = 0;

        for(StructField sf : struct_type.getFields())
        {
            sf.setOffset(fieldsByteSum); // positive
            fieldsByteSum += sf.getType().numberOfBytes();
            sf.accept(this, param);
        }
        return null;
    }
}
