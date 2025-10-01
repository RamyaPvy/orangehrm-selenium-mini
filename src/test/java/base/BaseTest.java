package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverFactory;

public class BaseTest {
    protected String baseUrl = "https://opensource-demo.orangehrmlive.com/";

    // ---- Slow Mode Controls ----
    protected static final boolean SLOW_MODE = true;   // set false to go back to normal speed
    protected static final int WAIT_SECONDS = 10;      // how long to pause each time
    protected void pause() {
        if (!SLOW_MODE) return;
        try { Thread.sleep(WAIT_SECONDS * 1000L); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
    // ----------------------------

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(baseUrl);
        pause(); // ✅ wait on the login page so you can see it
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        pause(); // ✅ wait at the end so you can see the final page
        DriverFactory.quitDriver();
    }
}