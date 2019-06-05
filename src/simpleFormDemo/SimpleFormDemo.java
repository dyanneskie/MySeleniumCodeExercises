package simpleFormDemo;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import excelUtility.Xls_GetData;

public class SimpleFormDemo {
	static WebDriver driver;
	SoftAssert softAssert = new SoftAssert();

	@BeforeMethod
	// @Parameters({ "baseURL", "browser" })
	// String baseURL, String browser

	public void beforeMethod() {

		/*
		 * if (browser.equals("chrome")) { System.setProperty("webdriver.chrome.driver",
		 * "E:\\Workspace\\lib\\chrome\\chromedriver.exe");
		 * 
		 * Map<String, Object> prefs = new HashMap<String, Object>();
		 * prefs.put("profile.default_content_setting_values.notifications", 2);
		 * ChromeOptions options = new ChromeOptions();
		 * options.setExperimentalOption("prefs", prefs);
		 * 
		 * driver = new ChromeDriver(options); } if (browser.equals("firefox")) {
		 * System.setProperty("webdriver.gecko.driver",
		 * "E:\\Workspace\\lib\\gecko\\geckodriver.exe");
		 * 
		 * DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		 * capabilities.setCapability("marionette", true); driver = new
		 * FirefoxDriver(capabilities);
		 * 
		 * }
		 */

		/*
		 * driver.manage().window().maximize(); driver.manage().deleteAllCookies();
		 * driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		 * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 * driver.get(baseURL);
		 */

		System.setProperty("webdriver.chrome.driver", "E:\\Workspace\\lib\\chrome\\chromedriver.exe");

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.seleniumeasy.com/test/");

		WebElement startPractice = driver.findElement(By.id("btn_basic_example"));
		startPractice.click();

		WebElement formDemo = driver
				.findElement(By.xpath("//div[@class=\"list-group\"]//a[contains(text (),\"Simple Form Demo\")]"));
		formDemo.click();
	}

	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testData = Xls_GetData.getDataFromExcel();
		return testData.iterator();

	}

	@Test(dataProvider = "getTestData")
	public void simpleForm(String enterMsg, String enterA, String enterB, String getTotal) {

		WebElement userMsg = driver.findElement(By.xpath("//input[@id=\"user-message\"]"));
		userMsg.sendKeys(enterMsg);

		WebElement showMsg = driver.findElement(By.xpath("//button[@onclick=\"showInput();\"]"));
		showMsg.click();

		// Verify Message Displayed
		WebElement msgDisplay = driver.findElement(By.xpath("//span[@id=\"display\"]"));
		System.out.println("Display msg is " + msgDisplay.getText());

		softAssert.assertEquals(msgDisplay.getText(), enterMsg);

		this.captureScreenShot(driver);

		WebElement sum1 = driver.findElement(By.xpath("//input[@id=\"sum1\"]"));
		sum1.sendKeys(enterA);

		WebElement sum2 = driver.findElement(By.xpath("//input[@id=\"sum2\"]"));
		sum2.sendKeys(enterB);

		WebElement showTotal = driver.findElement(By.xpath("//button[@onclick=\"return total()\"]"));
		showTotal.click();

		// Verify Get Total

		WebElement totalDisplay = driver.findElement(By.xpath("//span[@id=\"displayvalue\"]"));
		System.out.println("Display total is " + totalDisplay.getText());

		float floatValue = Float.parseFloat(getTotal);
		int intValue = (int) floatValue;

		softAssert.assertEquals(totalDisplay.getText(),intValue + "");

		this.captureScreenShot(driver);

		softAssert.assertAll();

	}

	private void captureScreenShot(WebDriver driver) {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot,
					new File("E:\\Workspace\\SeleniumEasyProject\\src\\artefactsSimpleFormDemo\\SimpleForm_"
							+ System.currentTimeMillis() + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
