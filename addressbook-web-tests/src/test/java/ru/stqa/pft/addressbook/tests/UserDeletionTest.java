package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserDeletionTest extends TestBase {

    @Test
    public void testDeletionGroup() throws InterruptedException {
        app.getNavigationHelper().gotoPageHome();

        if (! app.getContactHelper().isThereAUser()){
            app.getContactHelper().createNewUser(new UserData("first name", "middle name", "last name", "test1"), true);
        }
        Thread.sleep(5000);

        app.getContactHelper().selectUser();
        app.getContactHelper().deleteSelectedUser();
        app.getContactHelper().accertDialogWindow();
    }
}
