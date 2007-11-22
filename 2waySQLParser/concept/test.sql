/* テスト用のクエリ */
SELECT * FROM EMP 
/* BEGIN() {*/
WHERE 
	/* IF(deptId != null && 0 < deptId.length) {*/
	EMPID = /*BIND(empId)*/10
	/* }*/
	/* IF(deptId != null && 0 < deptId.length) {*/
	AND DEPTID = /* bind(deptId) */ 3
	/* } */
	OR COMPID IN/*bInD(comps) */('aaa','ほげ')
/*}*/
/* このクエリがパースできれば、大体ＯＫ */