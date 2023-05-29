package allureReports;




import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Listeners(AllureListener.class)
public class Tests extends BaseClass{
	
	public WebDriver driver;
	@BeforeClass
	public void setUp()
	{
		BaseClass bs=new BaseClass();
		bs.initlaize_drver();
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();
	}
	@Test(priority=1, description="Verify Logo presence on Home Screen")
	@Description("Verify Logo presence on Home Screen..........!")
	@Epic("EP001")
	@Feature("Festure1 : Logo")
	@Story("Story : Logo Presence")
	@Step("Verify Logo Presence")
	@Severity(SeverityLevel.MINOR)
	public void logoPresenceTest()
	{
		boolean flag=driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[1]/a/img")).isDisplayed();
		Assert.assertEquals(flag, true);
	}
	
	@Test(priority=2, description="Login to the account by providing valid credentials")
	@Description("Verify Login Page By providing Valid credentials.....!")
	@Epic("EP001")
	@Feature("Festure2 : Login")
	@Story("Story : Login page")
	@Step("Verify Login Page")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() throws InterruptedException
	{
		driver.findElement(By.linkText("Log in")).click();
		driver.findElement(By.id("Email")).sendKeys("admin@yourstore.com");
		driver.findElement(By.id("Password")).sendKeys("admin");
		driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/button")).click();
		Thread.sleep(5000);
		System.out.println(driver.getTitle());
		String title=driver.getTitle();
		
		Assert.assertEquals(title, "Dashboard / nopCommerce administration");
	}
	
	@Test(priority=3, description="Registering By Providing All Mondatory information")
	@Description("Verify Registration By Providing All Mondatory information.....!")
	@Epic("EP001")
	@Feature("Festure3 : Registration")
	@Story("Story : Registration page")
	@Step("Verify Registration page")
	@Severity(SeverityLevel.NORMAL)
	public void registerTest()
	{
		throw new SkipException("TestCase Skipped");
		
	}
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}

}
