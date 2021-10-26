package ru.stqa.pft.mantis.tests;

import com.google.protobuf.ServiceException;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.IssueInfo;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {


    protected static  final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));


    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();

    }

    public boolean isIssueOpen(int issueId) throws ServiceException, MalformedURLException, javax.xml.rpc.ServiceException, RemoteException {
        IssueInfo issue = app.soapHelper().getIssue(issueId);

        String name = issue.getResolution().getName();
        System.out.println("issue.getResolution().getName --- " + name);

        return !name.equals("fixed");
    }

    public void skipIfNotFixed(int issueId) throws ServiceException, MalformedURLException, javax.xml.rpc.ServiceException, RemoteException {
        if (isIssueOpen(issueId)) {
            System.out.println("Ignored because of issue " + issueId);
            throw new SkipException("Ignored because of issue " + issueId);

        }
    }


}
