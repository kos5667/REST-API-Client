package com.springboot.call_api.controller;

import java.io.File;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@RestController
public class OkHttpClientTest {
	
	private static final Logger log = LoggerFactory.getLogger(OkHttpClientTest.class);

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	
	private final String fileName = "down_test.tif";
	
	@GetMapping(value = "/okhttp_get")
	public void okhttp_get() throws Exception {
		
		String url_tomcat = "http://192.168.100.152:5001/reception_test?fileName=down_test.tif";
		
		String url_docker = "http://192.168.100.152:8000/reception_test?fileName=down_test.tif";

		OkHttpClient okHttpClient = new OkHttpClient();
		
		Request request = new Request.Builder()
									 .url(url_docker)
									 .addHeader("Connection","close")
									 .get()
									 .build();
		
		Object obj = null;
		JSONObject responseJson = null;
		try(Response response = okHttpClient.newCall(request).execute()) {
//			log.debug(response.body().string());
			obj = new JSONParser().parse(response.body().string());
			responseJson = (JSONObject) obj;
		}
        
        
		@SuppressWarnings("unchecked")
		Map<String, Object> resultMap = (Map<String, Object>) responseJson.get("result");
		
		
		byte[] byteFile = Base64.decodeBase64((String) resultMap.get("file"));
    	
		
    	File file = new File("S:\\git\\repository\\springboot-gradle-project\\src\\main\\webapp\\resources\\img\\"+fileName);
        FileUtils.writeByteArrayToFile(file, byteFile);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@GetMapping(value = "/okhttp_post") 
	public void okhttp_post() throws Exception {
		
		OkHttpClient okHttpClient = new OkHttpClient();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data1", 123);
		jsonObject.put("data2", "abc");
		
		RequestBody body = RequestBody.create(JSON, jsonObject.toString()); 
		
		Request request = new Request.Builder()
									 .url("http://192.168.100.152:8090/okhttp_post_test")
									 .post(body)
									 .build();
		
		Response response = okHttpClient.newCall(request).execute();
		
		log.debug(response.body().string());
	}

}
