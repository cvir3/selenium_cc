package testCase;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class TS1_mouseHover {
    public static WebDriver webDriver;

    @BeforeClass(alwaysRun = true)
    public void setup() throws IOException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().window().maximize();
        webDriver.get("https://qaplayground.dev/apps/mouse-hover/");
        System.out.println("Page title is " + webDriver.getTitle());
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }
    @Test
    public void hoverCountryImg() throws IOException, InterruptedException {
        Actions action = new Actions(webDriver);
        WebElement element = webDriver.findElement(By.xpath("//img[@class=\"poster\"]"));
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(element));
        action.moveToElement(element).build().perform();

        //Get Price
        WebElement priceElement = webDriver.findElement(By.xpath("//p[@class=\"current-price\"]"));
        String expectedPrice = priceElement.getText();
        String actualPrice = "$24.96";
        if (actualPrice.equals(expectedPrice)) {
            System.out.println("Test Passed! Current price is: " + actualPrice);
        } else {
            System.out.println("Test Failed! Expected price: " + expectedPrice + ", but found: " + actualPrice);
            //The phrase "but found" is used to indicate a comparison between an expected value and an actual value.
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        webDriver.quit();
    }
}

