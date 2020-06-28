package com.centime.assignment.task1service1.resource;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.centime.assignment.task1service1.exception.ExceptionController;

import brave.sampler.Sampler;


@RestController
@RequestMapping("/task1/service1/api/v1")
@EnableWebMvc
@Component
public class Resource {
	private  Logger logger = org.slf4j.LoggerFactory.getLogger(ExceptionController.class);

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
	@GetMapping("/healthCheck")
	public ResponseEntity<String> getHealthCheck() throws ResourceNotFoundException {	

		return ResponseEntity.ok().body("UP");

	}
	
	@Bean
	public WebClient getWebclient()
	{
		return WebClient
		  .create();
	}
	
	@PostMapping("/greet")
	public ResponseEntity<String> greetPerson( @RequestBody String personStr) throws InterruptedException, ExecutionException {

		logger.info("GreetPerson()");
		ResponseSpec srvc2Resp = getService2Response();
		ResponseSpec srvc3Resp = getService3Response(personStr);
		return ResponseEntity.ok().body(getServiceRespBody(srvc2Resp) + " " + getServiceRespBody(srvc3Resp));
	}
	private String getServiceRespBody(ResponseSpec serviceResp) {

		return 	serviceResp.toEntity(String.class).block().getBody();
	}
	private ResponseSpec getService3Response(String personStr) {
		
		return getWebclient()
				  .post()
				  .uri(URI.create("http://localhost:3003/task1/service3/api/v1/person"))
				  .body(BodyInserters.fromValue(personStr))
				  .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				  .acceptCharset(Charset.forName("UTF-8")).retrieve();
	
	}
	private ResponseSpec getService2Response() {
		
		return getWebclient()
				  .get()
				  .uri(URI.create("http://localhost:3002/task1/service2/api/v1/greet"))
				  .acceptCharset(Charset.forName("UTF-8")).retrieve();
					
	}

	

//	@PostMapping("/greet")
//	public ResponseEntity<String> greetPerson( @RequestBody String personStr) throws InterruptedException, ExecutionException {
//
//		CompletableFuture<HttpResponse<String>> service2Response = getService2Response();
//		CompletableFuture<HttpResponse<String>> service3Response = getService3Response(personStr);
//
//		while(true)
//		{
//			if(service2Response.isDone() && service3Response.isDone())
//			break;
//			
//			Thread.sleep(2000);
//		}
//		return ResponseEntity.ok().body(service2Response.get().body()+service3Response.get().body());
//	}
//
//	private CompletableFuture<HttpResponse<String>> getService3Response(String personStr) {
//
//		HttpClient httpClient = HttpClient.newHttpClient();
//		HttpRequest request = getHttpPostMethod(personStr);
//		return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
//
//	}
//
//	private HttpRequest getHttpPostMethod(String personStr) {
//		return HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(personStr)).uri(URI.create("$SERVICE3_ENDPOINT")).build();
//	}
//
//	private CompletableFuture<HttpResponse<String>>  getService2Response() {
//		HttpClient httpClient = HttpClient.newHttpClient();
//		HttpRequest request = getHttpGetMethod();
//		return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
//
//	}
//
//	private HttpRequest getHttpGetMethod() {
//		return HttpRequest.newBuilder().GET().uri(URI.create("$SERVICE2_ENDPOINT")).build();
//	}
}
