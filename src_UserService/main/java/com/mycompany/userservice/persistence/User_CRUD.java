/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.userservice.persistence;

import com.mycompany.userservice.helper.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author student
 */
public class User_CRUD {
    private static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/User_REPS?autoReconnect=true&useSSL=false&serverTimezone=UTC", "root", "student");
            System.out.println("Connection established");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public static User getUser(String username) {
        User bean = null;
        try {
            Connection con = getCon();

            String q = "select * from User_REPS.USER WHERE username LIKE \"" + username + "\"";

            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int userid = rs.getInt("user_id");
                String fname = rs.getString("firstname");
                String lname = rs.getString("lastname");
                String phone = rs.getString("phone");
                String uname = rs.getString("username");
                String pass = rs.getString("password");
                bean = new User(userid, fname, lname, phone, uname, pass);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return bean;
    }
    
        public static User getUserById(int userId) {
        User bean = null;
        try {
            Connection con = getCon();

            String q = "select * from User_REPS.USER WHERE user_id LIKE \"" + userId + "\"";

            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int userid = rs.getInt("user_id");
                String fname = rs.getString("firstname");
                String lname = rs.getString("lastname");
                String phone = rs.getString("phone");
                String uname = rs.getString("username");
                String pass = rs.getString("password");
                bean = new User(userid, fname, lname, phone, uname, pass);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return bean;
    }
    
}
