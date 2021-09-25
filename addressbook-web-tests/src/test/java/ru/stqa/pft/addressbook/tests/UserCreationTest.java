package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class UserCreationTest extends TestBase{

    @Test
    public void testCreateUser() throws Exception {
        app.goTo().pageHome();
        //Thread.sleep(5000);

        Set<UserData> before = app.contact().all();

        UserData user = new UserData().withFirstName("first name").withLastName("Zz-last name").withGroup("test1");
        app.contact().createNewUser(user, true);
        Set<UserData> after = app.contact().all();

        //проверка на размер - пока отличается
        Assert.assertEquals(after.size(), before.size() + 1);

        //сравниваем уже по-фамильно
        user.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());

        before.add(user);
        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(before, after);
        System.out.println("before ---> " + before);
        System.out.println("after  ---> " + after);
    }
}
