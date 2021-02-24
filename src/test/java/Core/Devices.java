package Core;


import Tests.LoginTests;
import Utils.Commons;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Devices implements ITestListener {

  public MobileDriver driver;

 public LoginTests loginTest;

 @BeforeMethod
    public void init() {

        Commons common = new Commons();
        Properties config = common.readConfigFile("runConfig");


        DesiredCapabilities capabilities = new DesiredCapabilities();

     capabilities.setCapability("platformName", config.getProperty("platformName"));
     capabilities.setCapability("platformVersion", config.getProperty("platformVersion"));
     capabilities.setCapability("deviceName", config.getProperty("deviceName"));
     capabilities.setCapability("app", new File(config.getProperty("appPath")).getAbsolutePath());
    capabilities.setCapability("autoAcceptAlerts",true);
    capabilities.setCapability("autoGrantPermissions",true);


     if(config.getProperty("platformName").equals("android")) {
            capabilities.setCapability("automationName", config.getProperty("automationName"));
            try {
                driver = new AndroidDriver(new URL(config.getProperty("server")), capabilities);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(config.getProperty("platformName").equals("iOS"))
        {
            capabilities.setCapability("automationName", config.getProperty("automationName"));
            try {
                driver = new IOSDriver(new URL(config.getProperty("server")), capabilities);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
     driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
     System.out.println("sout 1");

    }

    public void onTestFailure(ITestResult tr) {
        captureScreenShot(tr, "fail");
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();

    }


    public void captureScreenShot(ITestResult result, String status) {
        // AndroidDriver driver=ScreenshotOnPassFail.getDriver();
        String destDir = "";
        String passfailMethod = result.getMethod().getRealClass().getSimpleName() + "." + result.getMethod().getMethodName();
        // To capture screenshot.
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
        // If status = fail then set folder name "screenshots/Failures"
        if (status.equalsIgnoreCase("fail")) {
            destDir = "screenshots/Failures";
        }
        // If status = pass then set folder name "screenshots/Success"
        else if (status.equalsIgnoreCase("pass")) {
            destDir = "screenshots/Success";
        }

        // To create folder to store screenshots
        new File(destDir).mkdirs();
        // Set file name with combination of test class name + date time.
        String destFile = passfailMethod + " - " + dateFormat.format(new Date()) + ".png";

        try {
            // Store file at destination folder location
            FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
