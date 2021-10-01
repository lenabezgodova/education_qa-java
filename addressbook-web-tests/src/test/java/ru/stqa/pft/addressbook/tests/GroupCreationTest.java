package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTest extends TestBase {


    @Test
    public void testGroupCreation() throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withGroupName("test1");
        app.group().create(group);
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        // another realization - how to find max value
//        int maxLambda = after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId();
//        System.out.println("maxLambda: " + maxLambda );

        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        //Assert.assertEquals(before, after);
        System.out.println("before --->" + before);
        System.out.println("after ----> " + after);

    }

}
