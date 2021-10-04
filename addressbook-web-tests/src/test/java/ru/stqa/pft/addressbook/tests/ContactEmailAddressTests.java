package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailAddressTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().pageHome();
        if (app.contact().all().size() == 0) {
            app.contact().createNewUser(new UserData()
                    .withFirstName("Elena").withLastName("Petrova")
                    .withMobile("89515002001")
                    .withHome("8940(900)1040")
                    .withEmailFirst("test@mail.ru")
                    .withEmailSecond("test2@nnn.ru")
                    .withAddress("Mühlenstraße 66")
                    .withGroup("test1"), true);
        }
    }

    @Test
    public void testContactEmails() throws Exception {
        app.goTo().pageHome();
        UserData user = app.contact().all().iterator().next();
        UserData contactInfoFromEditForm = app.contact().infoFromEditForm(user);
        System.out.println("user.getAddress() ----> "  + user.getAddress());
        System.out.println("contactInfoFromEditForm.getAddress() ---> " + contactInfoFromEditForm.getAddress());
        assertThat(user.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(user.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));

    }

    private String mergeEmails(UserData contact) {
        return Arrays.asList(contact.getEmailFirst(), contact.getEmailSecond(), contact.getEmailThird()).stream()
                .filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }



}
