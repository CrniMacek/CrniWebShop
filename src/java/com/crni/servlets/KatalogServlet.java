/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crni.servlets;

import com.crni.data.Repozitorij;
import com.crni.model.Korisnik;
import com.crni.model.Narudzba;
import com.crni.model.Proizvod;
import com.crni.model.Stavka;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;

/**
 *
 * @author igor
 */
public class KatalogServlet extends HttpServlet {

    private static Repozitorij repo;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public KatalogServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            handleRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(KatalogServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            handleRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(KatalogServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        HttpSession s = request.getSession();
        repo = new Repozitorij();
        int op = 0;
        if (request.getParameter("op") != null) {
            op = Integer.parseInt(request.getParameter("op"));
        }
        if (op != 0) {

            int idNarudzba;
            Korisnik k = (Korisnik) (s.getAttribute("AuthedUser"));

            if (op == 1) {
                //kosarica dodaj
                kosaricaDodaj(request, s, repo);
                return;
            } else if (op == 2) {
                //narudzba nova

                if (s.getAttribute("AuthedUser") == null) {
                    response.sendError(405, "ZABRANJEN PRISTUP NEREGISTRIANIM KORISNICIMA!\n\n");
                    return;
                }

                Narudzba n = narudzbaNova(s, k);
                s.setAttribute("kosarica", null);
                s.setAttribute("iznos", null);
                s.setAttribute("ZadnjaNarudzba", n);
            } else if (op == 3) {
                //detaljiProizvoda
                vratiJSONDetaljaProizvoda(request, response, repo);
                return;
            } else if (op == 4) {
                //detaljiNarudzbe
                if (s.getAttribute("AuthedUser") == null) {
                    response.sendError(405, "ZABRANJEN PRISTUP NEREGISTRIANIM KORISNICIMA!\n\n");
                    return;
                }
                vratiDetaljeNarudzbeJSON(request, response);
                return;
            } else if (op == 5) {
                //isprazni kosaricu
                s.setAttribute("kosarica", null);
                s.setAttribute("iznos", null);
            } else if (op == 6) {
                //izbrisi narudzbu
                if (s.getAttribute("AuthedUser") == null) {
                    response.sendError(405, "ZABRANJEN PRISTUP NEREGISTRIANIM KORISNICIMA!\n\n");
                    return;
                }

                idNarudzba = Integer.parseInt(request.getParameter("nid"));
                repo.narudzba_Delete_SingleById(idNarudzba);
            } else if (op == 7) {
                //prikazi sadrzaj kosarice
                ArrayList<Stavka> stavke = dohvatiKosaricaProizvodi(request);
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                JSONObject obj = stavkeKosariceToJSON(stavke);
                out.println(obj);
                return;
            }
        } else {

            List<Proizvod> proizvodi = repo.proizvod_Select_All();
            request.setAttribute("sviProizvodi", proizvodi);
        }

        RequestDispatcher r = request.getRequestDispatcher("/KatalogProizvoda.jsp");
        r.forward(request, response);
    }

    @SuppressWarnings("unchecked")
    private void kosaricaDodaj(HttpServletRequest request, HttpSession s, Repozitorij repo) throws SQLException {

        ArrayList<Proizvod> kosarica;
        int idProizvod;
        idProizvod = Integer.parseInt(request.getParameter("pid"));
        kosarica = new ArrayList<Proizvod>();

        if (s.getAttribute("kosarica") == null) {
            s.setAttribute("kosarica", kosarica);
        }

        kosarica = (ArrayList<Proizvod>) s.getAttribute("kosarica");
        kosarica.add(repo.proizvod_Select_SingleById(idProizvod));

        s.setAttribute("kosarica", kosarica);

        Double iznos = 0.0;

        for (Proizvod p : kosarica) {

            iznos += p.getCijenaKN().doubleValue();

        }

        s.setAttribute("iznos", iznos);
    }

    private ArrayList<Stavka> dohvatiKosaricaProizvodi(HttpServletRequest request) {
        ArrayList<Proizvod> kosarica = (ArrayList<Proizvod>) request.getSession().getAttribute("kosarica");
        ArrayList<Stavka> stavke = new ArrayList<Stavka>();

        for (Proizvod p : kosarica) {

            Stavka stavka = new Stavka();
            stavka.setProizvodID(p.getIDProizvod());
            stavka.setProizvod(p);
            stavka.setCijenaStavkeKN(p.getCijenaKN());
            stavka.setOpisStavke("Proizvod: " + p.getNaziv() + ", OK!");
            stavka.setKolicinaProizvoda(1);

            if (stavke.size() == 0) {
                stavke.add(stavka);
                continue;
            }

            boolean getOut = false;
            for (Stavka st : stavke) {
                int stPID = st.getProizvodID();
                int prPID = p.getIDProizvod();
                if (stPID == prPID) {
                    st.setKolicinaProizvoda(st.getKolicinaProizvoda() + 1);
                    st.setCijenaStavkeKN(st.getCijenaStavkeKN().add(p.getCijenaKN()));
                    getOut = true;
                    break;
                }
            }

            if (getOut) {
                continue;
            }

            if (!stavke.contains(stavka.getProizvodID() == p.getIDProizvod())) {
                stavke.add(stavka);
            }
        }
        return stavke;
    }

    private JSONObject stavkeKosariceToJSON(ArrayList<Stavka> stavke) {
        ArrayList<JSONObject> objs = new ArrayList<>();
        JSONObject json;

        for (Stavka st : stavke) {
            json = new JSONObject();
            json.put("proizvodNaziv", st.getProizvod().getNaziv());
            json.put("kolicina", st.getKolicinaProizvoda());
            json.put("proizvodCijena", st.getProizvod().getCijenaKN());
            json.put("cijena", st.getCijenaStavkeKN());
            json.put("proizvodId", st.getProizvod().getIDProizvod());
            objs.add(json);
        }
        json = new JSONObject();
        json.put("stavke", objs);

        return json;
    }

    private void vratiJSONDetaljaProizvoda(HttpServletRequest request, HttpServletResponse response, Repozitorij repo) throws SQLException, IOException {
        int idProizvod;
        idProizvod = Integer.parseInt(request.getParameter("pid"));

        Proizvod p = repo.proizvod_Select_SingleById(idProizvod);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject obj = detaljiProizvodToJSON(p);
        out.println(obj);
    }

    private JSONObject detaljiProizvodToJSON(Proizvod p) {
        JSONObject json = new JSONObject();

        json.put("id", p.getIDProizvod());
        json.put("naziv", p.getNaziv());
        json.put("slike", p.getSlike());
        json.put("cijena", p.getCijenaKN());
        json.put("opis", p.getOpis());
        json.put("kategorijaNaziv", p.getKategorija().getNaziv());

        return json;
    }

    @SuppressWarnings("unchecked")
    private Narudzba narudzbaNova(HttpSession s, Korisnik k) throws SQLException {
        ArrayList<Proizvod> kosarica;
        Narudzba n = new Narudzba();

        kosarica = (ArrayList<Proizvod>) s.getAttribute("kosarica");

        ArrayList<Stavka> stavke = new ArrayList<Stavka>();

        for (Proizvod p : kosarica) {

            Stavka stavka = new Stavka();
            stavka.setProizvodID(p.getIDProizvod());
            stavka.setProizvod(p);
            stavka.setCijenaStavkeKN(p.getCijenaKN());
            stavka.setOpisStavke("Proizvod: " + p.getNaziv() + ", OK!");
            stavka.setKolicinaProizvoda(1);

            if (stavke.size() == 0) {
                stavke.add(stavka);
                continue;
            }

            boolean getOut = false;
            for (Stavka st : stavke) {
                int stPID = st.getProizvodID();
                int prPID = p.getIDProizvod();
                if (stPID == prPID) {
                    st.setKolicinaProizvoda(st.getKolicinaProizvoda() + 1);
                    st.setCijenaStavkeKN(st.getCijenaStavkeKN().add(p.getCijenaKN()));
                    getOut = true;
                    break;
                }
            }

            if (getOut) {
                continue;
            }

            if (!stavke.contains(stavka.getProizvodID() == p.getIDProizvod())) {
                stavke.add(stavka);
            }
        }

        n.setIDKorisnik(k.getIDKorisnik());
        n.setOpisNarudzbe("Narucitelj: " + k.getIme() + " " + k.getPrezime() + " | OK!");

        n = repo.narudzba_Insert_NewSingle(n, stavke);

        return n;
    }

    private void vratiDetaljeNarudzbeJSON(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int idNarudzba;
        idNarudzba = Integer.parseInt(request.getParameter("nid"));
        Narudzba n = repo.narudzba_Select(idNarudzba);
        JSONObject obj = serializeDetaljiNarudzbeToJSON(n);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(obj);
    }

    private JSONObject serializeDetaljiNarudzbeToJSON(Narudzba n) {
        int kol = 0;
        JSONObject obj = new JSONObject();
        JSONObject stavkeJSON;

        ArrayList<JSONObject> objs = new ArrayList<JSONObject>();

        for (Stavka nStavka : n.getStavke()) {
            kol += nStavka.getKolicinaProizvoda();
            stavkeJSON = new JSONObject();
            stavkeJSON.put("proizvodNaziv", nStavka.getProizvod().getNaziv());
            stavkeJSON.put("kolicinaProizvoda", nStavka.getKolicinaProizvoda());
            stavkeJSON.put("cijenaProizvoda", nStavka.getProizvod().getCijenaKN());
            stavkeJSON.put("cijenaStavke", nStavka.getCijenaStavkeKN());
            objs.add(stavkeJSON);
        }

        obj.put("idNarudzba", n.getIDNarudzba());
        obj.put("iznosKN", n.getPunaCijenaKn());
        obj.put("kolicinaProizvoda", kol);
        obj.put("narucitelj", n.getKorisnik().getIme() + " " + n.getKorisnik().getPrezime());
        obj.put("stavke", objs);

        return obj;
    }

}
