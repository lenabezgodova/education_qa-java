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

public class ApplicationManger {

    public WebDriver driver;
    String browser;
    private final Properties properties;


    public ApplicationManger(String browser) {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws InterruptedException, IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

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
        driver.get(properties.getProperty("web.baseUrl"));
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


}
