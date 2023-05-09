# Lost Animals Backend 

## API Documentation
Api docs coming soon. <br>

## Application Setup
### TL;DR
Run:

```shell
cd backend-environment-configuration
bash run.sh
```

or

```shell
cd backend-environment-configuration
bat run.bat
```

Depending on your OS.


### Using Docker Compose
The Lost Animals Backend contains of three services:
- Java Spring Application
- Postgres Database
- Cloud Beaver web IDE

In order to successfully run the whole stack **locally** you will need **Docker Desktop** (which contains _docker compose_). To install Docker Desktop
see [Download Docker Desktop](https://www.docker.com/products/docker-desktop/).

Docker Compose builds the whole backend application stack (spring app, postgres database, cloud beaver IDE) 
at once using the latest images from DockerHub.

To build the stack use one of two profiles:
- frontend (run whole backend stack)
- backend (run postgres database only)

>To ensure that you always use the latest image versions please use the _**run**_ convenience script. 
> **The profile should be specified as an argument for the script. If no profile is specified, the profile defaults to _frontend_.**

You might need a few seconds for the services to start before accessing them.

#### Run On Linux/Mac
```shell
cd backend-environment-configuration
bash run.sh
```

Or with specified profile:
```shell
cd backend-environment-configuration
bash run.sh backend
```

#### Run On Windows
```shell
cd backend-environment-configuration
bat run.bat backend
```