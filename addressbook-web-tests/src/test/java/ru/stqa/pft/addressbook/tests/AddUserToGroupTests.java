package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddUserToGroupTests extends TestBase {

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
    void addUserToGroupTest() throws InterruptedException {
        //выбираю пользователя, которого буду добавлять в группу
        UserData user = app.db().users().iterator().next();
        System.out.println("user ---> " + user);
        //нужен список всех групп
        Groups allGroups = app.db().groups();
        //нужен список групп, в которые пользовательуже добавлен
        Groups userGroups = user.getGroups();
        System.out.println("userGroups ---> " + userGroups);

        GroupData groupToAdd = getGroupDataToAdd(allGroups, userGroups);
        System.out.println("groupToAdd ---> " + groupToAdd);

        app.contact().addUserToGroup(user, groupToAdd);

        Groups userGroupsAfter = user.getGroups();
        System.out.println("userGroupsAfter ---> " + userGroupsAfter);

        assertThat(userGroupsAfter.size(), equalTo(user.getGroups().size() + 1));

        //need to find max ID in groups after a new group creation
        //assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        int maxIdGroups = userGroupsAfter.stream()
                .mapToInt(g -> g.getId())
                .max()
                .getAsInt();
        assertThat(userGroupsAfter, equalTo(user.getGroups()
                .withAdded(groupToAdd.withId(maxIdGroups))));

    }


    private GroupData getGroupDataToAdd(Groups allGroups, Groups userGroups) {
        GroupData groupToAdd;
        for (GroupData x : userGroups) {
            if (allGroups.contains(x)) {
                allGroups.remove(x);
            }
        }
        if (allGroups.isEmpty()) {
            app.goTo().groupPage();
            groupToAdd  = new GroupData().withGroupName("Name").withGroupHeader("test1");
            app.group().create(groupToAdd);
        } else {
            groupToAdd = allGroups.iterator().next();
        }
        return groupToAdd;
    }


}
