SELECT ID,NAME FROM TEST
WHERE
/* if aaa < 1 */
	ID = 0
	/*elseif 1 == aaa */
	or ID = /*?int? aaa */1
	/*else*/
	or ID = 2
/*end*/
