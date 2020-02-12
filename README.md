# selenium-jupiter-example
Minimal example using [selenium-jupiter](https://github.com/bonigarcia/selenium-jupiter) with JUnit5.

The Dockerfile demonstrates running PhantomJS-based tests from JUnit on a 
relatively minimal environment.

To test this, use:

    docker build -t selenium-jupiter-example .
    docker run -v $HOME/.m2/:/root/.m2/ selenium-jupiter-example

For now several tests are `@Disabled` due to the limitations of this `maven` Docker image.
