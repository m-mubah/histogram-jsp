package com.esd.histogram.controllers;

import com.esd.histogram.models.Student;
import com.esd.histogram.services.StudentsService;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CreateStudentServlet", value = "/students/create")
public class CreateStudentServlet extends HttpServlet {
    private StudentsService studentsService;

    @Inject
    public CreateStudentServlet(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        studentsService.create(Integer.parseInt(request.getParameter("marks")));

        response.sendRedirect(request.getContextPath() + "/?success=Student created successfully!");
    }
}
