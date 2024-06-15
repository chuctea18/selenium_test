package automationfc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_11_Button {
	WebDriver driver;

	//Tường minh: trạng thái cụ thể cho element
	//Visible / Invisible / Presence / Number / Clickable
	WebDriverWait expliciWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();

		expliciWait = new WebDriverWait(driver, 30);

		//Ngầm định: ko rõ ràng cho một trạng thái cụ thể nào của element hết
		//Phục vụ cho việc tìm kiếm element - findElement(s)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_JQuerry() {
		driver.get("https://egov.danang.gov.vn/reg");

		WebElement registerButton = driver.findElement(By.cssSelector("input.egov-button"));

		// Verify button bị disable khi chưa click vào checkbox
		Assert.assertFalse(registerButton.isEnabled());

		driver.findElement(By.cssSelector("input#chinhSach")).click();
		sleepInSeconds(2);
		// Verify button enable khi click vào checkbox
		Assert.assertTrue(registerButton.isEnabled());

		//Lấy mã màu nền của button
		Color registerBackgroundColor  = Color.fromString(registerButton.getCssValue("background-color"));
		System.out.println("Background color = " + registerBackgroundColor);

		Assert.assertEquals(registerBackgroundColor.asHex().toLowerCase(), "#ef5a00");

	}

	@Test
	public void TC_02_Fahasa_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		sleepInSeconds(2);

		WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));
		Assert.assertFalse(loginButton.isEnabled());

		Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(), "#0000000");

		driver.findElement(By.cssSelector("input#login_username")).sendKeys("test@gmail.com");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("12345678");
		sleepInSeconds(2);
		Assert.assertTrue(loginButton.isEnabled());
		Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(), "#c92127");
	}

	public void sleepInSeconds(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	//Những dữ liệu dùng để truyền vào sẽ xem là tham số
	public void selectItemInDropdown(String parentCss, String childItemCss, String itemTextExpected) {
		//1 - Click vào 1 thẻ để xổ hết các item bên trong dropdown ra
		driver.findElement(By.cssSelector(parentCss)).click();
		sleepInSeconds(3);
		//2.1 - Nó sẽ xổ ra chứa hết tất cả các item
		//2.2 - Nó sẽ xổ ra nhưng chỉ chứa 1 phần và đang load thêm
		//Chờ cho nó xổ ra hết tất cả các item trong dropdown
		//Có case item ko visible hết tất cả (Angulat, React...)
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));
		//allItems đang lưu trữ 19 item bên trong
		List<WebElement> allItems = driver.findElements(By.cssSelector(childItemCss));
		for (WebElement item : allItems) {
			if (item.getText().equals(itemTextExpected)) {
				item.click();
				break;
			}
		}
	}

	public void selectItemEditableDropdown(String parentCss, String childItemCss, String itemTextExpected) {
		driver.findElement(By.cssSelector(parentCss)).clear();
		driver.findElement(By.cssSelector(parentCss)).sendKeys(itemTextExpected);
		sleepInSeconds(1);
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));
		List<WebElement> allItems =	expliciWait.until(ExpectedConditions.
				presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));
		for(WebElement item : allItems) {
			if(item.getText().equals(itemTextExpected)) {
				item.click();
				break;
			}
		}

	}
}
