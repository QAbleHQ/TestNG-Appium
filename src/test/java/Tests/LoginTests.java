package Tests;

import Core.Devices;
import PageObjects.LoginTestPO;
import Utils.Commons;

import org.testng.annotations.Test;

public class LoginTests extends Devices {




    @Test
    public void loginWirthValidCredentials() {

       LoginTestPO loginTestPO = new LoginTestPO(driver);
        Commons common = new Commons();

    }



}
