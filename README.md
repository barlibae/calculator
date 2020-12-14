To run the application:

1. cd calculator
2. ./gradlew build
3. java -jar build/libs/calculator-api.jar server config.yml

or 
1. cd calculator
2. ./gradlew run

or 
1. Import the project in Intellij IDEA (File -> New -> Project from existing sources -> Import project from external model -> Gradle)
2. Edit configurations -> Add Application configuration -> Choose a name, choose main class name (com.calculator.CalculatorApplication), 
   add 'server config.yml' as program arguments