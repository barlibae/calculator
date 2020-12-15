### Requirements: 
You need JDK 14 or higher (or JRE 14 to run the jar available in build/libs)

### To run the application:

1. cd calculator
2. java -jar build/libs/calculator-api.jar server config.yml

or 
1. cd calculator
2. ./gradlew run

or 
1. Import the project in Intellij IDEA (File -> New -> Project from existing sources -> Import project from external model -> Gradle)
2. Edit configurations -> Add Application configuration -> Choose a name, choose main class name (com.calculator.CalculatorApplication), 
   add 'server config.yml' as program arguments

### To test / play with the application:

Can use swagger UI in order to test the application. 
1. Go to http://localhost:8080/index.html
2. Expand GET /calculations -> Try it out -> enter expression (e.g - / 10 + 1 1 * 1 2) -> Execute and check 
   the returned value in the response body
   
Note: The application will determine whether the expression is in infix or prefix notation based on the first character 
of the expression: if it is '(' (parenthesis) the notation will be considered infix, otherwise prefix.