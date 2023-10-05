default: runProgram

GraphADT.class: GraphADT.java
	javac GraphADT.java

CS400Graph.class: CS400Graph.java
	javac CS400Graph.java

runProgram: GraphADT.class
	java GraphADT

	CS400Graph.class
		java CS400Graph

# Makefile for running GraphTest
runTests: GraphTest.class junit5.jar
	java -jar junit5.jar -cp . --scan-classpath -n GraphTest

GraphTest.class: GraphTest.java
	javac -cp .:junit5.jar GraphTest.java

clean:
	rm *.class