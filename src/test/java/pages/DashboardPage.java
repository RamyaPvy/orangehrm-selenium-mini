package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;


public class DashboardPage {
private final WebDriver driver;
private final WebDriverWait wait;


private final By dashboardHeader = By.cssSelector("h6.oxd-text--h6.oxd-topbar-header-breadcrumb-module");


public DashboardPage(WebDriver driver) {
this.driver = driver;
this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
}


public String getHeaderText() {
return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader)).getText();
}
}
