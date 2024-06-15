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

public class Topic_12_Checkbox_Radio {
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
	public void TC_01_Default_Telerik_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

		By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");
		By rearSideCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::span/input");

		//Click vào checkbox
		checkToElement(dualZoneCheckbox);
		checkToElement(rearSideCheckbox);

		//Verify checkbox đã được chọn thành công
		Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(rearSideCheckbox).isSelected());

		//Bỏ chọn 2 checkbox
		uncheckToElement(dualZoneCheckbox);
		uncheckToElement(rearSideCheckbox);

		//Verify checkbox đã bỏ chọn thành công
		Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(rearSideCheckbox).isSelected());
	}

	@Test
	public void TC_02_Default_Telerik_Radio() {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

		By twoPetroRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");
		By twoDieselRadio = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::span/input");

		//Click chọn 1 trong 2
		checkToElement(twoPetroRadio);

		//Verify
		Assert.assertTrue(driver.findElement(twoPetroRadio).isSelected());
		Assert.assertFalse(driver.findElement(twoDieselRadio).isSelected());

		checkToElement(twoDieselRadio);

		//Verify
		Assert.assertFalse(driver.findElement(twoPetroRadio).isSelected());
		Assert.assertTrue(driver.findElement(twoDieselRadio).isSelected());
	}

	@Test
	public void TC_04_Select_All_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");

		List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));

		//Chọn hết tất cả checkbox có trong hình đó
		for (WebElement checkbox : allCheckboxes) {
			if (!checkbox.isSelected()) {
				checkbox.click();
				sleepInSeconds(1);
			}
		}

		//Verify hết tất cả checkbox
		for (WebElement checkbox : allCheckboxes) {
			Assert.assertTrue(checkbox.isSelected());
		}

		driver.manage().deleteAllCookies();
		driver.navigate().refresh();

		//Chọn 1 checkbox / radio nào đó trong tất cả các checkbox / radio
		for (WebElement checkbox : allCheckboxes) {
			if(checkbox.getAttribute("value").equals("Gout") && !checkbox.isSelected()) {
				checkbox.click();
				sleepInSeconds(1);
			}
		}

		//Verify hết tất cả checkbox
		for (WebElement checkbox : allCheckboxes) {
			if (checkbox.getAttribute("value").equals("Gout")) {
				Assert.assertTrue(checkbox.isSelected());
			} else Assert.assertFalse(checkbox.isSelected());
		}
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

	public void checkToElement(By byXpath) {
		if(!driver.findElement(byXpath).isSelected()) {
			driver.findElement(byXpath).click();
			sleepInSeconds(2);
		}
	}

	public void uncheckToElement(By byXpath) {
		if(driver.findElement(byXpath).isSelected()) {
			driver.findElement(byXpath).click();
			sleepInSeconds(2);
		}
	}
}
