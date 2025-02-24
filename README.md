# flagsmith-visual-demo

## Running the Java Apps

```shell
mise install
```

### Linux / Mac

If you run on Linux or Mac, follow these steps:

```sh
# start a single app instance
make j-start
# start multiple instances
make j-all
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
make flipt-start
```

- UI: http://localhost:8080
- Server: http://localhost:9000, http://localhost:8080/api/v1

### Flagsmith

- Start Flagsmith
  ```shell
  # Run Flagsmith locally
  make fs-start
  ```
- Create an account
- Create an organisation and a project
- Get the API key for the project and save it in `PROJECT_ENV_KEY`
- Start the JavaFX applications

- UI: http://localhost:8000
- API: http://localhost:8000/api/v1/

### Unleashed

- Start Flagsmith
  ```shell
  make ul-start
  ```
- In your browser, go to http://localhost:4242 and log in using the following credentials:
  - username: `admin`
  - password: `unleash4all`


- UI: http://localhost:4242
