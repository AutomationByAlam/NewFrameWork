package company.samPro.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class TestBases {
	
	public static final Logger logger = Logger.getLogger(TestBases.class.getName());
	public WebDriver driver;
	public static Properties OR;
	

	public File f1;
	public FileInputStream file;
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public ITestResult result;
	
	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir") + "/src/main/java/com/hybridFramework/report/test" + formater.format(calendar.getTime()) + ".html", false);
	}
	
	@BeforeTest
	public void launchBrowser() throws IOException{
		loadPropertiesFile();
		Config config = new Config(OR);
		getBrowser(config.getBrowser());
}
	

	@BeforeMethod()
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
	}
	

	public void loadPropertiesFile() throws IOException {
		
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		OR = new Properties();
		f1 = new File(System.getProperty("user.dir")+"/src/main/java/company/samPro/config/config.properties");
		file = new FileInputStream(f1);
		OR.load(file);
		logger.info("loading config.properties");
		
		f1 = new File(System.getProperty("user.dir")+"/src/main/java/company/samPro/config/config.properties");
		file = new FileInputStream(f1);
		OR.load(file);
		logger.info("loading or.properties");
		
	
			}

	


	public void getBrowser(String browser) throws IOException {
		
		if(System.getProperty("os.name").contains("Window")){
			if(browser.equalsIgnoreCase("firefox")){
				//https://github.com/mozilla/geckodriver/releases
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("chrome")){
				//https://chromedriver.storage.googleapis.com/index.html
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
				driver = new ChromeDriver();
			}
		}
		else if(System.getProperty("os.name").contains("Mac")){
			System.out.println(System.getProperty("os.name"));
			if(browser.equalsIgnoreCase("firefox")){
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver");
				driver = new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver");
				driver = new ChromeDriver();
			}
		}
	}
	
	@Test
	public void a()
	{
		Assert.assertTrue(false);
	}
	

	@AfterMethod()
	public void afterMethod(ITestResult result) throws IOException {
		getresult(result);
	}


public void getresult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.SUCCESS) {

			test.log(LogStatus.PASS, result.getName() + " test is pass");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getName() + " test is failed" + result.getThrowable());
			String screen = getScreenShot("");
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
		} else if (result.getStatus() == ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName() + " test is started");
		}
	}

public String getScreenShot(String imageName) throws IOException{
	
	if(imageName.equals("")){
		imageName = "blank";
	}
	File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String imagelocation = System.getProperty("user.dir")+"/src/main/java/com/hybridFramework/screenshot/";
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	String actualImageName = imagelocation+imageName+"_"+formater.format(calendar.getTime())+".png";
	File destFile = new File(actualImageName);
	FileUtils.copyFile(image, destFile);
	return actualImageName;
}


	@AfterClass(alwaysRun = true)
	public void endTest() {
		driver.quit();
		extent.endTest(test);
		extent.flush();
	}
	

}