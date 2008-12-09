// $ANTLR 3.1.1 D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g 2008-12-09 18:11:22

package werkzeugkasten.twowaysql.grammar;

import werkzeugkasten.twowaysql.error.*;
import werkzeugkasten.twowaysql.tree.*;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class TwoWaySqlParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IDENT", "QUOTED", "SYMBOLS", "SYM_BIND", "SYM_C", "SYM_LP", "SYM_RP", "C_ST", "C_ED", "C_LN_ST", "C_LN_ED", "IF", "ELSEIF", "ELSE", "BEGIN", "END", "IN", "SYM_Q", "LN_R", "LN_N", "CHAR", "WS", "LT", "WHITE_SPACES"
    };
    public static final int LT=26;
    public static final int C_ST=11;
    public static final int QUOTED=5;
    public static final int SYM_RP=10;
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
    public static final int SYMBOLS=6;
    public static final int SYM_C=8;
    public static final int SYM_BIND=7;
    public static final int END=19;
    public static final int C_LN_ED=14;
    public static final int LN_N=23;
    public static final int LN_R=22;

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
    public String getGrammarFileName() { return "D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g"; }


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

    public void reportErrorDbg(RecognitionException ex) {
    	this.coordinator.report(ex);
    	ex.printStackTrace();
    	super.reportError(ex);
    }
    public void reportError(RecognitionException ex) {
    	this.coordinator.report(ex);
    }

    protected static final ExceptionMapper EM_TXT = new TxtExceptionMapper();
    protected static final ExceptionMapper EM_EXPRESSION = new ExpressionExceptionMapper();
    protected static final ExceptionMapper EM_BLOCKCOMMENT = new BlockCommentExceptionMapper();
    protected static final ExceptionMapper EM_LINECOMMENT = new LineCommentExceptionMapper();
    protected static final ExceptionMapper EM_ELSECOMMENT = new ElseCommentExceptionMapper();
    protected static final ExceptionMapper EM_ENDCOMMENT = new EndCommentExceptionMapper();



    public static class twowaySQL_return extends ParserRuleReturnScope {
        public TwoWayQuery query;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "twowaySQL"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:70:1: twowaySQL returns [TwoWayQuery query] : nodelist EOF ;
    public final TwoWaySqlParser.twowaySQL_return twowaySQL() throws RecognitionException {
        TwoWaySqlParser.twowaySQL_return retval = new TwoWaySqlParser.twowaySQL_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token EOF2=null;
        TwoWaySqlParser.nodelist_return nodelist1 = null;


        CommonTree EOF2_tree=null;


        		retval.query = new TwoWayQuery();
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:78:2: ( nodelist EOF )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:78:4: nodelist EOF
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
        	reportErrorDbg(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:83:1: nodelist returns [ArrayList<QueryNode> list] : ( comment | inbind | txt )+ ;
    public final TwoWaySqlParser.nodelist_return nodelist() throws RecognitionException {
        TwoWaySqlParser.nodelist_return retval = new TwoWaySqlParser.nodelist_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.comment_return comment3 = null;

        TwoWaySqlParser.inbind_return inbind4 = null;

        TwoWaySqlParser.txt_return txt5 = null;




        		retval.list = new ArrayList<QueryNode>();
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:87:2: ( ( comment | inbind | txt )+ )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:88:2: ( comment | inbind | txt )+
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:88:2: ( comment | inbind | txt )+
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
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:88:3: comment
            	    {
            	    pushFollow(FOLLOW_comment_in_nodelist123);
            	    comment3=comment();

            	    state._fsp--;

            	    adaptor.addChild(root_0, comment3.getTree());
            	    retval.list.add((comment3!=null?comment3.node:null));

            	    }
            	    break;
            	case 2 :
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:89:4: inbind
            	    {
            	    pushFollow(FOLLOW_inbind_in_nodelist130);
            	    inbind4=inbind();

            	    state._fsp--;

            	    adaptor.addChild(root_0, inbind4.getTree());
            	    retval.list.add((inbind4!=null?inbind4.node:null));

            	    }
            	    break;
            	case 3 :
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:90:4: txt
            	    {
            	    pushFollow(FOLLOW_txt_in_nodelist137);
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
        	reportErrorDbg(ex);
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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:95:1: charactors : ( IDENT | QUOTED | SYMBOLS | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+ ;
    public final TwoWaySqlParser.charactors_return charactors() throws RecognitionException {
        TwoWaySqlParser.charactors_return retval = new TwoWaySqlParser.charactors_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set6=null;

        CommonTree set6_tree=null;

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:96:2: ( ( IDENT | QUOTED | SYMBOLS | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+ )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:97:2: ( IDENT | QUOTED | SYMBOLS | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:97:2: ( IDENT | QUOTED | SYMBOLS | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+
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
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:
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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:105:1: txt returns [TxtNode node] : charactors ;
    public final TwoWaySqlParser.txt_return txt() throws RecognitionException {
        TwoWaySqlParser.txt_return retval = new TwoWaySqlParser.txt_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.charactors_return charactors7 = null;




        		push(EM_TXT);
        		retval.node = new TxtNode();
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:114:2: ( charactors )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:115:2: charactors
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_charactors_in_txt218);
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
        	reportErrorDbg(ex);
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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:121:1: comment returns [QueryNode node] : ( begincomment | ifcomment | bindcomment | blockcomment | linecomment );
    public final TwoWaySqlParser.comment_return comment() throws RecognitionException {
        TwoWaySqlParser.comment_return retval = new TwoWaySqlParser.comment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.begincomment_return begincomment8 = null;

        TwoWaySqlParser.ifcomment_return ifcomment9 = null;

        TwoWaySqlParser.bindcomment_return bindcomment10 = null;

        TwoWaySqlParser.blockcomment_return blockcomment11 = null;

        TwoWaySqlParser.linecomment_return linecomment12 = null;



        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:121:32: ( begincomment | ifcomment | bindcomment | blockcomment | linecomment )
            int alt3=5;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:122:2: begincomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_begincomment_in_comment238);
                    begincomment8=begincomment();

                    state._fsp--;

                    adaptor.addChild(root_0, begincomment8.getTree());
                    retval.node = (begincomment8!=null?begincomment8.node:null);

                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:123:4: ifcomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_ifcomment_in_comment245);
                    ifcomment9=ifcomment();

                    state._fsp--;

                    adaptor.addChild(root_0, ifcomment9.getTree());
                    retval.node = (ifcomment9!=null?ifcomment9.node:null);

                    }
                    break;
                case 3 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:124:4: bindcomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_bindcomment_in_comment252);
                    bindcomment10=bindcomment();

                    state._fsp--;

                    adaptor.addChild(root_0, bindcomment10.getTree());
                    retval.node = (bindcomment10!=null?bindcomment10.node:null);

                    }
                    break;
                case 4 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:125:4: blockcomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_blockcomment_in_comment259);
                    blockcomment11=blockcomment();

                    state._fsp--;

                    adaptor.addChild(root_0, blockcomment11.getTree());
                    retval.node = (blockcomment11!=null?blockcomment11.node:null);

                    }
                    break;
                case 5 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:126:4: linecomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_linecomment_in_comment266);
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
        	reportErrorDbg(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "comment"

    public static class blockcomment_return extends ParserRuleReturnScope {
        public TxtNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "blockcomment"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:129:1: blockcomment returns [TxtNode node] : C_ST charactors C_ED ;
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:138:2: ( C_ST charactors C_ED )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:139:2: C_ST charactors C_ED
            {
            root_0 = (CommonTree)adaptor.nil();

            C_ST13=(Token)match(input,C_ST,FOLLOW_C_ST_in_blockcomment295); 
            C_ST13_tree = (CommonTree)adaptor.create(C_ST13);
            adaptor.addChild(root_0, C_ST13_tree);

            pushFollow(FOLLOW_charactors_in_blockcomment297);
            charactors14=charactors();

            state._fsp--;

            adaptor.addChild(root_0, charactors14.getTree());
            C_ED15=(Token)match(input,C_ED,FOLLOW_C_ED_in_blockcomment299); 
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
        	reportErrorDbg(ex);
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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:143:1: linecomment returns [TxtNode node] : C_LN_ST charactors C_LN_ED ;
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:152:2: ( C_LN_ST charactors C_LN_ED )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:153:2: C_LN_ST charactors C_LN_ED
            {
            root_0 = (CommonTree)adaptor.nil();

            C_LN_ST16=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_linecomment331); 
            C_LN_ST16_tree = (CommonTree)adaptor.create(C_LN_ST16);
            adaptor.addChild(root_0, C_LN_ST16_tree);

            pushFollow(FOLLOW_charactors_in_linecomment333);
            charactors17=charactors();

            state._fsp--;

            adaptor.addChild(root_0, charactors17.getTree());
            C_LN_ED18=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_linecomment335); 
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
        	reportErrorDbg(ex);
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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:157:1: ifcomment returns [IfNode node] : ( C_ST IF expression C_ED nodelist ( elseifnode )* ( elsenode )? endcomment ) ;
    public final TwoWaySqlParser.ifcomment_return ifcomment() throws RecognitionException {
        TwoWaySqlParser.ifcomment_return retval = new TwoWaySqlParser.ifcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST19=null;
        Token IF20=null;
        Token C_ED22=null;
        TwoWaySqlParser.expression_return expression21 = null;

        TwoWaySqlParser.nodelist_return nodelist23 = null;

        TwoWaySqlParser.elseifnode_return elseifnode24 = null;

        TwoWaySqlParser.elsenode_return elsenode25 = null;

        TwoWaySqlParser.endcomment_return endcomment26 = null;


        CommonTree C_ST19_tree=null;
        CommonTree IF20_tree=null;
        CommonTree C_ED22_tree=null;


        		retval.node = new IfNode();
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:165:2: ( ( C_ST IF expression C_ED nodelist ( elseifnode )* ( elsenode )? endcomment ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:166:2: ( C_ST IF expression C_ED nodelist ( elseifnode )* ( elsenode )? endcomment )
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:166:2: ( C_ST IF expression C_ED nodelist ( elseifnode )* ( elsenode )? endcomment )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:166:3: C_ST IF expression C_ED nodelist ( elseifnode )* ( elsenode )? endcomment
            {
            C_ST19=(Token)match(input,C_ST,FOLLOW_C_ST_in_ifcomment368); 
            C_ST19_tree = (CommonTree)adaptor.create(C_ST19);
            adaptor.addChild(root_0, C_ST19_tree);

            IF20=(Token)match(input,IF,FOLLOW_IF_in_ifcomment370); 
            IF20_tree = (CommonTree)adaptor.create(IF20);
            adaptor.addChild(root_0, IF20_tree);

            pushFollow(FOLLOW_expression_in_ifcomment372);
            expression21=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression21.getTree());
            C_ED22=(Token)match(input,C_ED,FOLLOW_C_ED_in_ifcomment374); 
            C_ED22_tree = (CommonTree)adaptor.create(C_ED22);
            adaptor.addChild(root_0, C_ED22_tree);

             retval.node.setExpression((expression21!=null?expression21.node:null)); 
            pushFollow(FOLLOW_nodelist_in_ifcomment380);
            nodelist23=nodelist();

            state._fsp--;

            adaptor.addChild(root_0, nodelist23.getTree());
             retval.node.setChildren((nodelist23!=null?nodelist23.list:null));
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:168:3: ( elseifnode )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==C_ST) ) {
                    int LA4_1 = input.LA(2);

                    if ( (LA4_1==ELSEIF) ) {
                        alt4=1;
                    }


                }
                else if ( (LA4_0==C_LN_ST) ) {
                    int LA4_2 = input.LA(2);

                    if ( (LA4_2==ELSEIF) ) {
                        alt4=1;
                    }


                }


                switch (alt4) {
            	case 1 :
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:168:4: elseifnode
            	    {
            	    pushFollow(FOLLOW_elseifnode_in_ifcomment387);
            	    elseifnode24=elseifnode();

            	    state._fsp--;

            	    adaptor.addChild(root_0, elseifnode24.getTree());
            	     retval.node.addElseIf((elseifnode24!=null?elseifnode24.node:null)); 

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:169:3: ( elsenode )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==C_ST) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==ELSE) ) {
                    alt5=1;
                }
            }
            else if ( (LA5_0==C_LN_ST) ) {
                int LA5_2 = input.LA(2);

                if ( (LA5_2==ELSE) ) {
                    alt5=1;
                }
            }
            switch (alt5) {
                case 1 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:169:4: elsenode
                    {
                    pushFollow(FOLLOW_elsenode_in_ifcomment397);
                    elsenode25=elsenode();

                    state._fsp--;

                    adaptor.addChild(root_0, elsenode25.getTree());
                     retval.node.setElse((elsenode25!=null?elsenode25.list:null)); 

                    }
                    break;

            }

            pushFollow(FOLLOW_endcomment_in_ifcomment405);
            endcomment26=endcomment();

            state._fsp--;

            adaptor.addChild(root_0, endcomment26.getTree());

            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.node.update(retval);
            		retval.node.freeze();
            	
        }

        catch (RecognitionException ex) {
        	reportErrorDbg(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:174:1: elseifnode returns [IfNode node] : elseifcomment nodelist ;
    public final TwoWaySqlParser.elseifnode_return elseifnode() throws RecognitionException {
        TwoWaySqlParser.elseifnode_return retval = new TwoWaySqlParser.elseifnode_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.elseifcomment_return elseifcomment27 = null;

        TwoWaySqlParser.nodelist_return nodelist28 = null;




        		retval.node = new IfNode();
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:182:2: ( elseifcomment nodelist )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:183:2: elseifcomment nodelist
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_elseifcomment_in_elseifnode436);
            elseifcomment27=elseifcomment();

            state._fsp--;

            adaptor.addChild(root_0, elseifcomment27.getTree());
            pushFollow(FOLLOW_nodelist_in_elseifnode438);
            nodelist28=nodelist();

            state._fsp--;

            adaptor.addChild(root_0, nodelist28.getTree());

            		retval.node.setExpression((elseifcomment27!=null?elseifcomment27.node:null));
            		retval.node.setChildren((nodelist28!=null?nodelist28.list:null));
            	

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.node.update(retval);
            				retval.node.freeze();
            	
        }

        catch (RecognitionException ex) {
        	reportErrorDbg(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:190:1: elseifcomment returns [ExpressionNode node] : ( elseifblockcomment | elseiflinecomment ) ;
    public final TwoWaySqlParser.elseifcomment_return elseifcomment() throws RecognitionException {
        TwoWaySqlParser.elseifcomment_return retval = new TwoWaySqlParser.elseifcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.elseifblockcomment_return elseifblockcomment29 = null;

        TwoWaySqlParser.elseiflinecomment_return elseiflinecomment30 = null;



        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:191:2: ( ( elseifblockcomment | elseiflinecomment ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:192:2: ( elseifblockcomment | elseiflinecomment )
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:192:2: ( elseifblockcomment | elseiflinecomment )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==C_ST) ) {
                alt6=1;
            }
            else if ( (LA6_0==C_LN_ST) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:192:3: elseifblockcomment
                    {
                    pushFollow(FOLLOW_elseifblockcomment_in_elseifcomment458);
                    elseifblockcomment29=elseifblockcomment();

                    state._fsp--;

                    adaptor.addChild(root_0, elseifblockcomment29.getTree());
                    retval.node = (elseifblockcomment29!=null?elseifblockcomment29.node:null);

                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:193:5: elseiflinecomment
                    {
                    pushFollow(FOLLOW_elseiflinecomment_in_elseifcomment466);
                    elseiflinecomment30=elseiflinecomment();

                    state._fsp--;

                    adaptor.addChild(root_0, elseiflinecomment30.getTree());
                    retval.node = (elseiflinecomment30!=null?elseiflinecomment30.node:null);

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException ex) {
        	reportErrorDbg(ex);
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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:197:1: elseifblockcomment returns [ExpressionNode node] : C_ST ELSEIF expression C_ED ;
    public final TwoWaySqlParser.elseifblockcomment_return elseifblockcomment() throws RecognitionException {
        TwoWaySqlParser.elseifblockcomment_return retval = new TwoWaySqlParser.elseifblockcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST31=null;
        Token ELSEIF32=null;
        Token C_ED34=null;
        TwoWaySqlParser.expression_return expression33 = null;


        CommonTree C_ST31_tree=null;
        CommonTree ELSEIF32_tree=null;
        CommonTree C_ED34_tree=null;

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:198:2: ( C_ST ELSEIF expression C_ED )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:198:4: C_ST ELSEIF expression C_ED
            {
            root_0 = (CommonTree)adaptor.nil();

            C_ST31=(Token)match(input,C_ST,FOLLOW_C_ST_in_elseifblockcomment485); 
            C_ST31_tree = (CommonTree)adaptor.create(C_ST31);
            adaptor.addChild(root_0, C_ST31_tree);

            ELSEIF32=(Token)match(input,ELSEIF,FOLLOW_ELSEIF_in_elseifblockcomment487); 
            ELSEIF32_tree = (CommonTree)adaptor.create(ELSEIF32);
            adaptor.addChild(root_0, ELSEIF32_tree);

            pushFollow(FOLLOW_expression_in_elseifblockcomment489);
            expression33=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression33.getTree());
            C_ED34=(Token)match(input,C_ED,FOLLOW_C_ED_in_elseifblockcomment491); 
            C_ED34_tree = (CommonTree)adaptor.create(C_ED34);
            adaptor.addChild(root_0, C_ED34_tree);

             retval.node = (expression33!=null?expression33.node:null); 

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException ex) {
        	reportErrorDbg(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:201:1: elseiflinecomment returns [ExpressionNode node] : C_LN_ST ELSEIF expression C_LN_ED ;
    public final TwoWaySqlParser.elseiflinecomment_return elseiflinecomment() throws RecognitionException {
        TwoWaySqlParser.elseiflinecomment_return retval = new TwoWaySqlParser.elseiflinecomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_LN_ST35=null;
        Token ELSEIF36=null;
        Token C_LN_ED38=null;
        TwoWaySqlParser.expression_return expression37 = null;


        CommonTree C_LN_ST35_tree=null;
        CommonTree ELSEIF36_tree=null;
        CommonTree C_LN_ED38_tree=null;

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:202:2: ( C_LN_ST ELSEIF expression C_LN_ED )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:202:4: C_LN_ST ELSEIF expression C_LN_ED
            {
            root_0 = (CommonTree)adaptor.nil();

            C_LN_ST35=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_elseiflinecomment507); 
            C_LN_ST35_tree = (CommonTree)adaptor.create(C_LN_ST35);
            adaptor.addChild(root_0, C_LN_ST35_tree);

            ELSEIF36=(Token)match(input,ELSEIF,FOLLOW_ELSEIF_in_elseiflinecomment509); 
            ELSEIF36_tree = (CommonTree)adaptor.create(ELSEIF36);
            adaptor.addChild(root_0, ELSEIF36_tree);

            pushFollow(FOLLOW_expression_in_elseiflinecomment511);
            expression37=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression37.getTree());
            C_LN_ED38=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_elseiflinecomment513); 
            C_LN_ED38_tree = (CommonTree)adaptor.create(C_LN_ED38);
            adaptor.addChild(root_0, C_LN_ED38_tree);

             retval.node = (expression37!=null?expression37.node:null); 

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException ex) {
        	reportErrorDbg(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:205:1: elsenode returns [List<QueryNode> list] : elsecomment nodelist ;
    public final TwoWaySqlParser.elsenode_return elsenode() throws RecognitionException {
        TwoWaySqlParser.elsenode_return retval = new TwoWaySqlParser.elsenode_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.elsecomment_return elsecomment39 = null;

        TwoWaySqlParser.nodelist_return nodelist40 = null;



        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:206:2: ( elsecomment nodelist )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:207:2: elsecomment nodelist
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_elsecomment_in_elsenode530);
            elsecomment39=elsecomment();

            state._fsp--;

            adaptor.addChild(root_0, elsecomment39.getTree());
            pushFollow(FOLLOW_nodelist_in_elsenode532);
            nodelist40=nodelist();

            state._fsp--;

            adaptor.addChild(root_0, nodelist40.getTree());
             retval.list = (nodelist40!=null?nodelist40.list:null); 

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException ex) {
        	reportErrorDbg(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "elsenode"

    public static class elsecomment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elsecomment"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:210:1: elsecomment : ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED ) ;
    public final TwoWaySqlParser.elsecomment_return elsecomment() throws RecognitionException {
        TwoWaySqlParser.elsecomment_return retval = new TwoWaySqlParser.elsecomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST41=null;
        Token ELSE42=null;
        Token C_ED43=null;
        Token C_LN_ST44=null;
        Token ELSE45=null;
        Token C_LN_ED46=null;

        CommonTree C_ST41_tree=null;
        CommonTree ELSE42_tree=null;
        CommonTree C_ED43_tree=null;
        CommonTree C_LN_ST44_tree=null;
        CommonTree ELSE45_tree=null;
        CommonTree C_LN_ED46_tree=null;


        		push(EM_ELSECOMMENT);
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:214:2: ( ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:215:2: ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED )
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:215:2: ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==C_ST) ) {
                alt7=1;
            }
            else if ( (LA7_0==C_LN_ST) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:215:3: C_ST ELSE C_ED
                    {
                    C_ST41=(Token)match(input,C_ST,FOLLOW_C_ST_in_elsecomment554); 
                    C_ST41_tree = (CommonTree)adaptor.create(C_ST41);
                    adaptor.addChild(root_0, C_ST41_tree);

                    ELSE42=(Token)match(input,ELSE,FOLLOW_ELSE_in_elsecomment556); 
                    ELSE42_tree = (CommonTree)adaptor.create(ELSE42);
                    adaptor.addChild(root_0, ELSE42_tree);

                    C_ED43=(Token)match(input,C_ED,FOLLOW_C_ED_in_elsecomment558); 
                    C_ED43_tree = (CommonTree)adaptor.create(C_ED43);
                    adaptor.addChild(root_0, C_ED43_tree);


                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:215:20: C_LN_ST ELSE C_LN_ED
                    {
                    C_LN_ST44=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_elsecomment562); 
                    C_LN_ST44_tree = (CommonTree)adaptor.create(C_LN_ST44);
                    adaptor.addChild(root_0, C_LN_ST44_tree);

                    ELSE45=(Token)match(input,ELSE,FOLLOW_ELSE_in_elsecomment564); 
                    ELSE45_tree = (CommonTree)adaptor.create(ELSE45);
                    adaptor.addChild(root_0, ELSE45_tree);

                    C_LN_ED46=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_elsecomment566); 
                    C_LN_ED46_tree = (CommonTree)adaptor.create(C_LN_ED46);
                    adaptor.addChild(root_0, C_LN_ED46_tree);


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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:224:1: expression returns [ExpressionNode node] : charactors ;
    public final TwoWaySqlParser.expression_return expression() throws RecognitionException {
        TwoWaySqlParser.expression_return retval = new TwoWaySqlParser.expression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.charactors_return charactors47 = null;




        		push(EM_EXPRESSION);
        		retval.node = new ExpressionNode();
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:233:2: ( charactors )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:234:2: charactors
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_charactors_in_expression606);
            charactors47=charactors();

            state._fsp--;

            adaptor.addChild(root_0, charactors47.getTree());

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.node.update(retval);
            		retval.node.freeze();
            	
        }

        catch (RecognitionException ex) {
        	reportErrorDbg(ex);
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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:238:1: begincomment returns [BeginNode node] : ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) nodelist endcomment ) ;
    public final TwoWaySqlParser.begincomment_return begincomment() throws RecognitionException {
        TwoWaySqlParser.begincomment_return retval = new TwoWaySqlParser.begincomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST48=null;
        Token BEGIN49=null;
        Token C_ED50=null;
        Token C_LN_ST51=null;
        Token BEGIN52=null;
        Token C_LN_ED53=null;
        TwoWaySqlParser.nodelist_return nodelist54 = null;

        TwoWaySqlParser.endcomment_return endcomment55 = null;


        CommonTree C_ST48_tree=null;
        CommonTree BEGIN49_tree=null;
        CommonTree C_ED50_tree=null;
        CommonTree C_LN_ST51_tree=null;
        CommonTree BEGIN52_tree=null;
        CommonTree C_LN_ED53_tree=null;


        		retval.node = new BeginNode();
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:246:2: ( ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) nodelist endcomment ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:247:2: ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) nodelist endcomment )
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:247:2: ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) nodelist endcomment )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:248:3: ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) nodelist endcomment
            {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:248:3: ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED )
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
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:248:4: C_ST BEGIN C_ED
                    {
                    C_ST48=(Token)match(input,C_ST,FOLLOW_C_ST_in_begincomment644); 
                    C_ST48_tree = (CommonTree)adaptor.create(C_ST48);
                    adaptor.addChild(root_0, C_ST48_tree);

                    BEGIN49=(Token)match(input,BEGIN,FOLLOW_BEGIN_in_begincomment646); 
                    BEGIN49_tree = (CommonTree)adaptor.create(BEGIN49);
                    adaptor.addChild(root_0, BEGIN49_tree);

                    C_ED50=(Token)match(input,C_ED,FOLLOW_C_ED_in_begincomment648); 
                    C_ED50_tree = (CommonTree)adaptor.create(C_ED50);
                    adaptor.addChild(root_0, C_ED50_tree);


                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:248:22: C_LN_ST BEGIN C_LN_ED
                    {
                    C_LN_ST51=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_begincomment652); 
                    C_LN_ST51_tree = (CommonTree)adaptor.create(C_LN_ST51);
                    adaptor.addChild(root_0, C_LN_ST51_tree);

                    BEGIN52=(Token)match(input,BEGIN,FOLLOW_BEGIN_in_begincomment654); 
                    BEGIN52_tree = (CommonTree)adaptor.create(BEGIN52);
                    adaptor.addChild(root_0, BEGIN52_tree);

                    C_LN_ED53=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_begincomment656); 
                    C_LN_ED53_tree = (CommonTree)adaptor.create(C_LN_ED53);
                    adaptor.addChild(root_0, C_LN_ED53_tree);


                    }
                    break;

            }

            pushFollow(FOLLOW_nodelist_in_begincomment659);
            nodelist54=nodelist();

            state._fsp--;

            adaptor.addChild(root_0, nodelist54.getTree());
            pushFollow(FOLLOW_endcomment_in_begincomment661);
            endcomment55=endcomment();

            state._fsp--;

            adaptor.addChild(root_0, endcomment55.getTree());

            }


            		retval.node.setChildren((nodelist54!=null?nodelist54.list:null));
            	

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.node.update(retval);
            		retval.node.freeze();
            	
        }

        catch (RecognitionException ex) {
        	reportErrorDbg(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "begincomment"

    public static class endcomment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "endcomment"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:255:1: endcomment : ( C_ST END C_ED | C_LN_ST END C_LN_ED );
    public final TwoWaySqlParser.endcomment_return endcomment() throws RecognitionException {
        TwoWaySqlParser.endcomment_return retval = new TwoWaySqlParser.endcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST56=null;
        Token END57=null;
        Token C_ED58=null;
        Token C_LN_ST59=null;
        Token END60=null;
        Token C_LN_ED61=null;

        CommonTree C_ST56_tree=null;
        CommonTree END57_tree=null;
        CommonTree C_ED58_tree=null;
        CommonTree C_LN_ST59_tree=null;
        CommonTree END60_tree=null;
        CommonTree C_LN_ED61_tree=null;


        		push(EM_ENDCOMMENT);
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:259:2: ( C_ST END C_ED | C_LN_ST END C_LN_ED )
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
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:260:2: C_ST END C_ED
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    C_ST56=(Token)match(input,C_ST,FOLLOW_C_ST_in_endcomment685); 
                    C_ST56_tree = (CommonTree)adaptor.create(C_ST56);
                    adaptor.addChild(root_0, C_ST56_tree);

                    END57=(Token)match(input,END,FOLLOW_END_in_endcomment687); 
                    END57_tree = (CommonTree)adaptor.create(END57);
                    adaptor.addChild(root_0, END57_tree);

                    C_ED58=(Token)match(input,C_ED,FOLLOW_C_ED_in_endcomment689); 
                    C_ED58_tree = (CommonTree)adaptor.create(C_ED58);
                    adaptor.addChild(root_0, C_ED58_tree);


                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:260:18: C_LN_ST END C_LN_ED
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    C_LN_ST59=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_endcomment693); 
                    C_LN_ST59_tree = (CommonTree)adaptor.create(C_LN_ST59);
                    adaptor.addChild(root_0, C_LN_ST59_tree);

                    END60=(Token)match(input,END,FOLLOW_END_in_endcomment695); 
                    END60_tree = (CommonTree)adaptor.create(END60);
                    adaptor.addChild(root_0, END60_tree);

                    C_LN_ED61=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_endcomment697); 
                    C_LN_ED61_tree = (CommonTree)adaptor.create(C_LN_ED61);
                    adaptor.addChild(root_0, C_LN_ED61_tree);


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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:269:1: bindcomment returns [BindNode node] : ( C_ST SYM_BIND expression C_ED txt ) ;
    public final TwoWaySqlParser.bindcomment_return bindcomment() throws RecognitionException {
        TwoWaySqlParser.bindcomment_return retval = new TwoWaySqlParser.bindcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST62=null;
        Token SYM_BIND63=null;
        Token C_ED65=null;
        TwoWaySqlParser.expression_return expression64 = null;

        TwoWaySqlParser.txt_return txt66 = null;


        CommonTree C_ST62_tree=null;
        CommonTree SYM_BIND63_tree=null;
        CommonTree C_ED65_tree=null;


        		retval.node = new BindNode();
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:277:2: ( ( C_ST SYM_BIND expression C_ED txt ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:278:2: ( C_ST SYM_BIND expression C_ED txt )
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:278:2: ( C_ST SYM_BIND expression C_ED txt )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:278:3: C_ST SYM_BIND expression C_ED txt
            {
            C_ST62=(Token)match(input,C_ST,FOLLOW_C_ST_in_bindcomment737); 
            C_ST62_tree = (CommonTree)adaptor.create(C_ST62);
            adaptor.addChild(root_0, C_ST62_tree);

            SYM_BIND63=(Token)match(input,SYM_BIND,FOLLOW_SYM_BIND_in_bindcomment739); 
            SYM_BIND63_tree = (CommonTree)adaptor.create(SYM_BIND63);
            adaptor.addChild(root_0, SYM_BIND63_tree);

            pushFollow(FOLLOW_expression_in_bindcomment741);
            expression64=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression64.getTree());
            C_ED65=(Token)match(input,C_ED,FOLLOW_C_ED_in_bindcomment743); 
            C_ED65_tree = (CommonTree)adaptor.create(C_ED65);
            adaptor.addChild(root_0, C_ED65_tree);

            pushFollow(FOLLOW_txt_in_bindcomment745);
            txt66=txt();

            state._fsp--;

            adaptor.addChild(root_0, txt66.getTree());

            }


            		retval.node.setExpression((expression64!=null?expression64.node:null));
            		retval.node.setSkipped((txt66!=null?txt66.node:null));
            	

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.node.update(retval);
            		retval.node.freeze();
            	
        }

        catch (RecognitionException ex) {
        	reportErrorDbg(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:285:1: inbind returns [InBindNode node] : IN C_ST SYM_BIND expression C_ED SYM_LP inbindchars ( SYM_C inbindchars )* SYM_RP ;
    public final TwoWaySqlParser.inbind_return inbind() throws RecognitionException {
        TwoWaySqlParser.inbind_return retval = new TwoWaySqlParser.inbind_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IN67=null;
        Token C_ST68=null;
        Token SYM_BIND69=null;
        Token C_ED71=null;
        Token SYM_LP72=null;
        Token SYM_C74=null;
        Token SYM_RP76=null;
        TwoWaySqlParser.expression_return expression70 = null;

        TwoWaySqlParser.inbindchars_return inbindchars73 = null;

        TwoWaySqlParser.inbindchars_return inbindchars75 = null;


        CommonTree IN67_tree=null;
        CommonTree C_ST68_tree=null;
        CommonTree SYM_BIND69_tree=null;
        CommonTree C_ED71_tree=null;
        CommonTree SYM_LP72_tree=null;
        CommonTree SYM_C74_tree=null;
        CommonTree SYM_RP76_tree=null;


        		retval.node = new InBindNode();
        		TxtNode skip = new TxtNode();
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:295:2: ( IN C_ST SYM_BIND expression C_ED SYM_LP inbindchars ( SYM_C inbindchars )* SYM_RP )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:296:2: IN C_ST SYM_BIND expression C_ED SYM_LP inbindchars ( SYM_C inbindchars )* SYM_RP
            {
            root_0 = (CommonTree)adaptor.nil();

            IN67=(Token)match(input,IN,FOLLOW_IN_in_inbind776); 
            IN67_tree = (CommonTree)adaptor.create(IN67);
            adaptor.addChild(root_0, IN67_tree);

            C_ST68=(Token)match(input,C_ST,FOLLOW_C_ST_in_inbind778); 
            C_ST68_tree = (CommonTree)adaptor.create(C_ST68);
            adaptor.addChild(root_0, C_ST68_tree);

            SYM_BIND69=(Token)match(input,SYM_BIND,FOLLOW_SYM_BIND_in_inbind780); 
            SYM_BIND69_tree = (CommonTree)adaptor.create(SYM_BIND69);
            adaptor.addChild(root_0, SYM_BIND69_tree);

            pushFollow(FOLLOW_expression_in_inbind782);
            expression70=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression70.getTree());
            C_ED71=(Token)match(input,C_ED,FOLLOW_C_ED_in_inbind784); 
            C_ED71_tree = (CommonTree)adaptor.create(C_ED71);
            adaptor.addChild(root_0, C_ED71_tree);

            SYM_LP72=(Token)match(input,SYM_LP,FOLLOW_SYM_LP_in_inbind786); 
            SYM_LP72_tree = (CommonTree)adaptor.create(SYM_LP72);
            adaptor.addChild(root_0, SYM_LP72_tree);

            pushFollow(FOLLOW_inbindchars_in_inbind788);
            inbindchars73=inbindchars();

            state._fsp--;

            adaptor.addChild(root_0, inbindchars73.getTree());
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:296:54: ( SYM_C inbindchars )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==SYM_C) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:296:55: SYM_C inbindchars
            	    {
            	    SYM_C74=(Token)match(input,SYM_C,FOLLOW_SYM_C_in_inbind791); 
            	    SYM_C74_tree = (CommonTree)adaptor.create(SYM_C74);
            	    adaptor.addChild(root_0, SYM_C74_tree);

            	    pushFollow(FOLLOW_inbindchars_in_inbind793);
            	    inbindchars75=inbindchars();

            	    state._fsp--;

            	    adaptor.addChild(root_0, inbindchars75.getTree());

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            SYM_RP76=(Token)match(input,SYM_RP,FOLLOW_SYM_RP_in_inbind797); 
            SYM_RP76_tree = (CommonTree)adaptor.create(SYM_RP76);
            adaptor.addChild(root_0, SYM_RP76_tree);


            		retval.node.setExpression((expression70!=null?expression70.node:null));
            		skip.update(SYM_RP76);
            		skip.update(SYM_LP72);
            		retval.node.setSkipped(skip);
            	

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		skip.freeze();
            		retval.node.update(retval);
            		retval.node.freeze();
            	
        }

        catch (RecognitionException ex) {
        	reportErrorDbg(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "inbind"

    public static class inbindchars_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "inbindchars"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:305:1: inbindchars : ( IDENT | QUOTED | SYMBOLS | SYM_BIND )+ ;
    public final TwoWaySqlParser.inbindchars_return inbindchars() throws RecognitionException {
        TwoWaySqlParser.inbindchars_return retval = new TwoWaySqlParser.inbindchars_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set77=null;

        CommonTree set77_tree=null;

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:306:2: ( ( IDENT | QUOTED | SYMBOLS | SYM_BIND )+ )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:307:2: ( IDENT | QUOTED | SYMBOLS | SYM_BIND )+
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:307:2: ( IDENT | QUOTED | SYMBOLS | SYM_BIND )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>=IDENT && LA11_0<=SYM_BIND)) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:
            	    {
            	    set77=(Token)input.LT(1);
            	    if ( (input.LA(1)>=IDENT && input.LA(1)<=SYM_BIND) ) {
            	        input.consume();
            	        adaptor.addChild(root_0, (CommonTree)adaptor.create(set77));
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException ex) {
        	reportErrorDbg(ex);
        	recover(input,ex);
        	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), ex);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "inbindchars"

    // Delegated rules


    protected DFA3 dfa3 = new DFA3(this);
    static final String DFA3_eotS =
        "\13\uffff";
    static final String DFA3_eofS =
        "\11\uffff\1\6\1\uffff";
    static final String DFA3_minS =
        "\1\13\2\4\2\uffff\1\4\2\uffff\2\4\1\uffff";
    static final String DFA3_maxS =
        "\1\15\2\22\2\uffff\1\14\2\uffff\1\14\1\24\1\uffff";
    static final String DFA3_acceptS =
        "\3\uffff\1\1\1\2\1\uffff\1\4\1\5\2\uffff\1\3";
    static final String DFA3_specialS =
        "\13\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\1\1\uffff\1\2",
            "\3\6\1\5\3\6\4\uffff\1\4\2\uffff\1\3",
            "\7\7\7\uffff\1\3",
            "",
            "",
            "\7\10\1\uffff\1\6",
            "",
            "",
            "\7\10\1\uffff\1\11",
            "\7\12\1\6\1\uffff\1\6\6\uffff\1\6",
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
            return "121:1: comment returns [QueryNode node] : ( begincomment | ifcomment | bindcomment | blockcomment | linecomment );";
        }
    }
 

    public static final BitSet FOLLOW_nodelist_in_twowaySQL98 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_twowaySQL100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comment_in_nodelist123 = new BitSet(new long[]{0x0000000000102FF2L});
    public static final BitSet FOLLOW_inbind_in_nodelist130 = new BitSet(new long[]{0x0000000000102FF2L});
    public static final BitSet FOLLOW_txt_in_nodelist137 = new BitSet(new long[]{0x0000000000102FF2L});
    public static final BitSet FOLLOW_set_in_charactors156 = new BitSet(new long[]{0x00000000000007F2L});
    public static final BitSet FOLLOW_charactors_in_txt218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_begincomment_in_comment238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifcomment_in_comment245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bindcomment_in_comment252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_blockcomment_in_comment259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_linecomment_in_comment266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_blockcomment295 = new BitSet(new long[]{0x0000000000103FF0L});
    public static final BitSet FOLLOW_charactors_in_blockcomment297 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_C_ED_in_blockcomment299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_linecomment331 = new BitSet(new long[]{0x0000000000106FF0L});
    public static final BitSet FOLLOW_charactors_in_linecomment333 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_C_LN_ED_in_linecomment335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_ifcomment368 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_IF_in_ifcomment370 = new BitSet(new long[]{0x0000000000102FF0L});
    public static final BitSet FOLLOW_expression_in_ifcomment372 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_C_ED_in_ifcomment374 = new BitSet(new long[]{0x0000000000102FF0L});
    public static final BitSet FOLLOW_nodelist_in_ifcomment380 = new BitSet(new long[]{0x0000000000002800L});
    public static final BitSet FOLLOW_elseifnode_in_ifcomment387 = new BitSet(new long[]{0x0000000000002800L});
    public static final BitSet FOLLOW_elsenode_in_ifcomment397 = new BitSet(new long[]{0x0000000000002800L});
    public static final BitSet FOLLOW_endcomment_in_ifcomment405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elseifcomment_in_elseifnode436 = new BitSet(new long[]{0x0000000000102FF0L});
    public static final BitSet FOLLOW_nodelist_in_elseifnode438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elseifblockcomment_in_elseifcomment458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elseiflinecomment_in_elseifcomment466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_elseifblockcomment485 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ELSEIF_in_elseifblockcomment487 = new BitSet(new long[]{0x0000000000102FF0L});
    public static final BitSet FOLLOW_expression_in_elseifblockcomment489 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_C_ED_in_elseifblockcomment491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_elseiflinecomment507 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ELSEIF_in_elseiflinecomment509 = new BitSet(new long[]{0x0000000000102FF0L});
    public static final BitSet FOLLOW_expression_in_elseiflinecomment511 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_C_LN_ED_in_elseiflinecomment513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elsecomment_in_elsenode530 = new BitSet(new long[]{0x0000000000102FF0L});
    public static final BitSet FOLLOW_nodelist_in_elsenode532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_elsecomment554 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_ELSE_in_elsecomment556 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_C_ED_in_elsecomment558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_elsecomment562 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_ELSE_in_elsecomment564 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_C_LN_ED_in_elsecomment566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_charactors_in_expression606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_begincomment644 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_BEGIN_in_begincomment646 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_C_ED_in_begincomment648 = new BitSet(new long[]{0x0000000000102FF0L});
    public static final BitSet FOLLOW_C_LN_ST_in_begincomment652 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_BEGIN_in_begincomment654 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_C_LN_ED_in_begincomment656 = new BitSet(new long[]{0x0000000000102FF0L});
    public static final BitSet FOLLOW_nodelist_in_begincomment659 = new BitSet(new long[]{0x0000000000002800L});
    public static final BitSet FOLLOW_endcomment_in_begincomment661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_endcomment685 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_END_in_endcomment687 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_C_ED_in_endcomment689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_endcomment693 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_END_in_endcomment695 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_C_LN_ED_in_endcomment697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_bindcomment737 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_SYM_BIND_in_bindcomment739 = new BitSet(new long[]{0x0000000000102FF0L});
    public static final BitSet FOLLOW_expression_in_bindcomment741 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_C_ED_in_bindcomment743 = new BitSet(new long[]{0x0000000000102FF0L});
    public static final BitSet FOLLOW_txt_in_bindcomment745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IN_in_inbind776 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_C_ST_in_inbind778 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_SYM_BIND_in_inbind780 = new BitSet(new long[]{0x0000000000102FF0L});
    public static final BitSet FOLLOW_expression_in_inbind782 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_C_ED_in_inbind784 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_SYM_LP_in_inbind786 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_inbindchars_in_inbind788 = new BitSet(new long[]{0x0000000000000500L});
    public static final BitSet FOLLOW_SYM_C_in_inbind791 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_inbindchars_in_inbind793 = new BitSet(new long[]{0x0000000000000500L});
    public static final BitSet FOLLOW_SYM_RP_in_inbind797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_inbindchars812 = new BitSet(new long[]{0x00000000000000F2L});

}