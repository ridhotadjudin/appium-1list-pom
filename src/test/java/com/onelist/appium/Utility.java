package com.onelist.appium;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Utility {
	
	private AndroidDriver driver;
	
	public Utility(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void screenshotAll() {
		File fileSource = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String formatName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String fileName = "D:\\appium-workspace\\1List\\screenshotOneList\\" + formatName + ".jpg";
		File ss = new File(fileName);
		try {
			FileUtils.copyFile(fileSource, ss);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void delay(int input) {
		try {
			Thread.sleep(input * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
