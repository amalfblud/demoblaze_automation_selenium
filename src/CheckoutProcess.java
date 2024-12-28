import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutProcess {
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

            WebElement cartPageButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("cartur")));
            cartPageButton.click();

            WebElement placeOrderButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Place Order']")));
            placeOrderButton.click();

            WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("name")));
            nameField.sendKeys("Alfi");

            WebElement creditCartField = wait.until(ExpectedConditions.elementToBeClickable(By.id("card")));
            creditCartField.sendKeys("10011");

            WebElement confirmPurchaseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Purchase']")));
            confirmPurchaseButton.click();

            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sweet-alert")));
            WebElement popupText = popup.findElement(By.xpath(".//h2[contains(text(),'Thank you for your purchase!')]"));

            if (popupText != null) {
                System.out.println("Purchase success");
            } else {
                System.out.println("Purchase failed");
            }
            WebElement closeButton = popup.findElement(By.xpath(".//button[text()='OK']"));
            closeButton.click();

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}