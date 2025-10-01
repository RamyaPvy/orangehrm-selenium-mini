package utils;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class DriverFactory {
private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();


public static void initDriver() {
if (DRIVER.get() == null) {
WebDriverManager.chromedriver().setup();
ChromeOptions options = new ChromeOptions();
options.addArguments("--start-maximized");
// options.addArguments("--headless=new"); // uncomment for headless CI
DRIVER.set(new ChromeDriver(options));
}
}


public static WebDriver getDriver() {
return DRIVER.get();
}


public static void quitDriver() {
if (DRIVER.get() != null) {
DRIVER.get().quit();
DRIVER.remove();
}
}
}