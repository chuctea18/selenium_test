package automationfc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_09_Default_Dropdown {
	WebDriver driver;
	String firstName = "Truc", lastName = "Tran", email = getEmailRandom();
	String company = "Selenium WebDriver", passWord = "12345678";

	String day = "15", month = "January", year = "2000";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/register");
	}

	@Test
	public void TC_01_Register() {
		driver.findElement(By.cssSelector("a.ico-register")).click();
		sleepInSeconds(2);
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);

		//Day dropdown
		Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));

		//Chọn ngày
		day.selectByVisibleText(this.day);

		//Verify dropdown này là Single (ko phải Multiple)
		Assert.assertFalse(day.isMultiple());

		//Verify dropdown có 32 item
		List<WebElement> dayOptions = day.getOptions();
		Assert.assertEquals(dayOptions.size(), 32);

		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);

		driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
		driver.findElement(By.cssSelector("input#Company")).sendKeys(company);
		driver.findElement(By.cssSelector("input#Password")).sendKeys(passWord);
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(passWord);

		driver.findElement(By.cssSelector("button#register-button")).click();
		sleepInSeconds(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
	}

	@Test
	public void TC_02_Login() {
		driver.get("https://demo.nopcommerce.com/register");

		driver.findElement(By.cssSelector("a.ico-login")).click();
		sleepInSeconds(2);

		//Login
		driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
		driver.findElement(By.cssSelector("input#Password")).sendKeys(passWord);
		driver.findElement(By.cssSelector("button.login-button")).click();
		sleepInSeconds(2);

		//Verify
		driver.findElement(By.cssSelector("a.ico-account")).click();
		sleepInSeconds(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), lastName);

		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);

		Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), email);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"), company);

	}

	public void sleepInSeconds(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public String getEmailRandom() {
		return "automation" + new Random().nextInt(9999) + "@gmail.com";
	}
}
