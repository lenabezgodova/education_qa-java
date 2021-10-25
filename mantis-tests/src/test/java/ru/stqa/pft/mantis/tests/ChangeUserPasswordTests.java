package ru.stqa.pft.mantis.tests;

import org.hibernate.service.spi.ServiceException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangeUserPasswordTests extends TestBase{

    UserData userForResetPassword;
    String newPassword = "paSSword_new";

    // TODO вынести метод findConfirmationLink - дублируется в двух тестах

    @BeforeMethod
    public void preconditions(){

        app.mail().start();

        app.adminActionsHelper().loginAsAdmin();
        app.adminActionsHelper().goToManagementPage();
        List<UserData> usersWithInfo = app.userHelper().getListUsersWithInfo();
        System.out.println("usersWithInfo --- " + usersWithInfo);

        userForResetPassword = usersWithInfo.iterator().next();

        app.adminActionsHelper().clickOnTheUser(userForResetPassword.getUsername());
        app.adminActionsHelper().clickOnButtonResetPassword();
    }

    @Test
    public void testChangePass() throws IOException, ServiceException {

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);//ждем 10 секунд
        String confirmationLink = findConfirmationLink(mailMessages, userForResetPassword.getEmail());
        System.out.println("confirmationLink ---> " + confirmationLink);
        app.registration().finish(confirmationLink, userForResetPassword.getUsername(), newPassword);

        HttpSession session = app.newSession();
        assertTrue(session.loginUser(userForResetPassword.getUsername(), newPassword));
        assertTrue(session.isLoggedInAs(userForResetPassword.getUsername()));
    }


    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }



    @AfterMethod(alwaysRun = true)
    public void postconditions(){
        app.mail().stop();
    }
}
