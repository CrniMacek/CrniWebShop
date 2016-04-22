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
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author igor
 */
public class MojProfilServlet extends HttpServlet {

    private static Repozitorij repo;

    public MojProfilServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession s = request.getSession();
        if (s.getAttribute("AuthedUser") == null) {
            response.sendError(405, "ZABRANJEN PRISTUP NEREGISTRIRANIM KORISNICIMA! \n\n");
            return;
        }
        
        repo = new Repozitorij();
        Korisnik k = (Korisnik)s.getAttribute("AuthedUser");
    }
}
