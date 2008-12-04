tree grammar TwoWaySqlTreeBuilder;

options {
  tokenVocab = TwoWaySql;
  ASTLabelType = CommonTree;
}

@header {
package twowaysql.grammar;
}

@members {

	static class Node {
		
	}
}

build returns[]
	:	
;
