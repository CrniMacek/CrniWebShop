/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function logoutUser() {
    try {
        $.get("OdjavaKorisnika").success(function () {
            var authed = $('#authed');
            if (authed == null) {
                logoutUser();
            }
            window.location = "Home";
        });
    } catch (e) {
        alert("Deauthentication error! :-(");
    }
}

function sleep(milliseconds) {
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds) {
            break;
        }
    }
}



$(document).ready(function () {

    //Click Handlers...	
    $('input[type="button"]').click(function () {

        var val = $(this).attr("value");
        var id = $(this).attr("data-id");

        switch (val) {

            // 1 Dodaj u kosaricu proizvod
            case "Kupi !":

                try {
                    $.get("Katalog", {op: 1, pid: id}).success(function () {
                        window.location.reload();
                    });
                } catch (e) {
                    alert("Shopping communications error! :(");
                    window.location.reload();
                }

                break;

                // 2 Naruci sve iz kosarice
            case "Naruci !":

                var kid = $('.cUser').attr("data-kid");
                var kkom = $('#kosarica_ProizvodCount').html();

                if (kid == null) {
                    alert('Morate biti ulogirani za narucivanje!');

                    ($('a.login-window')).click().focus();
                } else if (kkom == "0 kom.") {

                    alert("Nemoguce je narucivanje ukoliko niste odabrali proizvode!");
                    return;

                } else {

                    $.get("Katalog", {op: 2}).success(function () {
                        alert("Uspjesno naruceno!");

                        window.location.reload();
                    });
                }

                break;

                // 3 Detalji Proizvoda
            case "Detalji":
                pokaziDetaljeProizvoda(id);
                break;

                // 4 Detalji Narudzbe
            case "Prikazi detalje":
                try {
                    $.get("Katalog", {op: 4, nid: id}).success(function (data) {
                        setJSONDataToNarudzbaDetalji(data);
                    });

                    $('#dn-box').focus();

                } catch (e) {
                    alert("HTTP GET Details communications error! :(");
                    window.location.reload();
                }

                break;

                // 5 Isprazni kosaricu
            case "Isprazni !":
                try {

                    $.get("Katalog", {op: 5}).success(function () {
                        window.location.reload();
                    });
                } catch (e) {
                    alert("Cart communications error! :-(");
                    window.location.reload();
                }
                break;

                // 6 Izbrisi odabranu
            case "Izbrisi narudzbu":

                $.get("Katalog", {op: 6, nid: id}).success(function () {
                    window.location.reload();
                });
                break;
        }
    });

    $('.stavkaProizvod').click(function () {

        var id = $(this).attr("data-id");

        try {

        } catch (e) {
            alert("HTTP GET Details communications error! :(");
            window.location.reload();
        }

    });

    $('#btn_ShowKosarica').click(function () {

        if ($('#kosarica_ProizvodCount').html() !== '0 kom.') {
            $.get('Katalog', {op: 7}).success(function (data) {

                var $kp = $('.fieldsetKosarice');
                $kp.remove();

                jSONDataToKosaricaDetalji(data);

            });
        } else {
            alert('Kosarica je prazna!');
        }

    });

    $('a.logout-window').click(function () {
        logoutUser();
    });

    $('a.login-window').click(function () {
        $('#login-box').fadeIn(300).focus();
        $('#reg-box').fadeOut(300);
    });

    $('a.close, #mask').live('click', function () {
        $('#mask , .login-popup').fadeOut(300, function () {
            $('#mask').remove();
        });
    });

    $('#newReg').click(function () {

        $('#login-box').fadeOut(300);
        $('#reg-box').fadeIn(300);

    });

    $('#btn_RegisterNewUser').click(function () {

        var uname = $('#n_username').attr("value");
        var pwd = $('#n_password').attr("value");
        var em = $('#n_email').attr("value");
        if (!uname || !pwd || !em) {

            alert("Niste ispunili sva polja!");
            window.location = "Home";
            $('#reg-box').fadeIn(300);
        } else {
            $.post('NoviKorisnik', {username: uname, password: pwd, email: em}).success(function () {
                alert("Uspjesno ste registrirani!");
                window.location = "Home";
            });
        }
    });

    $('#btn_LoginUser').click(function () {

        var uname = $('#login_username').attr("value");
        var pwd = $('#login_password').attr("value");

        try {
            $.post("PrijavaKorisnika", {username: uname, password: pwd}).success(function () {
                $('body').append('<input id="authed" type="hidden" value="authed" />');
                window.location = "Home";
            }).error(function () {
                alert("Login error! :-(");
                window.location = "Home";
            });
        } catch (e) {
            alert("Login error! :-(");
        }
    });

});

function jSONDataToKosaricaDetalji(data) {

    var stavke = data.stavke;
    var $kp = $('.kosaricaContainer');
    $kp.append('<fieldset class="fieldsetKosarice"><legend>Stavke (' + stavke.length + ')</legend></fieldset>');
    $kp.children('fieldset').append('<div class="stavkeKosarice"></div>');
    var $sk = $('.stavkeKosarice');
    for (var x = 0; x < stavke.length; x++) {
        var id = stavke[x].proizvodId;
        $sk.append('<div class="stavkaKosarice"><span class="title">Proizvod:</span><br /><a class="stavkaProizvod" onclick="pokaziDetaljeProizvoda(' + id + ');" ><span class="val">' + stavke[x].proizvodNaziv + '</span></a><br />'
                + '<span class="title">Kolicina:</span><span class="val">' + stavke[x].kolicina + ' kom.</span><br />'
                + '<span class="title">Proizvod cijena:</span><br /><span class="val">' + stavke[x].proizvodCijena + ' kn</span><br />'
                + '<span class="title">Cijena stavke:</span><br /><span class="val">' + stavke[x].cijena + ' kn</span></div>');
    }

}

function pokaziDetaljeProizvoda(id) {

    try {
        $.get("Katalog", {op: 3, pid: id}, function (data) {
            JSONDataToProizvodDetalji(data);
            $('#pkupi').focus();
        });
    } catch (e) {
        alert("HTTP GET Details communications error! :(");
        window.location.reload();
    }
}

function JSONDataToProizvodDetalji(data) {

    $('#pn').html(data.naziv);
    $('#pckn').html(data.cijena + " kn");
    $('#pid').html(data.id);
    $('#pkn').html(data.kategorijaNaziv);
    $('#po').html(data.opis);
    $('#ps').attr('src', data.slike);
    $('#ps').attr('alt', data.naziv);
    $('#pkupi').attr('data-id', data.id);

}

function setJSONDataToNarudzbaDetalji(data) {

    $('#nid').html(data.idNarudzba);
    $('#nv').html($('#vn' + data.idNarudzba).html());
    $('#nckn').html(data.iznosKN + " kn");
    $('#nkp').html(data.kolicinaProizvoda + " kom.");
    $('#nk').html(data.narucitelj);

    var stavke = data.stavke;

    for (var i = 0; i < stavke.length; i++) {
        var stavka = "<div>"
                + "Naziv: <span>" + stavke[i].proizvodNaziv + "</span><br />"
                + "Kolicina: <span>" + stavke[i].kolicinaProizvoda + " kom.</span><br />"
                + "Cijena proizvoda: <span>" + stavke[i].cijenaProizvoda + " kn</span><br />"
                + "Iznos stavke: <span>" + stavke[i].cijenaStavke + " kn</span>"
                + "</div><br />";
        $('#nstavke').append(stavka);
    }

}