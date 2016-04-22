<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header>
    <div class="container1">
        <!--start title-->
        <h1 class="fontface" id="title">
            <span class="blue">CRNI</span><span class="green"> WEBSHOP</span>
        </h1>
        <!--end title-->
        <!--start menu-->
        <% if (request.getSession().getAttribute("AuthedUser") == null) {
        %><%@ include file="NavNotAuthed.jsp"%><%
        } else {
        %><%@ include file="NavAuthed.jsp"%><%
                                    }%>			
        <!--end menu-->
    </div>
    <div class="bottom"></div>
</header>

<%@ include file="AuthBox.jsp" %>
<%@ include file="Kosarica.jsp" %>
