package com.scode.admin.controllers;

import java.io.IOException;

import com.scode.admin.model.Student;
import com.scode.admin.model.Subject;
import com.scode.admin.model.SubjectAssignment;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AddFlightController
 */
@WebServlet("/assignsubject")
public class AssignSubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AssignSubjectController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("isValid") == null || session.getAttribute("isValid") == "false") {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		} else if (request.getParameter("classid") == null || request.getParameter("subjectid") == null) {
//			System.out.println("hello");
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
		} else {
			String sid = request.getParameter("subjectid");
			String cid = request.getParameter("classid");

			SubjectAssignment sa = new SubjectAssignment();
			sa.setCid(cid);
			sa.setSid(sid);

			RequestDispatcher rd = request.getRequestDispatcher("result.jsp");

			if (cid.length() > 6) {
				request.setAttribute("message",
						"Class ID length is greter than 6. Please enter a value less than or equal to 6");
			}
			if (sid.length() > 6) {
				request.setAttribute("message",
						"Subject ID length is greter than 6. Please enter a value less than or equal to 6");
			}
			int result = sa.assignSubject();
			if (result == -1) {
				request.setAttribute("message", "Subject ID " + sid
						+ " does not exists in the database. Please enter a valid value");
			}
			if (result == -2) {
				request.setAttribute("message", "Class ID " + cid
						+ " does not exists in the database. Please enter a valid value");
			}
			if (result == -3) {
				request.setAttribute("message", "Subject ID " + sid
						+ " is already assigned to different class. Please enter a valid value");
			}
			if (result > 0) {
				request.setAttribute("message", "Assignment " + sa.toString() + " created successfully");

			}
			sa.destroy();
			rd.forward(request, response);
//		System.out.println(request.getParameter("date"));
//		System.out.println(flight.toString());
		}
	}

}
