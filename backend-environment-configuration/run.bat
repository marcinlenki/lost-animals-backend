@echo off

IF "%#%" "-eq" "0" (
  SET "docker_profile=frontend"
) ELSE (
  SET "docker_profile=%~1"
  IF "!" "%docker_profile%" == "frontend" "]" || [ "!" "%docker_profile%" == "backend" (
    echo "Invalid profile %docker_profile%!"
    exit "1"
  )
)
docker-compose "stop"
docker-compose "rm" "-f"
docker-compose "pull"
docker-compose "--profile" "%docker_profile%" "up" "-d"