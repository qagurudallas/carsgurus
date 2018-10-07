import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Cars {
	WebDriver driver;
	WebDriverWait wait;
	@BeforeMethod()
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "c:\\qaclass\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://www.cargurus.com");
		// Searching and clicking Certified preowned cars div  		
		driver.findElement(By.xpath("//*[@id=\"conditionSelector\"]/div[3]/div[1]")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test(priority=1)
	public void verfiyTitleOfCars() {
		String actualTitle = driver.getTitle();
		assertTrue(actualTitle.contains("CarGurus"));
		
	}
	
	@Test(priority=2)
	public void SearchCarsForAZipCode() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.findElement(By.id("dealFinderZipCPOId")).sendKeys("11554");
		driver.findElement(By.id("dealFinderFormCPO_0")).click();
		String actualTitle = driver.getTitle();
		assertTrue(actualTitle.contains("East Meadow, NY"));
	}
	@Test(priority=3)
	public void titleOfSearchPageByZipCode() {
		driver.findElement(By.id("dealFinderZipCPOId")).sendKeys("11554");
		driver.findElement(By.id("dealFinderFormCPO_0")).click();
		String actualZipCode = driver.findElement(By.id("newSearchHeaderForm_UsedCar_zip")).getAttribute("value");
		String expectedZipCode="11554";
		Assert.assertEquals(actualZipCode, expectedZipCode);
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();		
	}

}
