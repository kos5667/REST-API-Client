package com.springboot.call_api.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Base64.Decoder;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Call_TEST {

	private static final Logger log = LoggerFactory.getLogger(Call_TEST.class);

	private final String fileName = "down_test.tif";
	
	@GetMapping(value = "/call_test")
	public void call_test() throws Exception {
		log.debug("CALL TEST Start");
		log.debug("Get File Name : {}", fileName);
		
		StringBuilder urlBuilder = new StringBuilder("http://192.168.100.152:8090/reception_test");
		
		Map<String, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("fileName", fileName);
		
		StringBuilder paramBuilder = new StringBuilder();
		for(Map.Entry<String, Object> param : paramMap.entrySet()) {
			
			if(paramBuilder.length() != 0) {
				paramBuilder.append("&");
			}
			else {
				paramBuilder.append("?");
			}
			
			paramBuilder.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			paramBuilder.append("=");
			paramBuilder.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
		}
		log.debug("Parameter : {}", paramBuilder.toString());

		
		URL url = new URL(urlBuilder.toString() + paramBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		log.debug("url : {}",url);
		
		
		//Response Code
		int responseCode = conn.getResponseCode();
		log.debug("Response Code : {}", responseCode);
		
		
		BufferedReader br;;
		if(responseCode >= 200 && responseCode <= 300) {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		}
		else {
			br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		
		
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        
        
        Object obj = new JSONParser().parse(sb.toString());
        JSONObject responseJson = (JSONObject) obj;
        
        
		@SuppressWarnings("unchecked")
		Map<String, Object> resultMap = (Map<String, Object>) responseJson.get("result");
        
        byte[] byteFile = Base64.decodeBase64((String) resultMap.get("file"));
    	
    	
    	File file = new File("S:\\git\\repository\\springboot-gradle-project\\src\\main\\webapp\\resources\\img\\"+fileName);
        FileUtils.writeByteArrayToFile(file, byteFile);
		
        
		log.debug("CALL TEST End");
	}
	
}
