package automationfc;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_Check_Environment {
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/v4/");
	}
	@Test
	public void TC_01_ValidationCurrentUrl() {
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "https://demo.guru99.com/v4/");
	}
	@Test
	   public void TC_02_ValidatePageTitle() {
	      String loginPageTitle = driver.getTitle();
	      Assert.assertEquals(loginPageTitle, "Guru99 Bank Home Page");
	   }
	   @Test
	   public void TC_03_LoginFormDisplayed() {
	      Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
	   }
	   @AfterClass
	   public void afterClass() {
	      driver.quit();
	   }
}
