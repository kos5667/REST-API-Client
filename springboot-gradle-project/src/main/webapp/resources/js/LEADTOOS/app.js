window.onload = function () {
	// License Key
	var licenseUrl    = "./resources/plugins/LEADTOOLS/License/Leadtools.lic.txt";
	var developerKey  = "20Com0NtLyAAvwus0Ce33w==";
	
	const LeadTools = new LEADTOOS(licenseUrl,developerKey);

	LeadTools.initImageSingleViewer();
	LeadTools.test();
	
	setLicense();


	const imageViewerDiv = document.getElementById("imageViewerDiv");
	const createOptions = new lt.Controls.ImageViewerCreateOptions(imageViewerDiv); 

	this.imageViewer = new lt.Controls.ImageViewer(createOptions); 
	this.imageViewer.viewVerticalAlignment = lt.Controls.ControlAlignment.center; 
	this.imageViewer.viewHorizontalAlignment = lt.Controls.ControlAlignment.center; 
	this.imageViewer.autoCreateCanvas = true; 

	// Add Pan/Zoom interactive mode 
	// Click and drag to pan, CTRL-Click and drag to zoom in and out 
	this.imageViewer.interactiveModes.add(new lt.Controls.ImageViewerPanZoomInteractiveMode()); 

	// Load an image 
	this.imageViewer.imageUrl = "https://demo.leadtools.com/images/jpeg/cannon.jpg"; 

	this.imageViewer.zoom( 
		lt.Controls.ControlSizeMode.fit, 
		0.9, 
		this.imageViewer.defaultZoomOrigin 
	);

	//Input a file 
	const fileInput = document.getElementById("fileInput"); 
	fileInput.addEventListener("change", (event) => { 
	  //Change the URL of the viewer image to the input file. 
	  if (event.target.files[0] != null) 
		this.imageViewer.imageUrl = URL.createObjectURL(event.target.files[0]); 
	});

	//Download functionality 
	const downloadButton = document.getElementById("downloadButton"); 
	downloadButton.addEventListener("click", () => { 
	  	var image = new Image(); 
	  	image.crossOrigin = "anonymous"; 
	  	image.src = this.imageViewer.imageUrl; 
   
	  	document.body.append(image); 
	  	image.onload = () => { 
		var canvas = document.createElement("canvas"); 
		canvas.width = image.width; 
		canvas.height = image.height; 
		canvas.getContext("2d").drawImage(image, 0, 0); 
   
		var blob; 
		blob = canvas.toDataURL("image/jpeg"); 
   
		var a = document.createElement("a"); 
		a.href = blob; 
		a.download = "output.png"; 
		document.body.appendChild(a); 
		a.click(); 
		document.body.removeChild(a); 
	  }; 
	  document.body.removeChild(image); 
	}); 

}; 

function setLicense() {
	
}


const LEADTOOS = class {

	constructor(licenseUrl,developerKey){
		this.licenseUrl = licenseUrl;
		this.developerKey = developerKey;
	}

	initImageSingleViewer() {
		// License Key
		// var licenseUrl    = "http://127.0.0.1:5500/License/Leadtools.lic.txt";
		// var developerKey  = "20Com0NtLyAAvwus0Ce33w==";

		lt.RasterSupport.setLicenseUri(this.licenseUrl, this.developerKey, function (setLicenseResult) { 
			// Check the status of the license 
			if (setLicenseResult.result) { 
				console.log("LEADTOOLS client license set successfully"); 
			} else { 
				var msg = "LEADTOOLS License is missing, invalid or expired\nError:\n"; 
				console.log(msg);
			}
		});
	}

	test() {
		
		function ccc() {


		}

		// aaa = () => {
		// 	console.log("aaa");
		// }

		var ddd = () => {
			console.log("ddd");
		}
	}
}



/*const promise = new Promise((a,b) => {
	if(a == 1){
		a("성공");
	} else {
		b("실패");
	}
});

promise
.then((message) => {
	console.log(message);
})
.then((error) => {
	console.log(error);
})
.finally(() => {
	console.log('finally');
})*/