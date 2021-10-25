package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.mantis.model.UserData;

import java.util.ArrayList;
import java.util.List;

public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager app) {
        super(app);
    }


    private static final String ACCESS_LEVEL_Admin = "администратор";

    public List<UserData> getListUsersWithInfo(){
        List<UserData> usersWithInfo = new ArrayList<UserData>(); //здесь сразу нельзя добавить значения - см.ниже GroupData - тип данных
        List<WebElement> elements = driver.findElements(By.cssSelector("table[class = 'table table-striped table-bordered table-condensed table-hover'] tbody tr"));

        for (WebElement element: elements){
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String usernameLink = cells.get(0).getText();
            //String username = cells.get(1).getText();
            String email = cells.get(2).getText();
            String accessType = cells.get(3).getText();
            if (!accessType.equals(ACCESS_LEVEL_Admin)){
                UserData user = new UserData().withUsername(usernameLink).withEmail(email);
                usersWithInfo.add(user);
            }

        }
        return usersWithInfo;
    }


}
