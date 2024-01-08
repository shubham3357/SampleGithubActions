package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;

public class BaseClass {

	public ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest loggerNew;
	public static WebDriver webDriver;
	ReadConfig readconfig = new ReadConfig();

	public String userName = readconfig.getUserName().trim(); //actions add additional space when write value in properties
	public String password = readconfig.getPassword(); //actions do not add spaces for last insert
	public String baseURL = readconfig.getApplicationURL().trim();
	public String platformType = readconfig.getPlatformType();
	public String browser = readconfig.getBrowser().trim(); //actions add additional space when write value in properties

	public void setUp() {
		try {
			if (platformType.equalsIgnoreCase("web")) {
				if (browser.equalsIgnoreCase("chrome")) {
					WebDriverManager.chromedriver().setup();
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--no-sandbox");
					options.addArguments("--disable-dev-shm-usage");
					options.addArguments("--headless");
					webDriver = new ChromeDriver(options);
				}

				webDriver.manage().window().maximize();
				webDriver.get(baseURL);

			}
		} catch (Exception e) {
			//loggerNew.info("Exception occured while returning the driver");
		}
	}



	public void tearDown() {
		if (platformType.equalsIgnoreCase("web") && webDriver != null) {
			webDriver.quit();
			//loggerNew.info("Successfully quit driver!!!");
		}
	}
}