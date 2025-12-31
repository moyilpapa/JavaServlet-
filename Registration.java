package com.student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int year = Integer.parseInt(request.getParameter("year"));

        try {
            Connection con = DBUtil.getConnection();
            String sql = "INSERT INTO students(name, email, year) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, year);

            ps.executeUpdate();
            con.close();

            response.sendRedirect("show_all");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: Student already exists or DB issue.");
        }
    }
}