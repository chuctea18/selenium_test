package automationfc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_XPath_Css {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }
    @Test
    public void TC_00_ValidationCurrentUrl() {
        String loginPageUrl = driver.getCurrentUrl();
        Assert.assertEquals(loginPageUrl, "https://demo.guru99.com/v4/");
    }
    @Test
    public void TC_01_RegiterWithEmptyData() {
        /*
        WebElement regBtn = driver.findElement(By.xpath("//form[@id='frmLogin']//button[(text()='ĐĂNG KÝ')]"));
        regBtn.click();
        WebElement txtFirstname_error = driver.findElement((By.xpath("//label[@id='txtFirstname-error']")));
        Assert.assertEquals(txtFirstname_error.getText(), "Vui lòng nhập họ tên");
        WebElement txtEmail_error = driver.findElement((By.xpath(("//label[@id='txtEmail-error']"))));
        Assert.assertEquals(txtEmail_error.getText(), "Vui lòng nhập email");
        WebElement txtCEmail_error = driver.findElement((By.xpath(("//label[@id='txtCEmail-error']"))));
        Assert.assertEquals(txtCEmail_error.getText(), "Vui lòng nhập lại địa chỉ email");
        WebElement txtPassword_error = driver.findElement((By.xpath(("//label[@id='txtPassword-error']"))));
        Assert.assertEquals(txtPassword_error.getText(), "Vui lòng nhập mật khẩu");
        WebElement txtCPassword_error = driver.findElement((By.xpath(("//label[@id='txtCPassword-error']"))));
        Assert.assertEquals(txtCPassword_error.getText(), "Vui lòng nhập lại mật khẩu");
        WebElement txtPhone_error = driver.findElement((By.xpath("//label[@id='txtPhone-error']")));
        Assert.assertEquals(txtPhone_error.getText(),"Vui lòng nhập số điện thoại.");
        */

        //Action
        driver.findElement(By.id("txtFirstname")).clear();
        driver.findElement(By.id("txtEmail")).clear();
        driver.findElement(By.id("txtCEmail")).clear();
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtCPassword")).clear();
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.xpath("//form[@id='frmLogin']//button[(text()='ĐĂNG KÝ')]")).click();

        //Verify
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
    }

    @Test
    public void TC_02_RegisterWithInvalidEmail() {
        /*
        WebElement txtFirstnameInput = driver.findElement(By.xpath(("//input[@id='txtFirstname']")));
        txtFirstnameInput.sendKeys("Tran Thanh Truc");
        WebElement txtEmailInput = driver.findElement((By.xpath("//input[@id='txtEmail']")));
        txtEmailInput.sendKeys("123@gmail@com");
        WebElement txtCEmailInput = driver.findElement((By.xpath("//input[@id='txtCEmail']")));
        txtCEmailInput.sendKeys("123@gmail@com");
        WebElement txtPasswordInput = driver.findElement((By.xpath("//input[@id='txtPassword']")));
        txtPasswordInput.sendKeys("123123");
        WebElement txtCPasswordInput = driver.findElement((By.xpath("//input[@id='txtCPassword']")));
        txtCPasswordInput.sendKeys("123123");
        WebElement txtPhoneInput = driver.findElement((By.xpath("//input[@id='txtPhone']")));
        txtPhoneInput.sendKeys("0342210903");
        WebElement regBtn = driver.findElement(By.xpath("//form[@id='frmLogin']//button[(text()='ĐĂNG KÝ')]"));
        regBtn.click();
        WebElement txtEmail_error = driver.findElement((By.xpath(("//label[@id='txtEmail-error']"))));
        Assert.assertEquals(txtEmail_error.getText(), "Vui lòng nhập email hợp lệ");
        WebElement txtCEmail_error = driver.findElement((By.xpath(("//label[@id='txtCEmail-error']"))));
        Assert.assertEquals(txtCEmail_error.getText(), "Email nhập lại không đúng");
         */

        //Action
        driver.findElement(By.id("txtFirstname")).sendKeys("Tran Thanh Truc");
        driver.findElement(By.id("txtEmail")).sendKeys("123@gmail@com");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@gmail@com");
        driver.findElement(By.id("txtPassword")).sendKeys("123123");
        driver.findElement(By.id("txtCPassword")).sendKeys("123123");
        driver.findElement(By.id("txtPhone")).sendKeys("0392210903");
        driver.findElement(By.xpath("//form[@id='frmLogin']//button[(text()='ĐĂNG KÝ')]")).click();

        //Verify
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
    }

    @Test
    public void TC_03_RegisterWithIncorrectConfirmEmail() {
        /*
        WebElement txtFirstnameInput = driver.findElement(By.xpath(("//input[@id='txtFirstname']")));
        txtFirstnameInput.sendKeys("Tran Thanh Truc");
        WebElement txtEmailInput = driver.findElement((By.xpath("//input[@id='txtEmail']")));
        txtEmailInput.sendKeys("123@gmail.com");
        WebElement txtCEmailInput = driver.findElement((By.xpath("//input[@id='txtCEmail']")));
        txtCEmailInput.sendKeys("123@gmail@com");
        WebElement txtPasswordInput = driver.findElement((By.xpath("//input[@id='txtPassword']")));
        txtPasswordInput.sendKeys("123123");
        WebElement txtCPasswordInput = driver.findElement((By.xpath("//input[@id='txtCPassword']")));
        txtCPasswordInput.sendKeys("123123");
        WebElement txtPhoneInput = driver.findElement((By.xpath("//input[@id='txtPhone']")));
        txtPhoneInput.sendKeys("0342210903");
        WebElement regBtn = driver.findElement(By.xpath("//form[@id='frmLogin']//button[(text()='ĐĂNG KÝ')]"));
        regBtn.click();
        WebElement txtCEmail_error = driver.findElement((By.xpath(("//label[@id='txtCEmail-error']"))));
        Assert.assertEquals(txtCEmail_error.getText(), "Email nhập lại không đúng");
        */

        //Action
        driver.findElement(By.id("txtFirstname")).sendKeys("Tran Thanh Truc");
        driver.findElement(By.id("txtEmail")).sendKeys("123@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@gmail@com");
        driver.findElement(By.id("txtPassword")).sendKeys("123123");
        driver.findElement(By.id("txtCPassword")).sendKeys("123123");
        driver.findElement(By.id("txtPhone")).sendKeys("0392210903");
        driver.findElement(By.xpath("//form[@id='frmLogin']//button[(text()='ĐĂNG KÝ')]")).click();

        //Verify
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
    }

    @Test
    public void TC_04_RegisterWithPassWord_LesserThan6Chacracters() {
        /*
        WebElement txtFirstnameInput = driver.findElement(By.xpath(("//input[@id='txtFirstname']")));
        txtFirstnameInput.sendKeys("Tran Thanh Truc");
        WebElement txtEmailInput = driver.findElement((By.xpath("//input[@id='txtEmail']")));
        txtEmailInput.sendKeys("123@gmail.com");
        WebElement txtCEmailInput = driver.findElement((By.xpath("//input[@id='txtCEmail']")));
        txtCEmailInput.sendKeys("123@gmail.com");
        WebElement txtPasswordInput = driver.findElement((By.xpath("//input[@id='txtPassword']")));
        txtPasswordInput.sendKeys("12345");
        WebElement txtCPasswordInput = driver.findElement((By.xpath("//input[@id='txtCPassword']")));
        txtCPasswordInput.sendKeys("12345");
        WebElement txtPhoneInput = driver.findElement((By.xpath("//input[@id='txtPhone']")));
        txtPhoneInput.sendKeys("0342210903");
        WebElement regBtn = driver.findElement(By.xpath("//form[@id='frmLogin']//button[(text()='ĐĂNG KÝ')]"));
        regBtn.click();
        WebElement txtPassword_error = driver.findElement(By.xpath("//label[@id='txtPassword-error']"));
        Assert.assertEquals(txtPassword_error.getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        WebElement txtCPassword_error = driver.findElement(By.xpath("//label[@id='txtCPassword-error']"));
        Assert.assertEquals(txtCPassword_error.getText(), "Mật khẩu phải có ít nhất 6 ký tự");
         */

        //Action
        driver.findElement(By.id("txtFirstname")).sendKeys("Tran Thanh Truc");
        driver.findElement(By.id("txtEmail")).sendKeys("123@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123");
        driver.findElement(By.id("txtCPassword")).sendKeys("123");
        driver.findElement(By.id("txtPhone")).sendKeys("0392210903");
        driver.findElement(By.xpath("//form[@id='frmLogin']//button[(text()='ĐĂNG KÝ')]")).click();

        //Verify
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");

    }

    @Test
    public void TC_05_RegisterWithCorrectPassword() {
        /*
        WebElement txtFirstnameInput = driver.findElement(By.xpath(("//input[@id='txtFirstname']")));
        txtFirstnameInput.sendKeys("Tran Thanh Truc");
        WebElement txtEmailInput = driver.findElement((By.xpath("//input[@id='txtEmail']")));
        txtEmailInput.sendKeys("123@gmail.com");
        WebElement txtCEmailInput = driver.findElement((By.xpath("//input[@id='txtCEmail']")));
        txtCEmailInput.sendKeys("123@gmail.com");
        WebElement txtPasswordInput = driver.findElement((By.xpath("//input[@id='txtPassword']")));
        txtPasswordInput.sendKeys("123456");
        WebElement txtCPasswordInput = driver.findElement((By.xpath("//input[@id='txtCPassword']")));
        txtCPasswordInput.sendKeys("1234567");
        WebElement txtPhoneInput = driver.findElement((By.xpath("//input[@id='txtPhone']")));
        txtPhoneInput.sendKeys("0342210903");
        WebElement regBtn = driver.findElement(By.xpath("//form[@id='frmLogin']//button[(text()='ĐĂNG KÝ')]"));
        regBtn.click();
        WebElement txtCPassword_error = driver.findElement(By.xpath("//label[@id='txtCPassword-error']"));
        Assert.assertEquals(txtCPassword_error.getText(), "Mật khẩu bạn nhập không khớp");
         */
        //Action
        driver.findElement(By.id("txtFirstname")).sendKeys("Tran Thanh Truc");
        driver.findElement(By.id("txtEmail")).sendKeys("123@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123123");
        driver.findElement(By.id("txtPhone")).sendKeys("0392210903");
        driver.findElement(By.xpath("//form[@id='frmLogin']//button[(text()='ĐĂNG KÝ')]")).click();

        //Verify
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void TC_06_RegisterWithInvalidPhoneNumber() {
        /*
        WebElement txtFirstnameInput = driver.findElement(By.xpath(("//input[@id='txtFirstname']")));
        txtFirstnameInput.sendKeys("Tran Thanh Truc");
        WebElement txtEmailInput = driver.findElement((By.xpath("//input[@id='txtEmail']")));
        txtEmailInput.sendKeys("123@gmail.com");
        WebElement txtCEmailInput = driver.findElement((By.xpath("//input[@id='txtCEmail']")));
        txtCEmailInput.sendKeys("123@gmail.com");
        WebElement txtPasswordInput = driver.findElement((By.xpath("//input[@id='txtPassword']")));
        txtPasswordInput.sendKeys("123456");
        WebElement txtCPasswordInput = driver.findElement((By.xpath("//input[@id='txtCPassword']")));
        txtCPasswordInput.sendKeys("123456");
        WebElement txtPhoneInput = driver.findElement((By.xpath("//input[@id='txtPhone']")));
        txtPhoneInput.sendKeys("034221");
        WebElement regBtn = driver.findElement(By.xpath("//form[@id='frmLogin']//button[(text()='ĐĂNG KÝ')]"));
        regBtn.click();
        WebElement txtPhone_error = driver.findElement(By.xpath("//label[@id='txtPhone-error']"));
        Assert.assertEquals(txtPhone_error.getText(), "Số điện thoại phải từ 10-11 số.");
        txtPhoneInput.clear();
        txtPhoneInput.sendKeys("123456");
        regBtn.click();
        Assert.assertEquals(txtPhone_error.getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
         */

        //Phone less than 10 chars
        driver.findElement(By.id("txtFirstname")).sendKeys("Tran Thanh Truc");
        driver.findElement(By.id("txtEmail")).sendKeys("123@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("039221090");
        driver.findElement(By.xpath("//form[@id='frmLogin']//button[(text()='ĐĂNG KÝ')]")).click();

        //Verify
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

        //Phone more than 11 chars
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("039221090312");

        //Verify
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

        //Phone <> phone center number
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("1234567891");

        //Verify
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }

    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }
}
