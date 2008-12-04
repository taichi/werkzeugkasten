tree grammar TwoWaySqlTreeBuilder;

options {
  tokenVocab = TwoWaySql;
  ASTLabelType = CommonTree;
}

@header {
package werkzeugkasten.twowaysql.grammar;

import werkzeugkasten.twowaysql.tree.*;
}

@members {
	NodeFactory f = NodeFactory.getInstance();
}

build 
	: ^(ROOTNODE txt)
;

txt 
	: txts | expression | inbind 
;

txts 
	: ^(TXTNODE charactors)
;

expression 
	: 
	;

inbind 
	:	
;
