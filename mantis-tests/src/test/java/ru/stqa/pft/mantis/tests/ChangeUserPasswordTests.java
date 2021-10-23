package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class ChangeUserPasswordTests extends TestBase{

    @BeforeMethod
    public void preconditions(){
        app.mail().start();
    }


    @AfterMethod(alwaysRun = true)
    public void postconditions(){
        app.mail().stop();
    }
}
