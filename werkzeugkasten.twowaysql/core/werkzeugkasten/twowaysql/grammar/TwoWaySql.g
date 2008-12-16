grammar TwoWaySql;

options {
	output = AST;
	ASTLabelType = CommonTree;
	superClass = Parser;
}

@parser::header {
package werkzeugkasten.twowaysql.grammar;

import werkzeugkasten.twowaysql.error.*;
import werkzeugkasten.twowaysql.error.mapper.*;
import werkzeugkasten.twowaysql.tree.*;

}
@parser::rulecatch {
catch (RecognitionException ex) {
	reportError(ex);
	recover(input,ex);
	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
}
}
@parser::members {
protected ProblemCoordinator coordinator;
public void setProblemCoordinator(ProblemCoordinator pc) {
	this.coordinator = pc;
}
public ProblemCoordinator getProblemCoordinator() {
	return this.coordinator;
}
public void push(ExceptionMapper em) {
	this.coordinator.push(em);
}
public void pop() {
	this.coordinator.pop();
}

public void reportError(RecognitionException ex) {
	if ( state.errorRecovery ) {
		return;
	}
	state.syntaxErrors++;
	state.errorRecovery = true;
	this.coordinator.report(ex);
}

protected static final ExceptionMapper EM_TXT = new TxtExceptionMapper();
protected static final ExceptionMapper EM_EXPRESSION = new ExpressionExceptionMapper();
protected static final ExceptionMapper EM_BLOCKCOMMENT = new BlockCommentExceptionMapper();
protected static final ExceptionMapper EM_LINECOMMENT = new LineCommentExceptionMapper();
protected static final ExceptionMapper EM_BEGINCOMMENT = new BeginCommentExceptionMapper();
protected static final ExceptionMapper EM_IFCOMMENT = new IfCommentExceptionMapper();
protected static final ExceptionMapper EM_ELSEIFNODE = new ElseIfNodeExceptionMapper();
protected static final ExceptionMapper EM_ELSEIFBLOCKCOMMENT = new ElseIfBlockCommentExceptionMapper();
protected static final ExceptionMapper EM_ELSEIFLINECOMMENT = new ElseIfLineCommentExceptionMapper();
protected static final ExceptionMapper EM_ELSENODE = new ElseNodeExceptionMapper();
protected static final ExceptionMapper EM_ELSECOMMENT = new ElseCommentExceptionMapper();
protected static final ExceptionMapper EM_ENDCOMMENT = new EndCommentExceptionMapper();
protected static final ExceptionMapper EM_BINDCOMMENT = new BindCommentExceptionMapper();
protected static final ExceptionMapper EM_BINDINGNAME = new BindingNameExceptionMapper();
protected static final ExceptionMapper EM_INBIND = new InBindExceptionMapper();
protected static final ExceptionMapper EM_INBINDSKIPPED = new InBindSkippedExceptionMapper();

}

@lexer::header {
package werkzeugkasten.twowaysql.grammar;
}

@lexer::members {
boolean inComment = false;
boolean inLineComment = false;
}

twowaySQL returns[TwoWayQuery query]
	@init {
		$query = new TwoWayQuery();
	}
	@after {
		$query.update(retval);
		$query.freeze();
	}
	: nodelist EOF {
		$query.setChildren($nodelist.list);
	}
	;
	finally {
		coordinator.raise();
	}

nodelist returns[ArrayList<QueryNode> list]
	// caller rule handles exceptions
	@init{
		$list = new ArrayList<QueryNode>();
	}
	:
	(comment {$list.add($comment.node);}
	| inbind {$list.add($inbind.node);}
	| txt {$list.add($txt.node);}
	)+
	;

charactors
	// caller rule handles exceptions
	:
	(IDENT | QUOTED | SYMBOLS | SYM_BIND | SYM_C | SYM_LP | SYM_RP)+ 
	;

txt returns[TxtNode node]
	@init {
		push(EM_TXT);
		$node = new TxtNode();
	}
	@after {
		$node.update(retval);
		$node.freeze();
	}
	:
	charactors
	;
	finally { pop(); }

// $<comment

comment returns[QueryNode node]:
	begincomment {$node = $begincomment.node;}
	| ifcomment {$node = $ifcomment.node;}
	| bindcomment {$node = $bindcomment.node;}
	| txtcomment {$node = $txtcomment.node;}
	;

txtcomment returns[TxtNode node]
	: blockcomment {$node = $blockcomment.node;}
	| linecomment {$node = $linecomment.node;}
	;	

blockcomment returns[TxtNode node]
	@init {
		push(EM_BLOCKCOMMENT);
		$node = new TxtNode();
	}
	@after {
		$node.update(retval);
		$node.freeze();
	}
	:
	C_ST charactors C_ED
	;
	finally { pop(); }

linecomment returns[TxtNode node]
	@init {
		push(EM_LINECOMMENT);
		$node = new TxtNode();
	}
	@after {
		$node.update(retval);
		$node.freeze();
	}
	:
	C_LN_ST charactors C_LN_ED
	;
	finally { pop(); }

ifcomment returns[IfNode node]
	@init {
		push(EM_IFCOMMENT);
		$node = new IfNode();
	}
	@after {
		$node.update(retval);
		$node.freeze();
	}
	:
	(C_ST IF expression C_ED { $node.setExpression($expression.node); }
		(MAYBE_SKIP {
			TxtNode skip = new TxtNode();
			skip.update($MAYBE_SKIP);
			skip.freeze();
			$node.setMaybeSkip(skip);
		} )?
		nodelist { $node.setChildren($nodelist.list);}
		(elseifnode { $node.addElseIf($elseifnode.node); })* 
		(elsenode { $node.setElse($elsenode.list); })?
		endcomment
	)
	;
	finally { pop(); }

elseifnode	 returns[IfNode node]
	@init {
		push(EM_ELSEIFNODE);
		$node = new IfNode();
	}
	@after {
		$node.update(retval);
		$node.freeze();
	}
	:
	elseifcomment nodelist 
	{
		$node.setExpression($elseifcomment.node);
		$node.setChildren($nodelist.list);
	}
	;
	finally { pop(); }

elseifcomment returns[ExpressionNode node]
	// needless to handle error messages.
	:
	(elseifblockcomment {$node = $elseifblockcomment.node;}
	 | elseiflinecomment {$node = $elseiflinecomment.node;}
	)
	;

elseifblockcomment returns[ExpressionNode node]
	@init {
		push(EM_ELSEIFBLOCKCOMMENT);
	}
	: C_ST ELSEIF expression C_ED { $node = $expression.node; }
	;
	finally { pop(); }

elseiflinecomment returns[ExpressionNode node]
	@init {
		push(EM_ELSEIFLINECOMMENT);
	}
	: C_LN_ST ELSEIF expression C_LN_ED { $node = $expression.node; }
	;
	finally { pop(); }

elsenode returns[List<QueryNode> list]
	@init {
		push(EM_ELSENODE);
	}
	:
	elsecomment nodelist { $list = $nodelist.list; }
	;
	finally { pop(); }

elsecomment 
	@init {
		push(EM_ELSECOMMENT);
	}
	:
	(C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED)
	;
	finally { pop(); }

expression returns[ExpressionNode node]
	@init {
		push(EM_EXPRESSION);
		$node = new ExpressionNode();
	}
	@after {
		$node.update(retval);
		$node.freeze();
	}
	:
	charactors
	;
	finally { pop(); }
	
begincomment returns[BeginNode node]
	@init {
		push(EM_BEGINCOMMENT);
		$node = new BeginNode();
	}
	@after {
		$node.update(retval);
		$node.freeze();
	}
	:
	(
		(C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED) nodelist endcomment
	)
	{
		$node.setChildren($nodelist.list);
	}
	;
	finally { pop(); }

endcomment
	@init {
		push(EM_ENDCOMMENT);
	}
	:
	C_ST END C_ED | C_LN_ST END C_LN_ED
	;
	finally { pop(); }

bindcomment returns[BindNode node]
	@init {
		push(EM_BINDCOMMENT);
		$node = new BindNode();
	}
	@after {
		$node.update(retval);
		$node.freeze();
	}
	:
	(C_ST 
		(bindingname {$node.setBindingName($bindingname.node); })?
		SYM_BIND expression C_ED bindchars)
	{
		$node.setExpression($expression.node);
		$node.setSkipped($bindchars.node);
	}
	;
	finally { pop(); }

bindingname returns[TxtNode node]
	@init {
		push(EM_BINDINGNAME);
		$node = new TxtNode();
	}
	@after {
		$node.freeze();
	}
	:
	(SYM_BIND IDENT { $node.update($IDENT); })
	;
	finally { pop(); }


inbind returns[InBindNode node]
	@init {
		push(EM_INBIND);
		$node = new InBindNode();
	}
	@after {
		$node.update(retval);
		$node.freeze();
	}
	:
	IN C_ST 
		(bindingname {$node.setBindingName($bindingname.node); })? 
		SYM_BIND expression C_ED inbindskipped
	{
		$node.setExpression($expression.node);
		$node.setSkipped($inbindskipped.node);
	}
	;
	finally { pop(); }

inbindskipped returns[TxtNode node]
	@init {
		push(EM_INBINDSKIPPED);
		$node = new TxtNode();
	}
	@after {
		$node.update(retval);
		$node.freeze();
	}
	:
	SYM_LP 
		txtcomment* bindchars txtcomment*
		(SYM_C 
			txtcomment* bindchars txtcomment*
		)*
	SYM_RP
	;
	finally { pop(); }

bindchars  returns[TxtNode node]
	// caller rule handles exceptions
	@init {
		$node = new TxtNode();
	}
	@after {
		$node.update(retval);
		$node.freeze();
	}
	:
	(IDENT | QUOTED | SYMBOLS | SYM_BIND)+
	;
	catch [EarlyExitException ex] {
		throw ex;
	}

// $>

// $<Symbols
SYMBOLS	: '*' | '/' | '-' | '#';
SYM_LP	: '(';
SYM_RP	: ')';
SYM_C	: ',';
SYM_BIND: '?';
// $>

QUOTED	: SYM_Q ~(SYM_Q)+ SYM_Q;
fragment SYM_Q	:	'\u0027' | '"';

// $<Comments
C_ST	:
	{!inComment}? '/*' { inComment = true; }
	;
	
C_ED	:
	{inComment}? '*/' { inComment = false; };
	
C_LN_ST	:
	{!inComment}? ('--'|'#') { inLineComment = true; inComment = true; };

C_LN_ED	:
	{inLineComment}? ( LN_R? LN_N | EOF ) { inLineComment = false; inComment = false; };
// $>

// $<Keywords
fragment AND :	('a'|'A')('n'|'N')('d'|'D');
fragment OR :	('o'|'O')('r'|'R');
MAYBE_SKIP :	AND | OR;
BEGIN 	: ('b'|'B')('e'|'E')('g'|'G')('i'|'I')('n'|'N');
IF		: ('i'|'I')('f'|'F');
ELSE	: ('e'|'E')('l'|'L')('s'|'S')('e'|'E');
ELSEIF	: ELSE IF;
END		: ('e'|'E')('n'|'N')('d'|'D');
IN		: {!inComment}? ('i'|'I')('n'|'N');
// $>

IDENT	: CHAR+;

fragment WS		: '\t' | ' ';
fragment LN_R	: '\r';
fragment LN_N	: '\n';
fragment CHAR	: ~(SYMBOLS | SYM_Q | SYM_BIND | SYM_LP | SYM_RP | SYM_C | LN_R | LN_N | WS);

// $<Hidden
LT : {!inLineComment}? (LN_R? LN_N)+ { $channel = HIDDEN; };
WHITE_SPACES	: (WS)+ { $channel = HIDDEN; };
// $>
