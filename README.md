# WebSocket Two-Way Connection and Palindrome Length Calculator

This application enables us to establish a two-way connection using WebSocket. Additionally, it uses Kafka for message delivery to calculate the longest palindromic length of the message and persist the data in the database.

## Getting Started

Follow the steps below to get started with the application:

1. Start the Docker container:
   - Navigate to the file directory and run the following command:
     ```
     docker compose -f docker-compose.yml up -d
     ```
     This command will start your Docker containers in the detach mode.

2. Start your Spring Boot application running on port 9091.

3. Open your browser and visit: [http://localhost:9091/](http://localhost:9091/)

## Stopping the Docker Container

To stop the container, run the following command:
    ```
     docker compose -f docker-compose.yml down
     ```
