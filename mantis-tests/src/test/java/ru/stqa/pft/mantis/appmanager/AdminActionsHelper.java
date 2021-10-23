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
        // link to the page http://localhost/mantisbt-2.25.2/manage_user_page.php
        driver.get(app.getProperty("web.baseUrl") + "manage_user_page.php");
    }

    public void clickOnTheUser(String username){
       // <a href="manage_user_edit_page.php?user_id=7">user1635020592664</a>
        // xpath //*[@id="main-container"]/div[2]/div[2]/div/div/div[4]/div[2]/div[2]/div/table/tbody/tr[6]/td[1]/a
    }

}
