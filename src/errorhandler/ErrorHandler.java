package errorhandler;

import ast.types.ErrorType;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

// Singleton - constructor must register itself upon construction in ErrorHandler
public final class ErrorHandler {

    private static ErrorHandler instance;
    private List<ErrorType> errors;

    private ErrorHandler(){
        this.errors = new ArrayList<>();
    }

    public static ErrorHandler getInstance(){
        if(instance == null) {
            instance = new ErrorHandler();
        }
        return instance;
    }

    public boolean anyErrors(){
        return !this.errors.isEmpty();
    }

    public void showErrors(PrintStream s){
        this.errors.forEach(errorType -> s.println(errorType.toString()));
    }

    public void addError(ErrorType error){
        this.errors.add(error);
    }
}
