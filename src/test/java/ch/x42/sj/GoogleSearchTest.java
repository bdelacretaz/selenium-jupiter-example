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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.DriverCapabilities;
import io.github.bonigarcia.seljup.DriverUrl;
import io.github.bonigarcia.seljup.SeleniumExtension;

/** Run tests using https://github.com/bonigarcia/selenium-jupiter to
 *  acquire various WebDrivers
 */
@ExtendWith(SeleniumExtension.class)
public class GoogleSearchTest {

  @DriverUrl
  // Start selenium-grid-docker-compose.yml to activate this hub
  private final String url = "http://localhost:4444/wd/hub";

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
  public void testRemoteChrome(@DriverCapabilities("browserName=chrome") RemoteWebDriver driver) {
    assertGoogleSearch(driver);
  }

  @Test
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