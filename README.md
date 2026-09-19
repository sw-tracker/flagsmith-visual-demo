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

All three backends are defined in the root [`docker-compose.yml`](docker-compose.yml) as
[Compose profiles](https://docs.docker.com/compose/how-tos/profiles/), so you only start the
one(s) you need, and each has a matching `make <name>-stop`. Run `make help` for the full list,
or `make ff-status` to see what's currently running.

### Flipt

```shell
brew install flipt-io/brew/flipt
flipt validate flipt/features.yml
```

```shell
make flipt-start   # docker compose --profile flipt up -d
make flipt-stop    # docker compose --profile flipt down
```

- UI: http://localhost:8080
- Server: http://localhost:9000, http://localhost:8080/api/v1

### Flagsmith

- Start Flagsmith
  ```shell
  make fs-start   # docker compose --profile flagsmith up -d
  make fs-stop    # docker compose --profile flagsmith down
  ```
- Create an account
- Create an organisation and a project
- Get the API key for the project and save it in `PROJECT_ENV_KEY`
- Start the JavaFX applications

- UI: http://localhost:8000
- API: http://localhost:8000/api/v1/

### Unleash

- Start Unleash
  ```shell
  make ul-start   # docker compose --profile unleash up -d
  make ul-stop    # docker compose --profile unleash down
  ```
- In your browser, go to http://localhost:4242 and log in using the following credentials:
  - username: `admin`
  - password: `unleash4all`

- UI: http://localhost:4242
