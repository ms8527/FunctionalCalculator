# Calculator
Command Line Java Calculator

Created a Maven project and implemented 3 levels of verbosity: INFO, ERROR, and DEBUG.  
By adding log4j.properties file src/main/resources

## Steps to work on:
Eclipse IDE is used, Maven is used to import any dependencies.
Compile the project using > *mvn compile
test and compile the test class > *mvn test-compile
create the test class> *mvn test
Build project or use the command line:> *mvn clean compile assembly:single*
Finally create the jar using >*mvn install


## How to execute in Eclipse IDE:
Create the project SynopsysCalculator and having Main.java file for starting it's execution
For program arguments has to be set, set "add(1,2)"

## Steps to run on the command line:
Use the Executable jar generated.
Open the commnad prompt and go it to the location where jar is saved for me it's desktop 
C:\Users\DRYPSAHANI\Desktop>
And run the following command by passing the expression as argument and see the desired result.
java -jar SynopsysCalculator.jar "add(1,2)"
Final value of the expression add(1,2)=3

java -jar SynopsysCalculator.jar "let(a, 5, add(a, a))"
Final value of the expression let(a, 5, add(a, a))=10

java -jar SynopsysCalculator.jar "let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))"
Final value of the expression let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))=40

## Test case:
Test case has been written junit
TestCalculator for the test cases written in class in src/test/java for unit tests.

##Some more set of Examples:
"add(1,mult(2,3))"                    //equals 7

"mult(add(2,2),div(9,3))"             //equals 12

"let(a,5,add(5,5))"                   //equals 10

"let(a,5,let(b,mult(a,10),add(b,a)))" //equals 55

"let(a,let(b,10,add(b,b)),let(b,20,add(a,b)))"     //equals 40

