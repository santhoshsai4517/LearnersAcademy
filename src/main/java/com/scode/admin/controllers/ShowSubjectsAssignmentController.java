package com.scode.admin.controllers;

import java.io.IOException;
import java.util.ArrayList;

import com.scode.admin.model.SubjectAssignment;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ShowPlacesController
 */
@WebServlet("/showsubjectassignments")
public class ShowSubjectsAssignmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowSubjectsAssignmentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("isValid") == null || session.getAttribute("isValid") == "false") {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}else {
			SubjectAssignment sa = new SubjectAssignment();
			ArrayList<SubjectAssignment> sas = sa.getAssignments();
			RequestDispatcher rd = request.getRequestDispatcher("showsubjectassign.jsp");
			if(sas.isEmpty()) {
				request.setAttribute("message", "No assignments found in database");
			}else {
				request.setAttribute("message", "Found below subjects in database");
			}
			request.setAttribute("sas", sas);
			sa.destroy();
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
