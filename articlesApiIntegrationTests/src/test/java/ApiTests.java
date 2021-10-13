import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {

    @Test
    public void getAllArticlesRequest(){
        given().baseUri("https://blog-api-with-mongo-db.herokuapp.com/articles")
                .contentType(ContentType.JSON)
                .when().get("")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getASpecificArticleByIdRequest(){
        Response articleId = given().baseUri("https://blog-api-with-mongo-db.herokuapp.com/articles")
                .contentType(ContentType.JSON)
                .when().get("/1")
                .thenReturn();

        Assert.assertEquals(articleId.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(articleId.getBody().jsonPath().getInt("id"),1);
    }

    @Test
    public void createArticleRequest(){
        Response newArticle = given().baseUri("https://blog-api-with-mongo-db.herokuapp.com/articles")
                .contentType(ContentType.JSON)
                .body("{\n"+
                        "\"id\": \"6\",\n"+
                        "\"title\": \"Bucegi: Places you must visit before you die\",\n"+
                        "\"tag\": \"Must Visit\",\n"+
                        "\"author\": \"Jonnathan Mercadina\",\n"+
                        "\"date\": \"June 30, 2018\",\n"+
                        "\"imgUrl\": \"img/bucegi.jpg\",\n"+
                        "\"content\": \"Lorem ipsum dolor sit amet consectetur, adipisicing elit. Est totam laboriosam debitis magnam voluptatum, incidunt neque. \",\n"+
                        "\"dateTimestamp\": \"2021-09-01T08:04:56.000+00:00\"\n"+"}")
                .when().post("/create")
                .thenReturn();
        Assert.assertEquals(200,newArticle.getStatusCode());
    }

    private static String responseBody = "{\n"+
            "\"title\": \"Test\",\n"+
            "\"tag\": \"Must Visit\",\n"+
            "\"author\": \"Jonnathan Mercadina\",\n"+
            "\"date\": \"June 30, 2018\",\n"+
            "\"imgUrl\": \"img/bucegi.jpg\",\n"+
            "\"content\": \"Lorem ipsum dolor sit amet consectetur, adipisicing elit. Est totam laboriosam debitis magnam voluptatum, incidunt neque. \",\n"+
            "\"dateTimestamp\": \"2021-09-01T08:04:56.000+00:00\"\n"+"}";
    @Test
    public void updateArticleByIdRequest(){
        Response articleBody = given().baseUri("https://blog-api-with-mongo-db.herokuapp.com/articles")
                .contentType(ContentType.JSON)
                .body(responseBody)
                .when().put("/5/update")
                .thenReturn();

        Assert.assertEquals(200,articleBody.getStatusCode());
    }

    @Test
    public void deleteArticleByIdRequest(){
        Response articleId = given().baseUri("https://blog-api-with-mongo-db.herokuapp.com/articles")
                .contentType(ContentType.JSON)
                .when().delete("/1/delete")
                .thenReturn();
        Assert.assertEquals(200,articleId.getStatusCode());
    }

    @Test
    public void deleteArticleByBadIdRequest(){
        Response articleId = given().baseUri("https://blog-api-with-mongo-db.herokuapp.com/articles")
                .contentType(ContentType.JSON)
                .when().delete("/6/delete")
                .thenReturn();
        Assert.assertEquals(204,articleId.getStatusCode());
    }

    @Test
    public void getASpecificArticleByTitleRequest(){
        Response articleId = given().baseUri("https://blog-api-with-mongo-db.herokuapp.com/articles")
                .contentType(ContentType.JSON)
                .when().get("/searchArticleByTitle?title=The+God+Father")
                .thenReturn();

        Assert.assertEquals(articleId.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(articleId.getBody().jsonPath().getString("title"),"The+God+Father");
    }

}
