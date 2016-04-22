<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="katalogDiv">
    <table class="proizvodi">
        <c:forEach var="p" items="${sviProizvodi}">
            <tr>
                <td>
                    <div class="proizvodDiv">
                        <div class="col1">
                            <span>Naziv: <span class="proizvodNaziv">${p.naziv}</span></span><br />
                            <span>Cijena: <span class="proizvodCijena">${p.cijenaKN} kn</span></span><br />
                            <span>Kategorija: <span class="proizvodKategorija">${p.kategorija.naziv}</span></span>
                            <br />
                            <br />
                            <fieldset>
                                <legend>Opis</legend>
                                <span class="proizvodOpis">${p.opis}</span>
                            </fieldset>
                        </div>
                        <div class="col2">
                            <span><img class="slika" src="${p.slike}" alt="slika" /></span><br />
                            <input class="btn_DodajUKosaricu submit button" data-id="${p.IDProizvod}" type="button" value="Kupi !" />
                        </div>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
