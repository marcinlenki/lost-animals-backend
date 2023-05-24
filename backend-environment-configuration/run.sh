#!/bin/bash

env_file=".env"

if ! docker info > /dev/null 2>&1; then
  echo "ERROR: Docker isn't running, please start Docker and try again! Exiting..."
  exit 1
fi

if [ $# -eq 0 ]; then
    docker_profile="frontend"

else
  docker_profile=$1

  if [[ ! "$docker_profile" =~ ^(frontend|backend)$ ]]; then
        echo "ERROR: Invalid profile ${docker_profile}! Exiting..."
        exit 1
  fi
fi

if [[ "$docker_profile" = "backend" ]]; then
  # create empty env file
  touch "$env_file"

elif [ "$docker_profile" = "frontend" ] && [ ! -f "${env_file}" ]; then
  echo "ERROR: Can't locate \"$env_file\" environment file in the current directory! You should manually add the file. Exiting..."
  exit 1
fi

docker-compose --profile "${docker_profile}" stop &&
docker-compose --profile "${docker_profile}" rm -f &&
docker rmi -f marcinlenki/kpz-backend:latest 2> /dev/null || true &&
docker-compose --profile "${docker_profile}" up -d &&
echo "Finished container initialization..."

