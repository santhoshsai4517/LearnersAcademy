package com.scode.admin.controllers;

import java.io.IOException;

import com.scode.admin.model.Classes;

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
@WebServlet("/addclass")
public class AddClassController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddClassController() {
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
		} else if (request.getParameter("classid") == null || request.getParameter("classname") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
		} else {
			String id = request.getParameter("classid");
			String name = request.getParameter("classname");
//			System.out.println(id);
			Classes classes = new Classes();
			classes.setId(id);
			classes.setName(name);

			RequestDispatcher rd = request.getRequestDispatcher("result.jsp");

			if (id.length() > 6) {
				request.setAttribute("message",
						"Class ID length is greter than 6. Please enter a value less than or equal to 6");
			}
			if (name.length() > 10) {
				request.setAttribute("message",
						"Class name length is greter than 10. Please enter a value less than or equal to 10");
			}
			int result = classes.addClass();
			if (result == -1) {
				request.setAttribute("message", "Class ID " + id
						+ " already exists in the database. Please enter a new value");
			}
			if (result == -2) {
				request.setAttribute("message", "Class name " + name
						+ " already exists in the database. Please enter a new value");
			}
			if (result > 0) {
				request.setAttribute("message", "Class " + classes.toString() + " created successfully");

			}
			classes.destroy();
			rd.forward(request, response);
//		System.out.println(request.getParameter("date"));
//		System.out.println(flight.toString());
		}
	}

}
