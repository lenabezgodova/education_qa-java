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


    public List<UserData> getListUsersWithInfo(){
        List<UserData> usersWithInfo = new ArrayList<UserData>(); //здесь сразу нельзя добавить значения - см.ниже GroupData - тип данных
        List<WebElement> elements = driver.findElements(By.name("tr"));

        for (WebElement element: elements){
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String username = cells.get(1).getText();
            String email = cells.get(2).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

            UserData user = new UserData().withId(id).withUsername(username).withEmail(email);
            usersWithInfo.add(user);
        }
        return usersWithInfo;
    }


}
