package com.scode.admin.controllers;

import java.io.IOException;

import com.scode.admin.model.Subject;

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
@WebServlet("/addsubject")
public class AddSubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddSubjectController() {
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
		} else if (request.getParameter("subjectid") == null || request.getParameter("subjectname") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
		} else {
			String id = request.getParameter("subjectid");
			String name = request.getParameter("subjectname");

			Subject subject = new Subject();
			subject.setId(id);
			subject.setName(name);

			RequestDispatcher rd = request.getRequestDispatcher("result.jsp");

			if (id.length() > 6) {
				request.setAttribute("message",
						"Subject ID length is greter than 6. Please enter a value less than or equal to 6");
			}
			if (name.length() > 25) {
				request.setAttribute("message",
						"Subject ID length is greter than 25. Please enter a value less than or equal to 25");
			}
			int result = subject.addSubject();
			if (result == -1) {
				request.setAttribute("message", "Subject ID " + id
						+ " already exists in the database. Please enter a new value");
			}
			if (result == -2) {
				request.setAttribute("message", "Subject name " + name
						+ " already exists in the database. Please enter a new value");
			}
			if (result > 0) {
				request.setAttribute("message", "Subject " + subject.toString() + " created successfully");

			}
			subject.destroy();
			rd.forward(request, response);
//		System.out.println(request.getParameter("date"));
//		System.out.println(flight.toString());
		}
	}

}
