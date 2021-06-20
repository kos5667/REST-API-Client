package com.springboot.leadtools.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import leadtools.LTLibrary;
import leadtools.Platform;
import leadtools.RasterImage;
import leadtools.RasterImageFormat;
import leadtools.RasterSupport;
import leadtools.codecs.RasterCodecs;

@Controller
public class LEADTOOLS_TEST {

	private static final Logger log = LoggerFactory.getLogger(LEADTOOLS_TEST.class);

	@GetMapping(value = "/convert_image")
	public void ConvertImageToCmp() throws Exception {
		Platform.setLibPath("C:\\LEADTOOLS21\\Bin\\CDLL\\x64"); 
		Platform.loadLibrary(LTLibrary.LEADTOOLS); 
		Platform.loadLibrary(LTLibrary.CODECS);
		
		SetLicense(); 
		
		RasterCodecs codecs = new RasterCodecs(); 
		
		//TIF -> CMP
//		String inputFile = "C:\\LEADTOOLS21\\Resources\\Images\\cmc7.jpg";
//		String outputFile = "D:\\01.workspace\\brain.cmp";
		
		//CMP -> Tir
//		String inputFile = "C:\\LEADTOOLS21\\Resources\\Images\\redeye.cmp";
//		String outputFile = "D:\\01.workspace\\redeye.tif";
		
		//Image(JPG, PNG) -> Tif
//		String inputFile = "C:\\LEADTOOLS21\\Resources\\Images\\image2.jpg"; 
		String inputFile = "C:\\LEADTOOLS21\\Resources\\Images\\littlegflyingalpha.png"; 
		String outputFile = "S:\\git\\repository\\springboot-gradle-project\\src\\main\\webapp\\resources\\img\\output\\test.tif";
		
		RasterImage image = LoadImage(inputFile, codecs); 
		
//		SaveImage(image, outputFile, codecs);
		SaveImageToTif(image, outputFile, codecs);
//		SaveImageToCmp(image, outputFile, codecs); 
//		SaveCmpToTif(image, outputFile, codecs); 
	}
	
	
	static void SetLicense() throws IOException { 
		
		String licenseFile = "C:\\LEADTOOLS21\\Support\\Common\\License\\LEADTOOLS.LIC"; 
		String developerKey = Files.readString(Paths.get("C:\\LEADTOOLS21\\Support\\Common\\License\\LEADTOOLS.LIC.KEY")); 
		RasterSupport.setLicense(licenseFile, developerKey); 
		if(RasterSupport.getKernelExpired()) 
			System.out.println("License file invalid or expired."); 
		else 
			System.out.println("License file set successfully."); 
	}
	
	static RasterImage LoadImage(String inputFile, RasterCodecs codecs){ 
		RasterImage _image = codecs.load(inputFile); 
		System.out.println("Image loaded successfully."); 
		return _image; 
	}
	
	static void SaveImage(RasterImage _image, String outputFile, RasterCodecs _codecs) { 
		_codecs.save(_image, outputFile, RasterImageFormat.TIF, _image.getBitsPerPixel()); 
		System.out.println("Image saved successfully."); 
	}
	
	static void SaveImageToCmp(RasterImage _image, String outputFile, RasterCodecs _codecs) { 
		_codecs.save(_image, outputFile, RasterImageFormat.CMP, _image.getBitsPerPixel()); 
		System.out.println("Image saved successfully."); 
	}
	
	static void SaveImageToTif(RasterImage _image, String outputFile, RasterCodecs _codecs) { 
		_codecs.save(_image, outputFile, RasterImageFormat.TIF, _image.getBitsPerPixel()); 
		System.out.println("Image saved successfully."); 
	}
	
	static void SaveCmpToTif(RasterImage _image, String outputFile, RasterCodecs _codecs) { 
		_codecs.save(_image, outputFile, RasterImageFormat.TIF, _image.getBitsPerPixel()); 
		System.out.println("Image saved successfully."); 
	}
	
}
