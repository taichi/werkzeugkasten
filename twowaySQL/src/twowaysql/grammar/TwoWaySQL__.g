lexer grammar TwoWaySQL;
options {
  language=Java;

}
@members {
boolean inComment = false;
boolean inLineComment = false;
}
@header {
package twowaysql.grammar;
}

// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 96
SYMBOLS	: '*' | '/' | '-' | '#';
// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 97
SYM_LP	: '(';
// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 98
SYM_RP	: ')';
// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 99
SYM_C	: ',';
// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 100
SYM_BIND: '?';
// $>

// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 103
QUOTED	: SYM_Q ~(SYM_Q)+ SYM_Q;
// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 104
fragment SYM_Q	:	'\u0027' | '"';

// $<Comments
// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 107
C_ST	:
	{!inComment}? '/*' { inComment = true; };
// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 109
C_ED	:
	{inComment}? '*/' { inComment = false; };
	
// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 112
C_LN_ST	:
	{!inComment}? ('--'|'#') { inLineComment = true; inComment = true; };

// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 115
C_LN_ED	:
	{inLineComment}? ( LN_R LN_N | LN_R | LN_N | EOF ) { inLineComment = false; inComment = false; };
// $>

// $<Keywords
// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 120
BEGIN 	: ('b'|'B')('e'|'E')('g'|'G')('i'|'I')('n'|'N');
// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 121
IF		: ('i'|'I')('f'|'F');
// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 122
ELSE	: ('e'|'E')('l'|'L')('s'|'S')('e'|'E');
// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 123
ELSEIF	: ELSE IF;
// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 124
END		: ('e'|'E')('n'|'N')('d'|'D');
// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 125
IN		: {!inComment}? ('i'|'I')('n'|'N');
// $>

// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 128
IDENT	: CHAR+;

// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 130
fragment WS		: '\t' | ' ';
// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 131
fragment LN_R	: '\r';
// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 132
fragment LN_N	: '\n';
// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 133
fragment CHAR	: ~(SYMBOLS | SYM_Q | SYM_BIND | SYM_LP | SYM_RP | SYM_C | LN_R | LN_N | WS);

// $<Hidden
// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 136
LT : {!inLineComment}? (LN_R | LN_N)+ { $channel = HIDDEN; };
// $ANTLR src "D:\development\java\workspace-3.3\twowaySQL\src\twowaysql\grammar\TwoWaySQL.g" 137
WHITE_SPACES	: (WS)+ { $channel = HIDDEN; };
// $>
