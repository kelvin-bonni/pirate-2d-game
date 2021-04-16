# Pirate 2D Game

## Getting Started

Kindly follow these instructions to set up and run this application on your local machine

### Installation

1. Download and install the latest version of Docker [here](https://docs.docker.com/)

2. Clone the repository using `git clone https://github.com/kelvin-bonni/pirate-2d-game`

3. Navigate into the project folder where the Dockerfile is located 
   
4. Start the docker

5. Execute `docker image build -t pirate-2d-game .` in Command Prompt

6. Execute `docker container run -p 8080:8080 -d pirate-2d-game` in Command Prompt

7. Access the application on `http://localhost:8080`

(The following steps are for stopping the container)

8. Execute `docker container ps` in Command Prompt

9. Choose the first four letters of the ID of the CONTAINER you wish to stop -> $first_four_letters

10. Execute `docker container stop $first_four_letters` in Command Prompt
