package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By usernameInput = By.name("username");
    private final By passwordInput = By.name("password");
    private final By loginButton   = By.cssSelector("button[type='submit']");

    // small helper to pause this page independently
    private void pauseSeconds(int seconds) {
        try { Thread.sleep(seconds * 1000L); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public LoginPage typeUsername(String username) {
        WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        user.clear();
        user.sendKeys(username);
        pauseSeconds(10);   // ✅ watch the username typed
        return this;
    }

    public LoginPage typePassword(String password) {
        WebElement pass = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        pass.clear();
        pass.sendKeys(password);
        pauseSeconds(10);   // ✅ watch the password typed
        return this;
    }

    public DashboardPage clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        pauseSeconds(10);   // ✅ watch the transition after clicking Login
        return new DashboardPage(driver);
    }
}