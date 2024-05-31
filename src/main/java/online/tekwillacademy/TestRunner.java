package online.tekwillacademyopencart;

import online.tekwillacademy.managers.DataGeneratorManager;
import online.tekwillacademy.managers.DriverManager;
import online.tekwillacademy.managers.ScrollManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = DriverManager.getInstance().getDriver();


        String currentTabName= driver.getWindowHandle();
        Thread.sleep(5000);
        driver.switchTo().newWindow(WindowType.TAB);


        driver.get("https://tekwillacademy-opencart.online/");
        System.out.println("The current URL is: " + driver.getCurrentUrl());
        System.out.println("The current page title " + driver.getTitle());

        WebElement  userDropDownIcon= driver.findElement(By.xpath("//i[@class='fa-solid fa-user']"));
        userDropDownIcon.click();
        WebElement registerOption = driver.findElement(By.xpath("//a[normalize-space()='Register']"));
        registerOption.click();


        System.out.println("The current URL is: " + driver.getCurrentUrl());
        System.out.println("The current page title " + driver.getTitle());

        WebElement firstNameInput = driver.findElement(By.id("input-firstname"));
        firstNameInput.sendKeys("Sun");
        WebElement lastNameInput = driver.findElement(By.id("input-lastname"));
        lastNameInput.sendKeys("Sunshise");
        WebElement email = driver.findElement(By.id("input-email"));
        String emaiData = DataGeneratorManager.getRandomEmail();
        email.sendKeys(emaiData);
        System.out.println("Email: " + emaiData);
        WebElement inputPassword =driver.findElement(By.id("input-password"));
        String password = DataGeneratorManager.getRandomPassword(10,15);
        inputPassword.sendKeys(password);
        System.out.println("Password: " + password);
        WebElement privacyToggle = driver.findElement(By.cssSelector("input[value='1'][name='agree']"));
        ScrollManager.scrollToElement(privacyToggle);
        privacyToggle.click();

        WebElement continueButton = driver.findElement(By.cssSelector("button[type='submit']"));
        continueButton.click();

        System.out.println("The current URL is: " + driver.getCurrentUrl());
        System.out.println("The current page title " + driver.getTitle());




        driver.close();

        driver.switchTo().window(currentTabName);
        driver.get("https://tekwillacademy-opencart.online/");
        driver.quit();
        System.out.println("The test is finished and the driver is closed");



    }
}