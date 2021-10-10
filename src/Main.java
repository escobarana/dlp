import codeGeneration.*;
import errorhandler.ErrorHandler;
import parser.*;
import ast.Program;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;

import org.antlr.v4.runtime.*;
import semantic.IdentificationVisitor;
import semantic.TypeCheckingVisitor;

public class Main {

	public static void main(String... args) throws Exception {
		if (args.length<1) {
			System.err.println("Please, pass me the input file.");
			return;
		}

		// create a lexer that feeds off of input CharStream
		CharStream input = CharStreams.fromFileName(args[0]);
		CmmLexer lexer = new CmmLexer(input);

		// create a parser that feeds off the tokens buffer
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CmmParser parser = new CmmParser(tokens);
		// parser.program();

			//  --- From lab05 on --- //
		Program ast = parser.program().ast;

			// lab08 (SymbolTable)
		IdentificationVisitor iv = new IdentificationVisitor();
		ast.accept(iv,null);

			// lab07 (LValue)
		TypeCheckingVisitor tcv = new TypeCheckingVisitor();
		ast.accept(tcv,null);

			// lab10 (Offset and numberOfBytes)
		OffsetVisitor offset_visitor = new OffsetVisitor();
		ast.accept(offset_visitor, null);

			// lab06 - Error Handler
		if(ErrorHandler.getInstance().anyErrors()){
			ErrorHandler.getInstance().showErrors(System.err);
		}else{
			//* The AST is shown
//			IntrospectorModel model = new IntrospectorModel("Program", ast);
//			new IntrospectorTree("Introspector", model);

			/*IntrospectorModel model = new IntrospectorModel("Program", "Hello");
			new IntrospectorTree("Introspector", model);*/

				// labs 11, 12, 13
			CodeGeneration cg = new CodeGeneration(args[0], args[1]);
			ValueCGVisitor vv = new ValueCGVisitor(cg);
			AddressCGVisitor av = new AddressCGVisitor(cg);
			av.setValueVisitor(vv);
			vv.setAddressVisitor(av);
			ExecuteCGVisitor ev = new ExecuteCGVisitor(cg, av, vv);

			ast.accept(ev, null);
		}

	}

}