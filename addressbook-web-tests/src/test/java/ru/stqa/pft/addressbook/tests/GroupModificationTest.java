package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTest extends TestBase {

    //подготовка состояния для тестов, которые отвечают за модификацию
    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withGroupName("Name").withGroupHeader("test1"));
            }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        System.out.println("modifiedGroup ----> " + modifiedGroup);
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withGroupName("modif")
                .withGroupHeader("new").withGroupFooter("new");
        app.goTo().groupPage();
        app.group().modify(group);
        assertThat(app.group().getGroupCount(), equalTo(before.size()));
        Groups after = app.db().groups();

        //Assert.assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
        verifyGroupListInUI();
    }


}
