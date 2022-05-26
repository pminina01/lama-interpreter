JAVAC = javac
JAVAC_FLAGS = -sourcepath .

JAVA = java

.PHONY: bnfc runlama clean distclean vclean

all: bnfc runlama

runlama:
	${JAVAC} ${JAVAC_FLAGS} runlama.java
	chmod a+x runlama

bnfc:
	bnfc -m --java Lama.cf
	${JAVA} ${JAVA_FLAGS} JLex.Main Lama/Yylex
	${JAVA} ${JAVA_FLAGS} java_cup.Main -nopositions Lama/_cup.cup
	move sym.java Lama | move parser.java Lama

clean:
	 -rm -f Lama/Absyn/*.class Lama/*.class
	 -rm -f .dvi Lama.aux Lama.log Lama.ps  *.class

distclean: vclean

vclean: clean
	 -rm -f Lama/Absyn/*.java
	 -rmdir Lama/Absyn/
	 -rm -f Lama.tex Lama.dvi Lama.aux Lama.log Lama.ps 
	 -rm -f Lama/Yylex Lama/Lama.cup Lama/Yylex.java Lama/VisitSkel.java Lama/ComposVisitor.java Lama/AbstractVisitor.java Lama/FoldVisitor.java Lama/AllVisitor.java Lama/PrettyPrinter.java Lama/Skeleton.java Lama/Test.java Lama/sym.java Lama/parser.java Lama/*.class
	 -rmdir -p Lama/
