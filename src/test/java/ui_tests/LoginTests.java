package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ContactPage;
import pages.HomePage;
import pages.LoginPage;
import utils.RetryAnalyser;
import utils.TestNGListener;

import java.lang.reflect.Method;


import static utils.PropertiesReader.*;
@Listeners(TestNGListener.class)


public class LoginTests extends AppManager {
    @Test(retryAnalyzer = RetryAnalyser.class)
    public void loginPositiveTest()
    {
        // System.out.println("first test");

        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();

        LoginPage loginPage = new LoginPage(getDriver());
        //loginPage.typeLoginRegistrationForm("Marat1990@mail.com", "Marat1990!");
        loginPage.typeLoginRegistrationForm(getProperty("base.properties", "login"),
                getProperty("base.properties", "password"));
        loginPage.clickBtnLoginForm();

        //ContactPage contactPage = new ContactPage(getDriver());
        //Assert.assertTrue(contactPage.isAddInDisplayed(), "ERROR");
        //Assert.assertTrue(new ContactPage(getDriver()).isTextInBtnAddPresent("ADD"));


    }

    @Test
    public void deleteFirstContact(){
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationForm(getProperty("base.properties", "login"),
                getProperty("base.properties", "password"));
        loginPage.clickBtnLoginForm();
        ContactPage contactPage = new ContactPage(getDriver());
        contactPage.clickBtnContacts();
        contactPage.deleteFirstContact();

    }

    @Test(groups = {"smoke", "user"})
    public void loginPositiveTestWithUser(Method method)
    {
        User user = new User(getProperty("base.properties", "login"),
                getProperty("base.properties", "password"));
        logger.info("Start test " + method.getName() + " with user " + user);
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertTrue(new ContactPage(getDriver()).isTextInBtnSignOutPresent("Sign Out"));
//        ContactPage contactPage = new ContactPage(getDriver());
//        Assert.assertTrue(contactPage.isContactsInDisplayed(), "ERROR");
    }

    @Test(groups = "negative")
    public void loginNegativeTest_WrongEmail(){
        User user = new User("Marat1990mail.com", "Marat1990!");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }


    @Test
    public void loginNegativeTest_WrongPassword(){
        User user = new User("Marat1990@mail.com", "123456789");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegativeTest_EmptyPassword(){
        User user = new User("Marat1990@mail.com", "");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

}