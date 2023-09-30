package com.scode.admin.controllers;

import java.io.IOException;
import java.util.ArrayList;

import com.scode.admin.model.Teacher;
import com.scode.admin.model.TeacherAssignment;

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
@WebServlet("/showteacherassignments")
public class ShowTeachersAssignmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowTeachersAssignmentController() {
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
			TeacherAssignment ta = new TeacherAssignment();
			ArrayList<TeacherAssignment> tas = ta.getAssignments();
			RequestDispatcher rd = request.getRequestDispatcher("showteacherassign.jsp");
			if(tas.isEmpty()) {
				request.setAttribute("message", "No assignments found in database");
			}else {
				request.setAttribute("message", "Found below teachers in database");
			}
			request.setAttribute("tas", tas);
			ta.destroy();
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
