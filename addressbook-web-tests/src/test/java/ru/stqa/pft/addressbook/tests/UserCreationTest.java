package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class UserCreationTest extends TestBase{

    @Test
    public void testCreateUser() throws Exception {
        app.goTo().pageHome();

        Users before = app.contact().all();

        UserData user = new UserData().withFirstName("first name").withLastName("Zz-last name").withGroup("test1");
        app.contact().createNewUser(user, true);
        Users after = app.contact().all();

        //проверка на размер - пока отличается
        System.out.println("before ---> " + before.size());
        System.out.println("after  ---> " + after.size());
        //Assert.assertEquals(after.size(), before.size() + 1);
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(before.withAdded(user.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        System.out.println("before ---> " + before);
        System.out.println("after  ---> " + after);
    }
}
