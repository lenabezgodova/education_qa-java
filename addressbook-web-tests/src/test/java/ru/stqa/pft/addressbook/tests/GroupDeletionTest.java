package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupDeletionTest extends TestBase {

    //подготовка состояния для тестов, которые отвечают за удаление элемента
    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withGroupName("Name").withGroupHeader("test1"));
            }
    }

    @Test
    public void testGroupDeletion() throws InterruptedException {
        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        System.out.println("deletedGroup ----> " + deletedGroup);

        //int index = before.size() - 1;
        app.goTo().groupPage();
        app.group().delete(deletedGroup);
        assertThat(app.group().getGroupCount(), equalTo(before.size() - 1));
        Groups after = app.db().groups();
        //проверка размера - пока они отличаются,новый элемнт никуда не добавляла\удаляла
        assertThat(after, equalTo(before.without(deletedGroup)));
        assertThat(after.size(), equalTo(before.size()-1));

        System.out.println("after ---> " + after);
        }


}

