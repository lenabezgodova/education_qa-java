package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;


public class GroupDeletionTest extends TestBase {

    @Test
    public void testGroupDeletion() throws InterruptedException {
        app.getNavigationHelper().gotoGroupPage();

        int before = app.getGroupHelper().getGroupCount();

        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("Name", "test1", null));
        }
        Thread.sleep(5000);

        app.getGroupHelper().selectGroup(0);
        app.getGroupHelper().deteleSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().getGroupCount();

        System.out.println("before " + before + " :After: " + after);
        if (before == 0){
            Assert.assertEquals(after, before);
        } else {
            Assert.assertEquals(after, before-1);
        }

    }


}

