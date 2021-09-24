package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;

public class UserDeletionTest extends TestBase {

    @Test(enabled = false)
    public void testDeletionGroup() throws InterruptedException {
        app.goTo().onPageHome();

        if (! app.getContactHelper().isThereAUser()){
            app.getContactHelper().createNewUser(new UserData("first name", "middle name", "last name", "test1"), true);
        }
        Thread.sleep(3000);

        List<UserData> before = app.getContactHelper().getListUsers();

        app.getContactHelper().selectUser(before.size()-1);
        app.getContactHelper().deleteSelectedUser();
        app.getContactHelper().accertDialogWindow();
        Thread.sleep(3000);
        app.goTo().onPageHome();
        Thread.sleep(3000);

        List<UserData> after = app.getContactHelper().getListUsers();

        System.out.println("DeleteTest: Before: " + before);
        System.out.println("DeleteTest: After: " + after);
        //проверка размера - пока они отличаются,новый элемнт никуда не добавляла\удаляла
        Assert.assertEquals(after.size(), before.size()-1);

        before.remove(before.size()-1);
        System.out.println("DeleteTest:: this action was done - before.remove(before.size() - 1); ");
        System.out.println("DeleteTest: Before: " + before);
        System.out.println("DeleteTest: After: " + after);
        Assert.assertEquals(before, after);
    }
}
