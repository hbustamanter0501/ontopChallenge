# Wallet Transaction Service

This is the README file for the wallet transaction service. The service is designed with a Hexagonal Architecture.

### Prerequisites

Gradle must be installed on your machine. You can download Gradle from the official website and follow the installation instructions specific to your operating system.

### DDBB Access

Credentials are in the application.properties file. Is Postgres DB, recommend to use PGAdmin.

### Deployment Steps

1. Clone this repository to your local machine.
   ```
   git clone git@gitlab.com:ontopai/development/card/top-amplitude.git
   ```
2. Open a terminal or command prompt and navigate to the cloned repository directory. 
3. Run the following command to start the required containers:
    ```
    docker-compose up
    ```
    This command will start ZooKeeper, Kafka, and the Top Amplitude application containers.
4. Wait for the containers to start successfully. You can monitor the logs to ensure that all services are up and running.
5. Once the containers are running, you can access the AKHQ tool by opening your browser and navigating to http://localhost:8880. AKHQ provides a user-friendly web interface to manage and view the data in your local Kafka cluster.

### Debugging the Top Amplitude Application

To debug the Top Amplitude application running inside the Docker Compose environment using IntelliJ IDEA, follow these steps:

1. Open IntelliJ IDEA and open the project containing the Top Amplitude code.
2. Configure a remote debugging configuration in IntelliJ IDEA with the following settings:
   ![Debug Configuration](images/top-amplitude-debug-config.png)
3. Start the Docker Compose setup by running docker-compose up in a terminal.
4. Wait for the containers to start, and ensure that the top-amplitude container is running. 
5. Start the remote debugging configuration in IntelliJ IDEA. 
6. IntelliJ IDEA will connect to the Docker container running the Top Amplitude application, allowing you to set breakpoints and debug the code.