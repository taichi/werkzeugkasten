// $ANTLR 3.0.1 D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g 2008-04-17 17:51:35

package twowaysql.grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class TwoWaySQLLexer extends Lexer {
    public static final int BINDNODE=9;
    public static final int LT=33;
    public static final int C_ST=18;
    public static final int QUOTED=13;
    public static final int SYM_RP=17;
    public static final int INBINDNODE=10;
    public static final int ELSE=24;
    public static final int SYM_LP=16;
    public static final int CHAR=31;
    public static final int C_LN_ST=20;
    public static final int Tokens=35;
    public static final int EOF=-1;
    public static final int C_ED=19;
    public static final int IF=22;
    public static final int ELSEIFNODE=8;
    public static final int WHITE_SPACES=34;
    public static final int ELSEIF=23;
    public static final int SYM_Q=28;
    public static final int WS=32;
    public static final int IN=27;
    public static final int BEGIN=25;
    public static final int IFNODE=5;
    public static final int IDENT=11;
    public static final int SYM_C=15;
    public static final int SYMBOLS=12;
    public static final int SYM_BIND=14;
    public static final int EXPRESSIONNODE=6;
    public static final int END=26;
    public static final int ELSENODE=7;
    public static final int BEGINNODE=4;
    public static final int C_LN_ED=21;
    public static final int LN_N=30;
    public static final int LN_R=29;
    
    boolean inComment = false;
    boolean inLineComment = false;

    public TwoWaySQLLexer() {;} 
    public TwoWaySQLLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g"; }

    // $ANTLR start SYMBOLS
    public final void mSYMBOLS() throws RecognitionException {
        try {
            int _type = SYMBOLS;
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:96:9: ( '*' | '/' | '-' | '#' )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:
            {
            if ( input.LA(1)=='#'||input.LA(1)=='*'||input.LA(1)=='-'||input.LA(1)=='/' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SYMBOLS

    // $ANTLR start SYM_LP
    public final void mSYM_LP() throws RecognitionException {
        try {
            int _type = SYM_LP;
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:97:8: ( '(' )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:97:10: '('
            {
            match('('); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SYM_LP

    // $ANTLR start SYM_RP
    public final void mSYM_RP() throws RecognitionException {
        try {
            int _type = SYM_RP;
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:98:8: ( ')' )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:98:10: ')'
            {
            match(')'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SYM_RP

    // $ANTLR start SYM_C
    public final void mSYM_C() throws RecognitionException {
        try {
            int _type = SYM_C;
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:99:7: ( ',' )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:99:9: ','
            {
            match(','); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SYM_C

    // $ANTLR start SYM_BIND
    public final void mSYM_BIND() throws RecognitionException {
        try {
            int _type = SYM_BIND;
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:100:9: ( '?' )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:100:11: '?'
            {
            match('?'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SYM_BIND

    // $ANTLR start QUOTED
    public final void mQUOTED() throws RecognitionException {
        try {
            int _type = QUOTED;
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:103:8: ( SYM_Q (~ ( SYM_Q ) )+ SYM_Q )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:103:10: SYM_Q (~ ( SYM_Q ) )+ SYM_Q
            {
            mSYM_Q(); 
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:103:16: (~ ( SYM_Q ) )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\u0000' && LA1_0<='!')||(LA1_0>='#' && LA1_0<='&')||(LA1_0>='(' && LA1_0<='\uFFFE')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:103:16: ~ ( SYM_Q )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

            mSYM_Q(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end QUOTED

    // $ANTLR start SYM_Q
    public final void mSYM_Q() throws RecognitionException {
        try {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:104:16: ( '\\u0027' | '\"' )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:
            {
            if ( input.LA(1)=='\"'||input.LA(1)=='\'' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

        }
        finally {
        }
    }
    // $ANTLR end SYM_Q

    // $ANTLR start C_ST
    public final void mC_ST() throws RecognitionException {
        try {
            int _type = C_ST;
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:107:6: ({...}? '/*' )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:108:2: {...}? '/*'
            {
            if ( !(!inComment) ) {
                throw new FailedPredicateException(input, "C_ST", "!inComment");
            }
            match("/*"); 

             inComment = true; 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end C_ST

    // $ANTLR start C_ED
    public final void mC_ED() throws RecognitionException {
        try {
            int _type = C_ED;
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:109:6: ({...}? '*/' )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:110:2: {...}? '*/'
            {
            if ( !(inComment) ) {
                throw new FailedPredicateException(input, "C_ED", "inComment");
            }
            match("*/"); 

             inComment = false; 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end C_ED

    // $ANTLR start C_LN_ST
    public final void mC_LN_ST() throws RecognitionException {
        try {
            int _type = C_LN_ST;
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:112:9: ({...}? ( '--' | '#' ) )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:113:2: {...}? ( '--' | '#' )
            {
            if ( !(!inComment) ) {
                throw new FailedPredicateException(input, "C_LN_ST", "!inComment");
            }
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:113:16: ( '--' | '#' )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='-') ) {
                alt2=1;
            }
            else if ( (LA2_0=='#') ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("113:16: ( '--' | '#' )", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:113:17: '--'
                    {
                    match("--"); 


                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:113:22: '#'
                    {
                    match('#'); 

                    }
                    break;

            }

             inLineComment = true; inComment = true; 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end C_LN_ST

    // $ANTLR start C_LN_ED
    public final void mC_LN_ED() throws RecognitionException {
        try {
            int _type = C_LN_ED;
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:115:9: ({...}? ( LN_R LN_N | LN_R | LN_N | EOF ) )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:116:2: {...}? ( LN_R LN_N | LN_R | LN_N | EOF )
            {
            if ( !(inLineComment) ) {
                throw new FailedPredicateException(input, "C_LN_ED", "inLineComment");
            }
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:116:19: ( LN_R LN_N | LN_R | LN_N | EOF )
            int alt3=4;
            switch ( input.LA(1) ) {
            case '\r':
                {
                int LA3_1 = input.LA(2);

                if ( (LA3_1=='\n') ) {
                    alt3=1;
                }
                else {
                    alt3=2;}
                }
                break;
            case '\n':
                {
                alt3=3;
                }
                break;
            default:
                alt3=4;}

            switch (alt3) {
                case 1 :
                    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:116:21: LN_R LN_N
                    {
                    mLN_R(); 
                    mLN_N(); 

                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:116:33: LN_R
                    {
                    mLN_R(); 

                    }
                    break;
                case 3 :
                    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:116:40: LN_N
                    {
                    mLN_N(); 

                    }
                    break;
                case 4 :
                    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:116:47: EOF
                    {
                    match(EOF); 

                    }
                    break;

            }

             inLineComment = false; inComment = false; 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end C_LN_ED

    // $ANTLR start BEGIN
    public final void mBEGIN() throws RecognitionException {
        try {
            int _type = BEGIN;
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:120:8: ( ( 'b' | 'B' ) ( 'e' | 'E' ) ( 'g' | 'G' ) ( 'i' | 'I' ) ( 'n' | 'N' ) )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:120:10: ( 'b' | 'B' ) ( 'e' | 'E' ) ( 'g' | 'G' ) ( 'i' | 'I' ) ( 'n' | 'N' )
            {
            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end BEGIN

    // $ANTLR start IF
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:121:5: ( ( 'i' | 'I' ) ( 'f' | 'F' ) )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:121:7: ( 'i' | 'I' ) ( 'f' | 'F' )
            {
            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IF

    // $ANTLR start ELSE
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:122:6: ( ( 'e' | 'E' ) ( 'l' | 'L' ) ( 's' | 'S' ) ( 'e' | 'E' ) )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:122:8: ( 'e' | 'E' ) ( 'l' | 'L' ) ( 's' | 'S' ) ( 'e' | 'E' )
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ELSE

    // $ANTLR start ELSEIF
    public final void mELSEIF() throws RecognitionException {
        try {
            int _type = ELSEIF;
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:123:8: ( ELSE IF )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:123:10: ELSE IF
            {
            mELSE(); 
            mIF(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ELSEIF

    // $ANTLR start END
    public final void mEND() throws RecognitionException {
        try {
            int _type = END;
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:124:6: ( ( 'e' | 'E' ) ( 'n' | 'N' ) ( 'd' | 'D' ) )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:124:8: ( 'e' | 'E' ) ( 'n' | 'N' ) ( 'd' | 'D' )
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end END

    // $ANTLR start IN
    public final void mIN() throws RecognitionException {
        try {
            int _type = IN;
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:125:5: ({...}? ( 'i' | 'I' ) ( 'n' | 'N' ) )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:125:7: {...}? ( 'i' | 'I' ) ( 'n' | 'N' )
            {
            if ( !(!inComment) ) {
                throw new FailedPredicateException(input, "IN", "!inComment");
            }
            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IN

    // $ANTLR start IDENT
    public final void mIDENT() throws RecognitionException {
        try {
            int _type = IDENT;
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:128:7: ( ( CHAR )+ )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:128:9: ( CHAR )+
            {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:128:9: ( CHAR )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='\u0000' && LA4_0<='\b')||(LA4_0>='\u000B' && LA4_0<='\f')||(LA4_0>='\u000E' && LA4_0<='\u001F')||LA4_0=='!'||(LA4_0>='$' && LA4_0<='&')||LA4_0=='+'||LA4_0=='.'||(LA4_0>='0' && LA4_0<='>')||(LA4_0>='@' && LA4_0<='\uFFFE')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:128:9: CHAR
            	    {
            	    mCHAR(); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IDENT

    // $ANTLR start WS
    public final void mWS() throws RecognitionException {
        try {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:130:14: ( '\\t' | ' ' )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:
            {
            if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

        }
        finally {
        }
    }
    // $ANTLR end WS

    // $ANTLR start LN_R
    public final void mLN_R() throws RecognitionException {
        try {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:131:15: ( '\\r' )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:131:17: '\\r'
            {
            match('\r'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end LN_R

    // $ANTLR start LN_N
    public final void mLN_N() throws RecognitionException {
        try {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:132:15: ( '\\n' )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:132:17: '\\n'
            {
            match('\n'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end LN_N

    // $ANTLR start CHAR
    public final void mCHAR() throws RecognitionException {
        try {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:133:15: (~ ( SYMBOLS | SYM_Q | SYM_BIND | SYM_LP | SYM_RP | SYM_C | LN_R | LN_N | WS ) )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:133:17: ~ ( SYMBOLS | SYM_Q | SYM_BIND | SYM_LP | SYM_RP | SYM_C | LN_R | LN_N | WS )
            {
            if ( (input.LA(1)>='\u0000' && input.LA(1)<='\b')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\u001F')||input.LA(1)=='!'||(input.LA(1)>='$' && input.LA(1)<='&')||input.LA(1)=='+'||input.LA(1)=='.'||(input.LA(1)>='0' && input.LA(1)<='>')||(input.LA(1)>='@' && input.LA(1)<='\uFFFE') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

        }
        finally {
        }
    }
    // $ANTLR end CHAR

    // $ANTLR start LT
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:136:4: ({...}? ( LN_R | LN_N )+ )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:136:6: {...}? ( LN_R | LN_N )+
            {
            if ( !(!inLineComment) ) {
                throw new FailedPredicateException(input, "LT", "!inLineComment");
            }
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:136:24: ( LN_R | LN_N )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0=='\n'||LA5_0=='\r') ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:
            	    {
            	    if ( input.LA(1)=='\n'||input.LA(1)=='\r' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);

             channel = HIDDEN; 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LT

    // $ANTLR start WHITE_SPACES
    public final void mWHITE_SPACES() throws RecognitionException {
        try {
            int _type = WHITE_SPACES;
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:137:14: ( ( WS )+ )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:137:16: ( WS )+
            {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:137:16: ( WS )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0=='\t'||LA6_0==' ') ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:137:17: WS
            	    {
            	    mWS(); 

            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);

             channel = HIDDEN; 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end WHITE_SPACES

    public void mTokens() throws RecognitionException {
        // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:1:8: ( SYMBOLS | SYM_LP | SYM_RP | SYM_C | SYM_BIND | QUOTED | C_ST | C_ED | C_LN_ST | C_LN_ED | BEGIN | IF | ELSE | ELSEIF | END | IN | IDENT | LT | WHITE_SPACES )
        int alt7=19;
        int LA7_0 = input.LA(1);

        if ( (LA7_0=='/') ) {
            int LA7_1 = input.LA(2);

            if ( (LA7_1=='*') ) {
                alt7=7;
            }
            else {
                alt7=1;}
        }
        else if ( (LA7_0=='(') ) {
            alt7=2;
        }
        else if ( (LA7_0==')') ) {
            alt7=3;
        }
        else if ( (LA7_0==',') ) {
            alt7=4;
        }
        else if ( (LA7_0=='?') ) {
            alt7=5;
        }
        else if ( (LA7_0=='\"'||LA7_0=='\'') ) {
            alt7=6;
        }
        else if ( (LA7_0=='*') ) {
            int LA7_7 = input.LA(2);

            if ( (LA7_7=='/') ) {
                alt7=8;
            }
            else {
                alt7=1;}
        }
        else if ( (LA7_0=='-') ) {
            int LA7_8 = input.LA(2);

            if ( (LA7_8=='-') ) {
                alt7=9;
            }
            else {
                alt7=1;}
        }
        else if ( (LA7_0=='#') ) {
            int LA7_9 = input.LA(2);

            if ( (!(!inComment)) ) {
                alt7=1;
            }
            else if ( (!inComment) ) {
                alt7=9;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1:1: Tokens : ( SYMBOLS | SYM_LP | SYM_RP | SYM_C | SYM_BIND | QUOTED | C_ST | C_ED | C_LN_ST | C_LN_ED | BEGIN | IF | ELSE | ELSEIF | END | IN | IDENT | LT | WHITE_SPACES );", 7, 9, input);

                throw nvae;
            }
        }
        else if ( (LA7_0=='\r') ) {
            int LA7_10 = input.LA(2);

            if ( (LA7_10=='\n') ) {
                int LA7_24 = input.LA(3);

                if ( (LA7_24=='\n'||LA7_24=='\r') ) {
                    alt7=18;
                }
                else if ( (inLineComment) ) {
                    alt7=10;
                }
                else if ( (!inLineComment) ) {
                    alt7=18;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("1:1: Tokens : ( SYMBOLS | SYM_LP | SYM_RP | SYM_C | SYM_BIND | QUOTED | C_ST | C_ED | C_LN_ST | C_LN_ED | BEGIN | IF | ELSE | ELSEIF | END | IN | IDENT | LT | WHITE_SPACES );", 7, 24, input);

                    throw nvae;
                }
            }
            else if ( (LA7_10=='\r') ) {
                alt7=18;
            }
            else if ( (inLineComment) ) {
                alt7=10;
            }
            else if ( (!inLineComment) ) {
                alt7=18;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1:1: Tokens : ( SYMBOLS | SYM_LP | SYM_RP | SYM_C | SYM_BIND | QUOTED | C_ST | C_ED | C_LN_ST | C_LN_ED | BEGIN | IF | ELSE | ELSEIF | END | IN | IDENT | LT | WHITE_SPACES );", 7, 10, input);

                throw nvae;
            }
        }
        else if ( (LA7_0=='\n') ) {
            int LA7_11 = input.LA(2);

            if ( (LA7_11=='\n'||LA7_11=='\r') ) {
                alt7=18;
            }
            else if ( (inLineComment) ) {
                alt7=10;
            }
            else if ( (!inLineComment) ) {
                alt7=18;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1:1: Tokens : ( SYMBOLS | SYM_LP | SYM_RP | SYM_C | SYM_BIND | QUOTED | C_ST | C_ED | C_LN_ST | C_LN_ED | BEGIN | IF | ELSE | ELSEIF | END | IN | IDENT | LT | WHITE_SPACES );", 7, 11, input);

                throw nvae;
            }
        }
        else if ( (LA7_0=='B'||LA7_0=='b') ) {
            int LA7_13 = input.LA(2);

            if ( (LA7_13=='E'||LA7_13=='e') ) {
                int LA7_26 = input.LA(3);

                if ( (LA7_26=='G'||LA7_26=='g') ) {
                    int LA7_31 = input.LA(4);

                    if ( (LA7_31=='I'||LA7_31=='i') ) {
                        int LA7_36 = input.LA(5);

                        if ( (LA7_36=='N'||LA7_36=='n') ) {
                            int LA7_40 = input.LA(6);

                            if ( ((LA7_40>='\u0000' && LA7_40<='\b')||(LA7_40>='\u000B' && LA7_40<='\f')||(LA7_40>='\u000E' && LA7_40<='\u001F')||LA7_40=='!'||(LA7_40>='$' && LA7_40<='&')||LA7_40=='+'||LA7_40=='.'||(LA7_40>='0' && LA7_40<='>')||(LA7_40>='@' && LA7_40<='\uFFFE')) ) {
                                alt7=17;
                            }
                            else {
                                alt7=11;}
                        }
                        else {
                            alt7=17;}
                    }
                    else {
                        alt7=17;}
                }
                else {
                    alt7=17;}
            }
            else {
                alt7=17;}
        }
        else if ( (LA7_0=='I'||LA7_0=='i') ) {
            switch ( input.LA(2) ) {
            case 'F':
            case 'f':
                {
                int LA7_27 = input.LA(3);

                if ( ((LA7_27>='\u0000' && LA7_27<='\b')||(LA7_27>='\u000B' && LA7_27<='\f')||(LA7_27>='\u000E' && LA7_27<='\u001F')||LA7_27=='!'||(LA7_27>='$' && LA7_27<='&')||LA7_27=='+'||LA7_27=='.'||(LA7_27>='0' && LA7_27<='>')||(LA7_27>='@' && LA7_27<='\uFFFE')) ) {
                    alt7=17;
                }
                else {
                    alt7=12;}
                }
                break;
            case 'N':
            case 'n':
                {
                int LA7_28 = input.LA(3);

                if ( ((LA7_28>='\u0000' && LA7_28<='\b')||(LA7_28>='\u000B' && LA7_28<='\f')||(LA7_28>='\u000E' && LA7_28<='\u001F')||LA7_28=='!'||(LA7_28>='$' && LA7_28<='&')||LA7_28=='+'||LA7_28=='.'||(LA7_28>='0' && LA7_28<='>')||(LA7_28>='@' && LA7_28<='\uFFFE')) ) {
                    alt7=17;
                }
                else if ( (!inComment) ) {
                    alt7=16;
                }
                else if ( (true) ) {
                    alt7=17;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("1:1: Tokens : ( SYMBOLS | SYM_LP | SYM_RP | SYM_C | SYM_BIND | QUOTED | C_ST | C_ED | C_LN_ST | C_LN_ED | BEGIN | IF | ELSE | ELSEIF | END | IN | IDENT | LT | WHITE_SPACES );", 7, 28, input);

                    throw nvae;
                }
                }
                break;
            default:
                alt7=17;}

        }
        else if ( (LA7_0=='E'||LA7_0=='e') ) {
            switch ( input.LA(2) ) {
            case 'L':
            case 'l':
                {
                int LA7_29 = input.LA(3);

                if ( (LA7_29=='S'||LA7_29=='s') ) {
                    int LA7_34 = input.LA(4);

                    if ( (LA7_34=='E'||LA7_34=='e') ) {
                        int LA7_38 = input.LA(5);

                        if ( (LA7_38=='I'||LA7_38=='i') ) {
                            int LA7_42 = input.LA(6);

                            if ( (LA7_42=='F'||LA7_42=='f') ) {
                                int LA7_44 = input.LA(7);

                                if ( ((LA7_44>='\u0000' && LA7_44<='\b')||(LA7_44>='\u000B' && LA7_44<='\f')||(LA7_44>='\u000E' && LA7_44<='\u001F')||LA7_44=='!'||(LA7_44>='$' && LA7_44<='&')||LA7_44=='+'||LA7_44=='.'||(LA7_44>='0' && LA7_44<='>')||(LA7_44>='@' && LA7_44<='\uFFFE')) ) {
                                    alt7=17;
                                }
                                else {
                                    alt7=14;}
                            }
                            else {
                                alt7=17;}
                        }
                        else if ( ((LA7_38>='\u0000' && LA7_38<='\b')||(LA7_38>='\u000B' && LA7_38<='\f')||(LA7_38>='\u000E' && LA7_38<='\u001F')||LA7_38=='!'||(LA7_38>='$' && LA7_38<='&')||LA7_38=='+'||LA7_38=='.'||(LA7_38>='0' && LA7_38<='>')||(LA7_38>='@' && LA7_38<='H')||(LA7_38>='J' && LA7_38<='h')||(LA7_38>='j' && LA7_38<='\uFFFE')) ) {
                            alt7=17;
                        }
                        else {
                            alt7=13;}
                    }
                    else {
                        alt7=17;}
                }
                else {
                    alt7=17;}
                }
                break;
            case 'N':
            case 'n':
                {
                int LA7_30 = input.LA(3);

                if ( (LA7_30=='D'||LA7_30=='d') ) {
                    int LA7_35 = input.LA(4);

                    if ( ((LA7_35>='\u0000' && LA7_35<='\b')||(LA7_35>='\u000B' && LA7_35<='\f')||(LA7_35>='\u000E' && LA7_35<='\u001F')||LA7_35=='!'||(LA7_35>='$' && LA7_35<='&')||LA7_35=='+'||LA7_35=='.'||(LA7_35>='0' && LA7_35<='>')||(LA7_35>='@' && LA7_35<='\uFFFE')) ) {
                        alt7=17;
                    }
                    else {
                        alt7=15;}
                }
                else {
                    alt7=17;}
                }
                break;
            default:
                alt7=17;}

        }
        else if ( ((LA7_0>='\u0000' && LA7_0<='\b')||(LA7_0>='\u000B' && LA7_0<='\f')||(LA7_0>='\u000E' && LA7_0<='\u001F')||LA7_0=='!'||(LA7_0>='$' && LA7_0<='&')||LA7_0=='+'||LA7_0=='.'||(LA7_0>='0' && LA7_0<='>')||(LA7_0>='@' && LA7_0<='A')||(LA7_0>='C' && LA7_0<='D')||(LA7_0>='F' && LA7_0<='H')||(LA7_0>='J' && LA7_0<='a')||(LA7_0>='c' && LA7_0<='d')||(LA7_0>='f' && LA7_0<='h')||(LA7_0>='j' && LA7_0<='\uFFFE')) ) {
            alt7=17;
        }
        else if ( (LA7_0=='\t'||LA7_0==' ') ) {
            alt7=19;
        }
        else {
            alt7=10;}
        switch (alt7) {
            case 1 :
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:1:10: SYMBOLS
                {
                mSYMBOLS(); 

                }
                break;
            case 2 :
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:1:18: SYM_LP
                {
                mSYM_LP(); 

                }
                break;
            case 3 :
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:1:25: SYM_RP
                {
                mSYM_RP(); 

                }
                break;
            case 4 :
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:1:32: SYM_C
                {
                mSYM_C(); 

                }
                break;
            case 5 :
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:1:38: SYM_BIND
                {
                mSYM_BIND(); 

                }
                break;
            case 6 :
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:1:47: QUOTED
                {
                mQUOTED(); 

                }
                break;
            case 7 :
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:1:54: C_ST
                {
                mC_ST(); 

                }
                break;
            case 8 :
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:1:59: C_ED
                {
                mC_ED(); 

                }
                break;
            case 9 :
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:1:64: C_LN_ST
                {
                mC_LN_ST(); 

                }
                break;
            case 10 :
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:1:72: C_LN_ED
                {
                mC_LN_ED(); 

                }
                break;
            case 11 :
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:1:80: BEGIN
                {
                mBEGIN(); 

                }
                break;
            case 12 :
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:1:86: IF
                {
                mIF(); 

                }
                break;
            case 13 :
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:1:89: ELSE
                {
                mELSE(); 

                }
                break;
            case 14 :
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:1:94: ELSEIF
                {
                mELSEIF(); 

                }
                break;
            case 15 :
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:1:101: END
                {
                mEND(); 

                }
                break;
            case 16 :
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:1:105: IN
                {
                mIN(); 

                }
                break;
            case 17 :
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:1:108: IDENT
                {
                mIDENT(); 

                }
                break;
            case 18 :
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:1:114: LT
                {
                mLT(); 

                }
                break;
            case 19 :
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:1:117: WHITE_SPACES
                {
                mWHITE_SPACES(); 

                }
                break;

        }

    }


 

}