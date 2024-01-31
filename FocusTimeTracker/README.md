## Local Development Environment
1. Create a `.env` file with the following example values:
    ```
    POSTGRES_USER=postgres
    POSTGRES_PASSWORD=postgres
    POSTGRES_DB=play-together
    POSTGRES_PORT=5432
    PORT=5000
    SPRING_PROFILES_ACTIVE=dev
    ```
2. Initiate the services using `./local-dev/docker-compose-dev.yml` along with the `.env` file.  

You can either set up a run configuration in IntelliJ or start it manually.
Example of manual start command: `docker-compose -f ./local-dev/docker-compose-dev.yml --env-file .env up`

The Docker setup will launch:
1. PostgreSQL database
2. Development Proxy

## Before coding

### Running the Local Backend with IntelliJ
To start the application, execute `application bootRun` from the Gradle panel, using the variables specified in the `.env` file.

### Running the Backend with images
...
