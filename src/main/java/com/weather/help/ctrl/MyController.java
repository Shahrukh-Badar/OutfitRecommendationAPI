package com.weather.help.ctrl;

import com.weather.help.models.OutfitRecommendationResponse;
import com.weather.help.models.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/api/outfit")
    public ResponseEntity<OutfitRecommendationResponse> getOutfitRecommendation(@RequestParam final String cityName) {
        final OutfitRecommendationResponse outfitRecommendationResponse = new OutfitRecommendationResponse();
        try {
            final String uri = "http://api.openweathermap.org/data/2.5/weather?q=" + cityName
                    + "&appid=2f3f287f3496f500539248bc57678be4";

            final ResponseEntity<WeatherApiResponse> weatherApiResponse = restTemplate.exchange(uri, HttpMethod.GET, null, WeatherApiResponse.class);

            if (weatherApiResponse == null || !weatherApiResponse.hasBody()) {
                outfitRecommendationResponse.setErrorMessage("An error occurred");
                outfitRecommendationResponse.setOutfitLevel(0);
                outfitRecommendationResponse.setTemperature(0.0);
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(outfitRecommendationResponse);
            }
            if (!weatherApiResponse.getStatusCode().is2xxSuccessful()) {
                outfitRecommendationResponse.setErrorMessage(weatherApiResponse.getBody().toString());
                return ResponseEntity.status(weatherApiResponse.getStatusCode()).body(outfitRecommendationResponse);
            }

            Double temperature = weatherApiResponse.getBody().getMain().getTemp();
            temperature = temperature - 273.15d;
            int outfitLevel = 0;
            if (temperature >= 26d)
                outfitLevel = 1;
            else if (temperature > 21d && temperature <= 26d)
                outfitLevel = 2;
            else if (temperature > 15d && temperature <= 21d)
                outfitLevel = 3;
            else if (temperature > 5d && temperature <= 15d)
                outfitLevel = 4;
            else if (temperature <= 5d)
                outfitLevel = 5;
            outfitRecommendationResponse.setTemperature(temperature);
            outfitRecommendationResponse.setOutfitLevel(outfitLevel);
            return ResponseEntity.status(HttpStatus.OK).body(outfitRecommendationResponse);
        } catch (final HttpStatusCodeException ex) {
            ex.printStackTrace();
            outfitRecommendationResponse.setErrorMessage(ex.getMessage());
            return ResponseEntity.status(ex.getStatusCode()).body(outfitRecommendationResponse);
        } catch (final Exception ex) {
            ex.printStackTrace();
            outfitRecommendationResponse.setErrorMessage(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(outfitRecommendationResponse);
        }
    }

}
