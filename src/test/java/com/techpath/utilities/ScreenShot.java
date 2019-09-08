package com.techpath.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {

	public static void captureScreen(WebDriver driver, String testName) throws IOException {

		TakesScreenshot scrShot = (TakesScreenshot) driver;

		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		File DestFile = new File(System.getProperty("user.dir") + "/screenshots/" + testName + ".png");

		FileUtils.copyFile(SrcFile, DestFile);

		System.out.println("Screenshot is taken");

		/*
		 * <dependency> <groupId>org.apache.directory.studio</groupId>
		 * <artifactId>org.apache.commons.io</artifactId> <version>2.4</version>
		 * </dependency>
		 */

	}

}
