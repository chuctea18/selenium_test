package automationfc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowse_WebElement_Commands {
    WebDriver driver;
    @BeforeTest
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://live.techpanda.org/");
    }

    @Test
    public void TC_01_VerifyUrl() {
        WebElement myAccount_title = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
        myAccount_title.click();
        String loginPage_url = driver.getCurrentUrl();
        Assert.assertEquals(loginPage_url, "http://live.techpanda.org/index.php/customer/account/login/");
        WebElement createAnAccount_btn = driver.findElement(By.xpath("//form[@id='login-form']//a[@title='Create an Account']"));
        createAnAccount_btn.click();
        String createAnAccount_url = driver.getCurrentUrl();
        Assert.assertEquals(createAnAccount_url, "http://live.techpanda.org/index.php/customer/account/create/");
    }

    @Test
    public void TC_02_VerifyTitle() {
        WebElement myAccount_title = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
        myAccount_title.click();
        String loginPage_title = driver.getTitle();
        Assert.assertEquals(loginPage_title, "Customer Login");
        WebElement createAnAccount_btn = driver.findElement(By.xpath("//form[@id='login-form']//a[@title='Create an Account']"));
        createAnAccount_btn.click();
        String registerPage_title = driver.getTitle();
        Assert.assertEquals(registerPage_title, "Create New Customer Account");
    }

    @Test
    public void TC_03_NavigateFunction() {
        WebElement myAccount_title = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
        myAccount_title.click();
        WebElement createAnAccount_btn = driver.findElement(By.xpath("//form[@id='login-form']//a[@title='Create an Account']"));
        createAnAccount_btn.click();
        String registerPage_url = driver.getCurrentUrl();
        Assert.assertEquals(registerPage_url, "http://live.techpanda.org/index.php/customer/account/create/");
        driver.navigate().back();
        String loginPage_url = driver.getCurrentUrl();
        Assert.assertEquals(loginPage_url, "http://live.techpanda.org/index.php/customer/account/login/");
        driver.navigate().forward();
        String registerPage_title = driver.getTitle();
        Assert.assertEquals(registerPage_title, "Create New Customer Account");
    }

    @AfterTest
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }
}
