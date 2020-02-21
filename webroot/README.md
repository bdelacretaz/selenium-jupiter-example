This webroot folder is meant to validate the tunneling of Selenium Grid requests to URLs that are only accessible from the local network.

For this you would:

 * Start a Web server on port 8000, that serves the content of the `webroot` folder. Like `python3 -m SimpleHTTPServer` or any suitable server.
 * Verify that http://localhost:8000/ shows a page that contains "This is dynamic content, at..." followed by the current date.
 * Run the tests with `mvn clean test`. If you're using a SauceLabs Selenium Grid, you'll need to set the parameters for that, like `mvn clean test -DSAUCE_URL=<URL> -DSAUCE_USER=<user> -DSAUCE_ACCESS_KEY=<key>`

 But the current tests don't use this.