#!/bin/bash

if ! docker info > /dev/null 2>&1; then
  echo "Docker isn't running, please start Docker and try again!"
  exit 1
fi

if [ $# -eq 0 ]; then
    docker_profile="frontend"

else
  docker_profile=$1

  if [[ ! "$docker_profile" =~ ^(frontend|backend)$ ]]; then
        echo "Invalid profile ${docker_profile}!"
        exit 1
  fi
fi

docker-compose stop &&
docker-compose rm -f &&
docker rmi -f marcinlenki/kpz-backend:latest >> /dev/null || true &&
docker-compose --profile "${docker_profile}" up -d &&
echo "Finished container initialization..."

