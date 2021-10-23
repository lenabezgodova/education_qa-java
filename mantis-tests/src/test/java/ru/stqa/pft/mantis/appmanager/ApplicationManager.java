package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private WebDriver driver;
    private String browser;
    private final Properties properties;

    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mail;


    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws InterruptedException, IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        //Thread.sleep(2000);

    }

    public void stop() {
        if (driver != null){
            driver.quit();
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public HttpSession newSession(){
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null){
            registrationHelper =  new RegistrationHelper(this);
        }
        return registrationHelper;

    }

    public FtpHelper ftp() {
        if (ftp == null){
            ftp =  new FtpHelper(this);
        }
        return ftp;
    }

    public MailHelper mail() {
        if (mail == null){
            mail =  new MailHelper(this);
        }
        return mail;
    }

    public WebDriver getDriver(){
        if(driver == null){
            if (browser.equals(BrowserType.FIREFOX)){
                System.setProperty("webdriver.gecko.driver", "C:\\Users\\lenab\\IdeaProjects\\education_qa-java\\mantis-tests\\drivers\\geckodriver.exe");
                driver = new FirefoxDriver();

            } else  if (browser.equals(BrowserType.CHROME)){
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\lenab\\IdeaProjects\\education_qa-java\\mantis-tests\\drivers\\chromedriver.exe");
                driver = new ChromeDriver();
            } else if (browser.equals(BrowserType.EDGE)){
                driver = new EdgeDriver();
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.get(properties.getProperty("web.baseUrl"));
        }

        return driver;
    }
}
