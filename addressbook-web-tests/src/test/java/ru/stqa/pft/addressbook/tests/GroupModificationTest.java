package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupModificationTest extends TestBase {

    //подготовка состояния для тестов, которые отвечают за модификацию
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withGroupName("Name").withGroupHeader("test1"));
        }
    }

    @Test
    public void testGroupModification() throws InterruptedException {

        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        System.out.println("modifiedGroup ----> " + modifiedGroup);
        //int index = before.size() - 1;
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withGroupName("modif")
                .withGroupHeader("new").withGroupFooter("new");
        app.group().modify(group);
        Set<GroupData> after = app.group().all();
        before.remove(modifiedGroup);
        before.add(group);
        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(before, after);
        System.out.println("before ---> " + before);
        System.out.println("after ---> " + after);
    }


}
