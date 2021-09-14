package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupCreationTest extends TestBase {


    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();

        List<GroupData> before = app.getGroupHelper().gotoGroupPage();

        app.getGroupHelper().createGroup(new GroupData("Name", "test1", null));

        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before.size() + 1);

        Thread.sleep(5000);
        app.getGroupHelper().goLogOut("Logout");
    }

}
