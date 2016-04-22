<%@ page language="java" contentType="text/html; charset=ISO-8859-2" pageEncoding="ISO-8859-2"%>	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Crni__WEB__Shop KATALOG</title>

<!-- start SkripteILinkovi -->
<%@ include file="WEB-INF/HTML_Fragmenti/SkripteILinkovi.jsp"%>
<link href="${pageContext.request.contextPath}/CSS/katalog.css" rel="stylesheet" type="text/css" />
<!-- end SkripteILinkovi -->

</head>
<body>
	<!--start header-->
	<%@ include file="WEB-INF/HTML_Fragmenti/Header.jsp"%>
	<!--end header-->

	<!--start MainContent-->
	<%@ include file="WEB-INF/HTML_Fragmenti/Katalog/MainContent.jsp"%>
	<!--end MainContet-->
    
	<!--start footer-->
	<%@ include file="WEB-INF/HTML_Fragmenti/Footer.jsp"%>
	<!--end footer-->
</body>
</html>
