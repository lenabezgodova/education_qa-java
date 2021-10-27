package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Set;

public class ResTAssuredTests extends TestBase{

    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
        //RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
    }

    @Test
    public void testCreateIssue() throws IOException {
        long now = System.currentTimeMillis();
        String subject =  String.format("Zakharova-Test issue_%s", now);
        String description =  String.format("Zakharova-Test description_%s", now);


        Set<Issue> oldIssues = getIssue();
        Issue newIssue = new Issue().setSubject(subject).setDescription(description);

        int issueID = createIssue(newIssue);
        Set<Issue> newIssues = getIssue();

        oldIssues.add(newIssue.setId(issueID));
        System.out.println(oldIssues.size() + "" + oldIssues);
        System.out.println(newIssues.size() + "" + newIssues);

        Assert.assertEquals(newIssues, oldIssues);
    }

    @Test
    public void test() throws IOException {
        skipIfNotFixed(1537);

    }

    private Set<Issue> getIssue() {
        String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = JsonParser.parseString(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
    }

    private int createIssue(Issue newIssue) throws IOException {
        String json = RestAssured.given().parameter("subject", newIssue.getSubject())
                .parameter("description", newIssue.getDescription())
                .parameter("status", newIssue.getStatus())
                .post("https://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = JsonParser.parseString(json);
        System.out.println("---->" + parsed);
        System.out.println("--->   " + parsed.getAsJsonObject().get("issue_id").getAsInt());
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }
}
