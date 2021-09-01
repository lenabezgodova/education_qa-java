package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserModificationTest extends TestBase{

    @Test
    public void testUpdateUser() throws Exception {
        app.getContactHelper().initUserModification();
        app.getContactHelper().fullUserCreationForm(new UserData("first name", "middle name", "last name", "nick name", "title", "company", "address", "email1@mail.ru", "9109101010", "910 910 10 10", "https://ru.stackoverflow.com/"));
        app.getContactHelper().submitUserModification();
        app.getContactHelper().gotoHomePage();
    }

}
