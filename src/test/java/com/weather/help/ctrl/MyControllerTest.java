package com.weather.help.ctrl;

import com.weather.help.models.OutfitRecommendationResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class MyControllerTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private MyController myController = new MyController();

	@Test
	public void testGetOutfit_Success() {
		OutfitRecommendationResponse reccMock = new OutfitRecommendationResponse();
		reccMock.setOutfitLevel(0) ;
		reccMock.setTemperature(0.0);

		ResponseEntity<OutfitRecommendationResponse> myEntity = new ResponseEntity<OutfitRecommendationResponse>(reccMock, HttpStatus.ACCEPTED);

		Mockito.when(restTemplate.exchange(
				"http://api.openweathermap.org/data/2.5/weather?q=Darmstadt&appid=2f3f287f3496f500539248bc57678be4",
				HttpMethod.GET, null, OutfitRecommendationResponse.class)).thenReturn(myEntity);

		OutfitRecommendationResponse reccOrig = myController.getOutfitRecommendation("darmstadt").getBody();
		assertEquals(myEntity.getBody().getOutfitLevel(), reccOrig.getOutfitLevel());
		assertEquals(myEntity.getBody().getTemperature(), reccOrig.getTemperature());
	}

//	@Test
//	public void testGetOutfit_Failure() {
//		Recommendation reccMock = new Recommendation();
//		reccMock.setOutfitLevel("0");
//		reccMock.setTemperature("1");
//
//		ResponseEntity<Recommendation> myEntity = new ResponseEntity<Recommendation>(reccMock, HttpStatus.ACCEPTED);
//
//		Mockito.when(restTemplate.exchange(
//				"http://api.openweathermap.org/data/2.5/weather?q=Darmstadt&appid=2f3f287f3496f500539248bc57678be4",
//				HttpMethod.GET, null, Recommendation.class)).thenReturn(myEntity);
//
//		Recommendation reccOrig = myController.getWeatherData("darmstadt");
//		assertEquals(myEntity.getBody().getOutfitLevel(), reccOrig.getOutfitLevel());
//		assertEquals(myEntity.getBody().getTemperature(), reccOrig.getTemperature());
//	}
}
