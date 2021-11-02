package com.cs201.barcrawl.util;

import com.cs201.barcrawl.models.Business;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class GoogleMapsUtil {
    @Value("${google.api_key}")
    private String API_KEY;

    public List<Integer> distanceMatrix(Business origin, List<Business> destinations){
        String url = distanceApiUrlConstructor(origin, destinations);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();

        List<Integer> toReturn = new ArrayList<Integer>();
        try {
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            JsonParser jsonParser = new JsonParser();
            JsonElement responseBody = jsonParser.parse(json);

            JsonObject jsonObject = responseBody.getAsJsonObject();
            JsonArray distances = jsonObject.get("rows").getAsJsonArray().get(0).getAsJsonObject().get("elements").getAsJsonArray();
            for(int i = 0; i < distances.size(); i++){
                int distance = distances.get(i).getAsJsonObject().get("distance").getAsJsonObject().get("value").getAsInt();
                toReturn.add(distance);
            }
            return toReturn;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private String distanceApiUrlConstructor(Business origin, List<Business> destinations){
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url).append("origins=");
        stringBuilder.append(formatCoordinates(origin));
        stringBuilder.append("&destinations=");

        // Iterate through destinations
        Iterator<Business> destinationsIterator = destinations.iterator();
        while(destinationsIterator.hasNext()){
            String coordinates = formatCoordinates(destinationsIterator.next());
            stringBuilder.append(coordinates);
            if(destinationsIterator.hasNext()){
                String delimiter = "%7C";
                stringBuilder.append(delimiter);
            }
        }

        stringBuilder.append("&key=" + this.API_KEY);
        return stringBuilder.toString();
    }

    private String formatCoordinates(Business business){
        StringBuilder stringBuilder = new StringBuilder();
        Double latitude = business.getLatitude();
        Double longitude = business.getLongitude();

        stringBuilder.append(latitude).append("%2c").append(longitude);
        return stringBuilder.toString();
    }
}
