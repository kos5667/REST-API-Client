package com.springboot.call_api.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Call_Base64_Encode_Files {
	
	private static final Logger log = LoggerFactory.getLogger(Call_Base64_Encode_Files.class);
	
	private final String call_url = "http://192.168.100.152:8090/Multipart/download_res";

	private final String fileName = "down_test.tif";
	
	@GetMapping(value = "/run")
	public void run() throws Exception {
		log.debug("Call_Base64_Encode_Files Start");
		log.debug("Get File Name : {}", fileName);
		
		StringBuilder urlBuilder = new StringBuilder(call_url);
		
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
		
		
		URL url = new URL(urlBuilder.toString() + paramBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		log.debug("url : {}",url);
		
		//Response Code
		int responseCode = conn.getResponseCode();
		log.debug("Response Code : {}", responseCode);
		
		
		//Response Header
		Map<String, List<String>> map = conn.getHeaderFields();
		log.debug("Response Header...");
//		for(Map.Entry<String, List<String>> entry : map.entrySet()) {
//			log.debug("Key : {}, Value : {}", entry.getKey(), entry.getValue());
//		}
		
		
		//Response Header
		BufferedReader rd;
		if(responseCode >= 200 && responseCode <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		}
		else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		String line;
		while((line = rd.readLine()) != null) {
			stringBuilder.append(line);
		}
		
		
		
		rd.close();
		conn.disconnect();
		 
		log.debug("Response Body...");
		log.debug(stringBuilder.toString());
		
		
		//��� ������ �ִ� ��� �ٿ�ε�
//		if(map.containsKey("content-disposition")) {
//			String value = map.get("content-disposition").toString();
//			String fileName = value.replaceFirst("(?i)^.*filename=\"?([^\"]+)\"?.*$", "$1");
//			String path = "S:\\git\\repository\\springboot-gradle-project\\src\\main\\webapp\\resources\\img";
//			
//			String filePath = path + fileName;
//			File folder = new File(path);
//			if(!folder.exists()) {
//				folder.mkdir();
//			}
//			
//			FileWriter fw = new FileWriter(filePath);
//			fw.write(stringBuilder.toString());
//			fw.close();
//		}
		
		log.debug("Call_Base64_Encode_Files End");
	}
}
