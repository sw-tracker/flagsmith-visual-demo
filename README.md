# flagsmith-visual-demo

## Running the Java Apps

```shell
mise install
```

### Linux / Mac

If you run on Linux or Mac, follow these steps:

To run the project: `mvn clean compiler:compile javafx:run`

To create a fat jar:

```sh
mvn compile package
java -jar shade/hellofx.jar
```

To run a bunch of apps, use [this script](startDemo.sh). This script:
- builds the app (mvn command)
- launches apps
- if you type `q`, it will kill all apps and terminate

### Windows

If you run on Windows, follow these steps:

To run the project: `mvn compile exec:java`

To create a fat jar:

```sh
mvn compile package
java -jar shade\hellofx.jar
```

## Running the Feature Flag System

### Flipt

```shell
brew install flipt-io/brew/flipt
flipt validate features.yml
```

```shell
# Run Flipt locally
docker run -it --rm \
  -p 8080:8080 \
  -p 9000:9000 \
  -v "$(pwd):/data" \
  -e FLIPT_GIT_POLL_INTERVAL=5s \
  -e FLIPT_STORAGE_TYPE=local \
  -e FLIPT_STORAGE_LOCAL_PATH=/data \
  flipt/flipt:latest
```

- UI: localhost:8080
- Server: localhost:9000, localhost:8080/api/v1
