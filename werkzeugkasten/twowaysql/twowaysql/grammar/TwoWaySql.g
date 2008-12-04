grammar TwoWaySql;

options {
	output = AST;
	ASTLabelType = CommonTree;
	//superClass = Parser;
}

tokens {
	BEGINNODE;
	IFNODE;
	EXPRESSIONNODE;
	ELSENODE;
	ELSEIFNODE;
	BINDNODE;
	INBINDNODE;
	TXTNODE;
	ROOTNODE;
}

@header {
package werkzeugkasten.twowaysql.grammar;

}

@lexer::header {
package werkzeugkasten.twowaysql.grammar;
}



@lexer::members {
boolean inComment = false;
boolean inLineComment = false;
}

twowaySQL : txt EOF
	-> ^(ROOTNODE txt)
	;

txt : (comment | inbind | txts)+
	;

charactors :	
	(IDENT| SYMBOLS | QUOTED | SYM_BIND | SYM_C | SYM_LP | SYM_RP)+ 
;
txts :
	charactors
	-> ^(TXTNODE charactors)
	;

// $<comment

comment :
	begincomment
	| ifcomment
	| bindcomment
	| blockcomment
	| linecomment
	;

blockcomment :
	C_ST charactors C_ED 
	-> ^(TXTNODE C_ST charactors C_ED)
	;

linecomment :
	C_LN_ST charactors C_LN_ED 
	-> ^(TXTNODE C_LN_ST charactors C_LN_ED)
	;

ifcomment :
	(C_ST IF expression C_ED txt elseifnode* elsenode? endcomment)
	-> ^(IFNODE expression txt elseifnode* elsenode? )
	;

elseifnode	:
	elseifcomment txt -> ^(ELSEIFNODE elseifcomment txt);

elsenode :
	elsecomment txt -> ^(ELSENODE txt);

elseifcomment :
	(C_ST ELSEIF expression C_ED | C_LN_ST ELSEIF expression C_LN_ED)
		-> expression;

elsecomment :
	(C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED) ;

expression :
	charactors -> ^(EXPRESSIONNODE charactors) ;
	
begincomment :
	((C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED) txt endcomment) -> ^(BEGINNODE txt);

endcomment :
	C_ST END C_ED | C_LN_ST END C_LN_ED;

bindcomment :
	(C_ST SYM_BIND expression C_ED charactors)
		-> ^(BINDNODE expression);

inbind	:
	IN C_ST SYM_BIND expression C_ED SYM_LP inbindchars (SYM_C inbindchars)* SYM_RP
		-> ^(INBINDNODE IN expression);

inbindchars	: (IDENT| SYMBOLS | SYM_C | QUOTED)+;

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
	{!inComment}? '/*' { inComment = true; };
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
