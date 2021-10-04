package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhones() throws Exception {
        app.goTo().pageHome();
        UserData user = app.contact().all().iterator().next();
        UserData contactInfoFromEditForm = app.contact().infoFromEditForm(user);

        System.out.println("user.getAllPhones()"  + user.getAllPhones());
        System.out.println("mergePhones(contactInfoFromEditForm) ---> " + mergePhones(contactInfoFromEditForm));

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
