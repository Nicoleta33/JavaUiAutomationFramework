package online.tekwillacademy;

import online.tekwillacademy.managers.DataGeneratorManager;
import online.tekwillacademy.managers.DriverManager;
import online.tekwillacademy.pageobjects.AccountPage;
import online.tekwillacademy.pageobjects.HomePage;
import online.tekwillacademy.pageobjects.RegisterPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class TestRegistrationFlowWithJunit {

    static WebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;

    @BeforeAll
    public static  void executeOnceAllBeforeAllTheTeste(){


    }
    @BeforeEach
    public void executeTheCodeBeforeEachTest(){
        driver = DriverManager.getInstance().getDriver();
        driver.get("https://tekwillacademy-opencart.online/");
        homePage = new HomePage(driver);
        homePage.navigateToRegisterPage();
        registerPage = new RegisterPage(driver);
    }

    @Test
    @DisplayName("Succesfusful registration of a user using valid credentials")
    public void registerWithValidData() throws InterruptedException {

        RegisterPage registerPage = new RegisterPage(driver);
        String name = DataGeneratorManager.getRandomName();
        String email = DataGeneratorManager.getRandomEmail();
        String password = DataGeneratorManager.getRandomPassword(10, 20);
        System.out.println("Email: " + email + " / Password: " + password);

        registerPage.completeTheRegisterForm(name, name, email, password);
        registerPage.enablePrivacyToggle();
        registerPage.clickOnContiuneButton();

        Thread.sleep(500);

        AccountPage accountPage=new AccountPage(driver);
        Assertions.assertTrue(accountPage.isLogoutButtonDisplayed(), "The logOut Button is displayed");



    }

    @Test
    @DisplayName("Unable to register a user by using invalid password")
    public void registerWithInvalidData() throws InterruptedException {

        registerPage = new RegisterPage(driver);
        String name = DataGeneratorManager.getRandomName();
        String email = DataGeneratorManager.getRandomEmail();
        String password = DataGeneratorManager.getRandomPassword(1, 2);
        System.out.println("Email: " + email + " / Password: " + password);

        registerPage.completeTheRegisterForm(name, name, email, password);
        registerPage.enablePrivacyToggle();
        registerPage.clickOnContiuneButton();
        Thread.sleep(5000);

        boolean urlContainsRegisterKeyword=driver.getCurrentUrl().contains("register");
        Assertions.assertTrue(urlContainsRegisterKeyword,"The URL contains REGISTER keyword ");

    }

    @AfterEach
    public void executeScriptAfterEachTest() {
     DriverManager.getInstance().quiteDriver();
    }

    @AfterAll
    public static void executeTheScriptAfterAllTheSuiteTests(){
        System.out.println("The test suite has been executed");
    }
}