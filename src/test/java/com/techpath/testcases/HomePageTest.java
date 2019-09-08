package com.techpath.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.techpath.utilities.ConfigReader;

public class HomePageTest {

	WebDriver driver;

	ConfigReader reader;

	Logger logger;

	@BeforeClass
	public void setup() {

		reader = new ConfigReader();
		 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
				 + "//drivers//chromedriver.exe"); 

		driver = new ChromeDriver();

		logger = Logger.getLogger("HomePageTest");

		PropertyConfigurator.configure("log4j.properties");

		driver.get(reader.getWebURL());

		logger.info("The Website URL is up and running ");

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
	}

	@Test

	public void HomePage() throws InterruptedException {

		WebElement currencyChange = driver.findElement(By.xpath("//*[@id=\"form-currency\"]/div/button/i"));
		currencyChange.click();

		WebElement currency = driver.findElement(By.name("EUR"));

		currency.click();

		WebElement Canon = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[4]/div/div[2]/h4/a"));

		Canon.click();

		WebElement CanonPrice = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/ul[2]/li[2]/h2"));

		System.out.println(CanonPrice.getText());

		Assert.assertTrue(CanonPrice.getText().contentEquals("76.89€"));

		WebElement Desktop = driver.findElement(By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[1]/a"));

		Desktop.click();

		WebElement ShowallDesktop = driver.findElement(By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[1]/div/a"));

		ShowallDesktop.click();

		WebElement pctext = driver.findElement(By.xpath("//*[@id=\"column-left\"]/div[1]/a[2]"));

		System.out.println(pctext.getText());
		Assert.assertEquals(pctext.getText(), "   - PC (0)");

		WebElement maCtext = driver.findElement(By.xpath("//*[@id=\"column-left\"]/div[1]/a[3]"));

		System.out.println(maCtext.getText());

		Assert.assertEquals(maCtext.getText(), "   - Mac (1)");

		logger.trace("Trace Message!"); 

		logger.debug("Debug Message!");

		logger.info("Info Message!");

		logger.warn("Warn Message!");

		logger.error("Error Message!");

		logger.fatal("Fatal Message!");

		Thread.sleep(5000);

	}

	@AfterMethod
	@AfterClass
	public void tearDown() {

		driver.quit();

	}

}
