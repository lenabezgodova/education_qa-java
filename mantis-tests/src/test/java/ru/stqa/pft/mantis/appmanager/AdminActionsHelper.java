package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class AdminActionsHelper extends HelperBase {

    public  AdminActionsHelper(ApplicationManager app){
        super(app);
    }

    public void loginAsAdmin() {
        driver.get(app.getProperty("web.baseUrl") + "login.php");
        typeValueInTheField(By.name("username"), app.getProperty("web.adminLogin"));
        clickOnTheElement(By.cssSelector("input[type='submit']"));
        typeValueInTheField(By.name("password"), app.getProperty("web.adminPassword"));
        clickOnTheElement(By.cssSelector("input[type='submit']"));
    }

    public void clickOnButtonResetPassword(){
        clickOnTheElement(By.cssSelector("input[value = 'Сбросить пароль']"));
    }

    public void goToManagementPage(){
        driver.get(app.getProperty("web.baseUrl") + "manage_user_page.php");
    }

    public void clickOnTheUser(String username){
        String selector = "//a[text()='" + username + "']"; //a[text()='jjjj']
        driver.findElement(By.xpath(selector)).click();
    }

}
