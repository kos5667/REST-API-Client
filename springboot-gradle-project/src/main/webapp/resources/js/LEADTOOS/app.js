window.onload = function () {
	
   	// License Key
   	var licenseUrl    = "/resources/plugins/LEADTOOLS/License/Leadtools.lic.txt";
   	var developerKey  = "/resources/plugins/LEADTOOLS/License/Leadtools.lic.key.txt";

  	lt.RasterSupport.setLicenseUri(licenseUrl, developerKey, function ( 
    	setLicenseResult 
  	) { 
    	// Check the status of the license 
	    if (setLicenseResult.result) { 
	      console.log("LEADTOOLS client license set successfully"); 
	    } else { 
	      var msg = "LEADTOOLS License is missing, invalid or expired\nError:\n"; 
	      console.log(msg); 
	      alert(msg); 
	    } 
  	}); 
}; 
