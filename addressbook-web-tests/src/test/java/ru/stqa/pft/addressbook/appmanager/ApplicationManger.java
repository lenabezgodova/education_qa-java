package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManger {

    public WebDriver driver;
    String browser;
    private final Properties properties;

    private GroupHelper groupHelper;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;
    private DbHelper dbHelper;

    public ApplicationManger(String browser) {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws InterruptedException, IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        dbHelper = new DbHelper();
        if ("".equals(properties.getProperty("selenium.server"))){
            if (browser.equals(BrowserType.FIREFOX)){
                System.setProperty("webdriver.gecko.driver", "C:\\Users\\lenab\\IdeaProjects\\education_qa-java\\addressbook-web-tests\\drivers\\geckodriver.exe");
                driver = new FirefoxDriver();

            } else  if (browser.equals(BrowserType.CHROME)){
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\lenab\\IdeaProjects\\education_qa-java\\addressbook-web-tests\\drivers\\chromedriver.exe");
                driver = new ChromeDriver();
            } else if (browser.equals(BrowserType.EDGE)){
                driver = new EdgeDriver();
            }
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            driver = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
        }




        Thread.sleep(5000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(properties.getProperty("web.baseUrl"));
        groupHelper = new GroupHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        contactHelper = new ContactHelper(driver);
        sessionHelper = new SessionHelper(driver);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
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

    public DbHelper db(){
        return dbHelper;
    }
}
