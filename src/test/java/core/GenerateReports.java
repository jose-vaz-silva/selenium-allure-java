package core;

import java.io.IOException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GenerateReports extends GenerateEvidences {
	private static ExtentReports report;
	private static ExtentTest logger;

	public GenerateReports(String descriptionTest) {
		report = new ExtentReports(System.getProperty("user.dir") + "\\report\\testReports.html", false);
		logger = report.startTest(descriptionTest);
	}

	public void logger(LogStatus status, String descriptionStep) {
		logger.log(status, descriptionStep);
	}

	public static void saveLoggersReport() {
		report.endTest(logger);
		report.flush();
	}

	public ExtentTest getLogger() {
		return logger;
	}

	public static void takeScreenshotReport(LogStatus status, String screenshootName) throws IOException {
		String screenshot_path = getScreenshot(screenshootName);
		String image = logger.addScreenCapture(screenshot_path);
		logger.log(status, screenshootName, image);
	}
}
