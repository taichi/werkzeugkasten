// $ANTLR 3.1.1 C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g 2009-06-05 04:14:13

package werkzeugkasten.twowaysql.grammar;

import java.util.ArrayList;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.TreeAdaptor;

import werkzeugkasten.twowaysql.error.ExceptionMapper;
import werkzeugkasten.twowaysql.error.ProblemCoordinator;
import werkzeugkasten.twowaysql.error.mapper.BeginCommentExceptionMapper;
import werkzeugkasten.twowaysql.error.mapper.BindCommentExceptionMapper;
import werkzeugkasten.twowaysql.error.mapper.BindingNameExceptionMapper;
import werkzeugkasten.twowaysql.error.mapper.BlockCommentExceptionMapper;
import werkzeugkasten.twowaysql.error.mapper.ElseCommentExceptionMapper;
import werkzeugkasten.twowaysql.error.mapper.ElseIfBlockCommentExceptionMapper;
import werkzeugkasten.twowaysql.error.mapper.ElseIfLineCommentExceptionMapper;
import werkzeugkasten.twowaysql.error.mapper.ElseIfNodeExceptionMapper;
import werkzeugkasten.twowaysql.error.mapper.ElseNodeExceptionMapper;
import werkzeugkasten.twowaysql.error.mapper.EndCommentExceptionMapper;
import werkzeugkasten.twowaysql.error.mapper.ExpressionExceptionMapper;
import werkzeugkasten.twowaysql.error.mapper.IfCommentExceptionMapper;
import werkzeugkasten.twowaysql.error.mapper.InBindExceptionMapper;
import werkzeugkasten.twowaysql.error.mapper.InBindSkippedExceptionMapper;
import werkzeugkasten.twowaysql.error.mapper.LineCommentExceptionMapper;
import werkzeugkasten.twowaysql.error.mapper.TwoWaySqlExceptionMapper;
import werkzeugkasten.twowaysql.error.mapper.TxtExceptionMapper;
import werkzeugkasten.twowaysql.tree.BeginNode;
import werkzeugkasten.twowaysql.tree.BindNode;
import werkzeugkasten.twowaysql.tree.ElseNode;
import werkzeugkasten.twowaysql.tree.ExpressionNode;
import werkzeugkasten.twowaysql.tree.IfNode;
import werkzeugkasten.twowaysql.tree.InBindNode;
import werkzeugkasten.twowaysql.tree.QueryNode;
import werkzeugkasten.twowaysql.tree.TwoWayQuery;
import werkzeugkasten.twowaysql.tree.TxtNode;

public class TwoWaySqlParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IDENT", "QUOTED", "MAYBE_SKIP", "SYMBOLS", "SYM_BIND", "SYM_C", "SYM_LP", "SYM_RP", "C_ST", "C_ED", "C_LN_ST", "C_LN_ED", "IF", "ELSEIF", "ELSE", "BEGIN", "END", "IN", "SYM_Q", "LN_R", "LN_N", "AND", "OR", "CHAR", "WS", "LT", "WHITE_SPACES"
    };
    public static final int LT=29;
    public static final int C_ST=12;
    public static final int QUOTED=5;
    public static final int SYM_RP=11;
    public static final int ELSE=18;
    public static final int SYM_LP=10;
    public static final int CHAR=27;
    public static final int C_LN_ST=14;
    public static final int AND=25;
    public static final int EOF=-1;
    public static final int C_ED=13;
    public static final int IF=16;
    public static final int WHITE_SPACES=30;
    public static final int ELSEIF=17;
    public static final int WS=28;
    public static final int SYM_Q=22;
    public static final int IN=21;
    public static final int BEGIN=19;
    public static final int OR=26;
    public static final int IDENT=4;
    public static final int MAYBE_SKIP=6;
    public static final int SYMBOLS=7;
    public static final int SYM_C=9;
    public static final int END=20;
    public static final int SYM_BIND=8;
    public static final int C_LN_ED=15;
    public static final int LN_N=24;
    public static final int LN_R=23;

    // delegates
    // delegators


        public TwoWaySqlParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public TwoWaySqlParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return TwoWaySqlParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g"; }


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

    protected static final ExceptionMapper EM_TOWWAYSQL = new TwoWaySqlExceptionMapper();
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



    public static class twowaySQL_return extends ParserRuleReturnScope {
        public TwoWayQuery query;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "twowaySQL"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:120:1: twowaySQL returns [TwoWayQuery query] : nodelist ;
    public final TwoWaySqlParser.twowaySQL_return twowaySQL() throws RecognitionException {
        TwoWaySqlParser.twowaySQL_return retval = new TwoWaySqlParser.twowaySQL_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.nodelist_return nodelist1 = null;




        		push(EM_TOWWAYSQL);
        		retval.query = new TwoWayQuery();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:129:2: ( nodelist )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:129:4: nodelist
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_nodelist_in_twowaySQL98);
            nodelist1=nodelist();

            state._fsp--;

            adaptor.addChild(root_0, nodelist1.getTree());

            		retval.query.setChildren((nodelist1!=null?nodelist1.list:null));
            	

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.query.update(retval);
            		retval.query.freeze();
            	
        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {

            		pop();
            		coordinator.raise();
            	
        }
        return retval;
    }
    // $ANTLR end "twowaySQL"

    public static class nodelist_return extends ParserRuleReturnScope {
        public ArrayList<QueryNode> list;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "nodelist"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:138:1: nodelist returns [ArrayList<QueryNode> list] : ( comment | inbind | txt )+ ;
    public final TwoWaySqlParser.nodelist_return nodelist() throws RecognitionException {
        TwoWaySqlParser.nodelist_return retval = new TwoWaySqlParser.nodelist_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.comment_return comment2 = null;

        TwoWaySqlParser.inbind_return inbind3 = null;

        TwoWaySqlParser.txt_return txt4 = null;




        		retval.list = new ArrayList<QueryNode>();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:143:2: ( ( comment | inbind | txt )+ )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:144:2: ( comment | inbind | txt )+
            {
            root_0 = (CommonTree)adaptor.nil();

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:144:2: ( comment | inbind | txt )+
            int cnt1=0;
            loop1:
            do {
                int alt1=4;
                switch ( input.LA(1) ) {
                case C_ST:
                    {
                    int LA1_2 = input.LA(2);

                    if ( ((LA1_2>=IDENT && LA1_2<=SYM_RP)||LA1_2==IF||LA1_2==BEGIN) ) {
                        alt1=1;
                    }


                    }
                    break;
                case C_LN_ST:
                    {
                    int LA1_3 = input.LA(2);

                    if ( ((LA1_3>=IDENT && LA1_3<=SYM_RP)||LA1_3==BEGIN) ) {
                        alt1=1;
                    }


                    }
                    break;
                case IN:
                    {
                    alt1=2;
                    }
                    break;
                case IDENT:
                case QUOTED:
                case MAYBE_SKIP:
                case SYMBOLS:
                case SYM_BIND:
                case SYM_C:
                case SYM_LP:
                case SYM_RP:
                    {
                    alt1=3;
                    }
                    break;

                }

                switch (alt1) {
            	case 1 :
            	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:144:3: comment
            	    {
            	    pushFollow(FOLLOW_comment_in_nodelist128);
            	    comment2=comment();

            	    state._fsp--;

            	    adaptor.addChild(root_0, comment2.getTree());
            	    retval.list.add((comment2!=null?comment2.node:null));

            	    }
            	    break;
            	case 2 :
            	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:145:4: inbind
            	    {
            	    pushFollow(FOLLOW_inbind_in_nodelist135);
            	    inbind3=inbind();

            	    state._fsp--;

            	    adaptor.addChild(root_0, inbind3.getTree());
            	    retval.list.add((inbind3!=null?inbind3.node:null));

            	    }
            	    break;
            	case 3 :
            	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:146:4: txt
            	    {
            	    pushFollow(FOLLOW_txt_in_nodelist142);
            	    txt4=txt();

            	    state._fsp--;

            	    adaptor.addChild(root_0, txt4.getTree());
            	    retval.list.add((txt4!=null?txt4.node:null));

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


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "nodelist"

    public static class charactors_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "charactors"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:150:1: charactors : ( IDENT | QUOTED | MAYBE_SKIP | SYMBOLS | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+ ;
    public final TwoWaySqlParser.charactors_return charactors() throws RecognitionException {
        TwoWaySqlParser.charactors_return retval = new TwoWaySqlParser.charactors_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set5=null;

        CommonTree set5_tree=null;

        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:152:2: ( ( IDENT | QUOTED | MAYBE_SKIP | SYMBOLS | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+ )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:153:2: ( IDENT | QUOTED | MAYBE_SKIP | SYMBOLS | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+
            {
            root_0 = (CommonTree)adaptor.nil();

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:153:2: ( IDENT | QUOTED | MAYBE_SKIP | SYMBOLS | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=IDENT && LA2_0<=SYM_RP)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:
            	    {
            	    set5=(Token)input.LT(1);
            	    if ( (input.LA(1)>=IDENT && input.LA(1)<=SYM_RP) ) {
            	        input.consume();
            	        adaptor.addChild(root_0, (CommonTree)adaptor.create(set5));
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "charactors"

    public static class txt_return extends ParserRuleReturnScope {
        public TxtNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "txt"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:156:1: txt returns [TxtNode node] : charactors ;
    public final TwoWaySqlParser.txt_return txt() throws RecognitionException {
        TwoWaySqlParser.txt_return retval = new TwoWaySqlParser.txt_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.charactors_return charactors6 = null;




        		push(EM_TXT);
        		retval.node = new TxtNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:165:2: ( charactors )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:166:2: charactors
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_charactors_in_txt221);
            charactors6=charactors();

            state._fsp--;

            adaptor.addChild(root_0, charactors6.getTree());

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.node.update(retval);
            		retval.node.freeze();
            	
        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
             pop(); 
        }
        return retval;
    }
    // $ANTLR end "txt"

    public static class comment_return extends ParserRuleReturnScope {
        public QueryNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "comment"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:172:1: comment returns [QueryNode node] : ( begincomment | ifcomment | bindcomment | txtcomment );
    public final TwoWaySqlParser.comment_return comment() throws RecognitionException {
        TwoWaySqlParser.comment_return retval = new TwoWaySqlParser.comment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.begincomment_return begincomment7 = null;

        TwoWaySqlParser.ifcomment_return ifcomment8 = null;

        TwoWaySqlParser.bindcomment_return bindcomment9 = null;

        TwoWaySqlParser.txtcomment_return txtcomment10 = null;



        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:172:32: ( begincomment | ifcomment | bindcomment | txtcomment )
            int alt3=4;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:173:2: begincomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_begincomment_in_comment241);
                    begincomment7=begincomment();

                    state._fsp--;

                    adaptor.addChild(root_0, begincomment7.getTree());
                    retval.node = (begincomment7!=null?begincomment7.node:null);

                    }
                    break;
                case 2 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:174:4: ifcomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_ifcomment_in_comment248);
                    ifcomment8=ifcomment();

                    state._fsp--;

                    adaptor.addChild(root_0, ifcomment8.getTree());
                    retval.node = (ifcomment8!=null?ifcomment8.node:null);

                    }
                    break;
                case 3 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:175:4: bindcomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_bindcomment_in_comment255);
                    bindcomment9=bindcomment();

                    state._fsp--;

                    adaptor.addChild(root_0, bindcomment9.getTree());
                    retval.node = (bindcomment9!=null?bindcomment9.node:null);

                    }
                    break;
                case 4 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:176:4: txtcomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_txtcomment_in_comment262);
                    txtcomment10=txtcomment();

                    state._fsp--;

                    adaptor.addChild(root_0, txtcomment10.getTree());
                    retval.node = (txtcomment10!=null?txtcomment10.node:null);

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "comment"

    public static class txtcomment_return extends ParserRuleReturnScope {
        public TxtNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "txtcomment"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:179:1: txtcomment returns [TxtNode node] : ( blockcomment | linecomment );
    public final TwoWaySqlParser.txtcomment_return txtcomment() throws RecognitionException {
        TwoWaySqlParser.txtcomment_return retval = new TwoWaySqlParser.txtcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.blockcomment_return blockcomment11 = null;

        TwoWaySqlParser.linecomment_return linecomment12 = null;



        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:180:2: ( blockcomment | linecomment )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==C_ST) ) {
                alt4=1;
            }
            else if ( (LA4_0==C_LN_ST) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:180:4: blockcomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_blockcomment_in_txtcomment278);
                    blockcomment11=blockcomment();

                    state._fsp--;

                    adaptor.addChild(root_0, blockcomment11.getTree());
                    retval.node = (blockcomment11!=null?blockcomment11.node:null);

                    }
                    break;
                case 2 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:181:4: linecomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_linecomment_in_txtcomment285);
                    linecomment12=linecomment();

                    state._fsp--;

                    adaptor.addChild(root_0, linecomment12.getTree());
                    retval.node = (linecomment12!=null?linecomment12.node:null);

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "txtcomment"

    public static class blockcomment_return extends ParserRuleReturnScope {
        public TxtNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "blockcomment"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:184:1: blockcomment returns [TxtNode node] : C_ST charactors C_ED ;
    public final TwoWaySqlParser.blockcomment_return blockcomment() throws RecognitionException {
        TwoWaySqlParser.blockcomment_return retval = new TwoWaySqlParser.blockcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST13=null;
        Token C_ED15=null;
        TwoWaySqlParser.charactors_return charactors14 = null;


        CommonTree C_ST13_tree=null;
        CommonTree C_ED15_tree=null;


        		push(EM_BLOCKCOMMENT);
        		retval.node = new TxtNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:193:2: ( C_ST charactors C_ED )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:194:2: C_ST charactors C_ED
            {
            root_0 = (CommonTree)adaptor.nil();

            C_ST13=(Token)match(input,C_ST,FOLLOW_C_ST_in_blockcomment315); 
            C_ST13_tree = (CommonTree)adaptor.create(C_ST13);
            adaptor.addChild(root_0, C_ST13_tree);

            pushFollow(FOLLOW_charactors_in_blockcomment317);
            charactors14=charactors();

            state._fsp--;

            adaptor.addChild(root_0, charactors14.getTree());
            C_ED15=(Token)match(input,C_ED,FOLLOW_C_ED_in_blockcomment319); 
            C_ED15_tree = (CommonTree)adaptor.create(C_ED15);
            adaptor.addChild(root_0, C_ED15_tree);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.node.update(retval);
            		retval.node.freeze();
            	
        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
             pop(); 
        }
        return retval;
    }
    // $ANTLR end "blockcomment"

    public static class linecomment_return extends ParserRuleReturnScope {
        public TxtNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "linecomment"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:198:1: linecomment returns [TxtNode node] : C_LN_ST charactors C_LN_ED ;
    public final TwoWaySqlParser.linecomment_return linecomment() throws RecognitionException {
        TwoWaySqlParser.linecomment_return retval = new TwoWaySqlParser.linecomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_LN_ST16=null;
        Token C_LN_ED18=null;
        TwoWaySqlParser.charactors_return charactors17 = null;


        CommonTree C_LN_ST16_tree=null;
        CommonTree C_LN_ED18_tree=null;


        		push(EM_LINECOMMENT);
        		retval.node = new TxtNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:207:2: ( C_LN_ST charactors C_LN_ED )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:208:2: C_LN_ST charactors C_LN_ED
            {
            root_0 = (CommonTree)adaptor.nil();

            C_LN_ST16=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_linecomment351); 
            C_LN_ST16_tree = (CommonTree)adaptor.create(C_LN_ST16);
            adaptor.addChild(root_0, C_LN_ST16_tree);

            pushFollow(FOLLOW_charactors_in_linecomment353);
            charactors17=charactors();

            state._fsp--;

            adaptor.addChild(root_0, charactors17.getTree());
            C_LN_ED18=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_linecomment355); 
            C_LN_ED18_tree = (CommonTree)adaptor.create(C_LN_ED18);
            adaptor.addChild(root_0, C_LN_ED18_tree);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.node.update(retval);
            		retval.node.freeze();
            	
        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
             pop(); 
        }
        return retval;
    }
    // $ANTLR end "linecomment"

    public static class ifcomment_return extends ParserRuleReturnScope {
        public IfNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ifcomment"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:212:1: ifcomment returns [IfNode node] : ( C_ST IF expression C_ED ( MAYBE_SKIP )? nodelist ( elseifnode )* ( elsenode )? endcomment ) ;
    public final TwoWaySqlParser.ifcomment_return ifcomment() throws RecognitionException {
        TwoWaySqlParser.ifcomment_return retval = new TwoWaySqlParser.ifcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST19=null;
        Token IF20=null;
        Token C_ED22=null;
        Token MAYBE_SKIP23=null;
        TwoWaySqlParser.expression_return expression21 = null;

        TwoWaySqlParser.nodelist_return nodelist24 = null;

        TwoWaySqlParser.elseifnode_return elseifnode25 = null;

        TwoWaySqlParser.elsenode_return elsenode26 = null;

        TwoWaySqlParser.endcomment_return endcomment27 = null;


        CommonTree C_ST19_tree=null;
        CommonTree IF20_tree=null;
        CommonTree C_ED22_tree=null;
        CommonTree MAYBE_SKIP23_tree=null;


        		push(EM_IFCOMMENT);
        		retval.node = new IfNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:221:2: ( ( C_ST IF expression C_ED ( MAYBE_SKIP )? nodelist ( elseifnode )* ( elsenode )? endcomment ) )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:222:2: ( C_ST IF expression C_ED ( MAYBE_SKIP )? nodelist ( elseifnode )* ( elsenode )? endcomment )
            {
            root_0 = (CommonTree)adaptor.nil();

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:222:2: ( C_ST IF expression C_ED ( MAYBE_SKIP )? nodelist ( elseifnode )* ( elsenode )? endcomment )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:222:3: C_ST IF expression C_ED ( MAYBE_SKIP )? nodelist ( elseifnode )* ( elsenode )? endcomment
            {
            C_ST19=(Token)match(input,C_ST,FOLLOW_C_ST_in_ifcomment388); 
            C_ST19_tree = (CommonTree)adaptor.create(C_ST19);
            adaptor.addChild(root_0, C_ST19_tree);

            IF20=(Token)match(input,IF,FOLLOW_IF_in_ifcomment390); 
            IF20_tree = (CommonTree)adaptor.create(IF20);
            adaptor.addChild(root_0, IF20_tree);

            pushFollow(FOLLOW_expression_in_ifcomment392);
            expression21=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression21.getTree());
            C_ED22=(Token)match(input,C_ED,FOLLOW_C_ED_in_ifcomment394); 
            C_ED22_tree = (CommonTree)adaptor.create(C_ED22);
            adaptor.addChild(root_0, C_ED22_tree);

             retval.node.setExpression((expression21!=null?expression21.node:null)); 
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:223:3: ( MAYBE_SKIP )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==MAYBE_SKIP) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:223:4: MAYBE_SKIP
                    {
                    MAYBE_SKIP23=(Token)match(input,MAYBE_SKIP,FOLLOW_MAYBE_SKIP_in_ifcomment401); 
                    MAYBE_SKIP23_tree = (CommonTree)adaptor.create(MAYBE_SKIP23);
                    adaptor.addChild(root_0, MAYBE_SKIP23_tree);


                    			TxtNode skip = new TxtNode();
                    			skip.update(MAYBE_SKIP23);
                    			skip.freeze();
                    			retval.node.setMaybeSkip(skip);
                    		

                    }
                    break;

            }

            pushFollow(FOLLOW_nodelist_in_ifcomment410);
            nodelist24=nodelist();

            state._fsp--;

            adaptor.addChild(root_0, nodelist24.getTree());
             retval.node.setChildren((nodelist24!=null?nodelist24.list:null));
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:230:3: ( elseifnode )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==C_ST) ) {
                    int LA6_1 = input.LA(2);

                    if ( (LA6_1==ELSEIF) ) {
                        alt6=1;
                    }


                }
                else if ( (LA6_0==C_LN_ST) ) {
                    int LA6_2 = input.LA(2);

                    if ( (LA6_2==ELSEIF) ) {
                        alt6=1;
                    }


                }


                switch (alt6) {
            	case 1 :
            	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:230:4: elseifnode
            	    {
            	    pushFollow(FOLLOW_elseifnode_in_ifcomment417);
            	    elseifnode25=elseifnode();

            	    state._fsp--;

            	    adaptor.addChild(root_0, elseifnode25.getTree());
            	     retval.node.addElseIf((elseifnode25!=null?elseifnode25.node:null)); 

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:231:3: ( elsenode )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==C_ST) ) {
                int LA7_1 = input.LA(2);

                if ( (LA7_1==ELSE) ) {
                    alt7=1;
                }
            }
            else if ( (LA7_0==C_LN_ST) ) {
                int LA7_2 = input.LA(2);

                if ( (LA7_2==ELSE) ) {
                    alt7=1;
                }
            }
            switch (alt7) {
                case 1 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:231:4: elsenode
                    {
                    pushFollow(FOLLOW_elsenode_in_ifcomment427);
                    elsenode26=elsenode();

                    state._fsp--;

                    adaptor.addChild(root_0, elsenode26.getTree());
                     retval.node.setElse((elsenode26!=null?elsenode26.node:null)); 

                    }
                    break;

            }

            pushFollow(FOLLOW_endcomment_in_ifcomment435);
            endcomment27=endcomment();

            state._fsp--;

            adaptor.addChild(root_0, endcomment27.getTree());

            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.node.update(retval);
            		retval.node.freeze();
            	
        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
             pop(); 
        }
        return retval;
    }
    // $ANTLR end "ifcomment"

    public static class elseifnode_return extends ParserRuleReturnScope {
        public IfNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elseifnode"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:237:1: elseifnode returns [IfNode node] : elseifcomment ( MAYBE_SKIP )? nodelist ;
    public final TwoWaySqlParser.elseifnode_return elseifnode() throws RecognitionException {
        TwoWaySqlParser.elseifnode_return retval = new TwoWaySqlParser.elseifnode_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token MAYBE_SKIP29=null;
        TwoWaySqlParser.elseifcomment_return elseifcomment28 = null;

        TwoWaySqlParser.nodelist_return nodelist30 = null;


        CommonTree MAYBE_SKIP29_tree=null;


        		push(EM_ELSEIFNODE);
        		retval.node = new IfNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:246:2: ( elseifcomment ( MAYBE_SKIP )? nodelist )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:247:2: elseifcomment ( MAYBE_SKIP )? nodelist
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_elseifcomment_in_elseifnode471);
            elseifcomment28=elseifcomment();

            state._fsp--;

            adaptor.addChild(root_0, elseifcomment28.getTree());
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:248:2: ( MAYBE_SKIP )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==MAYBE_SKIP) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:248:3: MAYBE_SKIP
                    {
                    MAYBE_SKIP29=(Token)match(input,MAYBE_SKIP,FOLLOW_MAYBE_SKIP_in_elseifnode475); 
                    MAYBE_SKIP29_tree = (CommonTree)adaptor.create(MAYBE_SKIP29);
                    adaptor.addChild(root_0, MAYBE_SKIP29_tree);


                    		TxtNode skip = new TxtNode();
                    		skip.update(MAYBE_SKIP29);
                    		skip.freeze();
                    		retval.node.setMaybeSkip(skip);
                    	

                    }
                    break;

            }

            pushFollow(FOLLOW_nodelist_in_elseifnode484);
            nodelist30=nodelist();

            state._fsp--;

            adaptor.addChild(root_0, nodelist30.getTree());

            		retval.node.setExpression((elseifcomment28!=null?elseifcomment28.node:null));
            		retval.node.setChildren((nodelist30!=null?nodelist30.list:null));
            	

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.node.update(retval);
            		retval.node.freeze();
            	
        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
             pop(); 
        }
        return retval;
    }
    // $ANTLR end "elseifnode"

    public static class elseifcomment_return extends ParserRuleReturnScope {
        public ExpressionNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elseifcomment"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:263:1: elseifcomment returns [ExpressionNode node] : ( elseifblockcomment | elseiflinecomment ) ;
    public final TwoWaySqlParser.elseifcomment_return elseifcomment() throws RecognitionException {
        TwoWaySqlParser.elseifcomment_return retval = new TwoWaySqlParser.elseifcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.elseifblockcomment_return elseifblockcomment31 = null;

        TwoWaySqlParser.elseiflinecomment_return elseiflinecomment32 = null;



        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:265:2: ( ( elseifblockcomment | elseiflinecomment ) )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:266:2: ( elseifblockcomment | elseiflinecomment )
            {
            root_0 = (CommonTree)adaptor.nil();

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:266:2: ( elseifblockcomment | elseiflinecomment )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==C_ST) ) {
                alt9=1;
            }
            else if ( (LA9_0==C_LN_ST) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:266:3: elseifblockcomment
                    {
                    pushFollow(FOLLOW_elseifblockcomment_in_elseifcomment511);
                    elseifblockcomment31=elseifblockcomment();

                    state._fsp--;

                    adaptor.addChild(root_0, elseifblockcomment31.getTree());
                    retval.node = (elseifblockcomment31!=null?elseifblockcomment31.node:null);

                    }
                    break;
                case 2 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:267:5: elseiflinecomment
                    {
                    pushFollow(FOLLOW_elseiflinecomment_in_elseifcomment519);
                    elseiflinecomment32=elseiflinecomment();

                    state._fsp--;

                    adaptor.addChild(root_0, elseiflinecomment32.getTree());
                    retval.node = (elseiflinecomment32!=null?elseiflinecomment32.node:null);

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "elseifcomment"

    public static class elseifblockcomment_return extends ParserRuleReturnScope {
        public ExpressionNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elseifblockcomment"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:271:1: elseifblockcomment returns [ExpressionNode node] : C_ST ELSEIF expression C_ED ;
    public final TwoWaySqlParser.elseifblockcomment_return elseifblockcomment() throws RecognitionException {
        TwoWaySqlParser.elseifblockcomment_return retval = new TwoWaySqlParser.elseifblockcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST33=null;
        Token ELSEIF34=null;
        Token C_ED36=null;
        TwoWaySqlParser.expression_return expression35 = null;


        CommonTree C_ST33_tree=null;
        CommonTree ELSEIF34_tree=null;
        CommonTree C_ED36_tree=null;


        		push(EM_ELSEIFBLOCKCOMMENT);
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:275:2: ( C_ST ELSEIF expression C_ED )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:275:4: C_ST ELSEIF expression C_ED
            {
            root_0 = (CommonTree)adaptor.nil();

            C_ST33=(Token)match(input,C_ST,FOLLOW_C_ST_in_elseifblockcomment544); 
            C_ST33_tree = (CommonTree)adaptor.create(C_ST33);
            adaptor.addChild(root_0, C_ST33_tree);

            ELSEIF34=(Token)match(input,ELSEIF,FOLLOW_ELSEIF_in_elseifblockcomment546); 
            ELSEIF34_tree = (CommonTree)adaptor.create(ELSEIF34);
            adaptor.addChild(root_0, ELSEIF34_tree);

            pushFollow(FOLLOW_expression_in_elseifblockcomment548);
            expression35=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression35.getTree());
            C_ED36=(Token)match(input,C_ED,FOLLOW_C_ED_in_elseifblockcomment550); 
            C_ED36_tree = (CommonTree)adaptor.create(C_ED36);
            adaptor.addChild(root_0, C_ED36_tree);

             retval.node = (expression35!=null?expression35.node:null); 

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
             pop(); 
        }
        return retval;
    }
    // $ANTLR end "elseifblockcomment"

    public static class elseiflinecomment_return extends ParserRuleReturnScope {
        public ExpressionNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elseiflinecomment"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:279:1: elseiflinecomment returns [ExpressionNode node] : C_LN_ST ELSEIF expression C_LN_ED ;
    public final TwoWaySqlParser.elseiflinecomment_return elseiflinecomment() throws RecognitionException {
        TwoWaySqlParser.elseiflinecomment_return retval = new TwoWaySqlParser.elseiflinecomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_LN_ST37=null;
        Token ELSEIF38=null;
        Token C_LN_ED40=null;
        TwoWaySqlParser.expression_return expression39 = null;


        CommonTree C_LN_ST37_tree=null;
        CommonTree ELSEIF38_tree=null;
        CommonTree C_LN_ED40_tree=null;


        		push(EM_ELSEIFLINECOMMENT);
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:283:2: ( C_LN_ST ELSEIF expression C_LN_ED )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:283:4: C_LN_ST ELSEIF expression C_LN_ED
            {
            root_0 = (CommonTree)adaptor.nil();

            C_LN_ST37=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_elseiflinecomment577); 
            C_LN_ST37_tree = (CommonTree)adaptor.create(C_LN_ST37);
            adaptor.addChild(root_0, C_LN_ST37_tree);

            ELSEIF38=(Token)match(input,ELSEIF,FOLLOW_ELSEIF_in_elseiflinecomment579); 
            ELSEIF38_tree = (CommonTree)adaptor.create(ELSEIF38);
            adaptor.addChild(root_0, ELSEIF38_tree);

            pushFollow(FOLLOW_expression_in_elseiflinecomment581);
            expression39=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression39.getTree());
            C_LN_ED40=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_elseiflinecomment583); 
            C_LN_ED40_tree = (CommonTree)adaptor.create(C_LN_ED40);
            adaptor.addChild(root_0, C_LN_ED40_tree);

             retval.node = (expression39!=null?expression39.node:null); 

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
             pop(); 
        }
        return retval;
    }
    // $ANTLR end "elseiflinecomment"

    public static class elsenode_return extends ParserRuleReturnScope {
        public ElseNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elsenode"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:287:1: elsenode returns [ElseNode node] : elsecomment ( MAYBE_SKIP )? nodelist ;
    public final TwoWaySqlParser.elsenode_return elsenode() throws RecognitionException {
        TwoWaySqlParser.elsenode_return retval = new TwoWaySqlParser.elsenode_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token MAYBE_SKIP42=null;
        TwoWaySqlParser.elsecomment_return elsecomment41 = null;

        TwoWaySqlParser.nodelist_return nodelist43 = null;


        CommonTree MAYBE_SKIP42_tree=null;


        		push(EM_ELSENODE);
        		retval.node = new ElseNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:296:2: ( elsecomment ( MAYBE_SKIP )? nodelist )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:297:2: elsecomment ( MAYBE_SKIP )? nodelist
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_elsecomment_in_elsenode617);
            elsecomment41=elsecomment();

            state._fsp--;

            adaptor.addChild(root_0, elsecomment41.getTree());
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:298:2: ( MAYBE_SKIP )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==MAYBE_SKIP) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:298:3: MAYBE_SKIP
                    {
                    MAYBE_SKIP42=(Token)match(input,MAYBE_SKIP,FOLLOW_MAYBE_SKIP_in_elsenode621); 
                    MAYBE_SKIP42_tree = (CommonTree)adaptor.create(MAYBE_SKIP42);
                    adaptor.addChild(root_0, MAYBE_SKIP42_tree);


                    		TxtNode skip = new TxtNode();
                    		skip.update(MAYBE_SKIP42);
                    		skip.freeze();
                    		retval.node.setMaybeSkip(skip);
                    	

                    }
                    break;

            }

            pushFollow(FOLLOW_nodelist_in_elsenode631);
            nodelist43=nodelist();

            state._fsp--;

            adaptor.addChild(root_0, nodelist43.getTree());
             retval.node.setChildren((nodelist43!=null?nodelist43.list:null));

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.node.update(retval);
            		retval.node.freeze();
            	
        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
             pop(); 
        }
        return retval;
    }
    // $ANTLR end "elsenode"

    public static class elsecomment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elsecomment"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:310:1: elsecomment : ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED ) ;
    public final TwoWaySqlParser.elsecomment_return elsecomment() throws RecognitionException {
        TwoWaySqlParser.elsecomment_return retval = new TwoWaySqlParser.elsecomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST44=null;
        Token ELSE45=null;
        Token C_ED46=null;
        Token C_LN_ST47=null;
        Token ELSE48=null;
        Token C_LN_ED49=null;

        CommonTree C_ST44_tree=null;
        CommonTree ELSE45_tree=null;
        CommonTree C_ED46_tree=null;
        CommonTree C_LN_ST47_tree=null;
        CommonTree ELSE48_tree=null;
        CommonTree C_LN_ED49_tree=null;


        		push(EM_ELSECOMMENT);
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:314:2: ( ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED ) )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:315:2: ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED )
            {
            root_0 = (CommonTree)adaptor.nil();

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:315:2: ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==C_ST) ) {
                alt11=1;
            }
            else if ( (LA11_0==C_LN_ST) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:315:3: C_ST ELSE C_ED
                    {
                    C_ST44=(Token)match(input,C_ST,FOLLOW_C_ST_in_elsecomment658); 
                    C_ST44_tree = (CommonTree)adaptor.create(C_ST44);
                    adaptor.addChild(root_0, C_ST44_tree);

                    ELSE45=(Token)match(input,ELSE,FOLLOW_ELSE_in_elsecomment660); 
                    ELSE45_tree = (CommonTree)adaptor.create(ELSE45);
                    adaptor.addChild(root_0, ELSE45_tree);

                    C_ED46=(Token)match(input,C_ED,FOLLOW_C_ED_in_elsecomment662); 
                    C_ED46_tree = (CommonTree)adaptor.create(C_ED46);
                    adaptor.addChild(root_0, C_ED46_tree);


                    }
                    break;
                case 2 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:315:20: C_LN_ST ELSE C_LN_ED
                    {
                    C_LN_ST47=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_elsecomment666); 
                    C_LN_ST47_tree = (CommonTree)adaptor.create(C_LN_ST47);
                    adaptor.addChild(root_0, C_LN_ST47_tree);

                    ELSE48=(Token)match(input,ELSE,FOLLOW_ELSE_in_elsecomment668); 
                    ELSE48_tree = (CommonTree)adaptor.create(ELSE48);
                    adaptor.addChild(root_0, ELSE48_tree);

                    C_LN_ED49=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_elsecomment670); 
                    C_LN_ED49_tree = (CommonTree)adaptor.create(C_LN_ED49);
                    adaptor.addChild(root_0, C_LN_ED49_tree);


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
             pop(); 
        }
        return retval;
    }
    // $ANTLR end "elsecomment"

    public static class expression_return extends ParserRuleReturnScope {
        public ExpressionNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expression"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:319:1: expression returns [ExpressionNode node] : charactors ;
    public final TwoWaySqlParser.expression_return expression() throws RecognitionException {
        TwoWaySqlParser.expression_return retval = new TwoWaySqlParser.expression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.charactors_return charactors50 = null;




        		push(EM_EXPRESSION);
        		retval.node = new ExpressionNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:328:2: ( charactors )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:329:2: charactors
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_charactors_in_expression703);
            charactors50=charactors();

            state._fsp--;

            adaptor.addChild(root_0, charactors50.getTree());

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.node.update(retval);
            		retval.node.freeze();
            	
        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
             pop(); 
        }
        return retval;
    }
    // $ANTLR end "expression"

    public static class begincomment_return extends ParserRuleReturnScope {
        public BeginNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "begincomment"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:333:1: begincomment returns [BeginNode node] : ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) nodelist endcomment ) ;
    public final TwoWaySqlParser.begincomment_return begincomment() throws RecognitionException {
        TwoWaySqlParser.begincomment_return retval = new TwoWaySqlParser.begincomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST51=null;
        Token BEGIN52=null;
        Token C_ED53=null;
        Token C_LN_ST54=null;
        Token BEGIN55=null;
        Token C_LN_ED56=null;
        TwoWaySqlParser.nodelist_return nodelist57 = null;

        TwoWaySqlParser.endcomment_return endcomment58 = null;


        CommonTree C_ST51_tree=null;
        CommonTree BEGIN52_tree=null;
        CommonTree C_ED53_tree=null;
        CommonTree C_LN_ST54_tree=null;
        CommonTree BEGIN55_tree=null;
        CommonTree C_LN_ED56_tree=null;


        		push(EM_BEGINCOMMENT);
        		retval.node = new BeginNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:342:2: ( ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) nodelist endcomment ) )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:343:2: ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) nodelist endcomment )
            {
            root_0 = (CommonTree)adaptor.nil();

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:343:2: ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) nodelist endcomment )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:344:3: ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) nodelist endcomment
            {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:344:3: ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==C_ST) ) {
                alt12=1;
            }
            else if ( (LA12_0==C_LN_ST) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:344:4: C_ST BEGIN C_ED
                    {
                    C_ST51=(Token)match(input,C_ST,FOLLOW_C_ST_in_begincomment741); 
                    C_ST51_tree = (CommonTree)adaptor.create(C_ST51);
                    adaptor.addChild(root_0, C_ST51_tree);

                    BEGIN52=(Token)match(input,BEGIN,FOLLOW_BEGIN_in_begincomment743); 
                    BEGIN52_tree = (CommonTree)adaptor.create(BEGIN52);
                    adaptor.addChild(root_0, BEGIN52_tree);

                    C_ED53=(Token)match(input,C_ED,FOLLOW_C_ED_in_begincomment745); 
                    C_ED53_tree = (CommonTree)adaptor.create(C_ED53);
                    adaptor.addChild(root_0, C_ED53_tree);


                    }
                    break;
                case 2 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:344:22: C_LN_ST BEGIN C_LN_ED
                    {
                    C_LN_ST54=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_begincomment749); 
                    C_LN_ST54_tree = (CommonTree)adaptor.create(C_LN_ST54);
                    adaptor.addChild(root_0, C_LN_ST54_tree);

                    BEGIN55=(Token)match(input,BEGIN,FOLLOW_BEGIN_in_begincomment751); 
                    BEGIN55_tree = (CommonTree)adaptor.create(BEGIN55);
                    adaptor.addChild(root_0, BEGIN55_tree);

                    C_LN_ED56=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_begincomment753); 
                    C_LN_ED56_tree = (CommonTree)adaptor.create(C_LN_ED56);
                    adaptor.addChild(root_0, C_LN_ED56_tree);


                    }
                    break;

            }

            pushFollow(FOLLOW_nodelist_in_begincomment756);
            nodelist57=nodelist();

            state._fsp--;

            adaptor.addChild(root_0, nodelist57.getTree());
            pushFollow(FOLLOW_endcomment_in_begincomment758);
            endcomment58=endcomment();

            state._fsp--;

            adaptor.addChild(root_0, endcomment58.getTree());

            }


            		retval.node.setChildren((nodelist57!=null?nodelist57.list:null));
            	

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.node.update(retval);
            		retval.node.freeze();
            	
        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
             pop(); 
        }
        return retval;
    }
    // $ANTLR end "begincomment"

    public static class endcomment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "endcomment"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:352:1: endcomment : ( C_ST END C_ED | C_LN_ST END C_LN_ED );
    public final TwoWaySqlParser.endcomment_return endcomment() throws RecognitionException {
        TwoWaySqlParser.endcomment_return retval = new TwoWaySqlParser.endcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST59=null;
        Token END60=null;
        Token C_ED61=null;
        Token C_LN_ST62=null;
        Token END63=null;
        Token C_LN_ED64=null;

        CommonTree C_ST59_tree=null;
        CommonTree END60_tree=null;
        CommonTree C_ED61_tree=null;
        CommonTree C_LN_ST62_tree=null;
        CommonTree END63_tree=null;
        CommonTree C_LN_ED64_tree=null;


        		push(EM_ENDCOMMENT);
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:356:2: ( C_ST END C_ED | C_LN_ST END C_LN_ED )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==C_ST) ) {
                alt13=1;
            }
            else if ( (LA13_0==C_LN_ST) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:357:2: C_ST END C_ED
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    C_ST59=(Token)match(input,C_ST,FOLLOW_C_ST_in_endcomment787); 
                    C_ST59_tree = (CommonTree)adaptor.create(C_ST59);
                    adaptor.addChild(root_0, C_ST59_tree);

                    END60=(Token)match(input,END,FOLLOW_END_in_endcomment789); 
                    END60_tree = (CommonTree)adaptor.create(END60);
                    adaptor.addChild(root_0, END60_tree);

                    C_ED61=(Token)match(input,C_ED,FOLLOW_C_ED_in_endcomment791); 
                    C_ED61_tree = (CommonTree)adaptor.create(C_ED61);
                    adaptor.addChild(root_0, C_ED61_tree);


                    }
                    break;
                case 2 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:357:18: C_LN_ST END C_LN_ED
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    C_LN_ST62=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_endcomment795); 
                    C_LN_ST62_tree = (CommonTree)adaptor.create(C_LN_ST62);
                    adaptor.addChild(root_0, C_LN_ST62_tree);

                    END63=(Token)match(input,END,FOLLOW_END_in_endcomment797); 
                    END63_tree = (CommonTree)adaptor.create(END63);
                    adaptor.addChild(root_0, END63_tree);

                    C_LN_ED64=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_endcomment799); 
                    C_LN_ED64_tree = (CommonTree)adaptor.create(C_LN_ED64);
                    adaptor.addChild(root_0, C_LN_ED64_tree);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
             pop(); 
        }
        return retval;
    }
    // $ANTLR end "endcomment"

    public static class bindcomment_return extends ParserRuleReturnScope {
        public BindNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "bindcomment"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:361:1: bindcomment returns [BindNode node] : ( C_ST ( bindingname )? SYM_BIND expression C_ED bindchars ) ;
    public final TwoWaySqlParser.bindcomment_return bindcomment() throws RecognitionException {
        TwoWaySqlParser.bindcomment_return retval = new TwoWaySqlParser.bindcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST65=null;
        Token SYM_BIND67=null;
        Token C_ED69=null;
        TwoWaySqlParser.bindingname_return bindingname66 = null;

        TwoWaySqlParser.expression_return expression68 = null;

        TwoWaySqlParser.bindchars_return bindchars70 = null;


        CommonTree C_ST65_tree=null;
        CommonTree SYM_BIND67_tree=null;
        CommonTree C_ED69_tree=null;


        		push(EM_BINDCOMMENT);
        		retval.node = new BindNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:370:2: ( ( C_ST ( bindingname )? SYM_BIND expression C_ED bindchars ) )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:371:2: ( C_ST ( bindingname )? SYM_BIND expression C_ED bindchars )
            {
            root_0 = (CommonTree)adaptor.nil();

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:371:2: ( C_ST ( bindingname )? SYM_BIND expression C_ED bindchars )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:371:3: C_ST ( bindingname )? SYM_BIND expression C_ED bindchars
            {
            C_ST65=(Token)match(input,C_ST,FOLLOW_C_ST_in_bindcomment832); 
            C_ST65_tree = (CommonTree)adaptor.create(C_ST65);
            adaptor.addChild(root_0, C_ST65_tree);

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:372:3: ( bindingname )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==SYM_BIND) ) {
                int LA14_1 = input.LA(2);

                if ( (LA14_1==IDENT) ) {
                    int LA14_2 = input.LA(3);

                    if ( (LA14_2==SYM_BIND) ) {
                        alt14=1;
                    }
                }
            }
            switch (alt14) {
                case 1 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:372:4: bindingname
                    {
                    pushFollow(FOLLOW_bindingname_in_bindcomment838);
                    bindingname66=bindingname();

                    state._fsp--;

                    adaptor.addChild(root_0, bindingname66.getTree());
                    retval.node.setBindingName((bindingname66!=null?bindingname66.node:null)); 

                    }
                    break;

            }

            SYM_BIND67=(Token)match(input,SYM_BIND,FOLLOW_SYM_BIND_in_bindcomment846); 
            SYM_BIND67_tree = (CommonTree)adaptor.create(SYM_BIND67);
            adaptor.addChild(root_0, SYM_BIND67_tree);

            pushFollow(FOLLOW_expression_in_bindcomment848);
            expression68=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression68.getTree());
            C_ED69=(Token)match(input,C_ED,FOLLOW_C_ED_in_bindcomment850); 
            C_ED69_tree = (CommonTree)adaptor.create(C_ED69);
            adaptor.addChild(root_0, C_ED69_tree);

            pushFollow(FOLLOW_bindchars_in_bindcomment852);
            bindchars70=bindchars();

            state._fsp--;

            adaptor.addChild(root_0, bindchars70.getTree());

            }


            		retval.node.setExpression((expression68!=null?expression68.node:null));
            		retval.node.setSkipped((bindchars70!=null?bindchars70.node:null));
            	

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.node.update(retval);
            		retval.node.freeze();
            	
        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
             pop(); 
        }
        return retval;
    }
    // $ANTLR end "bindcomment"

    public static class bindingname_return extends ParserRuleReturnScope {
        public TxtNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "bindingname"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:381:1: bindingname returns [TxtNode node] : ( SYM_BIND IDENT ) ;
    public final TwoWaySqlParser.bindingname_return bindingname() throws RecognitionException {
        TwoWaySqlParser.bindingname_return retval = new TwoWaySqlParser.bindingname_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token SYM_BIND71=null;
        Token IDENT72=null;

        CommonTree SYM_BIND71_tree=null;
        CommonTree IDENT72_tree=null;


        		push(EM_BINDINGNAME);
        		retval.node = new TxtNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:389:2: ( ( SYM_BIND IDENT ) )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:390:2: ( SYM_BIND IDENT )
            {
            root_0 = (CommonTree)adaptor.nil();

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:390:2: ( SYM_BIND IDENT )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:390:3: SYM_BIND IDENT
            {
            SYM_BIND71=(Token)match(input,SYM_BIND,FOLLOW_SYM_BIND_in_bindingname889); 
            SYM_BIND71_tree = (CommonTree)adaptor.create(SYM_BIND71);
            adaptor.addChild(root_0, SYM_BIND71_tree);

            IDENT72=(Token)match(input,IDENT,FOLLOW_IDENT_in_bindingname891); 
            IDENT72_tree = (CommonTree)adaptor.create(IDENT72);
            adaptor.addChild(root_0, IDENT72_tree);

             retval.node.update(IDENT72); 

            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.node.freeze();
            	
        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
             pop(); 
        }
        return retval;
    }
    // $ANTLR end "bindingname"

    public static class inbind_return extends ParserRuleReturnScope {
        public InBindNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "inbind"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:395:1: inbind returns [InBindNode node] : IN C_ST ( bindingname )? SYM_BIND expression C_ED inbindskipped ;
    public final TwoWaySqlParser.inbind_return inbind() throws RecognitionException {
        TwoWaySqlParser.inbind_return retval = new TwoWaySqlParser.inbind_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IN73=null;
        Token C_ST74=null;
        Token SYM_BIND76=null;
        Token C_ED78=null;
        TwoWaySqlParser.bindingname_return bindingname75 = null;

        TwoWaySqlParser.expression_return expression77 = null;

        TwoWaySqlParser.inbindskipped_return inbindskipped79 = null;


        CommonTree IN73_tree=null;
        CommonTree C_ST74_tree=null;
        CommonTree SYM_BIND76_tree=null;
        CommonTree C_ED78_tree=null;


        		push(EM_INBIND);
        		retval.node = new InBindNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:404:2: ( IN C_ST ( bindingname )? SYM_BIND expression C_ED inbindskipped )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:405:2: IN C_ST ( bindingname )? SYM_BIND expression C_ED inbindskipped
            {
            root_0 = (CommonTree)adaptor.nil();

            IN73=(Token)match(input,IN,FOLLOW_IN_in_inbind927); 
            IN73_tree = (CommonTree)adaptor.create(IN73);
            adaptor.addChild(root_0, IN73_tree);

            C_ST74=(Token)match(input,C_ST,FOLLOW_C_ST_in_inbind929); 
            C_ST74_tree = (CommonTree)adaptor.create(C_ST74);
            adaptor.addChild(root_0, C_ST74_tree);

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:406:3: ( bindingname )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==SYM_BIND) ) {
                int LA15_1 = input.LA(2);

                if ( (LA15_1==IDENT) ) {
                    int LA15_2 = input.LA(3);

                    if ( (LA15_2==SYM_BIND) ) {
                        alt15=1;
                    }
                }
            }
            switch (alt15) {
                case 1 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:406:4: bindingname
                    {
                    pushFollow(FOLLOW_bindingname_in_inbind935);
                    bindingname75=bindingname();

                    state._fsp--;

                    adaptor.addChild(root_0, bindingname75.getTree());
                    retval.node.setBindingName((bindingname75!=null?bindingname75.node:null)); 

                    }
                    break;

            }

            SYM_BIND76=(Token)match(input,SYM_BIND,FOLLOW_SYM_BIND_in_inbind944); 
            SYM_BIND76_tree = (CommonTree)adaptor.create(SYM_BIND76);
            adaptor.addChild(root_0, SYM_BIND76_tree);

            pushFollow(FOLLOW_expression_in_inbind946);
            expression77=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression77.getTree());
            C_ED78=(Token)match(input,C_ED,FOLLOW_C_ED_in_inbind948); 
            C_ED78_tree = (CommonTree)adaptor.create(C_ED78);
            adaptor.addChild(root_0, C_ED78_tree);

            pushFollow(FOLLOW_inbindskipped_in_inbind950);
            inbindskipped79=inbindskipped();

            state._fsp--;

            adaptor.addChild(root_0, inbindskipped79.getTree());

            		retval.node.setExpression((expression77!=null?expression77.node:null));
            		retval.node.setSkipped((inbindskipped79!=null?inbindskipped79.node:null));
            	

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.node.update(retval);
            		retval.node.freeze();
            	
        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
             pop(); 
        }
        return retval;
    }
    // $ANTLR end "inbind"

    public static class inbindskipped_return extends ParserRuleReturnScope {
        public TxtNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "inbindskipped"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:415:1: inbindskipped returns [TxtNode node] : SYM_LP ( txtcomment )* bindchars ( txtcomment )* ( SYM_C ( txtcomment )* bindchars ( txtcomment )* )* SYM_RP ;
    public final TwoWaySqlParser.inbindskipped_return inbindskipped() throws RecognitionException {
        TwoWaySqlParser.inbindskipped_return retval = new TwoWaySqlParser.inbindskipped_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token SYM_LP80=null;
        Token SYM_C84=null;
        Token SYM_RP88=null;
        TwoWaySqlParser.txtcomment_return txtcomment81 = null;

        TwoWaySqlParser.bindchars_return bindchars82 = null;

        TwoWaySqlParser.txtcomment_return txtcomment83 = null;

        TwoWaySqlParser.txtcomment_return txtcomment85 = null;

        TwoWaySqlParser.bindchars_return bindchars86 = null;

        TwoWaySqlParser.txtcomment_return txtcomment87 = null;


        CommonTree SYM_LP80_tree=null;
        CommonTree SYM_C84_tree=null;
        CommonTree SYM_RP88_tree=null;


        		push(EM_INBINDSKIPPED);
        		retval.node = new TxtNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:424:2: ( SYM_LP ( txtcomment )* bindchars ( txtcomment )* ( SYM_C ( txtcomment )* bindchars ( txtcomment )* )* SYM_RP )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:425:2: SYM_LP ( txtcomment )* bindchars ( txtcomment )* ( SYM_C ( txtcomment )* bindchars ( txtcomment )* )* SYM_RP
            {
            root_0 = (CommonTree)adaptor.nil();

            SYM_LP80=(Token)match(input,SYM_LP,FOLLOW_SYM_LP_in_inbindskipped985); 
            SYM_LP80_tree = (CommonTree)adaptor.create(SYM_LP80);
            adaptor.addChild(root_0, SYM_LP80_tree);

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:426:3: ( txtcomment )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==C_ST||LA16_0==C_LN_ST) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:426:3: txtcomment
            	    {
            	    pushFollow(FOLLOW_txtcomment_in_inbindskipped990);
            	    txtcomment81=txtcomment();

            	    state._fsp--;

            	    adaptor.addChild(root_0, txtcomment81.getTree());

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            pushFollow(FOLLOW_bindchars_in_inbindskipped993);
            bindchars82=bindchars();

            state._fsp--;

            adaptor.addChild(root_0, bindchars82.getTree());
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:426:25: ( txtcomment )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==C_ST||LA17_0==C_LN_ST) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:426:25: txtcomment
            	    {
            	    pushFollow(FOLLOW_txtcomment_in_inbindskipped995);
            	    txtcomment83=txtcomment();

            	    state._fsp--;

            	    adaptor.addChild(root_0, txtcomment83.getTree());

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:427:3: ( SYM_C ( txtcomment )* bindchars ( txtcomment )* )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==SYM_C) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:427:4: SYM_C ( txtcomment )* bindchars ( txtcomment )*
            	    {
            	    SYM_C84=(Token)match(input,SYM_C,FOLLOW_SYM_C_in_inbindskipped1001); 
            	    SYM_C84_tree = (CommonTree)adaptor.create(SYM_C84);
            	    adaptor.addChild(root_0, SYM_C84_tree);

            	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:428:4: ( txtcomment )*
            	    loop18:
            	    do {
            	        int alt18=2;
            	        int LA18_0 = input.LA(1);

            	        if ( (LA18_0==C_ST||LA18_0==C_LN_ST) ) {
            	            alt18=1;
            	        }


            	        switch (alt18) {
            	    	case 1 :
            	    	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:428:4: txtcomment
            	    	    {
            	    	    pushFollow(FOLLOW_txtcomment_in_inbindskipped1007);
            	    	    txtcomment85=txtcomment();

            	    	    state._fsp--;

            	    	    adaptor.addChild(root_0, txtcomment85.getTree());

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop18;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_bindchars_in_inbindskipped1010);
            	    bindchars86=bindchars();

            	    state._fsp--;

            	    adaptor.addChild(root_0, bindchars86.getTree());
            	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:428:26: ( txtcomment )*
            	    loop19:
            	    do {
            	        int alt19=2;
            	        int LA19_0 = input.LA(1);

            	        if ( (LA19_0==C_ST||LA19_0==C_LN_ST) ) {
            	            alt19=1;
            	        }


            	        switch (alt19) {
            	    	case 1 :
            	    	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:428:26: txtcomment
            	    	    {
            	    	    pushFollow(FOLLOW_txtcomment_in_inbindskipped1012);
            	    	    txtcomment87=txtcomment();

            	    	    state._fsp--;

            	    	    adaptor.addChild(root_0, txtcomment87.getTree());

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop19;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            SYM_RP88=(Token)match(input,SYM_RP,FOLLOW_SYM_RP_in_inbindskipped1021); 
            SYM_RP88_tree = (CommonTree)adaptor.create(SYM_RP88);
            adaptor.addChild(root_0, SYM_RP88_tree);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.node.update(retval);
            		retval.node.freeze();
            	
        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
             pop(); 
        }
        return retval;
    }
    // $ANTLR end "inbindskipped"

    public static class bindchars_return extends ParserRuleReturnScope {
        public TxtNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "bindchars"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:434:1: bindchars returns [TxtNode node] : ( IDENT | QUOTED | SYMBOLS | SYM_BIND ) ;
    public final TwoWaySqlParser.bindchars_return bindchars() throws RecognitionException {
        TwoWaySqlParser.bindchars_return retval = new TwoWaySqlParser.bindchars_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set89=null;

        CommonTree set89_tree=null;


        		retval.node = new TxtNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:443:2: ( ( IDENT | QUOTED | SYMBOLS | SYM_BIND ) )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:444:2: ( IDENT | QUOTED | SYMBOLS | SYM_BIND )
            {
            root_0 = (CommonTree)adaptor.nil();

            set89=(Token)input.LT(1);
            if ( (input.LA(1)>=IDENT && input.LA(1)<=QUOTED)||(input.LA(1)>=SYMBOLS && input.LA(1)<=SYM_BIND) ) {
                input.consume();
                adaptor.addChild(root_0, (CommonTree)adaptor.create(set89));
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.node.update(retval);
            		retval.node.freeze();
            	
        }

        catch (RecognitionException ex) {
        	reportError(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "bindchars"

    // Delegated rules


    protected DFA3 dfa3 = new DFA3(this);
    static final String DFA3_eotS =
        "\14\uffff";
    static final String DFA3_eofS =
        "\11\uffff\1\6\2\uffff";
    static final String DFA3_minS =
        "\1\14\2\4\2\uffff\1\4\1\uffff\4\4\1\uffff";
    static final String DFA3_maxS =
        "\1\16\2\23\2\uffff\1\15\1\uffff\2\15\1\25\1\15\1\uffff";
    static final String DFA3_acceptS =
        "\3\uffff\1\1\1\2\1\uffff\1\4\4\uffff\1\3";
    static final String DFA3_specialS =
        "\14\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\1\1\uffff\1\2",
            "\4\6\1\5\3\6\4\uffff\1\4\2\uffff\1\3",
            "\10\6\7\uffff\1\3",
            "",
            "",
            "\1\7\7\10\1\uffff\1\6",
            "",
            "\4\10\1\12\3\10\1\uffff\1\11",
            "\10\10\1\uffff\1\11",
            "\2\13\1\6\2\13\4\6\1\uffff\1\6\6\uffff\1\6",
            "\10\10\1\uffff\1\11",
            ""
    };

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "172:1: comment returns [QueryNode node] : ( begincomment | ifcomment | bindcomment | txtcomment );";
        }
    }
 

    public static final BitSet FOLLOW_nodelist_in_twowaySQL98 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comment_in_nodelist128 = new BitSet(new long[]{0x0000000000205FF2L});
    public static final BitSet FOLLOW_inbind_in_nodelist135 = new BitSet(new long[]{0x0000000000205FF2L});
    public static final BitSet FOLLOW_txt_in_nodelist142 = new BitSet(new long[]{0x0000000000205FF2L});
    public static final BitSet FOLLOW_set_in_charactors162 = new BitSet(new long[]{0x0000000000000FF2L});
    public static final BitSet FOLLOW_charactors_in_txt221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_begincomment_in_comment241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifcomment_in_comment248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bindcomment_in_comment255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_txtcomment_in_comment262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_blockcomment_in_txtcomment278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_linecomment_in_txtcomment285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_blockcomment315 = new BitSet(new long[]{0x0000000000207FF0L});
    public static final BitSet FOLLOW_charactors_in_blockcomment317 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_C_ED_in_blockcomment319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_linecomment351 = new BitSet(new long[]{0x000000000020DFF0L});
    public static final BitSet FOLLOW_charactors_in_linecomment353 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_C_LN_ED_in_linecomment355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_ifcomment388 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_IF_in_ifcomment390 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_expression_in_ifcomment392 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_C_ED_in_ifcomment394 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_MAYBE_SKIP_in_ifcomment401 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_nodelist_in_ifcomment410 = new BitSet(new long[]{0x0000000000005000L});
    public static final BitSet FOLLOW_elseifnode_in_ifcomment417 = new BitSet(new long[]{0x0000000000005000L});
    public static final BitSet FOLLOW_elsenode_in_ifcomment427 = new BitSet(new long[]{0x0000000000005000L});
    public static final BitSet FOLLOW_endcomment_in_ifcomment435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elseifcomment_in_elseifnode471 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_MAYBE_SKIP_in_elseifnode475 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_nodelist_in_elseifnode484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elseifblockcomment_in_elseifcomment511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elseiflinecomment_in_elseifcomment519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_elseifblockcomment544 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_ELSEIF_in_elseifblockcomment546 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_expression_in_elseifblockcomment548 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_C_ED_in_elseifblockcomment550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_elseiflinecomment577 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_ELSEIF_in_elseiflinecomment579 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_expression_in_elseiflinecomment581 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_C_LN_ED_in_elseiflinecomment583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elsecomment_in_elsenode617 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_MAYBE_SKIP_in_elsenode621 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_nodelist_in_elsenode631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_elsecomment658 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_ELSE_in_elsecomment660 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_C_ED_in_elsecomment662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_elsecomment666 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_ELSE_in_elsecomment668 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_C_LN_ED_in_elsecomment670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_charactors_in_expression703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_begincomment741 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_BEGIN_in_begincomment743 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_C_ED_in_begincomment745 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_C_LN_ST_in_begincomment749 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_BEGIN_in_begincomment751 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_C_LN_ED_in_begincomment753 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_nodelist_in_begincomment756 = new BitSet(new long[]{0x0000000000005000L});
    public static final BitSet FOLLOW_endcomment_in_begincomment758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_endcomment787 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_END_in_endcomment789 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_C_ED_in_endcomment791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_endcomment795 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_END_in_endcomment797 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_C_LN_ED_in_endcomment799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_bindcomment832 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_bindingname_in_bindcomment838 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_SYM_BIND_in_bindcomment846 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_expression_in_bindcomment848 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_C_ED_in_bindcomment850 = new BitSet(new long[]{0x00000000000001B0L});
    public static final BitSet FOLLOW_bindchars_in_bindcomment852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SYM_BIND_in_bindingname889 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_bindingname891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IN_in_inbind927 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_C_ST_in_inbind929 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_bindingname_in_inbind935 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_SYM_BIND_in_inbind944 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_expression_in_inbind946 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_C_ED_in_inbind948 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_inbindskipped_in_inbind950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SYM_LP_in_inbindskipped985 = new BitSet(new long[]{0x00000000000051B0L});
    public static final BitSet FOLLOW_txtcomment_in_inbindskipped990 = new BitSet(new long[]{0x00000000000051B0L});
    public static final BitSet FOLLOW_bindchars_in_inbindskipped993 = new BitSet(new long[]{0x0000000000005A00L});
    public static final BitSet FOLLOW_txtcomment_in_inbindskipped995 = new BitSet(new long[]{0x0000000000005A00L});
    public static final BitSet FOLLOW_SYM_C_in_inbindskipped1001 = new BitSet(new long[]{0x00000000000051B0L});
    public static final BitSet FOLLOW_txtcomment_in_inbindskipped1007 = new BitSet(new long[]{0x00000000000051B0L});
    public static final BitSet FOLLOW_bindchars_in_inbindskipped1010 = new BitSet(new long[]{0x0000000000005A00L});
    public static final BitSet FOLLOW_txtcomment_in_inbindskipped1012 = new BitSet(new long[]{0x0000000000005A00L});
    public static final BitSet FOLLOW_SYM_RP_in_inbindskipped1021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_bindchars1056 = new BitSet(new long[]{0x0000000000000002L});

}