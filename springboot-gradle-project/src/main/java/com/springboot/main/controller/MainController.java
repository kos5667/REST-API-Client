package com.springboot.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = "/")
	public String Main() throws Exception {
		log.debug("Start Main...");
		return "main";
	}
	
	@GetMapping(value = "/leadtools")
	public String LEADTOOLS_index() throws Exception {
		return "leadtools/imageViewer";
	}
	
}
