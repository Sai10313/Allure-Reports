package allureReports;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;

public class AllureListener implements ITestListener{
	
	private static String getTestMethodName(ITestResult iTestResult)
	{
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Attachment
	public byte[] saveFailureScreenShot(WebDriver driver)
	{
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}
	@Attachment(value="{0}", type="text/plain")
	public static String saveTextLog(String message)
	{
		return message;
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("I am in onTestStart Method : "+getTestMethodName(result)+"start");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("I am in onTestSuccess Method : "+getTestMethodName(result)+"succeed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("I am in onTestFailure Method : "+getTestMethodName(result)+"failed");
		
		Object testClass=result.getInstance();
		WebDriver driver=BaseClass.getDriver();
		if(driver instanceof WebDriver)
		{
			System.out.println("Sceenshot captured for test case:" +getTestMethodName(result));
			saveFailureScreenShot(driver);
		}
		saveTextLog(getTestMethodName(result) +"failed and ScreenShot taken");
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("I am in onstart method : "+context.getName());
		context.setAttribute("WebDriver", BaseClass.getDriver());
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("I am in onFinish Method : "+context.getName());
	}
	
	
}
