package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactPage;
import pages.HomePage;
import pages.LoginPage;
import utils.RetryAnalyser;

public class LoginTests extends AppManager {
   @Test(retryAnalyzer = RetryAnalyser.class)
   public void loginPositiveTest(){
       //System.out.println("first test");
       HomePage homePage = new HomePage(getDriver());
       homePage.clickBtnLogin();
       LoginPage loginPage = new LoginPage(getDriver());
       loginPage.typeLoginRegistrationForm("marat11@mail.com", "Marat116!" );
       loginPage.clickBtnLoginForm();
       Assert.assertTrue(new ContactPage(getDriver()).isTextInBtnAddPresent("ADD"),"validate text");

   }
    @Test
    public void loginPositiveTestWithUser(){
        User user = new User("marat11@mail.com", "Marat116!");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertTrue(new ContactPage(getDriver()).isTextInBtnSignOutPresent("Sign Out"));

    }

    @Test
    public void loginNegativeTest_WrongEmail(){
       User user = new User("marat11@mail.co", "Marat116!");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");

    }
    @Test
    public void loginNegativeTest_WrongPassword(){
        User user = new User("marat11@mail.com", "amily123");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Login Failed with code 401");

    }

}
