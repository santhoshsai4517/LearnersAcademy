package com.scode.admin.controllers;

import java.io.IOException;
import java.util.ArrayList;

import com.scode.admin.model.Classes;
import com.scode.admin.model.Student;
import com.scode.admin.model.Subject;
import com.scode.admin.model.SubjectAssignment;
import com.scode.admin.model.Teacher;

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
@WebServlet("/showclassinfo")
public class ShowClassInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowClassInfoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("isValid") == null || session.getAttribute("isValid") == "false") {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		} else if (request.getParameter("classid") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
		} else {
			String id = request.getParameter("classid");
			Classes ci = new Classes();
			int result = ci.findClass(id);
			RequestDispatcher rd = request.getRequestDispatcher("displayclassinfo.jsp");
			if (result == -1) {
				request.setAttribute("message", "No class info found in database");
				request.setAttribute("isFound", false);
				request.setAttribute("teachers", "");
				ci.destroy();
				rd.forward(request, response);
			} else {
				request.setAttribute("isFound", true);
				Teacher teacher = new Teacher();
				ArrayList<Teacher> teachers = teacher.getTeachers(id);
				if(teachers.isEmpty()) {
					request.setAttribute("tmessage", "No teachers are assigned to this class");
				}else {
					request.setAttribute("tmessage", "Found below teachers assigned to this class");
				}
				request.setAttribute("teachers", teachers);
				Subject subject = new Subject();
				ArrayList<Subject> subjects = subject.getSubjects(id);
				if(subjects.isEmpty()) {
					request.setAttribute("smessage", "No subjects are assigned to this class");
				}else {
					request.setAttribute("smessage", "Found below subjects assigned to this class");
				}
				request.setAttribute("subjects", subjects);
				Student stu = new Student();
				ArrayList<Student> students = stu.getStudents(id);
				if(students.isEmpty()) {
					request.setAttribute("stumessage", "No students are assigned to this class");
				}else {
					request.setAttribute("stumessage", "Found below students assigned to this class");
				}
				request.setAttribute("students", students);
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
