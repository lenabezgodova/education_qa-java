package ru.stqa.pft.addressbook;

public class UserData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String title;
    private final String companyName;
    private final String addressMain;
    private final String emailMain;
    private final String fax;
    private final String mobile;
    private final String homePage;

    public UserData(String firstName, String middleName, String lastName, String nickName, String title, String companyName, String addressMain, String emailMain, String fax, String mobile, String homePage) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.title = title;
        this.companyName = companyName;
        this.addressMain = addressMain;
        this.emailMain = emailMain;
        this.fax = fax;
        this.mobile = mobile;
        this.homePage = homePage;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getTitle() {
        return title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddressMain() {
        return addressMain;
    }

    public String getEmailMain() {
        return emailMain;
    }

    public String getFax() {
        return fax;
    }

    public String getMobile() {
        return mobile;
    }

    public String getHomePage() {
        return homePage;
    }
}
