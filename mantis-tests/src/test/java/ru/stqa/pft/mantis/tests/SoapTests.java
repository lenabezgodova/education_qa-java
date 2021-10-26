package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import com.google.protobuf.ServiceException;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.IssueInfo;
import ru.stqa.pft.mantis.model.Project;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase {

    long now = System.currentTimeMillis();

    @Test(enabled = true)
    public void testGetProjects1() throws MalformedURLException, RemoteException, javax.xml.rpc.ServiceException {
        MantisConnectPortType mc = new MantisConnectLocator()
                .getMantisConnectPort(new URL("http://localhost/mantisbt-2.25.2/api/soap/mantisconnect.php"));
        ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
        System.out.println(projects.length);
        for (ProjectData project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test(enabled = true)
    public void testGetProjects2() throws MalformedURLException, RemoteException, javax.xml.rpc.ServiceException {
        Set<Project> projects = app.soapHelper().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test(enabled = true)
    public void testCreateIssue() throws MalformedURLException, RemoteException, javax.xml.rpc.ServiceException {
        Set<Project> projects = app.soapHelper().getProjects();
        IssueInfo issue = new IssueInfo().setSummary(String.format("Test issue_%s", now))
                .setDescription("Test issue description")
                .setProject(projects.iterator().next());

        IssueInfo createdIssue = app.soapHelper().addIssue(issue);
        System.out.println("createdIssue ---> " + createdIssue);

        Assert.assertEquals(issue.getSummary(), createdIssue.getSummary());
    }

}
