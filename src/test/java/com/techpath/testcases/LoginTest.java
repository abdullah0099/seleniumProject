package com.techpath.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.techpath.utilities.ConfigReader;
import com.techpath.utilities.ScreenShot;

public class LoginTest {

	WebDriver driver;

	ConfigReader reader = new ConfigReader();

	public static Logger logger;

	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) {

		logger = Logger.getLogger("techpath");

		PropertyConfigurator.configure("log4j.properties");
		
		if (browser.contentEquals("chrome")) {

			 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
			 + "//drivers//chromedriver.exe");

			//System.setProperty("webdriver.chrome.driver", reader.getChromePath());
			driver = new ChromeDriver();

		} else if (browser.contentEquals("firefox")) {

			System.setProperty("webdriver.gecko.driver", reader.getFirefoxPath());
			driver = new FirefoxDriver();

		} else if (browser.contentEquals("ie")) {

			System.setProperty("webdriver.ie.driver", reader.getIEPath());
			driver = new InternetExplorerDriver();

		}

		driver.get(reader.getWebURL());

		logger.info("The Website URL is up and running ");

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
	}

	@Test
	public void Login() throws InterruptedException, IOException {

		WebElement myAcctButton = driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a"));
		myAcctButton.click();

		logger.info("My Account button is clicked");

		WebElement loginButton1 = driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a"));
		loginButton1.click();

		WebElement userNameTextField = driver.findElement(By.name("email"));
		userNameTextField.sendKeys(reader.getUserName());

		logger.info("Username is inserted");

		WebElement passwordTextField = driver.findElement(By.name("password"));
		passwordTextField.sendKeys(reader.getPassword());

		WebElement loginButton2 = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input"));
		loginButton2.click();

		logger.trace("Trace Message!");
		logger.debug("Debug Message!");
		logger.info("Info Message!");
		logger.warn("Warn Message!");
		logger.error("Error Message!");
		logger.fatal("Fatal Message!");

		System.out.println(driver.getTitle());

		if (driver.getTitle().contentEquals("My Account")) {
			logger.info("My Account title has matched succesfully ");
		}

		else {
			logger.error("My Account title did not match");

			logger.info("the title is shown as" + driver.getTitle());

			ScreenShot.captureScreen(driver, "login");
		}
		Assert.assertEquals(driver.getTitle(), "My Account");

		Thread.sleep(5000);
	}

	@AfterClass
	public void tearDown() {

		driver.quit();

	}
}
