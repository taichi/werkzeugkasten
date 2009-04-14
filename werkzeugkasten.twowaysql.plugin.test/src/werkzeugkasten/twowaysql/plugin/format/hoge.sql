select AAA,BBB,CCC,DDD,hoge() From hoge
where /*BEGIN*/
/*if hoge!=null*/hoge = /*?hoge*/'fuga'/*end*/
/*if hoge!=null */
hoge = /*?hoge*/'fuga'/*end*/
/*if hoge!=null*/hoge = /*?hoge*/'fuga'
/*elseif hoge==null*/hoge =null*/
--else
piropiro
--end
/*if hoge!=null*/hoge = /*?hoge*/'fuga'
/*if fuga == null*/and fuga=null/*end*/
/*end*/
/*if hoge!=null*/hoge = /*?hoge*/'fuga'
/*if fuga < 0*/and fuga=null/*end*//*end*//*END*/