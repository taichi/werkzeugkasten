// $ANTLR 3.1.1 D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g 2009-01-06 16:49:57

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
    public String getGrammarFileName() { return "D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g"; }


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
    protected static final ExceptionMapper EM_BINDINGNAME = new BindingNameExceptionMapper();
    protected static final ExceptionMapper EM_INBIND = new InBindExceptionMapper();
    protected static final ExceptionMapper EM_INBINDSKIPPED = new InBindSkippedExceptionMapper();



    public static class twowaySQL_return extends ParserRuleReturnScope {
        public TwoWayQuery query;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "twowaySQL"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:112:1: twowaySQL returns [TwoWayQuery query] : nodelist EOF ;
    public final TwoWaySqlParser.twowaySQL_return twowaySQL() throws RecognitionException {
        TwoWaySqlParser.twowaySQL_return retval = new TwoWaySqlParser.twowaySQL_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token EOF2=null;
        TwoWaySqlParser.nodelist_return nodelist1 = null;


        CommonTree EOF2_tree=null;


        		retval.query = new TwoWayQuery();
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:120:2: ( nodelist EOF )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:120:4: nodelist EOF
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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:128:1: nodelist returns [ArrayList<QueryNode> list] : ( comment | inbind | txt )+ ;
    public final TwoWaySqlParser.nodelist_return nodelist() throws RecognitionException {
        TwoWaySqlParser.nodelist_return retval = new TwoWaySqlParser.nodelist_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.comment_return comment3 = null;

        TwoWaySqlParser.inbind_return inbind4 = null;

        TwoWaySqlParser.txt_return txt5 = null;




        		retval.list = new ArrayList<QueryNode>();
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:133:2: ( ( comment | inbind | txt )+ )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:134:2: ( comment | inbind | txt )+
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:134:2: ( comment | inbind | txt )+
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
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:134:3: comment
            	    {
            	    pushFollow(FOLLOW_comment_in_nodelist130);
            	    comment3=comment();

            	    state._fsp--;

            	    adaptor.addChild(root_0, comment3.getTree());
            	    retval.list.add((comment3!=null?comment3.node:null));

            	    }
            	    break;
            	case 2 :
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:135:4: inbind
            	    {
            	    pushFollow(FOLLOW_inbind_in_nodelist137);
            	    inbind4=inbind();

            	    state._fsp--;

            	    adaptor.addChild(root_0, inbind4.getTree());
            	    retval.list.add((inbind4!=null?inbind4.node:null));

            	    }
            	    break;
            	case 3 :
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:136:4: txt
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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:140:1: charactors : ( IDENT | QUOTED | MAYBE_SKIP | SYMBOLS | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+ ;
    public final TwoWaySqlParser.charactors_return charactors() throws RecognitionException {
        TwoWaySqlParser.charactors_return retval = new TwoWaySqlParser.charactors_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set6=null;

        CommonTree set6_tree=null;

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:142:2: ( ( IDENT | QUOTED | MAYBE_SKIP | SYMBOLS | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+ )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:143:2: ( IDENT | QUOTED | MAYBE_SKIP | SYMBOLS | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:143:2: ( IDENT | QUOTED | MAYBE_SKIP | SYMBOLS | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+
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
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:
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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:146:1: txt returns [TxtNode node] : charactors ;
    public final TwoWaySqlParser.txt_return txt() throws RecognitionException {
        TwoWaySqlParser.txt_return retval = new TwoWaySqlParser.txt_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.charactors_return charactors7 = null;




        		push(EM_TXT);
        		retval.node = new TxtNode();
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:155:2: ( charactors )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:156:2: charactors
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_charactors_in_txt223);
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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:162:1: comment returns [QueryNode node] : ( begincomment | ifcomment | bindcomment | txtcomment );
    public final TwoWaySqlParser.comment_return comment() throws RecognitionException {
        TwoWaySqlParser.comment_return retval = new TwoWaySqlParser.comment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.begincomment_return begincomment8 = null;

        TwoWaySqlParser.ifcomment_return ifcomment9 = null;

        TwoWaySqlParser.bindcomment_return bindcomment10 = null;

        TwoWaySqlParser.txtcomment_return txtcomment11 = null;



        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:162:32: ( begincomment | ifcomment | bindcomment | txtcomment )
            int alt3=4;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:163:2: begincomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_begincomment_in_comment243);
                    begincomment8=begincomment();

                    state._fsp--;

                    adaptor.addChild(root_0, begincomment8.getTree());
                    retval.node = (begincomment8!=null?begincomment8.node:null);

                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:164:4: ifcomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_ifcomment_in_comment250);
                    ifcomment9=ifcomment();

                    state._fsp--;

                    adaptor.addChild(root_0, ifcomment9.getTree());
                    retval.node = (ifcomment9!=null?ifcomment9.node:null);

                    }
                    break;
                case 3 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:165:4: bindcomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_bindcomment_in_comment257);
                    bindcomment10=bindcomment();

                    state._fsp--;

                    adaptor.addChild(root_0, bindcomment10.getTree());
                    retval.node = (bindcomment10!=null?bindcomment10.node:null);

                    }
                    break;
                case 4 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:166:4: txtcomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_txtcomment_in_comment264);
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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:169:1: txtcomment returns [TxtNode node] : ( blockcomment | linecomment );
    public final TwoWaySqlParser.txtcomment_return txtcomment() throws RecognitionException {
        TwoWaySqlParser.txtcomment_return retval = new TwoWaySqlParser.txtcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.blockcomment_return blockcomment12 = null;

        TwoWaySqlParser.linecomment_return linecomment13 = null;



        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:170:2: ( blockcomment | linecomment )
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
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:170:4: blockcomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_blockcomment_in_txtcomment280);
                    blockcomment12=blockcomment();

                    state._fsp--;

                    adaptor.addChild(root_0, blockcomment12.getTree());
                    retval.node = (blockcomment12!=null?blockcomment12.node:null);

                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:171:4: linecomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_linecomment_in_txtcomment287);
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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:174:1: blockcomment returns [TxtNode node] : C_ST charactors C_ED ;
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:183:2: ( C_ST charactors C_ED )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:184:2: C_ST charactors C_ED
            {
            root_0 = (CommonTree)adaptor.nil();

            C_ST14=(Token)match(input,C_ST,FOLLOW_C_ST_in_blockcomment317); 
            C_ST14_tree = (CommonTree)adaptor.create(C_ST14);
            adaptor.addChild(root_0, C_ST14_tree);

            pushFollow(FOLLOW_charactors_in_blockcomment319);
            charactors15=charactors();

            state._fsp--;

            adaptor.addChild(root_0, charactors15.getTree());
            C_ED16=(Token)match(input,C_ED,FOLLOW_C_ED_in_blockcomment321); 
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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:188:1: linecomment returns [TxtNode node] : C_LN_ST charactors C_LN_ED ;
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:197:2: ( C_LN_ST charactors C_LN_ED )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:198:2: C_LN_ST charactors C_LN_ED
            {
            root_0 = (CommonTree)adaptor.nil();

            C_LN_ST17=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_linecomment353); 
            C_LN_ST17_tree = (CommonTree)adaptor.create(C_LN_ST17);
            adaptor.addChild(root_0, C_LN_ST17_tree);

            pushFollow(FOLLOW_charactors_in_linecomment355);
            charactors18=charactors();

            state._fsp--;

            adaptor.addChild(root_0, charactors18.getTree());
            C_LN_ED19=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_linecomment357); 
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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:202:1: ifcomment returns [IfNode node] : ( C_ST IF expression C_ED ( MAYBE_SKIP )? nodelist ( elseifnode )* ( elsenode )? endcomment ) ;
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
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:211:2: ( ( C_ST IF expression C_ED ( MAYBE_SKIP )? nodelist ( elseifnode )* ( elsenode )? endcomment ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:212:2: ( C_ST IF expression C_ED ( MAYBE_SKIP )? nodelist ( elseifnode )* ( elsenode )? endcomment )
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:212:2: ( C_ST IF expression C_ED ( MAYBE_SKIP )? nodelist ( elseifnode )* ( elsenode )? endcomment )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:212:3: C_ST IF expression C_ED ( MAYBE_SKIP )? nodelist ( elseifnode )* ( elsenode )? endcomment
            {
            C_ST20=(Token)match(input,C_ST,FOLLOW_C_ST_in_ifcomment390); 
            C_ST20_tree = (CommonTree)adaptor.create(C_ST20);
            adaptor.addChild(root_0, C_ST20_tree);

            IF21=(Token)match(input,IF,FOLLOW_IF_in_ifcomment392); 
            IF21_tree = (CommonTree)adaptor.create(IF21);
            adaptor.addChild(root_0, IF21_tree);

            pushFollow(FOLLOW_expression_in_ifcomment394);
            expression22=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression22.getTree());
            C_ED23=(Token)match(input,C_ED,FOLLOW_C_ED_in_ifcomment396); 
            C_ED23_tree = (CommonTree)adaptor.create(C_ED23);
            adaptor.addChild(root_0, C_ED23_tree);

             retval.node.setExpression((expression22!=null?expression22.node:null)); 
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:213:3: ( MAYBE_SKIP )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==MAYBE_SKIP) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:213:4: MAYBE_SKIP
                    {
                    MAYBE_SKIP24=(Token)match(input,MAYBE_SKIP,FOLLOW_MAYBE_SKIP_in_ifcomment403); 
                    MAYBE_SKIP24_tree = (CommonTree)adaptor.create(MAYBE_SKIP24);
                    adaptor.addChild(root_0, MAYBE_SKIP24_tree);


                    			TxtNode skip = new TxtNode();
                    			skip.update(MAYBE_SKIP24);
                    			skip.freeze();
                    			retval.node.setMaybeSkip(skip);
                    		

                    }
                    break;

            }

            pushFollow(FOLLOW_nodelist_in_ifcomment412);
            nodelist25=nodelist();

            state._fsp--;

            adaptor.addChild(root_0, nodelist25.getTree());
             retval.node.setChildren((nodelist25!=null?nodelist25.list:null));
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:220:3: ( elseifnode )*
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
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:220:4: elseifnode
            	    {
            	    pushFollow(FOLLOW_elseifnode_in_ifcomment419);
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

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:221:3: ( elsenode )?
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
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:221:4: elsenode
                    {
                    pushFollow(FOLLOW_elsenode_in_ifcomment429);
                    elsenode27=elsenode();

                    state._fsp--;

                    adaptor.addChild(root_0, elsenode27.getTree());
                     retval.node.setElse((elsenode27!=null?elsenode27.node:null)); 

                    }
                    break;

            }

            pushFollow(FOLLOW_endcomment_in_ifcomment437);
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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:227:1: elseifnode returns [IfNode node] : elseifcomment ( MAYBE_SKIP )? nodelist ;
    public final TwoWaySqlParser.elseifnode_return elseifnode() throws RecognitionException {
        TwoWaySqlParser.elseifnode_return retval = new TwoWaySqlParser.elseifnode_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token MAYBE_SKIP30=null;
        TwoWaySqlParser.elseifcomment_return elseifcomment29 = null;

        TwoWaySqlParser.nodelist_return nodelist31 = null;


        CommonTree MAYBE_SKIP30_tree=null;


        		push(EM_ELSEIFNODE);
        		retval.node = new IfNode();
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:236:2: ( elseifcomment ( MAYBE_SKIP )? nodelist )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:237:2: elseifcomment ( MAYBE_SKIP )? nodelist
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_elseifcomment_in_elseifnode473);
            elseifcomment29=elseifcomment();

            state._fsp--;

            adaptor.addChild(root_0, elseifcomment29.getTree());
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:238:2: ( MAYBE_SKIP )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==MAYBE_SKIP) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:238:3: MAYBE_SKIP
                    {
                    MAYBE_SKIP30=(Token)match(input,MAYBE_SKIP,FOLLOW_MAYBE_SKIP_in_elseifnode477); 
                    MAYBE_SKIP30_tree = (CommonTree)adaptor.create(MAYBE_SKIP30);
                    adaptor.addChild(root_0, MAYBE_SKIP30_tree);


                    		TxtNode skip = new TxtNode();
                    		skip.update(MAYBE_SKIP30);
                    		skip.freeze();
                    		retval.node.setMaybeSkip(skip);
                    	

                    }
                    break;

            }

            pushFollow(FOLLOW_nodelist_in_elseifnode486);
            nodelist31=nodelist();

            state._fsp--;

            adaptor.addChild(root_0, nodelist31.getTree());

            		retval.node.setExpression((elseifcomment29!=null?elseifcomment29.node:null));
            		retval.node.setChildren((nodelist31!=null?nodelist31.list:null));
            	

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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:253:1: elseifcomment returns [ExpressionNode node] : ( elseifblockcomment | elseiflinecomment ) ;
    public final TwoWaySqlParser.elseifcomment_return elseifcomment() throws RecognitionException {
        TwoWaySqlParser.elseifcomment_return retval = new TwoWaySqlParser.elseifcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.elseifblockcomment_return elseifblockcomment32 = null;

        TwoWaySqlParser.elseiflinecomment_return elseiflinecomment33 = null;



        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:255:2: ( ( elseifblockcomment | elseiflinecomment ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:256:2: ( elseifblockcomment | elseiflinecomment )
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:256:2: ( elseifblockcomment | elseiflinecomment )
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
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:256:3: elseifblockcomment
                    {
                    pushFollow(FOLLOW_elseifblockcomment_in_elseifcomment513);
                    elseifblockcomment32=elseifblockcomment();

                    state._fsp--;

                    adaptor.addChild(root_0, elseifblockcomment32.getTree());
                    retval.node = (elseifblockcomment32!=null?elseifblockcomment32.node:null);

                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:257:5: elseiflinecomment
                    {
                    pushFollow(FOLLOW_elseiflinecomment_in_elseifcomment521);
                    elseiflinecomment33=elseiflinecomment();

                    state._fsp--;

                    adaptor.addChild(root_0, elseiflinecomment33.getTree());
                    retval.node = (elseiflinecomment33!=null?elseiflinecomment33.node:null);

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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:261:1: elseifblockcomment returns [ExpressionNode node] : C_ST ELSEIF expression C_ED ;
    public final TwoWaySqlParser.elseifblockcomment_return elseifblockcomment() throws RecognitionException {
        TwoWaySqlParser.elseifblockcomment_return retval = new TwoWaySqlParser.elseifblockcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST34=null;
        Token ELSEIF35=null;
        Token C_ED37=null;
        TwoWaySqlParser.expression_return expression36 = null;


        CommonTree C_ST34_tree=null;
        CommonTree ELSEIF35_tree=null;
        CommonTree C_ED37_tree=null;


        		push(EM_ELSEIFBLOCKCOMMENT);
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:265:2: ( C_ST ELSEIF expression C_ED )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:265:4: C_ST ELSEIF expression C_ED
            {
            root_0 = (CommonTree)adaptor.nil();

            C_ST34=(Token)match(input,C_ST,FOLLOW_C_ST_in_elseifblockcomment546); 
            C_ST34_tree = (CommonTree)adaptor.create(C_ST34);
            adaptor.addChild(root_0, C_ST34_tree);

            ELSEIF35=(Token)match(input,ELSEIF,FOLLOW_ELSEIF_in_elseifblockcomment548); 
            ELSEIF35_tree = (CommonTree)adaptor.create(ELSEIF35);
            adaptor.addChild(root_0, ELSEIF35_tree);

            pushFollow(FOLLOW_expression_in_elseifblockcomment550);
            expression36=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression36.getTree());
            C_ED37=(Token)match(input,C_ED,FOLLOW_C_ED_in_elseifblockcomment552); 
            C_ED37_tree = (CommonTree)adaptor.create(C_ED37);
            adaptor.addChild(root_0, C_ED37_tree);

             retval.node = (expression36!=null?expression36.node:null); 

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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:269:1: elseiflinecomment returns [ExpressionNode node] : C_LN_ST ELSEIF expression C_LN_ED ;
    public final TwoWaySqlParser.elseiflinecomment_return elseiflinecomment() throws RecognitionException {
        TwoWaySqlParser.elseiflinecomment_return retval = new TwoWaySqlParser.elseiflinecomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_LN_ST38=null;
        Token ELSEIF39=null;
        Token C_LN_ED41=null;
        TwoWaySqlParser.expression_return expression40 = null;


        CommonTree C_LN_ST38_tree=null;
        CommonTree ELSEIF39_tree=null;
        CommonTree C_LN_ED41_tree=null;


        		push(EM_ELSEIFLINECOMMENT);
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:273:2: ( C_LN_ST ELSEIF expression C_LN_ED )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:273:4: C_LN_ST ELSEIF expression C_LN_ED
            {
            root_0 = (CommonTree)adaptor.nil();

            C_LN_ST38=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_elseiflinecomment579); 
            C_LN_ST38_tree = (CommonTree)adaptor.create(C_LN_ST38);
            adaptor.addChild(root_0, C_LN_ST38_tree);

            ELSEIF39=(Token)match(input,ELSEIF,FOLLOW_ELSEIF_in_elseiflinecomment581); 
            ELSEIF39_tree = (CommonTree)adaptor.create(ELSEIF39);
            adaptor.addChild(root_0, ELSEIF39_tree);

            pushFollow(FOLLOW_expression_in_elseiflinecomment583);
            expression40=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression40.getTree());
            C_LN_ED41=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_elseiflinecomment585); 
            C_LN_ED41_tree = (CommonTree)adaptor.create(C_LN_ED41);
            adaptor.addChild(root_0, C_LN_ED41_tree);

             retval.node = (expression40!=null?expression40.node:null); 

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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:277:1: elsenode returns [ElseNode node] : elsecomment ( MAYBE_SKIP )? nodelist ;
    public final TwoWaySqlParser.elsenode_return elsenode() throws RecognitionException {
        TwoWaySqlParser.elsenode_return retval = new TwoWaySqlParser.elsenode_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token MAYBE_SKIP43=null;
        TwoWaySqlParser.elsecomment_return elsecomment42 = null;

        TwoWaySqlParser.nodelist_return nodelist44 = null;


        CommonTree MAYBE_SKIP43_tree=null;


        		push(EM_ELSENODE);
        		retval.node = new ElseNode();
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:286:2: ( elsecomment ( MAYBE_SKIP )? nodelist )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:287:2: elsecomment ( MAYBE_SKIP )? nodelist
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_elsecomment_in_elsenode619);
            elsecomment42=elsecomment();

            state._fsp--;

            adaptor.addChild(root_0, elsecomment42.getTree());
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:288:2: ( MAYBE_SKIP )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==MAYBE_SKIP) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:288:3: MAYBE_SKIP
                    {
                    MAYBE_SKIP43=(Token)match(input,MAYBE_SKIP,FOLLOW_MAYBE_SKIP_in_elsenode623); 
                    MAYBE_SKIP43_tree = (CommonTree)adaptor.create(MAYBE_SKIP43);
                    adaptor.addChild(root_0, MAYBE_SKIP43_tree);


                    		TxtNode skip = new TxtNode();
                    		skip.update(MAYBE_SKIP43);
                    		skip.freeze();
                    		retval.node.setMaybeSkip(skip);
                    	

                    }
                    break;

            }

            pushFollow(FOLLOW_nodelist_in_elsenode633);
            nodelist44=nodelist();

            state._fsp--;

            adaptor.addChild(root_0, nodelist44.getTree());
             retval.node.setChildren((nodelist44!=null?nodelist44.list:null));

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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:300:1: elsecomment : ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED ) ;
    public final TwoWaySqlParser.elsecomment_return elsecomment() throws RecognitionException {
        TwoWaySqlParser.elsecomment_return retval = new TwoWaySqlParser.elsecomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST45=null;
        Token ELSE46=null;
        Token C_ED47=null;
        Token C_LN_ST48=null;
        Token ELSE49=null;
        Token C_LN_ED50=null;

        CommonTree C_ST45_tree=null;
        CommonTree ELSE46_tree=null;
        CommonTree C_ED47_tree=null;
        CommonTree C_LN_ST48_tree=null;
        CommonTree ELSE49_tree=null;
        CommonTree C_LN_ED50_tree=null;


        		push(EM_ELSECOMMENT);
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:304:2: ( ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:305:2: ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED )
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:305:2: ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED )
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
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:305:3: C_ST ELSE C_ED
                    {
                    C_ST45=(Token)match(input,C_ST,FOLLOW_C_ST_in_elsecomment660); 
                    C_ST45_tree = (CommonTree)adaptor.create(C_ST45);
                    adaptor.addChild(root_0, C_ST45_tree);

                    ELSE46=(Token)match(input,ELSE,FOLLOW_ELSE_in_elsecomment662); 
                    ELSE46_tree = (CommonTree)adaptor.create(ELSE46);
                    adaptor.addChild(root_0, ELSE46_tree);

                    C_ED47=(Token)match(input,C_ED,FOLLOW_C_ED_in_elsecomment664); 
                    C_ED47_tree = (CommonTree)adaptor.create(C_ED47);
                    adaptor.addChild(root_0, C_ED47_tree);


                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:305:20: C_LN_ST ELSE C_LN_ED
                    {
                    C_LN_ST48=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_elsecomment668); 
                    C_LN_ST48_tree = (CommonTree)adaptor.create(C_LN_ST48);
                    adaptor.addChild(root_0, C_LN_ST48_tree);

                    ELSE49=(Token)match(input,ELSE,FOLLOW_ELSE_in_elsecomment670); 
                    ELSE49_tree = (CommonTree)adaptor.create(ELSE49);
                    adaptor.addChild(root_0, ELSE49_tree);

                    C_LN_ED50=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_elsecomment672); 
                    C_LN_ED50_tree = (CommonTree)adaptor.create(C_LN_ED50);
                    adaptor.addChild(root_0, C_LN_ED50_tree);


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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:309:1: expression returns [ExpressionNode node] : charactors ;
    public final TwoWaySqlParser.expression_return expression() throws RecognitionException {
        TwoWaySqlParser.expression_return retval = new TwoWaySqlParser.expression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.charactors_return charactors51 = null;




        		push(EM_EXPRESSION);
        		retval.node = new ExpressionNode();
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:318:2: ( charactors )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:319:2: charactors
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_charactors_in_expression705);
            charactors51=charactors();

            state._fsp--;

            adaptor.addChild(root_0, charactors51.getTree());

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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:323:1: begincomment returns [BeginNode node] : ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) nodelist endcomment ) ;
    public final TwoWaySqlParser.begincomment_return begincomment() throws RecognitionException {
        TwoWaySqlParser.begincomment_return retval = new TwoWaySqlParser.begincomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST52=null;
        Token BEGIN53=null;
        Token C_ED54=null;
        Token C_LN_ST55=null;
        Token BEGIN56=null;
        Token C_LN_ED57=null;
        TwoWaySqlParser.nodelist_return nodelist58 = null;

        TwoWaySqlParser.endcomment_return endcomment59 = null;


        CommonTree C_ST52_tree=null;
        CommonTree BEGIN53_tree=null;
        CommonTree C_ED54_tree=null;
        CommonTree C_LN_ST55_tree=null;
        CommonTree BEGIN56_tree=null;
        CommonTree C_LN_ED57_tree=null;


        		push(EM_BEGINCOMMENT);
        		retval.node = new BeginNode();
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:332:2: ( ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) nodelist endcomment ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:333:2: ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) nodelist endcomment )
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:333:2: ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) nodelist endcomment )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:334:3: ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) nodelist endcomment
            {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:334:3: ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED )
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
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:334:4: C_ST BEGIN C_ED
                    {
                    C_ST52=(Token)match(input,C_ST,FOLLOW_C_ST_in_begincomment743); 
                    C_ST52_tree = (CommonTree)adaptor.create(C_ST52);
                    adaptor.addChild(root_0, C_ST52_tree);

                    BEGIN53=(Token)match(input,BEGIN,FOLLOW_BEGIN_in_begincomment745); 
                    BEGIN53_tree = (CommonTree)adaptor.create(BEGIN53);
                    adaptor.addChild(root_0, BEGIN53_tree);

                    C_ED54=(Token)match(input,C_ED,FOLLOW_C_ED_in_begincomment747); 
                    C_ED54_tree = (CommonTree)adaptor.create(C_ED54);
                    adaptor.addChild(root_0, C_ED54_tree);


                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:334:22: C_LN_ST BEGIN C_LN_ED
                    {
                    C_LN_ST55=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_begincomment751); 
                    C_LN_ST55_tree = (CommonTree)adaptor.create(C_LN_ST55);
                    adaptor.addChild(root_0, C_LN_ST55_tree);

                    BEGIN56=(Token)match(input,BEGIN,FOLLOW_BEGIN_in_begincomment753); 
                    BEGIN56_tree = (CommonTree)adaptor.create(BEGIN56);
                    adaptor.addChild(root_0, BEGIN56_tree);

                    C_LN_ED57=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_begincomment755); 
                    C_LN_ED57_tree = (CommonTree)adaptor.create(C_LN_ED57);
                    adaptor.addChild(root_0, C_LN_ED57_tree);


                    }
                    break;

            }

            pushFollow(FOLLOW_nodelist_in_begincomment758);
            nodelist58=nodelist();

            state._fsp--;

            adaptor.addChild(root_0, nodelist58.getTree());
            pushFollow(FOLLOW_endcomment_in_begincomment760);
            endcomment59=endcomment();

            state._fsp--;

            adaptor.addChild(root_0, endcomment59.getTree());

            }


            		retval.node.setChildren((nodelist58!=null?nodelist58.list:null));
            	

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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:342:1: endcomment : ( C_ST END C_ED | C_LN_ST END C_LN_ED );
    public final TwoWaySqlParser.endcomment_return endcomment() throws RecognitionException {
        TwoWaySqlParser.endcomment_return retval = new TwoWaySqlParser.endcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST60=null;
        Token END61=null;
        Token C_ED62=null;
        Token C_LN_ST63=null;
        Token END64=null;
        Token C_LN_ED65=null;

        CommonTree C_ST60_tree=null;
        CommonTree END61_tree=null;
        CommonTree C_ED62_tree=null;
        CommonTree C_LN_ST63_tree=null;
        CommonTree END64_tree=null;
        CommonTree C_LN_ED65_tree=null;


        		push(EM_ENDCOMMENT);
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:346:2: ( C_ST END C_ED | C_LN_ST END C_LN_ED )
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
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:347:2: C_ST END C_ED
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    C_ST60=(Token)match(input,C_ST,FOLLOW_C_ST_in_endcomment789); 
                    C_ST60_tree = (CommonTree)adaptor.create(C_ST60);
                    adaptor.addChild(root_0, C_ST60_tree);

                    END61=(Token)match(input,END,FOLLOW_END_in_endcomment791); 
                    END61_tree = (CommonTree)adaptor.create(END61);
                    adaptor.addChild(root_0, END61_tree);

                    C_ED62=(Token)match(input,C_ED,FOLLOW_C_ED_in_endcomment793); 
                    C_ED62_tree = (CommonTree)adaptor.create(C_ED62);
                    adaptor.addChild(root_0, C_ED62_tree);


                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:347:18: C_LN_ST END C_LN_ED
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    C_LN_ST63=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_endcomment797); 
                    C_LN_ST63_tree = (CommonTree)adaptor.create(C_LN_ST63);
                    adaptor.addChild(root_0, C_LN_ST63_tree);

                    END64=(Token)match(input,END,FOLLOW_END_in_endcomment799); 
                    END64_tree = (CommonTree)adaptor.create(END64);
                    adaptor.addChild(root_0, END64_tree);

                    C_LN_ED65=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_endcomment801); 
                    C_LN_ED65_tree = (CommonTree)adaptor.create(C_LN_ED65);
                    adaptor.addChild(root_0, C_LN_ED65_tree);


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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:351:1: bindcomment returns [BindNode node] : ( C_ST ( bindingname )? SYM_BIND expression C_ED bindchars ) ;
    public final TwoWaySqlParser.bindcomment_return bindcomment() throws RecognitionException {
        TwoWaySqlParser.bindcomment_return retval = new TwoWaySqlParser.bindcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST66=null;
        Token SYM_BIND68=null;
        Token C_ED70=null;
        TwoWaySqlParser.bindingname_return bindingname67 = null;

        TwoWaySqlParser.expression_return expression69 = null;

        TwoWaySqlParser.bindchars_return bindchars71 = null;


        CommonTree C_ST66_tree=null;
        CommonTree SYM_BIND68_tree=null;
        CommonTree C_ED70_tree=null;


        		push(EM_BINDCOMMENT);
        		retval.node = new BindNode();
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:360:2: ( ( C_ST ( bindingname )? SYM_BIND expression C_ED bindchars ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:361:2: ( C_ST ( bindingname )? SYM_BIND expression C_ED bindchars )
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:361:2: ( C_ST ( bindingname )? SYM_BIND expression C_ED bindchars )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:361:3: C_ST ( bindingname )? SYM_BIND expression C_ED bindchars
            {
            C_ST66=(Token)match(input,C_ST,FOLLOW_C_ST_in_bindcomment834); 
            C_ST66_tree = (CommonTree)adaptor.create(C_ST66);
            adaptor.addChild(root_0, C_ST66_tree);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:362:3: ( bindingname )?
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
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:362:4: bindingname
                    {
                    pushFollow(FOLLOW_bindingname_in_bindcomment840);
                    bindingname67=bindingname();

                    state._fsp--;

                    adaptor.addChild(root_0, bindingname67.getTree());
                    retval.node.setBindingName((bindingname67!=null?bindingname67.node:null)); 

                    }
                    break;

            }

            SYM_BIND68=(Token)match(input,SYM_BIND,FOLLOW_SYM_BIND_in_bindcomment848); 
            SYM_BIND68_tree = (CommonTree)adaptor.create(SYM_BIND68);
            adaptor.addChild(root_0, SYM_BIND68_tree);

            pushFollow(FOLLOW_expression_in_bindcomment850);
            expression69=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression69.getTree());
            C_ED70=(Token)match(input,C_ED,FOLLOW_C_ED_in_bindcomment852); 
            C_ED70_tree = (CommonTree)adaptor.create(C_ED70);
            adaptor.addChild(root_0, C_ED70_tree);

            pushFollow(FOLLOW_bindchars_in_bindcomment854);
            bindchars71=bindchars();

            state._fsp--;

            adaptor.addChild(root_0, bindchars71.getTree());

            }


            		retval.node.setExpression((expression69!=null?expression69.node:null));
            		retval.node.setSkipped((bindchars71!=null?bindchars71.node:null));
            	

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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:371:1: bindingname returns [TxtNode node] : ( SYM_BIND IDENT ) ;
    public final TwoWaySqlParser.bindingname_return bindingname() throws RecognitionException {
        TwoWaySqlParser.bindingname_return retval = new TwoWaySqlParser.bindingname_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token SYM_BIND72=null;
        Token IDENT73=null;

        CommonTree SYM_BIND72_tree=null;
        CommonTree IDENT73_tree=null;


        		push(EM_BINDINGNAME);
        		retval.node = new TxtNode();
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:379:2: ( ( SYM_BIND IDENT ) )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:380:2: ( SYM_BIND IDENT )
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:380:2: ( SYM_BIND IDENT )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:380:3: SYM_BIND IDENT
            {
            SYM_BIND72=(Token)match(input,SYM_BIND,FOLLOW_SYM_BIND_in_bindingname891); 
            SYM_BIND72_tree = (CommonTree)adaptor.create(SYM_BIND72);
            adaptor.addChild(root_0, SYM_BIND72_tree);

            IDENT73=(Token)match(input,IDENT,FOLLOW_IDENT_in_bindingname893); 
            IDENT73_tree = (CommonTree)adaptor.create(IDENT73);
            adaptor.addChild(root_0, IDENT73_tree);

             retval.node.update(IDENT73); 

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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:385:1: inbind returns [InBindNode node] : IN C_ST ( bindingname )? SYM_BIND expression C_ED inbindskipped ;
    public final TwoWaySqlParser.inbind_return inbind() throws RecognitionException {
        TwoWaySqlParser.inbind_return retval = new TwoWaySqlParser.inbind_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IN74=null;
        Token C_ST75=null;
        Token SYM_BIND77=null;
        Token C_ED79=null;
        TwoWaySqlParser.bindingname_return bindingname76 = null;

        TwoWaySqlParser.expression_return expression78 = null;

        TwoWaySqlParser.inbindskipped_return inbindskipped80 = null;


        CommonTree IN74_tree=null;
        CommonTree C_ST75_tree=null;
        CommonTree SYM_BIND77_tree=null;
        CommonTree C_ED79_tree=null;


        		push(EM_INBIND);
        		retval.node = new InBindNode();
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:394:2: ( IN C_ST ( bindingname )? SYM_BIND expression C_ED inbindskipped )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:395:2: IN C_ST ( bindingname )? SYM_BIND expression C_ED inbindskipped
            {
            root_0 = (CommonTree)adaptor.nil();

            IN74=(Token)match(input,IN,FOLLOW_IN_in_inbind929); 
            IN74_tree = (CommonTree)adaptor.create(IN74);
            adaptor.addChild(root_0, IN74_tree);

            C_ST75=(Token)match(input,C_ST,FOLLOW_C_ST_in_inbind931); 
            C_ST75_tree = (CommonTree)adaptor.create(C_ST75);
            adaptor.addChild(root_0, C_ST75_tree);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:396:3: ( bindingname )?
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
                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:396:4: bindingname
                    {
                    pushFollow(FOLLOW_bindingname_in_inbind937);
                    bindingname76=bindingname();

                    state._fsp--;

                    adaptor.addChild(root_0, bindingname76.getTree());
                    retval.node.setBindingName((bindingname76!=null?bindingname76.node:null)); 

                    }
                    break;

            }

            SYM_BIND77=(Token)match(input,SYM_BIND,FOLLOW_SYM_BIND_in_inbind946); 
            SYM_BIND77_tree = (CommonTree)adaptor.create(SYM_BIND77);
            adaptor.addChild(root_0, SYM_BIND77_tree);

            pushFollow(FOLLOW_expression_in_inbind948);
            expression78=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression78.getTree());
            C_ED79=(Token)match(input,C_ED,FOLLOW_C_ED_in_inbind950); 
            C_ED79_tree = (CommonTree)adaptor.create(C_ED79);
            adaptor.addChild(root_0, C_ED79_tree);

            pushFollow(FOLLOW_inbindskipped_in_inbind952);
            inbindskipped80=inbindskipped();

            state._fsp--;

            adaptor.addChild(root_0, inbindskipped80.getTree());

            		retval.node.setExpression((expression78!=null?expression78.node:null));
            		retval.node.setSkipped((inbindskipped80!=null?inbindskipped80.node:null));
            	

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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:405:1: inbindskipped returns [TxtNode node] : SYM_LP ( txtcomment )* bindchars ( txtcomment )* ( SYM_C ( txtcomment )* bindchars ( txtcomment )* )* SYM_RP ;
    public final TwoWaySqlParser.inbindskipped_return inbindskipped() throws RecognitionException {
        TwoWaySqlParser.inbindskipped_return retval = new TwoWaySqlParser.inbindskipped_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token SYM_LP81=null;
        Token SYM_C85=null;
        Token SYM_RP89=null;
        TwoWaySqlParser.txtcomment_return txtcomment82 = null;

        TwoWaySqlParser.bindchars_return bindchars83 = null;

        TwoWaySqlParser.txtcomment_return txtcomment84 = null;

        TwoWaySqlParser.txtcomment_return txtcomment86 = null;

        TwoWaySqlParser.bindchars_return bindchars87 = null;

        TwoWaySqlParser.txtcomment_return txtcomment88 = null;


        CommonTree SYM_LP81_tree=null;
        CommonTree SYM_C85_tree=null;
        CommonTree SYM_RP89_tree=null;


        		push(EM_INBINDSKIPPED);
        		retval.node = new TxtNode();
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:414:2: ( SYM_LP ( txtcomment )* bindchars ( txtcomment )* ( SYM_C ( txtcomment )* bindchars ( txtcomment )* )* SYM_RP )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:415:2: SYM_LP ( txtcomment )* bindchars ( txtcomment )* ( SYM_C ( txtcomment )* bindchars ( txtcomment )* )* SYM_RP
            {
            root_0 = (CommonTree)adaptor.nil();

            SYM_LP81=(Token)match(input,SYM_LP,FOLLOW_SYM_LP_in_inbindskipped987); 
            SYM_LP81_tree = (CommonTree)adaptor.create(SYM_LP81);
            adaptor.addChild(root_0, SYM_LP81_tree);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:416:3: ( txtcomment )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==C_ST||LA16_0==C_LN_ST) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:416:3: txtcomment
            	    {
            	    pushFollow(FOLLOW_txtcomment_in_inbindskipped992);
            	    txtcomment82=txtcomment();

            	    state._fsp--;

            	    adaptor.addChild(root_0, txtcomment82.getTree());

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            pushFollow(FOLLOW_bindchars_in_inbindskipped995);
            bindchars83=bindchars();

            state._fsp--;

            adaptor.addChild(root_0, bindchars83.getTree());
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:416:25: ( txtcomment )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==C_ST||LA17_0==C_LN_ST) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:416:25: txtcomment
            	    {
            	    pushFollow(FOLLOW_txtcomment_in_inbindskipped997);
            	    txtcomment84=txtcomment();

            	    state._fsp--;

            	    adaptor.addChild(root_0, txtcomment84.getTree());

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:417:3: ( SYM_C ( txtcomment )* bindchars ( txtcomment )* )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==SYM_C) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:417:4: SYM_C ( txtcomment )* bindchars ( txtcomment )*
            	    {
            	    SYM_C85=(Token)match(input,SYM_C,FOLLOW_SYM_C_in_inbindskipped1003); 
            	    SYM_C85_tree = (CommonTree)adaptor.create(SYM_C85);
            	    adaptor.addChild(root_0, SYM_C85_tree);

            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:418:4: ( txtcomment )*
            	    loop18:
            	    do {
            	        int alt18=2;
            	        int LA18_0 = input.LA(1);

            	        if ( (LA18_0==C_ST||LA18_0==C_LN_ST) ) {
            	            alt18=1;
            	        }


            	        switch (alt18) {
            	    	case 1 :
            	    	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:418:4: txtcomment
            	    	    {
            	    	    pushFollow(FOLLOW_txtcomment_in_inbindskipped1009);
            	    	    txtcomment86=txtcomment();

            	    	    state._fsp--;

            	    	    adaptor.addChild(root_0, txtcomment86.getTree());

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop18;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_bindchars_in_inbindskipped1012);
            	    bindchars87=bindchars();

            	    state._fsp--;

            	    adaptor.addChild(root_0, bindchars87.getTree());
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:418:26: ( txtcomment )*
            	    loop19:
            	    do {
            	        int alt19=2;
            	        int LA19_0 = input.LA(1);

            	        if ( (LA19_0==C_ST||LA19_0==C_LN_ST) ) {
            	            alt19=1;
            	        }


            	        switch (alt19) {
            	    	case 1 :
            	    	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:418:26: txtcomment
            	    	    {
            	    	    pushFollow(FOLLOW_txtcomment_in_inbindskipped1014);
            	    	    txtcomment88=txtcomment();

            	    	    state._fsp--;

            	    	    adaptor.addChild(root_0, txtcomment88.getTree());

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

            SYM_RP89=(Token)match(input,SYM_RP,FOLLOW_SYM_RP_in_inbindskipped1023); 
            SYM_RP89_tree = (CommonTree)adaptor.create(SYM_RP89);
            adaptor.addChild(root_0, SYM_RP89_tree);


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
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:424:1: bindchars returns [TxtNode node] : ( IDENT | QUOTED | SYMBOLS | SYM_BIND )+ ;
    public final TwoWaySqlParser.bindchars_return bindchars() throws RecognitionException {
        TwoWaySqlParser.bindchars_return retval = new TwoWaySqlParser.bindchars_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set90=null;

        CommonTree set90_tree=null;


        		retval.node = new TxtNode();
        	
        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:433:2: ( ( IDENT | QUOTED | SYMBOLS | SYM_BIND )+ )
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:434:2: ( IDENT | QUOTED | SYMBOLS | SYM_BIND )+
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:434:2: ( IDENT | QUOTED | SYMBOLS | SYM_BIND )+
            int cnt21=0;
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0>=IDENT && LA21_0<=QUOTED)||(LA21_0>=SYMBOLS && LA21_0<=SYM_BIND)) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten.twowaysql\\core\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:
            	    {
            	    set90=(Token)input.LT(1);
            	    if ( (input.LA(1)>=IDENT && input.LA(1)<=QUOTED)||(input.LA(1)>=SYMBOLS && input.LA(1)<=SYM_BIND) ) {
            	        input.consume();
            	        adaptor.addChild(root_0, (CommonTree)adaptor.create(set90));
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt21 >= 1 ) break loop21;
                        EarlyExitException eee =
                            new EarlyExitException(21, input);
                        throw eee;
                }
                cnt21++;
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
            return "162:1: comment returns [QueryNode node] : ( begincomment | ifcomment | bindcomment | txtcomment );";
        }
    }
 

    public static final BitSet FOLLOW_nodelist_in_twowaySQL98 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_twowaySQL100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comment_in_nodelist130 = new BitSet(new long[]{0x0000000000205FF2L});
    public static final BitSet FOLLOW_inbind_in_nodelist137 = new BitSet(new long[]{0x0000000000205FF2L});
    public static final BitSet FOLLOW_txt_in_nodelist144 = new BitSet(new long[]{0x0000000000205FF2L});
    public static final BitSet FOLLOW_set_in_charactors164 = new BitSet(new long[]{0x0000000000000FF2L});
    public static final BitSet FOLLOW_charactors_in_txt223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_begincomment_in_comment243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifcomment_in_comment250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bindcomment_in_comment257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_txtcomment_in_comment264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_blockcomment_in_txtcomment280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_linecomment_in_txtcomment287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_blockcomment317 = new BitSet(new long[]{0x0000000000207FF0L});
    public static final BitSet FOLLOW_charactors_in_blockcomment319 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_C_ED_in_blockcomment321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_linecomment353 = new BitSet(new long[]{0x000000000020DFF0L});
    public static final BitSet FOLLOW_charactors_in_linecomment355 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_C_LN_ED_in_linecomment357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_ifcomment390 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_IF_in_ifcomment392 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_expression_in_ifcomment394 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_C_ED_in_ifcomment396 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_MAYBE_SKIP_in_ifcomment403 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_nodelist_in_ifcomment412 = new BitSet(new long[]{0x0000000000005000L});
    public static final BitSet FOLLOW_elseifnode_in_ifcomment419 = new BitSet(new long[]{0x0000000000005000L});
    public static final BitSet FOLLOW_elsenode_in_ifcomment429 = new BitSet(new long[]{0x0000000000005000L});
    public static final BitSet FOLLOW_endcomment_in_ifcomment437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elseifcomment_in_elseifnode473 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_MAYBE_SKIP_in_elseifnode477 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_nodelist_in_elseifnode486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elseifblockcomment_in_elseifcomment513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elseiflinecomment_in_elseifcomment521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_elseifblockcomment546 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_ELSEIF_in_elseifblockcomment548 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_expression_in_elseifblockcomment550 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_C_ED_in_elseifblockcomment552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_elseiflinecomment579 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_ELSEIF_in_elseiflinecomment581 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_expression_in_elseiflinecomment583 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_C_LN_ED_in_elseiflinecomment585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elsecomment_in_elsenode619 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_MAYBE_SKIP_in_elsenode623 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_nodelist_in_elsenode633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_elsecomment660 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_ELSE_in_elsecomment662 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_C_ED_in_elsecomment664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_elsecomment668 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_ELSE_in_elsecomment670 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_C_LN_ED_in_elsecomment672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_charactors_in_expression705 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_begincomment743 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_BEGIN_in_begincomment745 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_C_ED_in_begincomment747 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_C_LN_ST_in_begincomment751 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_BEGIN_in_begincomment753 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_C_LN_ED_in_begincomment755 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_nodelist_in_begincomment758 = new BitSet(new long[]{0x0000000000005000L});
    public static final BitSet FOLLOW_endcomment_in_begincomment760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_endcomment789 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_END_in_endcomment791 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_C_ED_in_endcomment793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_endcomment797 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_END_in_endcomment799 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_C_LN_ED_in_endcomment801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_bindcomment834 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_bindingname_in_bindcomment840 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_SYM_BIND_in_bindcomment848 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_expression_in_bindcomment850 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_C_ED_in_bindcomment852 = new BitSet(new long[]{0x00000000000001B0L});
    public static final BitSet FOLLOW_bindchars_in_bindcomment854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SYM_BIND_in_bindingname891 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_bindingname893 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IN_in_inbind929 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_C_ST_in_inbind931 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_bindingname_in_inbind937 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_SYM_BIND_in_inbind946 = new BitSet(new long[]{0x0000000000205FF0L});
    public static final BitSet FOLLOW_expression_in_inbind948 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_C_ED_in_inbind950 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_inbindskipped_in_inbind952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SYM_LP_in_inbindskipped987 = new BitSet(new long[]{0x00000000000051B0L});
    public static final BitSet FOLLOW_txtcomment_in_inbindskipped992 = new BitSet(new long[]{0x00000000000051B0L});
    public static final BitSet FOLLOW_bindchars_in_inbindskipped995 = new BitSet(new long[]{0x0000000000005A00L});
    public static final BitSet FOLLOW_txtcomment_in_inbindskipped997 = new BitSet(new long[]{0x0000000000005A00L});
    public static final BitSet FOLLOW_SYM_C_in_inbindskipped1003 = new BitSet(new long[]{0x00000000000051B0L});
    public static final BitSet FOLLOW_txtcomment_in_inbindskipped1009 = new BitSet(new long[]{0x00000000000051B0L});
    public static final BitSet FOLLOW_bindchars_in_inbindskipped1012 = new BitSet(new long[]{0x0000000000005A00L});
    public static final BitSet FOLLOW_txtcomment_in_inbindskipped1014 = new BitSet(new long[]{0x0000000000005A00L});
    public static final BitSet FOLLOW_SYM_RP_in_inbindskipped1023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_bindchars1058 = new BitSet(new long[]{0x00000000000001B2L});

}