grammar TwoWaySql;

options {
	output = AST;
	ASTLabelType = CommonTree;
	superClass = Parser;
}

@parser::header {
package werkzeugkasten.twowaysql.grammar;

import java.util.LinkedList;
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
	this.coordinator.report(ex);
	ex.printStackTrace();
	super.reportError(ex);
}

protected ExceptionMapper EM_CHARACTORS = new DefaultExceptionMapper();

}

@lexer::header {
package werkzeugkasten.twowaysql.grammar;
}

@lexer::members {
boolean inComment = false;
boolean inLineComment = false;

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
	this.coordinator.report(ex);
	ex.printStackTrace();
	super.reportError(ex);
}
}

twowaySQL returns[TwoWayQuery query]
	@init {
		$query = new TwoWayQuery();
	}
	@after {
		$query.freeze();
	}
	: nodelist EOF {
		$query.setChildren($nodelist.list);
		$query.update($nodelist.tree);
	}
	;

nodelist returns[LinkedList<QueryNode> list]
	@init{
		$list = new LinkedList<QueryNode>();
	}
	:
	(comment {$list.add($comment.node);}
	| inbind {$list.add($inbind.node);}
	| txt {$list.add($txt.node);}
	)+
	;

charactors
	@init {
		push(EM_CHARACTORS);
	}
	:
	(IDENT| SYMBOLS | QUOTED | SYM_BIND | SYM_C | SYM_LP | SYM_RP)+ 
	;
	finally { pop(); }

txt returns[TxtNode node]
	@init {
		$node = new TxtNode();
	}
	@after {
		$node.freeze();
	}
	:
	charactors { $node.update($charactors.tree); }
	;

// $<comment

comment returns[QueryNode node]:
	begincomment {$node = $begincomment.node;}
	| ifcomment {$node = $ifcomment.node;}
	| bindcomment {$node = $bindcomment.node;}
	| blockcomment {$node = $blockcomment.node;}
	| linecomment {$node = $linecomment.node;}
	;

blockcomment returns[TxtNode node]
	@init {
		$node = new TxtNode();
	}
	@after {
		$node.freeze();
	}
	:
	C_ST charactors C_ED { $node.update($C_ST);$node.update($C_ED); }
	;

linecomment returns[TxtNode node]
	@init {
		$node = new TxtNode();
	}
	@after {
		$node.freeze();
	}
	:
	C_LN_ST charactors C_LN_ED { $node.update($C_LN_ST);$node.update($C_LN_ED); }
	;

ifcomment returns[IfNode node]
	@init {
		$node = new IfNode();
	}
	@after {
		$node.freeze();
	}
	:
	(C_ST IF expression C_ED { $node.setExpression($expression.node); }
		nodelist { $node.setChildren($nodelist.list);}
		(elseifnode { $node.addElseIf($elseifnode.node); })* 
		(elsenode { $node.setElse($elsenode.list); })?
		endcomment
	)
	{
		$node.update($C_ST);
		$node.update($endcomment.tree);
	}
	;

elseifnode	 returns[IfNode node]
	@init {
		$node = new IfNode();
	}
	@after {
		$node.freeze();
	}
	:
	elseifcomment nodelist 
	{
		$node.update($elseifcomment.tree);
		$node.setExpression($elseifcomment.node);
		$node.setChildren($nodelist.list);
		$node.update($nodelist.tree);
	}
	;

elseifcomment returns[ExpressionNode node]
	:
	(elseifblockcomment {$node = $elseifblockcomment.node;}
	 | elseiflinecomment {$node = $elseiflinecomment.node;}
	)
	;

elseifblockcomment returns[ExpressionNode node]
	: C_ST ELSEIF expression C_ED { $node = $expression.node; }
	;

elseiflinecomment returns[ExpressionNode node]
	: C_LN_ST ELSEIF expression C_LN_ED { $node = $expression.node; }
	;

elsenode returns[LinkedList<QueryNode> list]
	:
	elsecomment nodelist { $list = $nodelist.list; }
	;

elsecomment :
	(C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED)
	;

expression returns[ExpressionNode node]
	@init {
		$node = new ExpressionNode();
	}
	@after {
		$node.freeze();
	}
	:
	charactors {$node.update($charactors.tree);}
	;
	
begincomment returns[BeginNode node]
	@init {
		$node = new BeginNode();
	}
	@after {
		$node.freeze();
	}
	:
	(
		(C_ST BEGIN C_ED {$node.update($C_ST);}
		 | C_LN_ST BEGIN C_LN_ED  {$node.update($C_LN_ST);}
		) nodelist endcomment
	)
	{
		$node.setChildren($nodelist.list);
		$node.update($endcomment.tree);
	}
	;

endcomment :
	C_ST END C_ED | C_LN_ST END C_LN_ED
	;

bindcomment returns[BindNode node]
	@init {
		$node = new BindNode();
	}
	@after {
		$node.freeze();
	}
	:
	(C_ST SYM_BIND expression C_ED txt)
	{
		$node.update($C_ST);
		$node.setExpression($expression.node);
		$node.setSkipped($txt.node);
				$node.update($txt.tree);
	}
	;

inbind returns[InBindNode node]
	@init {
		$node = new InBindNode();
		TxtNode skip = new TxtNode();
	}
	@after {
		$node.freeze();
	}
	:
	IN C_ST SYM_BIND expression C_ED SYM_LP inbindchars (SYM_C inbindchars)* SYM_RP
	{
		$node.update($IN);
		$node.setExpression($expression.node);
		skip.update($SYM_LP);
		skip.update($SYM_RP);
		$node.setSkipped(skip);
		$node.update($SYM_RP);
	}
	;

inbindchars
	:
	(IDENT| SYMBOLS | SYM_C | QUOTED)+
	;
	


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
LT : {!inLineComment}? (LN_R | LN_N)+ { $channel = HIDDEN; };
WHITE_SPACES	: (WS)+ { $channel = HIDDEN; };
// $>
