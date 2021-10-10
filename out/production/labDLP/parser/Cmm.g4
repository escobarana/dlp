grammar Cmm;	

@header{
    import ast.*;
    import ast.definitions.*;
    import ast.expressions.*;
    import ast.statements.*;
    import ast.types.*;
}

/** Self anotations from class

- Type definition (i.e., typedef) is not supported.

- Same thing:
    { $ast = new Arithmetic($e1.start.getLine(), $e1.start.getCharPositionInLine() +1, $e1.ast, $e2.ast, $op.text); }
    { $ast = new Arithmetic($e1.ast.getLine(), $e1.ast.getColumn(), ... ); } - other possibility

- '?' means it's optional
- Before '*' write parenthesis is mandatory
- To write 'Epsilon': | (**empty**)
- 'if' '(' e1=expression ')' b1=block ('else' b2=block { $elseBody=$b2.ast; })?
             { $ast = new If($e1.ast, $b1.ast, $elseBody); } // - Another option (more academic) - Need to have a local variable as a List<Statement> elseBody = new ArrayList<Statement>()

*/

// A program is a sequence of variable and function definitions
program returns [Program ast]:
            definitions main
            {$ast = new Program( $definitions.start.getLine(), $definitions.start.getCharPositionInLine()+1, $definitions.ast, $main.ast);}
            EOF
            ;

// Type followed by a non-empty enumeration of comma-separated identifiers.
// Must end with the ";" character.
variable_definitions returns [List<Definition> ast = new ArrayList<Definition>()]:
                          t=type ID {$ast.add(new VarDefinition($t.start.getLine(), $t.start.getCharPositionInLine() +1, $t.ast, $ID.text));}
                              (',' ID {$ast.add(new VarDefinition($t.start.getLine(), $t.start.getCharPositionInLine() +1, $t.ast, $ID.text));})*
                              ';'
                          ;

// The bodies of functions are sequences of variable definitions followed by sequences of statements.
// Both must end with the ";" character.
definitions returns [List<Definition> ast = new ArrayList<Definition>()]:
                (v=variable_definitions {$ast.addAll($v.ast);}
                |f=function_definitions {$ast.addAll($f.ast);} )*
                ;

// Defined specifying the return type, the function identifier and a list of coma-separated parameters
// between ( and ). The return type and parameter types must be built-in. Function body goes between { and }.
// Must end with the ";" character. - Go to block
function_definitions returns[List<Definition> ast = new ArrayList<Definition>()]:
                        returnType=parameter_type ID '(' parameters ')' body=block
                        {$ast.add(new FunctionDefinition($returnType.start.getLine(), $returnType.start.getCharPositionInLine()+1,
                           $ID.text,new FunctionType($returnType.start.getLine(), $returnType.start.getCharPositionInLine()+1,
                           $parameters.ast, $returnType.ast), $body.ast));}
                        ;

// Built-in types are "int", "double" and "char"
built_in_type returns[Type ast]:
                  i1='int' {$ast = new IntType($i1.getLine(), $i1.getCharPositionInLine()+1);}
                  | d1='double' {$ast = new DoubleType($d1.getLine(), $d1.getCharPositionInLine()+1);}
                  | c1='char' {$ast = new CharType($c1.getLine(), $c1.getCharPositionInLine()+1);}
                  ;

type returns[Type ast]:
          p=built_in_type {$ast = $p.ast;}
        | t1=type '[' i=INT_CONSTANT ']' // Specifying the size of the array with an integer constant
            {$ast = new ArrayType($i.getLine() , $i.getCharPositionInLine()+1, LexerHelper.lexemeToInt($i.text), $t1.ast );}
        | 'struct' r=record_block // Added for specifying record types
            {$ast = new StructType($r.start.getLine(), $r.start.getCharPositionInLine()+1, $r.ast);}
        ;

// Records have no type identifier (struct)
record_block returns [List<StructField> ast = new ArrayList<StructField>()]:
                '{' (r=record_field {$ast.addAll($r.ast);} )*'}'
                ;

// Fields are declared as var_definitions between { and }. (I get the {} from the record_block)
record_field returns[List<StructField> ast = new ArrayList<StructField>()]:
                t=type ID {$ast.add(new StructField($t.start.getLine(), $t.start.getCharPositionInLine() +1, $t.ast, $ID.text));}
                (',' ID {$ast.add(new StructField($t.start.getLine(), $t.start.getCharPositionInLine() +1, $t.ast, $ID.text));})*
                ';'
                 ;

// Returns void and receives no parameters.
main returns[FunctionDefinition ast]:
        returnType='void' m='main' '(' parameters ')'body=block
        {$ast=new FunctionDefinition($returnType.getLine(), $returnType.getCharPositionInLine()+1, $m.text,
                new FunctionType($returnType.getLine(),$returnType.getCharPositionInLine()+1,
                $parameters.ast,new VoidType($m.getLine(), $m.getCharPositionInLine()+1)), $body.ast);}
        ;

// Must end with the ";" character (sequence of statements within the body).
statement returns [List<Statement> ast = new ArrayList<Statement>()]:
            'write' e1=expression // "write" keyword followed by a non-empty comma-separated list of expressions.
                {$ast.add(new Write( $e1.start.getLine(), $e1.start.getCharPositionInLine()+1, $e1.ast));}
                (',' e2=expression {$ast.add((new Write( $e1.start.getLine(), $e1.start.getCharPositionInLine()+1, $e2.ast)));})*
                ';'
            | 'read' e1=expression // "read" keyword followed by a non-empty comma-separated list of expressions.
                {$ast.add(new Read( $e1.start.getLine(), $e1.start.getCharPositionInLine()+1, $e1.ast));}
                (',' e2=expression {$ast.add((new Read( $e1.start.getLine(), $e1.start.getCharPositionInLine()+1, $e2.ast)));})*
                ';'
            | 'return' e1=expression // return <expression> statement - expression MANDATORY
                {$ast.add(new Return($e1.start.getLine(), $e1.start.getCharPositionInLine()+1, $e1.ast));}
                ';'
            | e1=expression '=' e2=expression // Assignments are built with two expressions separated by the "=" operator.
                            {$ast.add(new Assignment($e1.start.getLine(), $e1.start.getCharPositionInLine()+1, $e1.ast, $e2.ast));}
                            ';'
            | 'if' '(' e1=expression ')' b1=block
                {$ast.add(new IfElse($e1.start.getLine(), $e1.start.getCharPositionInLine() +1, $e1.ast, $b1.ast));}
            | 'if' '(' e1=expression ')' b1=block 'else'  b2=block
                {$ast.add(new IfElse($e1.start.getLine(), $e1.start.getCharPositionInLine() +1, $e1.ast,$b1.ast, $b2.ast));}
            | 'while' '(' e1=expression ')' b1=block
                {$ast.add(new While($e1.start.getLine(), $e1.start.getCharPositionInLine() +1, $e1.ast, $b1.ast));}
            | f=function_invocation
                {$ast.add($f.ast);}
                ';'
            | t=type ID
                 {$ast.add(new VarDefinition($t.start.getLine(), $t.start.getCharPositionInLine()+1, $t.ast, $ID.text));}
                 (',' ID {$ast.add(new VarDefinition($t.start.getLine(), $t.start.getCharPositionInLine()+1, $t.ast, $ID.text));})*
                  ';'
//            | bt=built_in_type ID '=' e1=expression // exam variable definitions
//                {
//                    $ast.add(new VarDefinition($bt.start.getLine(), $bt.start.getCharPositionInLine()+1, $bt.ast, $ID.text));
//                    $ast.add(new Assignment($ID.getLine(), $ID.getCharPositionInLine() +1, new Variable($ID.getLine(), $ID.getCharPositionInLine() +1, $ID.text), $e1.ast));
//                }
//                ';' // Initialization of local variables upon definition. Only possible for built-in types
            ;

// Descending order of precedence
expression returns [Expression ast]:
             '(' e1=expression ')' {$ast= $e1.ast; }
            | e1=expression'[' e2=expression ']'
                 {$ast=new ArrayIndexing($e1.start.getLine(), $e1.start.getCharPositionInLine() +1, $e1.ast, $e2.ast);}
            | e1=expression '.' ID {$ast=new DotOperator($ID.getLine(), $ID.getCharPositionInLine()+1, $ID.text, $e1.ast); }
            |'(' p=built_in_type ')' expression
                 {$ast= new Cast($expression.start.getLine(), $expression.start.getCharPositionInLine() +1, $expression.ast, $p.ast); }
            | '-' e1=expression
                {$ast=new UnaryMinus($e1.start.getLine(), $e1.start.getCharPositionInLine() +1, $e1.ast); }
            | e1=expression op=('*'|'/'|'%') e2=expression
                {$ast=new Arithmetic($e1.start.getLine(), $e1.start.getCharPositionInLine() +1, $e1.ast, $e2.ast, $op.text); }
            | e1=expression op=('+'|'-') e2=expression
                {$ast=new Arithmetic($e1.start.getLine(), $e1.start.getCharPositionInLine() +1, $e1.ast, $e2.ast, $op.text); }
            | e1=expression op=('>' | '>=' | '<' | '<=' | '!=' | '==') e2=expression
                {$ast = new Comparison($e1.start.getLine(), $e1.start.getCharPositionInLine() +1, $e1.ast, $e2.ast, $op.text); }
            | '!' e1=expression
                {$ast= new UnaryNot($e1.start.getLine(), $e1.start.getCharPositionInLine() +1, $e1.ast); }
            | e1=expression op=('&&' | '||') e2=expression
                {$ast= new Logic($e1.start.getLine(), $e1.start.getCharPositionInLine() +1, $e1.ast, $e2.ast, $op.text); }
            | f=function_invocation {$ast= $f.ast;}
            | ID {$ast= new Variable($ID.getLine(), $ID.getCharPositionInLine() +1, $ID.text);}
            | i=INT_CONSTANT {$ast=new IntLiteral($i.getLine(), $i.getCharPositionInLine()+1, LexerHelper.lexemeToInt($i.text));}
            | c=CHAR_CONSTANT {$ast=new CharLiteral($c.getLine(), $c.getCharPositionInLine()+1, LexerHelper.lexemeToChar($c.text));}
            | r=REAL_CONSTANT {$ast= new RealLiteral($r.getLine(), $r.getCharPositionInLine() +1, LexerHelper.lexemeToReal($r.text));}
            ;

// A function invocation may be an expression or a statement. A procedure invocation is always a statement.
function_invocation returns [FunctionInvocation ast]:
                        ID '('{$ast = new FunctionInvocation($ID.getLine(), $ID.getCharPositionInLine()+1,  new Variable($ID.getLine(), $ID.getCharPositionInLine()+1, $ID.text));}')'
                        | // Epsilon
                        ID '(' args {$ast = new FunctionInvocation($ID.getLine(), $ID.getCharPositionInLine()+1,  new Variable($ID.getLine(), $ID.getCharPositionInLine()+1, $ID.text), $args.ast);}')'
                        ;

// The arguments are a sequence of expressions: e1, e2, e3 (...)
args returns [List<Expression> ast = new ArrayList<Expression>()]:
        (e1=expression {$ast.add($e1.ast);} (',' e2=expression {$ast.add($e2.ast);} )*)
        ;

// Parameters types can be void, int, double or char
parameter_type returns[Type ast]:
                v='void' {$ast = new VoidType($v.getLine(), $v.getCharPositionInLine()+1);}
                | p=built_in_type {$ast = $p.ast;}
                ;

// Must end with the ";" character. - Go to statement
block returns[List<Statement> ast = new ArrayList<Statement>()]:
        '{'(statement  {$ast.addAll($statement.ast);})*'}'
         | statement  {$ast.addAll($statement.ast);}
         ;

// A parameter example: int a
parameter returns [VarDefinition ast]:
              t=built_in_type ID
              {$ast=new VarDefinition($t.start.getLine(), $t.start.getCharPositionInLine()+1, $t.ast, $ID.text);}
              ;

// Parameters example: int a, int b
parameters returns [List<VarDefinition>ast = new ArrayList<VarDefinition>()]:
                p1=parameter {$ast.add($p1.ast);} (',' p2=parameter {$ast.add($p2.ast);})*
                | // Epsilon - main function receives no parameters
                ;

fragment
LETTER: [a-zA-Z]
      ;

fragment
DIGIT:[0-9]
     ;

fragment
REAL_CONSTANT_FLOAT: INT_CONSTANT'.' DIGIT*
                   |'.'INT_CONSTANT DIGIT*
                   ;

fragment
REAL_CONSTANT_MANTISSA: (REAL_CONSTANT_FLOAT|INT_CONSTANT) ('E'|'e') ('-')? INT_CONSTANT // '?' means optional => [+|-] is optional
                      ;

fragment
CHAR_CONSTANT_ASCII: '\'\\' INT_CONSTANT '\'' // Represents ASCII code, '$' means the end of a line and '^' means the beginning of a line
                   ;

fragment
CHAR_CONSTANT_SPECIAL: '\'' '\\' 'n' '\'' // Represents newline (\n)
                     | '\'' '\\' 't' '\'' // Represents tab (\t)
                     ;

INT_CONSTANT: [1-9]DIGIT*
            | '0'
            ;

ID:(LETTER|'_')(LETTER|DIGIT|'_')*
  ;

REAL_CONSTANT: REAL_CONSTANT_FLOAT
             | REAL_CONSTANT_MANTISSA
             ;

ONELINE_COMMENT: '//'.*?('\n'|EOF) ->skip // ('\n'|'\r'|EOF) before
                   ;

MULTIPLELINE_COMMENT: '/*'.*?'*/' ->skip // '?' means optional => Optionally can be someting between '/*' '*/'
                     ; // Dot matches line breaks

CHAR_CONSTANT:'\'' . '\''
             | CHAR_CONSTANT_ASCII
             | CHAR_CONSTANT_SPECIAL
             ;

BLANKS: [ \t\n\r] ->skip
      ;
