SELECT ID,NAME
FROM TEST
WHERE
/*if aaa < 1 */
	ID = /*?int? aaa*/1 
/*end*/
/*if 0 < bbb */
AND ID = /*?int? bbb*/1 
/*End*/ 