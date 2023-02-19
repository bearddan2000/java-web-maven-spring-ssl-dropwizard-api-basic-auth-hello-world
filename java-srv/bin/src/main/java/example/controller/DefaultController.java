package example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class DefaultController {

  private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

	private RestTemplate restTemplate = new RestTemplate();

	private final String API_URL = "https://api:8443/";

  HttpHeaders createHeaders(String username, String password){
    return new HttpHeaders() {{
        final String basicAuth = HttpHeaders.encodeBasicAuth(username, password, StandardCharsets.US_ASCII);
        setBasicAuth(basicAuth);
    }};
   }

	@RequestMapping(path="/", method=RequestMethod.GET)
	public String getPublic(){
    final String msg = "public access";
    logger.info(msg);
		return msg;
	}

	@RequestMapping(path="/api", method=RequestMethod.GET)
	public String getApi(){
    final String msg = "private access";
    logger.info(msg);
	 final ResponseEntity<String> responseEntity = restTemplate.exchange(
      API_URL + "/hello-world",
      HttpMethod.GET,
      new HttpEntity<Void>(createHeaders("user", "password")),
      String.class
    );
    return responseEntity.getBody();
	}
}
