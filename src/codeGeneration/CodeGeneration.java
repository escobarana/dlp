package codeGeneration;

import ast.types.Type;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

// Generate target code with the same semantics as the source program
public class CodeGeneration {

    private String input;
    private String output;
    private PrintStream ps;
    private int labels = 1;

    public CodeGeneration(String input, String output) throws FileNotFoundException {
        this.input = input;
        this.output = output;
        this.ps = new PrintStream(new FileOutputStream((this.output)));
    }

    public int getLabels(int howMany) {
        int temp = this.labels;
        this.labels += howMany;
        return temp;
    }

    // --------------------------------- PUSH --------------------------------------------
    public void pushBP() {
        ps.println("\tpush\tbp");
    }

    public void pushA(int offset) {
        ps.println("\tpusha\t" + offset);
    }

    public void push(Type type, int value) {
        ps.println("\tpush" + type.suffix() + "\t" + value);
    }

    public void push(Type type, double value) { ps.println("\tpush" + type.suffix() + "\t" + value); }

    public void pushI(int numberOfBytes) {
        ps.println("\tpushi\t" + numberOfBytes);
    }

    // --------------------------------- POP --------------------------------------------
    public void pop(Type type) {
        ps.println("\tpop" + type.suffix());
    }

    // --------------------------------- ADD --------------------------------------------
    public void addi() {
        ps.println("\taddi");
    }

    // --------------------------------- STORE --------------------------------------------
    public void store(Type type) {
        ps.println("\tstore" + type.suffix());
    }

    // --------------------------------- LOAD --------------------------------------------
    public void load(Type type) {
        ps.println("\tload" + type.suffix());
    }

    // --------------------------------- CAST --------------------------------------------
    public void conversion(Type type2, Type type1) {  // convertTo(...) from class slides
        switch (type2.suffix()) {
            case "b": // char
                if (type1.suffix().equals("i")) // integer
                {
                    ps.println("\tb2i"); // pops char pushes int
                } else if (type1.suffix().equals("f")) // float
                { // two steps
                    ps.println("\tb2i"); // step 1: pops char pushes int
                    ps.println("\ti2f"); // step 2: pops int pushes real
                }
                break;
            case "i": // integer
                if (type1.suffix().equals("b")) // char
                {
                    ps.println("\ti2b"); // pops int pushes char
                } else if (type1.suffix().equals("f")) // float
                {
                    ps.println("\ti2f"); // pops int pushes real
                }
                break;
            case "f":// float
                if (type1.suffix().equals("i")) // integer
                {
                    ps.println("\tf2i"); // pops real pushes int
                } else if (type1.suffix().equals("b")) // char
                { // two steps
                    ps.println("\tf2i"); // step 1: pops real pushes int
                    ps.println("\ti2b"); // step 2: pops int pushes char
                }
                break;
        }
    }

    // --------------------------------- ARITHMETIC --------------------------------------------
    public void arithmetic(String operator, Type type) {
        switch (operator)
        {
            case "+": // Addition
                ps.println("\tadd" + type.suffix());
                break;
            case "-": // Subtraction
                ps.println("\tsub" + type.suffix());
                break;
            case "*": // Multiplication
                ps.println("\tmul" + type.suffix());
                break;
            case "/": // Division
                ps.println("\tdiv" + type.suffix());
                break;
            case "%": // Modulus
                ps.println("\tmod" + type.suffix());
                break;
        }
    }

    public void muli() {
        ps.println("\tmuli");
    } // integer multiplication

    public void not() {
        ps.println("\tnot");
    }

    public void sub(Type type) {
        ps.print("\tsub" + type.suffix());
    }

    // --------------------------------- COMPARISON --------------------------------------------
    public void comparison(String operand, Type type) {
        switch (operand) {
            case ">": // Greater than
                ps.println("\tgt" + type.suffix());
                break;
            case "<": // Lower than
                ps.println("\tlt" + type.suffix());
                break;
            case "==": // Equals
                ps.println("\teq" + type.suffix());
                break;
            case "<=": // Lower than or equals
                ps.println("\tle" + type.suffix());
                break;
            case ">=": // Greater than or equals
                ps.println("\tge" + type.suffix());
                break;
            case "!=": // Not equals [pops 1 operand]
                ps.println("\tne" + type.suffix());
                break;
        }
    }

    // --------------------------------- LOGICAL --------------------------------------------
    public void logic(String operand) {
        if (operand.equals("&&")) {
            ps.println("\tand\t");
        } else if (operand.equals("||")) {
            ps.println("\tor\t");
        }
    }

    // --------------------------------- COMMENT --------------------------------------------
    public void write(String s) {
        ps.println(s);
    }

    // --------------------------------- INPUT --------------------------------------------
    // Reads a value from the keyboard and pushes its binary representation onto the stack
    public void in(Type type) {
        ps.println("\tin" + type.suffix());
    }

    // --------------------------------- OUTPUT --------------------------------------------
    // Pops one value off the stack and shows it in the console
    public void out(Type type) {
        ps.println("\tout" + type.suffix());
    }

    public void enter(int byteLocals) {
        ps.println("\tenter\t" + byteLocals);
    }

    public void line(int line) {
        ps.println("#line\t" + line);
    }

    public void ret(int bytesReturn, int byteLocals, int byteParams) {
        ps.println("\tret\t" + bytesReturn + ", " + byteLocals + ", " + byteParams);
    }

    public void source() {
        ps.println("#source\t\"" + input + "\"");
    }

    public void close() {
        ps.flush();
        ps.close();
    }

    public void call(String main) {
        ps.println("\tcall\t" + main);
    }

    public void halt() {
        ps.println("halt");
    }

    // [Jump instructions modify the value of the IP register]
    // --------------------------------- CONDITIONAL JUMP --------------------------------------------
    public void jz(int labelNumber) {
        ps.println("\tjz\tlabel" + labelNumber);
    }

    public void jnz(int labelNumber) {
        ps.println("\tjnz\tlabel" + labelNumber);
    }

    // --------------------------------- UNCONDITIONAL JUMP --------------------------------------------
    public void jmp(int i) {
        ps.println("\tjmp\tlabel" + i);
    }

    // --------------------------------- LABEL --------------------------------------------
    public void label(int labelNumber) {
        ps.println(" label" + labelNumber + ":");
    }
}
