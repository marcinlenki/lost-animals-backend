#!/bin/bash

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
docker-compose pull &&
docker-compose --profile "${docker_profile}" up -d &&
echo "Finished container initilization..."