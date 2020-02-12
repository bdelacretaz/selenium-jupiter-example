# Demonstrate running PhantomJS-based tests in Java
# after installing the required dependencies
FROM maven:3.6.3-jdk-11-slim
RUN export OPENSSL_CONF=/etc/ssl
RUN apt-get update
RUN apt-get install libfontconfig libssl1.1 libssl-dev -y
RUN mkdir /work
COPY pom.xml /work
COPY src /work
WORKDIR /work

# This Downloads The Web (tm) of course..
RUN mvn dependency:resolve

RUN mvn package