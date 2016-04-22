<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section id="main">
	<div class="portfolio">
		<h2 class="fontface">
			<span class="blue ">Katalog</span><span class="green"> proizvoda</span>		
		</h2>
	</div>
	<ul class="gallery">
		<c:forEach var="p" items="${sviProizvodi}" begin="0" end="11">
			<li>
				<span>${p.naziv}</span><br />
				<img class="slika" src="${p.slike}" /><br />
				<input class="btn_DetaljiProizvoda submit button" data-id="${p.IDProizvod}" type="button" value="Detalji" /><br />
				<input class="btn_DodajUKosaricu submit button" data-id="${p.IDProizvod}" type="button" value="Kupi !" />
			</li>
		</c:forEach>
	</ul>
</section>