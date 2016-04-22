<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="loginContainer">
    <c:if test="${empty sessionScope.AuthedUser}">
        <a href="#login-box" class="login-window">Prijava &gt;</a>
    </c:if>
    <c:if test="${not empty sessionScope.AuthedUser}">
        <span class="logout-WelcomeUser">Dobrodosli,
            <a href="MojProfil" class="cUser" data-kid="$(AuthedUser.IDKorisnik)">${AuthedUser.uname}</a></span>
        <a href="Home" class="logout-window">Odjava &gt;</a>
    </c:if>
</div>
