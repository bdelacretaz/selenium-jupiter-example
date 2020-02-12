# Demonstrate running PhantomJS-based tests in Java
# after installing the required dependencies
FROM maven:3.6.3-jdk-11-slim
RUN apt-get update
RUN apt-get install -y libfontconfig libssl1.1 libssl-dev
RUN apt-get clean
RUN mkdir /work
COPY pom.xml /work
COPY src /work/src
WORKDIR /work

# To avoid Downloading The Web, best is to 
# run this container with 
#   -v $HOME/.m2/:/root/.m2
# to mount your local Maven repository, assuming
# it contains what's needed
CMD export OPENSSL_CONF=/etc/ssl ; mvn clean test