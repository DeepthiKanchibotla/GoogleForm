package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */
    @Test
    public void TestCase01() throws InterruptedException {

        // Navigate to this google form.

        driver.get(
                "https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
        Thread.sleep(2000);

        // Fill in Crio Learner in the 1st text box
        // div[text()='Your answer']
        WebElement name = driver.findElement(By.xpath("//input[@type='text']"));
        Wrappers.sendkeys(name, "Crio Learner");
        System.out.println(name.getText());
        Thread.sleep(3000);

        // Write down "I want to be the best QA Engineer! 1710572021'' where 1710572021
        // is variable - needs to be the current epoch time.
        long epoch = System.currentTimeMillis() / 1000;
        String epcohTime = String.valueOf(epoch);
        String text = "I want to be the best QA Engineer! ";

        WebElement practiceElement = driver.findElement(By.xpath("//textarea[@aria-label='Your answer']"));
        Wrappers.sendkeys(practiceElement, text + " " + epcohTime);
        // Enter your Automation Testing experience in the next radio button

        Wrappers.radiobutton(driver, "0 - 2");

        Thread.sleep(2000);

        // Select Java, Selenium and TestNG from the next check-box

        Wrappers.checkbox(driver, "Java");
        Wrappers.checkbox(driver, "Selenium");
        Wrappers.checkbox(driver, "TestNG");

        Thread.sleep(2000);

        // address dropdown

        WebElement choosedropdown = driver.findElement(By.xpath("//div[@jsname='LgbsSe']"));
        choosedropdown.click();
        Thread.sleep(2000);

        Wrappers.addressdropdown(driver, "Mrs");

        // Provided the current date minus 7 days in the next date field, it should be
        // dynamically calculated and not hardcoded.

        WebElement dateinputbox = driver.findElement(By.xpath("//input[@type='date']"));
        String sevenDaysAgoDate = Wrappers.sevenDaysAgoDate();
        Wrappers.sendkeys(dateinputbox, sevenDaysAgoDate);

        // Provide the time 07:30 in the next field (Can also be in 24 hour clock)
        WebElement hoursInput = driver.findElement(By.xpath("//input[@aria-label='Hour']"));
        WebElement minuteInput = driver.findElement(By.xpath("//input[@aria-label='Minute']"));
        String currentTime = Wrappers.currentTime();
        String[] currentTimeinHHMM = currentTime.split(":");
        String HH = currentTimeinHHMM[0];
        String MM = currentTimeinHHMM[1];

        Wrappers.sendkeys(hoursInput, HH);
        Wrappers.sendkeys(minuteInput, MM);

        Thread.sleep(2000);

        // // Submit the form
        WebElement submitbutton = driver.findElement(By.xpath("//span[text()='Submit']"));
        submitbutton.click();

        WebElement successtext = driver
                .findElement(By.xpath("//div[text()='Thanks for your response, Automation Wizard!']"));

        if (successtext.isDisplayed() && successtext.equals("Thanks for your response, Automation Wizard!")) {
            System.out.println("Successfully submitted the form");
        }

    }

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();

    }
}