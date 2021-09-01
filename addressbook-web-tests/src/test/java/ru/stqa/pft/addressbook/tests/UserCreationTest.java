package ru.stqa.pft.addressbook.tests;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.LoginData;
import ru.stqa.pft.addressbook.model.UserData;


public class UserCreationTest {


    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\lenab\\IdeaProjects\\education_qa-java\\addressbook-web-tests\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        Thread.sleep(5000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://localhost/addressbook/");
        login(new LoginData("admin", "secret"));
    }

    @Test
    public void testUntitledTestCase() throws Exception {
        initUserCreation();
        fullUserCreationForm(new UserData("first name", "middle name", "last name", "nick name", "title", "company", "address", "email1@mail.ru", "9109101010", "910 910 10 10", "https://ru.stackoverflow.com/"));
        workWithDropDownBox();
        submitUserCreation();
        gotoHomePage();
    }

    private void workWithDropDownBox() {
        driver.findElement(By.name("bday")).click();
        new Select(driver.findElement(By.name("bday"))).selectByVisibleText("2");
        driver.findElement(By.name("bmonth")).click();
        new Select(driver.findElement(By.name("bmonth"))).selectByVisibleText("June");
    }

    private void gotoHomePage() {
        driver.findElement(By.linkText("home page")).click();
    }

    private void submitUserCreation() {
        driver.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }


    private void fullUserCreationForm(UserData userData) {
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys(userData.getFirstName());
        driver.findElement(By.name("middlename")).click();
        driver.findElement(By.name("middlename")).clear();
        driver.findElement(By.name("middlename")).sendKeys(userData.getMiddleName());
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys(userData.getLastName());
        driver.findElement(By.name("nickname")).click();
        driver.findElement(By.name("nickname")).clear();
        driver.findElement(By.name("nickname")).sendKeys(userData.getNickName());
//        driver.findElement(By.name("photo")).click();
//        driver.findElement(By.name("photo")).clear();
//        driver.findElement(By.name("photo")).sendKeys("C:\\fakepath\\ПЛАН-НА-МЕСЯЦ.jpg");
        driver.findElement(By.name("title")).click();
        driver.findElement(By.name("title")).clear();
        driver.findElement(By.name("title")).sendKeys(userData.getTitle());
        driver.findElement(By.name("company")).click();
        driver.findElement(By.name("company")).clear();
        driver.findElement(By.name("company")).sendKeys(userData.getCompanyName());
        driver.findElement(By.name("address")).click();
        driver.findElement(By.name("address")).clear();
        driver.findElement(By.name("address")).sendKeys(userData.getAddressMain());
        driver.findElement(By.name("mobile")).click();
        driver.findElement(By.name("mobile")).clear();
        driver.findElement(By.name("mobile")).sendKeys(userData.getMobile());
        driver.findElement(By.name("fax")).click();
        driver.findElement(By.name("fax")).clear();
        driver.findElement(By.name("fax")).sendKeys(userData.getFax());
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(userData.getEmailMain());
        driver.findElement(By.name("homepage")).click();
        driver.findElement(By.name("homepage")).clear();
        driver.findElement(By.name("homepage")).sendKeys(userData.getHomePage());
    }

    private void initUserCreation() {
        driver.findElement(By.linkText("add new")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
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

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private void login(LoginData loginData) {
        driver.findElement(By.name("user")).clear();
        driver.findElement(By.name("user")).sendKeys(loginData.getUsername());
        driver.findElement(By.name("pass")).clear();
        driver.findElement(By.name("pass")).sendKeys(loginData.getPassword());
        driver.findElement(By.xpath("//input[@value='Login']")).click();
    }

}
