package com.scode.admin.controllers;

import java.io.IOException;

import com.scode.admin.model.Student;
import com.scode.admin.model.TeacherAssignment;

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
@WebServlet("/assignteacher")
public class AssignTeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AssignTeacherController() {
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
		} else if (request.getParameter("teacherid") == null || request.getParameter("subjectid") == null || request.getParameter("classid") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
		} else {
			String tid = request.getParameter("teacherid");
			String sid = request.getParameter("subjectid");
			String cid = request.getParameter("classid");

			TeacherAssignment ta = new TeacherAssignment();
			ta.setCid(cid);
			ta.setSid(sid);
			ta.setTid(tid);

			RequestDispatcher rd = request.getRequestDispatcher("result.jsp");

			if (tid.length() > 6) {
				request.setAttribute("message",
						"Teacher ID length is greter than 6. Please enter a value less than or equal to 6");
			}
			if (sid.length() > 6) {
				request.setAttribute("message",
						"Subject ID length is greter than 6. Please enter a value less than or equal to 6");
			}
			if (cid.length() > 6) {
				request.setAttribute("message",
						"Class ID length is greter than 6. Please enter a value less than or equal to 6");
			}
			int result = ta.assignSubject();
			if (result == -1) {
				request.setAttribute("message", "Subject ID " + sid
						+ " does not exists in the database. Please enter a new value");
			}
			if (result == -2) {
				request.setAttribute("message", "Class ID " + sid
						+ " does not exists in the database. Please enter a new value");
			}

			if (result == -3) {
				request.setAttribute("message", "Teacher ID " + sid
						+ " does not exists in the database. Please enter a new value");
			}
			if (result == -4) {
				request.setAttribute("message", "Subject ID " + sid
						+ " is not assigned to Class ID "+cid);
			}
			if (result == -5) {
				request.setAttribute("message", "Subject ID " + sid
						+ " is for Class ID "+cid+" is already assigned to a teacher");
			}
			if (result > 0) {
				request.setAttribute("message", "Assignmet " + ta.toString() + " created successfully");

			}
			ta.destroy();
			rd.forward(request, response);
//		System.out.println(request.getParameter("date"));
//		System.out.println(flight.toString());
		}
	}

}
