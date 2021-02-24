package PageObjects;

import Core.UserAction;
import Utils.Commons;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;

import java.util.HashMap;
import java.util.Map;

public class LoginTestPO {


    String[] selectUniversity_textBox = {"Android Element Path",
            "iOS Element Path"};





    public MobileDriver driver;
    UserAction action = new UserAction();





    public LoginTestPO(MobileDriver driver) {
           this.driver = driver;
    }

    Commons common = new Commons();
    public void setUniversity(String university) {
        Commons.pause(5);

        common.logStepMessage("Step : Enter University Name - " + university);
        action.findElementByXpath(driver,selectUniversity_textBox).sendKeys(university);
        Commons.pause(2);
    }

    public void verifySignInHeader(){
        common.logStepMessage("Step : Verify Sign_In Header");
        Commons.pause(5);
    }

    public void performLogin(String userName, String password) {
        Commons.pause(10);
    }




}
