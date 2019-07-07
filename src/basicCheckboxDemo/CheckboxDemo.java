package basicCheckboxDemo;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CheckboxDemo {
	WebDriver driver;
	WebElement ageRadioBtn;
	WebElement option1RadioBtn;
	WebElement option2RadioBtn;
	WebElement option3RadioBtn;
	WebElement option4RadioBtn;
	WebElement unCheckBtn;
	WebElement CheckBtn;

	SoftAssert softAssert = new SoftAssert();

	@BeforeMethod
	@Parameters({ "browser", "checkboxURL" })
	public void beforeMethod(String browser, String checkboxURL) {
		this.initialisation(browser, checkboxURL);
	}

	@Test(groups = "Single.CheckBox")
	public void singleCheckBox() {
		this.SingleRadioBtnUtil();
	}

	@Test(groups = "Multiple.CheckBox")
	public void multipleCheckBoxCheckedAll() {
		this.Option1Btn();
		this.captureScreenShot(driver);

		this.Option2Btn();
		this.captureScreenShot(driver);

		this.Option3Btn();
		this.captureScreenShot(driver);

		this.Option4Btn();
		this.captureScreenShot(driver);

		this.UncheckALLBtn();

	}

	@Test(groups = "Multiple.CheckBox")
	public void multipleCheckBoxChecked3() {
		this.Option1Btn();
		this.captureScreenShot(driver);

		this.Option3Btn();
		this.captureScreenShot(driver);

		this.Option4Btn();
		this.captureScreenShot(driver);

		this.CheckALLBtn();
	}

	@Test(groups = "Multiple.CheckBox")
	public void multipleCheckBoxChecked2() {
		this.Option1Btn();
		this.captureScreenShot(driver);

		this.Option4Btn();
		this.captureScreenShot(driver);

		this.CheckALLBtn();
	}

	@Test(groups = "Multiple.CheckBox")
	public void multipleCheckBoxCheckedALLBtnClick() {
		this.CheckALLBtnClick();
		this.captureScreenShot(driver);

		this.UncheckALLBtn();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	private void initialisation(String browser, String checkboxURL) {
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "E:\\Workspace\\lib\\chrome\\chromedriver.exe");

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);

			driver = new ChromeDriver(options);

		}
		if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "E:\\Workspace\\lib\\gecko\\geckodriver.exe");
			driver = new FirefoxDriver();

			/*
			 * DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			 * capabilities.setCapability("marionette", true); driver = new
			 * FirefoxDriver(capabilities);
			 */

		}
		// Get URL
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(checkboxURL);

	}

	private void captureScreenShot(WebDriver driver) {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot,
					new File("E:\\Workspace\\SeleniumEasyProject\\src\\artefactsSimpleFormDemo\\CheckboxDemo_"
							+ System.currentTimeMillis() + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

////Checkbox Method
	// Single Checkbox Demo
	private void SingleRadioBtnUtil() {
		ageRadioBtn = driver.findElement(By.xpath("//input[@id=\"isAgeSelected\"]"));

		boolean ageRadioBtnIsDisplayed = ageRadioBtn.isDisplayed();
		System.out.println("Is Age radio button displayed: " + ageRadioBtnIsDisplayed);

		boolean ageRadioBtnIsEnabled = ageRadioBtn.isEnabled();
		System.out.println("Is Age radio button enabled: " + ageRadioBtnIsEnabled);

		boolean ageRadioBtnIsSelected = ageRadioBtn.isSelected();
		System.out.println("Is Age radio button selected: " + ageRadioBtnIsSelected);

		// softAssert.assertEquals(ageRadioBtnIsSelected, false);
		this.captureScreenShot(driver);

		ageRadioBtn.click();

		System.out.println("Is Age radio button selected: " + ageRadioBtn.isSelected());
		this.captureScreenShot(driver);

		// Verify Msg Display
		WebElement msgDisplay = driver.findElement(By.xpath("//div[@id=\"txtAge\"]"));
		System.out.println("Display msg is " + msgDisplay.getText());

		softAssert.assertEquals(msgDisplay.getText(), "Success - Check box is checked");
		softAssert.assertAll();
	}

	// Multiple Checkbox Demo
	public void Option1Btn() {
		// Option 1
		option1RadioBtn = driver.findElement(By.xpath("//label[text()='Option 1']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option1RadioBtn);
		this.captureScreenShot(driver);

		option1RadioBtn.click();
	}

	public void Option2Btn() {
		// Option 2
		option2RadioBtn = driver.findElement(By.xpath("//label[text()='Option 2']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option2RadioBtn);
		option2RadioBtn.click();
	}

	public void Option3Btn() {
		// Option 3
		option3RadioBtn = driver.findElement(By.xpath("//label[text()='Option 3']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option3RadioBtn);
		option3RadioBtn.click();
	}

	public void Option4Btn() {
		// Option 4
		option4RadioBtn = driver.findElement(By.xpath("//label[text()='Option 4']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option4RadioBtn);
		option4RadioBtn.click();
	}

	public void UncheckALLBtn() {
		// Verify Uncheck All Button WebElement uncheckAllBtn =
		unCheckBtn = driver.findElement(By.xpath("//input[@value=\"Uncheck All\"]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", unCheckBtn);

		softAssert.assertEquals(unCheckBtn.getAttribute("value"), "Uncheck All");
		System.out.println(unCheckBtn.getAttribute("value"));
		softAssert.assertAll();

	}

	public void CheckALLBtn() {
		// Verify Uncheck All Button WebElement uncheckAllBtn =
		CheckBtn = driver.findElement(By.xpath("//input[@value=\"Check All\"]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", CheckBtn);

		softAssert.assertEquals(CheckBtn.getAttribute("value"), "Check All");
		System.out.println(CheckBtn.getAttribute("value"));

		softAssert.assertAll();

	}

	public void CheckALLBtnClick() {
		// Verify Uncheck All Button WebElement uncheckAllBtn =
		CheckBtn = driver.findElement(By.xpath("//input[@value=\"Check All\"]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", CheckBtn);

		CheckBtn.click();

	}

	public void UncheckALLBtnClick() {
		// Verify Uncheck All Button WebElement uncheckAllBtn
		this.CheckALLBtnClick();
		unCheckBtn = driver.findElement(By.xpath("//input[@value=\"Uncheck All\"]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", unCheckBtn);

		softAssert.assertEquals(unCheckBtn.getAttribute("value"), "Uncheck All");
		System.out.println(unCheckBtn.getAttribute("value"));
		this.CheckALLBtn();
		unCheckBtn.click();

		softAssert.assertAll();

	}
}
