import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BlankLogin {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.demoblaze.com/index.html");

        try {
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login2")));
            loginButton.click();

            WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginusername")));
            usernameField.sendKeys("");

            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginpassword")));
            passwordField.sendKeys("");

            WebElement confirmLoginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Log in']")));
            confirmLoginButton.click();

            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertMessage = alert.getText();

            if (alertMessage.contains("Please fill out Username and Password.")) {
                System.out.println("Blank login success");
            } else {
                System.out.println("Blank login failed");
            }
            alert.accept();

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}