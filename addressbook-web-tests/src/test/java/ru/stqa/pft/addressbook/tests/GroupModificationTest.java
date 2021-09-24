package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTest extends TestBase {

    //подготовка состояния для тестов, которые отвечают за модификацию
    @BeforeMethod
    public void enusrePreconditions(){
        app.goTo().onGroupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("Name", "test1", null));
        }
    }

    @Test
    public void testGroupModification() throws InterruptedException {

        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        GroupData group = new GroupData(before.get(index).getId(),"modif", "new", "new");

        app.group().modify(index, group);

        List<GroupData> after = app.group().list();
        before.remove(index);
        before.add(group);
        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }


}
