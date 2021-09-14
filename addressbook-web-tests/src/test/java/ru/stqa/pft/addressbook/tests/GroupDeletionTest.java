package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;


public class GroupDeletionTest extends TestBase {

    @Test
    public void testGroupDeletion() throws InterruptedException {
        app.getNavigationHelper().gotoGroupPage();

        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("Name", "test1", null));
        }
        Thread.sleep(5000);


        app.getGroupHelper().selectGroup();
        Thread.sleep(5000);
        app.getGroupHelper().deteleSelectedGroups();
        Thread.sleep(5000);
        app.getGroupHelper().returnToGroupPage();
    }


}

