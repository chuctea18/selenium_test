package automationfc;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_WebElement_Commands {
    WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_Displayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        if(driver.findElement(By.xpath("//input[@id='mail']")).isDisplayed()) {
            driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("Automation Testing");
            System.out.println("Email Textbox is displayed");
        }
        else System.out.println("Email Textbox is not displayed");

        if(driver.findElement(By.xpath("//input[@value='under_18']")).isDisplayed()) {
            driver.findElement(By.xpath("//input[@value='under_18']")).click();
            System.out.println("Age under 18 Radio is displayed");
        }
        else System.out.println("Age under 18 Radio is not displayed");

        if(driver.findElement(By.xpath("//textarea[@id='edu']")).isDisplayed()) {
            driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("Automation");
            System.out.println("Education TextArea is displayed");
        }
        else System.out.println("Education TextArea is not displayed");

    }

    @Test
    public void TC_02_Enabled() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        if(driver.findElement(By.xpath("//input[@id='mail']")).isEnabled()) {
            System.out.println("Email Textbox is enabled");
        }
        else System.out.println("Email Textbox is disabled");

        if(driver.findElement(By.xpath("//input[@value='under_18']")).isEnabled()) {
            System.out.println("Age under 18 Radio is enabled");
        }
        else System.out.println("Age under 18 Radio is disabled");

        if(driver.findElement(By.xpath("//textarea[@id='edu']")).isDisplayed()) {
            driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("Automation");
            System.out.println("Education TextArea is displayed");
        }
        else System.out.println("Education TextArea is not displayed");

        if(driver.findElement(By.xpath("//textarea[@id='edu']")).isDisplayed()) {
            driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("Automation");
            System.out.println("Education TextArea is displayed");
        }
        else System.out.println("Education TextArea is not displayed");

    }

    @Test
    public void TC_03_Selected() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//input[@id='under_18']")).click();
        driver.findElement(By.xpath("//input[@id='java']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='under_18']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='java']")).isSelected());

        driver.findElement(By.xpath("//input[@id='java']")).click();
        sleepInSeconds(2);

        Assert.assertFalse(driver.findElement(By.xpath("//input[@id='java']")).isSelected());
    }

    @Test
    public void TC_04_MailChip() {
        driver.get("https://login.mailchimp.com/signup/");

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("minh@gmail.com");

        //Case 1 - Number
        driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("12345");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //Case 2 - LowerCase
        driver.findElement(By.xpath("//input[@id='new_password']")).clear();
        driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("auto");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //Case 3 - UpperCase
        driver.findElement(By.xpath("//input[@id='new_password']")).clear();
        driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("AUTO");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //Case 4 - special characters
        driver.findElement(By.xpath("//input[@id='new_password']")).clear();
        driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("@");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //Case 5 - max length
        driver.findElement(By.xpath("//input[@id='new_password']")).clear();
        driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("1234556789");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //Case 6 - valid input
        driver.findElement(By.xpath("//input[@id='new_password']")).clear();
        driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("Abc@123123");

        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

//    @AfterTest
//    public void tearDown() throws Exception {
//        Thread.sleep(2000);
//        driver.quit();
//    }
}
