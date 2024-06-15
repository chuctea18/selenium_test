package automationfc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_06_Login {
    WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_LoginWithEmptyMailAndPassword() {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(3);

        driver.findElement(By.xpath("//button[@id='send2']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).isDisplayed());
    }

    @Test
    public void TC_02_LoginWitInvalidEmail() {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(3);

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("12341234@12312.123123");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@id='send2']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(),
                "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC_03_LoginWithPassword_LessThan_6Chars() {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(3);

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
        driver.findElement(By.xpath("//button[@id='send2']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(),
                "Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_04_LoginWithIncorrectEmailOrPassword() {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(3);

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
        driver.findElement(By.xpath("//button[@id='send2']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='account-login']//li[@class='error-msg']//span")).getText(),
                "Invalid login or password.");

        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='pass']")).clear();

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.net");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("12312323");
        driver.findElement(By.xpath("//button[@id='send2']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='account-login']//li[@class='error-msg']//span")).getText(),
                "Invalid login or password.");
    }

    @Test
    public void TC_05_LoginSuccess() {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);

        //Đăng ký 1 tài khoản trước
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");

        String firstName = "Truc", lastName = "Tran", emailAddress = getEmailAddress(), passWord = "123456789";
        String fullName = firstName + " " + lastName;

        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(passWord);
        driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(passWord);
        driver.findElement(By.xpath("//input[@id='is_subscribed']")).click();
        driver.findElement(By.xpath("//button[@title='Register']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/index/");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(), "Hello," + " " + fullName + "!");

        String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        //Log out
        driver.findElement(By.cssSelector("a.skip-account")).click();
        driver.findElement(By.cssSelector("a[title='Log Out']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/logoutSuccess/");

        //Log in
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(passWord);
        driver.findElement(By.xpath("//button[@id='send2']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(), "Hello," + " " + fullName + "!");
        contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        //Verify Account
        driver.findElement(By.xpath("//a[text()='Account Information']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getAttribute("value"), emailAddress);
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEmailAddress() {
        return "automation" + new Random().nextInt(99999) + "@gmail.com";
    }
}

