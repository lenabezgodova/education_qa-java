package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class UserCreationTest extends TestBase{

    @DataProvider
    public Iterator<Object[]> validGroupsFromXML() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src\\test\\resources\\users.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xStream = new XStream();
        xStream.processAnnotations(UserData.class);
        List<UserData> users = (List<UserData>) xStream.fromXML(xml);
        return users.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validGroupsFromXML")
    public void testCreateUser(UserData user) {
        File photo = new File("src/test/resources/photoName.jpg"); //относительный путь

        app.goTo().pageHome();
        Users before = app.db().users();
        //UserData user = new UserData().withFirstName("first name").withLastName("Zz-last name").withGroup("test1");
        app.contact().createNewUser(user, true);
        app.goTo().pageHome();
        assertThat(app.contact().getUserCount(), equalTo(before.size() + 1));
        Users after = app.db().users();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(user.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        System.out.println("before ---> " + before);
        System.out.println("after  ---> " + after);
    }

}
