package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import com.google.protobuf.ServiceException;
import ru.stqa.pft.mantis.model.IssueInfo;
import ru.stqa.pft.mantis.model.Project;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

    private ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        this.app = app;
    }

    /* public Set<IssueInfo> getIssues() throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    IssueData[] issues = mc.mc_projects_get_user_accessible("administrator", "root");
    return Arrays.asList(issues).stream()
            .map((p) -> new Project().setId(p.getId()).setName(p.getSummary())
            .collect(Collectors.toSet());
      }
  */



    public Set<Project> getProjects() throws MalformedURLException, RemoteException, javax.xml.rpc.ServiceException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");

        return Arrays.asList(projects).stream()
                .map((p) -> new Project().setId(p.getId()).setName(p.getName()))
                .collect(Collectors.toSet());
    }

    private MantisConnectPortType getMantisConnect() throws MalformedURLException, javax.xml.rpc.ServiceException {
        MantisConnectPortType mc = new MantisConnectLocator()
                .getMantisConnectPort(new URL("http://localhost/mantisbt-2.25.2/api/soap/mantisconnect.php"));

        return mc;
    }



    public IssueInfo addIssue(IssueInfo issue) throws MalformedURLException, RemoteException, javax.xml.rpc.ServiceException {
        MantisConnectPortType mc = getMantisConnect();

        String[] categories =
                mc.mc_project_get_categories("administrator", "root", issue.getProject().getId());


        IssueData issueData = new IssueData(); //Класс из подключенной библиотеки Мантисс (9.3.) External Libraries
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        //issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()).intValue(), issue.getProject().getName()));
        issueData.setProject(new ObjectRef(issue.getProject().getId(), issue.getProject().getName()));
        issueData.setCategory(categories[0]);// берем первую из списков категорий данного проекта
        BigInteger issueId = mc.mc_issue_add("administrator", "root", issueData);


        IssueData createdIssueData = mc.mc_issue_get("administrator", "root", issueId);



        //issueData.setProject(new ObjectRef(issue.getProject().getId().intValue(), issue.getProject().getName()));
        //issueData.setProject(new IssueInfo(issue.getProject().getId().intValue(), issue.getProject().getName()));





        /*return new IssueInfo().setId(createdIssueData.getId().intValue()).setSummary(createdIssueData.getSummary())
            .setDescription(createdIssueData.getDescription())
            .setProject(issueData.getProject().getId().intValue());
            //.setProject(new Project().setId(createdIssueData.getProject().getId())
            .setName(createdIssueData.getProject().getName()));*/

        return new IssueInfo().setId(createdIssueData.getId().intValue())
                .setSummary(createdIssueData.getSummary())
                .setDescription(createdIssueData.getDescription()).setProject(new Project()
                        .setId(createdIssueData.getProject().getId())
                        .setName(createdIssueData.getProject().getName()));
    }


    public IssueInfo getIssue(int issueId) throws MalformedURLException, ServiceException, RemoteException, javax.xml.rpc.ServiceException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData issueData = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));

        IssueInfo issue = new IssueInfo();
        issue.setSummary(issueData.getSummary()).setDescription(issueData.getDescription()).
                setId(issueData.getId().intValue()).
                setProject(new Project(issueData.getProject().getId(), issueData.getProject().getName())).
                setResolution(issueData.getResolution());
    /*issue.setSummary(issueData.getSummary()).setDescription(issueData.getDescription())
            .setId(issueData.getId())
            .setProject(issueData.getProject().getId().intValue())
            //setProject(new Project(issueData.getProject().getId(), issueData.getProject().getName())).
            .setResolution(issueData.getResolution().getName());*/
        return issue;
    }
}
