## Local Development Environment
1. Create a `.env` file with the following example values:
    ```
    POSTGRES_USER=postgres
    POSTGRES_PASSWORD=postgres
    POSTGRES_DB=focus_time_tracker
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
1. Set prettier. In Intellij you can just set "Automatic Prettier configuration".

### Running the Local Backend with IntelliJ
To start the application, execute `application bootRun` from the Gradle panel, using the variables specified in the `.env` file.

### Running the Backend with images
...

## Tailwind
npx tailwind -i ./src/main/resources/static/main.css -o ./src/main/resources/static/output.css --watch

1. `<link href="../static/output.css" rel="stylesheet" />`
2. `<script th:href="@{/htmx.min.js}"></script>`
3. `<script src="https://unpkg.com/htmx.org@1.9.10" integrity="sha384-D1Kt99CQMDuVetoL1lrYwg5t+9QdHe7NLX/SoJYkXDFfX37iInKRy5xLSi8nO7UC" crossorigin="anonymous"></script>`


