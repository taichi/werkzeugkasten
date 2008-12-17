SELECT ID,NAME FROM TEST
WHERE
/* if aaa < 1 */
	ID = 0
	/*elseif 1 == aaa */
	ID = /*?int? aaa */1
	/*else*/
	ID = 2
/*end*/
