grammar SimpleTwoway;

@lexer::members {
private boolean inComment = false;
private boolean inLineComment = false;
}

//// Way-1 
program
  : w1_statement EOF
  ;

w1_statement
  :
    w2_statement_decorator?
    ( w1_print_statement
    | w1_if_statement
    | w1_empty_statement
    | w2_statement
    )
  ;

w1_print_statement
  : 'print' w1_expression ';'
  ;

w1_if_statement
  : 'if' '(' w1_expression ')' w1_statement
    ( 'else' w1_statement
    | /* empty */
    )
  ;

w1_empty_statement
  : ';'
  ;

w1_expression
  : w1_term
    ( '+' w1_term
    | '-' w1_term
    )*
  ;

w1_term
  : w1_unary
    ( '*' w1_unary
    | '/' w1_unary
    | '%' w1_unary
    )*
  ;

w1_unary
  : w1_factor
  | '-' w1_factor
  | '+' w1_factor
  ;

w1_factor
  :
    w2_expression_decorator?
    ( ident
    | number
    | '(' w1_expression ')'
    )
  ;

ident
  : IDENT
  ;
  
number
  : NUMBER
  ;

// Way-2
w2_statement
  :
    ( BLOCK_COMMENT_START 'IF' w2_expression BLOCK_COMMENT_END
    | LINE_COMMENT_START 'IF' w2_expression LINE_COMMENT_END
    )
    w1_statement
    (
      ( BLOCK_COMMENT_START 'ELSE' 'IF' w2_expression BLOCK_COMMENT_END
      | LINE_COMMENT_START 'ELSE' 'IF' w2_expression LINE_COMMENT_END
      )
      w1_statement
    )*
    ( BLOCK_COMMENT_START 'ELSE' BLOCK_COMMENT_END
    | LINE_COMMENT_START 'ELSE' LINE_COMMENT_END
    )
    w1_statement
  ;

w2_statement_decorator
  : w2_print_statement
  ;

w2_expression_decorator
  : BLOCK_COMMENT_START w2_expression BLOCK_COMMENT_END
  ;

w2_print_statement
  : BLOCK_COMMENT_START 'PRT' w2_expression BLOCK_COMMENT_END
  | LINE_COMMENT_START 'PRT' w2_expression LINE_COMMENT_END
  ;

w2_expression
  : w2_term
    ( '+' w2_term
    | '-' w2_term
    )*
  ;

w2_term
  : w2_unary
    ( '*' w2_unary
    | '/' w2_unary
    | '%' w2_unary
    )*
  ;

w2_unary
  : w2_factor
  | '-' w2_factor
  | '+' w2_factor
  ;

w2_factor
  : ident
  | number
  | '(' w2_expression ')'
  ;

//// Common tokens
IDENT
  : IDENT_START ( IDENT_START | DIGIT )*
  ;

NUMBER
  : '0'
  | NONZERO_DIGIT DIGIT*
  ;
  
fragment
IDENT_START
  : 'A'..'Z' | 'a'..'z' | '_' | '/' | '*'
  ;

fragment
DIGIT
  : '0' | NONZERO_DIGIT
  ;

fragment
NONZERO_DIGIT
  : '1'..'9'
  ;

//// comment bounds

// cannot be declared in other comments
BLOCK_COMMENT_START
  : {!inComment}? '/*' { inComment = true; }
  ;

// cannot be declared in line comments
BLOCK_COMMENT_END
  : {!inLineComment}? '*/' { inComment = false; }
  ;

// cannot be declared in other comments
LINE_COMMENT_START
  : {!inComment}? '--' { inLineComment = true; inComment = true; }
  ;

// cannot be declared in other line comments
LINE_COMMENT_END
  : {inLineComment}? ( '\r\n' | '\r' | '\n' | EOF ) { inLineComment = false; inComment = false; }
  ;

//// whitespaces
WHITE_SPACE
  : ( ' ' | '\t' )+ { $channel = HIDDEN; }
  ;

NEW_LINE
  : {!inLineComment}? ( '\r' | '\n' )+ { $channel = HIDDEN; }
  ;
