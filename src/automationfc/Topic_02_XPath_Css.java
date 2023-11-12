package automationfc;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_XPath_Css {
    WebDriver driver;
    public static void main (String[] args) {
        System.out.println("hello Truc");
    }
    @Test
    public void TC_01_ValidationCurrentUrl() {
        String loginPageUrl = driver.getCurrentUrl();
        Assert.assertEquals(loginPageUrl, "https://demo.guru99.com/v4/");
    }
}
