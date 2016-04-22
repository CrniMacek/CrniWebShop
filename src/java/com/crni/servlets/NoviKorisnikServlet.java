/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crni.servlets;

import com.crni.data.Repozitorij;
import java.io.IOException;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author igor
 */
public class NoviKorisnikServlet extends HttpServlet {

    private static Repozitorij repo;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoviKorisnikServlet() {
        super();
        // 
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        handleRequest(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        handleRequest(request, response);

    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uname, pwd, email;

        uname = request.getParameter("username");
        pwd = request.getParameter("password");
        email = request.getParameter("email");

        try {

            repo = new Repozitorij();
            boolean isRegOK = repo.korisnik_RegisterNewUser(uname, pwd, email);
            request.setAttribute("isRegOK", isRegOK);

        } catch (RemoteException e) {
            response.sendError(504, "REMOTE INTERNA GREŠKA!\n\n" + e.getMessage());
            return;
        } catch (SQLException ex) {
            Logger.getLogger(NoviKorisnikServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        //RequestDispatcher r = request.getRequestDispatcher("/Home");
        //r.forward(request, response);
    }
}
