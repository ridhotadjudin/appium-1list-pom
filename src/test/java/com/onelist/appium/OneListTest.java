package com.onelist.appium;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.android.AndroidDriver;

public class OneListTest extends ExtentReportsDemo {
	
	private AndroidDriver driver;
	private DesiredCapabilities capabilities;
	private MainActivity main;
	private Utility util;
	
	String contohNamaList = "Jadwal Harian";
	String[] contohIsi = {"Belajar","Masak","Olahraga"};
	String[] contohComment = {"Belajar","Masak","Olahraga"};
	
	@BeforeTest
	public void init() throws MalformedURLException {
		capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "11");
		capabilities.setCapability("uid", "emulator-5554");
		capabilities.setCapability("appPackage", "com.lolo.io.onelist");
		capabilities.setCapability("appActivity", "com.lolo.io.onelist.MainActivity");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		main = new MainActivity(driver);
		util = new Utility(driver);
		
	}
	
	@Test(priority = 1)
	public void addNewList() {
		ExtentTest testLog = extent.createTest("Test Insert New List");
		testLog.log(Status.INFO, "Test started.");
		util.delay(1);
		
		main.btnAddList.click();
		testLog.log(Status.PASS, "Hit add list button");
		util.delay(1);
		
		main.boxInputListTitle.sendKeys(contohNamaList);
		testLog.log(Status.PASS, "Insert new list name");
		util.delay(1);
		
		main.btnValidateEditList.click();
		testLog.log(Status.PASS, "Hit save list button");
		util.delay(1);
		
		// fungsi untuk test list
		List<WebElement> lstElement = driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.lolo.io.onelist:id/textView']"));
		String expectedChar = contohNamaList;
		boolean check = false;
		for (WebElement webElement : lstElement) {
			if (webElement.getText().contains(expectedChar)) {
				check = true;
				testLog.log(Status.PASS, "Back to main menu with new list added");
				break;
			}
		}
		assertTrue(check);
		util.delay(1);
		
		testLog.log(Status.INFO, "Test completed.");
		System.out.println("Test 1 beres");
	}
	
	@Test(priority=2)
	public void addNewIsiList() {
		ExtentTest testLog = extent.createTest("Test Insert New List");
		testLog.log(Status.INFO, "Test started.");
		util.delay(1);
		
		for(int i=0;i<=contohIsi.length;i++) {
			main.boxAddIsi.sendKeys(contohIsi[i]);
			testLog.log(Status.PASS, "Insert new list text");
			util.delay(1);
			
			// MASIH ERROR - check componen yg belum exist
//			if(main.boxAddIsiComment.isEnabled() == false) {
//				main.btnAddIsiComment.click();
//				testLog.log(Status.PASS, "Hit add comment button");
//				util.delay(1);
//			}
			
			main.boxAddIsiComment.sendKeys(contohComment[i]);
			testLog.log(Status.PASS, "Insert new list text comment");
			util.delay(1);
			
			main.btnSaveIsi.click();
			testLog.log(Status.PASS, "Hit save button");
			util.delay(1);
		}
	
		// function to test isi
		List<WebElement> lstElement = driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.lolo.io.onelist:id/text']"));
		String expectedChar = contohIsi[0];
		boolean check = false;
		for (WebElement webElement : lstElement) {
			if (webElement.getText().contains(expectedChar)) {
				check = true;
				testLog.log(Status.PASS, "Back to main menu with new list text and comment added");
				
				main.btnExpand.click();
				testLog.log(Status.PASS, "Hit expand button");
				util.delay(1);
				
				//fungsi untuk check comment
				List<WebElement> lstComment = driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.lolo.io.onelist:id/comment']"));
				String expComment = "";
				for (WebElement commentElement : lstComment) {
					if(commentElement.getText().equalsIgnoreCase(contohComment[0])) {
						testLog.log(Status.PASS, "Check comment pass");
						expComment = commentElement.getText();
						break;
					}
				}
				assertEquals(expComment, contohComment[0]);
				System.out.println("Comment found");
				util.delay(1);
				
				break;
			}
		}
		assertTrue(check);
		util.delay(1);
		
		testLog.log(Status.INFO, "Test completed.");
		System.out.println("Test 2 beres");
	}
	
//	@Test3(priority=3)
//	public void update
	
	
	//////////////////////////////////////////
	
//	@Test(priority = 1)
//	public void openApp() {
//		System.out.println("at main menu");
//		
//		String actual = main.labelMainTitle.getText();
//		String expected = "1List";
//		
//		Assert.assertEquals(actual, expected);
//		util.screenshotAll();
//		util.delay(1);
//	}
//	
//	@Test(priority=2)
//	public void clickAddList() {
//		main.btnAddList.click();
//		System.out.println("click add list");
//		util.delay(2);
//		
//		String actual = main.boxInputListTitle.getText();
//		String expected = "List name";
//		System.out.println("get title list box");
//		
//		Assert.assertEquals(actual, expected);
//		util.screenshotAll();
//		util.delay(1);
//	}
//	
//	@Test(priority=3)
//	public void insertListName() {
//		main.boxInputListTitle.sendKeys(contohNamaList);
//		System.out.println("input nama list");
//		
//		util.delay(1);
//		String actual = main.boxInputListTitle.getText();
//		String expected = "Jadwal Harian";
//		
//		Assert.assertEquals(actual, expected);
//		util.screenshotAll();
//		util.delay(1);
//	}
//	
//	@Test(priority=4)
//	public void clickBtnSave() {
//		main.btnValidateEditList.click();
//		System.out.println("hit save button");
//		
//		util.delay(1);
//		String actual = main.labelMainTitle.getText();
//		String expected = "1List";
//		
//		Assert.assertEquals(actual, expected);
//		util.screenshotAll();
//		util.delay(1);
//	}
//	
//	@Test(priority=5)
//	public void getNewListInMain() {
//		List<WebElement> lstElement = driver.findElements(
//				By.xpath("//android.widget.TextView[@resource-id='com.lolo.io.onelist:id/textView']"));
//		
//		String expectedChar = contohNamaList;
//		boolean check = false;
//		for (WebElement webElement : lstElement) {
//			if (webElement.getText().contains(expectedChar)) {
//				check = true;
//				break;
//			}
//		}
//		System.out.println("check new list");
//		assertTrue(check);
//		util.screenshotAll();
//		util.delay(1);
//	}
}
