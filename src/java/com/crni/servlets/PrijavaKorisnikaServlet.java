/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crni.servlets;

import com.crni.data.Repozitorij;
import com.crni.model.Korisnik;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

/**
 *
 * @author igor
 */
public class PrijavaKorisnikaServlet extends HttpServlet {

    private static Repozitorij repo;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrijavaKorisnikaServlet() {
        super();
        // 
    }

    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //handleRequest(request, response);
        response.sendRedirect("Home");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);

    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uname, pwd;

        uname = request.getParameter("username");
        pwd = request.getParameter("password");

        if (uname == null || pwd == null) {
            response.sendError(500, "VALIDACIJSKA GREŠKA!\n\nNiste unijeli potrebne podatke!");
        }

        try {

            repo = new Repozitorij();
            Korisnik k;
            k = repo.korisnik_AuthenticateUser(uname, pwd);

            if (k != null) {
                //spremi korisnika u session ako je prijava uredu
                request.getSession().setAttribute("AuthedUser", k);
            } else {
                response.sendError(500, "VALIDACIJSKA GREŠKA!\n\nNiste unijeli točne autentikacijske podatke!");
            }
        } catch (RemoteException e) {
            response.sendError(504, "REMOTE INTERNA GREŠKA!\n\n" + e.getMessage());
            return;
        } catch (SQLException ex) {
            Logger.getLogger(PrijavaKorisnikaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher r = request.getRequestDispatcher("Home");
        r.include(request, response);

    }

}
