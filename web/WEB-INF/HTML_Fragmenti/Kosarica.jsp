<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="kosaricaContainer">
	<a id="btn_ShowKosarica"><span id="kosarica_Naslov">Kosarica &gt;</span><br /></a>
	<span>Proizvoda:</span><span id="kosarica_ProizvodCount">${fn:length(kosarica)} kom.</span><br />
	<span>Ukupan iznos:</span><span id="kosarica_UkupanIznos">${iznos} kn</span>
	<c:if test="${fn:length(kosarica) > 0}">
		<input type="button" class="submit button" value="Naruci !" />
		<input type="button" class="submit button" value="Isprazni !" />		
	</c:if>
</div>
