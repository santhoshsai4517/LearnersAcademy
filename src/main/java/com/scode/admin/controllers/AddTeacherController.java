package com.scode.admin.controllers;

import java.io.IOException;

import com.scode.admin.model.Classes;
import com.scode.admin.model.Teacher;

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
@WebServlet("/addteacher")
public class AddTeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddTeacherController() {
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
		} else if (request.getParameter("teacherid") == null || request.getParameter("teachername") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
		} else {
			String id = request.getParameter("teacherid");
			String name = request.getParameter("teachername");
//			System.out.println(id);
			Teacher teacher = new Teacher();
			teacher.setId(id);
			teacher.setName(name);

			RequestDispatcher rd = request.getRequestDispatcher("result.jsp");

			if (id.length() > 6) {
				request.setAttribute("message",
						"Teacher ID length is greter than 6. Please enter a value less than or equal to 6");
			}
			int result = teacher.addTeacher();
			if (result == -1) {
				request.setAttribute("message", "Teacher ID " + id
						+ " already exists in the database. Please enter a new value");
			}
			if (result > 0) {
				request.setAttribute("message", "Teacher " + teacher.toString() + " created successfully");

			}
			teacher.destroy();
			rd.forward(request, response);
//		System.out.println(request.getParameter("date"));
//		System.out.println(flight.toString());
		}
	}

}
