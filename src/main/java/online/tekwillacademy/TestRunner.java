package online.tekwillacademyopencart;
import online.tekwillacademy.managers.DataGeneratorManager;
import online.tekwillacademy.managers.DriverManager;
import online.tekwillacademy.pageobjects.AccountPage;
import online.tekwillacademy.pageobjects.HomePage;
import online.tekwillacademy.pageobjects.LoginPage;
import online.tekwillacademy.pageobjects.RegisterPage;
import org.openqa.selenium.WebDriver;


public class TestRunner {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = DriverManager.getInstance().getDriver();
        driver.get("https://tekwillacademy-opencart.online/");

        HomePage homePage = new HomePage(driver);
        homePage.navigateToRegisterPage();
        RegisterPage registerPage = new RegisterPage(driver);
        String randomEmail = DataGeneratorManager.getRandomEmail();
        registerPage.clickOnContiuneButton();
        registerPage.completeTheRegisterForm(("Sun"), "Sunshine" , randomEmail,"Mia123!");
        registerPage.enablePrivacyToggle();
        registerPage.clickOnContiuneButton();

        Thread.sleep(2000);



        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickOnLogoutButton();
        homePage.navigateTotheLoginPage();

        LoginPage loginPage=new LoginPage(driver);
        loginPage.completeTheLoginForm(randomEmail,"Mia123!");
        loginPage.clickOnContinueButton();

        Thread.sleep(3000);

        driver.quit();
        System.out.println("The test is finished and the driver is closed");



    }
}