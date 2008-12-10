// $ANTLR 3.1.1 D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g 2008-12-10 18:42:04

package werkzeugkasten.twowaysql.grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class TwoWaySqlLexer extends Lexer {
    public static final int LT=29;
    public static final int C_ST=11;
    public static final int SYM_RP=10;
    public static final int QUOTED=5;
    public static final int ELSE=18;
    public static final int SYM_LP=9;
    public static final int CHAR=27;
    public static final int C_LN_ST=13;
    public static final int AND=25;
    public static final int EOF=-1;
    public static final int C_ED=12;
    public static final int IF=15;
    public static final int WHITE_SPACES=30;
    public static final int ELSEIF=17;
    public static final int WS=28;
    public static final int SYM_Q=22;
    public static final int IN=21;
    public static final int BEGIN=19;
    public static final int OR=26;
    public static final int IDENT=4;
    public static final int MAYBE_SKIP=16;
    public static final int SYM_C=8;
    public static final int SYMBOLS=6;
    public static final int SYM_BIND=7;
    public static final int END=20;
    public static final int C_LN_ED=14;
    public static final int LN_N=24;
    public static final int LN_R=23;

    boolean inComment = false;
    boolean inLineComment = false;


    // delegates
    // delegators

    public TwoWaySqlLexer() {;} 
    public TwoWaySqlLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public TwoWaySqlLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g"; }

    // $ANTLR start "SYMBOLS"
    public final void mSYMBOLS() throws RecognitionException {
        try {
            int _type = SYMBOLS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:341:9: ( '*' | '/' | '-' | '#' )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:
            {
            if ( input.LA(1)=='#'||input.LA(1)=='*'||input.LA(1)=='-'||input.LA(1)=='/' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SYMBOLS"

    // $ANTLR start "SYM_LP"
    public final void mSYM_LP() throws RecognitionException {
        try {
            int _type = SYM_LP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:342:8: ( '(' )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:342:10: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SYM_LP"

    // $ANTLR start "SYM_RP"
    public final void mSYM_RP() throws RecognitionException {
        try {
            int _type = SYM_RP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:343:8: ( ')' )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:343:10: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SYM_RP"

    // $ANTLR start "SYM_C"
    public final void mSYM_C() throws RecognitionException {
        try {
            int _type = SYM_C;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:344:7: ( ',' )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:344:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SYM_C"

    // $ANTLR start "SYM_BIND"
    public final void mSYM_BIND() throws RecognitionException {
        try {
            int _type = SYM_BIND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:345:9: ( '?' )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:345:11: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SYM_BIND"

    // $ANTLR start "QUOTED"
    public final void mQUOTED() throws RecognitionException {
        try {
            int _type = QUOTED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:348:8: ( SYM_Q (~ ( SYM_Q ) )+ SYM_Q )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:348:10: SYM_Q (~ ( SYM_Q ) )+ SYM_Q
            {
            mSYM_Q(); 
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:348:16: (~ ( SYM_Q ) )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\u0000' && LA1_0<='!')||(LA1_0>='#' && LA1_0<='&')||(LA1_0>='(' && LA1_0<='\uFFFF')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:348:16: ~ ( SYM_Q )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


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

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUOTED"

    // $ANTLR start "SYM_Q"
    public final void mSYM_Q() throws RecognitionException {
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:349:16: ( '\\u0027' | '\"' )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:
            {
            if ( input.LA(1)=='\"'||input.LA(1)=='\'' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "SYM_Q"

    // $ANTLR start "C_ST"
    public final void mC_ST() throws RecognitionException {
        try {
            int _type = C_ST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:352:6: ({...}? '/*' )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:353:2: {...}? '/*'
            {
            if ( !((!inComment)) ) {
                throw new FailedPredicateException(input, "C_ST", "!inComment");
            }
            match("/*"); 

             inComment = true; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "C_ST"

    // $ANTLR start "C_ED"
    public final void mC_ED() throws RecognitionException {
        try {
            int _type = C_ED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:356:6: ({...}? '*/' )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:357:2: {...}? '*/'
            {
            if ( !((inComment)) ) {
                throw new FailedPredicateException(input, "C_ED", "inComment");
            }
            match("*/"); 

             inComment = false; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "C_ED"

    // $ANTLR start "C_LN_ST"
    public final void mC_LN_ST() throws RecognitionException {
        try {
            int _type = C_LN_ST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:359:9: ({...}? ( '--' | '#' ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:360:2: {...}? ( '--' | '#' )
            {
            if ( !((!inComment)) ) {
                throw new FailedPredicateException(input, "C_LN_ST", "!inComment");
            }
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:360:16: ( '--' | '#' )
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
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:360:17: '--'
                    {
                    match("--"); 


                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:360:22: '#'
                    {
                    match('#'); 

                    }
                    break;

            }

             inLineComment = true; inComment = true; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "C_LN_ST"

    // $ANTLR start "C_LN_ED"
    public final void mC_LN_ED() throws RecognitionException {
        try {
            int _type = C_LN_ED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:362:9: ({...}? ( ( LN_R )? LN_N | EOF ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:363:2: {...}? ( ( LN_R )? LN_N | EOF )
            {
            if ( !((inLineComment)) ) {
                throw new FailedPredicateException(input, "C_LN_ED", "inLineComment");
            }
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:363:19: ( ( LN_R )? LN_N | EOF )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='\n'||LA4_0=='\r') ) {
                alt4=1;
            }
            else {
                alt4=2;}
            switch (alt4) {
                case 1 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:363:21: ( LN_R )? LN_N
                    {
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:363:21: ( LN_R )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0=='\r') ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:363:21: LN_R
                            {
                            mLN_R(); 

                            }
                            break;

                    }

                    mLN_N(); 

                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:363:34: EOF
                    {
                    match(EOF); 

                    }
                    break;

            }

             inLineComment = false; inComment = false; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "C_LN_ED"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:367:14: ( ( 'a' | 'A' ) ( 'n' | 'N' ) ( 'd' | 'D' ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:367:16: ( 'a' | 'A' ) ( 'n' | 'N' ) ( 'd' | 'D' )
            {
            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:368:13: ( ( 'o' | 'O' ) ( 'r' | 'R' ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:368:15: ( 'o' | 'O' ) ( 'r' | 'R' )
            {
            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "MAYBE_SKIP"
    public final void mMAYBE_SKIP() throws RecognitionException {
        try {
            int _type = MAYBE_SKIP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:369:12: ( AND | OR )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='A'||LA5_0=='a') ) {
                alt5=1;
            }
            else if ( (LA5_0=='O'||LA5_0=='o') ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:369:14: AND
                    {
                    mAND(); 

                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:369:20: OR
                    {
                    mOR(); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MAYBE_SKIP"

    // $ANTLR start "BEGIN"
    public final void mBEGIN() throws RecognitionException {
        try {
            int _type = BEGIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:370:8: ( ( 'b' | 'B' ) ( 'e' | 'E' ) ( 'g' | 'G' ) ( 'i' | 'I' ) ( 'n' | 'N' ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:370:10: ( 'b' | 'B' ) ( 'e' | 'E' ) ( 'g' | 'G' ) ( 'i' | 'I' ) ( 'n' | 'N' )
            {
            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BEGIN"

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:371:5: ( ( 'i' | 'I' ) ( 'f' | 'F' ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:371:7: ( 'i' | 'I' ) ( 'f' | 'F' )
            {
            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IF"

    // $ANTLR start "ELSE"
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:372:6: ( ( 'e' | 'E' ) ( 'l' | 'L' ) ( 's' | 'S' ) ( 'e' | 'E' ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:372:8: ( 'e' | 'E' ) ( 'l' | 'L' ) ( 's' | 'S' ) ( 'e' | 'E' )
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ELSE"

    // $ANTLR start "ELSEIF"
    public final void mELSEIF() throws RecognitionException {
        try {
            int _type = ELSEIF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:373:8: ( ELSE IF )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:373:10: ELSE IF
            {
            mELSE(); 
            mIF(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ELSEIF"

    // $ANTLR start "END"
    public final void mEND() throws RecognitionException {
        try {
            int _type = END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:374:6: ( ( 'e' | 'E' ) ( 'n' | 'N' ) ( 'd' | 'D' ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:374:8: ( 'e' | 'E' ) ( 'n' | 'N' ) ( 'd' | 'D' )
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "END"

    // $ANTLR start "IN"
    public final void mIN() throws RecognitionException {
        try {
            int _type = IN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:375:5: ({...}? ( 'i' | 'I' ) ( 'n' | 'N' ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:375:7: {...}? ( 'i' | 'I' ) ( 'n' | 'N' )
            {
            if ( !((!inComment)) ) {
                throw new FailedPredicateException(input, "IN", "!inComment");
            }
            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IN"

    // $ANTLR start "IDENT"
    public final void mIDENT() throws RecognitionException {
        try {
            int _type = IDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:378:7: ( ( CHAR )+ )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:378:9: ( CHAR )+
            {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:378:9: ( CHAR )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='\u0000' && LA6_0<='\b')||(LA6_0>='\u000B' && LA6_0<='\f')||(LA6_0>='\u000E' && LA6_0<='\u001F')||LA6_0=='!'||(LA6_0>='$' && LA6_0<='&')||LA6_0=='+'||LA6_0=='.'||(LA6_0>='0' && LA6_0<='>')||(LA6_0>='@' && LA6_0<='\uFFFF')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:378:9: CHAR
            	    {
            	    mCHAR(); 

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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IDENT"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:380:14: ( '\\t' | ' ' )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:
            {
            if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "LN_R"
    public final void mLN_R() throws RecognitionException {
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:381:15: ( '\\r' )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:381:17: '\\r'
            {
            match('\r'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "LN_R"

    // $ANTLR start "LN_N"
    public final void mLN_N() throws RecognitionException {
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:382:15: ( '\\n' )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:382:17: '\\n'
            {
            match('\n'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "LN_N"

    // $ANTLR start "CHAR"
    public final void mCHAR() throws RecognitionException {
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:383:15: (~ ( SYMBOLS | SYM_Q | SYM_BIND | SYM_LP | SYM_RP | SYM_C | LN_R | LN_N | WS ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:383:17: ~ ( SYMBOLS | SYM_Q | SYM_BIND | SYM_LP | SYM_RP | SYM_C | LN_R | LN_N | WS )
            {
            if ( (input.LA(1)>='\u0000' && input.LA(1)<='\b')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\u001F')||input.LA(1)=='!'||(input.LA(1)>='$' && input.LA(1)<='&')||input.LA(1)=='+'||input.LA(1)=='.'||(input.LA(1)>='0' && input.LA(1)<='>')||(input.LA(1)>='@' && input.LA(1)<='\uFFFF') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "CHAR"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:386:4: ({...}? ( ( LN_R )? LN_N )+ )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:386:6: {...}? ( ( LN_R )? LN_N )+
            {
            if ( !((!inLineComment)) ) {
                throw new FailedPredicateException(input, "LT", "!inLineComment");
            }
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:386:24: ( ( LN_R )? LN_N )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='\n'||LA8_0=='\r') ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:386:25: ( LN_R )? LN_N
            	    {
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:386:25: ( LN_R )?
            	    int alt7=2;
            	    int LA7_0 = input.LA(1);

            	    if ( (LA7_0=='\r') ) {
            	        alt7=1;
            	    }
            	    switch (alt7) {
            	        case 1 :
            	            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:386:25: LN_R
            	            {
            	            mLN_R(); 

            	            }
            	            break;

            	    }

            	    mLN_N(); 

            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);

             _channel = HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "WHITE_SPACES"
    public final void mWHITE_SPACES() throws RecognitionException {
        try {
            int _type = WHITE_SPACES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:387:14: ( ( WS )+ )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:387:16: ( WS )+
            {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:387:16: ( WS )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0=='\t'||LA9_0==' ') ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:387:17: WS
            	    {
            	    mWS(); 

            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
            } while (true);

             _channel = HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHITE_SPACES"

    public void mTokens() throws RecognitionException {
        // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:8: ( SYMBOLS | SYM_LP | SYM_RP | SYM_C | SYM_BIND | QUOTED | C_ST | C_ED | C_LN_ST | C_LN_ED | MAYBE_SKIP | BEGIN | IF | ELSE | ELSEIF | END | IN | IDENT | LT | WHITE_SPACES )
        int alt10=20;
        alt10 = dfa10.predict(input);
        switch (alt10) {
            case 1 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:10: SYMBOLS
                {
                mSYMBOLS(); 

                }
                break;
            case 2 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:18: SYM_LP
                {
                mSYM_LP(); 

                }
                break;
            case 3 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:25: SYM_RP
                {
                mSYM_RP(); 

                }
                break;
            case 4 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:32: SYM_C
                {
                mSYM_C(); 

                }
                break;
            case 5 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:38: SYM_BIND
                {
                mSYM_BIND(); 

                }
                break;
            case 6 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:47: QUOTED
                {
                mQUOTED(); 

                }
                break;
            case 7 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:54: C_ST
                {
                mC_ST(); 

                }
                break;
            case 8 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:59: C_ED
                {
                mC_ED(); 

                }
                break;
            case 9 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:64: C_LN_ST
                {
                mC_LN_ST(); 

                }
                break;
            case 10 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:72: C_LN_ED
                {
                mC_LN_ED(); 

                }
                break;
            case 11 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:80: MAYBE_SKIP
                {
                mMAYBE_SKIP(); 

                }
                break;
            case 12 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:91: BEGIN
                {
                mBEGIN(); 

                }
                break;
            case 13 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:97: IF
                {
                mIF(); 

                }
                break;
            case 14 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:100: ELSE
                {
                mELSE(); 

                }
                break;
            case 15 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:105: ELSEIF
                {
                mELSEIF(); 

                }
                break;
            case 16 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:112: END
                {
                mEND(); 

                }
                break;
            case 17 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:116: IN
                {
                mIN(); 

                }
                break;
            case 18 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:119: IDENT
                {
                mIDENT(); 

                }
                break;
            case 19 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:125: LT
                {
                mLT(); 

                }
                break;
            case 20 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:128: WHITE_SPACES
                {
                mWHITE_SPACES(); 

                }
                break;

        }

    }


    protected DFA10 dfa10 = new DFA10(this);
    static final String DFA10_eotS =
        "\1\14\1\25\5\uffff\2\25\1\30\1\uffff\1\31\1\uffff\5\22\11\uffff"+
        "\1\22\1\43\1\22\1\45\1\46\2\22\1\43\1\uffff\1\22\2\uffff\1\22\1"+
        "\54\1\22\1\uffff\1\56\1\uffff\1\60\1\uffff\1\22\1\uffff\1\62\1\uffff";
    static final String DFA10_eofS =
        "\63\uffff";
    static final String DFA10_minS =
        "\1\0\1\52\5\uffff\1\57\1\55\1\0\2\12\1\uffff\1\116\1\122\1\105"+
        "\1\106\1\114\6\uffff\2\0\1\uffff\1\104\1\0\1\107\2\0\1\123\1\104"+
        "\1\0\1\uffff\1\111\1\uffff\1\0\1\105\1\0\1\116\1\uffff\1\0\1\uffff"+
        "\1\0\1\uffff\1\106\1\uffff\1\0\1\uffff";
    static final String DFA10_maxS =
        "\1\uffff\1\52\5\uffff\1\57\1\55\1\0\1\12\1\15\1\uffff\1\156\1\162"+
        "\1\145\2\156\6\uffff\2\0\1\uffff\1\144\1\uffff\1\147\2\uffff\1\163"+
        "\1\144\1\uffff\1\uffff\1\151\1\uffff\1\0\1\145\1\uffff\1\156\1\uffff"+
        "\1\uffff\1\uffff\1\uffff\1\uffff\1\146\1\uffff\1\uffff\1\uffff";
    static final String DFA10_acceptS =
        "\2\uffff\1\2\1\3\1\4\1\5\1\6\5\uffff\1\12\5\uffff\1\22\1\24\1\7"+
        "\1\1\1\10\1\11\2\uffff\1\23\10\uffff\1\13\1\uffff\1\15\4\uffff\1"+
        "\21\1\uffff\1\20\1\uffff\1\16\1\uffff\1\14\1\uffff\1\17";
    static final String DFA10_specialS =
        "\1\2\27\uffff\1\7\1\1\2\uffff\1\6\1\uffff\1\11\1\4\2\uffff\1\3"+
        "\3\uffff\1\0\1\uffff\1\10\2\uffff\1\5\1\uffff\1\12\3\uffff\1\13"+
        "\1\uffff}>";
    static final String[] DFA10_transitionS = {
            "\11\22\1\23\1\13\2\22\1\12\22\22\1\23\1\22\1\6\1\11\3\22\1"+
            "\6\1\2\1\3\1\7\1\22\1\4\1\10\1\22\1\1\17\22\1\5\1\22\1\15\1"+
            "\17\2\22\1\21\3\22\1\20\5\22\1\16\21\22\1\15\1\17\2\22\1\21"+
            "\3\22\1\20\5\22\1\16\uff90\22",
            "\1\24",
            "",
            "",
            "",
            "",
            "",
            "\1\26",
            "\1\27",
            "\1\uffff",
            "\1\13",
            "\1\32\2\uffff\1\32",
            "",
            "\1\33\37\uffff\1\33",
            "\1\34\37\uffff\1\34",
            "\1\35\37\uffff\1\35",
            "\1\36\7\uffff\1\37\27\uffff\1\36\7\uffff\1\37",
            "\1\40\1\uffff\1\41\35\uffff\1\40\1\uffff\1\41",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "",
            "\1\42\37\uffff\1\42",
            "\11\22\2\uffff\2\22\1\uffff\22\22\1\uffff\1\22\2\uffff\3\22"+
            "\4\uffff\1\22\2\uffff\1\22\1\uffff\17\22\1\uffff\uffc0\22",
            "\1\44\37\uffff\1\44",
            "\11\22\2\uffff\2\22\1\uffff\22\22\1\uffff\1\22\2\uffff\3\22"+
            "\4\uffff\1\22\2\uffff\1\22\1\uffff\17\22\1\uffff\uffc0\22",
            "\11\22\2\uffff\2\22\1\uffff\22\22\1\uffff\1\22\2\uffff\3\22"+
            "\4\uffff\1\22\2\uffff\1\22\1\uffff\17\22\1\uffff\uffc0\22",
            "\1\47\37\uffff\1\47",
            "\1\50\37\uffff\1\50",
            "\11\22\2\uffff\2\22\1\uffff\22\22\1\uffff\1\22\2\uffff\3\22"+
            "\4\uffff\1\22\2\uffff\1\22\1\uffff\17\22\1\uffff\uffc0\22",
            "",
            "\1\51\37\uffff\1\51",
            "",
            "\1\uffff",
            "\1\53\37\uffff\1\53",
            "\11\22\2\uffff\2\22\1\uffff\22\22\1\uffff\1\22\2\uffff\3\22"+
            "\4\uffff\1\22\2\uffff\1\22\1\uffff\17\22\1\uffff\uffc0\22",
            "\1\55\37\uffff\1\55",
            "",
            "\11\22\2\uffff\2\22\1\uffff\22\22\1\uffff\1\22\2\uffff\3\22"+
            "\4\uffff\1\22\2\uffff\1\22\1\uffff\17\22\1\uffff\11\22\1\57"+
            "\37\22\1\57\uff96\22",
            "",
            "\11\22\2\uffff\2\22\1\uffff\22\22\1\uffff\1\22\2\uffff\3\22"+
            "\4\uffff\1\22\2\uffff\1\22\1\uffff\17\22\1\uffff\uffc0\22",
            "",
            "\1\61\37\uffff\1\61",
            "",
            "\11\22\2\uffff\2\22\1\uffff\22\22\1\uffff\1\22\2\uffff\3\22"+
            "\4\uffff\1\22\2\uffff\1\22\1\uffff\17\22\1\uffff\uffc0\22",
            ""
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( SYMBOLS | SYM_LP | SYM_RP | SYM_C | SYM_BIND | QUOTED | C_ST | C_ED | C_LN_ST | C_LN_ED | MAYBE_SKIP | BEGIN | IF | ELSE | ELSEIF | END | IN | IDENT | LT | WHITE_SPACES );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA10_38 = input.LA(1);

                         
                        int index10_38 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((!inComment)) ) {s = 42;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index10_38);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA10_25 = input.LA(1);

                         
                        int index10_25 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((inLineComment)) ) {s = 12;}

                        else if ( ((!inLineComment)) ) {s = 26;}

                         
                        input.seek(index10_25);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA10_0 = input.LA(1);

                        s = -1;
                        if ( (LA10_0=='/') ) {s = 1;}

                        else if ( (LA10_0=='(') ) {s = 2;}

                        else if ( (LA10_0==')') ) {s = 3;}

                        else if ( (LA10_0==',') ) {s = 4;}

                        else if ( (LA10_0=='?') ) {s = 5;}

                        else if ( (LA10_0=='\"'||LA10_0=='\'') ) {s = 6;}

                        else if ( (LA10_0=='*') ) {s = 7;}

                        else if ( (LA10_0=='-') ) {s = 8;}

                        else if ( (LA10_0=='#') ) {s = 9;}

                        else if ( (LA10_0=='\r') ) {s = 10;}

                        else if ( (LA10_0=='\n') ) {s = 11;}

                        else if ( (LA10_0=='A'||LA10_0=='a') ) {s = 13;}

                        else if ( (LA10_0=='O'||LA10_0=='o') ) {s = 14;}

                        else if ( (LA10_0=='B'||LA10_0=='b') ) {s = 15;}

                        else if ( (LA10_0=='I'||LA10_0=='i') ) {s = 16;}

                        else if ( (LA10_0=='E'||LA10_0=='e') ) {s = 17;}

                        else if ( ((LA10_0>='\u0000' && LA10_0<='\b')||(LA10_0>='\u000B' && LA10_0<='\f')||(LA10_0>='\u000E' && LA10_0<='\u001F')||LA10_0=='!'||(LA10_0>='$' && LA10_0<='&')||LA10_0=='+'||LA10_0=='.'||(LA10_0>='0' && LA10_0<='>')||LA10_0=='@'||(LA10_0>='C' && LA10_0<='D')||(LA10_0>='F' && LA10_0<='H')||(LA10_0>='J' && LA10_0<='N')||(LA10_0>='P' && LA10_0<='`')||(LA10_0>='c' && LA10_0<='d')||(LA10_0>='f' && LA10_0<='h')||(LA10_0>='j' && LA10_0<='n')||(LA10_0>='p' && LA10_0<='\uFFFF')) ) {s = 18;}

                        else if ( (LA10_0=='\t'||LA10_0==' ') ) {s = 19;}

                        else s = 12;

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA10_34 = input.LA(1);

                        s = -1;
                        if ( ((LA10_34>='\u0000' && LA10_34<='\b')||(LA10_34>='\u000B' && LA10_34<='\f')||(LA10_34>='\u000E' && LA10_34<='\u001F')||LA10_34=='!'||(LA10_34>='$' && LA10_34<='&')||LA10_34=='+'||LA10_34=='.'||(LA10_34>='0' && LA10_34<='>')||(LA10_34>='@' && LA10_34<='\uFFFF')) ) {s = 18;}

                        else s = 35;

                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA10_31 = input.LA(1);

                        s = -1;
                        if ( ((LA10_31>='\u0000' && LA10_31<='\b')||(LA10_31>='\u000B' && LA10_31<='\f')||(LA10_31>='\u000E' && LA10_31<='\u001F')||LA10_31=='!'||(LA10_31>='$' && LA10_31<='&')||LA10_31=='+'||LA10_31=='.'||(LA10_31>='0' && LA10_31<='>')||(LA10_31>='@' && LA10_31<='\uFFFF')) ) {s = 18;}

                        else s = 38;

                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA10_43 = input.LA(1);

                        s = -1;
                        if ( (LA10_43=='I'||LA10_43=='i') ) {s = 47;}

                        else if ( ((LA10_43>='\u0000' && LA10_43<='\b')||(LA10_43>='\u000B' && LA10_43<='\f')||(LA10_43>='\u000E' && LA10_43<='\u001F')||LA10_43=='!'||(LA10_43>='$' && LA10_43<='&')||LA10_43=='+'||LA10_43=='.'||(LA10_43>='0' && LA10_43<='>')||(LA10_43>='@' && LA10_43<='H')||(LA10_43>='J' && LA10_43<='h')||(LA10_43>='j' && LA10_43<='\uFFFF')) ) {s = 18;}

                        else s = 46;

                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA10_28 = input.LA(1);

                        s = -1;
                        if ( ((LA10_28>='\u0000' && LA10_28<='\b')||(LA10_28>='\u000B' && LA10_28<='\f')||(LA10_28>='\u000E' && LA10_28<='\u001F')||LA10_28=='!'||(LA10_28>='$' && LA10_28<='&')||LA10_28=='+'||LA10_28=='.'||(LA10_28>='0' && LA10_28<='>')||(LA10_28>='@' && LA10_28<='\uFFFF')) ) {s = 18;}

                        else s = 35;

                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA10_24 = input.LA(1);

                         
                        int index10_24 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (!(((!inComment)))) ) {s = 21;}

                        else if ( ((!inComment)) ) {s = 23;}

                         
                        input.seek(index10_24);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA10_40 = input.LA(1);

                        s = -1;
                        if ( ((LA10_40>='\u0000' && LA10_40<='\b')||(LA10_40>='\u000B' && LA10_40<='\f')||(LA10_40>='\u000E' && LA10_40<='\u001F')||LA10_40=='!'||(LA10_40>='$' && LA10_40<='&')||LA10_40=='+'||LA10_40=='.'||(LA10_40>='0' && LA10_40<='>')||(LA10_40>='@' && LA10_40<='\uFFFF')) ) {s = 18;}

                        else s = 44;

                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA10_30 = input.LA(1);

                        s = -1;
                        if ( ((LA10_30>='\u0000' && LA10_30<='\b')||(LA10_30>='\u000B' && LA10_30<='\f')||(LA10_30>='\u000E' && LA10_30<='\u001F')||LA10_30=='!'||(LA10_30>='$' && LA10_30<='&')||LA10_30=='+'||LA10_30=='.'||(LA10_30>='0' && LA10_30<='>')||(LA10_30>='@' && LA10_30<='\uFFFF')) ) {s = 18;}

                        else s = 37;

                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA10_45 = input.LA(1);

                        s = -1;
                        if ( ((LA10_45>='\u0000' && LA10_45<='\b')||(LA10_45>='\u000B' && LA10_45<='\f')||(LA10_45>='\u000E' && LA10_45<='\u001F')||LA10_45=='!'||(LA10_45>='$' && LA10_45<='&')||LA10_45=='+'||LA10_45=='.'||(LA10_45>='0' && LA10_45<='>')||(LA10_45>='@' && LA10_45<='\uFFFF')) ) {s = 18;}

                        else s = 48;

                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA10_49 = input.LA(1);

                        s = -1;
                        if ( ((LA10_49>='\u0000' && LA10_49<='\b')||(LA10_49>='\u000B' && LA10_49<='\f')||(LA10_49>='\u000E' && LA10_49<='\u001F')||LA10_49=='!'||(LA10_49>='$' && LA10_49<='&')||LA10_49=='+'||LA10_49=='.'||(LA10_49>='0' && LA10_49<='>')||(LA10_49>='@' && LA10_49<='\uFFFF')) ) {s = 18;}

                        else s = 50;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 10, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}