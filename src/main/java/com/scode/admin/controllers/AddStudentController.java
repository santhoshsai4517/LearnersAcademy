package com.scode.admin.controllers;

import java.io.IOException;

import com.scode.admin.model.Student;

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
@WebServlet("/addstudent")
public class AddStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddStudentController() {
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
		} else if (request.getParameter("studentid") == null || request.getParameter("studentname") == null || request.getParameter("classid") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
		} else {
			String id = request.getParameter("studentid");
			String name = request.getParameter("studentname");
			String classid = request.getParameter("classid");

			Student student = new Student();
			student.setId(id);
			student.setName(name);
			student.setClassid(classid);

			RequestDispatcher rd = request.getRequestDispatcher("result.jsp");

			if (id.length() > 10) {
				request.setAttribute("message",
						"Student ID length is greter than 6. Please enter a value less than or equal to 6");
			}
			int result = student.addStudent();
			if (result == -1) {
				request.setAttribute("message", "Student ID " + id
						+ " already exists in the database. Please enter a new value");
			}
			if (result > 0) {
				request.setAttribute("message", "Student " + student.toString() + " created successfully");

			}
			student.destroy();
			rd.forward(request, response);
//		System.out.println(request.getParameter("date"));
//		System.out.println(flight.toString());
		}
	}

}
