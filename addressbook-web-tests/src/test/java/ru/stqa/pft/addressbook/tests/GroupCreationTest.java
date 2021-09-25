package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTest extends TestBase {


    @Test
    public void testGroupCreation() throws Exception {
        app.goTo().groupPage();

        List<GroupData> before = app.group().list();

        GroupData group = new GroupData().withGroupName("test1").withGroupHeader("header").withGroupFooter("footer");
        app.group().create(group);

        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() + 1);


        int max = 0;

        for (GroupData g: after){
            if (g.getId() > max){
                max = g.getId();
            }
        }

        // another realization - how to find max value
        int maxLambda = after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId();
        System.out.println("maxLambda: " + maxLambda );

        group.withId(max);
        before.add(group);
        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        Thread.sleep(3000);
    }

}
