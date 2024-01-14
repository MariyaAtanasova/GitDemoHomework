import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddProductTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://shop.pragmatic.bg/index.php?route=product/product&product_id=43");
    }

    @Test
    public void ProductTest() {
        WebElement addToCartButton = driver.findElement(By.id("button-cart"));
        addToCartButton.click();
        WebElement buttonItems = driver.findElement(By.id("cart-total"));
        buttonItems.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#cart a:nth-of-type(2)")));
        WebElement checkout = driver.findElement(By.cssSelector("#cart a:nth-of-type(2)"));
        checkout.click();
        WebElement checkoutId = driver.findElement(By.id("content"));
        Assert.assertTrue(checkoutId.getText().contains("Checkout"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
