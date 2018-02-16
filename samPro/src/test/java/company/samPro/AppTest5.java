package company.samPro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest5 
    
{
    @Test
    public void testApp()
   {
    	
    	WebDriver driver;

    	   
   /* azhrggggg	 
    	  
   	 System.setProperty("webdriver.chrome.driver","H:\\Drivers\\chromedriver.exe");
   	// Create object of ChromeOptions class
   	 ChromeOptions options = new ChromeOptions();
   	  
   	 // add parameter which will disable the extension
   	 options.addArguments("--disable-extensions");
   	  
   	 // Start the chrome session
   	 
   		  driver = new ChromeDriver(options);
   		  
   		  driver.get("http://www.fb.com");
   		  
   		  System.out.println("azhar alam");
   		  
   		  driver.close();
   		  
   		  
//   		public String baseUrl = "http://newtours.demoaut.com/";
   	   
   	 
   	  
   		// System.setProperty("webdriver.ie.driver","C:\\Users\\vaio\\Downloads\\Compressed\\IEDriverServer_x64_3.4.0\\IEDriverServer.exe");
   		// "C:\Users\vaio\Downloads\Compressed\IEDriverServer_Win32_3.4.0\IEDriverServer.exe"
   		 
   		 System.setProperty("webdriver.ie.driver","H:\\Drivers\\IEDriverServer.exe");

   		 driver = new InternetExplorerDriver();
   			  
   			  driver.get("http://www.fb.com");
   		      String expectedTitle = "Welcome: Mercury Tours";
   		      driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("azhar1911");
   		      String actualTitle = driver.getTitle();
   		     // Assert.assertEquals(actualTitle, expectedTitle);
   		      driver.close();
   			  
   	*/	   
    	System.setProperty("webdriver.gecko.driver", "H:\\Drivers\\geckodriver.exe");
    	
   		   driver = new FirefoxDriver();
   		driver.get("http://www.fb.com");
   		//driver.close();
   		driver.manage().window().maximize();
   		
   		String s1 = driver.getTitle();
   		String s2 = driver.getPageSource();
   		
   		String s3 = "Facebook - Log In or Sign Up";
   		
   		//Assert.assertEquals(s1,s3);
   		
   		//Assert.assertTrue(s1.contains("Facebook"));
   		
   	//	Assert.assertTrue(s2.contains("Facebook"));
   		
   		System.out.println(s1+"   azhar ");
   		
   	}

   	      
   		  }

