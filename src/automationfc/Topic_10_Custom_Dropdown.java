package automationfc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_10_Custom_Dropdown {
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
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		//1 - Click vào 1 thẻ để xổ hết các item bên trong dropdown ra
//		driver.findElement(By.xpath("//span[@id='number-button']")).click();
//		sleepInSeconds(10);
//
//		//2.1 - Nó sẽ xổ ra chứa hết tất cả các item
//		//2.2 - Nó sẽ xổ ra nhưng chỉ chứa 1 phần và đang load thêm
//		//Chờ cho nó xổ ra hết tất cả các item trong dropdown
//
//		//Có case item ko visible hết tất cả (Angulat, React...)
//		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));
//
//		//allItems đang lưu trữ 19 item bên trong
//		List<WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu div"));
//
//		for (WebElement item : allItems) {
//			if (item.getText().equals("15")) {
//				item.click();
//				break;
//			}
//		}

		selectItemInDropdown("span#speed-button", "ul#speed-menu div", "Faster");
		sleepInSeconds(3);
		selectItemInDropdown("span#files-button", "ul#files-menu div", "jQuery.js");
		sleepInSeconds(3);
		selectItemInDropdown("span#number-button", "ul#number-menu div", "15");
		sleepInSeconds(3);
		selectItemInDropdown("span#salutation-button", "ul#salutation-menu div", "Dr.");
		sleepInSeconds(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Faster");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(), "jQuery.js");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "15");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Dr.");
	}

	@Test
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInDropdown("i.dropdown.icon", "div.item>span.text","Jenny Hess" );
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
		sleepInSeconds(3);
		
		selectItemInDropdown("i.dropdown.icon", "div.item>span.text","Elliot Fu" );
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Elliot Fu");
		sleepInSeconds(3);
	}

	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		selectItemInDropdown("div.btn-group", "ul.dropdown-menu a", "Second Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group li ")).getText(), "Second Option");
		sleepInSeconds(3);

		selectItemInDropdown("div.btn-group", "ul.dropdown-menu a", "First Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group li ")).getText(), "First Option");
		sleepInSeconds(3);

		selectItemInDropdown("div.btn-group", "ul.dropdown-menu a", "Third Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group li ")).getText(), "Third Option");
		sleepInSeconds(3);
	}

	@Test
	public void TC_04_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		selectItemEditableDropdown("input.search", "div.item span", "Aland Islands");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Aland Islands");
		sleepInSeconds(3);

		selectItemEditableDropdown("input.search", "div.item span", "Australia");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Australia");
		sleepInSeconds(3);

		selectItemEditableDropdown("input.search", "div.item span", "Bahrain");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Bahrain");
		sleepInSeconds(3);
	}

	@Test
	public void TC_05_NopEcommerce() {
		driver.get("https://demo.nopcommerce.com/register");

		selectItemInDropdown("select[name='DateOfBirthDay']", "select[name='DateOfBirthDay']>option", "18");
		Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']>option[value='18']")).isSelected());
		sleepInSeconds(2);

		selectItemInDropdown("select[name='DateOfBirthMonth']", "select[name='DateOfBirthMonth']>option", "June");
		Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']>option[value='6']")).isSelected());
		sleepInSeconds(2);

		selectItemInDropdown("select[name='DateOfBirthYear']", "select[name='DateOfBirthYear']>option", "2001");
		Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']>option[value='2001']")).isSelected());
		sleepInSeconds(2);
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
