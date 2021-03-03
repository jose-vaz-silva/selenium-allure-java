package page;

import static constants.TimeOut.MAX_SECONDS;

import java.io.File;
import java.io.FilenameFilter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.DriverFactory;
import core.Hook;

public class BasePage {
	private WebDriver driver;

	public BasePage() {
		driver = Hook.driver;
	}

	public void navigate(String url) {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		if (!url.isEmpty())
//			if (driver == null) {
//				driver = new ChromeDriver();
//			}
		driver.navigate().to(url);
	}

	public void click(WebElement element) {
		element.click();
	}

	public void quitBrowser() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public void closeBrowser() {
		driver.close();
	}

	public void sendKeys(WebElement element, String value) {
		element.sendKeys(value);
	}

	public void sendKey(WebElement element, Keys key) {
		element.sendKeys(key);
	}

	public WebElement getWebElement(By by) {
		WebDriverWait wait = new WebDriverWait(driver, MAX_SECONDS);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
		return element;
	}

	public void waitElement(By locator, int timeOut) throws NoSuchElementException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, MAX_SECONDS);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("No elements found with " + locator + " locator");
		} catch (TimeoutException ex) {
			throw new TimeoutException("Time (" + MAX_SECONDS + ") exceeded to find element: " + locator);
		}
	}

	public void waitElementToBeClicable(By locator, int timeOut) throws NoSuchElementException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, MAX_SECONDS);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("No elements found with " + locator + " locator");
		} catch (TimeoutException ex) {
			throw new TimeoutException("Time (" + MAX_SECONDS + ") exceeded to find element: " + locator);
		}
	}

	public void clickJs(By by) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", getWebElement(by));
	}

	public void scrollDynamic(By by) {
		WebElement element = driver.findElement(by);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void clickActions(By by) {
		WebElement element = getWebElement(by);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
	}

	public String getTextElement(By by) {
		WebElement element = getWebElement(by);
		return element.getText();
	}

	public String getOptionSelected(By by) {
		Select select = new Select(getWebElement(by));
		WebElement option = select.getFirstSelectedOption();
		return option.getText();
	}

	public void scrollToElement(By by) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
	}

	public static String getLocalhost() throws UnknownHostException {
		InetAddress inet = InetAddress.getLocalHost();
		String localhost = inet.toString().split("/")[1];
		return localhost;
	}

	/**
	 * get files in directory with filter
	 * 
	 * @param directoryPath
	 * @param filterFile
	 * @return
	 */
	public static File[] getDirectoryFilesWithFilter(String directoryPath, String filterFile) {
		File directory = new File(directoryPath);
		File[] arquivos = directory.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File file, String nome) {
				return nome.matches(filterFile);
			}
		});
		return arquivos;
	}

}
