package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;


public class UserCreationTest extends TestBase{

    @Test
    public void testUntitledTestCase() throws Exception {
        app.getContactHelper().initUserCreation();
        app.getContactHelper().fullUserCreationForm(new UserData("first name", "middle name", "last name", "nick name", "title", "company", "address", "email1@mail.ru", "9109101010", "910 910 10 10", "https://ru.stackoverflow.com/"));
        app.getContactHelper().workWithDropDownBox();
        app.getContactHelper().submitUserCreation();
        app.getContactHelper().gotoHomePage();
    }
}
