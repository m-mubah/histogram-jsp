package com.esd.histogram.controllers;

import com.esd.histogram.services.StudentsService;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(name = "FileUploadServlet", value = "/files/upload")
public class FileUploadServlet extends HttpServlet {
    private StudentsService studentsService;

    @Inject
    public FileUploadServlet(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");

        if (!filePart.getContentType().equalsIgnoreCase("text/plain")) {
            response.sendRedirect(request.getContextPath() + "/?error=Only plain-text .txt files allowed!");
            return;
        }

        InputStream fileContent = filePart.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileContent));

        String line = bufferedReader.readLine();
        List<Integer> data = new ArrayList();

        try {
            while (line != null) {
                data.add(Integer.parseInt(line));

                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/?error=An error occured: \n" + e.getMessage());
        }

        studentsService.createMany(data);

        response.sendRedirect(request.getContextPath() + "/?success=File uploaded successfully!");
    }
}
