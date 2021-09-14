package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserModificationTest extends TestBase{

    @Test
    public void testUpdateUser() throws Exception {
        app.getNavigationHelper().gotoPageHome();

        if (! app.getContactHelper().isThereAUser()){
            app.getContactHelper().createNewUser(new UserData("first name", "middle name", "last name", "test1"), true);
        }
        Thread.sleep(5000);

        app.getContactHelper().initUserModification();
        app.getContactHelper().fullUserCreationForm(new UserData("Ivanov", "Ivan", "Ivanovich", null), false);
        app.getContactHelper().submitUserModification();
        app.getContactHelper().gotoHomePage();
    }

}
