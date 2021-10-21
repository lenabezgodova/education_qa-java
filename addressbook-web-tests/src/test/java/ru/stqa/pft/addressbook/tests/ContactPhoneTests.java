package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

//    @BeforeMethod
//    public void ensurePreconditions() {
//        app.goTo().pageHome();
//        if (app.contact().all().size() == 0) {
//            app.contact().createNewUser(new UserData()
//                    .withFirstName("Elena").withLastName("Petrova")
//                    .withMobile("89515002001")
//                    .withHome("8940(900)1040")
//                    .withGroup("test1"), true);
//        }
//    }


    @Test
    public void testContactPhones() throws Exception {
        app.goTo().pageHome();
        UserData user = app.contact().all().iterator().next();
        UserData contactInfoFromEditForm = app.contact().infoFromEditForm(user);
        //assertThat(user.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
        assertThat(user.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(UserData contact) {
        return Arrays.asList(contact.getHome(), contact.getMobile(), contact.getWorkPhone()).stream()
                .filter((s) -> ! s.equals(""))
                .map(this::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public String cleaned (String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

}
