package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("users")
@Entity
@Table(name = "addressbook")
public class UserData {

    @XStreamOmitField
    @Id
    private int id = Integer.MAX_VALUE;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "middlename")
    private String middleName;
    @Column(name = "lastname")
    private String lastName;
    @Transient
    private String group;
    @Column(name = "home")
    @Type(type = "text")
    private String home;
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobile;
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    @Transient
    private String allPhones;

    @Column(name = "email")
    @Type(type = "text")
    private String emailFirst;
    @Column(name = "email2")
    @Type(type = "text")
    private String emailSecond;
    @Column(name = "email3")
    @Type(type = "text")
    private String emailThird;
    @Transient
    private String allEmails;

    @Column(name = "address")
    @Type(type = "text")
    private String address;
    @Transient
    private File photo;


//    public UserData(String firstName, String middleName, String lastName, String group) {
//        this.id = 0;
//        this.firstName = firstName;
//        this.middleName = middleName;
//        this.lastName = lastName;
//        this.group = group;
//    }
//
//    public UserData(int id, String firstName, String middleName, String lastName, String group) {
//        this.id = id;
//        this.firstName = firstName;
//        this.middleName = middleName;
//        this.lastName = lastName;
//        this.group = group;
//    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGroup() {
        return group;
    }

    public int getId() {
        return id;
    }

    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    public UserData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public UserData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserData withGroup(String group) {
        this.group = group;
        return this;
    }

    public String getHome() {
        return home;
    }

    public UserData withHome(String home) {
        this.home = home;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public UserData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public UserData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public UserData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getEmailFirst() {
        return emailFirst;
    }

    public UserData withEmailFirst(String emailFirst) {
        this.emailFirst = emailFirst;
        return this;
    }

    public String getEmailSecond() {
        return emailSecond;
    }

    public UserData withEmailSecond(String emailSecond) {
        this.emailSecond = emailSecond;
        return this;
    }

    public String getEmailThird() {
        return emailThird;
    }

    public UserData withEmailThird(String emailThird) {
        this.emailThird = emailThird;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public UserData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserData withAddress(String address) {
        this.address = address;
        return this;
    }

    public File getPhoto() {
        return photo;
    }

    public UserData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", group='" + group + '\'' +
                ", home='" + home + '\'' +
                ", mobile='" + mobile + '\'' +
                ", workPhone='" + workPhone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return id == userData.id &&
                Objects.equals(firstName, userData.firstName) &&
                Objects.equals(lastName, userData.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
