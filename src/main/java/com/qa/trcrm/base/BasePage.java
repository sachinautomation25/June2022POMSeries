package com.qa.trcrm.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public WebDriver driver;
	public Properties prop;
	OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to initialized the Webdriver on the basis of browserName
	 * 
	 * @param browserName
	 * @return this method will return the driver instance
	 */
	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser");
		optionsManager = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// option = new OptionsManager(prop);

			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();

			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));

		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();

			tlDriver.set(new EdgeDriver());
		} else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println(browserName + " not found");
		}
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();

		return getDriver();
	}

	public WebDriver init_driver2(Properties prop, String browserName) {

		// String browserName=prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		} else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			driver = new SafariDriver();
		} else {
			System.out.println(browserName + " not found");
		}
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	/**
	 * 
	 * @return this method returns properties-prop available in config.properties
	 *         file
	 */
	public Properties init_prop() {

		String path = null;
		String env = null;

		prop = new Properties();
		try {
			env = System.getProperty("env");

//			switch (env) {
//			case "qa":
//				path = "./src/main/java/com/qa/trcrm/config/config_qa.properties";
//				System.out.println("qa" + path);
//				break;
//			case "prod":
//				path = "./src/main/java/com/qa/trcrm/config/config_qa.properties";
//				break;
//
//			default:
//				path = "./src/main/java/com/qa/trcrm/config/config.properties";
//				System.out.println("default" + path);
//				break;
//			}

			if (env==null) {
				path = "./src/main/java/com/qa/trcrm/config/config.properties";
			}
			else if (env.equalsIgnoreCase("qa")) {
				path = "./src/main/java/com/qa/trcrm/config/config_qa.properties";
			} else if (env.equalsIgnoreCase("prod")) {
				path = "./src/main/java/com/qa/trcrm/config/config_prod.properties";
			} 
			FileInputStream fis = null;
			File file = new File(path);

			fis = new FileInputStream(file);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return prop;

	}

	/*
	 * take screenshot
	 */
	public String getScreenshot() {

		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File dest = new File(path);
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
