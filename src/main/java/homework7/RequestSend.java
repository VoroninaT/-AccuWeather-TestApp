package homework7;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class RequestSend {

    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    final static private String API_KEY = "nGSrWePfth2aNkntuz4ppOGKEIVRVixY";


    public String sendCityRequest(String cityName) throws IOException {

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegment("locations")
                .addPathSegment("v1")
                .addPathSegment("cities")
                .addPathSegment("search")
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", cityName)
                .build();

        //System.out.println("URL: " + httpUrl);

        Request request1 = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(httpUrl)
                .build();

        Response response = okHttpClient.newCall(request1).execute();

        String responseJson = response.body().string();

        //System.out.println("Response JSON: " + responseJson);
        //System.out.println();

        JsonNode cityNode = objectMapper
                .readTree(responseJson)
                .at("/0/Key");
        return cityNode.textValue();
    }


    public void printForecastRequest(String cityCode) throws IOException {

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegment("forecasts")
                .addPathSegment("v1")
                .addPathSegment("daily")
                .addPathSegment("1day")
                .addPathSegment(cityCode)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("language", "ru")
                .addQueryParameter("metric", "true")
                .build();
        //System.out.println("URL: " + httpUrl);

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(httpUrl)
                .build();

        Response response = okHttpClient.newCall(request).execute();

        String responseJson = response.body().string();

        //System.out.println("Response JSON: " + responseJson);
        //System.out.println();

        JsonNode jsonTree = objectMapper
                .readTree(responseJson);

        String weatherDescription = jsonTree.at("/Headline/Text").asText();
        String minTemp = jsonTree.at("/DailyForecasts/0/Temperature/Minimum/Value").asText();
        String maxTemp = jsonTree.at("/DailyForecasts/0/Temperature/Maximum/Value").asText();


        System.out.println("  Описание: " + weatherDescription);
        System.out.println("  Мин. температура:  " + minTemp);
        System.out.println("  Макс. температура: " + maxTemp);
    }
}
