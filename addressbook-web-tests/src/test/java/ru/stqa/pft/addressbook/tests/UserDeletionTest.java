package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDeletionTest extends TestBase {

    @BeforeTest
    public void ensurePreconditions(){
        if (app.db().users().size() == 0){
            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withGroupName("Name").withGroupHeader("test1"));
            }
            app.goTo().pageHome();
            app.contact().createNewUser(new UserData().withFirstName("first name").withMiddleName("middle name")
                    .withLastName("last name").inGroup(app.db().groups().iterator().next()), true);
        }
    }

    @Test
    public void testDeletionGroup() throws InterruptedException {
        Users before = app.db().users();
        app.goTo().pageHome();

        UserData deletedUser = before.iterator().next();
        System.out.println("deletedUser ---> " + deletedUser);

        app.contact().delete(deletedUser);
        app.goTo().pageHome();
        Thread.sleep(3000);
        Users after = app.db().users();

        //проверка размера - пока они отличаются,новый элемнт никуда не добавляла\удаляла
        Assert.assertEquals(after.size(), before.size()-1);
        assertThat(after, equalTo(before.without(deletedUser)));
        assertThat(after.size(), equalTo(before.size()-1));

        System.out.println("DeleteTest: Before: " + before);
        System.out.println("DeleteTest: After: " + after);
    }

}
