package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserModificationTest extends TestBase{

    @Test
    public void testUpdateUser() throws Exception {
        app.getContactHelper().initUserModification();
        app.getContactHelper().fullUserCreationForm(new UserData("first name", "middle name", "last name", null), false);
        app.getContactHelper().submitUserModification();
        app.getContactHelper().gotoHomePage();
    }

}
