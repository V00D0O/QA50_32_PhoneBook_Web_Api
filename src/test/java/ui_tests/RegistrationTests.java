package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.Random;
import static utils.UserFactory.*;


public class RegistrationTests extends AppManager {
   LoginPage loginPage;

    @BeforeMethod
    public void goToRegisrationPage(){
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }
    @Test
    public void registrationPositiveTest(){
        int i = new Random().nextInt(1000);
        User user = new User("muyitr"+i+"@gmail.com", "Passwword123!");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertTrue(new ContactPage(getDriver()).isTextIncontactPageMessagePresent("No Contacts here!"));
    }
    @Test
    public void registrationPositiveTest_WithFaker(){
        User user = positiveUser();
        System.out.println(user);
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertTrue(new ContactPage(getDriver()).isTextIncontactPageMessagePresent("No Contacts here!"));
    }
}
