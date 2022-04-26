JAVAC = javac
JAVAC_FLAGS = -sourcepath .

JAVA = java

.PHONY: bnfc runmini clean distclean vclean

all: bnfc runmini

runmini:
	${JAVAC} ${JAVAC_FLAGS} runmini.java
	chmod a+x runmini

bnfc:
	bnfc -m --java Mini.cf
	${JAVA} ${JAVA_FLAGS} JLex.Main Mini/Yylex
	${JAVA} ${JAVA_FLAGS} java_cup.Main -nopositions Mini/_cup.cup
	move sym.java Mini | move parser.java Mini

clean:
	 -rm -f Mini/Absyn/*.class Mini/*.class
	 -rm -f .dvi Mini.aux Mini.log Mini.ps  *.class

distclean: vclean

vclean: clean
	 -rm -f Mini/Absyn/*.java
	 -rmdir Mini/Absyn/
	 -rm -f Mini.tex Mini.dvi Mini.aux Mini.log Mini.ps 
	 -rm -f Mini/Yylex Mini/Mini.cup Mini/Yylex.java Mini/VisitSkel.java Mini/ComposVisitor.java Mini/AbstractVisitor.java Mini/FoldVisitor.java Mini/AllVisitor.java Mini/PrettyPrinter.java Mini/Skeleton.java Mini/Test.java Mini/sym.java Mini/parser.java Mini/*.class
	 -rmdir -p Mini/