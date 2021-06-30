package com.esd.histogram.controllers;

import com.esd.histogram.services.StudentsService;
import com.esd.histogram.viewmodels.Histogram;
import com.esd.histogram.viewmodels.Marks;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "HistogramServlet", value = "/")
public class HistogramServlet extends HttpServlet {
    private StudentsService studentsService;

    @Inject
    public HistogramServlet(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Histogram histogram = new Histogram(studentsService.generateHistogram());
        Marks marks = new Marks();

        request.setAttribute("histogram", histogram);
        request.setAttribute("marks", marks);
        request.setAttribute("success", request.getParameter("success"));
        request.setAttribute("error", request.getParameter("error"));

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
