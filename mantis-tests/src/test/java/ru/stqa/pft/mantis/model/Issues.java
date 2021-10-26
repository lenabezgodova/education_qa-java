package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Issues extends ForwardingSet<IssueInfo> {

    private Set<IssueInfo> delegateObject;

    public Issues(ru.stqa.pft.mantis.model.Issues issues) {
        this.delegateObject = new HashSet<IssueInfo>(issues.delegateObject);
    }

    public Issues() {
        this.delegateObject = new HashSet<IssueInfo>();
    }

    public Issues(Collection<IssueInfo> issues) { //см 7.4. 06.30
        this.delegateObject = new HashSet<IssueInfo>(issues);
    }


    @Override //обязательный метод библиотеки ForwardingSet
    protected Set<IssueInfo> delegate() {
        return delegateObject;
    }

}
