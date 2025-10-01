package tests;


import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.DriverFactory;


public class LoginTest extends BaseTest {


@Test(description = "Verify user can login and land on Dashboard")
public void userCanLogin() {
LoginPage login = new LoginPage(DriverFactory.getDriver());
DashboardPage dashboard = login
.typeUsername("Admin")
.typePassword("admin123")
.clickLogin();


String header = dashboard.getHeaderText();
Assert.assertTrue(header.contains("Dashboard"),
"Expected to be on Dashboard, but header was: " + header);
}
}
