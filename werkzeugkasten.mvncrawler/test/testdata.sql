DELETE FROM ARTIFACT;
INSERT INTO ARTIFACT( ID, GROUP_ID , ARTIFACT_ID , VERSION ) VALUES (1,'gid1','aid1','v1');
INSERT INTO ARTIFACT( ID, GROUP_ID , ARTIFACT_ID , VERSION ) VALUES (2,'gid2','aid2','v2');
INSERT INTO ARTIFACT( ID, GROUP_ID , ARTIFACT_ID , VERSION ) VALUES (3,'gid3','aid3','v3');
INSERT INTO ARTIFACT( ID, GROUP_ID , ARTIFACT_ID , VERSION ) VALUES (4,'gid4','aid4','v4');
INSERT INTO ARTIFACT( ID, GROUP_ID , ARTIFACT_ID , VERSION ) VALUES (5,'gid5','aid5','v5');

DELETE FROM DEPENDENCY;
INSERT INTO DEPENDENCY ( ID, FROM_ARTIFACT_ID , TO_ARTIFACT_ID , OPTIONAL ) VALUES (1,1,2,true);
INSERT INTO DEPENDENCY ( ID, FROM_ARTIFACT_ID , TO_ARTIFACT_ID , OPTIONAL ) VALUES (2,1,3,false);
INSERT INTO DEPENDENCY ( ID, FROM_ARTIFACT_ID , TO_ARTIFACT_ID , OPTIONAL ) VALUES (3,2,4,true);
INSERT INTO DEPENDENCY ( ID, FROM_ARTIFACT_ID , TO_ARTIFACT_ID , OPTIONAL ) VALUES (4,3,1,true);

