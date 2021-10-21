package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManger;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import javax.swing.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {


    protected static  final ApplicationManger app = new ApplicationManger(System.getProperty("browser", BrowserType.FIREFOX));
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

    public void verifyGroupListInUI(){
        Groups dbGroups = app.db().groups();
        System.out.println("dbGroups---> " + dbGroups);

        Groups uiGroups = app.group().all();
        System.out.println("uiGroups---> " + uiGroups);

        Set<GroupData> dbGroups2 = dbGroups.stream().map((g) -> new GroupData().withId(g.getId()).withGroupName(g.getGroupName())).collect(Collectors.toSet());
        System.out.println("dbGroups2---> " + dbGroups2);

        assertThat(uiGroups, equalTo(dbGroups.stream().map((g) -> new GroupData().withId(g.getId()).withGroupName(g.getGroupName()))
                .collect(Collectors.toSet())));
    }

}
