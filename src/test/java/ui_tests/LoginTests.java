package ui_tests;

import manager.AppManager;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends AppManager {
   @Test
   public void loginPositiveTest(){
       System.out.println("first test");
       HomePage homePage = new HomePage(getDriver());
       LoginPage loginPage = new LoginPage(getDriver());
       loginPage.typeLoginRegistrationForm("family@mail.ru", "Family123!" );
       ;loginPage.clickBtnLoginForm();
   }

}
