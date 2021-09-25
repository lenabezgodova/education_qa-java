package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupCreationTest extends TestBase {


    @Test
    public void testGroupCreation() throws Exception {
        app.goTo().groupPage();

        Set<GroupData> before = app.group().all();

        GroupData group = new GroupData().withGroupName("test1").withGroupHeader("header").withGroupFooter("footer");
        app.group().create(group);

        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        // another realization - how to find max value
//        int maxLambda = after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId();
//        System.out.println("maxLambda: " + maxLambda );

        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);
        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(before, after);
        System.out.println("before --->" + before);
        System.out.println("after ----> " + after);

    }

}
