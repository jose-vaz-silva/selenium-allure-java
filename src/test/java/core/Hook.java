package core;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import page.BasePage;

public class Hook extends BasePage {
	public static  WebDriver driver;
	
	@After
	public void fechar(Scenario scenario) throws IOException {
		GenerateReports.saveLoggersReport();
//		logError(scenario);
		GenerateEvidences.getScreenshot("Resultado da execução", System.getProperty("user.dir") + File.separator + "execution-docker-result" + File.separator + "evidences" + File.separator
				+ "resultado da execução"  + System.currentTimeMillis() + ".png");
		quitBrowser();
	
	}
	
	@Before
	public void inicializar() {
		quitBrowser();
		Hook.driver = DriverFactory.getDriver();
	}

//	private static String logError(Scenario scenario) {
//		Field field = FieldUtils.getField(((ScenarioImpl) scenario).getClass(), "stepResults", true);
//		field.setAccessible(true);
//		String error = null;
//		try {
//			@SuppressWarnings("unchecked")
//			ArrayList<Result> results = (ArrayList<Result>) field.get(scenario);
//			for (Result result : results) {
//				if (result.getError() != null) {
//					System.out.printf("Error Scenario: {}", scenario.getId(), result.getError().getMessage());
//					String[] resultErrorMessage =  result.getError().getMessage().split("Build info:");
//					error = resultErrorMessage[0].replaceFirst("([A-Z,a-z,0-9]+\\.?)+\\:\\s", "");
//				}
//			}
//		} catch (Exception e) {
//			System.out.printf("\nError while logging error", e);
//		}
//		return error;
//	}

}
