// $ANTLR 3.0.1 D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g 2008-04-17 17:51:32

package twowaysql.grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class TwoWaySQLParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BEGINNODE", "IFNODE", "EXPRESSIONNODE", "ELSENODE", "ELSEIFNODE", "BINDNODE", "INBINDNODE", "IDENT", "SYMBOLS", "QUOTED", "SYM_BIND", "SYM_C", "SYM_LP", "SYM_RP", "C_ST", "C_ED", "C_LN_ST", "C_LN_ED", "IF", "ELSEIF", "ELSE", "BEGIN", "END", "IN", "SYM_Q", "LN_R", "LN_N", "CHAR", "WS", "LT", "WHITE_SPACES"
    };
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
    public static final int EOF=-1;
    public static final int C_ED=19;
    public static final int IF=22;
    public static final int ELSEIFNODE=8;
    public static final int WHITE_SPACES=34;
    public static final int ELSEIF=23;
    public static final int WS=32;
    public static final int SYM_Q=28;
    public static final int IN=27;
    public static final int BEGIN=25;
    public static final int IFNODE=5;
    public static final int IDENT=11;
    public static final int SYMBOLS=12;
    public static final int SYM_C=15;
    public static final int END=26;
    public static final int EXPRESSIONNODE=6;
    public static final int SYM_BIND=14;
    public static final int ELSENODE=7;
    public static final int C_LN_ED=21;
    public static final int BEGINNODE=4;
    public static final int LN_N=30;
    public static final int LN_R=29;

        public TwoWaySQLParser(TokenStream input) {
            super(input);
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g"; }


    public static class twowaySQL_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start twowaySQL
    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:32:1: twowaySQL : txt EOF ;
    public final twowaySQL_return twowaySQL() throws RecognitionException {
        twowaySQL_return retval = new twowaySQL_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token EOF2=null;
        txt_return txt1 = null;


        CommonTree EOF2_tree=null;

        try {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:32:11: ( txt EOF )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:32:13: txt EOF
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_txt_in_twowaySQL96);
            txt1=txt();
            _fsp--;

            adaptor.addChild(root_0, txt1.getTree());
            EOF2=(Token)input.LT(1);
            match(input,EOF,FOLLOW_EOF_in_twowaySQL98); 
            EOF2_tree = (CommonTree)adaptor.create(EOF2);
            adaptor.addChild(root_0, EOF2_tree);


            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end twowaySQL

    public static class txt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start txt
    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:34:1: txt : ( comment | inbind | charactors )+ ;
    public final txt_return txt() throws RecognitionException {
        txt_return retval = new txt_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        comment_return comment3 = null;

        inbind_return inbind4 = null;

        charactors_return charactors5 = null;



        try {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:34:5: ( ( comment | inbind | charactors )+ )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:34:7: ( comment | inbind | charactors )+
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:34:7: ( comment | inbind | charactors )+
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

                switch (alt1) {
            	case 1 :
            	    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:34:8: comment
            	    {
            	    pushFollow(FOLLOW_comment_in_txt107);
            	    comment3=comment();
            	    _fsp--;

            	    adaptor.addChild(root_0, comment3.getTree());

            	    }
            	    break;
            	case 2 :
            	    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:34:18: inbind
            	    {
            	    pushFollow(FOLLOW_inbind_in_txt111);
            	    inbind4=inbind();
            	    _fsp--;

            	    adaptor.addChild(root_0, inbind4.getTree());

            	    }
            	    break;
            	case 3 :
            	    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:34:27: charactors
            	    {
            	    pushFollow(FOLLOW_charactors_in_txt115);
            	    charactors5=charactors();
            	    _fsp--;

            	    adaptor.addChild(root_0, charactors5.getTree());

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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end txt

    public static class charactors_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start charactors
    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:37:1: charactors : ( IDENT | SYMBOLS | QUOTED | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+ ;
    public final charactors_return charactors() throws RecognitionException {
        charactors_return retval = new charactors_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set6=null;

        CommonTree set6_tree=null;

        try {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:37:12: ( ( IDENT | SYMBOLS | QUOTED | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+ )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:38:2: ( IDENT | SYMBOLS | QUOTED | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:38:2: ( IDENT | SYMBOLS | QUOTED | SYM_BIND | SYM_C | SYM_LP | SYM_RP )+
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
            	    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:
            	    {
            	    set6=(Token)input.LT(1);
            	    if ( (input.LA(1)>=IDENT && input.LA(1)<=SYM_RP) ) {
            	        input.consume();
            	        adaptor.addChild(root_0, adaptor.create(set6));
            	        errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recoverFromMismatchedSet(input,mse,FOLLOW_set_in_charactors128);    throw mse;
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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end charactors

    public static class comment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start comment
    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:42:1: comment : ( begincomment | ifcomment | bindcomment | blockcomment | linecomment );
    public final comment_return comment() throws RecognitionException {
        comment_return retval = new comment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        begincomment_return begincomment7 = null;

        ifcomment_return ifcomment8 = null;

        bindcomment_return bindcomment9 = null;

        blockcomment_return blockcomment10 = null;

        linecomment_return linecomment11 = null;



        try {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:42:9: ( begincomment | ifcomment | bindcomment | blockcomment | linecomment )
            int alt3=5;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:43:2: begincomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_begincomment_in_comment166);
                    begincomment7=begincomment();
                    _fsp--;

                    adaptor.addChild(root_0, begincomment7.getTree());

                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:44:4: ifcomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_ifcomment_in_comment171);
                    ifcomment8=ifcomment();
                    _fsp--;

                    adaptor.addChild(root_0, ifcomment8.getTree());

                    }
                    break;
                case 3 :
                    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:45:4: bindcomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_bindcomment_in_comment176);
                    bindcomment9=bindcomment();
                    _fsp--;

                    adaptor.addChild(root_0, bindcomment9.getTree());

                    }
                    break;
                case 4 :
                    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:46:4: blockcomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_blockcomment_in_comment181);
                    blockcomment10=blockcomment();
                    _fsp--;

                    adaptor.addChild(root_0, blockcomment10.getTree());

                    }
                    break;
                case 5 :
                    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:47:4: linecomment
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_linecomment_in_comment186);
                    linecomment11=linecomment();
                    _fsp--;

                    adaptor.addChild(root_0, linecomment11.getTree());

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
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end comment

    public static class blockcomment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start blockcomment
    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:50:1: blockcomment : C_ST charactors C_ED ;
    public final blockcomment_return blockcomment() throws RecognitionException {
        blockcomment_return retval = new blockcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST12=null;
        Token C_ED14=null;
        charactors_return charactors13 = null;


        CommonTree C_ST12_tree=null;
        CommonTree C_ED14_tree=null;

        try {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:50:14: ( C_ST charactors C_ED )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:51:2: C_ST charactors C_ED
            {
            root_0 = (CommonTree)adaptor.nil();

            C_ST12=(Token)input.LT(1);
            match(input,C_ST,FOLLOW_C_ST_in_blockcomment197); 
            C_ST12_tree = (CommonTree)adaptor.create(C_ST12);
            adaptor.addChild(root_0, C_ST12_tree);

            pushFollow(FOLLOW_charactors_in_blockcomment199);
            charactors13=charactors();
            _fsp--;

            adaptor.addChild(root_0, charactors13.getTree());
            C_ED14=(Token)input.LT(1);
            match(input,C_ED,FOLLOW_C_ED_in_blockcomment201); 
            C_ED14_tree = (CommonTree)adaptor.create(C_ED14);
            adaptor.addChild(root_0, C_ED14_tree);


            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end blockcomment

    public static class linecomment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start linecomment
    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:53:1: linecomment : C_LN_ST charactors C_LN_ED ;
    public final linecomment_return linecomment() throws RecognitionException {
        linecomment_return retval = new linecomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_LN_ST15=null;
        Token C_LN_ED17=null;
        charactors_return charactors16 = null;


        CommonTree C_LN_ST15_tree=null;
        CommonTree C_LN_ED17_tree=null;

        try {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:53:13: ( C_LN_ST charactors C_LN_ED )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:54:2: C_LN_ST charactors C_LN_ED
            {
            root_0 = (CommonTree)adaptor.nil();

            C_LN_ST15=(Token)input.LT(1);
            match(input,C_LN_ST,FOLLOW_C_LN_ST_in_linecomment211); 
            C_LN_ST15_tree = (CommonTree)adaptor.create(C_LN_ST15);
            adaptor.addChild(root_0, C_LN_ST15_tree);

            pushFollow(FOLLOW_charactors_in_linecomment213);
            charactors16=charactors();
            _fsp--;

            adaptor.addChild(root_0, charactors16.getTree());
            C_LN_ED17=(Token)input.LT(1);
            match(input,C_LN_ED,FOLLOW_C_LN_ED_in_linecomment215); 
            C_LN_ED17_tree = (CommonTree)adaptor.create(C_LN_ED17);
            adaptor.addChild(root_0, C_LN_ED17_tree);


            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end linecomment

    public static class ifcomment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start ifcomment
    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:56:1: ifcomment : ( C_ST IF expression C_ED txt ( elseifnode )* ( elsenode )? endcomment ) -> ^( IFNODE expression txt ( elseifnode )* ( elsenode )? ) ;
    public final ifcomment_return ifcomment() throws RecognitionException {
        ifcomment_return retval = new ifcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST18=null;
        Token IF19=null;
        Token C_ED21=null;
        expression_return expression20 = null;

        txt_return txt22 = null;

        elseifnode_return elseifnode23 = null;

        elsenode_return elsenode24 = null;

        endcomment_return endcomment25 = null;


        CommonTree C_ST18_tree=null;
        CommonTree IF19_tree=null;
        CommonTree C_ED21_tree=null;
        RewriteRuleTokenStream stream_C_ST=new RewriteRuleTokenStream(adaptor,"token C_ST");
        RewriteRuleTokenStream stream_C_ED=new RewriteRuleTokenStream(adaptor,"token C_ED");
        RewriteRuleTokenStream stream_IF=new RewriteRuleTokenStream(adaptor,"token IF");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_elsenode=new RewriteRuleSubtreeStream(adaptor,"rule elsenode");
        RewriteRuleSubtreeStream stream_endcomment=new RewriteRuleSubtreeStream(adaptor,"rule endcomment");
        RewriteRuleSubtreeStream stream_txt=new RewriteRuleSubtreeStream(adaptor,"rule txt");
        RewriteRuleSubtreeStream stream_elseifnode=new RewriteRuleSubtreeStream(adaptor,"rule elseifnode");
        try {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:56:11: ( ( C_ST IF expression C_ED txt ( elseifnode )* ( elsenode )? endcomment ) -> ^( IFNODE expression txt ( elseifnode )* ( elsenode )? ) )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:57:2: ( C_ST IF expression C_ED txt ( elseifnode )* ( elsenode )? endcomment )
            {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:57:2: ( C_ST IF expression C_ED txt ( elseifnode )* ( elsenode )? endcomment )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:57:3: C_ST IF expression C_ED txt ( elseifnode )* ( elsenode )? endcomment
            {
            C_ST18=(Token)input.LT(1);
            match(input,C_ST,FOLLOW_C_ST_in_ifcomment226); 
            stream_C_ST.add(C_ST18);

            IF19=(Token)input.LT(1);
            match(input,IF,FOLLOW_IF_in_ifcomment228); 
            stream_IF.add(IF19);

            pushFollow(FOLLOW_expression_in_ifcomment230);
            expression20=expression();
            _fsp--;

            stream_expression.add(expression20.getTree());
            C_ED21=(Token)input.LT(1);
            match(input,C_ED,FOLLOW_C_ED_in_ifcomment232); 
            stream_C_ED.add(C_ED21);

            pushFollow(FOLLOW_txt_in_ifcomment234);
            txt22=txt();
            _fsp--;

            stream_txt.add(txt22.getTree());
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:57:31: ( elseifnode )*
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
            	    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:57:31: elseifnode
            	    {
            	    pushFollow(FOLLOW_elseifnode_in_ifcomment236);
            	    elseifnode23=elseifnode();
            	    _fsp--;

            	    stream_elseifnode.add(elseifnode23.getTree());

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:57:43: ( elsenode )?
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
                    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:57:43: elsenode
                    {
                    pushFollow(FOLLOW_elsenode_in_ifcomment239);
                    elsenode24=elsenode();
                    _fsp--;

                    stream_elsenode.add(elsenode24.getTree());

                    }
                    break;

            }

            pushFollow(FOLLOW_endcomment_in_ifcomment242);
            endcomment25=endcomment();
            _fsp--;

            stream_endcomment.add(endcomment25.getTree());

            }


            // AST REWRITE
            // elements: txt, elseifnode, elsenode, expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 58:2: -> ^( IFNODE expression txt ( elseifnode )* ( elsenode )? )
            {
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:58:5: ^( IFNODE expression txt ( elseifnode )* ( elsenode )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(IFNODE, "IFNODE"), root_1);

                adaptor.addChild(root_1, stream_expression.next());
                adaptor.addChild(root_1, stream_txt.next());
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:58:29: ( elseifnode )*
                while ( stream_elseifnode.hasNext() ) {
                    adaptor.addChild(root_1, stream_elseifnode.next());

                }
                stream_elseifnode.reset();
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:58:41: ( elsenode )?
                if ( stream_elsenode.hasNext() ) {
                    adaptor.addChild(root_1, stream_elsenode.next());

                }
                stream_elsenode.reset();

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end ifcomment

    public static class elseifnode_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start elseifnode
    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:61:1: elseifnode : elseifcomment txt -> ^( ELSEIFNODE elseifcomment txt ) ;
    public final elseifnode_return elseifnode() throws RecognitionException {
        elseifnode_return retval = new elseifnode_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        elseifcomment_return elseifcomment26 = null;

        txt_return txt27 = null;


        RewriteRuleSubtreeStream stream_txt=new RewriteRuleSubtreeStream(adaptor,"rule txt");
        RewriteRuleSubtreeStream stream_elseifcomment=new RewriteRuleSubtreeStream(adaptor,"rule elseifcomment");
        try {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:61:12: ( elseifcomment txt -> ^( ELSEIFNODE elseifcomment txt ) )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:62:2: elseifcomment txt
            {
            pushFollow(FOLLOW_elseifcomment_in_elseifnode273);
            elseifcomment26=elseifcomment();
            _fsp--;

            stream_elseifcomment.add(elseifcomment26.getTree());
            pushFollow(FOLLOW_txt_in_elseifnode275);
            txt27=txt();
            _fsp--;

            stream_txt.add(txt27.getTree());

            // AST REWRITE
            // elements: elseifcomment, txt
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 62:20: -> ^( ELSEIFNODE elseifcomment txt )
            {
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:62:23: ^( ELSEIFNODE elseifcomment txt )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(ELSEIFNODE, "ELSEIFNODE"), root_1);

                adaptor.addChild(root_1, stream_elseifcomment.next());
                adaptor.addChild(root_1, stream_txt.next());

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end elseifnode

    public static class elsenode_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start elsenode
    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:64:1: elsenode : elsecomment txt -> ^( ELSENODE txt ) ;
    public final elsenode_return elsenode() throws RecognitionException {
        elsenode_return retval = new elsenode_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        elsecomment_return elsecomment28 = null;

        txt_return txt29 = null;


        RewriteRuleSubtreeStream stream_elsecomment=new RewriteRuleSubtreeStream(adaptor,"rule elsecomment");
        RewriteRuleSubtreeStream stream_txt=new RewriteRuleSubtreeStream(adaptor,"rule txt");
        try {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:64:10: ( elsecomment txt -> ^( ELSENODE txt ) )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:65:2: elsecomment txt
            {
            pushFollow(FOLLOW_elsecomment_in_elsenode294);
            elsecomment28=elsecomment();
            _fsp--;

            stream_elsecomment.add(elsecomment28.getTree());
            pushFollow(FOLLOW_txt_in_elsenode296);
            txt29=txt();
            _fsp--;

            stream_txt.add(txt29.getTree());

            // AST REWRITE
            // elements: txt
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 65:18: -> ^( ELSENODE txt )
            {
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:65:21: ^( ELSENODE txt )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(ELSENODE, "ELSENODE"), root_1);

                adaptor.addChild(root_1, stream_txt.next());

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end elsenode

    public static class elseifcomment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start elseifcomment
    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:67:1: elseifcomment : ( C_ST ELSEIF expression C_ED | C_LN_ST ELSEIF expression C_LN_ED ) -> expression ;
    public final elseifcomment_return elseifcomment() throws RecognitionException {
        elseifcomment_return retval = new elseifcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST30=null;
        Token ELSEIF31=null;
        Token C_ED33=null;
        Token C_LN_ST34=null;
        Token ELSEIF35=null;
        Token C_LN_ED37=null;
        expression_return expression32 = null;

        expression_return expression36 = null;


        CommonTree C_ST30_tree=null;
        CommonTree ELSEIF31_tree=null;
        CommonTree C_ED33_tree=null;
        CommonTree C_LN_ST34_tree=null;
        CommonTree ELSEIF35_tree=null;
        CommonTree C_LN_ED37_tree=null;
        RewriteRuleTokenStream stream_C_LN_ST=new RewriteRuleTokenStream(adaptor,"token C_LN_ST");
        RewriteRuleTokenStream stream_C_ST=new RewriteRuleTokenStream(adaptor,"token C_ST");
        RewriteRuleTokenStream stream_C_LN_ED=new RewriteRuleTokenStream(adaptor,"token C_LN_ED");
        RewriteRuleTokenStream stream_C_ED=new RewriteRuleTokenStream(adaptor,"token C_ED");
        RewriteRuleTokenStream stream_ELSEIF=new RewriteRuleTokenStream(adaptor,"token ELSEIF");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:67:15: ( ( C_ST ELSEIF expression C_ED | C_LN_ST ELSEIF expression C_LN_ED ) -> expression )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:68:2: ( C_ST ELSEIF expression C_ED | C_LN_ST ELSEIF expression C_LN_ED )
            {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:68:2: ( C_ST ELSEIF expression C_ED | C_LN_ST ELSEIF expression C_LN_ED )
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
                    new NoViableAltException("68:2: ( C_ST ELSEIF expression C_ED | C_LN_ST ELSEIF expression C_LN_ED )", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:68:3: C_ST ELSEIF expression C_ED
                    {
                    C_ST30=(Token)input.LT(1);
                    match(input,C_ST,FOLLOW_C_ST_in_elseifcomment314); 
                    stream_C_ST.add(C_ST30);

                    ELSEIF31=(Token)input.LT(1);
                    match(input,ELSEIF,FOLLOW_ELSEIF_in_elseifcomment316); 
                    stream_ELSEIF.add(ELSEIF31);

                    pushFollow(FOLLOW_expression_in_elseifcomment318);
                    expression32=expression();
                    _fsp--;

                    stream_expression.add(expression32.getTree());
                    C_ED33=(Token)input.LT(1);
                    match(input,C_ED,FOLLOW_C_ED_in_elseifcomment320); 
                    stream_C_ED.add(C_ED33);


                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:68:33: C_LN_ST ELSEIF expression C_LN_ED
                    {
                    C_LN_ST34=(Token)input.LT(1);
                    match(input,C_LN_ST,FOLLOW_C_LN_ST_in_elseifcomment324); 
                    stream_C_LN_ST.add(C_LN_ST34);

                    ELSEIF35=(Token)input.LT(1);
                    match(input,ELSEIF,FOLLOW_ELSEIF_in_elseifcomment326); 
                    stream_ELSEIF.add(ELSEIF35);

                    pushFollow(FOLLOW_expression_in_elseifcomment328);
                    expression36=expression();
                    _fsp--;

                    stream_expression.add(expression36.getTree());
                    C_LN_ED37=(Token)input.LT(1);
                    match(input,C_LN_ED,FOLLOW_C_LN_ED_in_elseifcomment330); 
                    stream_C_LN_ED.add(C_LN_ED37);


                    }
                    break;

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
            // 69:3: -> expression
            {
                adaptor.addChild(root_0, stream_expression.next());

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end elseifcomment

    public static class elsecomment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start elsecomment
    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:71:1: elsecomment : ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED ) ;
    public final elsecomment_return elsecomment() throws RecognitionException {
        elsecomment_return retval = new elsecomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST38=null;
        Token ELSE39=null;
        Token C_ED40=null;
        Token C_LN_ST41=null;
        Token ELSE42=null;
        Token C_LN_ED43=null;

        CommonTree C_ST38_tree=null;
        CommonTree ELSE39_tree=null;
        CommonTree C_ED40_tree=null;
        CommonTree C_LN_ST41_tree=null;
        CommonTree ELSE42_tree=null;
        CommonTree C_LN_ED43_tree=null;

        try {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:71:13: ( ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED ) )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:72:2: ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED )
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:72:2: ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED )
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
                    new NoViableAltException("72:2: ( C_ST ELSE C_ED | C_LN_ST ELSE C_LN_ED )", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:72:3: C_ST ELSE C_ED
                    {
                    C_ST38=(Token)input.LT(1);
                    match(input,C_ST,FOLLOW_C_ST_in_elsecomment347); 
                    C_ST38_tree = (CommonTree)adaptor.create(C_ST38);
                    adaptor.addChild(root_0, C_ST38_tree);

                    ELSE39=(Token)input.LT(1);
                    match(input,ELSE,FOLLOW_ELSE_in_elsecomment349); 
                    ELSE39_tree = (CommonTree)adaptor.create(ELSE39);
                    adaptor.addChild(root_0, ELSE39_tree);

                    C_ED40=(Token)input.LT(1);
                    match(input,C_ED,FOLLOW_C_ED_in_elsecomment351); 
                    C_ED40_tree = (CommonTree)adaptor.create(C_ED40);
                    adaptor.addChild(root_0, C_ED40_tree);


                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:72:20: C_LN_ST ELSE C_LN_ED
                    {
                    C_LN_ST41=(Token)input.LT(1);
                    match(input,C_LN_ST,FOLLOW_C_LN_ST_in_elsecomment355); 
                    C_LN_ST41_tree = (CommonTree)adaptor.create(C_LN_ST41);
                    adaptor.addChild(root_0, C_LN_ST41_tree);

                    ELSE42=(Token)input.LT(1);
                    match(input,ELSE,FOLLOW_ELSE_in_elsecomment357); 
                    ELSE42_tree = (CommonTree)adaptor.create(ELSE42);
                    adaptor.addChild(root_0, ELSE42_tree);

                    C_LN_ED43=(Token)input.LT(1);
                    match(input,C_LN_ED,FOLLOW_C_LN_ED_in_elsecomment359); 
                    C_LN_ED43_tree = (CommonTree)adaptor.create(C_LN_ED43);
                    adaptor.addChild(root_0, C_LN_ED43_tree);


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end elsecomment

    public static class expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start expression
    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:74:1: expression : charactors -> ^( EXPRESSIONNODE charactors ) ;
    public final expression_return expression() throws RecognitionException {
        expression_return retval = new expression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        charactors_return charactors44 = null;


        RewriteRuleSubtreeStream stream_charactors=new RewriteRuleSubtreeStream(adaptor,"rule charactors");
        try {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:74:12: ( charactors -> ^( EXPRESSIONNODE charactors ) )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:75:2: charactors
            {
            pushFollow(FOLLOW_charactors_in_expression370);
            charactors44=charactors();
            _fsp--;

            stream_charactors.add(charactors44.getTree());

            // AST REWRITE
            // elements: charactors
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 75:13: -> ^( EXPRESSIONNODE charactors )
            {
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:75:16: ^( EXPRESSIONNODE charactors )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(EXPRESSIONNODE, "EXPRESSIONNODE"), root_1);

                adaptor.addChild(root_1, stream_charactors.next());

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end expression

    public static class begincomment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start begincomment
    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:77:1: begincomment : ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) txt endcomment ) -> ^( BEGINNODE txt ) ;
    public final begincomment_return begincomment() throws RecognitionException {
        begincomment_return retval = new begincomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST45=null;
        Token BEGIN46=null;
        Token C_ED47=null;
        Token C_LN_ST48=null;
        Token BEGIN49=null;
        Token C_LN_ED50=null;
        txt_return txt51 = null;

        endcomment_return endcomment52 = null;


        CommonTree C_ST45_tree=null;
        CommonTree BEGIN46_tree=null;
        CommonTree C_ED47_tree=null;
        CommonTree C_LN_ST48_tree=null;
        CommonTree BEGIN49_tree=null;
        CommonTree C_LN_ED50_tree=null;
        RewriteRuleTokenStream stream_C_LN_ST=new RewriteRuleTokenStream(adaptor,"token C_LN_ST");
        RewriteRuleTokenStream stream_C_ST=new RewriteRuleTokenStream(adaptor,"token C_ST");
        RewriteRuleTokenStream stream_C_LN_ED=new RewriteRuleTokenStream(adaptor,"token C_LN_ED");
        RewriteRuleTokenStream stream_BEGIN=new RewriteRuleTokenStream(adaptor,"token BEGIN");
        RewriteRuleTokenStream stream_C_ED=new RewriteRuleTokenStream(adaptor,"token C_ED");
        RewriteRuleSubtreeStream stream_endcomment=new RewriteRuleSubtreeStream(adaptor,"rule endcomment");
        RewriteRuleSubtreeStream stream_txt=new RewriteRuleSubtreeStream(adaptor,"rule txt");
        try {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:77:14: ( ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) txt endcomment ) -> ^( BEGINNODE txt ) )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:78:2: ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) txt endcomment )
            {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:78:2: ( ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) txt endcomment )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:78:3: ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED ) txt endcomment
            {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:78:3: ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED )
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
                    new NoViableAltException("78:3: ( C_ST BEGIN C_ED | C_LN_ST BEGIN C_LN_ED )", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:78:4: C_ST BEGIN C_ED
                    {
                    C_ST45=(Token)input.LT(1);
                    match(input,C_ST,FOLLOW_C_ST_in_begincomment391); 
                    stream_C_ST.add(C_ST45);

                    BEGIN46=(Token)input.LT(1);
                    match(input,BEGIN,FOLLOW_BEGIN_in_begincomment393); 
                    stream_BEGIN.add(BEGIN46);

                    C_ED47=(Token)input.LT(1);
                    match(input,C_ED,FOLLOW_C_ED_in_begincomment395); 
                    stream_C_ED.add(C_ED47);


                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:78:22: C_LN_ST BEGIN C_LN_ED
                    {
                    C_LN_ST48=(Token)input.LT(1);
                    match(input,C_LN_ST,FOLLOW_C_LN_ST_in_begincomment399); 
                    stream_C_LN_ST.add(C_LN_ST48);

                    BEGIN49=(Token)input.LT(1);
                    match(input,BEGIN,FOLLOW_BEGIN_in_begincomment401); 
                    stream_BEGIN.add(BEGIN49);

                    C_LN_ED50=(Token)input.LT(1);
                    match(input,C_LN_ED,FOLLOW_C_LN_ED_in_begincomment403); 
                    stream_C_LN_ED.add(C_LN_ED50);


                    }
                    break;

            }

            pushFollow(FOLLOW_txt_in_begincomment406);
            txt51=txt();
            _fsp--;

            stream_txt.add(txt51.getTree());
            pushFollow(FOLLOW_endcomment_in_begincomment408);
            endcomment52=endcomment();
            _fsp--;

            stream_endcomment.add(endcomment52.getTree());

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
            // 78:61: -> ^( BEGINNODE txt )
            {
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:78:64: ^( BEGINNODE txt )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(BEGINNODE, "BEGINNODE"), root_1);

                adaptor.addChild(root_1, stream_txt.next());

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end begincomment

    public static class endcomment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start endcomment
    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:80:1: endcomment : ( C_ST END C_ED | C_LN_ST END C_LN_ED );
    public final endcomment_return endcomment() throws RecognitionException {
        endcomment_return retval = new endcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST53=null;
        Token END54=null;
        Token C_ED55=null;
        Token C_LN_ST56=null;
        Token END57=null;
        Token C_LN_ED58=null;

        CommonTree C_ST53_tree=null;
        CommonTree END54_tree=null;
        CommonTree C_ED55_tree=null;
        CommonTree C_LN_ST56_tree=null;
        CommonTree END57_tree=null;
        CommonTree C_LN_ED58_tree=null;

        try {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:80:12: ( C_ST END C_ED | C_LN_ST END C_LN_ED )
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
                    new NoViableAltException("80:1: endcomment : ( C_ST END C_ED | C_LN_ST END C_LN_ED );", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:81:2: C_ST END C_ED
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    C_ST53=(Token)input.LT(1);
                    match(input,C_ST,FOLLOW_C_ST_in_endcomment426); 
                    C_ST53_tree = (CommonTree)adaptor.create(C_ST53);
                    adaptor.addChild(root_0, C_ST53_tree);

                    END54=(Token)input.LT(1);
                    match(input,END,FOLLOW_END_in_endcomment428); 
                    END54_tree = (CommonTree)adaptor.create(END54);
                    adaptor.addChild(root_0, END54_tree);

                    C_ED55=(Token)input.LT(1);
                    match(input,C_ED,FOLLOW_C_ED_in_endcomment430); 
                    C_ED55_tree = (CommonTree)adaptor.create(C_ED55);
                    adaptor.addChild(root_0, C_ED55_tree);


                    }
                    break;
                case 2 :
                    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:81:18: C_LN_ST END C_LN_ED
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    C_LN_ST56=(Token)input.LT(1);
                    match(input,C_LN_ST,FOLLOW_C_LN_ST_in_endcomment434); 
                    C_LN_ST56_tree = (CommonTree)adaptor.create(C_LN_ST56);
                    adaptor.addChild(root_0, C_LN_ST56_tree);

                    END57=(Token)input.LT(1);
                    match(input,END,FOLLOW_END_in_endcomment436); 
                    END57_tree = (CommonTree)adaptor.create(END57);
                    adaptor.addChild(root_0, END57_tree);

                    C_LN_ED58=(Token)input.LT(1);
                    match(input,C_LN_ED,FOLLOW_C_LN_ED_in_endcomment438); 
                    C_LN_ED58_tree = (CommonTree)adaptor.create(C_LN_ED58);
                    adaptor.addChild(root_0, C_LN_ED58_tree);


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
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end endcomment

    public static class bindcomment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start bindcomment
    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:83:1: bindcomment : ( C_ST SYM_BIND expression C_ED ( charactors )+ ) -> ^( BINDNODE expression ) ;
    public final bindcomment_return bindcomment() throws RecognitionException {
        bindcomment_return retval = new bindcomment_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token C_ST59=null;
        Token SYM_BIND60=null;
        Token C_ED62=null;
        expression_return expression61 = null;

        charactors_return charactors63 = null;


        CommonTree C_ST59_tree=null;
        CommonTree SYM_BIND60_tree=null;
        CommonTree C_ED62_tree=null;
        RewriteRuleTokenStream stream_C_ST=new RewriteRuleTokenStream(adaptor,"token C_ST");
        RewriteRuleTokenStream stream_SYM_BIND=new RewriteRuleTokenStream(adaptor,"token SYM_BIND");
        RewriteRuleTokenStream stream_C_ED=new RewriteRuleTokenStream(adaptor,"token C_ED");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_charactors=new RewriteRuleSubtreeStream(adaptor,"rule charactors");
        try {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:83:13: ( ( C_ST SYM_BIND expression C_ED ( charactors )+ ) -> ^( BINDNODE expression ) )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:84:2: ( C_ST SYM_BIND expression C_ED ( charactors )+ )
            {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:84:2: ( C_ST SYM_BIND expression C_ED ( charactors )+ )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:84:3: C_ST SYM_BIND expression C_ED ( charactors )+
            {
            C_ST59=(Token)input.LT(1);
            match(input,C_ST,FOLLOW_C_ST_in_bindcomment448); 
            stream_C_ST.add(C_ST59);

            SYM_BIND60=(Token)input.LT(1);
            match(input,SYM_BIND,FOLLOW_SYM_BIND_in_bindcomment450); 
            stream_SYM_BIND.add(SYM_BIND60);

            pushFollow(FOLLOW_expression_in_bindcomment452);
            expression61=expression();
            _fsp--;

            stream_expression.add(expression61.getTree());
            C_ED62=(Token)input.LT(1);
            match(input,C_ED,FOLLOW_C_ED_in_bindcomment454); 
            stream_C_ED.add(C_ED62);

            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:84:33: ( charactors )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>=IDENT && LA10_0<=SYM_RP)) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:84:33: charactors
            	    {
            	    pushFollow(FOLLOW_charactors_in_bindcomment456);
            	    charactors63=charactors();
            	    _fsp--;

            	    stream_charactors.add(charactors63.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt10 >= 1 ) break loop10;
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        throw eee;
                }
                cnt10++;
            } while (true);


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
            // 85:3: -> ^( BINDNODE expression )
            {
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:85:6: ^( BINDNODE expression )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(BINDNODE, "BINDNODE"), root_1);

                adaptor.addChild(root_1, stream_expression.next());

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end bindcomment

    public static class inbind_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start inbind
    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:87:1: inbind : IN C_ST SYM_BIND expression C_ED SYM_LP inbindchars ( SYM_C ( inbindchars )+ )* SYM_RP -> ^( INBINDNODE IN expression ) ;
    public final inbind_return inbind() throws RecognitionException {
        inbind_return retval = new inbind_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IN64=null;
        Token C_ST65=null;
        Token SYM_BIND66=null;
        Token C_ED68=null;
        Token SYM_LP69=null;
        Token SYM_C71=null;
        Token SYM_RP73=null;
        expression_return expression67 = null;

        inbindchars_return inbindchars70 = null;

        inbindchars_return inbindchars72 = null;


        CommonTree IN64_tree=null;
        CommonTree C_ST65_tree=null;
        CommonTree SYM_BIND66_tree=null;
        CommonTree C_ED68_tree=null;
        CommonTree SYM_LP69_tree=null;
        CommonTree SYM_C71_tree=null;
        CommonTree SYM_RP73_tree=null;
        RewriteRuleTokenStream stream_C_ST=new RewriteRuleTokenStream(adaptor,"token C_ST");
        RewriteRuleTokenStream stream_IN=new RewriteRuleTokenStream(adaptor,"token IN");
        RewriteRuleTokenStream stream_SYM_C=new RewriteRuleTokenStream(adaptor,"token SYM_C");
        RewriteRuleTokenStream stream_SYM_BIND=new RewriteRuleTokenStream(adaptor,"token SYM_BIND");
        RewriteRuleTokenStream stream_SYM_RP=new RewriteRuleTokenStream(adaptor,"token SYM_RP");
        RewriteRuleTokenStream stream_C_ED=new RewriteRuleTokenStream(adaptor,"token C_ED");
        RewriteRuleTokenStream stream_SYM_LP=new RewriteRuleTokenStream(adaptor,"token SYM_LP");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_inbindchars=new RewriteRuleSubtreeStream(adaptor,"rule inbindchars");
        try {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:87:8: ( IN C_ST SYM_BIND expression C_ED SYM_LP inbindchars ( SYM_C ( inbindchars )+ )* SYM_RP -> ^( INBINDNODE IN expression ) )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:88:2: IN C_ST SYM_BIND expression C_ED SYM_LP inbindchars ( SYM_C ( inbindchars )+ )* SYM_RP
            {
            IN64=(Token)input.LT(1);
            match(input,IN,FOLLOW_IN_in_inbind477); 
            stream_IN.add(IN64);

            C_ST65=(Token)input.LT(1);
            match(input,C_ST,FOLLOW_C_ST_in_inbind479); 
            stream_C_ST.add(C_ST65);

            SYM_BIND66=(Token)input.LT(1);
            match(input,SYM_BIND,FOLLOW_SYM_BIND_in_inbind481); 
            stream_SYM_BIND.add(SYM_BIND66);

            pushFollow(FOLLOW_expression_in_inbind483);
            expression67=expression();
            _fsp--;

            stream_expression.add(expression67.getTree());
            C_ED68=(Token)input.LT(1);
            match(input,C_ED,FOLLOW_C_ED_in_inbind485); 
            stream_C_ED.add(C_ED68);

            SYM_LP69=(Token)input.LT(1);
            match(input,SYM_LP,FOLLOW_SYM_LP_in_inbind487); 
            stream_SYM_LP.add(SYM_LP69);

            pushFollow(FOLLOW_inbindchars_in_inbind489);
            inbindchars70=inbindchars();
            _fsp--;

            stream_inbindchars.add(inbindchars70.getTree());
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:88:54: ( SYM_C ( inbindchars )+ )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==SYM_C) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:88:55: SYM_C ( inbindchars )+
            	    {
            	    SYM_C71=(Token)input.LT(1);
            	    match(input,SYM_C,FOLLOW_SYM_C_in_inbind492); 
            	    stream_SYM_C.add(SYM_C71);

            	    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:88:61: ( inbindchars )+
            	    int cnt11=0;
            	    loop11:
            	    do {
            	        int alt11=2;
            	        int LA11_0 = input.LA(1);

            	        if ( ((LA11_0>=IDENT && LA11_0<=QUOTED)||LA11_0==SYM_C) ) {
            	            alt11=1;
            	        }


            	        switch (alt11) {
            	    	case 1 :
            	    	    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:88:61: inbindchars
            	    	    {
            	    	    pushFollow(FOLLOW_inbindchars_in_inbind494);
            	    	    inbindchars72=inbindchars();
            	    	    _fsp--;

            	    	    stream_inbindchars.add(inbindchars72.getTree());

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
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            SYM_RP73=(Token)input.LT(1);
            match(input,SYM_RP,FOLLOW_SYM_RP_in_inbind499); 
            stream_SYM_RP.add(SYM_RP73);


            // AST REWRITE
            // elements: IN, expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 89:3: -> ^( INBINDNODE IN expression )
            {
                // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:89:6: ^( INBINDNODE IN expression )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(INBINDNODE, "INBINDNODE"), root_1);

                adaptor.addChild(root_1, stream_IN.next());
                adaptor.addChild(root_1, stream_expression.next());

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end inbind

    public static class inbindchars_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start inbindchars
    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:91:1: inbindchars : ( IDENT | SYMBOLS | SYM_C | QUOTED )+ ;
    public final inbindchars_return inbindchars() throws RecognitionException {
        inbindchars_return retval = new inbindchars_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set74=null;

        CommonTree set74_tree=null;

        try {
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:91:13: ( ( IDENT | SYMBOLS | SYM_C | QUOTED )+ )
            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:91:15: ( IDENT | SYMBOLS | SYM_C | QUOTED )+
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:91:15: ( IDENT | SYMBOLS | SYM_C | QUOTED )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==SYM_C) ) {
                    alt13=1;
                }
                else if ( ((LA13_0>=IDENT && LA13_0<=QUOTED)) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // D:\\development\\java\\workspace-3.3\\twowaySQL\\src\\twowaysql\\grammar\\TwoWaySQL.g:
            	    {
            	    set74=(Token)input.LT(1);
            	    if ( (input.LA(1)>=IDENT && input.LA(1)<=QUOTED)||input.LA(1)==SYM_C ) {
            	        input.consume();
            	        adaptor.addChild(root_0, adaptor.create(set74));
            	        errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recoverFromMismatchedSet(input,mse,FOLLOW_set_in_inbindchars519);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end inbindchars


    protected DFA3 dfa3 = new DFA3(this);
    static final String DFA3_eotS =
        "\13\uffff";
    static final String DFA3_eofS =
        "\11\uffff\1\6\1\uffff";
    static final String DFA3_minS =
        "\1\22\2\13\1\uffff\1\13\3\uffff\2\13\1\uffff";
    static final String DFA3_maxS =
        "\1\24\2\31\1\uffff\1\23\3\uffff\1\23\1\33\1\uffff";
    static final String DFA3_acceptS =
        "\3\uffff\1\1\1\uffff\1\2\1\4\1\5\2\uffff\1\3";
    static final String DFA3_specialS =
        "\13\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\1\1\uffff\1\2",
            "\3\6\1\4\3\6\4\uffff\1\5\2\uffff\1\3",
            "\7\7\7\uffff\1\3",
            "",
            "\7\10\1\uffff\1\6",
            "",
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
            return "42:1: comment : ( begincomment | ifcomment | bindcomment | blockcomment | linecomment );";
        }
    }
 

    public static final BitSet FOLLOW_txt_in_twowaySQL96 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_twowaySQL98 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comment_in_txt107 = new BitSet(new long[]{0x000000000817F802L});
    public static final BitSet FOLLOW_inbind_in_txt111 = new BitSet(new long[]{0x000000000817F802L});
    public static final BitSet FOLLOW_charactors_in_txt115 = new BitSet(new long[]{0x000000000817F802L});
    public static final BitSet FOLLOW_set_in_charactors128 = new BitSet(new long[]{0x000000000003F802L});
    public static final BitSet FOLLOW_begincomment_in_comment166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifcomment_in_comment171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bindcomment_in_comment176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_blockcomment_in_comment181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_linecomment_in_comment186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_blockcomment197 = new BitSet(new long[]{0x000000000003F800L});
    public static final BitSet FOLLOW_charactors_in_blockcomment199 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_C_ED_in_blockcomment201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_linecomment211 = new BitSet(new long[]{0x000000000003F800L});
    public static final BitSet FOLLOW_charactors_in_linecomment213 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_C_LN_ED_in_linecomment215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_ifcomment226 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_IF_in_ifcomment228 = new BitSet(new long[]{0x000000000003F800L});
    public static final BitSet FOLLOW_expression_in_ifcomment230 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_C_ED_in_ifcomment232 = new BitSet(new long[]{0x000000000817F800L});
    public static final BitSet FOLLOW_txt_in_ifcomment234 = new BitSet(new long[]{0x0000000000140000L});
    public static final BitSet FOLLOW_elseifnode_in_ifcomment236 = new BitSet(new long[]{0x0000000000140000L});
    public static final BitSet FOLLOW_elsenode_in_ifcomment239 = new BitSet(new long[]{0x0000000000140000L});
    public static final BitSet FOLLOW_endcomment_in_ifcomment242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elseifcomment_in_elseifnode273 = new BitSet(new long[]{0x000000000817F800L});
    public static final BitSet FOLLOW_txt_in_elseifnode275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elsecomment_in_elsenode294 = new BitSet(new long[]{0x000000000817F800L});
    public static final BitSet FOLLOW_txt_in_elsenode296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_elseifcomment314 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ELSEIF_in_elseifcomment316 = new BitSet(new long[]{0x000000000003F800L});
    public static final BitSet FOLLOW_expression_in_elseifcomment318 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_C_ED_in_elseifcomment320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_elseifcomment324 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ELSEIF_in_elseifcomment326 = new BitSet(new long[]{0x000000000003F800L});
    public static final BitSet FOLLOW_expression_in_elseifcomment328 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_C_LN_ED_in_elseifcomment330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_elsecomment347 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ELSE_in_elsecomment349 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_C_ED_in_elsecomment351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_elsecomment355 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ELSE_in_elsecomment357 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_C_LN_ED_in_elsecomment359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_charactors_in_expression370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_begincomment391 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_BEGIN_in_begincomment393 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_C_ED_in_begincomment395 = new BitSet(new long[]{0x000000000817F800L});
    public static final BitSet FOLLOW_C_LN_ST_in_begincomment399 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_BEGIN_in_begincomment401 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_C_LN_ED_in_begincomment403 = new BitSet(new long[]{0x000000000817F800L});
    public static final BitSet FOLLOW_txt_in_begincomment406 = new BitSet(new long[]{0x0000000000140000L});
    public static final BitSet FOLLOW_endcomment_in_begincomment408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_endcomment426 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_END_in_endcomment428 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_C_ED_in_endcomment430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_LN_ST_in_endcomment434 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_END_in_endcomment436 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_C_LN_ED_in_endcomment438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_ST_in_bindcomment448 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_SYM_BIND_in_bindcomment450 = new BitSet(new long[]{0x000000000003F800L});
    public static final BitSet FOLLOW_expression_in_bindcomment452 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_C_ED_in_bindcomment454 = new BitSet(new long[]{0x000000000003F800L});
    public static final BitSet FOLLOW_charactors_in_bindcomment456 = new BitSet(new long[]{0x000000000003F802L});
    public static final BitSet FOLLOW_IN_in_inbind477 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_C_ST_in_inbind479 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_SYM_BIND_in_inbind481 = new BitSet(new long[]{0x000000000003F800L});
    public static final BitSet FOLLOW_expression_in_inbind483 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_C_ED_in_inbind485 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_SYM_LP_in_inbind487 = new BitSet(new long[]{0x000000000000B800L});
    public static final BitSet FOLLOW_inbindchars_in_inbind489 = new BitSet(new long[]{0x0000000000028000L});
    public static final BitSet FOLLOW_SYM_C_in_inbind492 = new BitSet(new long[]{0x000000000000B800L});
    public static final BitSet FOLLOW_inbindchars_in_inbind494 = new BitSet(new long[]{0x000000000002B800L});
    public static final BitSet FOLLOW_SYM_RP_in_inbind499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_inbindchars519 = new BitSet(new long[]{0x000000000000B802L});

}