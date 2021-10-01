package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupDeletionTest extends TestBase {

    //подготовка состояния для тестов, которые отвечают за удаление элемента
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withGroupName("Name").withGroupHeader("test1"));
        }
    }

    @Test
    public void testGroupDeletion() throws InterruptedException {
        Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        System.out.println("deletedGroup ----> " + deletedGroup);

        //int index = before.size() - 1;
        app.group().delete(deletedGroup);
        Groups after = app.group().all();
        //проверка размера - пока они отличаются,новый элемнт никуда не добавляла\удаляла
        Assert.assertEquals(after.size(),  before.size() - 1);
        assertThat(after, equalTo(before.without(deletedGroup)));
        assertThat(after.size(), equalTo(before.size()-1));

        System.out.println("after ---> " + after);
        }



}

