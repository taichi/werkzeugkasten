/* テスト用のクエリ */
SELECT * FROM EMP 
/*? BEGIN() {*/
WHERE 
	/*? IF(empId != null && 0 < empId.length) {*/
		EMPID = /*?BIND(empId){*/10 /*?}*/AND EMPIDREF = /*?bind(empId){*/	20/*?}
	}*/
	/*? IF(deptId != null && 0 < deptId.length){
	*/AND DEPTID = /*? bind(deptId) {*/ 3/*?}
	}
	if(comps != null && 0 < comps.size()) {*/ OR
		COMPID IN	/*?InBIND(comps) {*/ ('aaa' ,'ほげ')/*?}*/
	/*?} */
	OR EMPNAME LIKE /*?llike(empName){*/'ccc'/*?}*/
/*?}*/
/* このクエリがパースできれば、大体ＯＫ */


	/*? IF(empId != null && 0 < empId.length) {*/
		EMPID = 
		/*?BIND(empId){*/
			10 
		/*?}*/
		AND EMPIDREF = 
		/*?bind(empId){*/
			20
		/*?}
	}*/
