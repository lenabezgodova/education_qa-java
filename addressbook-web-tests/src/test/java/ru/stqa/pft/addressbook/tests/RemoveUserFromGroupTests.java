package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeTest;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

public class RemoveUserFromGroupTests extends TestBase {

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



}
