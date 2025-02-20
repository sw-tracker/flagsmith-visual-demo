# flagsmith-visual-demo

## Running the Java Apps

### Linux / Mac

If you run on Linux or Mac, follow these steps:

To run the project: `mvn clean compiler:compile javafx:run`

To create a fat jar:

```sh
mvn compile package
java -jar shade/hellofx.jar
```

### Windows

If you run on Windows, follow these steps:

To run the project: `mvn compile exec:java`

To create a fat jar:

```sh
mvn compile package
java -jar shade\hellofx.jar
```

## Running the Feature Flag System

### FeatureVisor
