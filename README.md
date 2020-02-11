# selenium-jupiter-example
Minimal example using selenium-jupiter with JUnit5

To test the build in a Docker image the provides no local browsers and no
Docker engine, simulating a true headless environment, use

    docker run -it -v $HOME/.m2/:/root/.m2/ -v $PWD:/work maven:3.6.3-jdk-11-slim bash -c "cd /work ; mvn package"