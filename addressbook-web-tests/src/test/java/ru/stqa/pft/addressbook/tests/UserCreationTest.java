package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;


import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class UserCreationTest extends TestBase{

    @Test
    public void testCreateUser() {
        File photo = new File("src/test/resources/photoName.jpg"); //относительный путь

        app.goTo().pageHome();
        Users before = app.contact().all();
        UserData user = new UserData().withFirstName("first name").withLastName("Zz-last name").withGroup("test1");
        app.contact().createNewUser(user, true);
        app.goTo().pageHome();
        assertThat(app.contact().getUserCount(), equalTo(before.size() + 1));
        Users after = app.contact().all();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(user.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        System.out.println("before ---> " + before);
        System.out.println("after  ---> " + after);
    }

//    @Test
//    public void testCurrentDir(){
//        File currentDir = new File(".");
//        currentDir.getAbsolutePath();
//        currentDir.getPath();
//        System.out.println("before ---> " + currentDir.getAbsolutePath());
//        System.out.println("after  ---> " + currentDir.getPath());
//        File photo = new File("src/test/resources/photoName.jpg");
//        System.out.println("photo abs path ---> " + photo.getAbsolutePath());
//        System.out.println("exists  ---> " + photo.exists());
//
//    }
}
