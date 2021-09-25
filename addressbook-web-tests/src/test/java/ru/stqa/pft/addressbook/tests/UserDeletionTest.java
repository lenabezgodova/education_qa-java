package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;

public class UserDeletionTest extends TestBase {

    @BeforeTest
    public void ensurePreconditions(){
        app.goTo().pageHome();

        if (! app.contact().isThereAUser()){
            app.contact().createNewUser(new UserData().withFirstName("first name").withMiddleName("middle name").withLastName("last name").withGroup("test1"), true);
        }
    }

    @Test
    public void testDeletionGroup() throws InterruptedException {

        List<UserData> before = app.contact().getListUsers();
        int index = before.size()-1;

        app.contact().delete(index);
        app.goTo().pageHome();
        Thread.sleep(3000);
        List<UserData> after = app.contact().getListUsers();

        //System.out.println("DeleteTest: Before: " + before);
        //System.out.println("DeleteTest: After: " + after);
        //проверка размера - пока они отличаются,новый элемнт никуда не добавляла\удаляла
        Assert.assertEquals(after.size(), before.size()-1);

        before.remove(index);
        System.out.println("DeleteTest:: this action was done - before.remove(before.size() - 1); ");
        System.out.println("DeleteTest: Before: " + before);
        System.out.println("DeleteTest: After: " + after);
        Assert.assertEquals(before, after);
    }


}
