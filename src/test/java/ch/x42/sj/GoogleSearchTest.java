package ch.x42.sj;

import static io.github.bonigarcia.seljup.BrowserType.OPERA;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.DriverCapabilities;
import io.github.bonigarcia.seljup.DriverUrl;
import io.github.bonigarcia.seljup.SeleniumExtension;

/** Run tests using https://github.com/bonigarcia/selenium-jupiter to
 *  acquire various WebDrivers
 */
@ExtendWith(SeleniumExtension.class)
public class GoogleSearchTest {

  // Testing with SauceLabs
  @DriverUrl
  static final String url = "https://ondemand.eu-central-1.saucelabs.com/wd/hub";

  @DriverCapabilities
  DesiredCapabilities capabilities = new DesiredCapabilities();
  {
      capabilities.setCapability("username", System.getProperty("SAUCE_USER"));
      capabilities.setCapability("accessKey", System.getProperty("SAUCE_ACCESS_KEY"));
      capabilities.setCapability("browserName", "Chrome");
      capabilities.setCapability("platform", "Windows 10");
      capabilities.setCapability("version", "59.0");
      capabilities.setCapability("name", "selenium-jupiter-example");
  }

  private void assertGoogleSearch(WebDriver driver) {
    final long timeoutSeconds = 10;
    final WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
    try {
      driver.get("https://google.com/ncr");
      driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER);
      WebElement firstResult = wait.until(presenceOfElementLocated(By.cssSelector("h3>div")));
      System.out.println(firstResult.getAttribute("textContent"));
    } finally {
      driver.quit();
    }
  }

  @Test
  void testWithSaucelabs(RemoteWebDriver driver) {
      assertGoogleSearch(driver);
  }

  @Test
  @Disabled("Kept as an example that runs with local Selenium Grid")
  public void testRemoteChrome(@DriverCapabilities("browserName=chrome") RemoteWebDriver driver) {
    assertGoogleSearch(driver);
  }

  @Test
  @Disabled("Kept as an example that runs with local Selenium Grid")
  public void testRemoteFirefox(@DriverCapabilities("browserName=firefox") RemoteWebDriver driver) {
    assertGoogleSearch(driver);
  }

  @Test
  @Disabled("Kept as an example")
  public void testLocalChrome(ChromeDriver driver) {
    assertGoogleSearch(driver);
  }

  @Test
  @Disabled("Kept as an example")
  public void testDockerBrowser(@DockerBrowser(type = OPERA) WebDriver driver) {
    assertGoogleSearch(driver);
  }

  @Test
  @Disabled("HtmlUnit is quite limited")
  public void testHtmlUnit(HtmlUnitDriver driver) {
    assertGoogleSearch(driver);
  }

  @Test
  @Disabled("Testing Selenium Grid for now")
  public void testPhantomJS(PhantomJSDriver driver) {
    // To test this in a minimal environment, use:
    // docker build -t selenium-jupiter-example .
    // docker run -v $HOME/.m2/:/root/.m2/ selenium-jupiter-example
    assertGoogleSearch(driver);
  }
}