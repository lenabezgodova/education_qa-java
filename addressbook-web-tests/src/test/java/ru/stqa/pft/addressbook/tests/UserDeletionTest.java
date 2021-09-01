package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class UserDeletionTest extends TestBase {

    @Test
    public void testDeletionGroup() throws InterruptedException {
        app.getNavigationHelper().gotoPageHome();
        app.getContactHelper().selectUser();
        app.getContactHelper().deleteSelectedUser();
        app.getContactHelper().accertDialogWindow();
    }
}
