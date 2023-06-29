# Realtime Data streaming service
This application enable us to establish two way connection using websocket.Additionally it also uses Kafka for message delivery to calculate the longest palindromic length of the message and persist the data in database.

#start docker container:
Navigate to the file directory and run:
docker compose -f docker-compose.yml up -d
This command will start your docker containers in the detach mode.
To stop the container:
docker compose -f docker-compose.yml down

#start your springboot application running on port 9091

#Open your browser and visit : http://localhost:9091/
