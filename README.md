# selenium-jupiter-example
Minimal example using [selenium-jupiter](https://github.com/bonigarcia/selenium-jupiter) with JUnit5.

This module demonstrates running WebDriver tests from JUnit using a Selenium Grid.

To test this, start the Grid with:

    docker-compose -f selenium-grid-docker-compose.yml up

And run the tests with `mvn clean test`.