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
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author student
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    
    JWTAuthenticationService authentication;
    private final String authenticationCookieName = "login_token";
    private static int userId;
    
    public LoginServlet(){
       authentication = new JWTAuthenticationService();
    }
    
    public Map.Entry<String, String> isAuthenticated(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String token = "";
        
        try {
            System.out.println("Token is: ");
            for (Cookie cookie : cookies){
                System.out.println(cookie.getName());
                if (cookie.getName().equals(authenticationCookieName)){
                    token = cookie.getValue();
                }
            }
        } catch (Exception e){
            
        }
        
        if (!token.isEmpty()){
            try {
                if (authentication.verify(token).getKey()){
                    Map.Entry entry = new AbstractMap.SimpleEntry<String, String>(token, authentication.verify(token).getValue());
                    return entry;
                } else {
                    Map.Entry entry = new AbstractMap.SimpleEntry<String, String>("", "");
                    return entry;
                }
            } catch (UnsupportedEncodingException ex){
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        Map.Entry entry = new AbstractMap.SimpleEntry<String, String>("", "");
        return entry;
    }
    
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
        String token = isAuthenticated(request).getKey();
        String uname = isAuthenticated(request).getValue();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = BusinessUtil.isAuthenticated(username, password, token);
        System.out.println("User: " + user);
        if (user != null){
            Set<Exercise> exercises = BusinessUtil.getExercises(user.getUserid());
//            System.out.println("LoginServlet: "+ exercises);
            user.setExercises(exercises);
            userId = user.getUserid();
            request.setAttribute("user", user);
                        
            token = authentication.createJWT("LoginServlet", username, 100000);
            
            Cookie newCookie = new Cookie(authenticationCookieName, token);
            response.addCookie(newCookie);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("userExercises.jsp");
            requestDispatcher.forward(request, response);
        } else {
            System.out.println("User not found!");
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("userExercises.jsp");
            requestDispatcher.forward(request, response);
        }
    }
    
    public static int getUserId(){
        return userId;
    }
    
    public static void setUserId(int user_id){
        userId = user_id;
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
        return "Login Servlet";
    }// </editor-fold>

}
