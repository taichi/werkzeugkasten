SELECT ID,NAME
FROM TEST
WHERE
/*if aaa < 1 */
	ID = /*?int? aaa*/1 
/*end*/
/*if 0 < bbb[0] */
AND ID IN/*?int? bbb*/(1,2) 
/*End*/ 