// $ANTLR 3.1.1 D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g 2008-12-04 21:15:36

package werkzeugkasten.twowaysql.grammar;

import werkzeugkasten.twowaysql.tree.*;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.debug.*;
import java.io.IOException;

import org.antlr.runtime.tree.*;

public class TwoWaySqlParser extends DebugParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BEGINNODE", "IFNODE", "EXPRESSIONNODE", "ELSENODE", "ELSEIFNODE", "BINDNODE", "INBINDNODE", "TXTNODE", "ROOTNODE", "IDENT", "SYMBOLS", "QUOTED", "SYM_BIND", "SYM_C", "SYM_LP", "SYM_RP", "C_ST", "C_ED", "C_LN_ST", "C_LN_ED", "IF", "ELSEIF", "ELSE", "BEGIN", "END", "IN", "SYM_Q", "LN_R", "LN_N", "CHAR", "WS", "LT", "WHITE_SPACES"
    };
    public static final int BINDNODE=9;
    public static final int TXTNODE=11;
    public static final int LT=35;
    public static final int C_ST=20;
    public static final int QUOTED=15;
    public static final int SYM_RP=19;
    public static final int INBINDNODE=10;
    public static final int ELSE=26;
    public static final int SYM_LP=18;
    public static final int CHAR=33;
    public static final int C_LN_ST=22;
    public static final int EOF=-1;
    public static final int C_ED=21;
    public static final int IF=24;
    public static final int ELSEIFNODE=8;
    public static final int WHITE_SPACES=36;
    public static final int ELSEIF=25;
    public static final int WS=34;
    public static final int SYM_Q=30;
    public static final int IN=29;
    public static final int BEGIN=27;
    public static final int IFNODE=5;
    public static final int IDENT=13;
    public static final int SYMBOLS=14;
    public static final int SYM_C=17;
    public static final int END=28;
    public static final int EXPRESSIONNODE=6;
    public static final int SYM_BIND=16;
    public static final int ELSENODE=7;
    public static final int C_LN_ED=23;
    public static final int BEGINNODE=4;
    public static final int LN_N=32;
    public static final int LN_R=31;
    public static final int ROOTNODE=12;

    // delegates
    // delegators

    public static final String[] ruleNames = new String[] {
        "invalidRule", "elsenode", "txts", "inbind", "elseifnode", "comment", 
        "txt", "expression", "inbindchars", "elseifcomment", "begincomment", 
        "charactors", "ifcomment", "elsecomment", "endcomment", "twowaySQL", 
        "linecomment", "blockcomment", "bindcomment"
    };
     
        public int ruleLevel = 0;
        public int getRuleLevel() { return ruleLevel; }
        public void incRuleLevel() { ruleLevel++; }
        public void decRuleLevel() { ruleLevel--; }
        public TwoWaySqlParser(TokenStream input) {
            this(input, DebugEventSocketProxy.DEFAULT_DEBUGGER_PORT, new RecognizerSharedState());
        }
        public TwoWaySqlParser(TokenStream input, int port, RecognizerSharedState state) {
            super(input, state);
            DebugEventSocketProxy proxy =
                new DebugEventSocketProxy(this,port,adaptor);
            setDebugListener(proxy);
            setTokenStream(new DebugTokenStream(input,proxy));
            try {
                proxy.handshake();
            }
            catch (IOException ioe) {
                reportError(ioe);
            }
            TreeAdaptor adap = new CommonTreeAdaptor();
            setTreeAdaptor(adap);
            proxy.setTreeAdaptor(adap);
        }
    public TwoWaySqlParser(TokenStream input, DebugEventListener dbg) {
        super(input, dbg);

         
        TreeAdaptor adap = new CommonTreeAdaptor();
        setTreeAdaptor(adap);

    }
    protected boolean evalPredicate(boolean result, String predicate) {
        dbg.semanticPredicate(result, predicate);
        return result;
    }

    protected DebugTreeAdaptor adaptor;
    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = new DebugTreeAdaptor(dbg,adaptor);

    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }


    public String[] getTokenNames() { return TwoWaySqlParser.tokenNames; }
    public String getGrammarFileName() { return "D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g"; }


    	NodeFactory f = NodeFactory.getInstance();


    public static class twowaySQL_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "twowaySQL"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:41:1: twowaySQL : txt EOF -> ^( ROOTNODE txt ) ;
    public final TwoWaySqlParser.twowaySQL_return twowaySQL() throws RecognitionException {
        TwoWaySqlParser.twowaySQL_return retval = new TwoWaySqlParser.twowaySQL_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token EOF2=null;
        TwoWaySqlParser.txt_return txt1 = null;


        CommonTree EOF2_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_txt=new RewriteRuleSubtreeStream(adaptor,"rule txt");
        try { dbg.enterRule(getGrammarFileName(), "twowaySQL");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(41, 1);

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:41:11: ( txt EOF -> ^( ROOTNODE txt ) )
            dbg.enterAlt(1);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:41:13: txt EOF
            {
            dbg.location(41,13);
            pushFollow(FOLLOW_txt_in_twowaySQL104);
            txt1=txt();

            state._fsp--;

            stream_txt.add(txt1.getTree());
            dbg.location(41,17);
            EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_twowaySQL106);  
            stream_EOF.add(EOF2);



            // AST REWRITE
            // elements: txt
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 42:2: -> ^( ROOTNODE txt )
            {
                dbg.location(42,5);
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:42:5: ^( ROOTNODE txt )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                dbg.location(42,7);
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ROOTNODE, "ROOTNODE"), root_1);

                dbg.location(42,16);
                adaptor.addChild(root_1, stream_txt.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(43, 2);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "twowaySQL");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "twowaySQL"

    public static class txt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "txt"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:45:1: txt : ( comment | inbind | txts )+ ;
    public final TwoWaySqlParser.txt_return txt() throws RecognitionException {
        TwoWaySqlParser.txt_return retval = new TwoWaySqlParser.txt_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.comment_return comment3 = null;

        TwoWaySqlParser.inbind_return inbind4 = null;

        TwoWaySqlParser.txts_return txts5 = null;



        try { dbg.enterRule(getGrammarFileName(), "txt");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(45, 1);

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:45:5: ( ( comment | inbind | txts )+ )
            dbg.enterAlt(1);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:45:7: ( comment | inbind | txts )+
            {
            root_0 = (CommonTree)adaptor.nil();

            dbg.location(45,7);
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:45:7: ( comment | inbind | txts )+
            int cnt1=0;
            try { dbg.enterSubRule(1);

            loop1:
            do {
                int alt1=4;
                try { dbg.enterDecision(1);

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
                case SYMBOLS:
                case QUOTED:
                case SYM_BIND:
                case SYM_C:
                case SYM_LP:
                case SYM_RP:
                    {
                    alt1=3;
                    }
                    break;

                }

                } finally {dbg.exitDecision(1);}

                switch (alt1) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:45:8: comment
            	    {
            	    dbg.location(45,8);
            	    pushFollow(FOLLOW_comment_in_txt126);
            	    comment3=comment();

            	    state._fsp--;

            	    adaptor.addChild(root_0, comment3.getTree());

            	    }
            	    break;
            	case 2 :
            	    dbg.enterAlt(2);

            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:45:18: inbind
            	    {
            	    dbg.location(45,18);
            	    pushFollow(FOLLOW_inbind_in_txt130);
            	    inbind4=inbind();

            	    state._fsp--;

            	    adaptor.addChild(root_0, inbind4.getTree());

            	    }
            	    break;
            	case 3 :
            	    dbg.enterAlt(3);

            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:45:27: txts
            	    {
            	    dbg.location(45,27);
            	    pushFollow(FOLLOW_txts_in_txt134);
            	    txts5=txts();

            	    state._fsp--;

            	    adaptor.addChild(root_0, txts5.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        dbg.recognitionException(eee);

                        throw eee;
                }
                cnt1++;
            } while (true);
            } finally {dbg.exitSubRule(1);}


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(46, 2);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "txt");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "txt"

    public static class charactors_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "charactors"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:48:1: charactors : ( IDENT | SYMBOLS | QUOTED | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+ ;
    public final TwoWaySqlParser.charactors_return charactors() throws RecognitionException {
        TwoWaySqlParser.charactors_return retval = new TwoWaySqlParser.charactors_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set6=null;

        CommonTree set6_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "charactors");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(48, 1);

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:48:12: ( ( IDENT | SYMBOLS | QUOTED | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+ )
            dbg.enterAlt(1);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:49:2: ( IDENT | SYMBOLS | QUOTED | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+
            {
            root_0 = (CommonTree)adaptor.nil();

            dbg.location(49,2);
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:49:2: ( IDENT | SYMBOLS | QUOTED | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+
            int cnt2=0;
            try { dbg.enterSubRule(2);

            loop2:
            do {
                int alt2=2;
                try { dbg.enterDecision(2);

                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=IDENT && LA2_0<=SYM_RP)) ) {
                    alt2=1;
                }


                } finally {dbg.exitDecision(2);}

                switch (alt2) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:
            	    {
            	    dbg.location(49,2);
            	    set6=(Token)input.LT(1);
            	    if ( (input.LA(1)>=IDENT && input.LA(1)<=SYM_RP) ) {
            	        input.consume();
            	        adaptor.addChild(root_0, (CommonTree)adaptor.create(set6));
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        dbg.recognitionException(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        dbg.recognitionException(eee);

                        throw eee;
                }
                cnt2++;
            } while (true);
            } finally {dbg.exitSubRule(2);}


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(50, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "charactors");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "charactors"

    public static class txts_return extends ParserRuleReturnScope {
        public TextNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "txts"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:51:1: txts returns [TextNode node] : charactors -> ^( TXTNODE charactors ) ;
    public final TwoWaySqlParser.txts_return txts() throws RecognitionException {
        TwoWaySqlParser.txts_return retval = new TwoWaySqlParser.txts_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.charactors_return charactors7 = null;


        RewriteRuleSubtreeStream stream_charactors=new RewriteRuleSubtreeStream(adaptor,"rule charactors");

        		retval.node = f.textNode();
        	
        try { dbg.enterRule(getGrammarFileName(), "txts");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(51, 1);

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:58:2: ( charactors -> ^( TXTNODE charactors ) )
            dbg.enterAlt(1);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:59:2: charactors
            {
            dbg.location(59,2);
            pushFollow(FOLLOW_charactors_in_txts199);
            charactors7=charactors();

            state._fsp--;

            stream_charactors.add(charactors7.getTree());
            dbg.location(59,13);
             retval.node.append((charactors7!=null?((CommonTree)charactors7.tree):null)); 


            // AST REWRITE
            // elements: charactors
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 60:2: -> ^( TXTNODE charactors )
            {
                dbg.location(60,5);
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:60:5: ^( TXTNODE charactors )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                dbg.location(60,7);
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TXTNODE, "TXTNODE"), root_1);

                dbg.location(60,15);
                adaptor.addChild(root_1, stream_charactors.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.node.freeze();
            	
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(61, 2);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "txts");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "txts"

    public static class comment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "comment"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:65:1: comment : ( begincomment | ifcomment | bindcomment | blockcomment | linecomment );
    public final TwoWaySqlParser.comment_return comment() throws RecognitionException {
        TwoWaySqlParser.comment_return retval = new TwoWaySqlParser.comment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.begincomment_return begincomment8 = null;

        TwoWaySqlParser.ifcomment_return ifcomment9 = null;

        TwoWaySqlParser.bindcomment_return bindcomment10 = null;

        TwoWaySqlParser.blockcomment_return blockcomment11 = null;

        TwoWaySqlParser.linecomment_return linecomment12 = null;



        try { dbg.enterRule(getGrammarFileName(), "comment");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(65, 1);

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:65:9: ( begincomment | ifcomment | bindcomment | blockcomment | linecomment )
            int alt3=5;
            try { dbg.enterDecision(3);

            try {
                isCyclicDecision = true;
                alt3 = dfa3.predict(input);
            }
            catch (NoViableAltException nvae) {
                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(3);}

            switch (alt3) {
                case 1 :
                    dbg.enterAlt(1);

                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:66:2: begincomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    dbg.location(66,2);
                    pushFollow(FOLLOW_begincomment_in_comment224);
                    begincomment8=begincomment();

                    state._fsp--;

                    adaptor.addChild(root_0, begincomment8.getTree());

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:67:4: ifcomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    dbg.location(67,4);
                    pushFollow(FOLLOW_ifcomment_in_comment229);
                    ifcomment9=ifcomment();

                    state._fsp--;

                    adaptor.addChild(root_0, ifcomment9.getTree());

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:68:4: bindcomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    dbg.location(68,4);
                    pushFollow(FOLLOW_bindcomment_in_comment234);
                    bindcomment10=bindcomment();

                    state._fsp--;

                    adaptor.addChild(root_0, bindcomment10.getTree());

                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:69:4: blockcomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    dbg.location(69,4);
                    pushFollow(FOLLOW_blockcomment_in_comment239);
                    blockcomment11=blockcomment();

                    state._fsp--;

                    adaptor.addChild(root_0, blockcomment11.getTree());

                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:70:4: linecomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    dbg.location(70,4);
                    pushFollow(FOLLOW_linecomment_in_comment244);
                    linecomment12=linecomment();

                    state._fsp--;

                    adaptor.addChild(root_0, linecomment12.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(71, 2);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "comment");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "comment"

    public static class blockcomment_return extends ParserRuleReturnScope {
        public TextNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "blockcomment"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:73:1: blockcomment returns [TextNode node] : C_ST charactors C_ED -> ^( TXTNODE C_ST charactors C_ED ) ;
    public final TwoWaySqlParser.blockcomment_return blockcomment() throws RecognitionException {
        TwoWaySqlParser.blockcomment_return retval = new TwoWaySqlParser.blockcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST13=null;
        Token C_ED15=null;
        TwoWaySqlParser.charactors_return charactors14 = null;


        CommonTree C_ST13_tree=null;
        CommonTree C_ED15_tree=null;
        RewriteRuleTokenStream stream_C_ST=new RewriteRuleTokenStream(adaptor,"token C_ST");
        RewriteRuleTokenStream stream_C_ED=new RewriteRuleTokenStream(adaptor,"token C_ED");
        RewriteRuleSubtreeStream stream_charactors=new RewriteRuleSubtreeStream(adaptor,"rule charactors");

        		retval.node = f.textNode();
        	
        try { dbg.enterRule(getGrammarFileName(), "blockcomment");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(73, 1);

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:80:2: ( C_ST charactors C_ED -> ^( TXTNODE C_ST charactors C_ED ) )
            dbg.enterAlt(1);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:81:2: C_ST charactors C_ED
            {
            dbg.location(81,2);
            C_ST13=(Token)match(input,C_ST,FOLLOW_C_ST_in_blockcomment271);  
            stream_C_ST.add(C_ST13);

            dbg.location(81,7);
            pushFollow(FOLLOW_charactors_in_blockcomment273);
            charactors14=charactors();

            state._fsp--;

            stream_charactors.add(charactors14.getTree());
            dbg.location(81,18);
            C_ED15=(Token)match(input,C_ED,FOLLOW_C_ED_in_blockcomment275);  
            stream_C_ED.add(C_ED15);

            dbg.location(81,23);
             retval.node.append(C_ST13);retval.node.append(C_ED15); 


            // AST REWRITE
            // elements: C_ED, C_ST, charactors
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 82:2: -> ^( TXTNODE C_ST charactors C_ED )
            {
                dbg.location(82,5);
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:82:5: ^( TXTNODE C_ST charactors C_ED )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                dbg.location(82,7);
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TXTNODE, "TXTNODE"), root_1);

                dbg.location(82,15);
                adaptor.addChild(root_1, stream_C_ST.nextNode());
                dbg.location(82,20);
                adaptor.addChild(root_1, stream_charactors.nextTree());
                dbg.location(82,31);
                adaptor.addChild(root_1, stream_C_ED.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.node.freeze();
            	
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(83, 2);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "blockcomment");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "blockcomment"

    public static class linecomment_return extends ParserRuleReturnScope {
        public TextNode node;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "linecomment"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:85:1: linecomment returns [TextNode node] : C_LN_ST charactors C_LN_ED -> ^( TXTNODE C_LN_ST charactors C_LN_ED ) ;
    public final TwoWaySqlParser.linecomment_return linecomment() throws RecognitionException {
        TwoWaySqlParser.linecomment_return retval = new TwoWaySqlParser.linecomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_LN_ST16=null;
        Token C_LN_ED18=null;
        TwoWaySqlParser.charactors_return charactors17 = null;


        CommonTree C_LN_ST16_tree=null;
        CommonTree C_LN_ED18_tree=null;
        RewriteRuleTokenStream stream_C_LN_ST=new RewriteRuleTokenStream(adaptor,"token C_LN_ST");
        RewriteRuleTokenStream stream_C_LN_ED=new RewriteRuleTokenStream(adaptor,"token C_LN_ED");
        RewriteRuleSubtreeStream stream_charactors=new RewriteRuleSubtreeStream(adaptor,"rule charactors");

        		retval.node = f.textNode();
        	
        try { dbg.enterRule(getGrammarFileName(), "linecomment");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(85, 1);

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:92:2: ( C_LN_ST charactors C_LN_ED -> ^( TXTNODE C_LN_ST charactors C_LN_ED ) )
            dbg.enterAlt(1);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:93:2: C_LN_ST charactors C_LN_ED
            {
            dbg.location(93,2);
            C_LN_ST16=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_linecomment317);  
            stream_C_LN_ST.add(C_LN_ST16);

            dbg.location(93,10);
            pushFollow(FOLLOW_charactors_in_linecomment319);
            charactors17=charactors();

            state._fsp--;

            stream_charactors.add(charactors17.getTree());
            dbg.location(93,21);
            C_LN_ED18=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_linecomment321);  
            stream_C_LN_ED.add(C_LN_ED18);

            dbg.location(93,29);
             retval.node.append(C_LN_ST16);retval.node.append(C_LN_ED18); 


            // AST REWRITE
            // elements: charactors, C_LN_ED, C_LN_ST
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 94:2: -> ^( TXTNODE C_LN_ST charactors C_LN_ED )
            {
                dbg.location(94,5);
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:94:5: ^( TXTNODE C_LN_ST charactors C_LN_ED )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                dbg.location(94,7);
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TXTNODE, "TXTNODE"), root_1);

                dbg.location(94,15);
                adaptor.addChild(root_1, stream_C_LN_ST.nextNode());
                dbg.location(94,23);
                adaptor.addChild(root_1, stream_charactors.nextTree());
                dbg.location(94,34);
                adaptor.addChild(root_1, stream_C_LN_ED.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);


            		retval.node.freeze();
            	
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(95, 2);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "linecomment");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "linecomment"

    public static class ifcomment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ifcomment"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:97:1: ifcomment : ( C_ST IF expression C_ED txt ( elseifnode )* ( elsenode )? endcomment ) -> ^( IFNODE expression txt ( elseifnode )* ( elsenode )? ) ;
    public final TwoWaySqlParser.ifcomment_return ifcomment() throws RecognitionException {
        TwoWaySqlParser.ifcomment_return retval = new TwoWaySqlParser.ifcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST19=null;
        Token IF20=null;
        Token C_ED22=null;
        TwoWaySqlParser.expression_return expression21 = null;

        TwoWaySqlParser.txt_return txt23 = null;

        TwoWaySqlParser.elseifnode_return elseifnode24 = null;

        TwoWaySqlParser.elsenode_return elsenode25 = null;

        TwoWaySqlParser.endcomment_return endcomment26 = null;


        CommonTree C_ST19_tree=null;
        CommonTree IF20_tree=null;
        CommonTree C_ED22_tree=null;
        RewriteRuleTokenStream stream_C_ST=new RewriteRuleTokenStream(adaptor,"token C_ST");
        RewriteRuleTokenStream stream_C_ED=new RewriteRuleTokenStream(adaptor,"token C_ED");
        RewriteRuleTokenStream stream_IF=new RewriteRuleTokenStream(adaptor,"token IF");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_elsenode=new RewriteRuleSubtreeStream(adaptor,"rule elsenode");
        RewriteRuleSubtreeStream stream_endcomment=new RewriteRuleSubtreeStream(adaptor,"rule endcomment");
        RewriteRuleSubtreeStream stream_txt=new RewriteRuleSubtreeStream(adaptor,"rule txt");
        RewriteRuleSubtreeStream stream_elseifnode=new RewriteRuleSubtreeStream(adaptor,"rule elseifnode");
        try { dbg.enterRule(getGrammarFileName(), "ifcomment");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(97, 1);

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:97:11: ( ( C_ST IF expression C_ED txt ( elseifnode )* ( elsenode )? endcomment ) -> ^( IFNODE expression txt ( elseifnode )* ( elsenode )? ) )
            dbg.enterAlt(1);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:98:2: ( C_ST IF expression C_ED txt ( elseifnode )* ( elsenode )? endcomment )
            {
            dbg.location(98,2);
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:98:2: ( C_ST IF expression C_ED txt ( elseifnode )* ( elsenode )? endcomment )
            dbg.enterAlt(1);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:98:3: C_ST IF expression C_ED txt ( elseifnode )* ( elsenode )? endcomment
            {
            dbg.location(98,3);
            C_ST19=(Token)match(input,C_ST,FOLLOW_C_ST_in_ifcomment348);  
            stream_C_ST.add(C_ST19);

            dbg.location(98,8);
            IF20=(Token)match(input,IF,FOLLOW_IF_in_ifcomment350);  
            stream_IF.add(IF20);

            dbg.location(98,11);
            pushFollow(FOLLOW_expression_in_ifcomment352);
            expression21=expression();

            state._fsp--;

            stream_expression.add(expression21.getTree());
            dbg.location(98,22);
            C_ED22=(Token)match(input,C_ED,FOLLOW_C_ED_in_ifcomment354);  
            stream_C_ED.add(C_ED22);

            dbg.location(98,27);
            pushFollow(FOLLOW_txt_in_ifcomment356);
            txt23=txt();

            state._fsp--;

            stream_txt.add(txt23.getTree());
            dbg.location(98,31);
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:98:31: ( elseifnode )*
            try { dbg.enterSubRule(4);

            loop4:
            do {
                int alt4=2;
                try { dbg.enterDecision(4);

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


                } finally {dbg.exitDecision(4);}

                switch (alt4) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:98:31: elseifnode
            	    {
            	    dbg.location(98,31);
            	    pushFollow(FOLLOW_elseifnode_in_ifcomment358);
            	    elseifnode24=elseifnode();

            	    state._fsp--;

            	    stream_elseifnode.add(elseifnode24.getTree());

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);
            } finally {dbg.exitSubRule(4);}

            dbg.location(98,43);
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:98:43: ( elsenode )?
            int alt5=2;
            try { dbg.enterSubRule(5);
            try { dbg.enterDecision(5);

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
            } finally {dbg.exitDecision(5);}

            switch (alt5) {
                case 1 :
                    dbg.enterAlt(1);

                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:98:43: elsenode
                    {
                    dbg.location(98,43);
                    pushFollow(FOLLOW_elsenode_in_ifcomment361);
                    elsenode25=elsenode();

                    state._fsp--;

                    stream_elsenode.add(elsenode25.getTree());

                    }
                    break;

            }
            } finally {dbg.exitSubRule(5);}

            dbg.location(98,53);
            pushFollow(FOLLOW_endcomment_in_ifcomment364);
            endcomment26=endcomment();

            state._fsp--;

            stream_endcomment.add(endcomment26.getTree());

            }



            // AST REWRITE
            // elements: elsenode, elseifnode, expression, txt
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 99:2: -> ^( IFNODE expression txt ( elseifnode )* ( elsenode )? )
            {
                dbg.location(99,5);
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:99:5: ^( IFNODE expression txt ( elseifnode )* ( elsenode )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                dbg.location(99,7);
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(IFNODE, "IFNODE"), root_1);

                dbg.location(99,14);
                adaptor.addChild(root_1, stream_expression.nextTree());
                dbg.location(99,25);
                adaptor.addChild(root_1, stream_txt.nextTree());
                dbg.location(99,29);
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:99:29: ( elseifnode )*
                while ( stream_elseifnode.hasNext() ) {
                    dbg.location(99,29);
                    adaptor.addChild(root_1, stream_elseifnode.nextTree());

                }
                stream_elseifnode.reset();
                dbg.location(99,41);
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:99:41: ( elsenode )?
                if ( stream_elsenode.hasNext() ) {
                    dbg.location(99,41);
                    adaptor.addChild(root_1, stream_elsenode.nextTree());

                }
                stream_elsenode.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(100, 2);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "ifcomment");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "ifcomment"

    public static class elseifnode_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elseifnode"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:102:1: elseifnode : elseifcomment txt -> ^( ELSEIFNODE elseifcomment txt ) ;
    public final TwoWaySqlParser.elseifnode_return elseifnode() throws RecognitionException {
        TwoWaySqlParser.elseifnode_return retval = new TwoWaySqlParser.elseifnode_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.elseifcomment_return elseifcomment27 = null;

        TwoWaySqlParser.txt_return txt28 = null;


        RewriteRuleSubtreeStream stream_txt=new RewriteRuleSubtreeStream(adaptor,"rule txt");
        RewriteRuleSubtreeStream stream_elseifcomment=new RewriteRuleSubtreeStream(adaptor,"rule elseifcomment");
        try { dbg.enterRule(getGrammarFileName(), "elseifnode");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(102, 1);

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:102:12: ( elseifcomment txt -> ^( ELSEIFNODE elseifcomment txt ) )
            dbg.enterAlt(1);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:103:2: elseifcomment txt
            {
            dbg.location(103,2);
            pushFollow(FOLLOW_elseifcomment_in_elseifnode394);
            elseifcomment27=elseifcomment();

            state._fsp--;

            stream_elseifcomment.add(elseifcomment27.getTree());
            dbg.location(103,16);
            pushFollow(FOLLOW_txt_in_elseifnode396);
            txt28=txt();

            state._fsp--;

            stream_txt.add(txt28.getTree());


            // AST REWRITE
            // elements: elseifcomment, txt
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 103:20: -> ^( ELSEIFNODE elseifcomment txt )
            {
                dbg.location(103,23);
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:103:23: ^( ELSEIFNODE elseifcomment txt )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                dbg.location(103,25);
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ELSEIFNODE, "ELSEIFNODE"), root_1);

                dbg.location(103,36);
                adaptor.addChild(root_1, stream_elseifcomment.nextTree());
                dbg.location(103,50);
                adaptor.addChild(root_1, stream_txt.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(103, 54);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "elseifnode");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "elseifnode"

    public static class elsenode_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elsenode"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:105:1: elsenode : elsecomment txt -> ^( ELSENODE txt ) ;
    public final TwoWaySqlParser.elsenode_return elsenode() throws RecognitionException {
        TwoWaySqlParser.elsenode_return retval = new TwoWaySqlParser.elsenode_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.elsecomment_return elsecomment29 = null;

        TwoWaySqlParser.txt_return txt30 = null;


        RewriteRuleSubtreeStream stream_elsecomment=new RewriteRuleSubtreeStream(adaptor,"rule elsecomment");
        RewriteRuleSubtreeStream stream_txt=new RewriteRuleSubtreeStream(adaptor,"rule txt");
        try { dbg.enterRule(getGrammarFileName(), "elsenode");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(105, 1);

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:105:10: ( elsecomment txt -> ^( ELSENODE txt ) )
            dbg.enterAlt(1);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:106:2: elsecomment txt
            {
            dbg.location(106,2);
            pushFollow(FOLLOW_elsecomment_in_elsenode415);
            elsecomment29=elsecomment();

            state._fsp--;

            stream_elsecomment.add(elsecomment29.getTree());
            dbg.location(106,14);
            pushFollow(FOLLOW_txt_in_elsenode417);
            txt30=txt();

            state._fsp--;

            stream_txt.add(txt30.getTree());


            // AST REWRITE
            // elements: txt
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 106:18: -> ^( ELSENODE txt )
            {
                dbg.location(106,21);
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:106:21: ^( ELSENODE txt )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                dbg.location(106,23);
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ELSENODE, "ELSENODE"), root_1);

                dbg.location(106,32);
                adaptor.addChild(root_1, stream_txt.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(106, 36);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "elsenode");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "elsenode"

    public static class elseifcomment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elseifcomment"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:108:1: elseifcomment : ( C_ST ELSEIF expression C_ED | C_LN_ST ELSEIF expression C_LN_ED ) -> expression ;
    public final TwoWaySqlParser.elseifcomment_return elseifcomment() throws RecognitionException {
        TwoWaySqlParser.elseifcomment_return retval = new TwoWaySqlParser.elseifcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST31=null;
        Token ELSEIF32=null;
        Token C_ED34=null;
        Token C_LN_ST35=null;
        Token ELSEIF36=null;
        Token C_LN_ED38=null;
        TwoWaySqlParser.expression_return expression33 = null;

        TwoWaySqlParser.expression_return expression37 = null;


        CommonTree C_ST31_tree=null;
        CommonTree ELSEIF32_tree=null;
        CommonTree C_ED34_tree=null;
        CommonTree C_LN_ST35_tree=null;
        CommonTree ELSEIF36_tree=null;
        CommonTree C_LN_ED38_tree=null;
        RewriteRuleTokenStream stream_C_LN_ST=new RewriteRuleTokenStream(adaptor,"token C_LN_ST");
        RewriteRuleTokenStream stream_C_ST=new RewriteRuleTokenStream(adaptor,"token C_ST");
        RewriteRuleTokenStream stream_C_LN_ED=new RewriteRuleTokenStream(adaptor,"token C_LN_ED");
        RewriteRuleTokenStream stream_C_ED=new RewriteRuleTokenStream(adaptor,"token C_ED");
        RewriteRuleTokenStream stream_ELSEIF=new RewriteRuleTokenStream(adaptor,"token ELSEIF");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try { dbg.enterRule(getGrammarFileName(), "elseifcomment");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(108, 1);

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:108:15: ( ( C_ST ELSEIF expression C_ED | C_LN_ST ELSEIF expression C_LN_ED ) -> expression )
            dbg.enterAlt(1);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:109:2: ( C_ST ELSEIF expression C_ED | C_LN_ST ELSEIF expression C_LN_ED )
            {
            dbg.location(109,2);
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:109:2: ( C_ST ELSEIF expression C_ED | C_LN_ST ELSEIF expression C_LN_ED )
            int alt6=2;
            try { dbg.enterSubRule(6);
            try { dbg.enterDecision(6);

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

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(6);}

            switch (alt6) {
                case 1 :
                    dbg.enterAlt(1);

                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:109:3: C_ST ELSEIF expression C_ED
                    {
                    dbg.location(109,3);
                    C_ST31=(Token)match(input,C_ST,FOLLOW_C_ST_in_elseifcomment435);  
                    stream_C_ST.add(C_ST31);

                    dbg.location(109,8);
                    ELSEIF32=(Token)match(input,ELSEIF,FOLLOW_ELSEIF_in_elseifcomment437);  
                    stream_ELSEIF.add(ELSEIF32);

                    dbg.location(109,15);
                    pushFollow(FOLLOW_expression_in_elseifcomment439);
                    expression33=expression();

                    state._fsp--;

                    stream_expression.add(expression33.getTree());
                    dbg.location(109,26);
                    C_ED34=(Token)match(input,C_ED,FOLLOW_C_ED_in_elseifcomment441);  
                    stream_C_ED.add(C_ED34);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:109:33: C_LN_ST ELSEIF expression C_LN_ED
                    {
                    dbg.location(109,33);
                    C_LN_ST35=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_elseifcomment445);  
                    stream_C_LN_ST.add(C_LN_ST35);

                    dbg.location(109,41);
                    ELSEIF36=(Token)match(input,ELSEIF,FOLLOW_ELSEIF_in_elseifcomment447);  
                    stream_ELSEIF.add(ELSEIF36);

                    dbg.location(109,48);
                    pushFollow(FOLLOW_expression_in_elseifcomment449);
                    expression37=expression();

                    state._fsp--;

                    stream_expression.add(expression37.getTree());
                    dbg.location(109,59);
                    C_LN_ED38=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_elseifcomment451);  
                    stream_C_LN_ED.add(C_LN_ED38);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(6);}



            // AST REWRITE
            // elements: expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 110:3: -> expression
            {
                dbg.location(110,6);
                adaptor.addChild(root_0, stream_expression.nextTree());

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(110, 16);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "elseifcomment");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "elseifcomment"

    public static class elsecomment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elsecomment"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:112:1: elsecomment : ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED ) ;
    public final TwoWaySqlParser.elsecomment_return elsecomment() throws RecognitionException {
        TwoWaySqlParser.elsecomment_return retval = new TwoWaySqlParser.elsecomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST39=null;
        Token ELSE40=null;
        Token C_ED41=null;
        Token C_LN_ST42=null;
        Token ELSE43=null;
        Token C_LN_ED44=null;

        CommonTree C_ST39_tree=null;
        CommonTree ELSE40_tree=null;
        CommonTree C_ED41_tree=null;
        CommonTree C_LN_ST42_tree=null;
        CommonTree ELSE43_tree=null;
        CommonTree C_LN_ED44_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "elsecomment");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(112, 1);

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:112:13: ( ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED ) )
            dbg.enterAlt(1);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:113:2: ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED )
            {
            root_0 = (CommonTree)adaptor.nil();

            dbg.location(113,2);
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:113:2: ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED )
            int alt7=2;
            try { dbg.enterSubRule(7);
            try { dbg.enterDecision(7);

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

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(7);}

            switch (alt7) {
                case 1 :
                    dbg.enterAlt(1);

                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:113:3: C_ST ELSE C_ED
                    {
                    dbg.location(113,3);
                    C_ST39=(Token)match(input,C_ST,FOLLOW_C_ST_in_elsecomment468); 
                    C_ST39_tree = (CommonTree)adaptor.create(C_ST39);
                    adaptor.addChild(root_0, C_ST39_tree);

                    dbg.location(113,8);
                    ELSE40=(Token)match(input,ELSE,FOLLOW_ELSE_in_elsecomment470); 
                    ELSE40_tree = (CommonTree)adaptor.create(ELSE40);
                    adaptor.addChild(root_0, ELSE40_tree);

                    dbg.location(113,13);
                    C_ED41=(Token)match(input,C_ED,FOLLOW_C_ED_in_elsecomment472); 
                    C_ED41_tree = (CommonTree)adaptor.create(C_ED41);
                    adaptor.addChild(root_0, C_ED41_tree);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:113:20: C_LN_ST ELSE C_LN_ED
                    {
                    dbg.location(113,20);
                    C_LN_ST42=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_elsecomment476); 
                    C_LN_ST42_tree = (CommonTree)adaptor.create(C_LN_ST42);
                    adaptor.addChild(root_0, C_LN_ST42_tree);

                    dbg.location(113,28);
                    ELSE43=(Token)match(input,ELSE,FOLLOW_ELSE_in_elsecomment478); 
                    ELSE43_tree = (CommonTree)adaptor.create(ELSE43);
                    adaptor.addChild(root_0, ELSE43_tree);

                    dbg.location(113,33);
                    C_LN_ED44=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_elsecomment480); 
                    C_LN_ED44_tree = (CommonTree)adaptor.create(C_LN_ED44);
                    adaptor.addChild(root_0, C_LN_ED44_tree);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(7);}


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(113, 42);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "elsecomment");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "elsecomment"

    public static class expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expression"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:115:1: expression : charactors -> ^( EXPRESSIONNODE charactors ) ;
    public final TwoWaySqlParser.expression_return expression() throws RecognitionException {
        TwoWaySqlParser.expression_return retval = new TwoWaySqlParser.expression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        TwoWaySqlParser.charactors_return charactors45 = null;


        RewriteRuleSubtreeStream stream_charactors=new RewriteRuleSubtreeStream(adaptor,"rule charactors");
        try { dbg.enterRule(getGrammarFileName(), "expression");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(115, 1);

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:115:12: ( charactors -> ^( EXPRESSIONNODE charactors ) )
            dbg.enterAlt(1);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:116:2: charactors
            {
            dbg.location(116,2);
            pushFollow(FOLLOW_charactors_in_expression491);
            charactors45=charactors();

            state._fsp--;

            stream_charactors.add(charactors45.getTree());


            // AST REWRITE
            // elements: charactors
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 116:13: -> ^( EXPRESSIONNODE charactors )
            {
                dbg.location(116,16);
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:116:16: ^( EXPRESSIONNODE charactors )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                dbg.location(116,18);
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EXPRESSIONNODE, "EXPRESSIONNODE"), root_1);

                dbg.location(116,33);
                adaptor.addChild(root_1, stream_charactors.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(116, 45);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "expression");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "expression"

    public static class begincomment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "begincomment"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:118:1: begincomment : ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) txt endcomment ) -> ^( BEGINNODE txt ) ;
    public final TwoWaySqlParser.begincomment_return begincomment() throws RecognitionException {
        TwoWaySqlParser.begincomment_return retval = new TwoWaySqlParser.begincomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST46=null;
        Token BEGIN47=null;
        Token C_ED48=null;
        Token C_LN_ST49=null;
        Token BEGIN50=null;
        Token C_LN_ED51=null;
        TwoWaySqlParser.txt_return txt52 = null;

        TwoWaySqlParser.endcomment_return endcomment53 = null;


        CommonTree C_ST46_tree=null;
        CommonTree BEGIN47_tree=null;
        CommonTree C_ED48_tree=null;
        CommonTree C_LN_ST49_tree=null;
        CommonTree BEGIN50_tree=null;
        CommonTree C_LN_ED51_tree=null;
        RewriteRuleTokenStream stream_C_LN_ST=new RewriteRuleTokenStream(adaptor,"token C_LN_ST");
        RewriteRuleTokenStream stream_C_ST=new RewriteRuleTokenStream(adaptor,"token C_ST");
        RewriteRuleTokenStream stream_C_LN_ED=new RewriteRuleTokenStream(adaptor,"token C_LN_ED");
        RewriteRuleTokenStream stream_BEGIN=new RewriteRuleTokenStream(adaptor,"token BEGIN");
        RewriteRuleTokenStream stream_C_ED=new RewriteRuleTokenStream(adaptor,"token C_ED");
        RewriteRuleSubtreeStream stream_endcomment=new RewriteRuleSubtreeStream(adaptor,"rule endcomment");
        RewriteRuleSubtreeStream stream_txt=new RewriteRuleSubtreeStream(adaptor,"rule txt");
        try { dbg.enterRule(getGrammarFileName(), "begincomment");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(118, 1);

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:118:14: ( ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) txt endcomment ) -> ^( BEGINNODE txt ) )
            dbg.enterAlt(1);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:119:2: ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) txt endcomment )
            {
            dbg.location(119,2);
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:119:2: ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) txt endcomment )
            dbg.enterAlt(1);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:119:3: ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) txt endcomment
            {
            dbg.location(119,3);
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:119:3: ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED )
            int alt8=2;
            try { dbg.enterSubRule(8);
            try { dbg.enterDecision(8);

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

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(8);}

            switch (alt8) {
                case 1 :
                    dbg.enterAlt(1);

                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:119:4: C_ST BEGIN C_ED
                    {
                    dbg.location(119,4);
                    C_ST46=(Token)match(input,C_ST,FOLLOW_C_ST_in_begincomment512);  
                    stream_C_ST.add(C_ST46);

                    dbg.location(119,9);
                    BEGIN47=(Token)match(input,BEGIN,FOLLOW_BEGIN_in_begincomment514);  
                    stream_BEGIN.add(BEGIN47);

                    dbg.location(119,15);
                    C_ED48=(Token)match(input,C_ED,FOLLOW_C_ED_in_begincomment516);  
                    stream_C_ED.add(C_ED48);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:119:22: C_LN_ST BEGIN C_LN_ED
                    {
                    dbg.location(119,22);
                    C_LN_ST49=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_begincomment520);  
                    stream_C_LN_ST.add(C_LN_ST49);

                    dbg.location(119,30);
                    BEGIN50=(Token)match(input,BEGIN,FOLLOW_BEGIN_in_begincomment522);  
                    stream_BEGIN.add(BEGIN50);

                    dbg.location(119,36);
                    C_LN_ED51=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_begincomment524);  
                    stream_C_LN_ED.add(C_LN_ED51);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(8);}

            dbg.location(119,45);
            pushFollow(FOLLOW_txt_in_begincomment527);
            txt52=txt();

            state._fsp--;

            stream_txt.add(txt52.getTree());
            dbg.location(119,49);
            pushFollow(FOLLOW_endcomment_in_begincomment529);
            endcomment53=endcomment();

            state._fsp--;

            stream_endcomment.add(endcomment53.getTree());

            }



            // AST REWRITE
            // elements: txt
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 119:61: -> ^( BEGINNODE txt )
            {
                dbg.location(119,64);
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:119:64: ^( BEGINNODE txt )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                dbg.location(119,66);
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BEGINNODE, "BEGINNODE"), root_1);

                dbg.location(119,76);
                adaptor.addChild(root_1, stream_txt.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(119, 80);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "begincomment");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "begincomment"

    public static class endcomment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "endcomment"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:121:1: endcomment : ( C_ST END C_ED | C_LN_ST END C_LN_ED );
    public final TwoWaySqlParser.endcomment_return endcomment() throws RecognitionException {
        TwoWaySqlParser.endcomment_return retval = new TwoWaySqlParser.endcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST54=null;
        Token END55=null;
        Token C_ED56=null;
        Token C_LN_ST57=null;
        Token END58=null;
        Token C_LN_ED59=null;

        CommonTree C_ST54_tree=null;
        CommonTree END55_tree=null;
        CommonTree C_ED56_tree=null;
        CommonTree C_LN_ST57_tree=null;
        CommonTree END58_tree=null;
        CommonTree C_LN_ED59_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "endcomment");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(121, 1);

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:121:12: ( C_ST END C_ED | C_LN_ST END C_LN_ED )
            int alt9=2;
            try { dbg.enterDecision(9);

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

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(9);}

            switch (alt9) {
                case 1 :
                    dbg.enterAlt(1);

                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:122:2: C_ST END C_ED
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    dbg.location(122,2);
                    C_ST54=(Token)match(input,C_ST,FOLLOW_C_ST_in_endcomment547); 
                    C_ST54_tree = (CommonTree)adaptor.create(C_ST54);
                    adaptor.addChild(root_0, C_ST54_tree);

                    dbg.location(122,7);
                    END55=(Token)match(input,END,FOLLOW_END_in_endcomment549); 
                    END55_tree = (CommonTree)adaptor.create(END55);
                    adaptor.addChild(root_0, END55_tree);

                    dbg.location(122,11);
                    C_ED56=(Token)match(input,C_ED,FOLLOW_C_ED_in_endcomment551); 
                    C_ED56_tree = (CommonTree)adaptor.create(C_ED56);
                    adaptor.addChild(root_0, C_ED56_tree);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:122:18: C_LN_ST END C_LN_ED
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    dbg.location(122,18);
                    C_LN_ST57=(Token)match(input,C_LN_ST,FOLLOW_C_LN_ST_in_endcomment555); 
                    C_LN_ST57_tree = (CommonTree)adaptor.create(C_LN_ST57);
                    adaptor.addChild(root_0, C_LN_ST57_tree);

                    dbg.location(122,26);
                    END58=(Token)match(input,END,FOLLOW_END_in_endcomment557); 
                    END58_tree = (CommonTree)adaptor.create(END58);
                    adaptor.addChild(root_0, END58_tree);

                    dbg.location(122,30);
                    C_LN_ED59=(Token)match(input,C_LN_ED,FOLLOW_C_LN_ED_in_endcomment559); 
                    C_LN_ED59_tree = (CommonTree)adaptor.create(C_LN_ED59);
                    adaptor.addChild(root_0, C_LN_ED59_tree);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(122, 37);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "endcomment");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "endcomment"

    public static class bindcomment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "bindcomment"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:124:1: bindcomment : ( C_ST SYM_BIND expression C_ED charactors ) -> ^( BINDNODE expression ) ;
    public final TwoWaySqlParser.bindcomment_return bindcomment() throws RecognitionException {
        TwoWaySqlParser.bindcomment_return retval = new TwoWaySqlParser.bindcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST60=null;
        Token SYM_BIND61=null;
        Token C_ED63=null;
        TwoWaySqlParser.expression_return expression62 = null;

        TwoWaySqlParser.charactors_return charactors64 = null;


        CommonTree C_ST60_tree=null;
        CommonTree SYM_BIND61_tree=null;
        CommonTree C_ED63_tree=null;
        RewriteRuleTokenStream stream_C_ST=new RewriteRuleTokenStream(adaptor,"token C_ST");
        RewriteRuleTokenStream stream_SYM_BIND=new RewriteRuleTokenStream(adaptor,"token SYM_BIND");
        RewriteRuleTokenStream stream_C_ED=new RewriteRuleTokenStream(adaptor,"token C_ED");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_charactors=new RewriteRuleSubtreeStream(adaptor,"rule charactors");
        try { dbg.enterRule(getGrammarFileName(), "bindcomment");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(124, 1);

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:124:13: ( ( C_ST SYM_BIND expression C_ED charactors ) -> ^( BINDNODE expression ) )
            dbg.enterAlt(1);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:125:2: ( C_ST SYM_BIND expression C_ED charactors )
            {
            dbg.location(125,2);
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:125:2: ( C_ST SYM_BIND expression C_ED charactors )
            dbg.enterAlt(1);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:125:3: C_ST SYM_BIND expression C_ED charactors
            {
            dbg.location(125,3);
            C_ST60=(Token)match(input,C_ST,FOLLOW_C_ST_in_bindcomment569);  
            stream_C_ST.add(C_ST60);

            dbg.location(125,8);
            SYM_BIND61=(Token)match(input,SYM_BIND,FOLLOW_SYM_BIND_in_bindcomment571);  
            stream_SYM_BIND.add(SYM_BIND61);

            dbg.location(125,17);
            pushFollow(FOLLOW_expression_in_bindcomment573);
            expression62=expression();

            state._fsp--;

            stream_expression.add(expression62.getTree());
            dbg.location(125,28);
            C_ED63=(Token)match(input,C_ED,FOLLOW_C_ED_in_bindcomment575);  
            stream_C_ED.add(C_ED63);

            dbg.location(125,33);
            pushFollow(FOLLOW_charactors_in_bindcomment577);
            charactors64=charactors();

            state._fsp--;

            stream_charactors.add(charactors64.getTree());

            }



            // AST REWRITE
            // elements: expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 126:3: -> ^( BINDNODE expression )
            {
                dbg.location(126,6);
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:126:6: ^( BINDNODE expression )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                dbg.location(126,8);
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BINDNODE, "BINDNODE"), root_1);

                dbg.location(126,17);
                adaptor.addChild(root_1, stream_expression.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(126, 28);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "bindcomment");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "bindcomment"

    public static class inbind_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "inbind"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:128:1: inbind : IN C_ST SYM_BIND expression C_ED SYM_LP inbindchars ( SYM_C inbindchars )* SYM_RP -> ^( INBINDNODE IN expression ) ;
    public final TwoWaySqlParser.inbind_return inbind() throws RecognitionException {
        TwoWaySqlParser.inbind_return retval = new TwoWaySqlParser.inbind_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IN65=null;
        Token C_ST66=null;
        Token SYM_BIND67=null;
        Token C_ED69=null;
        Token SYM_LP70=null;
        Token SYM_C72=null;
        Token SYM_RP74=null;
        TwoWaySqlParser.expression_return expression68 = null;

        TwoWaySqlParser.inbindchars_return inbindchars71 = null;

        TwoWaySqlParser.inbindchars_return inbindchars73 = null;


        CommonTree IN65_tree=null;
        CommonTree C_ST66_tree=null;
        CommonTree SYM_BIND67_tree=null;
        CommonTree C_ED69_tree=null;
        CommonTree SYM_LP70_tree=null;
        CommonTree SYM_C72_tree=null;
        CommonTree SYM_RP74_tree=null;
        RewriteRuleTokenStream stream_C_ST=new RewriteRuleTokenStream(adaptor,"token C_ST");
        RewriteRuleTokenStream stream_IN=new RewriteRuleTokenStream(adaptor,"token IN");
        RewriteRuleTokenStream stream_SYM_C=new RewriteRuleTokenStream(adaptor,"token SYM_C");
        RewriteRuleTokenStream stream_SYM_BIND=new RewriteRuleTokenStream(adaptor,"token SYM_BIND");
        RewriteRuleTokenStream stream_SYM_RP=new RewriteRuleTokenStream(adaptor,"token SYM_RP");
        RewriteRuleTokenStream stream_C_ED=new RewriteRuleTokenStream(adaptor,"token C_ED");
        RewriteRuleTokenStream stream_SYM_LP=new RewriteRuleTokenStream(adaptor,"token SYM_LP");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_inbindchars=new RewriteRuleSubtreeStream(adaptor,"rule inbindchars");
        try { dbg.enterRule(getGrammarFileName(), "inbind");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(128, 1);

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:128:8: ( IN C_ST SYM_BIND expression C_ED SYM_LP inbindchars ( SYM_C inbindchars )* SYM_RP -> ^( INBINDNODE IN expression ) )
            dbg.enterAlt(1);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:129:2: IN C_ST SYM_BIND expression C_ED SYM_LP inbindchars ( SYM_C inbindchars )* SYM_RP
            {
            dbg.location(129,2);
            IN65=(Token)match(input,IN,FOLLOW_IN_in_inbind597);  
            stream_IN.add(IN65);

            dbg.location(129,5);
            C_ST66=(Token)match(input,C_ST,FOLLOW_C_ST_in_inbind599);  
            stream_C_ST.add(C_ST66);

            dbg.location(129,10);
            SYM_BIND67=(Token)match(input,SYM_BIND,FOLLOW_SYM_BIND_in_inbind601);  
            stream_SYM_BIND.add(SYM_BIND67);

            dbg.location(129,19);
            pushFollow(FOLLOW_expression_in_inbind603);
            expression68=expression();

            state._fsp--;

            stream_expression.add(expression68.getTree());
            dbg.location(129,30);
            C_ED69=(Token)match(input,C_ED,FOLLOW_C_ED_in_inbind605);  
            stream_C_ED.add(C_ED69);

            dbg.location(129,35);
            SYM_LP70=(Token)match(input,SYM_LP,FOLLOW_SYM_LP_in_inbind607);  
            stream_SYM_LP.add(SYM_LP70);

            dbg.location(129,42);
            pushFollow(FOLLOW_inbindchars_in_inbind609);
            inbindchars71=inbindchars();

            state._fsp--;

            stream_inbindchars.add(inbindchars71.getTree());
            dbg.location(129,54);
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:129:54: ( SYM_C inbindchars )*
            try { dbg.enterSubRule(10);

            loop10:
            do {
                int alt10=2;
                try { dbg.enterDecision(10);

                int LA10_0 = input.LA(1);

                if ( (LA10_0==SYM_C) ) {
                    alt10=1;
                }


                } finally {dbg.exitDecision(10);}

                switch (alt10) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:129:55: SYM_C inbindchars
            	    {
            	    dbg.location(129,55);
            	    SYM_C72=(Token)match(input,SYM_C,FOLLOW_SYM_C_in_inbind612);  
            	    stream_SYM_C.add(SYM_C72);

            	    dbg.location(129,61);
            	    pushFollow(FOLLOW_inbindchars_in_inbind614);
            	    inbindchars73=inbindchars();

            	    state._fsp--;

            	    stream_inbindchars.add(inbindchars73.getTree());

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);
            } finally {dbg.exitSubRule(10);}

            dbg.location(129,75);
            SYM_RP74=(Token)match(input,SYM_RP,FOLLOW_SYM_RP_in_inbind618);  
            stream_SYM_RP.add(SYM_RP74);



            // AST REWRITE
            // elements: IN, expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 130:3: -> ^( INBINDNODE IN expression )
            {
                dbg.location(130,6);
                // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:130:6: ^( INBINDNODE IN expression )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                dbg.location(130,8);
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(INBINDNODE, "INBINDNODE"), root_1);

                dbg.location(130,19);
                adaptor.addChild(root_1, stream_IN.nextNode());
                dbg.location(130,22);
                adaptor.addChild(root_1, stream_expression.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(130, 33);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "inbind");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "inbind"

    public static class inbindchars_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "inbindchars"
    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:132:1: inbindchars : ( IDENT | SYMBOLS | SYM_C | QUOTED )+ ;
    public final TwoWaySqlParser.inbindchars_return inbindchars() throws RecognitionException {
        TwoWaySqlParser.inbindchars_return retval = new TwoWaySqlParser.inbindchars_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set75=null;

        CommonTree set75_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "inbindchars");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(132, 1);

        try {
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:132:13: ( ( IDENT | SYMBOLS | SYM_C | QUOTED )+ )
            dbg.enterAlt(1);

            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:132:15: ( IDENT | SYMBOLS | SYM_C | QUOTED )+
            {
            root_0 = (CommonTree)adaptor.nil();

            dbg.location(132,15);
            // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:132:15: ( IDENT | SYMBOLS | SYM_C | QUOTED )+
            int cnt11=0;
            try { dbg.enterSubRule(11);

            loop11:
            do {
                int alt11=2;
                try { dbg.enterDecision(11);

                int LA11_0 = input.LA(1);

                if ( ((LA11_0>=IDENT && LA11_0<=QUOTED)||LA11_0==SYM_C) ) {
                    alt11=1;
                }


                } finally {dbg.exitDecision(11);}

                switch (alt11) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // D:\\development\\java\\workspace-3.4\\werkzeugkasten\\twowaysql\\werkzeugkasten\\twowaysql\\grammar\\TwoWaySql.g:
            	    {
            	    dbg.location(132,15);
            	    set75=(Token)input.LT(1);
            	    if ( (input.LA(1)>=IDENT && input.LA(1)<=QUOTED)||input.LA(1)==SYM_C ) {
            	        input.consume();
            	        adaptor.addChild(root_0, (CommonTree)adaptor.create(set75));
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        dbg.recognitionException(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        dbg.recognitionException(eee);

                        throw eee;
                }
                cnt11++;
            } while (true);
            } finally {dbg.exitSubRule(11);}


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(132, 49);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "inbindchars");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
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
        "\1\24\2\15\2\uffff\1\15\2\uffff\2\15\1\uffff";
    static final String DFA3_maxS =
        "\1\26\2\33\2\uffff\1\25\2\uffff\1\25\1\35\1\uffff";
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
            return "65:1: comment : ( begincomment | ifcomment | bindcomment | blockcomment | linecomment );";
        }
        public void error(NoViableAltException nvae) {
            dbg.recognitionException(nvae);
        }
    }
 

    public static final BitSet FOLLOW_txt_in_twowaySQL104 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_twowaySQL106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comment_in_txt126 = new BitSet(new long[]{0x00000000205FE002L});
    public static final BitSet FOLLOW_inbind_in_txt130 = new BitSet(new long[]{0x00000000205FE002L});
    public static final BitSet FOLLOW_txts_in_txt134 = new BitSet(new long[]{0x00000000205FE002L});
    public static final BitSet FOLLOW_set_in_charactors147 = new BitSet(new long[]{0x00000000000FE002L});
    public static final BitSet FOLLOW_charactors_in_txts199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_begincomment_in_comment224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifcomment_in_comment229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bindcomment_in_comment234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_blockcomment_in_comment239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_linecomment_in_comment244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_blockcomment271 = new BitSet(new long[]{0x00000000207FE000L});
    public static final BitSet FOLLOW_charactors_in_blockcomment273 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_C_ED_in_blockcomment275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_linecomment317 = new BitSet(new long[]{0x0000000020DFE000L});
    public static final BitSet FOLLOW_charactors_in_linecomment319 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_C_LN_ED_in_linecomment321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_ifcomment348 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_IF_in_ifcomment350 = new BitSet(new long[]{0x00000000205FE000L});
    public static final BitSet FOLLOW_expression_in_ifcomment352 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_C_ED_in_ifcomment354 = new BitSet(new long[]{0x00000000205FE000L});
    public static final BitSet FOLLOW_txt_in_ifcomment356 = new BitSet(new long[]{0x0000000000500000L});
    public static final BitSet FOLLOW_elseifnode_in_ifcomment358 = new BitSet(new long[]{0x0000000000500000L});
    public static final BitSet FOLLOW_elsenode_in_ifcomment361 = new BitSet(new long[]{0x0000000000500000L});
    public static final BitSet FOLLOW_endcomment_in_ifcomment364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elseifcomment_in_elseifnode394 = new BitSet(new long[]{0x00000000205FE000L});
    public static final BitSet FOLLOW_txt_in_elseifnode396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elsecomment_in_elsenode415 = new BitSet(new long[]{0x00000000205FE000L});
    public static final BitSet FOLLOW_txt_in_elsenode417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_elseifcomment435 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ELSEIF_in_elseifcomment437 = new BitSet(new long[]{0x00000000205FE000L});
    public static final BitSet FOLLOW_expression_in_elseifcomment439 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_C_ED_in_elseifcomment441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_elseifcomment445 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ELSEIF_in_elseifcomment447 = new BitSet(new long[]{0x00000000205FE000L});
    public static final BitSet FOLLOW_expression_in_elseifcomment449 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_C_LN_ED_in_elseifcomment451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_elsecomment468 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ELSE_in_elsecomment470 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_C_ED_in_elsecomment472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_elsecomment476 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ELSE_in_elsecomment478 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_C_LN_ED_in_elsecomment480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_charactors_in_expression491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_begincomment512 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_BEGIN_in_begincomment514 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_C_ED_in_begincomment516 = new BitSet(new long[]{0x00000000205FE000L});
    public static final BitSet FOLLOW_C_LN_ST_in_begincomment520 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_BEGIN_in_begincomment522 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_C_LN_ED_in_begincomment524 = new BitSet(new long[]{0x00000000205FE000L});
    public static final BitSet FOLLOW_txt_in_begincomment527 = new BitSet(new long[]{0x0000000000500000L});
    public static final BitSet FOLLOW_endcomment_in_begincomment529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_endcomment547 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_END_in_endcomment549 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_C_ED_in_endcomment551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_endcomment555 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_END_in_endcomment557 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_C_LN_ED_in_endcomment559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_bindcomment569 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_SYM_BIND_in_bindcomment571 = new BitSet(new long[]{0x00000000205FE000L});
    public static final BitSet FOLLOW_expression_in_bindcomment573 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_C_ED_in_bindcomment575 = new BitSet(new long[]{0x00000000205FE000L});
    public static final BitSet FOLLOW_charactors_in_bindcomment577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IN_in_inbind597 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_C_ST_in_inbind599 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_SYM_BIND_in_inbind601 = new BitSet(new long[]{0x00000000205FE000L});
    public static final BitSet FOLLOW_expression_in_inbind603 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_C_ED_in_inbind605 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_SYM_LP_in_inbind607 = new BitSet(new long[]{0x000000000002E000L});
    public static final BitSet FOLLOW_inbindchars_in_inbind609 = new BitSet(new long[]{0x00000000000A0000L});
    public static final BitSet FOLLOW_SYM_C_in_inbind612 = new BitSet(new long[]{0x000000000002E000L});
    public static final BitSet FOLLOW_inbindchars_in_inbind614 = new BitSet(new long[]{0x00000000000A0000L});
    public static final BitSet FOLLOW_SYM_RP_in_inbind618 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_inbindchars638 = new BitSet(new long[]{0x000000000002E002L});

}