#!/bin/bash

env_file=".env"
compose_file="docker-compose-arm.yml"
mvn="../mvnw" # use maven wrapper
current_branch=$(git branch --show-current)

if ! docker info > /dev/null 2>&1; then
  echo "[ERROR]: Docker isn't running, please start Docker and try again! Exiting..."
  exit 1
fi

if [ $# -eq 0 ]; then
    docker_profile="frontend"

else
  docker_profile=$1

  if [[ ! "$docker_profile" =~ ^(frontend|backend)$ ]]; then
        echo "[ERROR]: Invalid profile ${docker_profile}! Exiting..."
        exit 1
  fi
fi

if [[ "$docker_profile" = "backend" ]]; then
  # create empty env file
  touch "$env_file"

elif [ "$docker_profile" = "frontend" ] && [ ! -f "${env_file}" ]; then
  echo "[ERROR]: Can't locate \"$env_file\" environment file in the current directory! You should manually add the file. Exiting..."
  exit 1
fi

echo "[INFO] Checking for changes in the remote..."
git diff "$current_branch..origin/$current_branch" --quiet;
change=$?

if [ "$change" == 1 ]; then
  echo -n "[INFO] Changed detected, pulling from the remote... "
  git pull --quiet
  echo "Done"
  rm -rf "../target"
fi

if ! compgen -G "../target/*.jar" > /dev/null; then
    echo -n "[INFO] Building Spring Boot Application... "

    if ! eval "$mvn -B package --file ../pom.xml -DskipTests > /dev/null"; then
      echo "" #newline
      echo "[ERROR]: Unable to build Spring project. Exiting..."
      exit 1
    else
      echo "Done"
    fi

fi

docker-compose --profile "${docker_profile}" -f "$compose_file" stop &&
docker-compose --profile "${docker_profile}" -f "$compose_file" rm -f &&
docker-compose --profile "${docker_profile}" -f "$compose_file" up -d &&
echo "Finished container initialization..."
