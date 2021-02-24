package Core;

import Utils.Commons;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

public class UserAction {


    public MobileElement findElementByXpath(MobileDriver driver, String[] elementName) {
        Commons common = new Commons();
        Properties config = common.readConfigFile("runConfig");
        MobileElement element = null;

        if (config.getProperty("platformName").equals("android")) {
            element = ((AndroidElement) driver.findElement(By.xpath(elementName[0])));


        } else if (config.getProperty("platformName").equals("iOS")) {
            element = ((IOSElement) driver.findElement(By.xpath(elementName[1])));

        }
        return element;

    }

    public List<MobileElement> findElementListByXpath(MobileDriver driver, String[] elementName) {

        Commons common = new Commons();
        Properties config = common.readConfigFile("runConfig");
        List<MobileElement> element = null;

        if (config.getProperty("platformName").equals("android")) {

            element = driver.findElements(By.xpath(elementName[0]));
            System.out.println("Found Element" + element.size());

        } else if (config.getProperty("platformName").equals("iOS")) {
            element = driver.findElements(By.xpath(elementName[0]));
            System.out.println("Found Element" + element.size());

        }


        return element;
    }

    private String getReferenceImageB64(String image1) {
        String image = null;
        try {

            File refImgFile = new File(image1);

            image = Base64.getEncoder().encodeToString(Files.readAllBytes(refImgFile.toPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Images" + image);
        return image;
    }

    public MobileElement findElementById(MobileDriver driver, String element) {
        return (AndroidElement) driver.findElement(By.id(element));
    }

    public MobileElement findElementByImage(MobileDriver driver, String ImagePath) {
        return (AndroidElement) driver.findElement(MobileBy.image(getReferenceImageB64(ImagePath)));
    }

}
