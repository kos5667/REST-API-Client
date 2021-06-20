<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<!--Import LEADTOOLS Dependencies-->
    <script type="text/javascript" src="/resources/js/LEADTOOS/lib/Leadtools.js"></script>
    <script type="text/javascript" src="/resources/js/LEADTOOS/lib/Leadtools.Controls.js"></script>
	
	<!--Import Project Dependencies-->
    <script type="text/javascript" src="/resources/js/LEADTOOS/app.js"></script>
    
</head>
<body>
    <div 
      id="imageViewerDiv" 
      style="width: 600px; height: 600px; background-color: darkgray"> 
    </div>

    <h3>Click and drag to pan, CTRL-Click and drag to zoom in and out</h3> 
 
    <input id="fileInput" type="file" accept="image/png, image/jpeg" /> 
 
    <button id="downloadButton">Save Image</button> 
</body>
</html>