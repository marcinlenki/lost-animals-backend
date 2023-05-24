#!/bin/bash

# This script can be used to shut down the application whole stack.
# Its main purpose is to be used as linux service ExecStop script.

if ! docker info > /dev/null 2>&1; then
  echo "ERROR: Docker isn't running, please start Docker and try again! Exiting..."
  exit 1
fi

docker_profile="frontend"

docker compose --profile "${docker_profile}" stop
