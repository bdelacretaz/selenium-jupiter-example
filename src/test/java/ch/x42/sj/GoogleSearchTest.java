package ch.x42.sj;

import static io.github.bonigarcia.seljup.BrowserType.OPERA;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumExtension;

/** Run tests using https://github.com/bonigarcia/selenium-jupiter to
 *  acquire various WebDrivers
 */
@ExtendWith(SeleniumExtension.class)
public class GoogleSearchTest {

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
    public void testLocalFirefox(FirefoxDriver driver) {
        assertGoogleSearch(driver);
	}

	@Test
    public void testLocalChrome(ChromeDriver driver) {
        assertGoogleSearch(driver);
	}

	@Test
    public void testDockerBrowser(@DockerBrowser(type = OPERA) WebDriver driver) {
		assertGoogleSearch(driver);
	}
}