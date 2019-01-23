import io.restassured.RestAssured;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.Is.is;

public class test {
    @Before
    public void baseUrl() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void getTest() {
        given()
                .get("/api/users?page=2").
        then()
                .body("data.first_name", hasItems("Eve", "Charles", "Tracey"))
                .body("data.last_name", hasItems("Holt", "Morris", "Ramos"))
                .statusCode(200);
    }

    @Test
    public void postTest() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("name", "morpheus");
        json.put("job", "leader");
        String jput = json.toString();

        given()
                .contentType("application/json").
        when()
                .body(jput)
                .post("https://reqres.in/api/users").
        then()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }
}
