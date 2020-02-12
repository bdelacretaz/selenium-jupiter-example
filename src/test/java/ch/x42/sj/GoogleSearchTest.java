package ch.x42.sj;

import static io.github.bonigarcia.seljup.BrowserType.OPERA;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumExtension;

/** Run tests using https://github.com/bonigarcia/selenium-jupiter to
 *  acquire various WebDrivers
 */
@ExtendWith(SeleniumExtension.class)
public class GoogleSearchTest {

  private void assertGoogleSearch(WebDriver driver) {
    driver.get("http://www.google.com");					
    WebElement element = driver.findElement(By.name("q"));	
    element.sendKeys("what's up, doc?");
    element.submit();
    assertTrue(driver.getTitle().contains("what"));
}

  @Test
  @Disabled("To run in an environment with no local browser")
  public void testLocalChrome(ChromeDriver driver) {
    assertGoogleSearch(driver);
  }

  @Test
  @Disabled("To run in an environment with no local browser")
  public void testDockerBrowser(@DockerBrowser(type = OPERA) WebDriver driver) {
    assertGoogleSearch(driver);
  }

  @Test
  public void testHtmlUnit(HtmlUnitDriver driver) {
    assertGoogleSearch(driver);
  }

  @Test
  @Disabled("To run in an environment with no PhantomJS")
  public void testPhantomJS(PhantomJSDriver driver) {
    assertGoogleSearch(driver);
  }
}