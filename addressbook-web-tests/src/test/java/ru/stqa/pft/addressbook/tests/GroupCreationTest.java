package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src\\test\\resources\\groups.csv")))){
            String line = reader.readLine();
            while (line != null) {
                String[] split = line.split(";");
                list.add(new Object[] {new GroupData().withGroupName(split[0]).withGroupHeader(split[1]).withGroupFooter(split[2])});
                line = reader.readLine();
            }
//        list.add(new Object[] {new GroupData().withGroupName("test1").withGroupHeader("header1").withGroupFooter("footer1")});
            return list.iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromXML() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src\\test\\resources\\groups.xml")))){
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(GroupData.class);
            List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
            return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validGroupsFromXML")
    public void testGroupCreation(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.db().groups();
        app.group().create(group);
        assertThat(app.group().getGroupCount(), equalTo(before.size() + 1));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        System.out.println("before --->" + before);
        System.out.println("after ----> " + after);
    }
}
