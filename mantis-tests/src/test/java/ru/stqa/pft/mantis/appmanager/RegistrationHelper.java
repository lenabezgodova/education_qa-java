package ru.stqa.pft.mantis.appmanager;


import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase{


    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        driver.get(app.getProperty("web.baseUrl") + "signup_page.php");

        typeValueInTheField(By.name("username"), username);
        typeValueInTheField(By.name("email"), email);
        clickOnTheElement(By.cssSelector("input[value = 'Зарегистрироваться']"));

    }

    public void finish(String confirmationLink, String user, String password) {
        driver.get(confirmationLink);
        typeValueInTheField(By.name("realname"), user);
        typeValueInTheField(By.name("password"), password);
        typeValueInTheField(By.name("password_confirm"), password);
        clickOnTheElement(By.cssSelector("span.submit-button button"));
    }
}
