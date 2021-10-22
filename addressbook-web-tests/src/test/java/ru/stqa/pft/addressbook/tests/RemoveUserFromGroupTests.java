package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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

    // todo найти юзера с пользователями. Получить список его групп. Удалить все группы!
    // todo additional task

    @Test
    public void removeUserFromGroupTest() throws InterruptedException {

        UserData modifiedUser = app.db().users().iterator().next();
        Groups groupsBefore = modifiedUser.getGroups();
        Groups allGroups = app.db().groups();
        GroupData deletedGroup;
        app.goTo().pageHome();

        if (modifiedUser.getGroups().size() == 0){
            System.out.println(" ----------   I am here -------------");
            //need to add a group
            deletedGroup = allGroups.iterator().next();
            app.contact().addUserToGroup(modifiedUser, deletedGroup);

        } else {
            deletedGroup = modifiedUser.getGroups().iterator().next();
        }

        System.out.println("modifiedUser.getGroups: ------- " + modifiedUser.getGroups());
        System.out.println("deletedGroup: ------- " + deletedGroup);
        app.contact().removeFromGroup(modifiedUser, deletedGroup);
        UserData afterDeletedGroup = app.db().users().getUserInfo(modifiedUser);
        Groups groupsAfter = afterDeletedGroup.getGroups();
        System.out.println("groupsAfter: ------- " + groupsAfter);

        assertThat(groupsAfter.size(), equalTo(groupsBefore.size()-1));
        assertThat(groupsAfter, equalTo(groupsBefore.without(deletedGroup)));

    }

}
