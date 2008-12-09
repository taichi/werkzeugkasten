// $ANTLR 3.1.1 D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g 2008-12-09 15:10:33

package werkzeugkasten.twowaysql.grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class TwoWaySqlLexer extends Lexer {
    public static final int LT=26;
    public static final int C_ST=11;
    public static final int SYM_RP=10;
    public static final int QUOTED=5;
    public static final int ELSE=17;
    public static final int SYM_LP=9;
    public static final int CHAR=24;
    public static final int C_LN_ST=13;
    public static final int EOF=-1;
    public static final int C_ED=12;
    public static final int IF=15;
    public static final int WHITE_SPACES=27;
    public static final int ELSEIF=16;
    public static final int WS=25;
    public static final int SYM_Q=21;
    public static final int IN=20;
    public static final int BEGIN=18;
    public static final int IDENT=4;
    public static final int SYM_C=8;
    public static final int SYMBOLS=6;
    public static final int END=19;
    public static final int SYM_BIND=7;
    public static final int C_LN_ED=14;
    public static final int LN_N=23;
    public static final int LN_R=22;

    boolean inComment = false;
    boolean inLineComment = false;

    public void reportError(RecognitionException ex) {
    	ex.printStackTrace();
    	super.reportError(ex);
    }


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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:295:9: ( '*' | '/' | '-' | '#' )
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:296:8: ( '(' )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:296:10: '('
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:297:8: ( ')' )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:297:10: ')'
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:298:7: ( ',' )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:298:9: ','
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:299:9: ( '?' )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:299:11: '?'
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:302:8: ( SYM_Q (~ ( SYM_Q ) )+ SYM_Q )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:302:10: SYM_Q (~ ( SYM_Q ) )+ SYM_Q
            {
            mSYM_Q(); 
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:302:16: (~ ( SYM_Q ) )+
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
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:302:16: ~ ( SYM_Q )
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:303:16: ( '\\u0027' | '\"' )
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:306:6: ({...}? '/*' )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:307:2: {...}? '/*'
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:310:6: ({...}? '*/' )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:311:2: {...}? '*/'
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:313:9: ({...}? ( '--' | '#' ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:314:2: {...}? ( '--' | '#' )
            {
            if ( !((!inComment)) ) {
                throw new FailedPredicateException(input, "C_LN_ST", "!inComment");
            }
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:314:16: ( '--' | '#' )
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
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:314:17: '--'
                    {
                    match("--"); 


                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:314:22: '#'
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:316:9: ({...}? ( ( LN_R )? LN_N | EOF ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:317:2: {...}? ( ( LN_R )? LN_N | EOF )
            {
            if ( !((inLineComment)) ) {
                throw new FailedPredicateException(input, "C_LN_ED", "inLineComment");
            }
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:317:19: ( ( LN_R )? LN_N | EOF )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='\n'||LA4_0=='\r') ) {
                alt4=1;
            }
            else {
                alt4=2;}
            switch (alt4) {
                case 1 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:317:21: ( LN_R )? LN_N
                    {
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:317:21: ( LN_R )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0=='\r') ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:317:21: LN_R
                            {
                            mLN_R(); 

                            }
                            break;

                    }

                    mLN_N(); 

                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:317:34: EOF
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

    // $ANTLR start "BEGIN"
    public final void mBEGIN() throws RecognitionException {
        try {
            int _type = BEGIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:321:8: ( ( 'b' | 'B' ) ( 'e' | 'E' ) ( 'g' | 'G' ) ( 'i' | 'I' ) ( 'n' | 'N' ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:321:10: ( 'b' | 'B' ) ( 'e' | 'E' ) ( 'g' | 'G' ) ( 'i' | 'I' ) ( 'n' | 'N' )
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:322:5: ( ( 'i' | 'I' ) ( 'f' | 'F' ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:322:7: ( 'i' | 'I' ) ( 'f' | 'F' )
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:323:6: ( ( 'e' | 'E' ) ( 'l' | 'L' ) ( 's' | 'S' ) ( 'e' | 'E' ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:323:8: ( 'e' | 'E' ) ( 'l' | 'L' ) ( 's' | 'S' ) ( 'e' | 'E' )
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:324:8: ( ELSE IF )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:324:10: ELSE IF
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:325:6: ( ( 'e' | 'E' ) ( 'n' | 'N' ) ( 'd' | 'D' ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:325:8: ( 'e' | 'E' ) ( 'n' | 'N' ) ( 'd' | 'D' )
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:326:5: ({...}? ( 'i' | 'I' ) ( 'n' | 'N' ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:326:7: {...}? ( 'i' | 'I' ) ( 'n' | 'N' )
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:329:7: ( ( CHAR )+ )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:329:9: ( CHAR )+
            {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:329:9: ( CHAR )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='\u0000' && LA5_0<='\b')||(LA5_0>='\u000B' && LA5_0<='\f')||(LA5_0>='\u000E' && LA5_0<='\u001F')||LA5_0=='!'||(LA5_0>='$' && LA5_0<='&')||LA5_0=='+'||LA5_0=='.'||(LA5_0>='0' && LA5_0<='>')||(LA5_0>='@' && LA5_0<='\uFFFF')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:329:9: CHAR
            	    {
            	    mCHAR(); 

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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:331:14: ( '\\t' | ' ' )
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:332:15: ( '\\r' )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:332:17: '\\r'
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:333:15: ( '\\n' )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:333:17: '\\n'
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:334:15: (~ ( SYMBOLS | SYM_Q | SYM_BIND | SYM_LP | SYM_RP | SYM_C | LN_R | LN_N | WS ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:334:17: ~ ( SYMBOLS | SYM_Q | SYM_BIND | SYM_LP | SYM_RP | SYM_C | LN_R | LN_N | WS )
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:337:4: ({...}? ( ( LN_R )? LN_N )+ )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:337:6: {...}? ( ( LN_R )? LN_N )+
            {
            if ( !((!inLineComment)) ) {
                throw new FailedPredicateException(input, "LT", "!inLineComment");
            }
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:337:24: ( ( LN_R )? LN_N )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='\n'||LA7_0=='\r') ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:337:25: ( LN_R )? LN_N
            	    {
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:337:25: ( LN_R )?
            	    int alt6=2;
            	    int LA6_0 = input.LA(1);

            	    if ( (LA6_0=='\r') ) {
            	        alt6=1;
            	    }
            	    switch (alt6) {
            	        case 1 :
            	            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:337:25: LN_R
            	            {
            	            mLN_R(); 

            	            }
            	            break;

            	    }

            	    mLN_N(); 

            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:338:14: ( ( WS )+ )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:338:16: ( WS )+
            {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:338:16: ( WS )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='\t'||LA8_0==' ') ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:338:17: WS
            	    {
            	    mWS(); 

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
    // $ANTLR end "WHITE_SPACES"

    public void mTokens() throws RecognitionException {
        // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:8: ( SYMBOLS | SYM_LP | SYM_RP | SYM_C | SYM_BIND | QUOTED | C_ST | C_ED | C_LN_ST | C_LN_ED | BEGIN | IF | ELSE | ELSEIF | END | IN | IDENT | LT | WHITE_SPACES )
        int alt9=19;
        alt9 = dfa9.predict(input);
        switch (alt9) {
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
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:80: BEGIN
                {
                mBEGIN(); 

                }
                break;
            case 12 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:86: IF
                {
                mIF(); 

                }
                break;
            case 13 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:89: ELSE
                {
                mELSE(); 

                }
                break;
            case 14 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:94: ELSEIF
                {
                mELSEIF(); 

                }
                break;
            case 15 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:101: END
                {
                mEND(); 

                }
                break;
            case 16 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:105: IN
                {
                mIN(); 

                }
                break;
            case 17 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:108: IDENT
                {
                mIDENT(); 

                }
                break;
            case 18 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:114: LT
                {
                mLT(); 

                }
                break;
            case 19 :
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:1:117: WHITE_SPACES
                {
                mWHITE_SPACES(); 

                }
                break;

        }

    }


    protected DFA9 dfa9 = new DFA9(this);
    static final String DFA9_eotS =
        "\1\14\1\23\5\uffff\2\23\1\26\1\uffff\1\27\1\uffff\3\20\11\uffff"+
        "\1\20\1\37\1\40\3\20\2\uffff\1\20\1\46\1\20\1\uffff\1\50\1\uffff"+
        "\1\52\1\uffff\1\20\1\uffff\1\54\1\uffff";
    static final String DFA9_eofS =
        "\55\uffff";
    static final String DFA9_minS =
        "\1\0\1\52\5\uffff\1\57\1\55\1\0\2\12\1\uffff\1\105\1\106\1\114"+
        "\6\uffff\2\0\1\uffff\1\107\2\0\1\123\1\104\1\111\1\uffff\1\0\1\105"+
        "\1\0\1\116\1\uffff\1\0\1\uffff\1\0\1\uffff\1\106\1\uffff\1\0\1\uffff";
    static final String DFA9_maxS =
        "\1\uffff\1\52\5\uffff\1\57\1\55\1\0\1\12\1\15\1\uffff\1\145\2\156"+
        "\6\uffff\2\0\1\uffff\1\147\2\uffff\1\163\1\144\1\151\1\uffff\1\0"+
        "\1\145\1\uffff\1\156\1\uffff\1\uffff\1\uffff\1\uffff\1\uffff\1\146"+
        "\1\uffff\1\uffff\1\uffff";
    static final String DFA9_acceptS =
        "\2\uffff\1\2\1\3\1\4\1\5\1\6\5\uffff\1\12\3\uffff\1\21\1\23\1\7"+
        "\1\1\1\10\1\11\2\uffff\1\22\6\uffff\1\14\4\uffff\1\20\1\uffff\1"+
        "\17\1\uffff\1\15\1\uffff\1\13\1\uffff\1\16";
    static final String DFA9_specialS =
        "\1\1\25\uffff\1\10\1\0\2\uffff\1\7\1\11\4\uffff\1\2\1\uffff\1\5"+
        "\2\uffff\1\3\1\uffff\1\4\3\uffff\1\6\1\uffff}>";
    static final String[] DFA9_transitionS = {
            "\11\20\1\21\1\13\2\20\1\12\22\20\1\21\1\20\1\6\1\11\3\20\1"+
            "\6\1\2\1\3\1\7\1\20\1\4\1\10\1\20\1\1\17\20\1\5\2\20\1\15\2"+
            "\20\1\17\3\20\1\16\30\20\1\15\2\20\1\17\3\20\1\16\uff96\20",
            "\1\22",
            "",
            "",
            "",
            "",
            "",
            "\1\24",
            "\1\25",
            "\1\uffff",
            "\1\13",
            "\1\30\2\uffff\1\30",
            "",
            "\1\31\37\uffff\1\31",
            "\1\32\7\uffff\1\33\27\uffff\1\32\7\uffff\1\33",
            "\1\34\1\uffff\1\35\35\uffff\1\34\1\uffff\1\35",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "",
            "\1\36\37\uffff\1\36",
            "\11\20\2\uffff\2\20\1\uffff\22\20\1\uffff\1\20\2\uffff\3\20"+
            "\4\uffff\1\20\2\uffff\1\20\1\uffff\17\20\1\uffff\uffc0\20",
            "\11\20\2\uffff\2\20\1\uffff\22\20\1\uffff\1\20\2\uffff\3\20"+
            "\4\uffff\1\20\2\uffff\1\20\1\uffff\17\20\1\uffff\uffc0\20",
            "\1\41\37\uffff\1\41",
            "\1\42\37\uffff\1\42",
            "\1\43\37\uffff\1\43",
            "",
            "\1\uffff",
            "\1\45\37\uffff\1\45",
            "\11\20\2\uffff\2\20\1\uffff\22\20\1\uffff\1\20\2\uffff\3\20"+
            "\4\uffff\1\20\2\uffff\1\20\1\uffff\17\20\1\uffff\uffc0\20",
            "\1\47\37\uffff\1\47",
            "",
            "\11\20\2\uffff\2\20\1\uffff\22\20\1\uffff\1\20\2\uffff\3\20"+
            "\4\uffff\1\20\2\uffff\1\20\1\uffff\17\20\1\uffff\11\20\1\51"+
            "\37\20\1\51\uff96\20",
            "",
            "\11\20\2\uffff\2\20\1\uffff\22\20\1\uffff\1\20\2\uffff\3\20"+
            "\4\uffff\1\20\2\uffff\1\20\1\uffff\17\20\1\uffff\uffc0\20",
            "",
            "\1\53\37\uffff\1\53",
            "",
            "\11\20\2\uffff\2\20\1\uffff\22\20\1\uffff\1\20\2\uffff\3\20"+
            "\4\uffff\1\20\2\uffff\1\20\1\uffff\17\20\1\uffff\uffc0\20",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( SYMBOLS | SYM_LP | SYM_RP | SYM_C | SYM_BIND | QUOTED | C_ST | C_ED | C_LN_ST | C_LN_ED | BEGIN | IF | ELSE | ELSEIF | END | IN | IDENT | LT | WHITE_SPACES );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA9_23 = input.LA(1);

                         
                        int index9_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((inLineComment)) ) {s = 12;}

                        else if ( ((!inLineComment)) ) {s = 24;}

                         
                        input.seek(index9_23);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA9_0 = input.LA(1);

                        s = -1;
                        if ( (LA9_0=='/') ) {s = 1;}

                        else if ( (LA9_0=='(') ) {s = 2;}

                        else if ( (LA9_0==')') ) {s = 3;}

                        else if ( (LA9_0==',') ) {s = 4;}

                        else if ( (LA9_0=='?') ) {s = 5;}

                        else if ( (LA9_0=='\"'||LA9_0=='\'') ) {s = 6;}

                        else if ( (LA9_0=='*') ) {s = 7;}

                        else if ( (LA9_0=='-') ) {s = 8;}

                        else if ( (LA9_0=='#') ) {s = 9;}

                        else if ( (LA9_0=='\r') ) {s = 10;}

                        else if ( (LA9_0=='\n') ) {s = 11;}

                        else if ( (LA9_0=='B'||LA9_0=='b') ) {s = 13;}

                        else if ( (LA9_0=='I'||LA9_0=='i') ) {s = 14;}

                        else if ( (LA9_0=='E'||LA9_0=='e') ) {s = 15;}

                        else if ( ((LA9_0>='\u0000' && LA9_0<='\b')||(LA9_0>='\u000B' && LA9_0<='\f')||(LA9_0>='\u000E' && LA9_0<='\u001F')||LA9_0=='!'||(LA9_0>='$' && LA9_0<='&')||LA9_0=='+'||LA9_0=='.'||(LA9_0>='0' && LA9_0<='>')||(LA9_0>='@' && LA9_0<='A')||(LA9_0>='C' && LA9_0<='D')||(LA9_0>='F' && LA9_0<='H')||(LA9_0>='J' && LA9_0<='a')||(LA9_0>='c' && LA9_0<='d')||(LA9_0>='f' && LA9_0<='h')||(LA9_0>='j' && LA9_0<='\uFFFF')) ) {s = 16;}

                        else if ( (LA9_0=='\t'||LA9_0==' ') ) {s = 17;}

                        else s = 12;

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA9_32 = input.LA(1);

                         
                        int index9_32 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((!inComment)) ) {s = 36;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index9_32);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA9_37 = input.LA(1);

                        s = -1;
                        if ( (LA9_37=='I'||LA9_37=='i') ) {s = 41;}

                        else if ( ((LA9_37>='\u0000' && LA9_37<='\b')||(LA9_37>='\u000B' && LA9_37<='\f')||(LA9_37>='\u000E' && LA9_37<='\u001F')||LA9_37=='!'||(LA9_37>='$' && LA9_37<='&')||LA9_37=='+'||LA9_37=='.'||(LA9_37>='0' && LA9_37<='>')||(LA9_37>='@' && LA9_37<='H')||(LA9_37>='J' && LA9_37<='h')||(LA9_37>='j' && LA9_37<='\uFFFF')) ) {s = 16;}

                        else s = 40;

                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA9_39 = input.LA(1);

                        s = -1;
                        if ( ((LA9_39>='\u0000' && LA9_39<='\b')||(LA9_39>='\u000B' && LA9_39<='\f')||(LA9_39>='\u000E' && LA9_39<='\u001F')||LA9_39=='!'||(LA9_39>='$' && LA9_39<='&')||LA9_39=='+'||LA9_39=='.'||(LA9_39>='0' && LA9_39<='>')||(LA9_39>='@' && LA9_39<='\uFFFF')) ) {s = 16;}

                        else s = 42;

                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA9_34 = input.LA(1);

                        s = -1;
                        if ( ((LA9_34>='\u0000' && LA9_34<='\b')||(LA9_34>='\u000B' && LA9_34<='\f')||(LA9_34>='\u000E' && LA9_34<='\u001F')||LA9_34=='!'||(LA9_34>='$' && LA9_34<='&')||LA9_34=='+'||LA9_34=='.'||(LA9_34>='0' && LA9_34<='>')||(LA9_34>='@' && LA9_34<='\uFFFF')) ) {s = 16;}

                        else s = 38;

                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA9_43 = input.LA(1);

                        s = -1;
                        if ( ((LA9_43>='\u0000' && LA9_43<='\b')||(LA9_43>='\u000B' && LA9_43<='\f')||(LA9_43>='\u000E' && LA9_43<='\u001F')||LA9_43=='!'||(LA9_43>='$' && LA9_43<='&')||LA9_43=='+'||LA9_43=='.'||(LA9_43>='0' && LA9_43<='>')||(LA9_43>='@' && LA9_43<='\uFFFF')) ) {s = 16;}

                        else s = 44;

                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA9_26 = input.LA(1);

                        s = -1;
                        if ( ((LA9_26>='\u0000' && LA9_26<='\b')||(LA9_26>='\u000B' && LA9_26<='\f')||(LA9_26>='\u000E' && LA9_26<='\u001F')||LA9_26=='!'||(LA9_26>='$' && LA9_26<='&')||LA9_26=='+'||LA9_26=='.'||(LA9_26>='0' && LA9_26<='>')||(LA9_26>='@' && LA9_26<='\uFFFF')) ) {s = 16;}

                        else s = 31;

                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA9_22 = input.LA(1);

                         
                        int index9_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (!(((!inComment)))) ) {s = 19;}

                        else if ( ((!inComment)) ) {s = 21;}

                         
                        input.seek(index9_22);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA9_27 = input.LA(1);

                        s = -1;
                        if ( ((LA9_27>='\u0000' && LA9_27<='\b')||(LA9_27>='\u000B' && LA9_27<='\f')||(LA9_27>='\u000E' && LA9_27<='\u001F')||LA9_27=='!'||(LA9_27>='$' && LA9_27<='&')||LA9_27=='+'||LA9_27=='.'||(LA9_27>='0' && LA9_27<='>')||(LA9_27>='@' && LA9_27<='\uFFFF')) ) {s = 16;}

                        else s = 32;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 9, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}