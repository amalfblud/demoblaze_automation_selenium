import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddtoCartItem {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.demoblaze.com/index.html");

        try {
            WebElement itemLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Samsung galaxy s6")));
            itemLink.click();

            WebElement addCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add to cart")));
            addCartButton.click();

            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertMessage = alert.getText();

            if (alertMessage.contains("Product added")) {
                System.out.println("Add to cart success");
            } else {
                System.out.println("Add to cart failed");
            }
            alert.accept();

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}