/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.frontend.frontend;

import com.mycompany.frontend.business.BusinessUtil;
import com.mycompany.frontend.helper.Exercise;
import com.mycompany.frontend.helper.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author student
 */
@WebServlet(name = "AddExercise", urlPatterns = {"/AddExercise"})
public class AddExercise extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String exerciseName = (String) request.getParameter("exerciseName");
        String exerciseDescription = (String) request.getParameter("exerciseDescription");
        String startDateTime = (String) request.getParameter("startDateTime");
        String endDateTime = (String) request.getParameter("endDateTime");

//        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()){
//            System.out.println("Key: "+entry.getKey() + ", Value: " + entry.getValue());
//        }
        if (exerciseName == null || exerciseDescription == null || startDateTime == null || endDateTime == null) {
            System.out.println("INVALID INPUT");
        } else {
            //        System.out.println(exerciseName + ", " + exerciseDescription + "\n" + startDateTime + "\n" + endDateTime);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            LocalDateTime startDt = LocalDateTime.parse(startDateTime, formatter);
            LocalDateTime endDt = LocalDateTime.parse(endDateTime, formatter);

//            User currentUser = (User) request.getSession().getAttribute("user");
//            Exercise exercise = new Exercise(exerciseName, exerciseDescription, startDt, endDt);
            Exercise exercise = new Exercise();
            exercise.setName(exerciseName);
            exercise.setDescription(exerciseDescription);
            exercise.setStartDate(startDt);
            exercise.setEndDate(endDt);
            System.out.println("exercise to add: "+ exercise);
            
            exercise = BusinessUtil.addExercise(exercise, LoginServlet.getUserId());
            System.out.println("SAVING EXERCISE");
//            ExerciseService.saveExercise(exercise, currentUser.getUserid());
//            User currentUser = (User) request.getAttribute("user");
//            System.out.println("User within AddExercise: " + currentUser);

            User currentUser = BusinessUtil.getCurrentUser(LoginServlet.getUserId());
            currentUser.setExercises(BusinessUtil.getExercises(currentUser.getUserid()));
            System.out.println("Current user: "+ currentUser);
            request.setAttribute("user", currentUser);
//            request.getSession().setAttribute("user", currentUser);
            RequestDispatcher rd = request.getRequestDispatcher("userExercises.jsp");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
