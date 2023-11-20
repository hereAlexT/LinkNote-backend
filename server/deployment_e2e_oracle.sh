#!/bin/bash

start=$(date +"%s")


ssh -p "${SERVER_SSH_PORT}" "${SERVER_USER}"@"${SERVER_HOST}" -i e2e_private_key.key -t -t -o StrictHostKeyChecking=no << 'ENDSSH'

set -a
source .env
set +a

echo "${DOCKERHUB_USERNAME}"
echo "Logging in to Docker Hub..."
echo "${DOCKERHUB_TOKEN}" | docker login --username "${DOCKERHUB_USERNAME}" --password-stdin


# stop all docker container
echo "Stopping all Docker containers..."
docker stop $(docker ps -aq)

# run the services docker-compose -d
echo "Starting services with Docker Compose..."
docker-compose up -d

exit
ENDSSH

if [ $? -eq 0 ]; then
  echo "Deployment succeeded."
  exit 0
else
  echo "Deployment failed."
  exit 1
fi

# Record the end time
end=$(date +"%s")

# Calculate the time difference
diff=$(("$end" - "$start"))

# Output the deployment time
echo "Deployed in : ${diff}s"