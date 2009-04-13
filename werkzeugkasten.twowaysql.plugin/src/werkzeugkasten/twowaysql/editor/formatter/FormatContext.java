package werkzeugkasten.twowaysql.editor.formatter;

public class FormatContext {

	int depth = 0;

	// INSERT
	// INSERT INTO HOGE (AAA,BBB,CCC,DDD) VALUES (1,2,3,4);
	// INSERT INTO HOGE
	// SELECT AAA,BBB FROM FUGA WHERE AAA = 1 AND BBB = 2 ORDER BY CCC DESC;
	// UPDATE
	// UPDATE SET AAA = 1, BBB = 2 WHERE CCC = 3;
	// 
	// DELETE
	// SELECT
}
