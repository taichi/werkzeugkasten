SET CLASSPATH=.\bin;lib\fuzzyxml.jar;lib\slf4j-api-1.5.0.jar;lib\slf4j-simple-1.5.0.jar;lib\commons-jxpath-1.2.jar;lib\h2-2008-02-22.jar;lib\mvnhack-0.0.2.jar;
C:\jdk1.6.0_02\bin\java.exe -Xmx1024m -Xms1024m -cp %CLASSPATH% werkzeugkasten.mvncrawler.Main >crawl.log 2>crawl.error.log