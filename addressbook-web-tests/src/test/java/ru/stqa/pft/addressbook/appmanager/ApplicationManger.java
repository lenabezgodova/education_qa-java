package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.LoginData;

import java.util.concurrent.TimeUnit;

public class ApplicationManger {

    public WebDriver driver;

    private GroupHelper groupHelper;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;

    public void init() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\lenab\\IdeaProjects\\education_qa-java\\addressbook-web-tests\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        Thread.sleep(5000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        contactHelper = new ContactHelper(driver);
        sessionHelper = new SessionHelper(driver);
        sessionHelper.login(new LoginData("admin", "secret"));
    }

    public void stop() {
        driver.quit();
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper(){
        return navigationHelper;
    }

    public ContactHelper getContactHelper(){
        return contactHelper;
    }
}