package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;
import java.util.Set;

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
        Set<UserData> before = app.contact().all();
        UserData deletedUser = before.iterator().next();
        System.out.println("deletedUser ---> " + deletedUser);

        //int index = before.size()-1;
        app.contact().delete(deletedUser);
        app.goTo().pageHome();
        Thread.sleep(3000);
        Set<UserData> after = app.contact().all();

        //проверка размера - пока они отличаются,новый элемнт никуда не добавляла\удаляла
        Assert.assertEquals(after.size(), before.size()-1);
        before.remove(deletedUser);
        Assert.assertEquals(before, after);

        System.out.println("DeleteTest: Before: " + before);
        System.out.println("DeleteTest: After: " + after);
    }


}
