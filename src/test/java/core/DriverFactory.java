package core;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {
	private DriverFactory() {
	};

//	private static RemoteWebDriver driver;
	private static WebDriver driver;

	public static void setupChromeDriver() {
		String chromedriverFileName = "chromedriver_88";
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("windows")) {
			chromedriverFileName += ".exe";
		}

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + File.separator + "driver" + File.separator + chromedriverFileName);
	}

	public static ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
//		options.addArguments("--headless");
		options.addArguments("disable-gpu");
		options.addArguments("--start-maximized");
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.setExperimentalOption("useAutomationExtension", false);
		options.setCapability(ChromeOptions.CAPABILITY, options);
		return options;
	}
	
	public static WebDriver getDriver() {
		if (driver == null || driver.toString().toLowerCase().contains("null")) {
			setupChromeDriver();
			
			driver = new ChromeDriver(getChromeOptions());
		}
		return driver;
	}

	public static void killDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

}