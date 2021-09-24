package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import ru.stqa.pft.addressbook.model.LoginData;

import java.util.concurrent.TimeUnit;

public class ApplicationManger {

    public WebDriver driver;
    String browser;

    private GroupHelper groupHelper;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;

    public ApplicationManger(String browser) {
        this.browser = browser;
    }


    public void init() throws InterruptedException {

        if (browser.equals(BrowserType.FIREFOX)){
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\lenab\\IdeaProjects\\education_qa-java\\addressbook-web-tests\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();

        } else  if (browser.equals(BrowserType.CHROME)){
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\lenab\\IdeaProjects\\education_qa-java\\addressbook-web-tests\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equals(BrowserType.EDGE)){
            driver = new EdgeDriver();
        }


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

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo(){
        return navigationHelper;
    }

    public ContactHelper contact(){
        return contactHelper;
    }
}
