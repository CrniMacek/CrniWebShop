/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crni.servlets;

import com.crni.data.Repozitorij;
import com.crni.model.Korisnik;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

/**
 *
 * @author igor
 */
public class OdjavaKorisnikaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static Repozitorij repo;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OdjavaKorisnikaServlet() {
        super();
        // 
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        deauth_user(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        deauth_user(request, response);
    }

    private void deauth_user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            HttpSession s = request.getSession();
            if (s.getAttribute("AuthedUser") == null) {
                response.sendError(405, "ZABRANJEN PRISTUP NEREGISTRIANIM KORISNICIMA!\n\n");
                return;
            }
            repo = new Repozitorij();
            Korisnik k = (Korisnik) s.getAttribute("AuthedUser");
            repo.korisnik_DeautheticateUser(k);
            s.setAttribute("AuthedUser", null);

        } catch (RemoteException e) {
            response.sendError(504, "REMOTE INTERNA GREŠKA!\n\n" + e.getMessage());
            return;
        } catch (SQLException ex) {
            Logger.getLogger(OdjavaKorisnikaServlet.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }
}
