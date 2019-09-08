package com.techpath.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

	Properties prop;

	public ConfigReader() {

		try {

			prop = new Properties();

			String filePath = "./configurations//config.properties";

			InputStream input = new FileInputStream(filePath);

			prop.load(input);

		} catch (Exception ex) {

			ex.printStackTrace();
		}

	}

	public String getWebURL() {

		String websiteName = prop.getProperty("webURL");

		return websiteName;
	}

	public String getUserName() {
		return prop.getProperty("userName");
	}

	public String getPassword() {
		return prop.getProperty("password");
	}

	public String getChromePath() {

		return prop.getProperty("chromepath");
	}

	public String getFirefoxPath() {

		return prop.getProperty("firefoxpath");
	}

	public String getIEPath() {

		return prop.getProperty("iepath");
	}
}
