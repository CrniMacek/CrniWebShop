/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crni.data;

import com.crni.model.Korisnik;
import com.crni.model.Narudzba;
import com.crni.model.Proizvod;
import com.crni.model.Stavka;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author igor
 */
public class Repozitorij {

    private DataSource dataSource;
    private KorisnikRepozitorij korisnikRepozitorij;
    private HistoryLogRepozitorij historyLogRepozitorij;
    private ProizvodRepozitorij proizvodRepozitorij;
    private NarudbaRepozitorij narudbaRepozitorij;
    private StavkaRepozitorij stavkaRepozitoirj;

    public Repozitorij() {
        this.dataSource = DataSourceBase.getDataSource();
    }

    public Korisnik korisnik_AuthenticateUser(String uname, String pwd) throws SQLException {

        korisnikRepozitorij = new KorisnikRepozitorij(dataSource);
        List<Korisnik> korisnici = korisnikRepozitorij.korisnik_Select_All();

        for (Korisnik k : korisnici) {
            if (k.getUname().equals(uname) && k.getPwd().equals(pwd)) {
                historyLogRepozitorij = new HistoryLogRepozitorij(dataSource);
                historyLogRepozitorij.historyLog_Insert_New("KORISNIK AUTHED: " + uname, k.getIDKorisnik());
                return k;
            }
        }
        return null;
    }

    public boolean korisnik_RegisterNewUser(String uname, String pwd, String email) throws SQLException, RemoteException {

        korisnikRepozitorij = new KorisnikRepozitorij(dataSource);
        List<Korisnik> korisnici = korisnikRepozitorij.korisnik_Select_All();

        for (Korisnik k : korisnici) {
            if (k.getUname().equalsIgnoreCase(uname)) {
                throw new RemoteException("Korisničko ime već postoji u bazi podataka, odaberite novo!");
            }
        }
        return korisnikRepozitorij.korisnik_Insert_NewRegistration(uname, pwd, email, 2);

    }

    public List<Proizvod> proizvod_Select_All() throws SQLException {

        proizvodRepozitorij = new ProizvodRepozitorij(dataSource);
        return proizvodRepozitorij.proizvod_Select_All();

    }

    public void korisnik_DeautheticateUser(Korisnik k) throws SQLException {
        historyLogRepozitorij = new HistoryLogRepozitorij(dataSource);
        historyLogRepozitorij.historyLog_Insert_New("KORISNIK DEAUTHED: " + k.getUname(), k.getIDKorisnik());
    }

    public Proizvod proizvod_Select_SingleById(int idProizvod) throws SQLException {
        proizvodRepozitorij = new ProizvodRepozitorij(dataSource);
        return proizvodRepozitorij.proizvod_Select_Single_By_Id(idProizvod);
    }

    public Narudzba narudzba_Insert_NewSingle(Narudzba n, ArrayList<Stavka> stavke) throws SQLException {

        narudbaRepozitorij = new NarudbaRepozitorij(dataSource);
        stavkaRepozitoirj = new StavkaRepozitorij(dataSource);

        int nid = narudbaRepozitorij.narudzba_Insert_Nova(n.getOpisNarudzbe(), new BigDecimal(0), GregorianCalendar.getInstance(), n.getIDKorisnik());

        BigDecimal suma = new BigDecimal(0);

        for (Stavka s : stavke) {
            Double sd = suma.doubleValue();
            sd = sd + s.getCijenaStavkeKN().doubleValue();
            suma = new BigDecimal(sd);
            stavkaRepozitoirj.stavka_Insert_New(s.getCijenaStavkeKN(), s.getKolicinaProizvoda(), s.getOpisStavke(), s.getProizvodID(), nid);
        }

        narudbaRepozitorij.narudzba_Update_SingleById(nid, suma);

        return narudbaRepozitorij.narudzba_Select_SingleById(nid, stavke);
    }

    public Narudzba narudzba_Select(int idNarudzba) throws SQLException {
        narudbaRepozitorij = new NarudbaRepozitorij(dataSource);

        return narudbaRepozitorij.narudzba_Select_AllItems(idNarudzba);
    }

    public void narudzba_Delete_SingleById(int idNarudzba) throws SQLException {
        narudbaRepozitorij = new NarudbaRepozitorij(dataSource);

        narudbaRepozitorij.narudzba_Delete_BYId(idNarudzba);
    }
}
