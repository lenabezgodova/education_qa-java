package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManger;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {


    protected static  final ApplicationManger app = new ApplicationManger(System.getProperty("browser", BrowserType.CHROME));
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();

    }

    @BeforeMethod
    public void logTestStart(Method method, Object[] param){
        logger.info("Start test - " + method.getName() + " with param " + Arrays.asList(param));

    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method method){
        logger.info("Stop test - " + method.getName());
    }

}
