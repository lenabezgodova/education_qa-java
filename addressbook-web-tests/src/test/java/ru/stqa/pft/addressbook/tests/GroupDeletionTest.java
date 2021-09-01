package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;


public class GroupDeletionTest extends TestBase{

    @Test
    public void testGroupDeletion() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deteleSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }


}

