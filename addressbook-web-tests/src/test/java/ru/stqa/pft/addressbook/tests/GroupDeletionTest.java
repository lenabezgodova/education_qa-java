package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class GroupDeletionTest extends TestBase {

    //подготовка состояния для тестов, которые отвечают за удаление элемента
    @BeforeMethod
    public void enusrePreconditions(){
        app.goTo().onGroupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withGroupName("Name").withGroupHeader("test1"));
        }
    }

    @Test
    public void testGroupDeletion() throws InterruptedException {
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;

        app.group().delete(index);
        List<GroupData> after = app.group().list();

        //проверка размера - пока они отличаются,новый элемнт никуда не добавляла\удаляла
        Assert.assertEquals(after.size(),  before.size() - 1);
        before.remove(index);
        Assert.assertEquals(before, after);

        }



}

