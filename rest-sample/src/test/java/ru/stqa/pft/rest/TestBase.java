package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class TestBase {

    @Test
    public void testTest() throws IOException {
        isIssueOpen(1537);

    }

    public boolean isIssueOpen(int issueId) throws IOException {
        String json = getExecutor()
                .execute(Request.Get(String.format("https://bugify.stqa.ru/api/issues/%s.json", issueId)))
                .returnContent().asString();
        JsonElement parsed = JsonParser.parseString(json);
        System.out.println(parsed + "" + parsed);
        System.out.println("Issue " +  issueId );

        String stateName = parsed.getAsJsonObject()
                .getAsJsonArray("issues")
                .get(0)
                .getAsJsonObject()
                .get("state_name")
                .getAsString();

        System.out.println("stateName " +  stateName );

        return stateName.equals("Open");
    }


    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        } else {
            System.out.println("Issue is ready for test: " + issueId);
        }
    }

    private Executor getExecutor() {
        return Executor.newInstance()
                .auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

}
