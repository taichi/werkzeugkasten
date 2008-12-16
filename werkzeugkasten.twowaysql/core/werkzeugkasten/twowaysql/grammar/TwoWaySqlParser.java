// $ANTLR 3.1.1 C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g 2008-12-16 22:33:25

package werkzeugkasten.twowaysql.grammar;

import werkzeugkasten.twowaysql.error.*;
import werkzeugkasten.twowaysql.error.mapper.*;
import werkzeugkasten.twowaysql.tree.*;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class TwoWaySqlParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IDENT", "QUOTED", "SYMBOLS", "SYM_BIND", "SYM_C", "SYM_LP", "SYM_RP", "C_ST", "C_ED", "C_LN_ST", "C_LN_ED", "IF", "MAYBE_SKIP", "ELSEIF", "ELSE", "BEGIN", "END", "IN", "SYM_Q", "LN_R", "LN_N", "AND", "OR", "CHAR", "WS", "LT", "WHITE_SPACES"
    };
    public static final int LT=29;
    public static final int C_ST=11;
    public static final int QUOTED=5;
    public static final int SYM_RP=10;
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
    public static final int SYMBOLS=6;
    public static final int SYM_C=8;
    public static final int END=20;
    public static final int SYM_BIND=7;
    public static final int C_LN_ED=14;
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
    protected static final ExceptionMapper EM_INBIND = new InBindExceptionMapper();
    protected static final ExceptionMapper EM_INBINDSKIPPED = new InBindSkippedExceptionMapper();



    public static class twowaySQL_return extends ParserRuleReturnScope {
        public TwoWayQuery query;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "twowaySQL"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:75:1: twowaySQL returns [TwoWayQuery query] : nodelist EOF ;
    public final TwoWaySqlParser.twowaySQL_return twowaySQL() throws RecognitionException {
        TwoWaySqlParser.twowaySQL_return retval = new TwoWaySqlParser.twowaySQL_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token EOF2=null;
        TwoWaySqlParser.nodelist_return nodelist1 = null;


        CommonTree EOF2_tree=null;


        		retval.query = new TwoWayQuery();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:83:2: ( nodelist EOF )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:83:4: nodelist EOF
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_nodelist_in_twowaySQL98);
            nodelist1=nodelist();

            state._fsp--;

            adaptor.addChild(root_0, nodelist1.getTree());
            EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_twowaySQL100); 
            EOF2_tree = (CommonTree)adaptor.create(EOF2);
            adaptor.addChild(root_0, EOF2_tree);


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
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:91:1: nodelist returns [ArrayList<QueryNode> list] : ( comment | inbind | txt )+ ;
    public final TwoWaySqlParser.nodelist_return nodelist() throws RecognitionException {
        TwoWaySqlParser.nodelist_return retval = new TwoWaySqlParser.nodelist_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.comment_return comment3 = null;

        TwoWaySqlParser.inbind_return inbind4 = null;

        TwoWaySqlParser.txt_return txt5 = null;




        		retval.list = new ArrayList<QueryNode>();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:96:2: ( ( comment | inbind | txt )+ )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:97:2: ( comment | inbind | txt )+
            {
            root_0 = (CommonTree)adaptor.nil();

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:97:2: ( comment | inbind | txt )+
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
            	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:97:3: comment
            	    {
            	    pushFollow(FOLLOW_comment_in_nodelist130);
            	    comment3=comment();

            	    state._fsp--;

            	    adaptor.addChild(root_0, comment3.getTree());
            	    retval.list.add((comment3!=null?comment3.node:null));

            	    }
            	    break;
            	case 2 :
            	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:98:4: inbind
            	    {
            	    pushFollow(FOLLOW_inbind_in_nodelist137);
            	    inbind4=inbind();

            	    state._fsp--;

            	    adaptor.addChild(root_0, inbind4.getTree());
            	    retval.list.add((inbind4!=null?inbind4.node:null));

            	    }
            	    break;
            	case 3 :
            	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:99:4: txt
            	    {
            	    pushFollow(FOLLOW_txt_in_nodelist144);
            	    txt5=txt();

            	    state._fsp--;

            	    adaptor.addChild(root_0, txt5.getTree());
            	    retval.list.add((txt5!=null?txt5.node:null));

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
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:103:1: charactors : ( IDENT | QUOTED | SYMBOLS | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+ ;
    public final TwoWaySqlParser.charactors_return charactors() throws RecognitionException {
        TwoWaySqlParser.charactors_return retval = new TwoWaySqlParser.charactors_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set6=null;

        CommonTree set6_tree=null;

        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:105:2: ( ( IDENT | QUOTED | SYMBOLS | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+ )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:106:2: ( IDENT | QUOTED | SYMBOLS | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+
            {
            root_0 = (CommonTree)adaptor.nil();

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:106:2: ( IDENT | QUOTED | SYMBOLS | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+
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
            	    set6=(Token)input.LT(1);
            	    if ( (input.LA(1)>=IDENT && input.LA(1)<=SYM_RP) ) {
            	        input.consume();
            	        adaptor.addChild(root_0, (CommonTree)adaptor.create(set6));
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
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:109:1: txt returns [TxtNode node] : charactors ;
    public final TwoWaySqlParser.txt_return txt() throws RecognitionException {
        TwoWaySqlParser.txt_return retval = new TwoWaySqlParser.txt_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.charactors_return charactors7 = null;




        		push(EM_TXT);
        		retval.node = new TxtNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:118:2: ( charactors )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:119:2: charactors
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_charactors_in_txt219);
            charactors7=charactors();

            state._fsp--;

            adaptor.addChild(root_0, charactors7.getTree());

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
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:125:1: comment returns [QueryNode node] : ( begincomment | ifcomment | bindcomment | txtcomment );
    public final TwoWaySqlParser.comment_return comment() throws RecognitionException {
        TwoWaySqlParser.comment_return retval = new TwoWaySqlParser.comment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.begincomment_return begincomment8 = null;

        TwoWaySqlParser.ifcomment_return ifcomment9 = null;

        TwoWaySqlParser.bindcomment_return bindcomment10 = null;

        TwoWaySqlParser.txtcomment_return txtcomment11 = null;



        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:125:32: ( begincomment | ifcomment | bindcomment | txtcomment )
            int alt3=4;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:126:2: begincomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_begincomment_in_comment239);
                    begincomment8=begincomment();

                    state._fsp--;

                    adaptor.addChild(root_0, begincomment8.getTree());
                    retval.node = (begincomment8!=null?begincomment8.node:null);

                    }
                    break;
                case 2 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:127:4: ifcomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_ifcomment_in_comment246);
                    ifcomment9=ifcomment();

                    state._fsp--;

                    adaptor.addChild(root_0, ifcomment9.getTree());
                    retval.node = (ifcomment9!=null?ifcomment9.node:null);

                    }
                    break;
                case 3 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:128:4: bindcomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_bindcomment_in_comment253);
                    bindcomment10=bindcomment();

                    state._fsp--;

                    adaptor.addChild(root_0, bindcomment10.getTree());
                    retval.node = (bindcomment10!=null?bindcomment10.node:null);

                    }
                    break;
                case 4 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:129:4: txtcomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_txtcomment_in_comment260);
                    txtcomment11=txtcomment();

                    state._fsp--;

                    adaptor.addChild(root_0, txtcomment11.getTree());
                    retval.node = (txtcomment11!=null?txtcomment11.node:null);

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
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:132:1: txtcomment returns [TxtNode node] : ( blockcomment | linecomment );
    public final TwoWaySqlParser.txtcomment_return txtcomment() throws RecognitionException {
        TwoWaySqlParser.txtcomment_return retval = new TwoWaySqlParser.txtcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.blockcomment_return blockcomment12 = null;

        TwoWaySqlParser.linecomment_return linecomment13 = null;



        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:133:2: ( blockcomment | linecomment )
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
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:133:4: blockcomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_blockcomment_in_txtcomment276);
                    blockcomment12=blockcomment();

                    state._fsp--;

                    adaptor.addChild(root_0, blockcomment12.getTree());
                    retval.node = (blockcomment12!=null?blockcomment12.node:null);

                    }
                    break;
                case 2 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:134:4: linecomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_linecomment_in_txtcomment283);
                    linecomment13=linecomment();

                    state._fsp--;

                    adaptor.addChild(root_0, linecomment13.getTree());
                    retval.node = (linecomment13!=null?linecomment13.node:null);

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
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:137:1: blockcomment returns [TxtNode node] : C_ST charactors C_ED ;
    public final TwoWaySqlParser.blockcomment_return blockcomment() throws RecognitionException {
        TwoWaySqlParser.blockcomment_return retval = new TwoWaySqlParser.blockcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST14=null;
        Token C_ED16=null;
        TwoWaySqlParser.charactors_return charactors15 = null;


        CommonTree C_ST14_tree=null;
        CommonTree C_ED16_tree=null;


        		push(EM_BLOCKCOMMENT);
        		retval.node = new TxtNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:146:2: ( C_ST charactors C_ED )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:147:2: C_ST charactors C_ED
            {
            root_0 = (CommonTree)adaptor.nil();

            C_ST14=(Token)match(input,C_ST,FOLLOW_C_ST_in_blockcomment313); 
            C_ST14_tree = (CommonTree)adaptor.create(C_ST14);
            adaptor.addChild(root_0, C_ST14_tree);

            pushFollow(FOLLOW_charactors_in_blockcomment315);
            charactors15=charactors();

            state._fsp--;

            adaptor.addChild(root_0, charactors15.getTree());
            C_ED16=(Token)match(input,C_ED,FOLLOW_C_ED_in_blockcomment317); 
            C_ED16_tree = (CommonTree)adaptor.create(C_ED16);
            adaptor.addChild(root_0, C_ED16_tree);


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
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:151:1: linecomment returns [TxtNode node] : C_LN_ST charactors C_LN_ED ;
    public final TwoWaySqlParser.linecomment_return linecomment() throws RecognitionException {
        TwoWaySqlParser.linecomment_return retval = new TwoWaySqlParser.linecomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_LN_ST17=null;
        Token C_LN_ED19=null;
        TwoWaySqlParser.charactors_return charactors18 = null;


        CommonTree C_LN_ST17_tree=null;
        CommonTree C_LN_ED19_tree=null;


        		push(EM_LINECOMMENT);
        		retval.node = new TxtNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:160:2: ( C_LN_ST charactors C_LN_ED )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:161:2: C_LN_ST charactors C_LN_ED
            {
            root_0 = (CommonTree)adaptor.nil();

            C_LN_ST17=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_linecomment349); 
            C_LN_ST17_tree = (CommonTree)adaptor.create(C_LN_ST17);
            adaptor.addChild(root_0, C_LN_ST17_tree);

            pushFollow(FOLLOW_charactors_in_linecomment351);
            charactors18=charactors();

            state._fsp--;

            adaptor.addChild(root_0, charactors18.getTree());
            C_LN_ED19=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_linecomment353); 
            C_LN_ED19_tree = (CommonTree)adaptor.create(C_LN_ED19);
            adaptor.addChild(root_0, C_LN_ED19_tree);


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
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:165:1: ifcomment returns [IfNode node] : ( C_ST IF expression C_ED ( MAYBE_SKIP )? nodelist ( elseifnode )* ( elsenode )? endcomment ) ;
    public final TwoWaySqlParser.ifcomment_return ifcomment() throws RecognitionException {
        TwoWaySqlParser.ifcomment_return retval = new TwoWaySqlParser.ifcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST20=null;
        Token IF21=null;
        Token C_ED23=null;
        Token MAYBE_SKIP24=null;
        TwoWaySqlParser.expression_return expression22 = null;

        TwoWaySqlParser.nodelist_return nodelist25 = null;

        TwoWaySqlParser.elseifnode_return elseifnode26 = null;

        TwoWaySqlParser.elsenode_return elsenode27 = null;

        TwoWaySqlParser.endcomment_return endcomment28 = null;


        CommonTree C_ST20_tree=null;
        CommonTree IF21_tree=null;
        CommonTree C_ED23_tree=null;
        CommonTree MAYBE_SKIP24_tree=null;


        		push(EM_IFCOMMENT);
        		retval.node = new IfNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:174:2: ( ( C_ST IF expression C_ED ( MAYBE_SKIP )? nodelist ( elseifnode )* ( elsenode )? endcomment ) )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:175:2: ( C_ST IF expression C_ED ( MAYBE_SKIP )? nodelist ( elseifnode )* ( elsenode )? endcomment )
            {
            root_0 = (CommonTree)adaptor.nil();

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:175:2: ( C_ST IF expression C_ED ( MAYBE_SKIP )? nodelist ( elseifnode )* ( elsenode )? endcomment )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:175:3: C_ST IF expression C_ED ( MAYBE_SKIP )? nodelist ( elseifnode )* ( elsenode )? endcomment
            {
            C_ST20=(Token)match(input,C_ST,FOLLOW_C_ST_in_ifcomment386); 
            C_ST20_tree = (CommonTree)adaptor.create(C_ST20);
            adaptor.addChild(root_0, C_ST20_tree);

            IF21=(Token)match(input,IF,FOLLOW_IF_in_ifcomment388); 
            IF21_tree = (CommonTree)adaptor.create(IF21);
            adaptor.addChild(root_0, IF21_tree);

            pushFollow(FOLLOW_expression_in_ifcomment390);
            expression22=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression22.getTree());
            C_ED23=(Token)match(input,C_ED,FOLLOW_C_ED_in_ifcomment392); 
            C_ED23_tree = (CommonTree)adaptor.create(C_ED23);
            adaptor.addChild(root_0, C_ED23_tree);

             retval.node.setExpression((expression22!=null?expression22.node:null)); 
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:176:3: ( MAYBE_SKIP )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==MAYBE_SKIP) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:176:4: MAYBE_SKIP
                    {
                    MAYBE_SKIP24=(Token)match(input,MAYBE_SKIP,FOLLOW_MAYBE_SKIP_in_ifcomment399); 
                    MAYBE_SKIP24_tree = (CommonTree)adaptor.create(MAYBE_SKIP24);
                    adaptor.addChild(root_0, MAYBE_SKIP24_tree);


                    			TxtNode skip = new TxtNode();
                    			skip.update(MAYBE_SKIP24);
                    			skip.freeze();
                    			retval.node.setMaybeSkip(skip);
                    		

                    }
                    break;

            }

            pushFollow(FOLLOW_nodelist_in_ifcomment408);
            nodelist25=nodelist();

            state._fsp--;

            adaptor.addChild(root_0, nodelist25.getTree());
             retval.node.setChildren((nodelist25!=null?nodelist25.list:null));
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:183:3: ( elseifnode )*
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
            	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:183:4: elseifnode
            	    {
            	    pushFollow(FOLLOW_elseifnode_in_ifcomment415);
            	    elseifnode26=elseifnode();

            	    state._fsp--;

            	    adaptor.addChild(root_0, elseifnode26.getTree());
            	     retval.node.addElseIf((elseifnode26!=null?elseifnode26.node:null)); 

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:184:3: ( elsenode )?
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
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:184:4: elsenode
                    {
                    pushFollow(FOLLOW_elsenode_in_ifcomment425);
                    elsenode27=elsenode();

                    state._fsp--;

                    adaptor.addChild(root_0, elsenode27.getTree());
                     retval.node.setElse((elsenode27!=null?elsenode27.list:null)); 

                    }
                    break;

            }

            pushFollow(FOLLOW_endcomment_in_ifcomment433);
            endcomment28=endcomment();

            state._fsp--;

            adaptor.addChild(root_0, endcomment28.getTree());

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
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:190:1: elseifnode returns [IfNode node] : elseifcomment nodelist ;
    public final TwoWaySqlParser.elseifnode_return elseifnode() throws RecognitionException {
        TwoWaySqlParser.elseifnode_return retval = new TwoWaySqlParser.elseifnode_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.elseifcomment_return elseifcomment29 = null;

        TwoWaySqlParser.nodelist_return nodelist30 = null;




        		push(EM_ELSEIFNODE);
        		retval.node = new IfNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:199:2: ( elseifcomment nodelist )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:200:2: elseifcomment nodelist
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_elseifcomment_in_elseifnode469);
            elseifcomment29=elseifcomment();

            state._fsp--;

            adaptor.addChild(root_0, elseifcomment29.getTree());
            pushFollow(FOLLOW_nodelist_in_elseifnode471);
            nodelist30=nodelist();

            state._fsp--;

            adaptor.addChild(root_0, nodelist30.getTree());

            		retval.node.setExpression((elseifcomment29!=null?elseifcomment29.node:null));
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
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:208:1: elseifcomment returns [ExpressionNode node] : ( elseifblockcomment | elseiflinecomment ) ;
    public final TwoWaySqlParser.elseifcomment_return elseifcomment() throws RecognitionException {
        TwoWaySqlParser.elseifcomment_return retval = new TwoWaySqlParser.elseifcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.elseifblockcomment_return elseifblockcomment31 = null;

        TwoWaySqlParser.elseiflinecomment_return elseiflinecomment32 = null;



        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:210:2: ( ( elseifblockcomment | elseiflinecomment ) )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:211:2: ( elseifblockcomment | elseiflinecomment )
            {
            root_0 = (CommonTree)adaptor.nil();

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:211:2: ( elseifblockcomment | elseiflinecomment )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==C_ST) ) {
                alt8=1;
            }
            else if ( (LA8_0==C_LN_ST) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:211:3: elseifblockcomment
                    {
                    pushFollow(FOLLOW_elseifblockcomment_in_elseifcomment498);
                    elseifblockcomment31=elseifblockcomment();

                    state._fsp--;

                    adaptor.addChild(root_0, elseifblockcomment31.getTree());
                    retval.node = (elseifblockcomment31!=null?elseifblockcomment31.node:null);

                    }
                    break;
                case 2 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:212:5: elseiflinecomment
                    {
                    pushFollow(FOLLOW_elseiflinecomment_in_elseifcomment506);
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
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:216:1: elseifblockcomment returns [ExpressionNode node] : C_ST ELSEIF expression C_ED ;
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
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:220:2: ( C_ST ELSEIF expression C_ED )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:220:4: C_ST ELSEIF expression C_ED
            {
            root_0 = (CommonTree)adaptor.nil();

            C_ST33=(Token)match(input,C_ST,FOLLOW_C_ST_in_elseifblockcomment531); 
            C_ST33_tree = (CommonTree)adaptor.create(C_ST33);
            adaptor.addChild(root_0, C_ST33_tree);

            ELSEIF34=(Token)match(input,ELSEIF,FOLLOW_ELSEIF_in_elseifblockcomment533); 
            ELSEIF34_tree = (CommonTree)adaptor.create(ELSEIF34);
            adaptor.addChild(root_0, ELSEIF34_tree);

            pushFollow(FOLLOW_expression_in_elseifblockcomment535);
            expression35=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression35.getTree());
            C_ED36=(Token)match(input,C_ED,FOLLOW_C_ED_in_elseifblockcomment537); 
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
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:224:1: elseiflinecomment returns [ExpressionNode node] : C_LN_ST ELSEIF expression C_LN_ED ;
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
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:228:2: ( C_LN_ST ELSEIF expression C_LN_ED )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:228:4: C_LN_ST ELSEIF expression C_LN_ED
            {
            root_0 = (CommonTree)adaptor.nil();

            C_LN_ST37=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_elseiflinecomment564); 
            C_LN_ST37_tree = (CommonTree)adaptor.create(C_LN_ST37);
            adaptor.addChild(root_0, C_LN_ST37_tree);

            ELSEIF38=(Token)match(input,ELSEIF,FOLLOW_ELSEIF_in_elseiflinecomment566); 
            ELSEIF38_tree = (CommonTree)adaptor.create(ELSEIF38);
            adaptor.addChild(root_0, ELSEIF38_tree);

            pushFollow(FOLLOW_expression_in_elseiflinecomment568);
            expression39=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression39.getTree());
            C_LN_ED40=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_elseiflinecomment570); 
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
        public List<QueryNode> list;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elsenode"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:232:1: elsenode returns [List<QueryNode> list] : elsecomment nodelist ;
    public final TwoWaySqlParser.elsenode_return elsenode() throws RecognitionException {
        TwoWaySqlParser.elsenode_return retval = new TwoWaySqlParser.elsenode_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.elsecomment_return elsecomment41 = null;

        TwoWaySqlParser.nodelist_return nodelist42 = null;




        		push(EM_ELSENODE);
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:236:2: ( elsecomment nodelist )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:237:2: elsecomment nodelist
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_elsecomment_in_elsenode598);
            elsecomment41=elsecomment();

            state._fsp--;

            adaptor.addChild(root_0, elsecomment41.getTree());
            pushFollow(FOLLOW_nodelist_in_elsenode600);
            nodelist42=nodelist();

            state._fsp--;

            adaptor.addChild(root_0, nodelist42.getTree());
             retval.list = (nodelist42!=null?nodelist42.list:null); 

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
    // $ANTLR end "elsenode"

    public static class elsecomment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elsecomment"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:241:1: elsecomment : ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED ) ;
    public final TwoWaySqlParser.elsecomment_return elsecomment() throws RecognitionException {
        TwoWaySqlParser.elsecomment_return retval = new TwoWaySqlParser.elsecomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST43=null;
        Token ELSE44=null;
        Token C_ED45=null;
        Token C_LN_ST46=null;
        Token ELSE47=null;
        Token C_LN_ED48=null;

        CommonTree C_ST43_tree=null;
        CommonTree ELSE44_tree=null;
        CommonTree C_ED45_tree=null;
        CommonTree C_LN_ST46_tree=null;
        CommonTree ELSE47_tree=null;
        CommonTree C_LN_ED48_tree=null;


        		push(EM_ELSECOMMENT);
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:245:2: ( ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED ) )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:246:2: ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED )
            {
            root_0 = (CommonTree)adaptor.nil();

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:246:2: ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED )
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
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:246:3: C_ST ELSE C_ED
                    {
                    C_ST43=(Token)match(input,C_ST,FOLLOW_C_ST_in_elsecomment627); 
                    C_ST43_tree = (CommonTree)adaptor.create(C_ST43);
                    adaptor.addChild(root_0, C_ST43_tree);

                    ELSE44=(Token)match(input,ELSE,FOLLOW_ELSE_in_elsecomment629); 
                    ELSE44_tree = (CommonTree)adaptor.create(ELSE44);
                    adaptor.addChild(root_0, ELSE44_tree);

                    C_ED45=(Token)match(input,C_ED,FOLLOW_C_ED_in_elsecomment631); 
                    C_ED45_tree = (CommonTree)adaptor.create(C_ED45);
                    adaptor.addChild(root_0, C_ED45_tree);


                    }
                    break;
                case 2 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:246:20: C_LN_ST ELSE C_LN_ED
                    {
                    C_LN_ST46=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_elsecomment635); 
                    C_LN_ST46_tree = (CommonTree)adaptor.create(C_LN_ST46);
                    adaptor.addChild(root_0, C_LN_ST46_tree);

                    ELSE47=(Token)match(input,ELSE,FOLLOW_ELSE_in_elsecomment637); 
                    ELSE47_tree = (CommonTree)adaptor.create(ELSE47);
                    adaptor.addChild(root_0, ELSE47_tree);

                    C_LN_ED48=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_elsecomment639); 
                    C_LN_ED48_tree = (CommonTree)adaptor.create(C_LN_ED48);
                    adaptor.addChild(root_0, C_LN_ED48_tree);


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
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:250:1: expression returns [ExpressionNode node] : charactors ;
    public final TwoWaySqlParser.expression_return expression() throws RecognitionException {
        TwoWaySqlParser.expression_return retval = new TwoWaySqlParser.expression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.charactors_return charactors49 = null;




        		push(EM_EXPRESSION);
        		retval.node = new ExpressionNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:259:2: ( charactors )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:260:2: charactors
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_charactors_in_expression672);
            charactors49=charactors();

            state._fsp--;

            adaptor.addChild(root_0, charactors49.getTree());

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
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:264:1: begincomment returns [BeginNode node] : ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) nodelist endcomment ) ;
    public final TwoWaySqlParser.begincomment_return begincomment() throws RecognitionException {
        TwoWaySqlParser.begincomment_return retval = new TwoWaySqlParser.begincomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST50=null;
        Token BEGIN51=null;
        Token C_ED52=null;
        Token C_LN_ST53=null;
        Token BEGIN54=null;
        Token C_LN_ED55=null;
        TwoWaySqlParser.nodelist_return nodelist56 = null;

        TwoWaySqlParser.endcomment_return endcomment57 = null;


        CommonTree C_ST50_tree=null;
        CommonTree BEGIN51_tree=null;
        CommonTree C_ED52_tree=null;
        CommonTree C_LN_ST53_tree=null;
        CommonTree BEGIN54_tree=null;
        CommonTree C_LN_ED55_tree=null;


        		push(EM_BEGINCOMMENT);
        		retval.node = new BeginNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:273:2: ( ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) nodelist endcomment ) )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:274:2: ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) nodelist endcomment )
            {
            root_0 = (CommonTree)adaptor.nil();

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:274:2: ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) nodelist endcomment )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:275:3: ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) nodelist endcomment
            {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:275:3: ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==C_ST) ) {
                alt10=1;
            }
            else if ( (LA10_0==C_LN_ST) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:275:4: C_ST BEGIN C_ED
                    {
                    C_ST50=(Token)match(input,C_ST,FOLLOW_C_ST_in_begincomment710); 
                    C_ST50_tree = (CommonTree)adaptor.create(C_ST50);
                    adaptor.addChild(root_0, C_ST50_tree);

                    BEGIN51=(Token)match(input,BEGIN,FOLLOW_BEGIN_in_begincomment712); 
                    BEGIN51_tree = (CommonTree)adaptor.create(BEGIN51);
                    adaptor.addChild(root_0, BEGIN51_tree);

                    C_ED52=(Token)match(input,C_ED,FOLLOW_C_ED_in_begincomment714); 
                    C_ED52_tree = (CommonTree)adaptor.create(C_ED52);
                    adaptor.addChild(root_0, C_ED52_tree);


                    }
                    break;
                case 2 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:275:22: C_LN_ST BEGIN C_LN_ED
                    {
                    C_LN_ST53=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_begincomment718); 
                    C_LN_ST53_tree = (CommonTree)adaptor.create(C_LN_ST53);
                    adaptor.addChild(root_0, C_LN_ST53_tree);

                    BEGIN54=(Token)match(input,BEGIN,FOLLOW_BEGIN_in_begincomment720); 
                    BEGIN54_tree = (CommonTree)adaptor.create(BEGIN54);
                    adaptor.addChild(root_0, BEGIN54_tree);

                    C_LN_ED55=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_begincomment722); 
                    C_LN_ED55_tree = (CommonTree)adaptor.create(C_LN_ED55);
                    adaptor.addChild(root_0, C_LN_ED55_tree);


                    }
                    break;

            }

            pushFollow(FOLLOW_nodelist_in_begincomment725);
            nodelist56=nodelist();

            state._fsp--;

            adaptor.addChild(root_0, nodelist56.getTree());
            pushFollow(FOLLOW_endcomment_in_begincomment727);
            endcomment57=endcomment();

            state._fsp--;

            adaptor.addChild(root_0, endcomment57.getTree());

            }


            		retval.node.setChildren((nodelist56!=null?nodelist56.list:null));
            	

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
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:283:1: endcomment : ( C_ST END C_ED | C_LN_ST END C_LN_ED );
    public final TwoWaySqlParser.endcomment_return endcomment() throws RecognitionException {
        TwoWaySqlParser.endcomment_return retval = new TwoWaySqlParser.endcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST58=null;
        Token END59=null;
        Token C_ED60=null;
        Token C_LN_ST61=null;
        Token END62=null;
        Token C_LN_ED63=null;

        CommonTree C_ST58_tree=null;
        CommonTree END59_tree=null;
        CommonTree C_ED60_tree=null;
        CommonTree C_LN_ST61_tree=null;
        CommonTree END62_tree=null;
        CommonTree C_LN_ED63_tree=null;


        		push(EM_ENDCOMMENT);
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:287:2: ( C_ST END C_ED | C_LN_ST END C_LN_ED )
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
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:288:2: C_ST END C_ED
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    C_ST58=(Token)match(input,C_ST,FOLLOW_C_ST_in_endcomment756); 
                    C_ST58_tree = (CommonTree)adaptor.create(C_ST58);
                    adaptor.addChild(root_0, C_ST58_tree);

                    END59=(Token)match(input,END,FOLLOW_END_in_endcomment758); 
                    END59_tree = (CommonTree)adaptor.create(END59);
                    adaptor.addChild(root_0, END59_tree);

                    C_ED60=(Token)match(input,C_ED,FOLLOW_C_ED_in_endcomment760); 
                    C_ED60_tree = (CommonTree)adaptor.create(C_ED60);
                    adaptor.addChild(root_0, C_ED60_tree);


                    }
                    break;
                case 2 :
                    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:288:18: C_LN_ST END C_LN_ED
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    C_LN_ST61=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_endcomment764); 
                    C_LN_ST61_tree = (CommonTree)adaptor.create(C_LN_ST61);
                    adaptor.addChild(root_0, C_LN_ST61_tree);

                    END62=(Token)match(input,END,FOLLOW_END_in_endcomment766); 
                    END62_tree = (CommonTree)adaptor.create(END62);
                    adaptor.addChild(root_0, END62_tree);

                    C_LN_ED63=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_endcomment768); 
                    C_LN_ED63_tree = (CommonTree)adaptor.create(C_LN_ED63);
                    adaptor.addChild(root_0, C_LN_ED63_tree);


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
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:292:1: bindcomment returns [BindNode node] : ( C_ST SYM_BIND expression C_ED inbindchars ) ;
    public final TwoWaySqlParser.bindcomment_return bindcomment() throws RecognitionException {
        TwoWaySqlParser.bindcomment_return retval = new TwoWaySqlParser.bindcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST64=null;
        Token SYM_BIND65=null;
        Token C_ED67=null;
        TwoWaySqlParser.expression_return expression66 = null;

        TwoWaySqlParser.inbindchars_return inbindchars68 = null;


        CommonTree C_ST64_tree=null;
        CommonTree SYM_BIND65_tree=null;
        CommonTree C_ED67_tree=null;


        		push(EM_BINDCOMMENT);
        		retval.node = new BindNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:301:2: ( ( C_ST SYM_BIND expression C_ED inbindchars ) )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:302:2: ( C_ST SYM_BIND expression C_ED inbindchars )
            {
            root_0 = (CommonTree)adaptor.nil();

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:302:2: ( C_ST SYM_BIND expression C_ED inbindchars )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:302:3: C_ST SYM_BIND expression C_ED inbindchars
            {
            C_ST64=(Token)match(input,C_ST,FOLLOW_C_ST_in_bindcomment801); 
            C_ST64_tree = (CommonTree)adaptor.create(C_ST64);
            adaptor.addChild(root_0, C_ST64_tree);

            SYM_BIND65=(Token)match(input,SYM_BIND,FOLLOW_SYM_BIND_in_bindcomment803); 
            SYM_BIND65_tree = (CommonTree)adaptor.create(SYM_BIND65);
            adaptor.addChild(root_0, SYM_BIND65_tree);

            pushFollow(FOLLOW_expression_in_bindcomment805);
            expression66=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression66.getTree());
            C_ED67=(Token)match(input,C_ED,FOLLOW_C_ED_in_bindcomment807); 
            C_ED67_tree = (CommonTree)adaptor.create(C_ED67);
            adaptor.addChild(root_0, C_ED67_tree);

            pushFollow(FOLLOW_inbindchars_in_bindcomment809);
            inbindchars68=inbindchars();

            state._fsp--;

            adaptor.addChild(root_0, inbindchars68.getTree());

            }


            		retval.node.setExpression((expression66!=null?expression66.node:null));
            		retval.node.setSkipped((inbindchars68!=null?inbindchars68.node:null));
            	

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

    public static class inbind_return extends ParserRuleReturnScope {
        public InBindNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "inbind"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:310:1: inbind returns [InBindNode node] : IN C_ST SYM_BIND expression C_ED inbindskipped ;
    public final TwoWaySqlParser.inbind_return inbind() throws RecognitionException {
        TwoWaySqlParser.inbind_return retval = new TwoWaySqlParser.inbind_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IN69=null;
        Token C_ST70=null;
        Token SYM_BIND71=null;
        Token C_ED73=null;
        TwoWaySqlParser.expression_return expression72 = null;

        TwoWaySqlParser.inbindskipped_return inbindskipped74 = null;


        CommonTree IN69_tree=null;
        CommonTree C_ST70_tree=null;
        CommonTree SYM_BIND71_tree=null;
        CommonTree C_ED73_tree=null;


        		push(EM_INBIND);
        		retval.node = new InBindNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:319:2: ( IN C_ST SYM_BIND expression C_ED inbindskipped )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:320:2: IN C_ST SYM_BIND expression C_ED inbindskipped
            {
            root_0 = (CommonTree)adaptor.nil();

            IN69=(Token)match(input,IN,FOLLOW_IN_in_inbind845); 
            IN69_tree = (CommonTree)adaptor.create(IN69);
            adaptor.addChild(root_0, IN69_tree);

            C_ST70=(Token)match(input,C_ST,FOLLOW_C_ST_in_inbind847); 
            C_ST70_tree = (CommonTree)adaptor.create(C_ST70);
            adaptor.addChild(root_0, C_ST70_tree);

            SYM_BIND71=(Token)match(input,SYM_BIND,FOLLOW_SYM_BIND_in_inbind849); 
            SYM_BIND71_tree = (CommonTree)adaptor.create(SYM_BIND71);
            adaptor.addChild(root_0, SYM_BIND71_tree);

            pushFollow(FOLLOW_expression_in_inbind851);
            expression72=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression72.getTree());
            C_ED73=(Token)match(input,C_ED,FOLLOW_C_ED_in_inbind853); 
            C_ED73_tree = (CommonTree)adaptor.create(C_ED73);
            adaptor.addChild(root_0, C_ED73_tree);

            pushFollow(FOLLOW_inbindskipped_in_inbind855);
            inbindskipped74=inbindskipped();

            state._fsp--;

            adaptor.addChild(root_0, inbindskipped74.getTree());

            		retval.node.setExpression((expression72!=null?expression72.node:null));
            		retval.node.setSkipped((inbindskipped74!=null?inbindskipped74.node:null));
            	

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
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:328:1: inbindskipped returns [TxtNode node] : SYM_LP ( txtcomment )* inbindchars ( txtcomment )* ( SYM_C ( txtcomment )* inbindchars ( txtcomment )* )* SYM_RP ;
    public final TwoWaySqlParser.inbindskipped_return inbindskipped() throws RecognitionException {
        TwoWaySqlParser.inbindskipped_return retval = new TwoWaySqlParser.inbindskipped_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token SYM_LP75=null;
        Token SYM_C79=null;
        Token SYM_RP83=null;
        TwoWaySqlParser.txtcomment_return txtcomment76 = null;

        TwoWaySqlParser.inbindchars_return inbindchars77 = null;

        TwoWaySqlParser.txtcomment_return txtcomment78 = null;

        TwoWaySqlParser.txtcomment_return txtcomment80 = null;

        TwoWaySqlParser.inbindchars_return inbindchars81 = null;

        TwoWaySqlParser.txtcomment_return txtcomment82 = null;


        CommonTree SYM_LP75_tree=null;
        CommonTree SYM_C79_tree=null;
        CommonTree SYM_RP83_tree=null;


        		push(EM_INBINDSKIPPED);
        		retval.node = new TxtNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:337:2: ( SYM_LP ( txtcomment )* inbindchars ( txtcomment )* ( SYM_C ( txtcomment )* inbindchars ( txtcomment )* )* SYM_RP )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:338:2: SYM_LP ( txtcomment )* inbindchars ( txtcomment )* ( SYM_C ( txtcomment )* inbindchars ( txtcomment )* )* SYM_RP
            {
            root_0 = (CommonTree)adaptor.nil();

            SYM_LP75=(Token)match(input,SYM_LP,FOLLOW_SYM_LP_in_inbindskipped890); 
            SYM_LP75_tree = (CommonTree)adaptor.create(SYM_LP75);
            adaptor.addChild(root_0, SYM_LP75_tree);

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:339:3: ( txtcomment )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==C_ST||LA12_0==C_LN_ST) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:339:3: txtcomment
            	    {
            	    pushFollow(FOLLOW_txtcomment_in_inbindskipped895);
            	    txtcomment76=txtcomment();

            	    state._fsp--;

            	    adaptor.addChild(root_0, txtcomment76.getTree());

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            pushFollow(FOLLOW_inbindchars_in_inbindskipped898);
            inbindchars77=inbindchars();

            state._fsp--;

            adaptor.addChild(root_0, inbindchars77.getTree());
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:339:27: ( txtcomment )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==C_ST||LA13_0==C_LN_ST) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:339:27: txtcomment
            	    {
            	    pushFollow(FOLLOW_txtcomment_in_inbindskipped900);
            	    txtcomment78=txtcomment();

            	    state._fsp--;

            	    adaptor.addChild(root_0, txtcomment78.getTree());

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:340:3: ( SYM_C ( txtcomment )* inbindchars ( txtcomment )* )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==SYM_C) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:340:4: SYM_C ( txtcomment )* inbindchars ( txtcomment )*
            	    {
            	    SYM_C79=(Token)match(input,SYM_C,FOLLOW_SYM_C_in_inbindskipped906); 
            	    SYM_C79_tree = (CommonTree)adaptor.create(SYM_C79);
            	    adaptor.addChild(root_0, SYM_C79_tree);

            	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:341:4: ( txtcomment )*
            	    loop14:
            	    do {
            	        int alt14=2;
            	        int LA14_0 = input.LA(1);

            	        if ( (LA14_0==C_ST||LA14_0==C_LN_ST) ) {
            	            alt14=1;
            	        }


            	        switch (alt14) {
            	    	case 1 :
            	    	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:341:4: txtcomment
            	    	    {
            	    	    pushFollow(FOLLOW_txtcomment_in_inbindskipped912);
            	    	    txtcomment80=txtcomment();

            	    	    state._fsp--;

            	    	    adaptor.addChild(root_0, txtcomment80.getTree());

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop14;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_inbindchars_in_inbindskipped915);
            	    inbindchars81=inbindchars();

            	    state._fsp--;

            	    adaptor.addChild(root_0, inbindchars81.getTree());
            	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:341:28: ( txtcomment )*
            	    loop15:
            	    do {
            	        int alt15=2;
            	        int LA15_0 = input.LA(1);

            	        if ( (LA15_0==C_ST||LA15_0==C_LN_ST) ) {
            	            alt15=1;
            	        }


            	        switch (alt15) {
            	    	case 1 :
            	    	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:341:28: txtcomment
            	    	    {
            	    	    pushFollow(FOLLOW_txtcomment_in_inbindskipped917);
            	    	    txtcomment82=txtcomment();

            	    	    state._fsp--;

            	    	    adaptor.addChild(root_0, txtcomment82.getTree());

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop15;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            SYM_RP83=(Token)match(input,SYM_RP,FOLLOW_SYM_RP_in_inbindskipped926); 
            SYM_RP83_tree = (CommonTree)adaptor.create(SYM_RP83);
            adaptor.addChild(root_0, SYM_RP83_tree);


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

    public static class inbindchars_return extends ParserRuleReturnScope {
        public TxtNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "inbindchars"
    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:347:1: inbindchars returns [TxtNode node] : ( IDENT | QUOTED | SYMBOLS | SYM_BIND )+ ;
    public final TwoWaySqlParser.inbindchars_return inbindchars() throws RecognitionException {
        TwoWaySqlParser.inbindchars_return retval = new TwoWaySqlParser.inbindchars_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set84=null;

        CommonTree set84_tree=null;


        		retval.node = new TxtNode();
        	
        try {
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:356:2: ( ( IDENT | QUOTED | SYMBOLS | SYM_BIND )+ )
            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:357:2: ( IDENT | QUOTED | SYMBOLS | SYM_BIND )+
            {
            root_0 = (CommonTree)adaptor.nil();

            // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:357:2: ( IDENT | QUOTED | SYMBOLS | SYM_BIND )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0>=IDENT && LA17_0<=SYM_BIND)) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // C:\\development\\java\\workspace_3.4.1\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:
            	    {
            	    set84=(Token)input.LT(1);
            	    if ( (input.LA(1)>=IDENT && input.LA(1)<=SYM_BIND) ) {
            	        input.consume();
            	        adaptor.addChild(root_0, (CommonTree)adaptor.create(set84));
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt17 >= 1 ) break loop17;
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.node.update(retval);
            		retval.node.freeze();
            	
        }
        catch (EarlyExitException ex) {

            		throw ex;
            	
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "inbindchars"

    // Delegated rules


    protected DFA3 dfa3 = new DFA3(this);
    static final String DFA3_eotS =
        "\12\uffff";
    static final String DFA3_eofS =
        "\10\uffff\1\6\1\uffff";
    static final String DFA3_minS =
        "\1\13\2\4\2\uffff\1\4\1\uffff\2\4\1\uffff";
    static final String DFA3_maxS =
        "\1\15\2\23\2\uffff\1\14\1\uffff\1\14\1\25\1\uffff";
    static final String DFA3_acceptS =
        "\3\uffff\1\1\1\2\1\uffff\1\4\2\uffff\1\3";
    static final String DFA3_specialS =
        "\12\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\1\1\uffff\1\2",
            "\3\6\1\5\3\6\4\uffff\1\4\3\uffff\1\3",
            "\7\6\10\uffff\1\3",
            "",
            "",
            "\7\7\1\uffff\1\6",
            "",
            "\7\7\1\uffff\1\10",
            "\4\11\4\6\1\uffff\1\6\7\uffff\1\6",
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
            return "125:1: comment returns [QueryNode node] : ( begincomment | ifcomment | bindcomment | txtcomment );";
        }
    }
 

    public static final BitSet FOLLOW_nodelist_in_twowaySQL98 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_twowaySQL100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comment_in_nodelist130 = new BitSet(new long[]{0x0000000000202FF2L});
    public static final BitSet FOLLOW_inbind_in_nodelist137 = new BitSet(new long[]{0x0000000000202FF2L});
    public static final BitSet FOLLOW_txt_in_nodelist144 = new BitSet(new long[]{0x0000000000202FF2L});
    public static final BitSet FOLLOW_set_in_charactors164 = new BitSet(new long[]{0x00000000000007F2L});
    public static final BitSet FOLLOW_charactors_in_txt219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_begincomment_in_comment239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifcomment_in_comment246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bindcomment_in_comment253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_txtcomment_in_comment260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_blockcomment_in_txtcomment276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_linecomment_in_txtcomment283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_blockcomment313 = new BitSet(new long[]{0x0000000000203FF0L});
    public static final BitSet FOLLOW_charactors_in_blockcomment315 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_C_ED_in_blockcomment317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_linecomment349 = new BitSet(new long[]{0x0000000000206FF0L});
    public static final BitSet FOLLOW_charactors_in_linecomment351 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_C_LN_ED_in_linecomment353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_ifcomment386 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_IF_in_ifcomment388 = new BitSet(new long[]{0x0000000000202FF0L});
    public static final BitSet FOLLOW_expression_in_ifcomment390 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_C_ED_in_ifcomment392 = new BitSet(new long[]{0x0000000000212FF0L});
    public static final BitSet FOLLOW_MAYBE_SKIP_in_ifcomment399 = new BitSet(new long[]{0x0000000000212FF0L});
    public static final BitSet FOLLOW_nodelist_in_ifcomment408 = new BitSet(new long[]{0x0000000000002800L});
    public static final BitSet FOLLOW_elseifnode_in_ifcomment415 = new BitSet(new long[]{0x0000000000002800L});
    public static final BitSet FOLLOW_elsenode_in_ifcomment425 = new BitSet(new long[]{0x0000000000002800L});
    public static final BitSet FOLLOW_endcomment_in_ifcomment433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elseifcomment_in_elseifnode469 = new BitSet(new long[]{0x0000000000212FF0L});
    public static final BitSet FOLLOW_nodelist_in_elseifnode471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elseifblockcomment_in_elseifcomment498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elseiflinecomment_in_elseifcomment506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_elseifblockcomment531 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_ELSEIF_in_elseifblockcomment533 = new BitSet(new long[]{0x0000000000202FF0L});
    public static final BitSet FOLLOW_expression_in_elseifblockcomment535 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_C_ED_in_elseifblockcomment537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_elseiflinecomment564 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_ELSEIF_in_elseiflinecomment566 = new BitSet(new long[]{0x0000000000202FF0L});
    public static final BitSet FOLLOW_expression_in_elseiflinecomment568 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_C_LN_ED_in_elseiflinecomment570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elsecomment_in_elsenode598 = new BitSet(new long[]{0x0000000000212FF0L});
    public static final BitSet FOLLOW_nodelist_in_elsenode600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_elsecomment627 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_ELSE_in_elsecomment629 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_C_ED_in_elsecomment631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_elsecomment635 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_ELSE_in_elsecomment637 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_C_LN_ED_in_elsecomment639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_charactors_in_expression672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_begincomment710 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_BEGIN_in_begincomment712 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_C_ED_in_begincomment714 = new BitSet(new long[]{0x0000000000212FF0L});
    public static final BitSet FOLLOW_C_LN_ST_in_begincomment718 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_BEGIN_in_begincomment720 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_C_LN_ED_in_begincomment722 = new BitSet(new long[]{0x0000000000212FF0L});
    public static final BitSet FOLLOW_nodelist_in_begincomment725 = new BitSet(new long[]{0x0000000000002800L});
    public static final BitSet FOLLOW_endcomment_in_begincomment727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_endcomment756 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_END_in_endcomment758 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_C_ED_in_endcomment760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_endcomment764 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_END_in_endcomment766 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_C_LN_ED_in_endcomment768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_bindcomment801 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_SYM_BIND_in_bindcomment803 = new BitSet(new long[]{0x0000000000202FF0L});
    public static final BitSet FOLLOW_expression_in_bindcomment805 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_C_ED_in_bindcomment807 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_inbindchars_in_bindcomment809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IN_in_inbind845 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_C_ST_in_inbind847 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_SYM_BIND_in_inbind849 = new BitSet(new long[]{0x0000000000202FF0L});
    public static final BitSet FOLLOW_expression_in_inbind851 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_C_ED_in_inbind853 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_inbindskipped_in_inbind855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SYM_LP_in_inbindskipped890 = new BitSet(new long[]{0x00000000000028F0L});
    public static final BitSet FOLLOW_txtcomment_in_inbindskipped895 = new BitSet(new long[]{0x00000000000028F0L});
    public static final BitSet FOLLOW_inbindchars_in_inbindskipped898 = new BitSet(new long[]{0x0000000000002D00L});
    public static final BitSet FOLLOW_txtcomment_in_inbindskipped900 = new BitSet(new long[]{0x0000000000002D00L});
    public static final BitSet FOLLOW_SYM_C_in_inbindskipped906 = new BitSet(new long[]{0x00000000000028F0L});
    public static final BitSet FOLLOW_txtcomment_in_inbindskipped912 = new BitSet(new long[]{0x00000000000028F0L});
    public static final BitSet FOLLOW_inbindchars_in_inbindskipped915 = new BitSet(new long[]{0x0000000000002D00L});
    public static final BitSet FOLLOW_txtcomment_in_inbindskipped917 = new BitSet(new long[]{0x0000000000002D00L});
    public static final BitSet FOLLOW_SYM_RP_in_inbindskipped926 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_inbindchars961 = new BitSet(new long[]{0x00000000000000F2L});

}