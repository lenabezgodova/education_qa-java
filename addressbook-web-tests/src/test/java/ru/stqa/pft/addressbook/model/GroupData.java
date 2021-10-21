package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("group")
@Entity
@Table(name = "group_list")
public class GroupData {

    @XStreamOmitField
    @Id
    @Column(name = "group_id")
    private int id = Integer.MAX_VALUE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupData groupData = (GroupData) o;
        return id == groupData.id &&
                Objects.equals(groupName, groupData.groupName) &&
                Objects.equals(groupHeader, groupData.groupHeader) &&
                Objects.equals(groupFooter, groupData.groupFooter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupName, groupHeader, groupFooter);
    }

    @Column(name = "group_name")
    private String groupName;
    @Column(name = "group_header")
    @Type(type = "text")
    private String groupHeader;
    @Column(name = "group_footer")
    @Type(type = "text")
    private String groupFooter;

//    public GroupData(String groupName, String groupHeader, String groupFooter) {
//        this.id = 0;
//        this.groupName = groupName;
//        this.groupHeader = groupHeader;
//        this.groupFooter = groupFooter;
//    }
//
//    public GroupData(int id, String groupName, String groupHeader, String groupFooter) {
//        this.id = id;
//        this.groupName = groupName;
//        this.groupHeader = groupHeader;
//        this.groupFooter = groupFooter;
//    }


    @Override
    public String toString() {
        return "GroupData{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", groupHeader='" + groupHeader + '\'' +
                ", groupFooter='" + groupFooter + '\'' +
                '}';
    }

    @ManyToMany(mappedBy = "groups")
    private Set<UserData> users = new HashSet<UserData>();


    public Users getUsers() {
        return new Users(users);
    }

    public void setUsers(Set<UserData> users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public GroupData withId(int id) {
        this.id = id;
        return this;
    }

    public GroupData withGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public GroupData withGroupHeader(String groupHeader) {
        this.groupHeader = groupHeader;
        return this;
    }

    public GroupData withGroupFooter(String groupFooter) {
        this.groupFooter = groupFooter;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupHeader() {
        return groupHeader;
    }

    public String getGroupFooter() {
        return groupFooter;
    }

}
