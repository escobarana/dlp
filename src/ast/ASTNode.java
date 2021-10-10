package ast;

import visitor.IVisitor;
/*
	EXAM: Lexical and syntax specifications
    New production(s) added to the abstract grammar (lexical specification):

 */
public interface ASTNode {

	int getLine();

	int getColumn();

	<TP, TR> TR accept(IVisitor<TP, TR> visitor, TP param);
}
